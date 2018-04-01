package com.qmx163.activity.FocusOn;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.activity.Found.FoundHotNewListFrag;
import com.qmx163.activity.Found.FoundTeacherListFrag;
import com.qmx163.base.BaseAc;
import com.vhall.uilibs.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  create by likai on 17-8-31
 *  我的关注页面
 */

public class FocusOnActivity extends BaseAc {

    // 标题栏
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ibtn_left)
    RelativeLayout iBtnLeft;
    @BindView(R.id.Right_img)
    ImageView imageRight;

    @BindView(R.id.focuson_frag_coursell)
    LinearLayout coursell;
    @BindView(R.id.focuson_frag_teacherll)
    LinearLayout teacherll;
    @BindView(R.id.tea_kc)
    TextView teaKc;
    @BindView(R.id.tea_text)
    TextView teaText;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
//    public static SwipeRefreshLayout swipe;
    private int mType = 0;
    @BindView(R.id.focuson_frag_content)
    FrameLayout mFocusonFragContent;
    @BindView(R.id.focuson_frag_content_teach)
    FrameLayout mFocusonFragContentTeach;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private static final int COURSE = 0X0001;
    private static final int TEACHER = 0X0002;

    private FoundHotNewListFrag mFoundHotNewListFrag;
    private FoundTeacherListFrag mFoundTeacherListFrag;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_on);

        ButterKnife.bind(this);

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

//        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
//        swipe.setProgressViewOffset(true, -20, 100);

//        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mSwipeRefreshListener.refresh(mType);
//            }
//        });

        initViews();
//      showFragment(COURSE);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (mType == 1){
//            coursell.setClickable(true);
//            teacherll.setClickable(false);
//            coursell.setBackgroundColor(Color.parseColor("#ededed"));
//            teacherll.setBackgroundColor(Color.WHITE);
//            isShow(true);
//            mType = 1;
//            mFocusonFragContent.setVisibility(View.GONE);
//            mFocusonFragContentTeach.setVisibility(View.VISIBLE);
//        }else {
//            mFoundHotNewListFrag = null;
//        }
//        showFragment(COURSE);
    }

    // 初始化页面
    private void initViews() {
        isShow(false);
        tvTitle.setText("我的关注");
        iBtnLeft.setVisibility(View.VISIBLE);
        coursell.setClickable(false);
        teacherll.setClickable(true);
        showFragment(COURSE);
    }


    @OnClick({R.id.focuson_frag_coursell, R.id.focuson_frag_teacherll})
    public void viewOnClick(View view) {
        switch (view.getId()) {
            case R.id.focuson_frag_coursell:
                coursell.setClickable(false);
                teacherll.setClickable(true);
                teacherll.setBackgroundColor(Color.parseColor("#ededed"));
                coursell.setBackgroundColor(Color.WHITE);
                isShow(false);
                mType = 0;
                mFocusonFragContent.setVisibility(View.VISIBLE);
                mFocusonFragContentTeach.setVisibility(View.GONE);
//                showFragment(COURSE);
                break;
            case R.id.focuson_frag_teacherll:
                coursell.setClickable(true);
                teacherll.setClickable(false);
                coursell.setBackgroundColor(Color.parseColor("#ededed"));
                teacherll.setBackgroundColor(Color.WHITE);
                isShow(true);
                mType = 1;
                mFocusonFragContent.setVisibility(View.GONE);
                mFocusonFragContentTeach.setVisibility(View.VISIBLE);
//                showFragment(TEACHER);
                break;

        }
    }

    public void isShow(boolean isshow) {
        if (isshow) {
            Drawable drawable = getResources().getDrawable(R.mipmap.nocurriculum);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            teaKc.setCompoundDrawables(drawable, null, null, null);
            teaKc.setTextColor(this.getResources().getColor(R.color.xbg));
            teaText.setTextColor(this.getResources().getColor(R.color.juhuan1));
            Drawable drawable1 = getResources().getDrawable(R.mipmap.focuson_teacher);
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            teaText.setCompoundDrawables(drawable1, null, null, null);
            //收藏老师
            if (mFoundTeacherListFrag!=null){
                ActivityUtils.delFragmentToActivity(getSupportFragmentManager(), mFoundTeacherListFrag);
            }
            mFoundTeacherListFrag = (FoundTeacherListFrag) getSupportFragmentManager().findFragmentById(R.id.focuson_frag_content_teach);
            mFoundTeacherListFrag = mFoundTeacherListFrag.newInstance("", "", "0", "");
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFoundTeacherListFrag, R.id.focuson_frag_content_teach);
        } else {
            Drawable drawable = getResources().getDrawable(R.mipmap.curriculum);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            teaKc.setCompoundDrawables(drawable, null, null, null);
            teaKc.setTextColor(this.getResources().getColor(R.color.juhuan1));
            teaText.setTextColor(this.getResources().getColor(R.color.xbg));
            Drawable drawable1 = getResources().getDrawable(R.mipmap.notea);
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            teaText.setCompoundDrawables(drawable1, null, null, null);
        }

//        Drawable drawable1= getResources().getDrawable(R.mipmap.curriculum);
//        drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
//        teaKc.setCompoundDrawables(drawable1,null,null,null);

    }

    // 切换显示的内容
    private void showFragment(int type) {
//        fm = getFragmentManager();
//        ft = fm.beginTransaction();
//        if (type == COURSE) {
//            ft.replace(R.id.focuson_frag_content, FoundHotNewListFrag.newInstance("", "", "0", "2",""));
//        } else {
//            ft.replace(R.id.focuson_frag_content, FoundTeacherListFrag.newInstance("", "", "0",""));
//        }
//        ft.commit();



        if (mFoundHotNewListFrag == null) {
            //收藏课程
            mFoundHotNewListFrag = (FoundHotNewListFrag) getSupportFragmentManager().findFragmentById(R.id.focuson_frag_content);
            mFoundHotNewListFrag = mFoundHotNewListFrag.newInstance("", "", "0", "2", "");
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFoundHotNewListFrag, R.id.focuson_frag_content);
        }

        if (mFoundTeacherListFrag == null) {
            //收藏老师
            mFoundTeacherListFrag = (FoundTeacherListFrag) getSupportFragmentManager().findFragmentById(R.id.focuson_frag_content_teach);
            mFoundTeacherListFrag = mFoundTeacherListFrag.newInstance("", "", "0", "");
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFoundTeacherListFrag, R.id.focuson_frag_content_teach);
        }



    }

}
