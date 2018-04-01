package com.qmx163.net;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.andview.refreshview.utils.LogUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmx163.activity.LoginActivity;
import com.qmx163.application.MyApplication;
import com.qmx163.base.BaseBean;
import com.qmx163.config.Constants;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.ToastUtil;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

import static com.qmx163.config.Constants.HTTP_ERROR;


public abstract class ApiCallback<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(String code);

    public abstract void onFinish();


    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;


            try {
                String content = exception.response().errorBody().string();
                Log.e(HTTP_ERROR, content);

                Gson gson=new Gson();
                BaseBean<Object> bean=gson.fromJson(content,new TypeToken<BaseBean<Object>>(){}.getType());
//                ToastUtil.toastShortShow(MyApplication.getContext(), bean.getMessage()+"");

                //当code为899时，直接跳转用户注册界面
                if (bean.getCode() == 899){

                    Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
                    if (!TextUtils.equals(PrefUtils.getString(MyApplication.getContext(), Constants.USER_TOKEN, ""),"")){
                    intent.putExtra("type",true);
                    }

                    PrefUtils.setString(MyApplication.getContext(), Constants.USER_ID, "");
                    PrefUtils.setString(MyApplication.getContext(), Constants.USER_TOKEN, "");

                    if (JPushInterface.isPushStopped(MyApplication.getContext())) {
                        JPushInterface.resumePush(MyApplication.getContext());
                    }

                    JPushInterface.setAliasAndTags(MyApplication.getContext(), "", null, new TagAliasCallback() {

                        @Override
                        public void gotResult(int code, String alias, Set<String> tags) {
                            switch (code) {
                                case 0:
                                    LogUtils.i(">>>>>>>>>>LoginActivity:Jpush set tag and alias success");
                                    break;
                                case 6002:
                                    LogUtils.i(">>>>>>>>>>LoginActivity:Jpush failed to set alias and tags due to timeout. Try again after 60s.");
                                    break;
                                default:
                                    LogUtils.i(">>>>>>>>>>LoginActivity:Jpush failed with errorCode = " + code);
                            }
                        }

                    });

                    intent.putExtra("check_user", true);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                    ActivityCollector.finishAll();
                    MyApplication.getContext().startActivity(intent);
                }else{
                    ToastUtil.toastShortShow(MyApplication.getContext(),bean.getMessage());
                }
                onFailure(bean.getCode()+"");

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(throwable instanceof ClientException){
            ToastUtil.toastShortShow(MyApplication.getContext(),"网络连接异常");
        }else if(throwable instanceof ServiceException){
            ToastUtil.toastShortShow(MyApplication.getContext(),"OSS服务器异常");
        }else if (throwable instanceof ConnectException||
                throwable instanceof SocketTimeoutException ||
                throwable instanceof TimeoutException){
            ToastUtil.toastShortShow(MyApplication.getContext(),"网络连接异常");
            onFailure("-666");
        }else if (throwable instanceof NullPointerException){

        } else {
            ToastUtil.toastShortShow(MyApplication.getContext(),"网络异常");
            onFailure("-666");
        }
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onCompleted() {
        onFinish();
    }
}
