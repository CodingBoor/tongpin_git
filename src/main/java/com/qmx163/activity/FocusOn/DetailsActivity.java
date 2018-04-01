package com.qmx163.activity.FocusOn;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.activity.Found.FoundHotNewListFragment;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.TeachDetailEn;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 老师详情
 */
public class DetailsActivity extends BaseAc {


    @BindView(R.id.focuson_frag_coursell)
    LinearLayout focusonFragCoursell;

    @BindView(R.id.focuson_frag_teacherll)
    LinearLayout focusonFragTeacherll;
    @BindView(R.id.focuson_frag_content)
    FrameLayout focusonFragContent;
    @BindView(R.id.Isfocus)
    ImageView Isfocus;
    @BindView(R.id.tea_suggest)
    TextView teaSuggest;
    @BindView(R.id.tea_process)
    TextView teaProcess;
    @BindView(R.id.tea_line)
    ImageView teaLine;
    @BindView(R.id.tea_proline)
    ImageView teaProline;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tea_ico)
    ImageView mTeaIco;
    @BindView(R.id.tea_state)
    TextView mTeaState;
    @BindView(R.id.tv_techer_name)
    TextView mTecherName;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    @BindView(R.id.topLayout)
    LinearLayout mTopLayout;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.swipe)
    SwipeRefreshLayout mSwipe;
    //    @BindView(R.id.iv_intro_img)
//    ImageView mIvIntroImg;
//    @BindView(R.id.web_content)
//    WebView mWebContent;
//    @BindView(R.id.scroll_view)
//    NestedScrollView mScrollView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private static final int COURSE = 0X0001;
    private static final int TEACHER = 0X0002;
    private String teacherId = "";
    public static SwipeRefreshLayout swipe;
    AppBarLayout appBar;
    public static int type = 0;
    private int pageNum = 1;
    private String intro;
    private String introVideoImg;
    public static boolean isCollection = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe);
        appBar = (AppBarLayout) findViewById(R.id.appBarLayout);
        teacherId = getIntent().getStringExtra("teach_id");

        swipe.setProgressViewOffset(true, -20, 100);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipe.setEnabled(true);
                } else {
                    swipe.setEnabled(false);
                }
            }
        });
        initViews();
        getDateTecher();
        focusonFragCoursell.setClickable(false);
        focusonFragTeacherll.setClickable(true);
    }

    // 初始化页面
    private void initViews() {
//        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                showToas("主fragment刷新2");
//                if (type == 0) {
//                    showToas("主fragment刷新1");
//
//                    swipe.setRefreshing(false);
//
//                } else {
//                    showToas("主fragment刷新2");
//
//                    swipe.setRefreshing(false);
//                }
//
//            }
//        });

        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipe.setRefreshing(false);
                showToas("主fragment刷新");
            }
        });

        teaSuggest.setTextColor(this.getResources().getColor(R.color.juhuan1));
        teaLine.setImageResource(R.color.juhuan1);
        teaProline.setImageResource(R.color.default_circle_indicator_fill_color);
        teaProcess.setTextColor(this.getResources().getColor(R.color.xbg));
