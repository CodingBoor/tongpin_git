package com.qmx163.activity.Found;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmx163.R;
import com.qmx163.activity.watch.WatchActivity;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.ConcernLessons;
import com.qmx163.data.bean.Mebean.LessonItemDetalEn;
import com.qmx163.data.bean.Mebean.TeachDetailEn;
import com.qmx163.data.bean.Mebean.WatchPlayBackEn;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vhall.uilibs.util.VhallUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qmx163.application.MyApplication.param;

/**
 * 发现页面（最新和热门列表）  我的关注(课程)  老师详情授课页面
 * lyf 2017.7.3
 */
public class FoundHotNewListFrag extends BaseFm {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String INT_INDEX = "index";
    private static final String TYPE = "type";
    private static final String SElEBY = "SeleBy";
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private String mParam1;
    private String mParam2;
    private String index;//0发现列表   1 关注列表  2 关注详情 授课过程
    private String type;//0最热 1最新  2关注 3 搜索
    private String SeleById;//科目Id;老师id,
    private String searchText = "";
    int pageNum = 1;
    boolean loadMore = true;
    @BindView(R.id.fragment_foundfrag_hot_new_list_rlv)
    RecyclerView recyclerView;
    //    @BindView(R.id.swipe)
//    SwipeRefreshLayout swipe;
    RefreshLayout refreshLayout;
    private boolean noWatch = false;


    //    private FoundHotNewListFragAdapter mAdapter;
    RefreshAdapter mAdapter;
    private List<LessonItemDetalEn> listData = new ArrayList<LessonItemDetalEn>();


    public FoundHotNewListFrag() {
    }

    public static FoundHotNewListFrag newInstance(String param1, String param2, String indexs, String type, String SeleBy) {
        FoundHotNewListFrag fragment = new FoundHotNewListFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(INT_INDEX, indexs);
        args.putString(TYPE, type);
        args.putString(SElEBY, SeleBy);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            index = getArguments().getString(INT_INDEX);
            type = getArguments().getString(TYPE);
            SeleById = getArguments().getString(SElEBY);
            searchText = getArguments().getString(SElEBY);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_foundfrag_hot_new_list, container, false);

        ButterKnife.bind(this, view);

        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshing();
//        initsView();
        //关注课程下拉刷新监听


        /**
         * 老师详情课程列表
         */
//if (DetailsActivity.swipe!=null){
//            DetailsActivity.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    if (DetailsActivity.type == 1){
//                    pageNum = 1;
//                    getDateTecher();
//
//                    }else {
//                        DetailsActivity.swipe.setRefreshing(false);
//                    }
//                }
//            });
//
//}


        return view;

    }

    // 初始化页面列表布局
    private void initsView() {
        if (index.equals("0")) {
            getDate1();
        } else if (index.equals("1")) {
            if (type.equals("3")) {//搜索

                //第一次进收索页面不搜索
                recyclerView.setVisibility(View.VISIBLE);
                if (searchText.equals("")) {

                } else {
                    getDateFxSear(searchText);
                }
            } else {
                getDateFx(type, SeleById);
            }
        } else if (index.equals("2")) {
//            showToas("老师课程");
            getDateTecher();
        }
        initPullRefresh();
//        initLoadMoreListener();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
//       mAdapter  = new FoundHotNewListFragAdapter(listData, getActivity());
        mAdapter = new RefreshAdapter(getActivity(), listData);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListener(new FoundHotNewListFragAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (listData.get(position).getId() == null) {
                    showToas(getString(R.string.no_study_toast));
                } else {

                    if (index.equals("0")) {
                        getDate(listData.get(position).getId(),position);
                    } else if (index.equals("1")) {
                        getDate(listData.get(position).getId(),position);
                    } else if (index.equals("2")) {
                        getDate(listData.get(position).getId(),position);
                    }
                }

            }
        });

//        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                pageNum = 1;
//                if (index.equals("0")) {
//                    getDate1();
//                } else if (index.equals("1")) {
//                    if (type.equals("3")) {//搜索
//                        recyclerView.setVisibility(View.VISIBLE);
//                        if (searchText.equals("")){
//                            swipe.setRefreshing(false);
//                            showToas(getString(R.string.search_toast));
//                        }else {
//                        getDateFxSear(searchText);
//                        }
//                    } else {
//                        getDateFx(type, SeleById);
//                    }
//                } else if (index.equals("2")) {
////            showToas("老师课程");
//                    getDateTecher();
//                }
//            }
//        });

