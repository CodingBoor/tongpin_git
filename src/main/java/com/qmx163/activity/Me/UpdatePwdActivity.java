package com.qmx163.activity.Me;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.activity.LoginActivity;
import com.qmx163.base.BaseAc;
import com.qmx163.base.BaseBean;
import com.qmx163.config.Constants;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.MD5Util;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码界面
 */
public class UpdatePwdActivity extends BaseAc {

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
    @BindView(R.id.et_oldpsw)
    EditText mEtOldpsw;
    @BindView(R.id.ll_oldpsw)
    LinearLayout mLlOldpsw;
    @BindView(R.id.et_newpsw)
    EditText mEtNewpsw;
    @BindView(R.id.ll_newpsw)
    LinearLayout mLlNewpsw;
    @BindView(R.id.et_newpsw_again)
    EditText mEtNewpswAgain;
    @BindView(R.id.ll_newpsw_again)
    LinearLayout mLlNewpswAgain;
    @BindView(R.id.tv_sure)
    com.rey.material.widget.TextView mTvSure;

    String OldPsw = "";
    String NewPsw = "";
    String NewPswAgain = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);
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

        mTvTitle.setText(R.string.text_setting);
        mIbtnLeft.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.ll_oldpsw, R.id.ll_newpsw, R.id.ll_newpsw_again, R.id.tv_sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_oldpsw:
                break;
            case R.id.ll_newpsw:
                break;
            case R.id.ll_newpsw_again:
                break;
            case R.id.tv_sure:
                OldPsw = mEtOldpsw.getText().toString();
                NewPsw = mEtNewpsw.getText().toString();
                NewPswAgain = mEtNewpswAgain.getText().toString();
                if (OldPsw.isEmpty()||NewPsw.isEmpty()||NewPswAgain.isEmpty()){
                    showToas("请输入完整信息");
                    return;
                }if (!Tools.isPsw(mEtNewpsw.getText().toString())) {
                return;
            }
                if (!NewPsw.equals(NewPswAgain)){
                    showToas("两次新密码不一致");
                    return;
                }
                MD5Util md5Util = new MD5Util();
                OldPsw = md5Util.MD5Encode(OldPsw, false);
                NewPsw = md5Util.MD5Encode(NewPsw, false);
                NewPswAgain = md5Util.MD5Encode(NewPswAgain, false);
                addSubscription(apiStores.updatePwd(PrefUtils.getString(UpdatePwdActivity.this, Constants.USER_LOGIN_ID, ""), OldPsw, NewPsw), new ApiCallback<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> model) {
                        showToas("修改成功");
                        PrefUtils.setString(UpdatePwdActivity.this, Constants.USER_TOKEN, "");
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(UpdatePwdActivity.this, LoginActivity.class);
                                        intent.putExtra("check_user", true);
//                                        ActivityCollector.finishAll();
                                        startActivity(intent);
                                    }
                                },960);
                    }

                    @Override
                    public void onFailure(String code) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
                break;
        }
    }
}
