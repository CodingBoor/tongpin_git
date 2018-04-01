package com.qmx163.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.net.AppClient;
import com.qmx163.net.WanToApi;
import com.qmx163.util.BaseActivityTrans;
import com.qmx163.util.Tools;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lzp on 2016/7/19 15:02
 */
public class BaseFm extends Fragment {

    private CompositeSubscription mCompositeSubscription;
    public WanToApi apiStores = AppClient.retrofit().create(WanToApi.class);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("Fragment", getClass().getSimpleName());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void logParams(Map<String, String> map) {
        String tag = this.getClass().getSimpleName();
        Log.i(tag, "rq ->" + map.get("rq"));
        Log.i(tag, "params ->" + map.toString());
    }
        public void setImg(Activity activity,String img,ImageView imageView){
            Glide.with(activity).load(img).centerCrop().placeholder(R.mipmap.moren).error(R.mipmap.moren).crossFade().into((ImageView)imageView );
        }
    public void addSubscription(Observable observable, Subscriber subscriber) {
//        if (!NetUtil.isNetworkConnected(getActivity())) {
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

    /**
     * dialog
     */

    public ProgressDialog progressDialog;

    public ProgressDialog showProgressDialog() {
        progressDialog = new ProgressDialog(getActivity());
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


    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            // progressDialog.hide();会导致android.view.WindowLeaked
            progressDialog.dismiss();
            if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
                mCompositeSubscription.clear();
        }
    }

    /**
     * activity跳转
     *
     * @param act
     */
    public void showActivity(Class act) {
        Intent intent = new Intent(getActivity(), act);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.slide_right_out);//平移进入退出
    }
    protected void showToas(String str) {
        Tools.showToast(getActivity(), str);
    }
    /**
     * 自己做跳转动画
     */
    public void doActJumpAnim() {
        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.slide_right_out);//平移进入退出
    }

    /**
     * 启动目标Activity,无参数
     *
     * @param clazz
     * <p/>
     * date: 2016年4月6日 下午2:57:26
     */
    protected void startAc(Class<?> clazz) {
        startAc(new Intent(getActivity(), clazz));
    }

    /**
     * 启动目标Activity,有参数
     *
     * @param intent
     * <p/>
     * date: 2016年4月6日 下午2:57:36
     */
    protected void startAc(Intent intent) {
        BaseActivityTrans.startMove(getActivity(), intent, BaseActivityTrans.REQUEST_CODE_NULL);
    }
}
