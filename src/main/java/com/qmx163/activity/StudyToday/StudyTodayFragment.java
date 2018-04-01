package com.qmx163.activity.StudyToday;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.activity.MainActivity;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.StudySearchEn;
import com.qmx163.listener.SwipeLoadmoreListener;
import com.qmx163.listener.SwipeRefreshListener;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 今日学习子fragment
 * Created by 李凯 on 2017/8/28.
 */

public class StudyTodayFragment extends BaseFm implements SwipeRefreshListener, SwipeLoadmoreListener {

    List<StudySearchEn.DataBean.ListBean> mStudyList = new ArrayList<>();
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    Unbinder unbinder;
    private RecyclerAdapter mRecyclerAdapter;
    private RecyclerView mRecyclerView;
    private String type = "0";
    private int pageNum = 1;
    public static final int REQUEST_CODE = 0;
    private int pressPosition = -1;
    private boolean loadMore = true;

    public static StudyTodayFragment newInstance(String type) {
        StudyTodayFragment studyTodayFragment = new StudyTodayFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
//        bundle.putStringArrayList("list", mStringList);
        studyTodayFragment.setArguments(bundle);
        return studyTodayFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ReceiveBroadCast receiveBroadCast;
    @Override
    public void onAttach(Activity activity) {

        /** 注册广播 */
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.gasFragment");    //只有持有相同的action的接受者才能接收此广播
        activity.registerReceiver(receiveBroadCast, filter);
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.study_today_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);
        type = getArguments().getString("type");
        MainActivity.StudyTodayMainFrag.setSwipeRefreshListener(this);
        MainActivity.StudyTodayMainFrag.setSwipeLoadmoreListener(this);
//        mStringList = getArguments().getStringArrayList("list");


//        StudyTodayFrag.mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                pageNum = 1;
//                initData();
//            }
//        });

        initData();

    }

    /**
     * 获取列表数据
     */
    private void initData() {
        addSubscription(apiStores.searchStudyTasks(type, PrefUtils.getString(getActivity(), Constants.USER_ID, ""), "", pageNum), new ApiCallback<StudySearchEn>() {
            @Override
            public void onSuccess(StudySearchEn model) {
                if (TextUtils.equals(model.getCode(), "200")) {
                    loadMore = model.getData().isHasNextPage();
                    if (pageNum == 1) {
                        mStudyList.clear();
                        mStudyList = model.getData().getList();
                        if (mRecyclerAdapter == null) {
                            BindRecycler(mStudyList);

                        } else {
                            BindRecycler(mStudyList);

                        }
                    } else {
                        mStudyList.addAll(model.getData().getList());
                        mRecyclerAdapter.notifyDataSetChanged();
                    }

                    if (mStudyList.size() == 0) {
                        llEmpty.setVisibility(View.VISIBLE);
                        ivEmpty.setImageResource(R.mipmap.empty_bg);
                        tvEmpty.setText("数据为空");

                    } else {
                        llEmpty.setVisibility(View.GONE);
                    }
                    if (loadMore) {
                        StudyTodayMainFrag.refreshLayout.setLoadmoreFinished(false);//将不会再次触发加载更多事件
                    } else {
                        StudyTodayMainFrag.refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                    }
                }

            }

            @Override
            public void onFailure(String code) {
//                if (StudyTodayFrag.mSwipe!=null){
//                    StudyTodayFrag.mSwipe.setRefreshing(false);
//                }

                if (code.equals("-666") || mStudyList.size() == 0) {
                    llEmpty.setVisibility(View.VISIBLE);
                    ivEmpty.setImageResource(R.mipmap.no_intelnet);
                    tvEmpty.setText("网络错误");
                } else {
                    llEmpty.setVisibility(View.GONE);
                }
                if (StudyTodayMainFrag.refreshLayout != null) {
                    StudyTodayMainFrag.refreshLayout.finishRefresh();
                    StudyTodayMainFrag.refreshLayout.finishLoadmore();
                    StudyTodayMainFrag.refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
                }
            }

            @Override
            public void onFinish() {
//                         if (StudyTodayFrag.mSwipe!=null){
//                StudyTodayFrag.mSwipe.setRefreshing(false);
//                         }

                if (StudyTodayMainFrag.refreshLayout != null) {
                    StudyTodayMainFrag.refreshLayout.finishRefresh();
                    StudyTodayMainFrag.refreshLayout.finishLoadmore();
                }
            }
        });
    }

