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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.activity.MainActivity;
import com.qmx163.adapter.RecyclerManagerAdapter;
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
 * Created by likai on 2017/9/5.
 */

public class StudyCommentDetalActivity extends BaseAcNoScroll implements BackEditext.BackListener, CommentSendListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    //    @BindView(R.id.swipe)
//    SwipeRefreshLayout mSwipe;
    RefreshLayout refreshLayout;


    private int pageNum = 1;  //学习id
    private int commentPosition = 0;  //学习id
    private boolean hasNextPage = false;  //是否有下一页
    private boolean isLoadmore = true;  // 加载下一页是否加载完成

//    private RecyclerAdapter commentAdapter; //评论适配器
    private RecyclerManagerAdapter commentManagerAdapter;
    private List<StudyComment> commentList = new ArrayList<>(); // 评论列表集合
    private String studyId = "";
    private boolean jPushComment = false;

    CommentDialog mCommentDialog;
    TextView tv_to_comment;
    private String parentMemberId = ""; //复评论人id
    private String parentCommentId = ""; //复品论id

    List<Boolean> showAllList = new ArrayList<>();
    List<StudyComment> list = new ArrayList<>();
    StudyCommentManager studyCommentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_comment_detal);
        ButterKnife.bind(this);
        studyId = getIntent().getStringExtra(Constants.STUDY_DASK_ID);
        jPushComment = getIntent().getBooleanExtra("jpush_comment", false);

        mIbtnLeft.setVisibility(View.VISIBLE);
        mTvTitle.setText(R.string.comment_text);

        studyCommentManager = new StudyCommentManager(list);

        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshing();

        showProgressDialog();

        getCommentData();

