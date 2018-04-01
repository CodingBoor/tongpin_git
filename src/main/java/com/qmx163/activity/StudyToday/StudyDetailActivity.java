package com.qmx163.activity.StudyToday;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.data.bean.Mebean.StudyCollection;
import com.qmx163.data.bean.Mebean.StudyComment;
import com.qmx163.data.bean.Mebean.StudyTaskDetailEn;
import com.qmx163.listener.CommentSendListener;
import com.qmx163.net.ApiCallback;
import com.qmx163.popuwindow.CommentDialog;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.manager.AsyncRun;
import com.qmx163.view.BackEditext;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create by likai on 17-8-31
 * 今日学习详情页
 */
public class StudyDetailActivity extends BaseAc implements BackEditext.BackListener, CommentSendListener {

    //    @BindView(R.id.web_context)
//    WebView mWebContext;
    @BindView(R.id.ll_web_context)
    RelativeLayout llWeb;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    @BindView(R.id.Right_img)
    ImageView mRightImg;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.activity_study_detail)
    LinearLayout mActivityStudyDetail;
    @BindView(R.id.tv_look)
    TextView mTvLook;
    @BindView(R.id.tv_star)
    TextView mTvStar;
    @BindView(R.id.tv_comment_size)
    TextView mTvCommentSize;
    @BindView(R.id.tv_comment_write)
    TextView mTvCommentWrite;
    @BindView(R.id.iv_comment)
    ImageView mIvComment;
    @BindView(R.id.iv_star)
    ImageView mIvStar;
    @BindView(R.id.iv_collection)
    ImageView mIvCollection;
    @BindView(R.id.ll_star)
    LinearLayout ll_star;
    WebView mWebContext;

    private String studyId = "";  //学习id
    private String studyContext = "";  //学习详情web
    private String studyTitle = "";  //学习详情标题
    private String studyCommentCount = "";  //评论条数
    private boolean starOn = false; // 点赞状态
    private int starSize = 0;
    private boolean collectionOn = false; //收藏状态
    private int pageNum = 1;  //翻页

    private RecyclerAdapter commentAdapter; //评论适配器
    private List<StudyComment> commentList = new ArrayList<>(); // 评论列表集合
    private CommentDialog mCommentDialog; //评论输入弹窗

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_detail);
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
//        studyContext = getIntent().getStringExtra(Constants.STUDY_CONTENT);
        studyTitle = getIntent().getStringExtra(Constants.STUDY_TITLE);
//        studyCommentCount = getIntent().getStringExtra(Constants.STUDY_COMMENT_COUNT);
        mTvTitle.setText(studyTitle);
        mIbtnLeft.setVisibility(View.VISIBLE);
