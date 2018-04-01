package com.qmx163.activity.Message;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.JPushMessageDetail;
import com.qmx163.data.bean.Mebean.JpushMessage;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qmx163.R.id.timer;

/**
 * 消息详情
 */
public class MsgListActivity extends BaseAc {

    @BindView(R.id.iamgeleft)
    ImageView iamgeleft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_bg_up)
    TextView mTvUp;
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
//    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerAdapter energyAdapter;
    JPushMessageDetail.DataBean mMessage;
    int messageType;
    String bizId;
    List<JPushMessageDetail.DataBean> Melist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_list);
        ButterKnife.bind(this);

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

        Intent intent = this.getIntent();
        bizId = intent.getStringExtra("bizId");
        messageType = intent.getIntExtra("type",-1);
        ibtnLeft.setVisibility(View.VISIBLE);
        iamgeleft.setImageDrawable(getResources().getDrawable(R.mipmap.back));
        switch (messageType){
            case 0://通知
                tvTitle.setText("系统消息");
                getMessageData();
                break;
            case 1://提问
                tvTitle.setText("提问");
                break;
            case 2://提问回复
                tvTitle.setText("提问回复");
                getMessageData();
                break;
            case 3://提问发布
                tvTitle.setText("提问发布");
                getMessageData();
                break;
        }

    }

    private void getMessage(String bizId) {
        addSubscription(apiStores.getMessageDetail(bizId), new ApiCallback<JpushMessage>() {
            @Override
            public void onSuccess(JpushMessage model) {
                if ("200".equals(model.getCode())) {

                  JpushMessage.DataBean  mJpushMessage = model.getData();
                    List<JpushMessage.DataBean> jPushMelist= new ArrayList<>();
                    jPushMelist.add(mJpushMessage);

                    energyAdapter = new RecyclerAdapter<JpushMessage.DataBean>(MsgListActivity.this, jPushMelist, R.layout.msg_list_adpter) {
                        @Override
                        public void convert(RecyclerViewHolder helper, final JpushMessage.DataBean item, int position) {
                            ImageView imageView = helper.getView(R.id.imgIc);
                            LinearLayout llCheck = helper.getView(R.id.ll_check);
                            llCheck.setVisibility(View.GONE);
                            //显示缩略图
                            Glide.with(MsgListActivity.this).load(
                                    item.getImg()).dontAnimate().centerCrop().error(R.mipmap.xiaoxi).crossFade().into((ImageView) imageView);
                            helper.setText(timer, item.getAddTime());
                            helper.setText(R.id.tv_content,item.getContent());
                        }
                    };
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(MsgListActivity.this, 1, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(energyAdapter);

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

    private void getMessageData() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("rq", "/app/getJPushMessageDetail");
        params.put("bizId", bizId);
        params.put("bizType", String.valueOf(messageType));
        params.put("memberId", PrefUtils.getString(MsgListActivity.this,Constants.USER_ID,""));
        addSubscription(apiStores.getJPushMessageDetail(params), new ApiCallback<JPushMessageDetail>() {
            @Override
            public void onSuccess(JPushMessageDetail model) {
                if ("200".equals(model.getCode())) {

                    mMessage = model.getData();
                    Melist = new ArrayList<>();
                    Melist.add(mMessage);
                    bindRecyclerView();
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


    /**
     * 初始化recyclerview
     */
    private void bindRecyclerView() {
        energyAdapter = new RecyclerAdapter<JPushMessageDetail.DataBean>(this, Melist, R.layout.msg_list_adpter) {
            @Override
            public void convert(RecyclerViewHolder helper, final JPushMessageDetail.DataBean item, final int position) {
                ImageView imageView = helper.getView(R.id.imgIc);
                LinearLayout llCheck = helper.getView(R.id.ll_check);
                com.rey.material.widget.RelativeLayout rlMingbai = helper.getView(R.id.rl_question_mingbai);
                com.rey.material.widget.RelativeLayout rlSoso = helper.getView(R.id.rl_question_soso);
                com.rey.material.widget.RelativeLayout rlInnocent = helper.getView(R.id.rl_question_innocent);

                if (messageType == 2){
                    llCheck.setVisibility(View.VISIBLE);
                    helper.setText(R.id.huifu, item.getUserName());
                    helper.setText(R.id.huifuzhi, item.getAnswerContent());

                    try {
                        //判断提问状态
                        if (item.getQuestionScore()!=null){

                            if (TextUtils.equals("0",item.getQuestionScore())){
                                rlInnocent.setVisibility(View.INVISIBLE);
                                rlSoso.setVisibility(View.INVISIBLE);
                                rlMingbai.setEnabled(false);
                            }else if (TextUtils.equals("1",item.getQuestionScore())){
                                rlInnocent.setVisibility(View.INVISIBLE);
                                rlMingbai.setVisibility(View.INVISIBLE);
                                rlSoso.setEnabled(false);
                            }else if (TextUtils.equals("2",item.getQuestionScore())){
                                rlMingbai.setVisibility(View.INVISIBLE);
                                rlSoso.setVisibility(View.INVISIBLE);
                                rlInnocent.setEnabled(false);
                            }else {
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }else {
                    llCheck.setVisibility(View.GONE);
                }
                //显示缩略图
                Glide.with(MsgListActivity.this).load(
                        item.getMemberImg()).dontAnimate().centerCrop().error(R.mipmap.xiaoxi).crossFade().into((ImageView) imageView);
                helper.setText(timer, item.getAddTime());
                helper.setText(R.id.tv_content,item.getContent());

                helper.getView(R.id.rl_question_mingbai).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addQuestionSame(item.getId(), "0",position);
                    }
                });
                helper.getView(R.id.rl_question_soso).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addQuestionSame(item.getId(), "1",position);
                    }
                });
                helper.getView(R.id.rl_question_innocent).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addQuestionSame(item.getId(), "2",position);
                    }
                });
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.energyAdapter);
    }
    //明白，一般， 不懂
    private void addQuestionSame(String id, final String score, final int position) {
        showProgressDialog();
        String userId = PrefUtils.getString(MsgListActivity.this, Constants.USER_ID, "");
        addSubscription(apiStores.addLessonQuestionScore(id, userId, score), new ApiCallback<SocketMessage>() {
            @Override
            public void onSuccess(SocketMessage model) {

                Melist.get(position).setQuestionScore(score);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        energyAdapter.notifyDataSetChanged();

                    }
                },0);
                showToas(model.getMessage());
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
}
