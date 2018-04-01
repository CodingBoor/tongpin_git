package com.qmx163.activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qmx163.R;
import com.qmx163.activity.Found.FoundFragment;
import com.qmx163.activity.Me.MeFrag;
import com.qmx163.activity.Message.MessageFrag;
import com.qmx163.activity.StudyToday.StudyTodayMainFrag;
import com.qmx163.aidl.IBackService;
import com.qmx163.base.BaseAc;
import com.qmx163.bottomNavigation.BottomNavigationBar;
import com.qmx163.bottomNavigation.BottomNavigationItem;
import com.qmx163.bottomNavigation.TextBadgeItem;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.MessageSize;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.listener.MessageChangeListener;
import com.qmx163.net.ApiCallback;
import com.qmx163.service.BackService;
import com.qmx163.service.JpushReceive;
import com.qmx163.util.PrefUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

public class MainActivity extends BaseAc implements BottomNavigationBar.OnTabSelectedListener, MessageChangeListener {


    @BindView(R.id.rl_fragment)
    RelativeLayout rlFragment;
    @BindView(R.id.bottom_navigation_bar)
    com.qmx163.bottomNavigation.BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    private FragmentManager fManager;
    private long clickTime = 0; // 第一次点击的时间
    private lessonPeriodsDetail.DataBean lessonDeta;

    private boolean jPushMessage = false;  //是否为推送跳转过来
    private boolean jPushComment = false;
//    private boolean islogin = false; //退出登录后再登录

    TextBadgeItem mBadgeItem = new TextBadgeItem(); //显示消息条数控件


    public static IBackService mIBackService;
    private Intent mServiceIntent;
    private boolean today = true;
    private boolean message = true;
    private boolean me = true;
    private boolean dialogShow = true;


//    // 注册广播
    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
//        intentFilter.addAction(BackService.MESSAGE_ACTION);
        intentFilter.addAction(BackService.CONNECT_SUCCESS);
        intentFilter.addAction(BackService.NEW_MESSAGE);
        registerReceiver(mReceiver, intentFilter);
    }

    //
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String message = intent.getStringExtra("message");
            // 消息广播
            if (action.equals(BackService.CONNECT_SUCCESS)) { //连接成功
//                    String sendMessage = Constants.socketLogin(lessonDeta.getLessonId(), lessonDeta.getId(), PrefUtils.getString(MainActivity.this, Constants.USER_ID, ""));
//                    try {
//                        Log.i("---------", "发送消息为：" + sendMessage);
//                        if (mIBackService == null) {
//                            Toast.makeText(MainActivity.this,
//                                    "socket连接失败", Toast.LENGTH_SHORT).show();
//                        } else {
//                            boolean isSend = mIBackService.sendMessage(sendMessage);
////                        Toast.makeText(getActivity(),
////                                isSend ? "success" : "fail", Toast.LENGTH_SHORT)
////                                .show();
//                        }
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }


                /**
                 * 定时请求发送socket消息
                 * 太消耗资源，所以取消
                 */
//                final Handler handler = new Handler();
//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        // TODO Auto-generated method stub
//                        //要做的事情
//                        String sendMessage = Constants.socketNewMessage(PrefUtils.getString(MainActivity.this, Constants.USER_ID, ""));
//                        try {
//                            Log.i("---------", "发送定时消息为：" + sendMessage);
//                            if (mIBackService == null) {
//                                Toast.makeText(MainActivity.this,
//                                        "socket连接失败", Toast.LENGTH_SHORT).show();
//                            } else {
//                                boolean isSend = mIBackService.sendMessage(sendMessage);
//                            }
//                        } catch (RemoteException e) {
//                            e.printStackTrace();
//                        }
//                        handler.postDelayed(this, 1000 * 60 * 2);
//                    }
//                };
//
//                handler.postDelayed(runnable, 1000 * 60 * 2);


            } else if (action.equals(BackService.NEW_MESSAGE)) { //获取未读消息条数
                /**
                 * 取消
                 */
//                Gson gson = new Gson();
//                SocketMessage messageCount = gson.fromJson(message, SocketMessage.class);
//                String messageSize = (String) messageCount.getData();
//                if (!messageCount.getData().equals("0")) {
////                    mBadgeItem.setBackgroundColor(Color.RED);
////                    mBadgeItem.setText(messageSize);
////                    showToas("未读: " + messageSize);
//                }

            } else if (action.equals(BackService.MESSAGE_ACTION)) { //收到消息


            }
        }
    };

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 未连接为空
            mIBackService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 已连接
            mIBackService = IBackService.Stub.asInterface(service);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        jPushComment = getIntent().getBooleanExtra("jpush_comment", false);
        jPushMessage = getIntent().getBooleanExtra("jpush_message", false);
        if (!PrefUtils.getString(MainActivity.this,Constants.USER_TOKEN,"").equals("")){

        //获取未读消息总数
        getMessageCount();
        }

        //获取app属性
        getAppConfit();


        mBadgeItem.setBackgroundColor(Color.TRANSPARENT);

        /**
         * socket
         */
        socketLine();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String packageName = getPackageName();
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + packageName));
                startActivity(intent);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.color_course_yellow));
            }
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


