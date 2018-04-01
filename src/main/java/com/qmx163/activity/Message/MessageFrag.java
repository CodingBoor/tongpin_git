package com.qmx163.activity.Message;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmx163.R;
import com.qmx163.activity.watch.WatchActivity;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.MessageEn;
import com.qmx163.data.bean.Mebean.WatchPlayBackEn;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.listener.MessageChangeListener;
import com.qmx163.net.ApiCallback;
import com.qmx163.service.JpushReceive;
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
import butterknife.Unbinder;

import static com.qmx163.application.MyApplication.param;

/**
 * 消息
 * Created by Administrator on 2017/6/13.
 */

public class MessageFrag extends BaseFm {
    Unbinder unbinder;
    @BindView(R.id.iamgeleft)
    ImageView iamgeleft;
    @BindView(R.id.tv_bg_up)
    TextView mTvUp;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.ibtn_left)
    RelativeLayout ibtnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.Right_img)
    ImageView RightImg;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rl_title)
    RelativeLayout rlTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    //    @BindView(R.id.loading)
//    LoadingLayout loading;
    //    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout swipeRefreshLayout;
    RefreshLayout refreshLayout;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private boolean loadMore = true;


    private RecyclerAdapter energyAdapter;
    List<MessageEn> Melist = new ArrayList<>();
    public static final int REQUEST_CODE = 0;
    private int pageNum = 1;
    MessageChangeListener mMessageChangeListener; //消息条数监听

    public void setMessageChangeListener(MessageChangeListener messageChangeListener) {
        mMessageChangeListener = messageChangeListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_frag, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvTitle.setText(R.string.message_title);

//设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) mTvUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mTvUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }



        refreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        refreshing();
//        loading = (LoadingLayout) view.findViewById(loading);
//loading = LoadingLayout.wrap(getActivity());
//        loading.showEmpty();

        if (PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "").equals("") || PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "") == null) {
//            startAc(LoginActivity.class);
//            getActivity().finish();
        } else {
//        Melist = new ArrayList<>();
//        for (int i = 0; i <= 10; i++) {
//            MessageEn me = new MessageEn();
//            me.setIco("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E4%BA%9A%E7%B4%A2%E5%9B%BE%E7%89%87&step_word=&hs=2&pn=19&spn=0&di=79181568620&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=3732755174%2C2196224101&os=3472422593%2C2984718177&simid=3380772617%2C160767245&adpicid=0&lpn=0&ln=1907&fr=&fmq=1499053628770_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fuploads.xuexila.com%2Fallimg%2F1612%2F907-161215120936-50.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bx7jxtsw_z%26e3Bv54AzdH3Fi7wAzdH3F4wgi7wAzdH3Fd889man_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0");
//            me.setTime("2017/7/03  12:45:11");
//            me.setTitle("标题:" + i);
//            me.setWenben("文本:" + i);
//            Melist.add(me);
//        bindRecyclerView();
            getData();
//        }
            //设置进度条的颜色
//            swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
//            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
////                Toast.makeText(getActivity(),"onRefresh there",Toast.LENGTH_SHORT).show();
////                swipeRefreshLayout.setRefreshing(false);
////                Melist.clear();
//                    pageNum = 1;
//                    getData();
//                }
//            });

        }


        return view;
    }

    private void getData() {
        //b368c9f05ba711e7905400163e323696  测试id
        addSubscription(apiStores.getMessage(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), pageNum),
                new ApiCallback<GetMessageEn>() {
                    @Override
                    public void onSuccess(GetMessageEn model) {
//                        boolean hasMessage = false;//是否有未读消息
                        if ("200".equals(model.getCode())) {
//                            loading.showContent();
                            loadMore = model.getData().isHasNextPage();
                            GetMessageEn.DataBean dataBean = model.getData();

                            if (pageNum == 1) {
                                boolean hasMessage = false;//是否有未读消息
                                Melist.clear();

                                Melist = dataBean.getList();
                                for (int i = 0; i < Melist.size(); i++) {
                                    if (Melist.get(i).getStatus() == 0) {
                                        hasMessage = true;
                                    }
                                    if (i == Melist.size() - 1) {
                                        if (!hasMessage) {
                                            mMessageChangeListener.setMessageSiae("0");
                                        } else {
                                            mMessageChangeListener.setMessageSiae("1");
                                        }
                                    }
                                }

                                if (Melist.size() <= 0) {
//                                showToas("没有数据");
                                } else {
                                    bindRecyclerView();
//                                if (energyAdapter == null) {
//                                } else {
//                                    energyAdapter.notifyDataSetChanged();
//                                }
                                }
                            } else {
                                Melist.addAll(dataBean.getList());
                                energyAdapter.notifyDataSetChanged();

                            }

//                            if (pageNum == 1) {
//                                Melist.clear();
//                            }
//                            Melist = dataBean.getList();
//                            for (int i = 0; i < Melist.size(); i++) {
//                                if (Melist.get(i).getStatus() == 0) {
//                                    hasMessage = true;
//                                }
//                                if (i == Melist.size() - 1) {
//                                    if (!hasMessage) {
//                                        mMessageChangeListener.setMessageSiae("0");
//                                    } else {
//                                        mMessageChangeListener.setMessageSiae("1");
//                                    }
//                                }
//                            }
//
//                            if (Melist.size() <= 0) {
////                                showToas("没有数据");
//                            } else {
//                                bindRecyclerView();
////                                if (energyAdapter == null) {
////                                } else {
////                                    energyAdapter.notifyDataSetChanged();
////                                }
//                            }


                            if (Melist.size() == 0) {
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
//                        if (swipeRefreshLayout != null) {
//                            swipeRefreshLayout.setRefreshing(false);
//                        }
                        if (code.equals("-666") || Melist.size() == 0) {
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
//                        if (swipeRefreshLayout != null) {
//                            swipeRefreshLayout.setRefreshing(false);
//                        }
                        refreshLayout.finishRefresh();
                        refreshLayout.finishLoadmore();
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            pageNum = 1;
            getData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (JpushReceive.jPushMessage) {
            JpushReceive.jPushMessage = false;
            pageNum = 1;
            getData();
        }
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (!hidden){
//            if (JpushReceive.jPushLive) {
//                JpushReceive.jPushLive = false;
//                pageNum = 1;
//                getData();
//            }else {
//                JpushReceive.jPushLive = false;
//            }
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * 初始化recyclerview
     */
    private void bindRecyclerView() {
        energyAdapter = new RecyclerAdapter<MessageEn>(getActivity(), Melist, R.layout.messagerecycle) {
            @Override
            public void convert(RecyclerViewHolder helper, MessageEn item, int position) {
                ImageView imageView = helper.getView(R.id.imgIc);
                TextView type = helper.getView(R.id.type);
                TextView title = helper.getView(R.id.title);
                TextView tvTime = helper.getView(R.id.tv_time);
                TextView messageRead = helper.getView(R.id.tv_message_read);
                //显示缩略图
                Glide.with(getActivity()).load(
                        item.getImg()).dontAnimate().centerCrop().error(R.mipmap.xiaoxi).crossFade().into((ImageView) imageView);
                tvTime.setText(item.getAddTime());

                //消息是否已读
                if (item.getStatus() == 0) {
                    messageRead.setVisibility(View.VISIBLE);
                    tvTime.setTextColor(Color.BLACK);
                    title.setTextColor(Color.BLACK);
                    type.setTextColor(Color.BLACK);
                } else {
                    tvTime.setTextColor(getResources().getColor(R.color.hintbg));
                    title.setTextColor(getResources().getColor(R.color.hintbg));
                    type.setTextColor(getResources().getColor(R.color.bg));
                    messageRead.setVisibility(View.GONE);
                }
                title.setText("【" + item.getTitle() + "】" + "点击跳转...");
//                type;// 类别 0通知 1直播 2提问回复 3提问发布 4未观看直播（逃课）
                switch (item.getType()) {
                    case 0://通知
                        type.setText("系统消息");
                        break;
                    case 1://直播
                        type.setText("开播消息");
                        break;
                    case 2://提问
                        type.setText("提问回复");
                        break;
                    case 3://提问回复
                        type.setText("提问发布");
                        break;
                    case 4://提问发布
                        type.setText("未观看直播（逃课）");
                        break;
                }
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.energyAdapter);
        energyAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                if (Melist.get(position).getType() == 1 || Melist.get(position).getType() == 4) {
                    showProgressDialog();
                    setMessageRead(Melist.get(position).getId());
                    getDate(Melist.get(position).getBizId());
                } else {
                    if (Melist.get(position).getStatus() == 0) {
                        setMessageRead(Melist.get(position).getId());
                    }
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), MsgListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("bizId", Melist.get(position).getBizId());
                    bundle.putInt("type", Melist.get(position).getType());
                    //bundle.putSerializable("user", Melist.get(position));
                    intent.putExtras(bundle);
                    startActivityForResult(intent, REQUEST_CODE);
                }

//                startAc(MsgListActivity.class);
            }
        });
    }

    /**
     * 课时详情
     */
    private lessonPeriodsDetail.DataBean lessonDeta;

    private void getDate(String lessonId) {
        addSubscription(apiStores.lessonPeriodsDetail(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonId), new ApiCallback<lessonPeriodsDetail>() {
            @Override
            public void onSuccess(lessonPeriodsDetail model) {
                if ("200".equals(model.getCode())) {
                    lessonDeta = (lessonPeriodsDetail.DataBean) model.getData();
                    param.setKey(PrefUtils.getString(getActivity(), Constants.USER_PASSWORD, ""));
                    param.setUserName(PrefUtils.getString(getActivity(), Constants.USER_NAME, ""));
                    param.setWatchId(model.getData().getWebinarId() + "");
                    param.setUserVhallId(PrefUtils.getString(getActivity(), Constants.USER_TOKEN, ""));


                    //获取回看直播路径
                    if (model.getData().getWebinarRecords() != null) {
                        Gson gson = new Gson();
                        List<WatchPlayBackEn> watchBackList = gson.fromJson(model.getData().getWebinarRecords(), new TypeToken<List<WatchPlayBackEn>>() {
                        }.getType());
                        param.setRecordId(watchBackList.get(0).getId() + "");

                    }

                    Intent intent = new Intent(getActivity(), WatchActivity.class);
                    intent.putExtra("param", param);
                    //status;// 状态 -1删除 1直播 2预告 3结束 4点播 5回放
                    switch (model.getData().getStatus()) {
                        case 1:
                            intent.putExtra("type", VhallUtil.WATCH_LIVE);
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
                            break;
                    }

//                    intent.putExtra("type", VhallUtil.WATCH_LIVE);
                    intent.putExtra("lessonDeta", lessonDeta);
                    startActivityForResult(intent, REQUEST_CODE);
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
                }, 400);
            }
        });
    }


    private void setMessageRead(String id) {
        addSubscription(apiStores.updateMessage(id), new ApiCallback<GetMessageEn>() {
            @Override
            public void onSuccess(GetMessageEn model) {

            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    private void refreshing() {
//        refreshLayout.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setEnableLoadmore(true);
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

}
