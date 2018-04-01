package com.qmx163.activity.FocusOn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmx163.R;
import com.qmx163.activity.Found.FoundHotNewListFragAdapter;
import com.qmx163.activity.Found.RefreshAdapter;
import com.qmx163.activity.watch.WatchActivity;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.LessonItemDetalEn;
import com.qmx163.data.bean.Mebean.TeachDetailEn;
import com.qmx163.data.bean.Mebean.WatchPlayBackEn;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.ActivityCollector;
import com.qmx163.util.PrefUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vhall.uilibs.util.VhallUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.qmx163.application.MyApplication.param;

/**
 * 老师详情页面
 */
public class TeacherDetailActivity extends BaseAc {

    @BindView(R.id.ll_web_context)
    RelativeLayout llWeb;
    @BindView(R.id.tea_ico)
    CircleImageView teaIco;
    @BindView(R.id.tv_techer_name)
    TextView tvTecherName;
    @BindView(R.id.tea_state)
    TextView teaState;
    @BindView(R.id.tea_suggest)
    TextView teaSuggest;
    @BindView(R.id.tea_line)
    ImageView teaLine;
    @BindView(R.id.focuson_frag_coursell)
    LinearLayout focusonFragCoursell;
    @BindView(R.id.tea_process)
    TextView teaProcess;
    @BindView(R.id.tea_proline)
    ImageView teaProline;
    @BindView(R.id.focuson_frag_teacherll)
    LinearLayout focusonFragTeacherll;
    @BindView(R.id.recycler_stuty)
    RecyclerView recyclerStuty;
    @BindView(R.id.iv_intro_img)
    ImageView ivIntroImg;
//    @BindView(R.id.web_content)
//    WebView webContent;
    @BindView(R.id.topLayout)
    LinearLayout topLayout;
    @BindView(R.id.iv_back)
    com.rey.material.widget.ImageView ivBack;
    @BindView(R.id.Isfocus)
    com.rey.material.widget.ImageView Isfocus;
    @BindView(R.id.activity_detail_new)
    RelativeLayout activityDetailNew;
    @BindView(R.id.ll_teacher)
    LinearLayout llTeacher;
    @BindView(R.id.cf_footer)
    ClassicsFooter mCfFooter;

    WebView mWebContext;

    SmartRefreshLayout refreshLayout;
    private String teacherId = "";
    private String intro;
    private String introVideoImg;
    private int pageNum = 1;
    private int pageNumlist = 1;
    public static int type = 0;
    RefreshAdapter mAdapter;
    private List<LessonItemDetalEn> listData = new ArrayList<LessonItemDetalEn>();
    public static boolean isCollection = false;
    private boolean loadMore = true;
    private boolean isLiving = false;
    private NestedScrollView nestView;
private RelativeLayout rlTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
        ButterKnife.bind(this);

        refreshLayout = (SmartRefreshLayout) findViewById(R.id.refreshLayout);
        nestView = (NestedScrollView) findViewById(R.id.nest_view);
        rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
//        refreshLayout.setEnableLoadmore(false);
        rlTitle.getBackground().setAlpha(0);
        refreshing();


        nestView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int height = rlTitle.getHeight();
                int alpht = map(0, height, 0, 255, scrollY);
                rlTitle.getBackground().setAlpha(alpht);
            }
        });

        teacherId = getIntent().getStringExtra("teach_id");
        isLiving = getIntent().getBooleanExtra("living",false);
        initViews();
        getDateTecher();
        focusonFragCoursell.setClickable(false);
        focusonFragTeacherll.setClickable(true);


    }

    private static int map(int from1,int to1,int from2,int to2,int value) {
        if (value < from1) value = from1;
        if (value > to1) value = to1;
        int dis = value - from1;
        float frac = (float)dis / (to1 - from1);
        return (int) (from2 + ((to2 - from2) * frac));
    }

    private void refreshing() {
//        refreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setEnableLoadmoreWhenContentNotFull(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.setLoadmoreFinished(false);
                if (type == 0){
                pageNum = 1;
                    getDateTecherList(pageNum);

                }else {
                    pageNumlist = 1;
                    getDateTecherList(pageNumlist);

                }
//                refreshlayout.getLayout().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        mAdapter.refresh(initData());
//                        refreshlayout.finishRefresh();
//                        refreshlayout.setLoadmoreFinished(false);
//                    }
//                }, 2000);
            }
        });

        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                refreshlayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mAdapter.loadmore(initData());
                        if (loadMore) {
                            if (type == 0){
//                            pageNum++;
//                            getDateTecherList(pageNum);
                                refreshlayout.finishLoadmore();
                                refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                            }else {
                                pageNumlist++;
                                getDateTecherList(pageNumlist);

                            }
                        } else {
//                            Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshlayout.finishLoadmore();
                            refreshlayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                        }
                    }
                }, 0);
            }
        });
    }


    // 初始化页面
    private void initViews() {
        teaSuggest.setTextColor(this.getResources().getColor(R.color.juhuan1));
        teaLine.setImageResource(R.color.juhuan1);
        teaProline.setImageResource(R.color.default_circle_indicator_fill_color);
        teaProcess.setTextColor(this.getResources().getColor(R.color.xbg));
//        if(lessonDeta.getConcern()==0){//是否关注 0否 1是
//            Isfocus.setBackgroundResource(R.mipmap.teacheckno);
//        }else{
//            Isfocus.setBackgroundResource(R.mipmap.taecheck);
//        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(TeacherDetailActivity.this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerStuty.setLayoutManager(linearLayoutManager);
        mAdapter = new RefreshAdapter(TeacherDetailActivity.this, listData);
        recyclerStuty.setNestedScrollingEnabled(false); //处理滑动卡顿
        recyclerStuty.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListener(new FoundHotNewListFragAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (listData.get(position).getId() == null) {
                    showToas(getString(R.string.no_study_toast));
                } else {
                getDate(listData.get(position).getId());
                }
            }
        });


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
                    loadMore = model.getData().getLessonPeriodses().isHasNextPage();
