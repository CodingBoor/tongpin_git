package com.qmx163.activity.StudyToday;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.activity.MainActivity;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseAcNoScroll;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.data.bean.Mebean.StudyComment;
import com.qmx163.data.bean.Mebean.StudyTaskDetailEn;
import com.qmx163.listener.CommentSendListener;
import com.qmx163.net.ApiCallback;
import com.qmx163.popuwindow.CommentDialog;
import com.qmx163.service.JpushReceive;
import com.qmx163.util.ActivityCollector;
import com.qmx163.util.DateUtil;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.manager.AsyncRun;
import com.qmx163.view.BackEditext;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 评论详情
 * Created by lzp on 2017/9/5.
 */

public class StudyCommentDetActivity extends BaseAcNoScroll implements BackEditext.BackListener, CommentSendListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    //    @BindView(R.id.swipe)
//    SwipeRefreshLayout mSwipe;
    RefreshLayout refreshLayout;


    private int pageNum = 1;  //学习id
    private int commentPosition = 0;  //学习id
    private boolean hasNextPage = false;  //是否有下一页
    private boolean isLoadmore = true;  // 加载下一页是否加载完成

    private RecyclerAdapter commentAdapter; //评论适配器
    private List<StudyComment> commentList = new ArrayList<>(); // 评论列表集合
    private String studyId = "";
    private boolean jPushComment = false;

    CommentDialog mCommentDialog;
    TextView tv_to_comment;
    private String parentMemberId = ""; //复评论人id
    private String parentCommentId = ""; //复品论id

    List<Boolean> showAllList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_comment_detal);
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
        studyId = getIntent().getStringExtra(Constants.STUDY_DASK_ID);
        jPushComment = getIntent().getBooleanExtra("jpush_comment", false);

        mIbtnLeft.setVisibility(View.VISIBLE);
        mTvTitle.setText(R.string.comment_text);

        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshing();

        showProgressDialog();

        getCommentData();

//        initLoadMoreListener();

        mIamgeleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCollector.activities.size() == 1) {
                    Intent intent1 = new Intent(StudyCommentDetActivity.this, MainActivity.class);
                    if (JpushReceive.jPushMessage) {
                        intent1.putExtra("jpush_message", true);
                    } else if (JpushReceive.jPushComment) {
                        intent1.putExtra("jpush_comment", true);
                    }
                    startAc(intent1);
                    finish();

                }else if (jPushComment){
                    Intent intent1 = new Intent(StudyCommentDetActivity.this, MainActivity.class);
//                    if (JpushReceive.jPushMessage) {
//                        intent1.putExtra("jpush_message", true);
//                    } else if (JpushReceive.jPushComment) {
//                        intent1.putExtra("jpush_comment", true);
//                    }
                    startAc(intent1);
                    finish();
                }else {
                    finish();
                }
//                finish();
            }
        });

        //下拉刷新监听
//        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                pageNum = 1;
////                commentList.clear();
////                showAllList.clear();
//                commentAdapter = null;
//                getCommentData();
//            }
//        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (ActivityCollector.activities.size() == 1) {
            Intent intent1 = new Intent(StudyCommentDetActivity.this, MainActivity.class);
            if (JpushReceive.jPushMessage) {
                intent1.putExtra("jpush_message", true);
            } else if (JpushReceive.jPushComment) {
                intent1.putExtra("jpush_comment", true);
            }
            startAc(intent1);
            finish();

        }else if (jPushComment){
            Intent intent1 = new Intent(StudyCommentDetActivity.this, MainActivity.class);
//                    if (JpushReceive.jPushMessage) {
//                        intent1.putExtra("jpush_message", true);
//                    } else if (JpushReceive.jPushComment) {
//                        intent1.putExtra("jpush_comment", true);
//                    }
            startAc(intent1);
            finish();
        }else {
            finish();
        }
