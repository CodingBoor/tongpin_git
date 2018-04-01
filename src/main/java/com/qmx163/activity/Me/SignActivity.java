package com.qmx163.activity.Me;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qmx163.R;
import com.qmx163.base.BaseAcNoScroll;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.net.ApiCallback;
import com.qmx163.net.AppClient;
import com.qmx163.net.WanToApi;
import com.qmx163.util.PrefUtils;
import com.qmx163.view.PinchImage.SignCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 签到
 */
public class SignActivity extends BaseAcNoScroll implements View.OnClickListener {

    @BindView(R.id.iv_del)
    com.rey.material.widget.ImageView mIvDel;
    @BindView(R.id.rl_calendar)
    RelativeLayout mRlCalendar;
    @BindView(R.id.tv_sign)
    com.rey.material.widget.ImageView mTvSign;
    @BindView(R.id.tv_point)
    TextView mTvPoint;
    @BindView(R.id.rl_background)
    RelativeLayout mRlBackground;
    @BindView(R.id.sign_calendar)
    SignCalendar mSignCalendar;
    @BindView(R.id.tv_sign_day)
    TextView mTvSignDay;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_getpoint)
    TextView mTvGetpoint;
    private CompositeSubscription mCompositeSubscription;
    public WanToApi apiStores = AppClient.retrofit().create(WanToApi.class);
    private String signHistory;
    private String lastModifyTime;
    private String today;
    private List<String> signList = new ArrayList();
    private Date date;
    private int upPoint;
    private String signDay;
    SimpleDateFormat format;
    private int siginSize = 0;


    @Override
    protected void onResume() {
        upPoint = 0;
        siginSize = 0;

        super.onResume();
        /**
         * 进入页面获取数据
         */
        addSubscription(apiStores.getSign(PrefUtils.getString(SignActivity.this, Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                if ("200".equals(model.getCode())) {
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    date = new Date(System.currentTimeMillis());
                    today = format.format(date);
                    signHistory = model.getData().getSignHistory();
                    lastModifyTime = model.getData().getLastModifyTime();
                    if (signHistory!=null){

                        signList = Arrays.asList(signHistory.split(","));
                        mSignCalendar.addMarks(signList, R.mipmap.me_sigin_already);//本月已经签到的打标记
                        signDay = model.getData().getSignCount();

                        Date now = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
                        String hehe = dateFormat.format( now );

                        //判断本月签到天数
                        for (int i = 0; i < signList.size(); i++) {
                            String k = signList.get(i).substring(5,7);
                            k = hehe.substring(5,7);
                            if (signList.get(i).substring(5,7).equals(hehe.substring(5,7))){
                                siginSize++;
                            }
                        }
                        mTvSignDay.setText(siginSize + "");//本月累计签到天数
                    }else {

                        mTvSignDay.setText("0");//本月累计签到天数
                    }
                    upPoint = model.getData().getScore();
                    mTvGetpoint.setText("今天签到可以获得" + upPoint + "点V能量，快来签到吧~");
                    mTvName.setText(PrefUtils.getString(SignActivity.this, Constants.USER_NAME, "") + "同学：");

//                    String lastDay = lastModifyTime.substring(0, 10);
                    //判断签到的最后一天是否为今天，如果为今天则表示已经签到
                    if (lastModifyTime.substring(0, 10).equals(today)) {
                        mTvSign.setImageResource(R.mipmap.calendar_sign_on);
                        mTvGetpoint.setText("签到成功，明日继续签到可获得" + upPoint + "点V能量");
                        mTvSign.setClickable(false);
                    }
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        mTvSign.setOnClickListener(this);
        mIvDel.setOnClickListener(this);
//        /**
//         * 进入页面获取数据
//         */
//        addSubscription(apiStores.getSign(PrefUtils.getString(SignActivity.this, Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
//            @Override
//            public void onSuccess(RegisteredEn model) {
//                if ("200".equals(model.getCode())) {
//                    format = new SimpleDateFormat("yyyy-MM-dd");
//                    date = new Date(System.currentTimeMillis());
//                    today = format.format(date);
//                    signHistory = model.getData().getSignHistory();
//                    lastModifyTime = model.getData().getLastModifyTime();
//                    if (signHistory!=null){
//
//                    signList = Arrays.asList(signHistory.split(","));
//                    mSignCalendar.addMarks(signList, R.mipmap.me_sigin_already);//本月已经签到的打标记
//                    signDay = model.getData().getSignCount();
//
//                    Date now = new Date();
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
//                    String hehe = dateFormat.format( now );
//
//                    //判断本月签到天数
//                    for (int i = 0; i < signList.size(); i++) {
//                        String k = signList.get(i).substring(5,7);
//                        k = hehe.substring(5,7);
//                        if (signList.get(i).substring(5,7).equals(hehe.substring(5,7))){
//                            siginSize++;
//                        }
//                    }
//                    mTvSignDay.setText(siginSize + "");//本月累计签到天数
//                    }else {
//
//                        mTvSignDay.setText("0");//本月累计签到天数
//                    }
//                    upPoint = model.getData().getScore();
//                    mTvGetpoint.setText("今天签到可以获得" + upPoint + "点V能量，快来签到吧~");
//                    mTvName.setText(PrefUtils.getString(SignActivity.this, Constants.USER_NAME, "") + "同学：");
//
////                    String lastDay = lastModifyTime.substring(0, 10);
//                    //判断签到的最后一天是否为今天，如果为今天则表示已经签到
//                    if (lastModifyTime.substring(0, 10).equals(today)) {
//                        mTvSign.setImageResource(R.mipmap.calendar_sign_on);
//                        mTvGetpoint.setText("签到成功，明日继续签到可获得" + upPoint + "点V能量");
//                        mTvSign.setClickable(false);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(String code) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign:  //用户签到
//                mTvSign.setBackgroundResource(R.mipmap.calendar_sign_on);

                //旋转动画，效果不行
//                float centerX = mRlBackground.getWidth()/2.0f;
//                float centerY = mRlBackground.getHeight()/2.0f;
//                float centerZ = 0f;
//                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0, 180, centerX, centerY, centerZ, Rotate3dAnimation.ROTATE_Y_AXIS, true);
//                rotate3dAnimation.setDuration(1000);
//                mRlBackground.setAnimation(rotate3dAnimation);


//                List list = new ArrayList();

                addSubscription(apiStores.addSign(PrefUtils.getString(this, Constants.USER_ID, "")),
                        new ApiCallback<RegisteredEn>() {
                            @Override
                            public void onSuccess(RegisteredEn model) {
                                if ("200".equals(model.getCode())) {
                                    try {
                                        List<String> signListTwo = new ArrayList();

                                        format = new SimpleDateFormat("yyyy-MM-dd");
                                        date = new Date(System.currentTimeMillis());
                                        today = format.format(date);
//                                        signList.clear();
                                        signListTwo.add(today);
                                        mSignCalendar.addMarks(signListTwo, R.mipmap.me_sigin_already);//签到后重新打标签
                                        mTvSign.setImageResource(R.mipmap.calendar_sign_on);
                                        mTvGetpoint.setText("签到成功，明日继续签到可获得" + upPoint + "点V能量");
//                                    signList.clear();
                                        mTvPoint.setText("+ " + upPoint);
                                        mTvPoint.setVisibility(View.VISIBLE);
                                        mTvSignDay.setText(siginSize + 1 + "");
                                        mTvSign.setClickable(false);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    Toast.makeText(SignActivity.this, model.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(String code) {

                            }

                            @Override
                            public void onFinish() {

                            }
                        });

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTvPoint.setVisibility(View.GONE);
                    }
                }, 3000);
                break;
            case R.id.iv_del:
                finish();
                break;
        }

    }

    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