//                    listData =  model.getData().getLessonPeriodses().getList();
//                    mAdapter.addListItem(listData);
                    if (model.getData().getConcern() == 0) {//是否关注 0否 1是
                        Isfocus.setImageResource(R.mipmap.teacheckno);
                    } else {
                        Isfocus.setImageResource(R.mipmap.taecheck);
                    }

                    Glide.with(TeacherDetailActivity.this).load(model.getData().getImg()).dontAnimate().error(R.mipmap.xiaoxi).into(teaIco);
                    tvTecherName.setText(model.getData().getName());
                    introVideoImg = model.getData().getIntroVideoImg();
                    intro = model.getData().getIntro();
                    teaState.setText("共授" + model.getData().getTotalLessonPeriods() + "节课");

                    mWebContext = new WebView(TeacherDetailActivity.this);
                    llWeb.addView(mWebContext);
                    mWebContext.loadDataWithBaseURL(null, intro, "text/html", "UTF-8", null);
                    Glide.with(TeacherDetailActivity.this).load(introVideoImg).centerCrop().dontAnimate().error(R.mipmap.defaultimg).crossFade().into(ivIntroImg);
                    if (pageNum == 1 && mAdapter != null) {
                        mAdapter.removeAll(listData);
                        listData.clear();
                    }
                    listData = model.getData().getLessonPeriodses().getList();
                    mAdapter.addListItem(listData);
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


    @OnClick({R.id.focuson_frag_coursell, R.id.focuson_frag_teacherll, R.id.iv_back, R.id.Isfocus})
    public void viewOnClick(View view) {
        switch (view.getId()) {
            case R.id.focuson_frag_coursell:
//                refreshLayout.setEnableLoadmore(false);
                focusonFragCoursell.setClickable(false);
                focusonFragTeacherll.setClickable(true);
                llTeacher.setVisibility(View.VISIBLE);
                recyclerStuty.setVisibility(View.GONE);
                type = 0;
                teaSuggest.setTextColor(this.getResources().getColor(R.color.juhuan1));
                teaLine.setImageResource(R.color.juhuan1);
                teaProline.setImageResource(R.color.default_circle_indicator_fill_color);
                teaProcess.setTextColor(this.getResources().getColor(R.color.xbg));
                break;
            case R.id.focuson_frag_teacherll:
                if (loadMore){
                    refreshLayout.setLoadmoreFinished(false);//将不会再次触发加载更多事件

                }else {
                    refreshLayout.setLoadmoreFinished(true);

                }
//                refreshLayout.setEnableLoadmore(true);
//                refreshLayout.setEnableRefresh(true);
//                refreshLayout.setEnableLoadmore(true);
                focusonFragCoursell.setClickable(true);
                focusonFragTeacherll.setClickable(false);
                llTeacher.setVisibility(View.GONE);
                recyclerStuty.setVisibility(View.VISIBLE);
                type = 1;
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

            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }


    /**
     * 获取老师详情,更新老师课时
     */
    private void getDateTecherList(int page) {
        addSubscription(apiStores.teacherDetail(PrefUtils.getString(TeacherDetailActivity.this, Constants.USER_ID, ""), teacherId, page), new ApiCallback<TeachDetailEn>() {
            @Override
            public void onSuccess(TeachDetailEn model) {
                if ("200".equals(model.getCode())) {
                    loadMore = model.getData().getLessonPeriodses().isHasNextPage();

                    tvTecherName.setText(model.getData().getName());
                    introVideoImg = model.getData().getIntroVideoImg();
                    intro = model.getData().getIntro();
                    teaState.setText("共授" + model.getData().getTotalLessonPeriods() + "节课");

                    if (type ==0){
                        mWebContext.loadDataWithBaseURL(null, intro, "text/html", "UTF-8", null);
                    Glide.with(TeacherDetailActivity.this).load(introVideoImg).centerCrop().dontAnimate().error(R.mipmap.defaultimg).crossFade().into(ivIntroImg);
                    }else {

                        if (pageNumlist == 1 && mAdapter != null) {
                            mAdapter.removeAll(listData);
                            listData.clear();
                        }
                        List<LessonItemDetalEn> listNewData = model.getData().getLessonPeriodses().getList();
                        listData.addAll(listNewData);
                        mAdapter.addListItem(listNewData);
                    }

                    if (loadMore){
                        refreshLayout.setLoadmoreFinished(false);//将不会再次触发加载更多事件

                    }else {
                        refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                    }
                }
            }

            @Override
            public void onFailure(String code) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
            }

            @Override
            public void onFinish() {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
            }
        });
    }


    /**
     * 课时关注详情
     */
    private lessonPeriodsDetail.DataBean lessonDeta;

    private void getDate(String lessonId) {
        showProgressDialog();
        addSubscription(apiStores.lessonPeriodsDetail(PrefUtils.getString(TeacherDetailActivity.this, Constants.USER_ID, ""), lessonId), new ApiCallback<lessonPeriodsDetail>() {
            @Override
            public void onSuccess(lessonPeriodsDetail model) {
                if ("200".equals(model.getCode())) {
//                    lessonDeta = (lessonPeriodsDetail.DataBean) model.getData();
//                    Intent intent = new Intent(TeacherDetailActivity.this, WatchActivity.class);
//                    intent.putExtra("param", param);
//                    intent.putExtra("type", VhallUtil.WATCH_LIVE);
//                    intent.putExtra("lessonDeta", lessonDeta);
//
//
//                    startAc(intent);


                    lessonDeta = (lessonPeriodsDetail.DataBean) model.getData();
                    param.setKey(PrefUtils.getString(TeacherDetailActivity.this, Constants.USER_PASSWORD, ""));
                    param.setUserName(PrefUtils.getString(TeacherDetailActivity.this, Constants.USER_NAME, ""));
                    param.setWatchId(model.getData().getWebinarId() + "");
                    param.setUserVhallId(PrefUtils.getString(TeacherDetailActivity.this, Constants.USER_TOKEN, ""));


                    //获取回看直播路径
                    if (model.getData().getWebinarRecords() != null) {
                        Gson gson = new Gson();
                        List<WatchPlayBackEn> watchBackList = gson.fromJson(model.getData().getWebinarRecords(), new TypeToken<List<WatchPlayBackEn>>() {
                        }.getType());
                        param.setRecordId(watchBackList.get(0).getId() + "");

                    }

                    //销毁前面的直播界面
                    if (isLiving){
                        if (ActivityCollector.activities.size()>=3){
                        for (int i = 0; i < ActivityCollector.activities.size(); i++) {
                            if (i == ActivityCollector.activities.size()-2){
                                ActivityCollector.removeActivity(ActivityCollector.activities.get(i));
//                                ActivityCollector.activities.remove(i);
                                break;
                            }

                        }

                        }
                    }


                    Intent intent = new Intent(TeacherDetailActivity.this, WatchActivity.class);
                    intent.putExtra("param", param);
                    intent.putExtra("living",true);
                    //status;// 状态 -1删除 1直播 2预告 3结束 4点播 5回放
                    switch (model.getData().getStatus()) {
                        case 1:
                            intent.putExtra("type", VhallUtil.WATCH_LIVE);
                            break;
                        case 2:
                            intent.putExtra("type", VhallUtil.WATCH_LIVE);
                            break;
                        case 3:
                            intent.putExtra("type", VhallUtil.WATCH_LIVE);
//                            showToas("直播已结束");
                            break;
                        case 4:
                            intent.putExtra("type", VhallUtil.WATCH_PLAYBACK);
                            break;
                        case 5:
                            intent.putExtra("type", VhallUtil.WATCH_PLAYBACK);
                            break;
                    }

//                    intent.putExtra("type", VhallUtil.WATCH_LIVE);
                    intent.putExtra("lessonDeta", lessonDeta);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            dismissProgressDialog();
//
//                        }
//                    }, 800);
                    startAc(intent);


                }
            }

            @Override
            public void onFailure(String code) {
                Log.e("detail------", "onFailure: "+code );
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();

                    }
                }, 400);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if( mWebContext!=null) {
            mWebContext.setVisibility(View.GONE);
            mWebContext.removeAllViews();
            mWebContext.destroy();
        }
        super.onDestroy();
    }
}