//        EventBus.getDefault().register(this);
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEnableGesture(false);

        MeFrag = new MeFrag();
        MessageFrag = new MessageFrag();
        FoundFragment = new FoundFragment();
//        FocusOnFrag = new FocusOnFrag();
        StudyTodayMainFrag = new StudyTodayMainFrag();
        fManager = getSupportFragmentManager();
        fManager.beginTransaction().add(R.id.rl_fragment, FoundFragment, "陪伴教育").commitAllowingStateLoss();
//        fManager.beginTransaction().add(R.id.rl_fragment, FocusOnFrag, "关注").commitAllowingStateLoss();
//        fManager.beginTransaction().add(R.id.rl_fragment, StudyTodayMainFrag, "今日学习").commitAllowingStateLoss();
//        fManager.beginTransaction().add(R.id.rl_fragment, MessageFrag, "消息").commitAllowingStateLoss();
//        fManager.beginTransaction().add(R.id.rl_fragment, MeFrag, "我的").commitAllowingStateLoss();

        isCheck(false, false, false, true);


        MessageFrag.setMessageChangeListener(this);
        initbuttonbar();
    }

    private void getAppConfit() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("rq", "/app/getAppConfig");
        addSubscription(apiStores.getAppConfig(params), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                if ("200".equals(model.getCode())) {
                    RegisteredEn.DataBean appConfit = model.getData();
                    PrefUtils.setString(MainActivity.this, Constants.APP_VERSION, appConfit.getAppVersion());
                    PrefUtils.setString(MainActivity.this, Constants.APP_STUDY_BG, appConfit.getPersonalBackground());
                }
            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });


    }


    /**
     * 获取未读消息总数
     */
    private void getMessageCount() {
        addSubscription(apiStores.getNotReadMessageCount(PrefUtils.getString(MainActivity.this, Constants.USER_ID, "")), new ApiCallback<MessageSize>() {
            @Override
            public void onSuccess(MessageSize model) {
                int messageCount = model.getData();

                if (messageCount != 0) {
                    mBadgeItem.setBackgroundColor(Color.RED);
                    mBadgeItem.setBorderWidth(0);
                    mBadgeItem.setBorderColor(Color.RED);
                    mBadgeItem.setTextColor(Color.RED);
                    mBadgeItem.setText(messageCount + "");

                }
            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    public void isCheck(Boolean ischeck, Boolean ischeck1, Boolean ischeck2, Boolean ischeck3) {
        if (ischeck) {
            if (me) {
                fManager.beginTransaction().add(R.id.rl_fragment, MeFrag, "我的").commitAllowingStateLoss();
                me = false;
            }
            fManager.beginTransaction().show(MeFrag).commitAllowingStateLoss();
        } else {
            fManager.beginTransaction().hide(MeFrag).commitAllowingStateLoss();
        }
        if (ischeck2) {
            if (today) {
                fManager.beginTransaction().add(R.id.rl_fragment, StudyTodayMainFrag, "今日学习").commitAllowingStateLoss();
                today = false;
            }
            fManager.beginTransaction().show(StudyTodayMainFrag).commitAllowingStateLoss();
        } else {
            fManager.beginTransaction().hide(StudyTodayMainFrag).commitAllowingStateLoss();
        }
        if (ischeck1) {
            if (message) {
                fManager.beginTransaction().add(R.id.rl_fragment, MessageFrag, "消息").commitAllowingStateLoss();
                message = false;
            }
            fManager.beginTransaction().show(MessageFrag).commitAllowingStateLoss();
        } else {
            fManager.beginTransaction().hide(MessageFrag).commitAllowingStateLoss();
        }
        if (ischeck3) {
            fManager.beginTransaction().show(FoundFragment).commitAllowingStateLoss();
        } else {
            fManager.beginTransaction().hide(FoundFragment).commitAllowingStateLoss();
        }
    }

    com.qmx163.activity.Me.MeFrag MeFrag;
    com.qmx163.activity.Message.MessageFrag MessageFrag;
    //    com.qmx163.activity.Found.FoundFrag FoundFrag;
    com.qmx163.activity.Found.FoundFragment FoundFragment;
    //    com.qmx163.activity.FocusOn.FocusOnFrag FocusOnFrag;
//    public static com.qmx163.activity.StudyToday.StudyTodayMainFrag StudyTodayMaFrag;
    public static com.qmx163.activity.StudyToday.StudyTodayMainFrag StudyTodayMainFrag;

    /**
     * 初始底部导航栏
     * <p>
     * Created by 邓靖 on  2017/3/14  17:58
     */
    private void initbuttonbar() {//点中图片，未点中图片，点中文字颜色
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.live_icon_on, "直播").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.live_icon)).setActiveColorResource(R.color.juhuan))
                .addItem(new BottomNavigationItem(R.mipmap.study_on_icon, "今日学习").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.study_icon)).setActiveColorResource(R.color.juhuan))
                .addItem(new BottomNavigationItem(R.mipmap.messagechaek, "消息").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.message)).setActiveColorResource(R.color.juhuan).setBadgeItem(mBadgeItem))
                .addItem(new BottomNavigationItem(R.mipmap.mecheck, "我的").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.me)).setActiveColorResource(R.color.juhuan))
                .initialise();//所有的设置需在调用该方法前完成
        bottomNavigationBar.setTabSelectedListener(this);
        if (jPushComment) {
            jPushComment = false;
            bottomNavigationBar.selectTab(1);
        } else if (jPushMessage) {
            jPushMessage = false;
            bottomNavigationBar.selectTab(2);
        }
    }


