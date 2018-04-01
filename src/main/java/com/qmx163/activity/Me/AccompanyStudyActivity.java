package com.qmx163.activity.Me;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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

import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.application.MyApplication;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.AccompaniesEn;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.data.bean.Mebean.User;
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
 * Created by likai on 2017/7/4.
 * 陪伴学习界面
 */

public class AccompanyStudyActivity extends BaseAc implements View.OnClickListener {

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
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    public static final int REQUEST_CODE = 0;
    @BindView(R.id.rv_study)
    RecyclerView mRvStudy;
    @BindView(R.id.ll_is_empty)
    LinearLayout mLlIsEmpty;
    //    @BindView(R.id.swipe)
//    SwipeRefreshLayout mSwipe;
    RefreshLayout refreshLayout;

    @BindView(R.id.rl_context)
    RelativeLayout mRlContext;
    private RecyclerAdapter studyAdapter;
    private List<String> studyList = new ArrayList<>();

    private List<User> accompanyUsers;
    int selectposition = -1;
    int pageNum = 1;
    private boolean isLoadmore = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accompany_study);
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
        mTvTitle.setText(R.string.me_study);
        mTvRight.setText("添加");
        mTvRight.setTextColor(Color.BLACK);
        mRightImg.setVisibility(View.GONE);
        mIvAdd.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
//        getDate();


        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        refreshing();

//        //下拉刷新监听
//        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                pageNum = 1;
//                studyList.clear();
//                getDate();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pageNum = 1;
        getDate();
    }

    /**
     * 填充recyclerview
     */
    private void bindRecyclerView() {
        this.studyAdapter = new RecyclerAdapter<User>(this, accompanyUsers, R.layout.item_accompany_study) {
            @Override
            public void convert(final RecyclerViewHolder helper, final User item, final int position) {
                selectposition = -1;
                TextView tvStatus = helper.getView(R.id.tv_study_status);
                final TextView delete = helper.getView(R.id.delete);
                RelativeLayout rlItem = helper.getView(R.id.rl_item);
                final TextView tv_star_point = helper.getView(R.id.tv_star_point);
                HorizontalScrollView scrollView = helper.getView(R.id.scroll_view);
                helper.setText(R.id.tv_name, item.getMemberName());
                helper.setImageUrl(AccompanyStudyActivity.this, R.id.civ_head, item.getImg());
                helper.setText(R.id.tv_energy_size, "累计获得" + item.getScore() + "个");
                helper.setText(R.id.tv_lesson_count, item.getStudyLessonCount() + "节");
                helper.setText(R.id.tv_question_count, "累计提问" + item.getQuestionCount() + "个");
                helper.setText(R.id.tv_study_time, "累计学习" + item.getStudyDurationTime() + "小时");

                //设置item主体为填充父窗体
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(MyApplication.getInstance().getmWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
                rlItem.setLayoutParams(lp);

                if (item.getLessonPeriodsStatus() == 0) {
                    tvStatus.setBackgroundResource(R.drawable.shape_study_sleep);
                    tvStatus.setText("休息");
                }else {
                    tvStatus.setBackgroundResource(R.drawable.shape_study_start);
                    tvStatus.setText("正在学习");
                }

                //家长点赞
                helper.getView(R.id.iv_star).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        addSubscription(apiStores.addEncourage(PrefUtils.getString(AccompanyStudyActivity.this, Constants.USER_ID, ""), item.getMemberId()), new ApiCallback<SocketMessage>() {
                            @Override
                            public void onSuccess(SocketMessage model) {
                                if (model.getCode().equals("200")) {
                                    tv_star_point.setVisibility(View.VISIBLE);
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv_star_point.setVisibility(View.INVISIBLE);
                                        }
                                    }, 3000);
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
                });


                //删除陪伴学习
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        addSubscription(apiStores.DeleteAccompany(item.getId(), PrefUtils.getString(AccompanyStudyActivity.this, Constants.USER_ID, "")), new ApiCallback<SocketMessage>() {
                            @Override
                            public void onSuccess(SocketMessage model) {
                                if (model.getCode().equals("200")) {
                                    showToas("删除成功");
                                    pageNum = 1;
                                    getDate();
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

        mRvStudy.setHasFixedSize(true);
        mRvStudy.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        mRvStudy.setAdapter(studyAdapter);
    }

    /**
     * 获取列表数据
     */
    private void getDate() {

        addSubscription(apiStores.Accompanies(PrefUtils.getString(this, Constants.USER_ID, ""), pageNum + ""), new ApiCallback<AccompaniesEn>() {
            @Override
            public void onSuccess(AccompaniesEn model) {
                if (TextUtils.equals(model.getCode(), "200")) {
                    isLoadmore = model.getData().isHasNextPage();
                    AccompaniesEn.DataBean userlist = model.getData();
                    if (pageNum == 1) {
                        accompanyUsers = userlist.getList();
                        if (accompanyUsers.size() == 0) {
                            mTvRight.setVisibility(View.GONE);
                            mRlContext.setVisibility(View.GONE);
                            mLlIsEmpty.setVisibility(View.VISIBLE);
                        } else {
                            mRlContext.setVisibility(View.VISIBLE);
                            mTvRight.setVisibility(View.VISIBLE);
                            mLlIsEmpty.setVisibility(View.GONE);
                            bindRecyclerView();
                        }
                    }else {
                        mRlContext.setVisibility(View.VISIBLE);
                        mTvRight.setVisibility(View.VISIBLE);
                        mLlIsEmpty.setVisibility(View.GONE);
                        accompanyUsers.addAll(userlist.getList());
                        studyAdapter.notifyDataSetChanged();
                    }
                    if (isLoadmore){
                        refreshLayout.setLoadmoreFinished(false);
                    }else {
                        refreshLayout.setLoadmoreFinished(true);
                    }
                }
            }

            @Override
            public void onFailure(String code) {
//                if (mSwipe != null) {
//                    mSwipe.setRefreshing(false);
//                }
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                requestPermission(1, Manifest.permission.CAMERA, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AccompanyStudyActivity.this, CaptureActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent, REQUEST_CODE);

                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        showToas(getResources().getString(R.string.no_permission_to_use_the_camera));
                    }
                });
                break;
            case R.id.tv_right:
                requestPermission(1, Manifest.permission.CAMERA, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AccompanyStudyActivity.this, CaptureActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent, REQUEST_CODE);

                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        showToas(getResources().getString(R.string.no_permission_to_use_the_camera));
                    }
                });
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
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
                studyList.clear();
                getDate();
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
                        if (isLoadmore) {
                                pageNum++;
                                getDate();
                        } else {
//                            Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                            refreshlayout.finishLoadmore();
                            refreshlayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                        }
                    }
                }, 1200);
            }
        });
    }
}