    private void BindRecycler(final List<StudySearchEn.DataBean.ListBean> mStudyList) {
        mRecyclerAdapter = new RecyclerAdapter<StudySearchEn.DataBean.ListBean>(getActivity(), mStudyList, R.layout.item_study_today) {
            @Override
            public void convert(RecyclerViewHolder helper, final StudySearchEn.DataBean.ListBean item, int position) {
                final TextView tvStar = helper.getView(R.id.tv_star);
                ImageView ivImg = helper.getView(R.id.iv_img);
                helper.setText(R.id.tv_title, item.getTitle());
                helper.setText(R.id.tv_time, item.getIssueDay());
                helper.setText(R.id.tv_look, item.getWatch() + "");
                helper.setText(R.id.tv_star, item.getLikesCount() + "");
//                if (item.isLike()) {
//                    Drawable drawable = getResources().getDrawable(R.mipmap.study_star_on_icon);
//                    /// 这一步必须要做,否则不会显示.
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    tvStar.setCompoundDrawables(drawable, null, null, null);
//                }
                Glide.with(getActivity()).load(
                        item.getImg()).dontAnimate().centerCrop().error(R.mipmap.logo).crossFade().into(ivImg);


                //取消点赞按钮
//                tvStar.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        addSubscription(apiStores.addStudyTaskLike(item.getId(), PrefUtils.getString(getActivity(), Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
//                            @Override
//                            public void onSuccess(RegisteredEn model) {
//                                if (TextUtils.equals(model.getCode(), "200")) {
//                                    tvStar.setText(model.getData().getLikeCount() + "");
//                                    showToas(model.getData().getMsg());
//                                    if (TextUtils.equals(model.getData().getMsg(), "点赞成功")) {
//                                        Drawable drawable = getResources().getDrawable(R.mipmap.study_star_on_icon);
//                                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                                        tvStar.setCompoundDrawables(drawable, null, null, null);
//                                    } else {
//                                        Drawable drawable = getResources().getDrawable(R.mipmap.study_star_icon);
//                                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                                        tvStar.setCompoundDrawables(drawable, null, null, null);
//                                    }
//                                }
//
//                            }
//
//                            @Override
//                            public void onFailure(String code) {
//
//                            }
//
//                            @Override
//                            public void onFinish() {
//                            }
//                        });
//
//                    }
//                });
            }
        };
        mRecyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                pressPosition = position;
                mStudyList.get(position).setWatch(mStudyList.get(position).getWatch() + 1);
                Intent intent = new Intent(getActivity(), StudyDetailActivity.class);
                intent.putExtra(Constants.STUDY_DASK_ID, mStudyList.get(position).getId());
                intent.putExtra(Constants.STUDY_CONTENT, mStudyList.get(position).getContent());
                intent.putExtra(Constants.STUDY_TITLE, mStudyList.get(position).getTitle());
                intent.putExtra(Constants.STUDY_COMMENT_COUNT, mStudyList.get(position).getCommentsCount() + "");
                startActivityForResult(intent, REQUEST_CODE);
//                startAc(intent);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == 1) {
//                String k = data.getIntExtra("star_size",0)+"";
                mStudyList.get(pressPosition).setLikesCount(mStudyList.get(pressPosition).getLikesCount() + data.getIntExtra("star_size", 0));
            }

            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refresh(int type) {
        if (type == 0) {
            pageNum = 1;
            initData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        initData();
    }

    @Override
    public void loadMore(int type) {

        if (type == 0) {

            if (loadMore) {
                pageNum++;
                initData();
            } else {
//                            Toast.makeText(getApplication(), "数据全部加载完毕", Toast.LENGTH_SHORT).show();
                StudyTodayMainFrag.refreshLayout.finishLoadmore();
                StudyTodayMainFrag.refreshLayout.setLoadmoreFinished(true);//将不会再次触发加载更多事件
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(receiveBroadCast);
        unbinder.unbind();
    }

    class ReceiveBroadCast extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            //得到广播中得到的数据，并显示出来
            String id = intent.getExtras().getString("id");
            String starSize = intent.getExtras().getString("starsize");
            String lookSize = intent.getExtras().getString("looksize");
            for (int i = 0; i < mStudyList.size(); i++) {
                if (mStudyList.get(i).getId().equals(id)){
                    mStudyList.get(i).setLikesCount(Integer.parseInt(starSize));
                    mStudyList.get(i).setWatch(Integer.parseInt(lookSize));
                }

            }
//            String address = intent.getExtras().getString("address");

//            gasadderss.setText("地址：\n  "+address);
//            gasName.setText(gasname);
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }
}