//        finish();
    }

    /**
     * 获取课件详情（包括评论），展示评论
     */
    private void getCommentData() {
        String k = PrefUtils.getString(StudyCommentDetActivity.this, Constants.USER_ID, "");
        addSubscription(apiStores.studyTaskDetail(studyId, PrefUtils.getString(StudyCommentDetActivity.this, Constants.USER_ID, ""), pageNum), new ApiCallback<StudyTaskDetailEn>() {
            @Override
            public void onSuccess(StudyTaskDetailEn model) {

                isLoadmore = true;
                List<StudyComment> list = model.getData().getCommentPages().getList();
                hasNextPage = model.getData().getCommentPages().isHasNextPage();

                if (pageNum == 1) {
                    commentList.clear();
                    showAllList.clear();
                    commentList = list;
                } else {
                    commentList.addAll(list);
                }

                for (int i = 0; i < commentList.size(); i++) {
                    showAllList.add(true);
                }
                if (commentAdapter == null) {
                    commentAdapter = new RecyclerAdapter<StudyComment>(StudyCommentDetActivity.this, commentList, R.layout.item_study_task_new) {
                        @Override
                        public void convert(RecyclerViewHolder helper, final StudyComment item, final int position) {
                            RecyclerView recyclerViewSun = helper.getView(R.id.recycler_view_sun);
                            List<StudyComment> commentSunList = new ArrayList<>(); // 子评论列表集合
                            if (commentSunList != null) {
                                commentSunList.clear();
                            }
                            commentSunList = commentList.get(position).getChildComments();
                            de.hdodenhof.circleimageview.CircleImageView circleImageView = helper.getView(R.id.civ_head);
                            final TextView tvStar = helper.getView(R.id.tv_star);
                            final TextView tvComment = helper.getView(R.id.tv_context);
                            TextView tvShowMore = helper.getView(R.id.show_more);
                            final RelativeLayout rlContext = helper.getView(R.id.rl_context);
                            final TextView tvCommentAll = helper.getView(R.id.tv_context_all);
//                            final TextView tv_context_size = helper.getView(R.id.tv_content_size);
//                            tv_context_size.setText(item.getContent());
                            tvCommentAll.setText(item.getContent());
                            tvComment.setText(item.getContent());
                            //此处缺少参数，无法判断是否点赞

                            if (commentList.get(position).getMore() == null){
//                            if (showAllList.get(position)) {
                                //评论展示（用两个textview）
                                tvCommentAll.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                    @Override
                                    public boolean onPreDraw() {
                                        tvCommentAll.getViewTreeObserver().removeOnPreDrawListener(this);
                                        Log.e("---------position----", "convert: " + tvCommentAll.getLineCount());
                                        int k = tvCommentAll.getLineCount();
                                        if (k > 3) {
                                            commentList.get(position).setMore("2");
                                            tvCommentAll.setVisibility(View.GONE);
                                            rlContext.setVisibility(View.VISIBLE);
                                        } else {
                                            commentList.get(position).setMore("1");
                                            tvCommentAll.setVisibility(View.VISIBLE);
                                            rlContext.setVisibility(View.GONE);
                                        }
                                        return false;
                                    }
                                });

//                            }

                            }else {
                                if (commentList.get(position).getMore() == "2"){  //需要判断是否显示全部

                                    if (showAllList.get(position)){
                                        tvCommentAll.setVisibility(View.GONE);
                                        rlContext.setVisibility(View.VISIBLE);
                                    }else {
                                        tvCommentAll.setVisibility(View.VISIBLE);
                                        rlContext.setVisibility(View.GONE);
                                    }
                                }else {
                                    tvCommentAll.setVisibility(View.VISIBLE);
                                    rlContext.setVisibility(View.GONE);
                                }
                            }

//                            tv_context_size.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                                @Override
//                                public boolean onPreDraw() {
//                                    tv_context_size.getViewTreeObserver().removeOnPreDrawListener(this);
//
//                                    Log.e("---------draw----", "convert: " + tv_context_size.getLineCount());
//                                    return false;
//                                }
//                            });

//                            tvCommentAll.post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Log.e("---------runnable----", "convert: " + tv_context_size.getLineCount());
//
//                                }
//                            });
//                            Log.e("-------------------", "convert: " + tv_context_size.getLineCount());

//                            if (tvComment.getLineCount()>3){
//                                tvShowMore.setVisibility(View.VISIBLE);
//                                tvComment.setLines(3);
//                            }
                            helper.setText(R.id.tv_name, item.getMemberName());
                            helper.setText(R.id.tv_star, item.getCommentLikeCount() + "");
                            helper.setText(R.id.tv_time, DateUtil.testDiffDate(item.getAddTime()));
                            if (item.isLike()) {
                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment_on);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                tvStar.setCompoundDrawables(null, null, drawable, null);
                            } else {
                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment);
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                tvStar.setCompoundDrawables(null, null, drawable, null);
                            }
                            Glide.with(StudyCommentDetActivity.this).load(commentList.get(position).getHeadImg()).dontAnimate().error(R.mipmap.xiaoxi).into(circleImageView);
                            tv_to_comment = helper.getView(R.id.tv_to_comment);


                            //点击展示全部评论
                            tvShowMore.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showAllList.set(position, false);
                                    tvCommentAll.setVisibility(View.VISIBLE);
                                    rlContext.setVisibility(View.GONE);
                                }
                            });

                            //点击点赞
                            tvStar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showProgressDialog();
                                    addSubscription(apiStores.addStudyTaskCommentLike(item.getId(), PrefUtils.getString(StudyCommentDetActivity.this, Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
                                        @Override
                                        public void onSuccess(RegisteredEn model) {
                                            if (TextUtils.equals(model.getCode(), "200")) {
                                                tvStar.setText(model.getData().getLikeCount() + "");
                                                commentList.get(position).setCommentLikeCount(model.getData().getLikeCount());
                                                showToas(model.getData().getMsg());
                                                if (TextUtils.equals(model.getData().getMsg(), "点赞成功")) {
//                                                mIvStar.setImageResource(R.mipmap.study_star_on);
                                                    Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment_on);
                                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                                    tvStar.setCompoundDrawables(null, null, drawable, null);
                                                    commentList.get(position).setLike(true);

                                                } else {
//                                                mIvStar.setImageResource(R.mipmap.study_star);
                                                    Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment);
                                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                                    tvStar.setCompoundDrawables(null, null, drawable, null);
                                                    commentList.get(position).setLike(false);
                                                }
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
                            });


                            //点击复评
                            tv_to_comment.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    parentMemberId = item.getMemberId();
                                    parentCommentId = item.getId();
                                    commentPosition = position;
                                    if (mCommentDialog == null) {
                                        mCommentDialog = new CommentDialog(StudyCommentDetActivity.this, R.style.dialog_translucent);
//                    commentDialog.setOnCommentLisenter(this);
                                        mCommentDialog.setCommentSendListener(StudyCommentDetActivity.this);
                                        mCommentDialog.setBackLisenter(StudyCommentDetActivity.this);
                                    }
                                    mCommentDialog.show();

                                }
                            });

                            RecyclerAdapter commentAdapterSun = new RecyclerAdapter<StudyComment>(StudyCommentDetActivity.this, commentSunList, R.layout.item_study_sun_task) {
                                @Override
                                public void convert(RecyclerViewHolder helper, StudyComment item, int position) {
                                    if (position == 0) {
                                        helper.getView(R.id.tv_line).setVisibility(View.GONE);
                                    }
                                    helper.setText(R.id.tv_name, item.getMemberName());
                                    helper.setText(R.id.tv_comment, item.getContent());
                                }
                            };
                            recyclerViewSun.setHasFixedSize(true);
