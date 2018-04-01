package com.qmx163.net;

import android.util.Log;


;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.qmx163.application.MyApplication;
import com.qmx163.util.ToastUtil;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.functions.Action1;

import static com.qmx163.config.Constants.HTTP_ERROR;

/**
 * Created by UI-PC-2 on 2017/5/4.
 */

public class HttpError implements Action1<Throwable> {

    @Override
    public void call(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof HttpException) {
            HttpException exception = (HttpException) throwable;
            try {
                String content = exception.response().errorBody().string();
                Log.e(HTTP_ERROR, content);
                onBody(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(throwable instanceof ClientException){
            ToastUtil.toastShortShow(MyApplication.getContext(),"网络连接异常");
        }else if(throwable instanceof ServiceException){
            ToastUtil.toastShortShow(MyApplication.getContext(),"OSS服务器异常");
        }
    }



    /**
     * http 请求失败消息体
     * @param body
     */
    public void onBody(String body) {

    }
}
