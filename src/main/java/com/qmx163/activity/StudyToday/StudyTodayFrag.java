package com.qmx163.activity.StudyToday;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.activity.LoginActivity;
import com.qmx163.activity.Me.SignActivity;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.listener.SwipeRefreshListener;
import com.qmx163.listener.SwipeRefreshTwoListener;
import com.qmx163.util.PrefUtils;
import com.vhall.uilibs.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 今日学习页面
 * Created by likai on 2017/8/28.
 */

public class StudyTodayFrag extends BaseFm {
    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
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
    @BindView(R.id.rb_title_left)
    RadioButton mRbTitleLeft;
    @BindView(R.id.rb_title_right)
    RadioButton mRbTitleRight;
    @BindView(R.id.rg_title)
    RadioGroup mRgTitle;
    @BindView(R.id.study_today)
    FrameLayout mStudyToday;
    @BindView(R.id.study_history)
    FrameLayout mStudyHistory;
    @BindView(R.id.tv_sigin)
    TextView tvSigin;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBar;

    public static SwipeRefreshLayout mSwipe;
    private int type = 0;
    StudyTodayFragment mStudyTodayFragment;
    StudyHistoryFragment mStudyHistoryFragment;
    SwipeRefreshListener mSwipeRefreshListener;
    SwipeRefreshTwoListener mSwipeRefreshTwoListener;

    public void setSwipeRefreshListener(SwipeRefreshListener swipeRefreshListener) {
        mSwipeRefreshListener = swipeRefreshListener;
    }

    public void setSwipeRefreshTwoListener(SwipeRefreshTwoListener swipeRefreshTwoListener) {
        mSwipeRefreshTwoListener = swipeRefreshTwoListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.study_today_frag, container, false);
        ButterKnife.bind(this, view);

        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) mUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        if (PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "").equals("") || PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "") == null) {
            startAc(LoginActivity.class);
            getActivity().finish();
        } else {
            mSwipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
            initViews();

        }

        return view;
    }

    // 初始化页面
    private void initViews() {
        mTvTitle.setText(R.string.study_today_text);
//        mIbtnLeft.setVisibility(View.VISIBLE);
//        mTvRight.setVisibility(View.VISIBLE);
//        mTvRight.setText(R.string.sigin_text);
//        mTvRight.setTextColor(Color.BLACK);
//        mIamgeleft.setImageResource(R.mipmap.message);

        mSwipe.setProgressViewOffset(true, -20, 100);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.e("=====", "onOffsetChanged: "+verticalOffset );
                if (verticalOffset >= 0) {
                    mSwipe.setEnabled(true);
                } else {
                    mSwipe.setEnabled(false);
                }
            }
        });


        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshListener.refresh(type);
                mSwipeRefreshTwoListener.refreshTwo(type);
            }
        });


        //今日学习
        mStudyTodayFragment = (StudyTodayFragment) getFragmentManager().findFragmentById(R.id.study_today);
        //历史学习
        mStudyHistoryFragment = (StudyHistoryFragment) getFragmentManager().findFragmentById(R.id.study_history);

        if (mStudyTodayFragment == null){
            mStudyTodayFragment = mStudyTodayFragment.newInstance("0");
            ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(),mStudyTodayFragment,R.id.study_today);
        }

        if (mStudyHistoryFragment == null){
            mStudyHistoryFragment = mStudyHistoryFragment.newInstance("1");
            ActivityUtils.addFragmentToActivity(getActivity().getSupportFragmentManager(),mStudyHistoryFragment,R.id.study_history);
        }

        mRgTitle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_title_left:
                        type = 0;
                        mStudyToday.setVisibility(View.VISIBLE);
                        mStudyHistory.setVisibility(View.GONE);
                        break;
                    case R.id.rb_title_right:
                        type = 1;
                        mStudyToday.setVisibility(View.GONE);
                        mStudyHistory.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }


    // 切换显示的内容
    private void showFragment(int type) {
//        fm = getFragmentManager();
//        ft = fm.beginTransaction();
//        if (type == COURSE) {
//            ft.replace(R.id.focuson_frag_content, FoundHotNewListFrag.newInstance("", "", "0", "2", ""));
//        } else {
//            ft.replace(R.id.focuson_frag_content, FoundTeacherListFrag.newInstance("", "", "0", ""));
//        }
//        ft.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * 点击事件
     * @param view
     */
    @OnClick({R.id.iamgeleft,R.id.tv_sigin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.iamgeleft:
//                    startAc(MessageActivity.class);
//                break;

            case R.id.tv_sigin:
                    startAc(SignActivity.class);
                break;
        }

    }


//    @Override
//    public void onRefresh() {
//        if (mType == 0){
//            showFragment(COURSE);
//        }else if (mType == 1){
//            showFragment(TEACHER);
//        }
//    }
}