//                            recyclerViewSun.setNestedScrollingEnabled(false);
                            recyclerViewSun.setLayoutManager(new GridLayoutManager(StudyCommentDetActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                            recyclerViewSun.setAdapter(commentAdapterSun);
//                            recyclerViewSun.addItemDecoration(new RecycleViewDivider(StudyCommentDetalActivity.this, LinearLayoutManager.VERTICAL, 6, getResources().getColor(R.color.select)));

                        }

                        @Override
                        public void onBindViewHolder(RecyclerViewHolder holder, int position, boolean isItem) {
                            super.onBindViewHolder(holder, position, isItem);
                        }
                    };
                    mRecyclerView.setHasFixedSize(true);
//                    mRecyclerView.setNestedScrollingEnabled(false);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(StudyCommentDetActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                    mRecyclerView.setAdapter(commentAdapter);
                } else {
                    commentAdapter.notifyDataSetChanged();
                }

                if (hasNextPage) {
                    refreshLayout.setLoadmoreFinished(false);

//                    commentAdapter.changeMoreStatus(commentAdapter.LOADING_MORE);
                } else {
                    refreshLayout.setLoadmoreFinished(true);
//                    commentAdapter.changeMoreStatus(commentAdapter.NO_LOAD_MORE);
                }
            }


            @Override
            public void onFailure(String code) {
//                if (mSwipe!=null){
//                    mSwipe.setRefreshing(false);
//                }
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                refreshLayout.setLoadmoreFinished(true);
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
//                if (mSwipe!=null){
//                mSwipe.setRefreshing(false);
//                }
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                dismissProgressDialog();
            }
        });
    }


    private void initLoadMoreListener() {

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                try {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == commentAdapter.getItemCount()) {

                        //设置正在加载更多
                        if (hasNextPage && isLoadmore) {
                            isLoadmore = false;
                            pageNum++;
                            getCommentData();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
//                        >= recyclerView.computeVerticalScrollRange()&&!isLoadmore){
//                    showToas("zuihou一条");
//                }


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
    public void back() {
         /*隐藏软键盘*/
        InputMethodManager imm = (InputMethodManager) tv_to_comment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(tv_to_comment.getApplicationWindowToken(), 0);
        }
        AsyncRun.run(new Runnable() {
            @Override
            public void run() {
                if (mCommentDialog != null && mCommentDialog.isShowing()) {
                    mCommentDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void sendOk(final String comment) {
//        showToas("fasong成功"+comment);


        addSubscription(apiStores.addStudyTaskRecomment(PrefUtils.getString(StudyCommentDetActivity.this, Constants.USER_ID, ""), parentMemberId, parentCommentId, comment), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                if (TextUtils.equals(model.getCode(), "200")) {
//                    getCommentData();
                    StudyComment addComment = new StudyComment();
                    addComment.setMemberName(PrefUtils.getString(StudyCommentDetActivity.this, Constants.USER_NAME, ""));
                    addComment.setContent(comment);
                    commentList.get(commentPosition).getChildComments().add(addComment);
                    commentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });

    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
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
//                commentList.clear();
//                showAllList.clear();
                commentAdapter = null;
                getCommentData();
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
                        if (hasNextPage) {
                            if (isLoadmore) {
                                isLoadmore = false;
                                pageNum++;
                                getCommentData();
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

}