//        mTvCommentSize.setText(studyCommentCount);
//        mWebContext.loadDataWithBaseURL(null, studyContext, "text/html", "UTF-8", null);

        mIamgeleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("star_size", starSize);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
                setResult(1, intent);
                finish();
            }
        });


        addSubscription(apiStores.studyTaskDetail(studyId, PrefUtils.getString(StudyDetailActivity.this, Constants.USER_ID, ""), pageNum), new ApiCallback<StudyTaskDetailEn>() {
            @Override
            public void onSuccess(StudyTaskDetailEn model) {
                ll_star.setVisibility(View.VISIBLE);
                commentList = model.getData().getCommentPages().getList();

                mTvStar.setText(model.getData().getLikesCount() + "");
                mTvLook.setText(model.getData().getWatch() + "");
                mTvCommentSize.setText(model.getData().getCommentsCount() + "");


                mWebContext = new WebView(StudyDetailActivity.this);
                llWeb.addView(mWebContext);


                mWebContext.loadDataWithBaseURL(null, model.getData().getContent(), "text/html", "UTF-8", null);

                mWebContext.setWebViewClient(new WebViewClient(){
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        showProgressDialog();

                    }

                    /**
                     * 加载给定URL资源内容   该方法待使用    判断webview是否可以返回,不能返回就隐藏返回按钮
                     */
                    @Override
                    public void onLoadResource(final WebView view, String url) {
                        dismissProgressDialog();
                        super.onLoadResource(view, url);

                    }

                    /**
                     * 页面加载完成回调方法，获取对应url地址
                     * */
                    @Override
                    public void onPageFinished(final WebView view, String url) {
                        dismissProgressDialog();
                        super.onPageFinished(view, url);
                    }

                    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                        view.setVisibility(View.GONE);
                        dismissProgressDialog();
                        super.onReceivedError(view, request, error);
                    }

                    /**
                     * 自己定义加载错误处理效果，比如：TeachCourse定义在没有网络时候，显示一张无网络的图片
                     *API 23之前调用
                     */
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        super.onReceivedError(view, errorCode, description, failingUrl);
                    }
                });
                if (model.getData().isLike()) {
                    starOn = true;
                    mIvStar.setImageResource(R.mipmap.study_star_on);
                }
                if (model.getData().isCollect()) {
                    collectionOn = true;
                    mIvCollection.setImageResource(R.mipmap.study_collection_on);
                }


                commentAdapter = new RecyclerAdapter<StudyComment>(StudyDetailActivity.this, commentList, R.layout.item_study_task) {
                    @Override
                    public void convert(RecyclerViewHolder helper, StudyComment item, int position) {
                        RecyclerView recyclerViewSun = helper.getView(R.id.recycler_view_sun);
                        List<StudyComment> commentSunList = new ArrayList<>(); // 子评论列表集合
                        if (commentSunList != null) {
                            commentSunList.clear();
                        }
                        commentSunList = commentList.get(position).getChildComments();
                        helper.setText(R.id.tv_name, item.getMemberName());
                        helper.setText(R.id.tv_context, item.getContent());

                        RecyclerAdapter commentAdapterSun = new RecyclerAdapter<StudyComment>(StudyDetailActivity.this, commentSunList, R.layout.item_study_sun_task) {
                            @Override
                            public void convert(RecyclerViewHolder helper, StudyComment item, int position) {
                                helper.setText(R.id.tv_name, item.getMemberName());
                                helper.setText(R.id.tv_context, item.getContent());
                            }
                        };
                        recyclerViewSun.setHasFixedSize(true);
                        recyclerViewSun.setNestedScrollingEnabled(false);
                        recyclerViewSun.setLayoutManager(new GridLayoutManager(StudyDetailActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                        recyclerViewSun.setAdapter(commentAdapterSun);

                    }

                    @Override
                    public void onBindViewHolder(RecyclerViewHolder holder, int position, boolean isItem) {
                        super.onBindViewHolder(holder, position, isItem);
                    }
                };
                mRecyclerView.setHasFixedSize(true);
                mRecyclerView.setNestedScrollingEnabled(false);
                mRecyclerView.setLayoutManager(new GridLayoutManager(StudyDetailActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                mRecyclerView.setAdapter(commentAdapter);
            }


            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    @OnClick({R.id.tv_comment_write, R.id.iv_comment, R.id.iv_star, R.id.iv_collection, R.id.iamgeleft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_comment_write:
                if (mCommentDialog == null) {
                    mCommentDialog = new CommentDialog(this, R.style.dialog_translucent);
//                    commentDialog.setOnCommentLisenter(this);
                    mCommentDialog.setCommentSendListener(StudyDetailActivity.this);
                    mCommentDialog.setBackLisenter(StudyDetailActivity.this);
                }
                mCommentDialog.show();

                break;
            case R.id.iv_comment:
                Intent intent = new Intent(this, StudyCommentDetalActivity.class);
                intent.putExtra(Constants.STUDY_DASK_ID, studyId);
                startActivity(intent);

                break;
            case R.id.iv_star:
//                showToas("点赞");
                addSubscription(apiStores.addStudyTaskLike(studyId, PrefUtils.getString(this, Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
                    @Override
                    public void onSuccess(RegisteredEn model) {
                        if (TextUtils.equals(model.getCode(), "200")) {
                            mTvStar.setText(model.getData().getLikeCount() + "");
                            showToas(model.getData().getMsg());
                            if (TextUtils.equals(model.getData().getMsg(), "点赞成功")) {
                                starSize += 1;
                                mTvStar.setText(model.getData().getLikeCount() + "");
                                mIvStar.setImageResource(R.mipmap.study_star_on);
//                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_on_icon);
//                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                                tvStar.setCompoundDrawables(drawable, null, null, null);
                            } else {
                                starSize -= 1;
                                mTvStar.setText(model.getData().getLikeCount() + "");
                                mIvStar.setImageResource(R.mipmap.study_star);
//                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_icon);
//                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                                tvStar.setCompoundDrawables(drawable, null, null, null);
                            }

                        }

                    }

                    @Override
                    public void onFailure(String code) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
                break;
            case R.id.iv_collection:
//                showToas("收藏");

                //如果已经收藏
//                if (collectionOn) {

//                    showToas("您已收藏");
//                    addSubscription(apiStores.delCollect(studyId), new ApiCallback<RegisteredEn>() {
//                        @Override
//                        public void onSuccess(RegisteredEn model) {
//                            if (TextUtils.equals(model.getCode(), "200")) {
//                                showToas(model.getMessage());
//                                if (TextUtils.equals(model.getMessage(), "成功")) {
//                                    collectionOn = false;
//                                    mIvCollection.setImageResource(R.mipmap.study_collection);
////                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_on_icon);
////                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
////                                tvStar.setCompoundDrawables(drawable, null, null, null);
//                                } else {
//                                    collectionOn = true;
//                                    mIvCollection.setImageResource(R.mipmap.study_collection_on);
////                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_icon);
////                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
////                                tvStar.setCompoundDrawables(drawable, null, null, null);
//                                }
//                            }
//
//                        }
//
//                        @Override
//                        public void onFailure(String code) {
//                            //返回999为已经收藏
//                            if (TextUtils.equals(code, "999")) {
//                                showToas("您已收藏");
//                            }
//                        }
//
//                        @Override
//                        public void onFinish() {
//
//                        }
//                    });

//                } else {
                addSubscription(apiStores.addCollect(PrefUtils.getString(this, Constants.USER_ID, ""), "0", studyId), new ApiCallback<StudyCollection>() {
                    @Override
                    public void onSuccess(StudyCollection model) {
                        if (TextUtils.equals(model.getCode(), "200")) {
                            if (model.getData() == 817) {
                                collectionOn = true;
                                mIvCollection.setImageResource(R.mipmap.study_collection_on);
                            } else if (model.getData() == 818) {
                                collectionOn = false;
                                mIvCollection.setImageResource(R.mipmap.study_collection);
                            }
                            showToas(model.getMessage());


//                                showToas("收藏成功");
//                                if (TextUtils.equals(model.getMessage(), "成功")) {
//                                    collectionOn = true;
//                                    mIvCollection.setImageResource(R.mipmap.study_collection_on);
////                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_on_icon);
////                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
////                                tvStar.setCompoundDrawables(drawable, null, null, null);
//                                } else {
//                                    collectionOn = false;
//                                    mIvCollection.setImageResource(R.mipmap.study_collection);
////                                Drawable drawable = getResources().getDrawable(R.mipmap.study_star_icon);
////                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
////                                tvStar.setCompoundDrawables(drawable, null, null, null);
//                                }
                        }

                    }

                    @Override
                    public void onFailure(String code) {
                        //返回999为已经收藏
                        if (TextUtils.equals(code, "999")) {
                            showToas("您已收藏");
                            collectionOn = true;
                            mIvCollection.setImageResource(R.mipmap.study_collection_on);
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                });

//                }

                break;
            case R.id.iamgeleft:
                finish();
                break;
        }
    }

    @Override
    public void back() {
         /*隐藏软键盘*/
        InputMethodManager imm = (InputMethodManager) mTvCommentWrite.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(mTvCommentWrite.getApplicationWindowToken(), 0);
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
    public void sendOk(String comment) {
        addSubscription(apiStores.addStudyTaskComment(studyId, PrefUtils.getString(this, Constants.USER_ID, ""), comment), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                if (TextUtils.equals(model.getCode(), "200")) {
                    int commentSize = Integer.parseInt(mTvCommentSize.getText().toString()) + 1;
                    mTvCommentSize.setText(commentSize + "");
                    showToas("发表成功");
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

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {//点击的是返回键
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {//按键的按下事件
//               return false;
            } else if (event.getAction() == KeyEvent.ACTION_UP && event.getRepeatCount() == 0) {//按键的抬起事件

                Intent intent = new Intent();
                intent.putExtra("star_size", starSize);
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
                setResult(1, intent);
//                finish();
//               return false;
            }
        }
        return super.dispatchKeyEvent(event);
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

    @Override
    protected void onStop() {
        Intent intent = new Intent();
        intent.setAction("com.gasFragment"); // 设置你这个广播的action
        intent.putExtra("id",studyId);
        intent.putExtra("starsize",mTvStar.getText());
        intent.putExtra("looksize",mTvLook.getText());
        sendBroadcast(intent);
        super.onStop();
    }
}
