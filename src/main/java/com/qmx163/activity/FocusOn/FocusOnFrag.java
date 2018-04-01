package com.qmx163.activity.FocusOn;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.activity.Found.FoundHotNewListFrag;
import com.qmx163.activity.Found.FoundTeacherListFrag;
import com.qmx163.base.BaseFm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关注
 * Created by lyf on 2017.7.4
 */

public class FocusOnFrag extends BaseFm{

    // 标题栏
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_bg_up)
    TextView mTvUp;
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
    public static SwipeRefreshLayout swipe;
    public static int mType = 0;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private static final int COURSE = 0X0001;
    private static final int TEACHER = 0X0002;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.focuson_frag, container, false);
        ButterKnife.bind(this, view);

        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) mTvUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mTvUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipe.setProgressViewOffset(true, -20, 100);
        initViews();
        showFragment(COURSE);
        return view;
    }

    // 初始化页面
    private void initViews() {
        isShow(false);
        tvTitle.setText("我的关注");
        coursell.setClickable(false);
        teacherll.setClickable(true);
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
                showFragment(COURSE);
                break;
            case R.id.focuson_frag_teacherll:
                coursell.setClickable(true);
                teacherll.setClickable(false);
                coursell.setBackgroundColor(Color.parseColor("#ededed"));
                teacherll.setBackgroundColor(Color.WHITE);
                isShow(true);
                mType = 1;
                showFragment(TEACHER);
                break;

        }
    }
    public void isShow(boolean isshow){
        if(isshow) {
            Drawable drawable = getResources().getDrawable(R.mipmap.nocurriculum);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            teaKc.setCompoundDrawables(drawable, null, null, null);
            teaKc.setTextColor(this.getResources().getColor(R.color.xbg));
            teaText.setTextColor(this.getResources().getColor(R.color.juhuan1));
            Drawable drawable1 = getResources().getDrawable(R.mipmap.focuson_teacher);
            drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
            teaText.setCompoundDrawables(drawable1, null, null, null);
        }else{
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
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        if (type == COURSE) {
            ft.replace(R.id.focuson_frag_content, FoundHotNewListFrag.newInstance("", "", "0", "2",""));
        } else {
            ft.replace(R.id.focuson_frag_content, FoundTeacherListFrag.newInstance("", "", "0",""));
        }
        ft.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
