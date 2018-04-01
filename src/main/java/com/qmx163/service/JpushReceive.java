package com.qmx163.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.google.gson.Gson;
import com.qmx163.activity.Message.JpushMsgListActivity;
import com.qmx163.activity.StudyToday.StudyCommentDetalActivity;
import com.qmx163.activity.watch.WatchActivity;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.util.AppRunningUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * 极光推送广播
 * Created by likai on 2017/921.
 */
public class JpushReceive extends BroadcastReceiver {
    private static final String TAG = "test";
    AudioManager audioManager;
    Vibrator vibrator;
    Ringtone ringtone;
    public final static String REFRESHAPPLY = "refresh_apply";
    public final static String REFRESHGROUP = "refresh_group";

    public static final String ADDFRIENDS = "601";
    public static int appRunning = 0;
    public static boolean jPushComment = false;
    public static boolean jPushMessage = false;
//    public static boolean jPushLive = false;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        String code = bundle.getString(JPushInterface.EXTRA_EXTRA);
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String body = bundle.getString(JPushInterface.EXTRA_ALERT);


        /**
         * 点击跳转
         */
        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())){
            //获取app的运行状态
            appRunning = AppRunningUtils.getAppSatus(context, "com.qmx163");//1, 栈顶,2,里, 3, 外
            if (code != null) {
            Gson gson = new Gson();
            SocketMessage pushContent = gson.fromJson(code, SocketMessage.class);

//            bizType 业务类型 0通知 1直播 2提问回复 3提问发布 4未观看直播（逃课）5今日学习
            if (appRunning == 1){  //app在栈顶
                if (pushContent.getBizType().equals("0")||pushContent.getBizType().equals("2")||pushContent.getBizType().equals("3")){
                    jPushMessage = true;
                    Intent i = new Intent(context, JpushMsgListActivity.class);  //自定义打开的界面
                    i .putExtra("biz_id",pushContent.getBizId());
                    i .putExtra("biz_type",pushContent.getBizType());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if (pushContent.getBizType().equals("5")){
                    jPushComment = true;
                    Intent i = new Intent(context, StudyCommentDetalActivity.class);  //自定义打开的界面
                    i.putExtra(Constants.STUDY_DASK_ID,pushContent.getBizId());
                    i.putExtra("jpush_comment",true);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if (pushContent.getBizType().equals("1")||pushContent.getBizType().equals("4")){
//                    jPushLive = true;
                    jPushMessage = true;
                    Intent i = new Intent(context, WatchActivity.class);  //自定义打开的界面
                    i .putExtra("biz_id",pushContent.getBizId());
                    i .putExtra("biz_type",pushContent.getBizType());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }


            }else if (appRunning == 2){ //app在栈里
                if (pushContent.getBizType().equals("0")||pushContent.getBizType().equals("2")||pushContent.getBizType().equals("3")){
                    jPushMessage = true;
                    Intent i = new Intent(context, JpushMsgListActivity.class);  //自定义打开的界面
                    i .putExtra("biz_id",pushContent.getBizId());
                    i .putExtra("biz_type",pushContent.getBizType());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if (pushContent.getBizType().equals("5")){
                    jPushComment = true;
                    Intent i = new Intent(context, StudyCommentDetalActivity.class);  //自定义打开的界面
                    i.putExtra(Constants.STUDY_DASK_ID,pushContent.getBizId());
                    i.putExtra("jpush_comment",true);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if (pushContent.getBizType().equals("1")||pushContent.getBizType().equals("4")){
//                    jPushLive = true;
                    jPushMessage = true;
                    Intent i = new Intent(context, WatchActivity.class);  //自定义打开的界面
                    i .putExtra("biz_id",pushContent.getBizId());
                    i .putExtra("biz_type",pushContent.getBizType());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            }else if (appRunning == 3){  //app 在栈外
                if (pushContent.getBizType().equals("0")||pushContent.getBizType().equals("2")||pushContent.getBizType().equals("3")){
                    jPushMessage = true;
                    Intent i = new Intent(context, JpushMsgListActivity.class);  //自定义打开的界面
                    i .putExtra("biz_id",pushContent.getBizId());
                    i .putExtra("biz_type",pushContent.getBizType());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if (pushContent.getBizType().equals("5")){
                    jPushComment = true;
                    Intent i = new Intent(context, StudyCommentDetalActivity.class);  //自定义打开的界面
                    i.putExtra(Constants.STUDY_DASK_ID,pushContent.getBizId());
                    i.putExtra("jpush_comment",true);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }else if (pushContent.getBizType().equals("1")||pushContent.getBizType().equals("4")){
//                    jPushLive = true;
                    jPushMessage = true;
                    Intent i = new Intent(context, WatchActivity.class);  //自定义打开的界面
                    i .putExtra("biz_id",pushContent.getBizId());
                    i .putExtra("biz_type",pushContent.getBizType());
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            }
        }



            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            Log.i(TAG, "MyReceiver接收到的消息内容： title: " + code + " message: " + message);
//            vibrateAndPlayTone(context);
//            JpushModel jpushModel = (JpushModel) JsonHelper.parseToObject(code, JpushModel.class);

            /**
             * 消息弹窗的跳转逻辑
             */
//            if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
//            }
        }
    }

}