//        initLoadMoreListener();

        mIamgeleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCollector.activities.size() == 1) {
                    Intent intent1 = new Intent(StudyCommentDetalActivity.this, MainActivity.class);
                    if (JpushReceive.jPushMessage) {
                        intent1.putExtra("jpush_message", true);
                    } else if (JpushReceive.jPushComment) {
                        intent1.putExtra("jpush_comment", true);
                    }
                    startAc(intent1);
                    finish();

                }
                finish();
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
            Intent intent1 = new Intent(StudyCommentDetalActivity.this, MainActivity.class);
            if (JpushReceive.jPushMessage) {
                intent1.putExtra("jpush_message", true);
            } else if (JpushReceive.jPushComment) {
                intent1.putExtra("jpush_comment", true);
            }
            startAc(intent1);
            finish();

        }
        finish();
    }

    /**
     * 获取课件详情（包括评论），展示评论
     */
    private void getCommentData() {
        addSubscription(apiStores.studyTaskDetail(studyId, PrefUtils.getString(StudyCommentDetalActivity.this, Constants.USER_ID, ""), pageNum), new ApiCallback<StudyTaskDetailEn>() {
            @Override
            public void onSuccess(StudyTaskDetailEn model) {

                isLoadmore = true;
                 list = model.getData().getCommentPages().getList();
                hasNextPage = model.getData().getCommentPages().isHasNextPage();
//                studyCommentManager.setLike(0,true);
//                studyCommentManager.setLike(3,true);
//
//                for (int i = 0; i < studyCommentManager.size(); i++) {
//                    Log.i("----qian", "onSuccess: "+studyCommentManager.getCommentByIndex(i).getComment().getContent()+"--"+i);
//                    Log.i("----qian", "onSuccess: "+studyCommentManager.getCommentByIndex(i).getComment().getCommentLikeCount()+"--"+i);
//                }
//                StudyComment studyComent = new StudyComment();
//                studyComent.setContent("测试内容111");
//                studyComent.setMemberName("测试数据111");
//
//                StudyComment studyComent2 = new StudyComment();
//                studyComent2.setContent("测试数据内容222");
//                studyComent2.setMemberName("测试数据222");
//
//                studyCommentManager.addChildComment(0,studyComent);
//                studyCommentManager.addChildComment(2,studyComent2);

//                Log.i("----", "onSuccess: ------------------------------------------");
//
//                for (int i = 0; i < studyCommentManager.size(); i++) {
//                    Log.i("----", "onSuccess: " + studyCommentManager.getCommentByIndex(i).getComment().getContent() + "--" + i);
////                    Log.i("----", "onSuccess: "+studyCommentManager.getCommentByIndex(i).getComment().getCommentLikeCount()+"--"+i);
//                }

//                studyCommentManager.getCommentByIndex(1).getComment().setChildComments();
//                studyCommentManager = new StudyCommentManager(commentList);
                if (pageNum == 1) {
                    studyCommentManager.cleanManager();
                    studyCommentManager.addList(list);
//                    commentList.clear();
                    showAllList.clear();
//                    commentList = list;

                } else {
                    studyCommentManager.addList(list);
//                    commentList.addAll(list);
                }


                for (int i = 0; i < studyCommentManager.size(); i++) {
                    showAllList.add(true);
                }
                if (commentManagerAdapter == null) {

                    commentManagerAdapter = new RecyclerManagerAdapter(StudyCommentDetalActivity.this, studyCommentManager, R.layout.item_study_task_manager) {
                        @Override
                        public void convert(RecyclerViewHolder helper, final Object item, final int position) {
                            LinearLayout llContextParnt = helper.getView(R.id.ll_context_parent);
                            LinearLayout llContextSun = helper.getView(R.id.ll_context_sun);

                            final TextView tvStar = helper.getView(R.id.tv_star);
                            TextView tvComment = helper.getView(R.id.tv_context);
                            TextView tvShowMore = helper.getView(R.id.show_more);
                            final RelativeLayout rlContext = helper.getView(R.id.rl_context);
                            final TextView tvCommentAll = helper.getView(R.id.tv_context_all);
                            de.hdodenhof.circleimageview.CircleImageView circleImageView = helper.getView(R.id.civ_head);
                            tv_to_comment = helper.getView(R.id.tv_to_comment);
                            TextView tv_line_down = helper.getView(R.id.tv_line_down);
                            TextView tv_line = helper.getView(R.id.tv_line);


                            TextView tvCommentSun = helper.getView(R.id.tv_comment_sun);
                            TextView tvNameSun = helper.getView(R.id.tv_name_sun);
                            TextView tvName = helper.getView(R.id.tv_name);
                            TextView tvTime = helper.getView(R.id.tv_time);


                            if (studyCommentManager.getCommentByIndex(position).getComment().getMore() == null){
//                            if (showAllList.get(position)) {
                                //评论展示（用两个textview）
                                tvCommentAll.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                    @Override
                                    public boolean onPreDraw() {
                                        tvCommentAll.getViewTreeObserver().removeOnPreDrawListener(this);
                                        Log.e("---------position----", "convert: " + tvCommentAll.getLineCount());
                                        int k = tvCommentAll.getLineCount();
                                        if (k > 3) {
                                            studyCommentManager.getCommentByIndex(position).getComment().setMore("2");
                                            tvCommentAll.setVisibility(View.GONE);
                                            rlContext.setVisibility(View.VISIBLE);
                                        } else {
                                            studyCommentManager.getCommentByIndex(position).getComment().setMore("1");
                                            tvCommentAll.setVisibility(View.VISIBLE);
                                            rlContext.setVisibility(View.GONE);
                                        }
                                        return false;
                                    }
                                });

//                            }

                            }else {
                                if (studyCommentManager.getCommentByIndex(position).getComment().getMore() == "2"){  //需要判断是否显示全部

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


                            if (position == studyCommentManager.size()-1){
                                tv_line.setVisibility(View.VISIBLE);
                            }else {
                                tv_line.setVisibility(View.GONE);
                            }

                            //判断条目是子评论还是父评论
                            if (studyCommentManager.getCommentByIndex(position).getType() == 0) {
                                llContextParnt.setVisibility(View.VISIBLE);
                                llContextSun.setVisibility(View.GONE);
                                if (position == 0) {
                                    tv_line_down.setVisibility(View.GONE);
                                } else {
                                    tv_line_down.setVisibility(View.VISIBLE);
                                }
                                tvStar.setText(studyCommentManager.getCommentByIndex(position).getComment().getCommentLikeCount() + "");
                                tvName.setText(studyCommentManager.getCommentByIndex(position).getComment().getMemberName());
                                tvCommentAll.setText(studyCommentManager.getCommentByIndex(position).getComment().getContent());
                                tvComment.setText(studyCommentManager.getCommentByIndex(position).getComment().getContent());
                                tvTime.setText(DateUtil.testDiffDate(studyCommentManager.getCommentByIndex(position).getComment().getAddTime()));
                                if (studyCommentManager.getCommentByIndex(position).getComment().isLike()) {
                                    Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment_on);
                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                    tvStar.setCompoundDrawables(null, null, drawable, null);
                                } else {
                                    Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment);
                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                    tvStar.setCompoundDrawables(null, null, drawable, null);
                                }
                                Glide.with(StudyCommentDetalActivity.this).load(studyCommentManager.getCommentByIndex(position).getComment().getHeadImg()).dontAnimate().error(R.mipmap.xiaoxi).into(circleImageView);


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
                                        addSubscription(apiStores.addStudyTaskCommentLike(studyCommentManager.getCommentByIndex(position).getComment().getId(), PrefUtils.getString(StudyCommentDetalActivity.this, Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
                                            @Override
                                            public void onSuccess(RegisteredEn model) {
                                                if (TextUtils.equals(model.getCode(), "200")) {
                                                    tvStar.setText(model.getData().getLikeCount() + "");
//                                                    commentList.get(position).setCommentLikeCount(model.getData().getLikeCount());
                                                    studyCommentManager.getCommentByIndex(position).getComment().setCommentLikeCount(model.getData().getLikeCount());
                                                    showToas(model.getData().getMsg());
                                                    if (TextUtils.equals(model.getData().getMsg(), "点赞成功")) {
//                                                mIvStar.setImageResource(R.mipmap.study_star_on);
                                                        Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment_on);
                                                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                                        tvStar.setCompoundDrawables(null, null, drawable, null);
//                                                        commentList.get(position).setLike(true);
                                                        studyCommentManager.setLike(position, true);

                                                    } else {
//                                                mIvStar.setImageResource(R.mipmap.study_star);
                                                        Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment);
                                                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                                        tvStar.setCompoundDrawables(null, null, drawable, null);
//                                                        commentList.get(position).setLike(false);
                                                        studyCommentManager.setLike(position, false);

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
//                            //点击复评
                                tv_to_comment.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        parentMemberId = studyCommentManager.getCommentByIndex(position).getComment().getMemberId();
                                        parentCommentId = studyCommentManager.getCommentByIndex(position).getComment().getId();
                                        commentPosition = position;
                                        if (mCommentDialog == null) {
                                            mCommentDialog = new CommentDialog(StudyCommentDetalActivity.this, R.style.dialog_translucent);
//                    commentDialog.setOnCommentLisenter(this);
                                            mCommentDialog.setCommentSendListener(StudyCommentDetalActivity.this);
                                            mCommentDialog.setBackLisenter(StudyCommentDetalActivity.this);
                                        }
                                        mCommentDialog.show();

                                    }
                                });


                                Log.i("----", "convert: " + "--" + studyCommentManager.getCommentByIndex(position).getType());
                            } else {
                                llContextParnt.setVisibility(View.GONE);
                                llContextSun.setVisibility(View.VISIBLE);
                                tv_line_down.setVisibility(View.GONE);
                                tvCommentSun.setText(studyCommentManager.getCommentByIndex(position).getComment().getContent());
                                tvNameSun.setText(studyCommentManager.getCommentByIndex(position).getComment().getMemberName());
                            }
                        }
                    };


//                    commentAdapter = new RecyclerAdapter<StudyComment>(StudyCommentDetalActivity.this, commentList, R.layout.item_study_task) {
//                        @Override
//                        public void convert(RecyclerViewHolder helper, final StudyComment item, final int position) {
//                            RecyclerView recyclerViewSun = helper.getView(R.id.recycler_view_sun);
//                            List<StudyComment> commentSunList = new ArrayList<>(); // 子评论列表集合
//                            if (commentSunList != null) {
//                                commentSunList.clear();
//                            }
//                            commentSunList = commentList.get(position).getChildComments();
//                            de.hdodenhof.circleimageview.CircleImageView circleImageView = helper.getView(R.id.civ_head);
//                            final TextView tvStar = helper.getView(R.id.tv_star);
//                            final TextView tvComment = helper.getView(R.id.tv_context);
//                            TextView tvShowMore = helper.getView(R.id.show_more);
//                            final RelativeLayout rlContext = helper.getView(R.id.rl_context);
//                            final TextView tvCommentAll = helper.getView(R.id.tv_context_all);
//                            //此处缺少参数，无法判断是否点赞
//                            tvCommentAll.setText(item.getContent());
//                            tvComment.setText(item.getContent());
//                            if (showAllList.get(position)) {
//                                tvCommentAll.post(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        int k = tvCommentAll.getLineCount();
//                                        if (k > 3) {
//                                            tvCommentAll.setVisibility(View.GONE);
//                                            rlContext.setVisibility(View.VISIBLE);
//                                        } else {
//                                            tvCommentAll.setVisibility(View.VISIBLE);
//                                            rlContext.setVisibility(View.GONE);
//                                        }
//                                    }
//                                });
//                            }
//
//                            helper.setText(R.id.tv_name, item.getMemberName());
//                            helper.setText(R.id.tv_star, item.getCommentLikeCount() + "");
//                            helper.setText(R.id.tv_time, DateUtil.testDiffDate(item.getAddTime()));
//                            if (item.isLike()) {
//                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment_on);
//                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                                tvStar.setCompoundDrawables(null, null, drawable, null);
//                            } else {
//                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment);
//                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                                tvStar.setCompoundDrawables(null, null, drawable, null);
//                            }
//                            Glide.with(StudyCommentDetalActivity.this).load(commentList.get(position).getHeadImg()).dontAnimate().error(R.mipmap.xiaoxi).into(circleImageView);
//                            tv_to_comment = helper.getView(R.id.tv_to_comment);
//
//
//                            //点击展示全部评论
//                            tvShowMore.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    showAllList.set(position, false);
//                                    tvCommentAll.setVisibility(View.VISIBLE);
//                                    rlContext.setVisibility(View.GONE);
//                                }
//                            });
//
//                            //点击点赞
//                            tvStar.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    showProgressDialog();
//                                    addSubscription(apiStores.addStudyTaskCommentLike(item.getId(), PrefUtils.getString(StudyCommentDetalActivity.this, Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
//                                        @Override
//                                        public void onSuccess(RegisteredEn model) {
//                                            if (TextUtils.equals(model.getCode(), "200")) {
//                                                tvStar.setText(model.getData().getLikeCount() + "");
//                                                commentList.get(position).setCommentLikeCount(model.getData().getLikeCount());
//                                                showToas(model.getData().getMsg());
//                                                if (TextUtils.equals(model.getData().getMsg(), "点赞成功")) {
////                                                mIvStar.setImageResource(R.mipmap.study_star_on);
//                                                    Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment_on);
//                                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                                                    tvStar.setCompoundDrawables(null, null, drawable, null);
//                                                    commentList.get(position).setLike(true);
//
//                                                } else {
////                                                mIvStar.setImageResource(R.mipmap.study_star);
//                                                    Drawable drawable = getResources().getDrawable(R.mipmap.study_star_comment);
//                                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                                                    tvStar.setCompoundDrawables(null, null, drawable, null);
//                                                    commentList.get(position).setLike(false);
//                                                }
//                                            }
//
//                                        }
//
//                                        @Override
//                                        public void onFailure(String code) {
//                                            dismissProgressDialog();
//                                        }
//
//                                        @Override
//                                        public void onFinish() {
//                                            dismissProgressDialog();
//                                        }
//                                    });
//                                }
//                            });
//
//
//                            //点击复评
//                            tv_to_comment.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    parentMemberId = item.getMemberId();
//                                    parentCommentId = item.getId();
//                                    commentPosition = position;
//                                    if (mCommentDialog == null) {
//                                        mCommentDialog = new CommentDialog(StudyCommentDetalActivity.this, R.style.dialog_translucent);
////                    commentDialog.setOnCommentLisenter(this);
//                                        mCommentDialog.setCommentSendListener(StudyCommentDetalActivity.this);
//                                        mCommentDialog.setBackLisenter(StudyCommentDetalActivity.this);
//                                    }
//                                    mCommentDialog.show();
//
//                                }
//                            });
//
//                            RecyclerAdapter commentAdapterSun = new RecyclerAdapter<StudyComment>(StudyCommentDetalActivity.this, commentSunList, R.layout.item_study_sun_task) {
//                                @Override
//                                public void convert(RecyclerViewHolder helper, StudyComment item, int position) {
//                                    if (position == 0) {
//                                        helper.getView(R.id.tv_line).setVisibility(View.GONE);
//                                    }
//                                    helper.setText(R.id.tv_name, item.getMemberName());
//                                    helper.setText(R.id.tv_comment, item.getContent());
//                                }
//                            };
//                            recyclerViewSun.setHasFixedSize(true);
////                            recyclerViewSun.setNestedScrollingEnabled(false);
//                            recyclerViewSun.setLayoutManager(new GridLayoutManager(StudyCommentDetalActivity.this, 1, LinearLayoutManager.VERTICAL, false));
//                            recyclerViewSun.setAdapter(commentAdapterSun);
////                            recyclerViewSun.addItemDecoration(new RecycleViewDivider(StudyCommentDetalActivity.this, LinearLayoutManager.VERTICAL, 6, getResources().getColor(R.color.select)));
//
//                        }
//
//                        @Override
//                        public void onBindViewHolder(RecyclerViewHolder holder, int position, boolean isItem) {
//                            super.onBindViewHolder(holder, position, isItem);
//                        }
//                    };
                    mRecyclerView.setHasFixedSize(true);
//                    mRecyclerView.setNestedScrollingEnabled(false);
                    mRecyclerView.setLayoutManager(new GridLayoutManager(StudyCommentDetalActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                    mRecyclerView.setAdapter(commentManagerAdapter);
                } else {
                    commentManagerAdapter.notifyDataSetChanged();
                }

                if (hasNextPage) {
//                    commentAdapter.changeMoreStatus(commentAdapter.LOADING_MORE);
                    refreshLayout.setLoadmoreFinished(false);//将不会再次触发加载更多事件

                } else {
                    refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件

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
                refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadmore();
                dismissProgressDialog();
            }
        });
    }


//


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


        addSubscription(apiStores.addStudyTaskRecomment(PrefUtils.getString(StudyCommentDetalActivity.this, Constants.USER_ID, ""), parentMemberId, parentCommentId, comment), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                if (TextUtils.equals(model.getCode(), "200")) {
//                    getCommentData();
                    StudyComment addComment = new StudyComment();
                    addComment.setMemberName(PrefUtils.getString(StudyCommentDetalActivity.this, Constants.USER_NAME, ""));
                    addComment.setContent(comment);
                    studyCommentManager.addChildComment(commentPosition, addComment);
                    commentManagerAdapter.notifyDataSetChanged();
//                    commentList.get(commentPosition).getChildComments().add(addComment);
//                    commentAdapter.notifyDataSetChanged();
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
//                commentAdapter = null;
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
