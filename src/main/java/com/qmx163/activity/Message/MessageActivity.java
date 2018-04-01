package com.qmx163.activity.Message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.MessageEn;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  create by likai on 17-8-31
 *  消息页面
 */
public class MessageActivity extends BaseAc {

    @BindView(R.id.iamgeleft)
    ImageView iamgeleft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
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
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerAdapter energyAdapter;
    List<MessageEn> Melist = new ArrayList<>();
    public static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
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


        getData();
//        }
        //设置进度条的颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                Toast.makeText(getActivity(),"onRefresh there",Toast.LENGTH_SHORT).show();
//                swipeRefreshLayout.setRefreshing(false);
//                Melist.clear();
                getData();
            }
        });
    }

    private void getData() {
        //b368c9f05ba711e7905400163e323696  测试id
        addSubscription(apiStores.getMessage(PrefUtils.getString(this, Constants.USER_ID,""), 1),
                new ApiCallback<GetMessageEn>() {
                    @Override
                    public void onSuccess(GetMessageEn model) {
                        if ("200".equals(model.getCode())) {
                            GetMessageEn.DataBean dataBean = model.getData();
                            Melist = dataBean.getList();
                            if (Melist.size() <= 0) {
//                                showToas("没有数据");
                            } else {
                                if (energyAdapter == null) {
                                    bindRecyclerView();
                                } else {
                                    energyAdapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            showToas(model.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(String code) {
                        if (swipeRefreshLayout != null) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFinish() {
                        if (swipeRefreshLayout != null) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            getData();
        }
    }

    /**
     * 初始化recyclerview
     */
    private void bindRecyclerView() {
        energyAdapter = new RecyclerAdapter<MessageEn>(this, Melist, R.layout.messagerecycle) {
            @Override
            public void convert(RecyclerViewHolder helper, MessageEn item, int position) {
                ImageView imageView = helper.getView(R.id.imgIc);
                TextView type = helper.getView(R.id.type);
                TextView title = helper.getView(R.id.title);
                TextView messageRead = helper.getView(R.id.tv_message_read);
                //显示缩略图
                Glide.with(MessageActivity.this).load(
                        item.getImg()).dontAnimate().centerCrop().error(R.mipmap.xiaoxi).crossFade().into((ImageView) imageView);
                helper.setText(R.id.tv_time, item.getAddTime());

                //消息是否已读
                if (item.getStatus()== 0){
                    messageRead.setVisibility(View.VISIBLE);
                }else {
                    messageRead.setVisibility(View.GONE);
                }
                title.setText("【" + item.getTitle() + "】" + "点击跳转...");
                switch (item.getType()) {
                    case 0://通知
                        type.setText("系统消息");
                        break;
                    case 1://提问
                        type.setText("提问");
                        break;
                    case 2://提问回复
                        type.setText("提问回复");
                        break;
                    case 3://提问发布
                        type.setText("提问发布");
                        break;
                }
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.energyAdapter);
        energyAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                Intent intent = new Intent();
                intent.setClass(MessageActivity.this, MsgListActivity.class);
                Bundle bundle = new Bundle();
                /*bundle.putInt("type", Melist.get(position).getType());
                bundle.putSerializable("user", Melist.get(position));*/
                bundle.putString("bizId",Melist.get(position).getBizId());
                bundle.putInt("type", Melist.get(position).getType());
                intent.putExtras(bundle);
                startActivityForResult(intent,REQUEST_CODE);
//                startAc(MsgListActivity.class);
            }
        });
    }
}
