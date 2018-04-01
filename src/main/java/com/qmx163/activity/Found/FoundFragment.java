package com.qmx163.activity.Found;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmx163.R;
import com.qmx163.activity.LoginActivity;
import com.qmx163.activity.Message.MessageActivity;
import com.qmx163.activity.watch.WatchActivity;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.ConcernLessons;
import com.qmx163.data.bean.Mebean.GetBannersEn;
import com.qmx163.data.bean.Mebean.LessonItemDetalEn;
import com.qmx163.data.bean.Mebean.WatchPlayBackEn;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;
import com.qmx163.view.BannerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.vhall.uilibs.util.VhallUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.qmx163.application.MyApplication.param;
import static com.qmx163.util.SccDateDialog.TAG;

/**
 * 首页（直播）
 * Created by likai on 2017/8/30.
 */

public class FoundFragment extends BaseFm {

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
    @BindView(R.id.faxian_list_heard_banner)
    BannerView mFaxianListHeardBanner;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.swipe)
//    SwipeRefreshLayout mSwipe;
    @BindView(R.id.nest_scrollview)
    NestedScrollView nestScrollview;
    RefreshLayout refreshLayout;


    Unbinder unbinder;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.tv_bg_up)
    TextView tvBgup;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private int pageNum = 1; //分页
    boolean loadMore = true; //是否加载更多
    private String index = "0";//0发现列表   1 关注列表  2 关注详情 授课过程
    private String type = "0";//0最热 1最新  2关注 3 搜索
    private String SeleById;//科目Id;老师id,

    //status;// 状态 -1删除 1直播 2预告 3结束 4点播 5回放
    RefreshAdapter mAdapter;
    private List<LessonItemDetalEn> listData = new ArrayList<LessonItemDetalEn>(); //t条目数据
    private List<LessonItemDetalEn> listDataNew = new ArrayList<LessonItemDetalEn>(); //t条目数据

    private boolean noWatch = false;
    private boolean More = true;
    private boolean areYounity = false;
    int height = 0;
    int liveTime = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        socketLine();
//
//        getActivity().bindService(mServiceIntent, conn, BIND_AUTO_CREATE);
//        // 开始服务
//        registerReceiver();
    }

    ReceiveBroadCast receiveBroadCast;

    @Override
    public void onAttach(Context context) {
        /** 注册广播 */
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.liveFragment");    //只有持有相同的action的接受者才能接收此广播
        context.registerReceiver(receiveBroadCast, filter);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.found_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);

        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshing();

        initsView();
        getBanners();

        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) tvBgup.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            tvBgup.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }
        return view;
    }


    // 初始化页面列表布局
    private void initsView() {
        mTvTitle.setText(R.string.title_live_text);
//        mIbtnLeft.setVisibility(View.VISIBLE);
        mRightImg.setVisibility(View.VISIBLE);
        mRightImg.setImageResource(R.mipmap.search);
        mTvRight.setTextColor(Color.BLACK);
        mIamgeleft.setImageResource(R.mipmap.message);

        getDateFx(type);
//        initLoadMoreListener();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
//       mAdapter  = new FoundHotNewListFragAdapter(listData, getActivity());
        mAdapter = new RefreshAdapter(getActivity(), listData);
        mRecyclerView.setNestedScrollingEnabled(false); //处理滑动卡顿
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListener(new FoundHotNewListFragAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "").equals("") || PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "") == null) {
                    new AlertDialog.Builder(getActivity())
                            .setMessage(R.string.me_check_out)
                            .setNegativeButton(R.string.me_sure, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                                    intent2.putExtra("check_user", true);
//                                    intent2.putExtra("check_out_user", true);
                                    startAc(intent2);
//                                    getActivity().finish();
                                }
                            })
                            .setNeutralButton(R.string.me_cancle, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    return;
                                }
                            })
                            .create().show();
                } else {

                    showProgressDialog();

                    //status;// 状态 -1删除 1直播 2预告 3结束 4点播 5回放
//                if (listData.get(position).getStatus() == 1) {
//
//                }

//                Intent intent = new Intent(getActivity(), WatchActivity.class);
//                param.watchId = lessonDeta.getWebinarId()+"";
//                intent.putExtra("param", param);

                    //status;// 状态 -1删除 1直播 2预告 3结束 4点播 5回放
//                switch (listData.get(position).getStatus()) {
//                    case 1:
//                        intent.putExtra("type", VhallUtil.WATCH_LIVE);
//                        break;
//                    case 2:
//                        intent.putExtra("type", VhallUtil.WATCH_LIVE);
//                        break;
//                    case 3:
//                        intent.putExtra("type", VhallUtil.WATCH_PLAYBACK);
//                        break;
//                    case 4:
//                        intent.putExtra("type", VhallUtil.WATCH_LIVE);
//                        break;
//                    case 5:
//                        intent.putExtra("type", VhallUtil.WATCH_LIVE);
//                        break;
//                }
//                intent.putExtra("type", VhallUtil.WATCH_LIVE);
//                intent.putExtra("lessonDeta", lessonDeta);
//                startAc(intent);

                    if (index.equals("0")) {
                        getDate(listData.get(position).getId(), position);
                    } else if (index.equals("1")) {
                        getDate(listData.get(position).getId(), position);
                    } else if (index.equals("2")) {
                        getDate(listData.get(position).getId(), position);
                    }

                }


                /**
                 * 跳转后请求代码
                 * 先注销，需要再用
                 */
