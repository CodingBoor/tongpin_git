package com.qmx163.activity.Found;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qmx163.R;
import com.qmx163.activity.FocusOn.DetailsActivity;
import com.qmx163.activity.watch.WatchActivity;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.ConcernLessons;
import com.qmx163.data.bean.Mebean.LessonItemDetalEn;
import com.qmx163.data.bean.Mebean.TeachDetailEn;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;
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
public class FoundHotNewListFragment extends BaseFm {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String INT_INDEX = "index";
    private static final String TYPE = "type";
    private static final String SElEBY = "SeleBy";
    private String mParam1;
    private String mParam2;
    private String index;//0发现列表   1 关注列表  2 关注详情 授课过程
    private String type;//0最热 1最新  2关注 3 搜索
    private String SeleById;//科目Id;老师id,
    int pageNum = 1;
    boolean loadMore = true;
    @BindView(R.id.fragment_foundfrag_hot_new_list_rlv)
    RecyclerView recyclerView;


    //    private FoundHotNewListFragAdapter mAdapter;
    RefreshAdapter mAdapter;
    private List<LessonItemDetalEn> listData = new ArrayList<LessonItemDetalEn>();


    public FoundHotNewListFragment() {
    }

    public static FoundHotNewListFragment newInstance(String param1, String param2, String indexs, String type, String SeleBy) {
        FoundHotNewListFragment fragment = new FoundHotNewListFragment();
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

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_foundfrag_hot_new_list_new, container, false);

        ButterKnife.bind(this, view);
        initsView();
        //关注课程下拉刷新监听
        DetailsActivity.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                if (index.equals("0")) {
                    getDate1();
                } else if (index.equals("1")) {
                    if (type.equals("3")) {//搜索
                        getDateFxSear(SeleById);
                    } else {
                        getDateFx(type, SeleById);
                    }
                } else if (index.equals("2")) {
//            showToas("老师课程");
                    getDateTecher();
                }
            }
        });

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
                getDateFxSear(SeleById);
            } else {
                getDateFx(type, SeleById);
            }
        } else if (index.equals("2")) {
//            showToas("老师课程");
            getDateTecher();
        }
        initPullRefresh();
        initLoadMoreListener();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
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
                        getDate(listData.get(position).getId());
                    } else if (index.equals("1")) {
                        getDate(listData.get(position).getId());
                    } else if (index.equals("2")) {
                        getDate(listData.get(position).getId());
                    }
                }
            }
        });
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
                }
            }

            @Override
            public void onFailure(String code) {
                if (DetailsActivity.swipe != null) {
                    DetailsActivity.swipe.setRefreshing(false);
                }
            }

            @Override
            public void onFinish() {
                if (DetailsActivity.swipe != null) {
                    DetailsActivity.swipe.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 课时关注详情
     */
    private lessonPeriodsDetail.DataBean lessonDeta;

    private void getDate(String lessonId) {
        showProgressDialog();
        addSubscription(apiStores.lessonPeriodsDetail(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonId), new ApiCallback<lessonPeriodsDetail>() {
            @Override
            public void onSuccess(lessonPeriodsDetail model) {
                if ("200".equals(model.getCode())) {
                    lessonDeta = (lessonPeriodsDetail.DataBean) model.getData();
                    Intent intent = new Intent(getActivity(), WatchActivity.class);
                    intent.putExtra("param", param);
                    intent.putExtra("type", VhallUtil.WATCH_LIVE);
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
                    mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
                    if (pageNum == 1 && mAdapter != null) {
                        mAdapter.removeAll(listData);
                    }
                    listData = (List<LessonItemDetalEn>) model.getData().getList();
                    mAdapter.addListItem(listData);
                }
            }

            @Override
            public void onFailure(String code) {
                if (DetailsActivity.swipe != null) {
                    DetailsActivity.swipe.setRefreshing(false);
                }
            }

            @Override
            public void onFinish() {
                if (DetailsActivity.swipe != null) {
                    DetailsActivity.swipe.setRefreshing(false);
                }
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
//                    mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
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
        addSubscription(apiStores.searchLessons(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), searchKey), new ApiCallback<ConcernLessons>() {
            @Override
            public void onSuccess(ConcernLessons model) {
                if ("200".equals(model.getCode())) {

                    loadMore = model.getData().isHasNextPage();
                    mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
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
                    mAdapter.changeMoreStatus(mAdapter.LOADING_MORE);
                    if (loadMore) {
                        pageNum++;
//                        mAdapter.changeMoreStatus(mAdapter.PULLUP_LOAD_MORE);
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
}
