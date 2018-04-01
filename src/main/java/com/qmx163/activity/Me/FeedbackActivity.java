package com.qmx163.activity.Me;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseAcNoStatusBar;
import com.qmx163.base.BaseBean;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.Feedbackes;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.View.RegexValidateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈
 */
public class FeedbackActivity extends BaseAcNoStatusBar {

    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
//    @BindView(R.id.tv_bg_up)
//    TextView mUp;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.Right_img)
    ImageView mRightImg;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.rv_feedback)
    RecyclerView mRvFeedback;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.rl_phone)
    EditText rlPhone;
    @BindView(R.id.contxt)
    EditText contxt;
    @BindView(R.id.textSize)
    TextView textSize;
    private RecyclerAdapter<Feedbackes.DataBean> feedbackAdapter;
    private List<Feedbackes.DataBean> feedbackList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);

        //设置statusbar高度
//        int statusBarHeight1 = -1;
////获取status_bar_height资源的ID
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            //根据资源ID获取响应的尺寸值
//            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
//
//            RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) mUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
//
//            linearParams.height = statusBarHeight1;// 控件的宽强制设成30
//
//            mUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
//        }

//        mUp.setVisibility(View.GONE);

        mIbtnLeft.setVisibility(View.VISIBLE);
        mRightImg.setVisibility(View.GONE);
        mTvTitle.setText(R.string.my_feedback);
        bindReceiverView();
    }

    //初始化意见反馈列表
    private void bindReceiverView() {
        getDate();
        contxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textSize.setText(s.length() + "/300");
            }
        });
    }

    /**
     * 获取反馈列表数据
     */
    private void getDate() {
        showProgressDialog();
        addSubscription(apiStores.feedBackes(PrefUtils.getString(FeedbackActivity.this, Constants.USER_ID, "")), new ApiCallback<Feedbackes>() {
            @Override
            public void onSuccess(Feedbackes model) {
                if ("200".equals(model.getCode())) {
                    feedbackList.clear();
                    feedbackList.addAll(model.getData());
                    feedbackAdapter = new RecyclerAdapter<Feedbackes.DataBean>(FeedbackActivity.this, feedbackList, R.layout.item_feedback) {
                        @Override
                        public void convert(RecyclerViewHolder helper, Feedbackes.DataBean item, int position) {
                            // -1删除 0正常 1已处理
                            helper.setText(R.id.tv_texts, feedbackList.get(position).getContent());
                            helper.setText(R.id.tv_timer, feedbackList.get(position).getAddTime());
                            TextView status = helper.getView(R.id.tv_status);
                            if (feedbackList.get(position).getStatus() == 1) {
                                status.setText("已处理");
                                status.setTextColor(FeedbackActivity.this.getResources().getColor(R.color.color_15a43d));
                            } else {
                                status.setText("处理中");
                                status.setTextColor(FeedbackActivity.this.getResources().getColor(R.color.color_e80500));
                            }
                        }
                    };
//                    feedbackAdapter.changeMoreStatus(feedbackAdapter.NO_LOAD_MORE);
                    mRvFeedback.setHasFixedSize(true);
                    mRvFeedback.setNestedScrollingEnabled(false);
                    mRvFeedback.setLayoutManager(new GridLayoutManager(FeedbackActivity.this, 1, GridLayoutManager.VERTICAL, false));
                    mRvFeedback.setAdapter(feedbackAdapter);
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


    @OnClick({R.id.tv_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_send:
                String contxts = contxt.getText().toString();
                String rlPhones = rlPhone.getText().toString();
                if (TextUtils.isEmpty(contxts)) {
                    showToas("请填写反馈意见!");
                    return;
                }
                if (RegexValidateUtil.checkCellphone(rlPhones)) {
                    showProgressDialog();
                    sendFeedback(contxts, rlPhones,"");
                    return;
                }else if (RegexValidateUtil.checkQQ(rlPhones)){
                    showProgressDialog();
                    sendFeedback(contxts, rlPhones,"");
                    return;
                }
                else if (RegexValidateUtil.checkEmail(rlPhones)){
                    showProgressDialog();
                    sendFeedback(contxts,"" ,rlPhones);
                }else {
                    showToas("请填写正确的联系方式");
                }
                break;
        }
    }


    //发送意见反馈
    private void sendFeedback(String contxts, String phone ,String email) {
        addSubscription(apiStores.AddfeedBackes(PrefUtils.getString(FeedbackActivity.this, Constants.USER_ID, ""),"", contxts, phone,email), new ApiCallback<BaseBean<Object>>() {
            @Override
            public void onSuccess(BaseBean<Object> model) {
                if (model.getCode() == 200) {
                    contxt.setText("");
//                    bindReceiverView();
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                    getDate();

                        }
                    });
                }
                showToas(model.getMessage());
            }

            @Override
            public void onFailure(String code) {
                dismissProgressDialog();
                showToas("请求失败");
//                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
