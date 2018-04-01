package com.qmx163.activity.StudyToday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.application.MyApplication;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.data.bean.Mebean.StudyCollectionEn;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by likai on 17-8-31
 * 我的收藏页面
 */

public class StudyCollectionActivity extends BaseAc {

    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    //    @BindView(R.id.swipe)
//    SwipeRefreshLayoutCompat mSwipe;
    public static final int REQUEST_CODE = 0;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private int pressPosition = -1;
    RefreshLayout refreshLayout;

    int selectposition = -1;
    private int pageNum = 1;  //翻页
    private boolean loadMore = true;
    List<StudyCollectionEn.DataBean.ListBean> mStudyCollectionList = new ArrayList<>();
    private RecyclerAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_collection);
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

        mIbtnLeft.setVisibility(View.VISIBLE);
        mTvTitle.setText(R.string.me_collection);

        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshing();

//        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                pageNum = 1;
//                getData();
//            }
//        });


        getData();
    }

    private void getData() {
        addSubscription(apiStores.getMyCollect(PrefUtils.getString(StudyCollectionActivity.this, Constants.USER_ID, ""), "0", pageNum), new ApiCallback<StudyCollectionEn>() {
            @Override
            public void onSuccess(StudyCollectionEn model) {
                if (TextUtils.equals(model.getCode(), "200")) {
                    loadMore = model.getData().isHasNextPage();
                    if (pageNum == 1) {
                        mStudyCollectionList.clear();
                        mStudyCollectionList = model.getData().getList();

                        mRecyclerAdapter = new RecyclerAdapter<StudyCollectionEn.DataBean.ListBean>(StudyCollectionActivity.this, mStudyCollectionList, R.layout.item_study_today_detail) {
                            @Override
                            public void convert(RecyclerViewHolder helper, final StudyCollectionEn.DataBean.ListBean item, final int position) {
                                RelativeLayout mRlItem = helper.getView(R.id.rl_item);
                                HorizontalScrollView scrollView = helper.getView(R.id.scroll_view);
                                final TextView delete = helper.getView(R.id.delete);


                                ImageView ivImg = helper.getView(R.id.iv_img);
                                helper.setText(R.id.tv_time, item.getIssueDay());
                                helper.setText(R.id.tv_look, item.getWatch() + "");
                                helper.setText(R.id.tv_star, item.getLikesCount() + "");
                                helper.setText(R.id.tv_title, item.getTitle());
                                Glide.with(StudyCollectionActivity.this).load(
                                        item.getImg()).dontAnimate().centerCrop().error(R.mipmap.logo).crossFade().into(ivImg);

                                //设置item主体为填充父窗体
                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(MyApplication.getInstance().getmWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
                                mRlItem.setLayoutParams(lp);

                                mRlItem.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        pressPosition = position;
                                        mStudyCollectionList.get(position).setWatch(mStudyCollectionList.get(position).getWatch() + 1);
                                        Intent intent = new Intent(StudyCollectionActivity.this, StudyDetailActivity.class);
                                        intent.putExtra(Constants.STUDY_DASK_ID, mStudyCollectionList.get(position).getBizId());
//                            intent.putExtra(Constants.STUDY_CONTENT, mStudyCollectionList.get(position).getContent());
                                        intent.putExtra(Constants.STUDY_TITLE, mStudyCollectionList.get(position).getTitle());
//                            intent.putExtra(Constants.STUDY_COMMENT_COUNT, mStudyCollectionList.get(position).getCommentsCount() + "");
                                        startActivityForResult(intent, REQUEST_CODE);

                                    }
                                });

                                /**
                                 * 删除收藏
                                 */
                                delete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        addSubscription(apiStores.delCollect(item.getId()), new ApiCallback<RegisteredEn>() {
                                            @Override
                                            public void onSuccess(RegisteredEn model) {
                                                if (TextUtils.equals(model.getCode(), "200")) {
                                                    showToas("已取消收藏");
                                                    mStudyCollectionList.remove(position);
                                                    mRecyclerAdapter.notifyDataSetChanged();
//                                                if (TextUtils.equals(model.getMessage(), "成功")) {
//                                                }
                                                }

                                            }

                                            @Override
                                            public void onFailure(String code) {
                                                //返回999为已经收藏
                                                if (TextUtils.equals(code, "999")) {
                                                    showToas("您已收藏");
                                                }
                                            }

                                            @Override
                                            public void onFinish() {

                                            }
                                        });
                                    }
                                });

                                //设置监听事件
                                scrollView.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        switch (event.getAction()) {

                                            case MotionEvent.ACTION_DOWN:

                                                break;

                                            case MotionEvent.ACTION_MOVE:
                                                if (selectposition != position && selectposition != -1) {
//                                        notifyItemChanged(selectposition);
                                                    notifyDataSetChanged();
                                                    selectposition = -1;
                                                }
                                                break;
                                            case MotionEvent.ACTION_UP:
                                                //获得HorizontalScrollView滑动的水平方向值.
                                                HorizontalScrollView h_scrollview = (HorizontalScrollView) v;
                                                int scrollX = h_scrollview.getScrollX();
                                                ViewHolder holder = (ViewHolder) h_scrollview.getTag();
                                                //获得操作区域的长度
                                                int actionW = delete.getWidth();
                                                //注意使用smoothScrollTo,这样效果看起来比较圆滑,不生硬
                                                //如果水平方向的移动值<操作区域的长度的一半,就复原
                                                if (scrollX < actionW / 2) {
                                                    h_scrollview.smoothScrollTo(0, 0);
                                                    selectposition = -1;
                                                } else { //否则的话显示操作区域
                                                    h_scrollview.smoothScrollTo(actionW, 0);
                                                    selectposition = position;
                                                }

                                                return true;
                                        }
                                        return false;
                                    }
                                });

                                //这里防止删除一条item后,item处于操作状态,直接还原
                                if (scrollView.getScrollX() != 0) {
                                    scrollView.scrollTo(0, 0);
                                }

                            }
                        };


                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setNestedScrollingEnabled(false);
                        mRecyclerView.setLayoutManager(new GridLayoutManager(StudyCollectionActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                        mRecyclerView.setAdapter(mRecyclerAdapter);
                    } else {
                        mStudyCollectionList.addAll(model.getData().getList());
                        mRecyclerAdapter.notifyDataSetChanged();
                    }


                    if (mStudyCollectionList.size() == 0) {
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
//                if (mSwipe!=null){
//                    mSwipe.setRefreshing(false);
//                }
dismissProgressDialog();
                if (code.equals("-666") || mStudyCollectionList.size() == 0) {
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
//                if (mSwipe!=null){
//                mSwipe.setRefreshing(false);
//                }
                dismissProgressDialog();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
//            if (resultCode == 1) {
//                String k = data.getIntExtra("star_size",0)+"";
//                int likeCount = Integer.parseInt(mStudyCollectionList.get(pressPosition).getLikesCount());
//                mStudyCollectionList.get(pressPosition).setLikesCount(likeCount + data.getIntExtra("star_size", 0) + "");
                showProgressDialog();
                getData();
//            }
//            initData();
//            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private void refreshing() {
//        refreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setEnableLoadmoreWhenContentNotFull(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                refreshlayout.setLoadmoreFinished(false);
                pageNum = 1;
                getData();
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
                            getData();
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

}