//                Intent i = new Intent(getActivity(), WatchActivity.class);  //自定义打开的界面
//                i .putExtra("biz_id",listData.get(position).getId());
//                i .putExtra("biz_type","0");
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                getActivity().startActivity(i);
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        dismissProgressDialog();
//
//                    }
//                }, 800);

            }
        });
//        设置分隔线
//        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), LinearLayoutManager.VERTICAL, 50, getResources().getColor(R.color.select)));
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
                getDateFx(type);
                getBanners();

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
                            getDateFx(type);
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


    /**
     * 加载广告轮播图
     */
    private void getBanners() {
        addSubscription(apiStores.getBanners("31", "", "", 1), new ApiCallback<GetBannersEn>() {
            @Override
            public void onSuccess(final GetBannersEn model) {
                // 填充轮播图
                List<String> strList = new ArrayList<>();
                for (int i = 0; i < model.getList().size(); i++) {
                    strList.add(model.getList().get(i).getImg());
                }
                mFaxianListHeardBanner.setList(strList);
                mFaxianListHeardBanner.setOnBannerItemClickListener(new BannerView.OnBannerItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        if (TextUtils.equals(model.getList().get(position).getUrl(),"")||model.getList().get(position).getUrl()==null){
                            showToas("无数据");
                        }else {
                            Intent intent = new Intent(getActivity(), BannerDetailActivity.class);
                            intent.putExtra("banner_url", model.getList().get(position).getUrl());
                            startAc(intent);
                        }

                    }
                });
            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });
    }


    /**
     * 发现课程
     */
    public void getDateFx(String type) {
        addSubscription(apiStores.searchLessonPeriodsBySubject("", PrefUtils.getString(getActivity(), Constants.USER_ID, ""), type, pageNum), new ApiCallback<ConcernLessons>() {
            @Override
            public void onSuccess(ConcernLessons model) {
                if ("200".equals(model.getCode())) {

                    loadMore = model.getData().isHasNextPage();//判断是否上拉加载更多

//                    if (!loadMore){
//                        refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
//                    }
//                    mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
                    if (pageNum == 1 && mAdapter != null) {
                        mAdapter.removeAll(listData);
                        listData.clear();
                    }
                    listDataNew = (List<LessonItemDetalEn>) model.getData().getList();
                    listData.addAll(listDataNew);
//                    mAdapter.addListItem(listDataNew);
                    mAdapter.notifyDataSetChanged();
                }

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

            @Override
            public void onFailure(String code) {
//                if (mSwipe != null) {
//                    mSwipe.setRefreshing(false);
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
//                if (mSwipe != null) {
//                    mSwipe.setRefreshing(false);
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

    private void getDate(String lessonId, final int position) {
        addSubscription(apiStores.lessonPeriodsDetail(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonId), new ApiCallback<lessonPeriodsDetail>() {
            @Override
            public void onSuccess(lessonPeriodsDetail model) {
                if ("200".equals(model.getCode())) {
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
                                listData.get(position).setAmount(lessonDeta.getAmount());
                            }else if (lessonDeta.getAmount()!=listData.get(position).getAmount()){
                                noWatch = true;
                                listData.get(position).setAmount(lessonDeta.getAmount());
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
                                listData.get(position).setPlaybackAmount(lessonDeta.getPlaybackAmount());
                            }else if (lessonDeta.getPlaybackAmount()!=listData.get(position).getPlaybackAmount()){
                                noWatch = true;
                                listData.get(position).setPlaybackAmount(lessonDeta.getPlaybackAmount());
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
            }

            @Override
            public void onFailure(String code) {
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
//                dismissProgressDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissProgressDialog();

                    }
                }, 800);
            }
        });
    }


    private void initLoadMoreListener() {

        /**
         * 由于滑动冲突，所以只能对scrollview监听实现上拉加载
         */
        nestScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    // 向下滑动
                }

//                if (scrollY < oldScrollY) {
//                    // 向上滑动
//
//                    if (scrollY <= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())-800) {
//                        // 底部
//                        //设置正在加载更多
//                        if (More){
//                        mAdapter.changeMoreStatus(mAdapter.LOADING_MORE);
//                            More = false;
//                        }
////                        if (loadMore) {
////                            pageNum++;
////                            getDateFx(type);
////                        } else {
////                            mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
//////                        showToas("没有更多数据加载");
////                        }
//
//                    }
//                }

                if (scrollY == 0) {
                    // 顶部
                }

                Log.e(TAG, "v.getChildAt(0).getMeasuredHeight(): " + v.getChildAt(0).getMeasuredHeight());
                Log.e(TAG, "v.getMeasuredHeight(): " + v.getMeasuredHeight());
                Log.e(TAG, "oldScrollY: " + oldScrollY);
                Log.e(TAG, "scrollY: " + scrollY);
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // 底部
                    //设置正在加载更多
                    mAdapter.changeMoreStatus(mAdapter.LOADING_MORE);
                    if (loadMore) {
                        pageNum++;
                        getDateFx(type);
                    } else {
//                        mRecyclerView.smoothScrollToPosition(listData.size());
                        mAdapter.changeMoreStatus(mAdapter.NO_LOAD_MORE);
//                        showToas("没有更多数据加载");
                    }

                }
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

//        /**
//         * 退出登录发送消息
//         */
//        String socketOutMessage = Constants.socketLoginOut();
//        try {
//            Log.i("---------", "发送消息为：" + socketOutMessage);
//            if (mIBackService == null) {
//                Toast.makeText(getActivity(),
//                        "socket连接失败", Toast.LENGTH_SHORT).show();
//            } else {
//                boolean isSend = mIBackService.sendMessage(socketOutMessage);
//                Toast.makeText(getActivity(),
//                        isSend ? "success" : "fail", Toast.LENGTH_SHORT)
//                        .show();
//            }
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
//        // 注销广播 最好在onPause上注销
//        getActivity().unregisterReceiver(mReceiver);
//        // 注销服务
//        getActivity().unbindService(conn);


    }

    @OnClick({R.id.iamgeleft, R.id.Right_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iamgeleft:
                startAc(MessageActivity.class);
                break;
            case R.id.Right_img:
                startAc(SearchFound.class);
                break;
        }
    }


//    /**
//     * socket长连接
//     */
//    private void socketLine() {
//        mServiceIntent = new Intent(getActivity(), BackService.class);
//    }


//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//            height = nestScrollview.getScrollY();
//        }else {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//            nestScrollview.scrollTo(0,height);
//
//                }
//            },0);
//        }
//    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && noWatch) {
            noWatch = false;
            areYounity = true;
//            mAdapter.notifyDataSetChanged();
        }
    }

    class ReceiveBroadCast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //得到广播中得到的数据，并显示出来
            String id = intent.getExtras().getString("id");
            liveTime = intent.getExtras().getInt("live_time");
            if (liveTime > 0){
                for (int i = 0; i < listData.size(); i++) {
                    if (TextUtils.equals(listData.get(i).getLessonId(),id)){
                        listData.get(i).setDurationTime(listData.get(i).getDurationTime()+liveTime);
                        mAdapter.notifyDataSetChanged();
                        liveTime = 0;
                        break;
                    }
                }
            }else if (areYounity){
                areYounity = false;
                mAdapter.notifyDataSetChanged();
            }
//            String lookSize = intent.getExtras().getString("looksize");
//            for (int i = 0; i < mStudyList.size(); i++) {
//                if (mStudyList.get(i).getId().equals(id)){
//                    mStudyList.get(i).setLikesCount(Integer.parseInt(starSize));
//                    mStudyList.get(i).setWatch(Integer.parseInt(lookSize));
//                }
//
//            }
////            String address = intent.getExtras().getString("address");
//
////            gasadderss.setText("地址：\n  "+address);
////            gasName.setText(gasname);
//            mRecyclerAdapter.notifyDataSetChanged();
        }
    }
}