//    /**
//     * 网络请求
//     * //http://116.236.172.102:8090/3abizapp/services/bizAppService?data=Ql7GP9g56TGlaL5zX7xmHw==&methodcode=105
//     * Created by 邓靖 on  2017/3/6  14:34
//     */
//    private void get1301() {
//        showProgressDialog();
//        addSubscription(apiStores.getcede("Ql7GP9g56TGlaL5zX7xmHw==", 105),
//                new ApiCallback<BaseBean<ArrayList<CardInfo>>>() {
//                    @Override
//                    public void onSuccess(BaseBean<ArrayList<CardInfo>> model) {
//                        showToas(model.getMessage());
//                        showsnackbar(model.getMessage());
//                        //   showBsanck(model.getMessage());
//                        bean = new ArrayList<CardInfo>();
//                        bean = model.getResult();
//                        tv1.setText(model.getMessage() + "---" + bean.size() + "条");
//
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        showToas(msg);
//                        Snackbar.make(tv1, msg, Snackbar.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        dismissProgressDialog();
//                    }
//                });
//    }

    @Override
    public void onTabSelected(int position) {
String userToken = PrefUtils.getString(this, Constants.USER_TOKEN, "");
if (TextUtils.equals(userToken,"")&&dialogShow){
    dialogShow = false;
    bottomNavigationBar.selectTab(0);
        new AlertDialog.Builder(this)
                .setMessage(R.string.me_check_out)
                .setCancelable(false)
                .setNegativeButton(R.string.me_sure, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                        intent2.putExtra("check_user", true);
                        startAc(intent2);
                        dialogShow = true;
//                        finish();
                    }
                })
                .setNeutralButton(R.string.me_cancle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dialogShow = true;
                        return;
                    }
                })
                .create().show();
}else {

        switch (position) {
            case 0:
                isCheck(false, false, false, true);
                break;
            case 1:
                isCheck(false, false, true, false);
                break;
            case 2:
                isCheck(false, true, false, false);
                break;
            case 3:
                isCheck(true, false, false, false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    //透明状态栏
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                        //透明导航栏
//                        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                    }
                }
                break;
//            case 3:
//                isCheck(true, false, false, false);
//                break;
        }
}
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 是否触发按键为back键
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        } else { // 如果不是back键正常响应
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            Toast.makeText(this, "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
        } else {
            this.clearActivity();
        }
    }

    /**
     * socket长连接
     */
    private void socketLine() {
        mServiceIntent = new Intent(this, BackService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(mServiceIntent, conn, BIND_AUTO_CREATE);
        // 开始服务
        registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        getMessageCount();
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        islogin = intent.getBooleanExtra("islogin",false);
//        if (islogin){
//            islogin = false;
////            fManager.beginTransaction().remove(StudyTodayMainFrag).commit();
//            me = true;
//            message = true;
////            today = true;
//            MeFrag = new MeFrag();
//            MessageFrag = new MessageFrag();
//            FoundFragment = new FoundFragment();
////        FocusOnFrag = new FocusOnFrag();
//            StudyTodayMainFrag = new StudyTodayMainFrag();
//            fManager = getSupportFragmentManager();
//            fManager.beginTransaction().add(R.id.rl_fragment, FoundFragment, "陪伴教育").commitAllowingStateLoss();
//            isCheck(false, false, false, true);
//            fManager.beginTransaction().remove(MeFrag);
//            fManager.beginTransaction().remove(MessageFrag).commit();
//            bottomNavigationBar.selectTab(0);
//            MessageFrag.setMessageChangeListener(this);
//        }
//    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (JpushReceive.appRunning != 3 || fManager != null) {

            if (JpushReceive.jPushComment) {
                JpushReceive.jPushComment = false;
                isCheck(false, false, true, false);
                bottomNavigationBar.selectTab(1);
            } else if (JpushReceive.jPushMessage) {
//                JpushReceive.jPushMessage = false;
                isCheck(false, true, false, false);
                bottomNavigationBar.selectTab(2);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


//        if (JpushReceive.appRunning != 3 || fManager != null) {
//
//            if (JpushReceive.jPushComment) {
//                JpushReceive.jPushComment = false;
//                isCheck(false, false, true, false);
//                bottomNavigationBar.selectTab(1);
//            } else if (JpushReceive.jPushMessage) {
////                JpushReceive.jPushMessage = false;
//                isCheck(false, true, false, false);
//                bottomNavigationBar.selectTab(2);
//            }
//        }
//        else if (jPushMessage){
//            isCheck(false, true, false, false);
//        }else {
//            isCheck(false, false, false, true);
//        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        islogin = getIntent().getBooleanExtra("islogin",false);
//        if (islogin){
//
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * 退出登录发送消息
         */
        String socketOutMessage = Constants.socketLoginOut();
        try {
            if (mIBackService == null) {
                Toast.makeText(getApplicationContext(),
                        "socket退出连接失败", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("---------", "退出登录发送消息为：" + socketOutMessage);
                boolean isSend = mIBackService.sendMessage(socketOutMessage);
//                Toast.makeText(MainActivity.this,
//                        isSend ? "success" : "fail", Toast.LENGTH_SHORT)
//                        .show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        // 注销广播 最好在onPause上注销
        unregisterReceiver(mReceiver);
        // 注销服务
        unbindService(conn);
    }

    @Override
    public void setMessageSiae(String size) {
        if (size.equals("0")) {
            mBadgeItem.setBackgroundColor(Color.TRANSPARENT);
            mBadgeItem.setTextColor(Color.TRANSPARENT);

        } else {
            mBadgeItem.setBackgroundColor(Color.RED);
            mBadgeItem.setTextColor(Color.RED);
        }

    }
}
