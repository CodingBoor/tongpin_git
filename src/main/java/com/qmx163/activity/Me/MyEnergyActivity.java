package com.qmx163.activity.Me;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetRewardRules;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.data.bean.Mebean.Scores;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的能量界面
 * Created by likai on 2017/7/3.
 */
public class MyEnergyActivity extends BaseAc implements View.OnClickListener {

    @BindView(R.id.rv_energy)
    RecyclerView mRvEnergy;
    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.Right_img)
    ImageView mRightImg;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.tv_score)
    TextView mTvScore;
    @BindView(R.id.tv_bg_up)
    TextView tvBgUp;
    @BindView(R.id.tv_one)
    TextView mTvOne;
    @BindView(R.id.tv_two)
    TextView mTvTwo;
    @BindView(R.id.tv_three)
    TextView mTvThree;
    @BindView(R.id.tv_fout)
    TextView mTvFout;
    @BindView(R.id.tv_five)
    TextView mTvFive;
    @BindView(R.id.tv_six)
    TextView mTvSix;
    @BindView(R.id.tv_serven)
    TextView mTvServen;
    private RecyclerAdapter energyAdapter;
    private List<Scores.DataBean.ListBean> energyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_energy);
        ButterKnife.bind(this);

        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) tvBgUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            tvBgUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        getRules();
//        mIvBack.setOnClickListener(this);//点击返回

        tvBgUp.setVisibility(View.INVISIBLE);
        mRlTitle.setBackgroundColor(Color.TRANSPARENT);
        mIbtnLeft.setVisibility(View.VISIBLE);
        mTvTitle.setText(R.string.me_energy_title);
        mRightImg.setVisibility(View.GONE);
        mTvScore.setText("");
//        mTvScore.setText(PrefUtils.getInt(this, Constants.USER_SCORE, 0) + "");
//        getDate();
//        bindRecyclerView();//绑定recyclerview
    }


    /**
     * 获取能量规则
     */
    private void getRules() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("rq", "/app/getRewardRules");
        addSubscription(apiStores.getRewardRules(params), new ApiCallback<GetRewardRules>() {
            @Override
            public void onSuccess(GetRewardRules model) {
                if ("200".equals(model.getCode())) {
                    Log.e("----------", "onSuccess: 点击" );
                    mTvOne.setText("1、" + model.getData().getRule_01());
                    mTvTwo.setText("2、" + model.getData().getRule_02());
                    mTvThree.setText("3、" + model.getData().getRule_03());
                    mTvFout.setText("4、" + model.getData().getRule_04());
                    mTvFive.setText("5、" + model.getData().getRule_05());
                    mTvSix.setText("6、" + model.getData().getRule_06());
                    mTvServen.setText("7、" + model.getData().getRule_07());
                }
            }

            @Override
            public void onFailure(String code) {
            }

            @Override
            public void onFinish() {
                getDate();
//                getDatePoint();
            }
        });
    }

    /**
     * 初始化recyclerview
     */
    private void bindRecyclerView() {
        energyAdapter = new RecyclerAdapter<Scores.DataBean.ListBean>(MyEnergyActivity.this, energyList, R.layout.item_energy_me) {
            @Override
            public void convert(RecyclerViewHolder helper, Scores.DataBean.ListBean item, int position) {
                helper.setText(R.id.My_title, item.getShortDesc());
                helper.setText(R.id.My_score, "" + item.getScore());
                if (item.getTimeDiff() == 0) {
                    helper.setText(R.id.My_timer, "刚刚");
                } else {
                    helper.setText(R.id.My_timer, item.getAddTime());
                }
            }

        };
        mRvEnergy.setHasFixedSize(true);
        mRvEnergy.setNestedScrollingEnabled(false);
        mRvEnergy.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        mRvEnergy.setAdapter(this.energyAdapter);
    }

    /**
     * 获取recyclerview数据
     */
    private void getDate() {
        showProgressDialog();
        addSubscription(apiStores.getScores("1", PrefUtils.getString(MyEnergyActivity.this, Constants.USER_ID, "")),
                new ApiCallback<Scores>() {
                    @Override
                    public void onSuccess(Scores model) {
                        if (model.getCode().equals("200")) {
                            Scores.DataBean scores = model.getData();
                            energyList.addAll(scores.getList());
                            bindRecyclerView();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        dismissProgressDialog();
                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }
                });
    }


    /**
     * 获取数据
     */
    private void getDatePoint() {
        addSubscription(apiStores.SelectPer(PrefUtils.getString(this, Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                if ("200".equals(model.getCode())) {
                    RegisteredEn.DataBean data = model.getData();
                    mTvScore.setText(data.getScore() + "");
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
    public void onClick(View v) {
        switch (v.getId()) {
//
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDatePoint();
    }
}
