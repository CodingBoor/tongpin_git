package com.qmx163.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.qmx163.aidl.IBackService;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.SocketMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BackService extends Service {
    private static final String TAG = "BackService";
    /**
     * 心跳检测时间
     */
    private static final long HEART_BEAT_RATE = 60 * 1000;
    /**
     * 主机IP地址
     */
    private static final String HOST = "47.94.208.70";
    /**
     * 端口号
     */
    public static final int PORT = 42081;
    /**
     * 消息广播
     */
    public static final String MESSAGE_ACTION = "org.feng.message_ACTION";

    /**
     * 连接成功消息广播
     */
    public static final String CONNECT_SUCCESS = "org.feng.connect_success_ACTION";

    /**
     * 未读消息总数
     * {"code":"NEW_MESSAGE", "message":"未读消息总数", "data":"0"}
     */
    public static final String NEW_MESSAGE = "org.feng.new_success_ACTION";

    /**
     * 直播开始后收到的id号
     *{"code":"MEMBER_ONLINE_TRACK", "message":"", "data":"8a7d3084c06c11e7949000163e06d055"}
     */
    public static final String LIVE_ID = "org.feng.live_id_ACTION";




    /**
     * 提问返回广播
     */
    public static final String HEARBEAT_ONLINE_QUESTION = "org.feng.question_ACTION";


    /**
     * 点击播放返回
     */
    public static final String MEMBER_ONLINE_TRACK = "org.feng.track_ACTION";

    /**
     * 直播消息
     */
    public static final String HEARBEAT_ONLINE_MESSAGES = "org.feng.message_ACTION";
    /**
     * 心跳广播
     */
    public static final String HEART_BEAT_ACTION = "org.feng.heart_beat_ACTION";

    private long sendTime = 0L;

    /**
     * 弱引用 在引用对象的同时允许对垃圾对象进行回收
     */
    private WeakReference<Socket> mSocket;

    private ReadThread mReadThread;

    private IBackService.Stub iBackService = new IBackService.Stub() {
        @Override
        public boolean sendMessage(String message) throws RemoteException {
            return sendMsg(message);
        }
    };

    @Override
    public IBinder onBind(Intent arg0) {
        return (IBinder) iBackService;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         *不建议的写法，暂时这样写
         */
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        new InitSocketThread().start();
    }

    // 发送心跳包
    private Handler mHandler = new Handler();
    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                boolean isSuccess = sendMsg("{\"code\":\"HEARBEAT\", \"message\":\"心跳包\", \"data\": null}\n");// 就发送一个\r\n过去, 如果发送失败，就重新初始化一个socket
                if (!isSuccess) {
                    mHandler.removeCallbacks(heartBeatRunnable);
                    mReadThread.release();
                    releaseLastSocket(mSocket);
                    new InitSocketThread().start();
                }
            }
            mHandler.postDelayed(this, HEART_BEAT_RATE);
        }
    };

    public boolean sendMsg(String msg) {
        if (null == mSocket || null == mSocket.get()) {
            return false;
        }
        Socket soc = mSocket.get();
        try {
            if (!soc.isClosed() && !soc.isOutputShutdown()) {
                OutputStream os = soc.getOutputStream();
                String message = msg + "\r\n";
                os.write(message.getBytes());
                os.flush();
                sendTime = System.currentTimeMillis();// 每次发送成功数据，就改一下最后成功发送的时间，节省心跳间隔时间
                Log.i(TAG, "发送消息成功的时间：" + sendTime);
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // 初始化socket
    private void initSocket() throws UnknownHostException, IOException {
        Socket socket = new Socket(HOST, PORT);
        mSocket = new WeakReference<Socket>(socket);
        mReadThread = new ReadThread(socket);
        mReadThread.start();
        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);// 初始化成功后，就准备发送心跳包
    }

    // 释放socket
    private void releaseLastSocket(WeakReference<Socket> mSocket) {
        try {
            if (null != mSocket) {
                Socket sk = mSocket.get();
                if (sk != null) {
                    if (!sk.isClosed()) {
                        sk.close();
                    }
                    sk = null;
                }
                mSocket = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class InitSocketThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                initSocket();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ReadThread extends Thread {
        private WeakReference<Socket> mWeakSocket;
        private boolean isStart = true;

        public ReadThread(Socket socket) {
            mWeakSocket = new WeakReference<Socket>(socket);
        }

        public void release() {
            isStart = false;
            releaseLastSocket(mWeakSocket);
        }

        @SuppressLint("NewApi")
        @Override
        public void run() {
            super.run();
            Socket socket = mWeakSocket.get();
            if (null != socket) {


                try {
                    InputStream is = socket.getInputStream();
                    byte[] buffer = new byte[1024 * 8];
                    int length = 0;
                    while (!socket.isClosed() && !socket.isInputShutdown()
                            && isStart && ((length = is.read(buffer)) != -1)) {
                        if (length > 0) {
                            String message = new String(Arrays.copyOf(buffer,
                                    length)).trim();
                            Log.i(TAG, "收到服务器发送来的消息：" + message);
                            // 收到服务器过来的消息，就通过Broadcast发送出去
                            if (message.equals("ok")) {// 处理心跳回复
                                Intent intent = new Intent(HEART_BEAT_ACTION);
                                sendBroadcast(intent);
                            } else {
                                // 其他消息回复
//                                String format = "[%s]";
//                                String messageTrans = message.replace("}{", "},{");
//                                messageTrans = messageTrans.format(format, messageTrans);

                                String messageTrans = message.replace("}{", "};{");
                                List<String> messageList = Arrays.asList(messageTrans.split(";"));


                                try {
                                    Gson gson = new Gson();
                                    for (int i = 0; i < messageList.size(); i++) {
                                        Log.i(TAG, "收到服务器发送来的消息messagelist：" + messageList.get(i));
                                        SocketMessage messageSocket = gson.fromJson(messageList.get(i), SocketMessage.class);
                                        if (TextUtils.equals(Constants.SOCKET_SUCCESS, messageSocket.getCode())) {  //登录成功
                                            Intent intent = new Intent(CONNECT_SUCCESS);
                                            intent.putExtra("message", messageList.get(i));
                                            sendBroadcast(intent);
                                        }else if (TextUtils.equals(Constants.SOCKET_NEW_MESSAGE,messageSocket.getCode())){  //未读消息总数
                                            Intent intent = new Intent(NEW_MESSAGE);
                                            intent.putExtra("message",messageList.get(i));
                                            sendBroadcast(intent);
                                        }else if (TextUtils.equals(Constants.SOCKET_MEMBER_ONLINE_TRACK,messageSocket.getCode())){  //直播开始，收到id号
                                            Intent intent = new Intent(LIVE_ID);
                                            intent.putExtra("message",messageList.get(i));
                                            sendBroadcast(intent);
                                        }

                                        else {
                                            Intent intent = new Intent(MESSAGE_ACTION);
                                            intent.putExtra("message", messageList.get(i));
                                            sendBroadcast(intent);
                                        }
                                    }
                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                }

//                                Gson gson = new Gson();
//                                SocketMessage messageSocket = gson.fromJson(message,SocketMessage.class);
//                                //连接成功返回消息
//                                if (TextUtils.equals(Constants.SOCKET_SUCCESS,messageSocket.getCode())){
//                                    Intent intent = new Intent(CONNECT_SUCCESS);
//                                    intent.putExtra("message", message);
//                                    sendBroadcast(intent);
//                                }else {
//
//                                Intent intent = new Intent(MESSAGE_ACTION);
//                                intent.putExtra("message", message);
//                                sendBroadcast(intent);
//                                }


                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


//                try {
//                    // 步骤1：创建输入流对象InputStream
//                    InputStream  is = socket.getInputStream();
//                    // 输入流读取器对象
//                    InputStreamReader isr ;
//                    BufferedReader br ;
//
//                    // 步骤2：创建输入流读取器对象 并传入输入流对象
//                    // 该对象作用：获取服务器返回的数据
//                    isr = new InputStreamReader(is);
//                    br = new BufferedReader(isr);
//
//                    // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
//                   String response = br.readLine();
//                    Log.i(TAG, "收到服务器发送来的消息：" + response);
//
////                    // 步骤4:通知主线程,将接收的消息显示到界面
////                    Message msg = Message.obtain();
////                    msg.what = 0;
////                    mMainHandler.sendMessage(msg);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


            }
        }
    }


    public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {
//    private static Logger logger = LoggerFactory.getLogger(HelloClientIntHandler.class);

        // 接收server端的消息，并打印出来
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        logger.info("HelloClientIntHandler.channelRead");
            ByteBuf result = (ByteBuf) msg;
            byte[] result1 = new byte[result.readableBytes()];
            result.readBytes(result1);
            System.out.println("Server said:" + new String(result1));
            result.release();
        }

        // 连接成功后，向server发送消息
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        logger.info("HelloClientIntHandler.channelActive");
            String msg = "Are you ok?";
            ByteBuf encoded = ctx.alloc().buffer(4 * msg.length());
            encoded.writeBytes(msg.getBytes());
            ctx.write(encoded);
            ctx.flush();
        }
    }

}
