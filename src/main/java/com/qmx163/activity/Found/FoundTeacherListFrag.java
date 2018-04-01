package com.qmx163.activity.Found;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.activity.FocusOn.TeacherDetailActivity;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.FxTeacher;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.View.RecycleViewDivider;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发现页面（名师列表）
 * lyf 2017.7.4
 */
public class FoundTeacherListFrag extends BaseFm {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String INT_INDEX = "index";
    private static final String SELEBY = "SeleBy";
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private String mParam1;
    private String mParam2;
    private String seleById = "";
    private String index;//1发现 2关注
    private int pageNum = 1;
    public static final int REQUEST_CODE = 0;
    private String searchText = "";
    RefreshLayout refreshLayout;
    boolean loadMore = true;


//    @BindView(R.id.swipe)
//    SwipeRefreshLayout swipe;

    @BindView(R.id.fragment_foundfrag_hot_new_list_rlv)
    RecyclerView recyclerView;


    private FoundTeacherListFragAdapter mAdapter;
    private List<FxTeacher.DataBean.ListBean> listData = new ArrayList<>();


    public FoundTeacherListFrag() {
    }

    public static FoundTeacherListFrag newInstance(String param1, String param2, String Index, String SeleBy) {

        FoundTeacherListFrag fragment = new FoundTeacherListFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(INT_INDEX, Index);
        args.putString(SELEBY, SeleBy);
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
//            seleById = getArguments().getString(SELEBY);
            searchText = getArguments().getString(SELEBY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_foundfrag_hot_new_list, container, false);

        ButterKnife.bind(this, view);

        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshing();


        if (index.equals("1")) {
            //收索首次进入隐藏，不加载
            recyclerView.setVisibility(View.VISIBLE);
            if (searchText.equals("")) {
            } else {
                bindReceiverViewFx(seleById, searchText);
            }
        } else {
            bindReceiverViewGz();
        }
        initsView();

        //关注老师下拉刷新监听
//        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                pageNum = 1;
//                if (index.equals("1")) {
//                    recyclerView.setVisibility(View.VISIBLE);
//                    if (searchText.equals("")){
//                        swipe.setRefreshing(false);
//                        showToas(getString(R.string.search_toast));
//                    }else {
//                    bindReceiverViewFx(seleById, searchText);
//                    }
//                } else {
//
//                    bindReceiverViewGz();
//                }
//            }
//        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (TeacherDetailActivity.isCollection) {
                pageNum = 1;
                bindReceiverViewGz();
            }
        }
    }

    // 初始化页面列表布局
    private void initsView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        mAdapter = new FoundTeacherListFragAdapter(listData, getActivity(), 0);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListener(new FoundTeacherListFragAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (index.equals("1")) {//发现跳转详情页
//                    showToas("发现");
                    Intent intent = new Intent(getActivity(), TeacherDetailActivity.class);
                    intent.putExtra("teach_id", listData.get(position).getId());
                    startAc(intent);
                } else {//我的关注跳转详情页
//                    showToas("关注");
                    Intent intent = new Intent(getActivity(), TeacherDetailActivity.class);
                    intent.putExtra("teach_id", listData.get(position).getId());
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });
//        设置分隔线
        recyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, 2, getResources().getColor(R.color.select)));

    }

    //发现 --查看教师
    public void bindReceiverViewFx(String seleByid, String searchKey) {
        seleById = seleByid;
        searchText = searchKey;
        recyclerView.setVisibility(View.VISIBLE);
        addSubscription(apiStores.searchTeacher(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), seleById, pageNum, searchText),
                new ApiCallback<FxTeacher>() {
                    @Override
                    public void onSuccess(FxTeacher model) {
                        if ("200".equals(model.getCode())) {
                            loadMore = model.getData().isHasNextPage();
                            listData = model.getData().getList();
                            if (listData.size() == 0) {
                                mAdapter.notifyDataSetChanged();
//                                showToas(getString(R.string.no_search_toast));
                            }
                            if (pageNum == 1 && mAdapter != null) {
                                mAdapter.removeAll(listData);
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
//                            showToas(model.getMessage());
                    }

                    @Override
                    public void onFailure(String code) {
//                        if (swipe!=null){
//                            swipe.setRefreshing(false);
//                        }

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
//                        if (swipe!=null){
//                        swipe.setRefreshing(false);
//                        }
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                    }
                });
    }

    //关注 --查看教师
    public void bindReceiverViewGz() {
        addSubscription(apiStores.ConcernTeachers(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), pageNum),
                new ApiCallback<FxTeacher>() {
                    @Override
                    public void onSuccess(FxTeacher model) {
                        if ("200".equals(model.getCode())) {
                            loadMore = model.getData().isHasNextPage();
                            if (pageNum == 1 && mAdapter != null) {
                                mAdapter.removeAll(listData);
                            }
                            listData = model.getData().getList();
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
                        } else {
                            showToas(model.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(String code) {
//                        if (swipe != null) {
//                            swipe.setRefreshing(false);
//                        }

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
//                        if (swipe != null) {
//                            swipe.setRefreshing(false);
//                        }
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                    }
                });
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
                if (index.equals("1")) {
                    recyclerView.setVisibility(View.VISIBLE);
                    if (searchText.equals("")||searchText.equals(" ")) {
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                        listData.clear();
                        mAdapter.notifyDataSetChanged();
                        showToas(getString(R.string.search_toast));
                    } else {
                        bindReceiverViewFx(seleById, searchText);
                    }
                } else {

                    bindReceiverViewGz();
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

                            if (index.equals("1")) {
                                recyclerView.setVisibility(View.VISIBLE);
                                if (searchText.equals("")||searchText.equals(" ")) {
                                    refreshLayout.finishRefresh();
                                    refreshLayout.finishLoadmore();
                                    listData.clear();
                                    mAdapter.notifyDataSetChanged();
                                    showToas(getString(R.string.search_toast));
                                } else {
                                    bindReceiverViewFx(seleById, searchText);
                                }
                            } else {

                                bindReceiverViewGz();
                            }

//                            getDateTecherList();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        null.unbind();
    }
}
