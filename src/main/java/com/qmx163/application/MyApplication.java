package com.qmx163.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.multidex.MultiDex;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.mob.MobApplication;
import com.qmx163.R;
import com.qmx163.activity.watch.Param;
import com.qmx163.config.Constants;
import com.qmx163.util.PrefUtils;
import com.vhall.business.VhallSDK;
import com.vhall.vhalllive.CameraFilterView;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends MobApplication {

    //用户同意权限权限申请
    private static Map<Integer, Runnable> allowablePermissionRunnables = new HashMap<>();
    //用户拒绝权限申请
    private static Map<Integer, Runnable> disallowablePermissionRunnables = new HashMap<>();
    public static void allowable(int id,Runnable allowableRunnable){
        allowablePermissionRunnables.put(id,allowableRunnable);
    }
    public static void disallowable(int id, Runnable disallowableRunnable){
        disallowablePermissionRunnables.put(id,disallowableRunnable);
    }

    public static Map<Integer,Runnable> getallowable(){
        return allowablePermissionRunnables;
    }
    public static Map<Integer,Runnable> getdesallowable(){
        return disallowablePermissionRunnables;
    }

    
    public static Param param;
    /**
     * Activity管理器
     */
    /**
     * 设备屏幕位深
     */
    private float mDensity;
    /**
     * 设备屏幕宽度（单位像素）
     */
    private int mWidth;
    /**
     * 设备屏幕高度（单位像素）
     */
    private int mHeight;

    private String imei;

    private static MyApplication mApp;

    private static Context context;

    /**
     * nickname for current user, the nickname instead of ID be shown when user receive notification from APNs
     */
    public static String currentUserNick = "";

    public static MyApplication getInstance() {
        return mApp;
    }

    /**
     * 应用上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();

//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String kk = sharedPreferences.getString(Constants.USER_ID,"");

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        if (PrefUtils.getString(this, Constants.USER_ID,"").equals("")){
//JPushInterface.stopPush(this);
        }else {
        //设置为调试模式，具体发布的时候可以直接设置为false
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);

        }

//        LeakCanary.install(this);
        getParam();
        VhallSDK.init(this, getResources().getString(R.string.vhall_app_key), getResources().getString(R.string.vhall_app_secret_key));
        VhallSDK.setLogEnable(false);

        /**
         * android 7.0 拍照适配
         */
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

//        Recovery.getInstance()
//                .debug(true)
//                .recoverInBackground(false)
//                .recoverStack(true)
//                .mainPage(MainActivity.class)
//                .recoverEnabled(true)
//                .callback(null)
//                .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
//                .skip(SplashAc.class)
//                .init(this);

        this.mApp = this;
        context = getApplicationContext();
        initResolution();// 初始化屏幕显示参数（宽，高，以及像素密度）
    }


    public Param getParam() {
        if (param == null) {
            param = new Param();
            SharedPreferences sp = this.getSharedPreferences("set", MODE_PRIVATE);
            TelephonyManager telephonyMgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
            param.broId = sp.getString("broid", "");
            param.broToken = sp.getString("brotoken", "");
            param.pixel_type = sp.getInt("pixeltype", CameraFilterView.TYPE_HDPI);
            param.videoBitrate = sp.getInt("videobitrate", 500);
            param.videoFrameRate = sp.getInt("videoframerate", 20);

            param.watchId = sp.getString("watchid", "");
            param.key = sp.getString("key", "");
            param.bufferSecond = sp.getInt("buffersecond", 2);

            param.userVhallId = sp.getString("uservhallid", "2e38f69754ba11e7905400163e323696");//""
//            param.userCustomId = sp.getString("usercustomid", telephonyMgr.getDeviceId());//此句代码会在小米mix2报错
            param.userCustomId = sp.getString("usercustomid", "");
            param.userName = sp.getString("username","");
            param.userAvatar = sp.getString("useravatar", "");
            param.recordId = sp.getString("recordId", "");
        }
        return param;
    }

    public static void setParam(Param mParam) {
        if (param == null)
            return;
        param = mParam;
        SharedPreferences sp = context.getSharedPreferences("set", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("broid", param.broId);
        editor.putString("brotoken", param.broToken);
        editor.putInt("pixeltype", param.pixel_type);
        editor.putInt("videobitrate", param.videoBitrate);
        editor.putInt("videoframerate", param.videoFrameRate);

        editor.putString("watchid", param.watchId);
        editor.putString("key", param.key);
        editor.putInt("buffersecond", param.bufferSecond);

        editor.putString("uservhallid", param.userVhallId);
        editor.putString("usercustomid", param.userCustomId);
        editor.putString("username", param.userName);
        editor.putString("useravatar", param.userAvatar);
        editor.putString("recordId", param.recordId);

        editor.commit();
    }

    /**
     * 当系统内存不足时调用.
     *
     * @see Application#onLowMemory()
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Runtime.getRuntime().gc();// 通知Java虚拟机回收垃圾
    }


    /**
     * 程序终止时候调用.
     *
     * @see Application#onTerminate()
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * getmDensity:得到屏幕的像素密度. <br/>
     *
     * @return 屏幕的像素密度
     * @author BaoHang
     */
    public float getmDensity() {
        return mDensity;
    }

    /**
     * getmWidth:得到屏幕的宽度. <br/>
     * 注意不同的手机，哪怕是相同的分辨率，也有可能或得到不同的值，这是由于Android系统造成的.<br/>
     *
     * @return the Phone‘s display width。
     * @author BaoHang
     */
    public int getmWidth() {
        return mWidth;
    }

    /**
     * getmHeight:得到屏幕高度，单位px. <br/>
     * 注意不同的手机，哪怕是相同的分辨率，也有可能或得到不同的值，这是由于Android系统造成的.<br/>
     *
     * @return the Phone‘s display height。
     */
    public int getmHeight() {
        return mHeight;
    }



    public String getImei() {
        return imei;
    }

    /**
     * initResolution:初始化屏幕信息. <br/>
     * 初始化屏幕的宽度，高度以及像素密度.<br/>
     * 宽高的单位都是像素，px不是dp.<br/>
     */
    private void initResolution() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        mWidth = dm.widthPixels;
        mHeight = dm.heightPixels;
        mDensity = dm.density;
    }

    /**
     * 初始化设备信息
     */
    private void initIMEI() {
        TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        imei = tm.getDeviceId();
        if (TextUtils.isEmpty(imei))
            imei = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * 兼容4.0配置
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
