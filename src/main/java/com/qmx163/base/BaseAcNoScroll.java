package com.qmx163.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.application.MyApplication;
import com.qmx163.net.AppClient;
import com.qmx163.net.WanToApi;
import com.qmx163.util.ActivityCollector;
import com.qmx163.util.BaseActivityTrans;
import com.qmx163.util.Custom.ToastUtil;
import com.qmx163.util.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/03/03 0003.
 */
public class BaseAcNoScroll extends FragmentActivity{
    private CompositeSubscription mCompositeSubscription;
    protected Activity mActivity;
    public WanToApi apiStores = AppClient.retrofit().create(WanToApi.class);
    Unbinder unbinder;
    protected List<BaseAcNoScroll> mActivityList = new ArrayList<BaseAcNoScroll>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCollector.addActivity(this); //activity 添加到集合管理

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            }
            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }


        setStatusBarLightMode(this,true);
        mActivityList.add(this);
        mActivity = this;
//        realm = Realm.getDefaultInstance();
//        StatusBarCompat.compat(this, getResources().getColor(R.color.grey));
    }

    public boolean setStatusBarLightMode(Activity activity, boolean isFontColorDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isFontColorDark) {
                // 沉浸式
                //                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                //非沉浸式
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                //非沉浸式
                activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
            return true;
        }
        return false;
    }
    @Override
    protected void onDestroy() {
//        realm.close();
        onUnsubscribe();
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }

    public void clearActivity(){
        for(BaseAcNoScroll baseActivity:mActivityList){
            baseActivity.finish();
        }
        mActivityList.clear();
    }

    /**
     * 左边按钮文字
     * <p/>
     */
    protected void setLefttextByID(int res_id) {
        setLefttextByString(getString(res_id));
    }

    /**
     * 左边按钮文字
     * <p/>
     */
    protected void setLefttextByString(String name) {
        findViewById(R.id.ibtn_left).setVisibility(View.VISIBLE);
        Resources mResources = getResources();
        int roundWidth = mResources.getDimensionPixelSize(R.dimen.dim20);
        TextView tv = (TextView) findViewById(R.id.tv_left);
        tv.setPadding(0, 0, roundWidth, 0);
        if (!Tools.isNull(name)){
            tv.setVisibility(View.VISIBLE);
        }
        tv.setText(name);
    }


    /**
     * Title右边文字Button设置
     * <p>
     */
    protected void setRightTextButton(String btn_name, View.OnClickListener listener) {
        TextView btn = (TextView) findViewById(R.id.tv_right);
        if (Tools.isNull(btn_name)) {
            return;
        }
        btn.setVisibility(View.VISIBLE);
        btn.setText(btn_name);
        if (null != listener) {
            btn.setOnClickListener(listener);
        }
    }

    /**
     * 通用点击事件，在子类中实现
     * <p>
     * Created by 邓靖 on  2017/3/14  17:49
     */
    public void onBtnClick(View view) {
      finishAc();
    }

    protected void showToas(String str) {
        Tools.showToast(this, str);
    }


    /**
     * 打印log
     * <p/>
     */
    protected void debugLog(String str) {
        Tools.debugLog(str);
    }

    /**
     * 启动目标Activity,无参数
     *
     * @param clazz
     * <p/>
     * date: 2016年4月6日 下午2:57:26
     */
    protected void startAc(Class<?> clazz) {
        startAc(new Intent(this, clazz));
    }

    /**
     * 启动目标Activity,有参数
     *
     * @param intent
     * <p/>
     * date: 2016年4月6日 下午2:57:36
     */
    protected void startAc(Intent intent) {
        BaseActivityTrans.startMove(this, intent, BaseActivityTrans.REQUEST_CODE_NULL);
    }

    /**
     * 启动目标Activity,有参数
     *
     * @param intent
     * <p/>
     * date: 2016年4月6日 下午2:57:36
     */
    protected void startAc(Intent intent,int code) {
        BaseActivityTrans.startMove(this, intent,code);
    }
    /**
     * 结束当前Activity, 无参数
     *
     * <p/>
     * date: 2016年4月6日 下午2:58:08
     */
    protected void finishAc() {
        finishAc(null, BaseActivityTrans.RESUTL_CODE_NULL);
    }

    /**
     * 结束当前Activity, 有参数
     *
     * @param intent
     * @param result_code
     * <p/>
     * date: 2016年4月6日 下午2:58:14
     */
    protected void finishAc(Intent intent, int result_code) {
        BaseActivityTrans.finishMove(this, intent, result_code);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.slide_right_out);//平移进入退出
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
//        if (!NetUtil.isNetworkConnected(this)) {
//            showToas(getString(R.string.no_net));
//        }
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));

    }

    public void addSubscription(Subscription subscription) {
//        if (!NetUtil.isNetworkConnected(this)) {
//            showToas(getString(R.string.no_net));
//        }
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);


    }


    public void onUnsubscribe() {
        // LogUtil.d("onUnsubscribe");
        //取消注册，以避免内存泄露
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
            mCompositeSubscription.unsubscribe();
    }
//    public void setImg(Activity activity,String img,ImageView imageView){
//        Glide.with(activity).load(img).centerCrop().placeholder(R.mipmap.moren).error(R.mipmap.moren).crossFade().into((ImageView)imageView );
//    }
    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage("加载中");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                // Cancel task.
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    progressDialog.dismiss();
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
                            mCompositeSubscription.clear();
                    }
                }
                return false;
            }
        });
        return progressDialog;
    }

    public ProgressDialog showProgressDialog(CharSequence message) {
        progressDialog = new ProgressDialog(mActivity);
        progressDialog.setMessage(message);
        progressDialog.show();
        return progressDialog;
    }

    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
            if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
                mCompositeSubscription.clear();
        }
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        return v;
    }

    /**
     * 请求权限
     *
     * @param id                   请求授权的id 唯一标识即可
     * @param permission           请求的权限
     * @param allowableRunnable    同意授权后的操作
     * @param disallowableRunnable 禁止权限后的操作
     */
    public void requestPermission(int id, String permission, Runnable allowableRunnable, Runnable disallowableRunnable) {
        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }
        MyApplication.allowable(id,allowableRunnable);
//        allowablePermissionRunnables.put(id, allowableRunnable);
        if (disallowableRunnable != null) {
            MyApplication.disallowable(id,disallowableRunnable);
//            disallowablePermissionRunnables.put(id, disallowableRunnable);
        }

        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //减少是否拥有权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getApplicationContext(), permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框接收权限
                ActivityCompat.requestPermissions(this, new String[]{permission}, id);
                return;
            } else {
                allowableRunnable.run();
            }
        } else {
            allowableRunnable.run();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Runnable allowRun = MyApplication.getallowable().get(requestCode);
            allowRun.run();
        } else {
            Runnable disallowRun = MyApplication.getdesallowable().get(requestCode);
            disallowRun.run();
        }
    }
    public void showToast(int textResId) {
        ToastUtil.toastShortShow(getApplication(), textResId);
    }
}