//        设置分隔线
//        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, 50, getResources().getColor(R.color.select)));
    }

    /**
     * 获取老师详情,更新老师课时
     */
    private void getDateTecher() {
        addSubscription(apiStores.teacherDetail(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), SeleById, pageNum), new ApiCallback<TeachDetailEn>() {
            @Override
            public void onSuccess(TeachDetailEn model) {
                if ("200".equals(model.getCode())) {
                    loadMore = model.getData().getLessonPeriodses().isHasNextPage();
                    mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
                    if (pageNum == 1 && mAdapter != null) {
                        mAdapter.removeAll(listData);
                    }
                    listData = model.getData().getLessonPeriodses().getList();
                    mAdapter.addListItem(listData);


                    if (listData.size() == 0) {
                        llEmpty.setVisibility(View.VISIBLE);
                        ivEmpty.setImageResource(R.mipmap.empty_bg);
                        tvEmpty.setText("数据为空");

                    } else {
                        llEmpty.setVisibility(View.GONE);
                    }
                    if (loadMore) {
                        refreshLayout.setLoadmoreFinished(false);
                    } else {
                        refreshLayout.setLoadmoreFinished(true);
                    }
                }
            }

            @Override
            public void onFailure(String code) {
//                if (swipe != null) {
//                    swipe.setRefreshing(false);
//                }
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                refreshLayout.setLoadmoreFinished(true);
            }

            @Override
            public void onFinish() {
//                if (swipe != null) {
//                    swipe.setRefreshing(false);
//                }
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
            }
        });
    }

    /**
     * 课时关注详情
     */
    private lessonPeriodsDetail.DataBean lessonDeta;

    private void getDate(String lessonId,final int position) {
        showProgressDialog();
        addSubscription(apiStores.lessonPeriodsDetail(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonId), new ApiCallback<lessonPeriodsDetail>() {
            @Override
            public void onSuccess(lessonPeriodsDetail model) {
                lessonDeta = (lessonPeriodsDetail.DataBean) model.getData();
                param.setKey(PrefUtils.getString(getActivity(), Constants.USER_PASSWORD, ""));
                param.setUserName(PrefUtils.getString(getActivity(), Constants.USER_NAME, "无名"));
                param.setWatchId(model.getData().getWebinarId() + "");
                param.setUserVhallId(PrefUtils.getString(getActivity(), Constants.USER_TOKEN, ""));


                //获取回看直播路径
                if (model.getData().getWebinarRecords() != null) {
                    Gson gson = new Gson();
                    List<WatchPlayBackEn> watchBackList = gson.fromJson(model.getData().getWebinarRecords(), new TypeToken<List<WatchPlayBackEn>>() {
                    }.getType());
                    for (int i = 0; i < watchBackList.size(); i++) {
                        if (watchBackList.get(i).getStatus() == 1) {
                            param.setRecordId(watchBackList.get(i).getId() + "");
                            break;
                        }
                    }

                }

                Intent intent = new Intent(getActivity(), WatchActivity.class);
                intent.putExtra("param", param);
                //status;// 状态 -1删除 1直播 2预告 3结束 4点播 5回放
                switch (model.getData().getStatus()) {
                    case 1:
                        intent.putExtra("type", VhallUtil.WATCH_LIVE);
                        if (lessonDeta.getIsWatch() == 0) {
                            noWatch = true;
                            lessonDeta.setAmount(lessonDeta.getAmount() + 1);
                            lessonDeta.setIsWatch(1);
                            listData.get(position).setAmount(listData.get(position).getAmount() + 1);
                        }
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
                        if (lessonDeta.getIsWatch() == 0) {
                            noWatch = true;
                            lessonDeta.setPlaybackAmount(lessonDeta.getPlaybackAmount() + 1);
                            lessonDeta.setIsWatch(1);
                            listData.get(position).setPlaybackAmount(listData.get(position).getPlaybackAmount() + 1);
                        }
                        break;
                }

//                    intent.putExtra("type", VhallUtil.WATCH_LIVE);
                intent.putExtra("lessonDeta", lessonDeta);
                if (model.getData().getStatus() != listData.get(position).getStatus()) {
                    noWatch = true;
                    listData.get(position).setStatus(model.getData().getStatus());
                }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            dismissProgressDialog();
//
//                        }
//                    }, 800);
                startActivityForResult(intent, 2);
//                    startAc(intent);

            }

            @Override
            public void onFailure(String code) {
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

    /**
     * 课程关注
     */
    private void getDate1() {
        addSubscription(apiStores.CurriculumConcerns(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), pageNum), new ApiCallback<ConcernLessons>() {
            @Override
            public void onSuccess(ConcernLessons model) {
                if ("200".equals(model.getCode())) {

                    loadMore = model.getData().isHasNextPage();
                    if (pageNum == 1 && mAdapter != null) {
                        mAdapter.removeAll(listData);
                    }
                    listData = (List<LessonItemDetalEn>) model.getData().getList();
                    mAdapter.addListItem(listData);

                    if (listData.size() == 0) {
                        llEmpty.setVisibility(View.VISIBLE);
                        ivEmpty.setImageResource(R.mipmap.empty_bg);
                        tvEmpty.setText("数据为空");

                    } else {
                        llEmpty.setVisibility(View.GONE);
                    }

                    if (loadMore) {
                        refreshLayout.setLoadmoreFinished(false);
                    } else {
                        refreshLayout.setLoadmoreFinished(true);

                    }
                }
            }

            @Override
            public void onFailure(String code) {
//                if (swipe != null) {
//                    swipe.setRefreshing(false);
//                }

                if (code.equals("-666") || listData.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                    ivEmpty.setImageResource(R.mipmap.no_intelnet);
                    tvEmpty.setText("网络错误");
                } else {
                    llEmpty.setVisibility(View.GONE);
                }
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                refreshLayout.setLoadmoreFinished(true);

            }

            @Override
            public void onFinish() {
//                if (swipe != null) {
//                    swipe.setRefreshing(false);
//                }
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
            }
        });
    }

    /**
     * 发现课程
     */
    public void getDateFx(String type, String SeleById) {
        addSubscription(apiStores.searchLessonPeriodsBySubject(SeleById, PrefUtils.getString(getActivity(), Constants.USER_ID, ""), type, pageNum), new ApiCallback<ConcernLessons>() {
            @Override
            public void onSuccess(ConcernLessons model) {
                if ("200".equals(model.getCode())) {

                    loadMore = model.getData().isHasNextPage();//判断是否上拉加载更多
                    if (pageNum == 1 && mAdapter != null) {
                        mAdapter.removeAll(listData);
                    }
                    listData = (List<LessonItemDetalEn>) model.getData().getList();
                    mAdapter.addListItem(listData);
                }
            }

            @Override
            public void onFailure(String code) {
                if (FoundFrag.swipe != null) {
                    FoundFrag.swipe.setRefreshing(false);
                }
            }

            @Override
            public void onFinish() {
                if (FoundFrag.swipe != null) {
                    FoundFrag.swipe.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 发现课程---搜索
     */
    public void getDateFxSear(String searchKey) {
        if (TextUtils.equals(searchKey," ")){
            listData.clear();
//            showToas(getString(R.string.no_search_toast));
            llEmpty.setVisibility(View.VISIBLE);
            ivEmpty.setImageResource(R.mipmap.empty_bg);
            tvEmpty.setText("数据为空");
            mAdapter.notifyDataSetChanged();
            return;
        }
        searchText = searchKey;
        recyclerView.setVisibility(View.VISIBLE);
        addSubscription(apiStores.searchLessons(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), searchText), new ApiCallback<ConcernLessons>() {
            @Override
            public void onSuccess(ConcernLessons model) {
                if ("200".equals(model.getCode())) {

                    loadMore = model.getData().isHasNextPage();
                    if (pageNum == 1 && mAdapter != null) {
                        mAdapter.removeAll(listData);
                    }
                    listData = (List<LessonItemDetalEn>) model.getData().getList();
                    if (listData.size() == 0) {
//                        showToas(getString(R.string.no_search_toast));
                    }
                    mAdapter.addListItem(listData);

                    if (listData.size() == 0) {
                        llEmpty.setVisibility(View.VISIBLE);
                        ivEmpty.setImageResource(R.mipmap.empty_bg);
                        tvEmpty.setText("数据为空");

                    } else {
                        llEmpty.setVisibility(View.GONE);
                    }
                    if (loadMore) {
                        refreshLayout.setLoadmoreFinished(false);
                    } else {
                        refreshLayout.setLoadmoreFinished(true);

                    }

                }
            }

            @Override
            public void onFailure(String code) {
//                if (swipe!=null){
//                    swipe.setRefreshing(false);
//                }


                if (code.equals("-666") || listData.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                    ivEmpty.setImageResource(R.mipmap.no_intelnet);
                    tvEmpty.setText("网络错误");
                } else {
                    llEmpty.setVisibility(View.GONE);
                }
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                refreshLayout.setLoadmoreFinished(true);

//                refreshLayout.finishRefresh();
//                refreshLayout.finishLoadmore();
//                if (FoundFrag.swipe != null) {
//                    FoundFrag.swipe.setRefreshing(false);
//                }
            }

            @Override
            public void onFinish() {
//                if (swipe!=null){
//                    swipe.setRefreshing(false);
//                }
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
//                if (FoundFrag.swipe != null) {
//                    FoundFrag.swipe.setRefreshing(false);
//                }
            }
        });
    }

    private void initPullRefresh() {
        //陪伴教育模块的下拉
//        FoundFrag.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                pageNum = 1;
//                if (index.equals("0")) {
//                    getDate1();
//                } else if (index.equals("1")) {
//                    if (type.equals("3")) {//搜索
//                        getDateFxSear(SeleById);
//                    } else {
//                        getDateFx(type, SeleById);
//                    }
//                }

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        //刷新完成
//                        FoundFrag.swipe.setRefreshing(false);
//                        Toast.makeText(getActivity(), "更新了 条目数据", Toast.LENGTH_SHORT).show();
//                    }
//                }, 3000);

//            }
//        });


    }

    private void initLoadMoreListener() {

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {

                    //设置正在加载更多
                    if (loadMore) {
                        pageNum++;
                        if (index.equals("0")) {
                            getDate1();
                        } else if (index.equals("1")) {
                            if (type.equals("3")) {//搜索
                                getDateFxSear(SeleById);
                            } else {
                                getDateFx(type, SeleById);
                            }
                        } else if (index.equals("2")) {
                            getDateTecher();
                        }

                    } else {
                        mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
//                        showToas("没有更多数据加载");
                    }

                    //改为网络请求
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            //设置回到上拉加载更多
//                            mAdapter.changeMoreStatus(mAdapter.PULLUP_LOAD_MORE);
//                            //没有加载更多了
//                            //mRefreshAdapter.changeMoreStatus(mRefreshAdapter.NO_LOAD_MORE);
////                            Toast.makeText(getActivity(), "加载了 条目数据", Toast.LENGTH_SHORT).show();
//                        }
//                    }, 3000);


                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initsView();
    }

    private void refreshing() {
//        refreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setEnableLoadmoreWhenContentNotFull(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.setLoadmoreFinished(false);
                pageNum = 1;
                if (index.equals("0")) {
                    getDate1();
                } else if (index.equals("1")) {
                    if (type.equals("3")) {//搜索
                        recyclerView.setVisibility(View.VISIBLE);
                        if (searchText.equals("")) {
                            refreshLayout.finishRefresh();
                            refreshLayout.finishLoadmore();
                            showToas(getString(R.string.search_toast));
                        } else {
                            getDateFxSear(searchText);
                        }
                    } else {
                        getDateFx(type, SeleById);
                    }
                } else if (index.equals("2")) {
//            showToas("老师课程");
                    getDateTecher();
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
                            pageNum++;
                            if (index.equals("0")) {
                                getDate1();
                            } else if (index.equals("1")) {
                                if (type.equals("3")) {//搜索
                                    recyclerView.setVisibility(View.VISIBLE);
                                    if (searchText.equals("")) {
                                        refreshLayout.finishRefresh();
                                        refreshLayout.finishLoadmore();
                                        showToas(getString(R.string.search_toast));
                                    } else {
                                        getDateFxSear(searchText);
                                    }
                                } else {
                                    getDateFx(type, SeleById);
                                }
                            } else if (index.equals("2")) {
//            showToas("老师课程");
                                getDateTecher();
                            }
                        } else {

                            refreshlayout.finishLoadmore();
                            refreshlayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                        }
                    }
                }, 0);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbin.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && noWatch) {
            noWatch = false;
            mAdapter.notifyDataSetChanged();
        }
    }
}