//        if(lessonDeta.getConcern()==0){//是否关注 0否 1是
//            Isfocus.setBackgroundResource(R.mipmap.teacheckno);
//        }else{
//            Isfocus.setBackgroundResource(R.mipmap.taecheck);
//        }
    }


    @OnClick({R.id.focuson_frag_coursell, R.id.focuson_frag_teacherll, R.id.iv_back, R.id.Isfocus})
    public void viewOnClick(View view) {
        switch (view.getId()) {
            case R.id.focuson_frag_coursell:
                focusonFragCoursell.setClickable(false);
                focusonFragTeacherll.setClickable(true);
                type = 0;
                showFragment(COURSE);
                teaSuggest.setTextColor(this.getResources().getColor(R.color.juhuan1));
                teaLine.setImageResource(R.color.juhuan1);
                teaProline.setImageResource(R.color.default_circle_indicator_fill_color);
                teaProcess.setTextColor(this.getResources().getColor(R.color.xbg));
                break;
            case R.id.focuson_frag_teacherll:
                focusonFragCoursell.setClickable(true);
                focusonFragTeacherll.setClickable(false);
                type = 1;
                showFragment(TEACHER);
                teaSuggest.setTextColor(this.getResources().getColor(R.color.xbg));
                teaLine.setImageResource(R.color.default_circle_indicator_fill_color);
                teaProline.setImageResource(R.color.juhuan1);
                teaProcess.setTextColor(this.getResources().getColor(R.color.juhuan1));
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.Isfocus:  //点击关注
                isCollection = true;
//                showToas("关注中");
                followTeacher();
                break;
        }
    }

    /**
     * 关注老师
     */
    private void followTeacher() {
        showProgressDialog();
        addSubscription(apiStores.teacherConcern(PrefUtils.getString(this, Constants.USER_ID, ""), teacherId), new ApiCallback<GetMessageEn>() {
            @Override
            public void onSuccess(GetMessageEn model) {
                if ("200".equals(model.getCode())) {
                    showToas(model.getMessage());
                    if (model.getMessage().equals("取消关注成功")) {
                        Isfocus.setImageResource(R.mipmap.teacheckno);

                    } else if (model.getMessage().equals("关注成功")) {
                        Isfocus.setImageResource(R.mipmap.taecheck);

                    } else {
                        showToas(model.getMessage());
                    }
//                    loadMore = model.getData().getLessonPeriodses().isHasNextPage();
//                    mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
//                    listData =  model.getData().getLessonPeriodses().getList();
//                    mAdapter.addListItem(listData);
//                    showFragment(COURSE);
                }
            }

            @Override
            public void onFailure(String code) {
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }

    // 切换显示的内容
    private void showFragment(int mType) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        if (mType == COURSE) {
            ft.replace(R.id.focuson_frag_content, TeaIntroductionFrag.newInstance(introVideoImg, intro));
           int k =  mAppBarLayout.getScrollY();
            k = mSwipe.getScrollY();
            k = mCoordinatorLayout.getScrollY();
            mAppBarLayout.scrollTo(0,0);
//            mCoordinatorLayout.scrollTo(0,0);
//            focusonFragContent.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));
//            mCoordinatorLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500));

        } else {//授课过程
            ft.replace(R.id.focuson_frag_content, FoundHotNewListFragment.newInstance("", "", "2", "2", teacherId));
        }
        ft.commit();
    }

    /**
     * 获取老师详情,更新老师课时
     */
    private void getDateTecher() {
        showProgressDialog();
        addSubscription(apiStores.teacherDetail(PrefUtils.getString(this, Constants.USER_ID, ""), teacherId, pageNum), new ApiCallback<TeachDetailEn>() {
            @Override
            public void onSuccess(TeachDetailEn model) {
                if ("200".equals(model.getCode())) {
//                    loadMore = model.getData().getLessonPeriodses().isHasNextPage();
//                    mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
//                    listData =  model.getData().getLessonPeriodses().getList();
//                    mAdapter.addListItem(listData);
                    if (model.getData().getConcern() == 0) {//是否关注 0否 1是
                        Isfocus.setImageResource(R.mipmap.teacheckno);
                    } else {
                        Isfocus.setImageResource(R.mipmap.taecheck);
                    }

                    Glide.with(DetailsActivity.this).load(model.getData().getImg()).dontAnimate().error(R.mipmap.xiaoxi).into(mTeaIco);
                    mTecherName.setText(model.getData().getName());
                    introVideoImg = model.getData().getIntroVideoImg();
                    intro = model.getData().getIntro();
                    mTeaState.setText("共授" + model.getData().getTotalLessonPeriods() + "节课");
                    showFragment(COURSE);
//                    mWebContent.loadDataWithBaseURL(null,intro,"text/html", "UTF-8",null);
//                    Glide.with(DetailsActivity.this).load(introVideoImg).centerCrop().dontAnimate().error(R.mipmap.defaultimg).crossFade().into(mIvIntroImg);

                }
            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }

}
