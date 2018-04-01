package com.qmx163.net;

import android.util.Log;

import com.qmx163.application.MyApplication;
import com.qmx163.config.Constants;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.photoUtil.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {
    public static Retrofit mRetrofit;

    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        //打印retrofit日志
                        Log.i(Constants.LogKey,message);
                    }
                });
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
//            OkHttpClient okHttpClient = builder.build();

            //添加请求头
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder builder1 = request.newBuilder();
                    Request build = builder1.addHeader("reqToken", PrefUtils.getString(MyApplication.getContext(),Constants.USER_TOKEN,"")).build();
                    return chain.proceed(build);
                }
            }).retryOnConnectionFailure(true).connectTimeout(8, TimeUnit.SECONDS)
                    .writeTimeout(8, TimeUnit.SECONDS)
                    .readTimeout(8, TimeUnit.SECONDS).build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(NetField.SITE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }



}
