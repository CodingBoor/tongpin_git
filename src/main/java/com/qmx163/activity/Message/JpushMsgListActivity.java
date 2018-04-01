package com.qmx163.activity.Message;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.activity.MainActivity;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.JPushMessageDetail;
import com.qmx163.data.bean.Mebean.JpushMessage;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.net.ApiCallback;
import com.qmx163.service.JpushReceive;
import com.qmx163.util.ActivityCollector;
import com.qmx163.util.ArrowTextView;
import com.qmx163.util.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 极光推送消息详情
 */
public class JpushMsgListActivity extends BaseAc {
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
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.timer)
    TextView timer;
    @BindView(R.id.imgIc)
    CircleImageView mImgIc;
    @BindView(R.id.arrowText)
    ArrowTextView mArrowText;
    @BindView(R.id.ll_check)
    LinearLayout mLlCheck;
    @BindView(R.id.huifu)
    TextView mHuifu;
    @BindView(R.id.rl_question_mingbai)
    com.rey.material.widget.RelativeLayout mRlQuestionMingbai;
    @BindView(R.id.rl_question_soso)
    com.rey.material.widget.RelativeLayout mRlQuestionSoso;
    @BindView(R.id.rl_question_innocent)
    com.rey.material.widget.RelativeLayout mRlQuestionInnocent;
    @BindView(R.id.huifuzhi)
    TextView mHuifuzhi;
    //    @BindView(R.id.swipeRefreshLayout)
//    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerAdapter energyAdapter;
    String messageType = "";
    String bizId = "";
    JPushMessageDetail.DataBean questionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jpush_msg_list);
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

        final Intent intent = this.getIntent();
        messageType = intent.getStringExtra("biz_type");
        bizId = intent.getStringExtra("biz_id");
        ibtnLeft.setVisibility(View.VISIBLE);
        iamgeleft.setImageDrawable(getResources().getDrawable(R.mipmap.back));
        switch (messageType) {
            case "0"://通知
                tvTitle.setText("系统消息");
                getMessageData(bizId, messageType);
                break;
            case "1"://提问
                tvTitle.setText("提问");
                getMessage(bizId);
                break;
            case "2"://提问回复
                tvTitle.setText("提问回复");
                getMessageData(bizId, messageType);
                break;
            case "3"://提问发布
                tvTitle.setText("提问发布");
                getMessageData(bizId, messageType);
                break;
        }

        iamgeleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCollector.activities.size() == 1) {
                    Intent intent1 = new Intent(JpushMsgListActivity.this, MainActivity.class);
                    if (JpushReceive.jPushMessage) {
                        intent1.putExtra("jpush_message", true);
                    } else if (JpushReceive.jPushComment) {
                        intent1.putExtra("jpush_comment", true);
                    }
                    startAc(intent1);
                }else {
                    Intent intent1 = new Intent(JpushMsgListActivity.this, MainActivity.class);
//                    if (JpushReceive.jPushMessage) {
//                        intent1.putExtra("jpush_message", true);
//                    } else if (JpushReceive.jPushComment) {
//                        intent1.putExtra("jpush_comment", true);
//                    }
                    startAc(intent1);
                }
                finish();

            }
        });

    }

    private void getMessage(String bizId) {
        addSubscription(apiStores.getMessageDetail(bizId), new ApiCallback<JpushMessage>() {
            @Override
            public void onSuccess(JpushMessage model) {
                timer.setText(model.getData().getAddTime());
                tvContent.setText(model.getData().getContent());
//                setMessageRead(model.getData().getId());

            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });
    }


    private void getMessageData(String bizId, final String type) {
        addSubscription(apiStores.getJPushMessageDetail(bizId, type,PrefUtils.getString(JpushMsgListActivity.this,Constants.USER_ID,"")), new ApiCallback<JPushMessageDetail>() {
            @Override
            public void onSuccess(JPushMessageDetail model) {
                questionData = model.getData();
                timer.setText(model.getData().getAddTime());
                tvContent.setText(model.getData().getContent());
                Glide.with(JpushMsgListActivity.this).load(
                        model.getData().getMemberImg()).dontAnimate().centerCrop().error(R.mipmap.xiaoxi).crossFade().into(mImgIc);

                if (type.equals("2")) {
                    //显示缩略图
//                setMessageRead(model.getData().getId());
                    mHuifu.setText(model.getData().getUserName());
                    mHuifuzhi.setText(model.getData().getAnswerContent());
                    mLlCheck.setVisibility(View.VISIBLE);


                    try {
                        //判断提问状态
                        if (questionData.getQuestionScore()!=null){

                            if (TextUtils.equals("0",questionData.getQuestionScore())){
                                mRlQuestionInnocent.setVisibility(View.INVISIBLE);
                                mRlQuestionSoso.setVisibility(View.INVISIBLE);
                                mRlQuestionMingbai.setEnabled(false);
                            }else if (TextUtils.equals("1",questionData.getQuestionScore())){
                                mRlQuestionInnocent.setVisibility(View.INVISIBLE);
                                mRlQuestionMingbai.setVisibility(View.INVISIBLE);
                                mRlQuestionSoso.setEnabled(false);
                            }else if (TextUtils.equals("2",questionData.getQuestionScore())){
                                mRlQuestionMingbai.setVisibility(View.INVISIBLE);
                                mRlQuestionSoso.setVisibility(View.INVISIBLE);
                                mRlQuestionInnocent.setEnabled(false);
                            }else {
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    mLlCheck.setVisibility(View.GONE);
                }
//                mHuifu.setText(model.getData().getQuestionSameCount());

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
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent1 = new Intent(JpushMsgListActivity.this, LoginActivity.class);
//        if (JpushReceive.jPushMessage){
//            intent1.putExtra("jpush_message",true);
//        }else if (JpushReceive.jPushComment){
//            intent1.putExtra("jpush_comment",true);
//        }
//        startAc(intent1);
//        finish();


        if (ActivityCollector.activities.size() == 1) {
            Intent intent1 = new Intent(JpushMsgListActivity.this, MainActivity.class);
            if (JpushReceive.jPushMessage) {
                intent1.putExtra("jpush_message", true);
            } else if (JpushReceive.jPushComment) {
                intent1.putExtra("jpush_comment", true);
            }
            startAc(intent1);
        }else {
            Intent intent1 = new Intent(JpushMsgListActivity.this, MainActivity.class);
//                    if (JpushReceive.jPushMessage) {
//                        intent1.putExtra("jpush_message", true);
//                    } else if (JpushReceive.jPushComment) {
//                        intent1.putExtra("jpush_comment", true);
//                    }
            startAc(intent1);
        }
        finish();
    }

    /**
     * 掉接口设置消息已读
     * 推送消息无法设置
     */
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


    /**
     * 切换activity时销毁
     */
    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @OnClick({R.id.rl_question_mingbai, R.id.rl_question_soso, R.id.rl_question_innocent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_question_mingbai:
                addQuestionSame("0");
                break;
            case R.id.rl_question_soso:
                addQuestionSame("1");
                break;
            case R.id.rl_question_innocent:
                addQuestionSame("2");
                break;
        }
    }

    //明白，一般， 不懂
    private void addQuestionSame(final String score) {
        showProgressDialog();
        String userId = PrefUtils.getString(JpushMsgListActivity.this, Constants.USER_ID, "");
        addSubscription(apiStores.addLessonQuestionScore(bizId, userId, score), new ApiCallback<SocketMessage>() {
            @Override
            public void onSuccess(SocketMessage model) {
                if (TextUtils.equals(score,"0")){
                    mRlQuestionInnocent.setVisibility(View.INVISIBLE);
                    mRlQuestionSoso.setVisibility(View.INVISIBLE);
                    mRlQuestionMingbai.setEnabled(false);
                }else if (TextUtils.equals(score,"1")){
                    mRlQuestionInnocent.setVisibility(View.INVISIBLE);
                    mRlQuestionMingbai.setVisibility(View.INVISIBLE);
                    mRlQuestionSoso.setEnabled(false);
                }else if (TextUtils.equals(score,"2")){
                    mRlQuestionMingbai.setVisibility(View.INVISIBLE);
                    mRlQuestionSoso.setVisibility(View.INVISIBLE);
                    mRlQuestionInnocent.setEnabled(false);
                }
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
