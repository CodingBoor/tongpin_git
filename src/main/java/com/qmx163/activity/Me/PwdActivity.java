package com.qmx163.activity.Me;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.base.BaseAc;
import com.qmx163.base.BaseBean;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.MD5Util;
import com.qmx163.util.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册页面和找回密码页面
 * Created by Administrator on 2017/6/14.
 */

public class PwdActivity extends BaseAc {

    // 标题栏
    @BindView(R.id.ibtn_left)
    RelativeLayout ibtnLeft;
    @BindView(R.id.iamgeleft)
    ImageView iamgeleft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.Right_img)
    ImageView iamgeRight;

    // 当前页面内容
    @BindView(R.id.btn_rg_cz)
    com.rey.material.widget.TextView btnRgCz;
    @BindView(R.id.pwd_logo)
    ImageView logo;
    @BindView(R.id.exitpwd)
    com.rey.material.widget.TextView exitpwd;
    @BindView(R.id.activity_pwd)
    LinearLayout activityPwd;
    @BindView(R.id.getSmsCode)
    com.rey.material.widget.TextView getSmsCode;
    @BindView(R.id.re_Edit)
    EditText reEdits;
    @BindView(R.id.ph_tel)
    EditText phTels;
    @BindView(R.id.pwd)
    EditText pwd;
//    @BindView(R.id.oldpwd)
//    EditText oldpwd;

    private TimeCount time;
    private String name;
    private int id;
    private String czPhone = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd);
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

        ibtnLeft.setVisibility(View.GONE);
        tvLeft.setVisibility(View.VISIBLE);
        name = getIntent().getStringExtra("name");
        id = getIntent().getIntExtra("id", 0);
        tvTitle.setText(name);

        time = new TimeCount(60000, 1000);
        iamgeRight.setVisibility(View.GONE);

        /**
         *  id = 0 是注册
         *  id = 1 是找回密码
         */
        if (id == 1) {
            ibtnLeft.setVisibility(View.VISIBLE);
            iamgeleft.setImageDrawable(getResources().getDrawable(R.mipmap.back));
            tvLeft.setVisibility(View.GONE);
            logo.setVisibility(View.GONE);
//            btnRgCz.setBackgroundResource(R.mipmap.czbt);
            btnRgCz.setText("重置");
        }
    }

    @OnClick({R.id.iamgeleft, R.id.btn_rg_cz, R.id.exitpwd, R.id.getSmsCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iamgeleft:
                finish();
                break;
            case R.id.btn_rg_cz:
                String reCode = reEdits.getText().toString();
                String Password = pwd.getText().toString();
                phTel = phTels.getText().toString();
                if (!Tools.isMobileNO(phTel)) {
                    showToas("请输入正确的手机号码!");
                    return;
                }
                if (reCode == null) {
                    showToas("请输入正确的验证码!");
                    return;
                }
                if (phTelTwo != null) {
                    if (!phTel.equals(phTelTwo)) {
                        showToas("手机号已改变,请重新获取验证码!");
                        return;
                    }
                }
                if (Password == null) {
                    showToas("密码不能为空");
                    return;
                }
                if (!Tools.isPsw(pwd.getText().toString())) {
                    return;
                }
                MD5Util md5Util = new MD5Util();
                String md5 = md5Util.MD5Encode(Password, false);
                if (reCode.equals(phTelCode)) {
                    if (id == 1) {//忘记密码
                        if (!TextUtils.equals(czPhone,phTels.getText().toString())){
                            showToas("手机号已改变,请重新获取验证码!");
                        }else {
                        updatePwd(phTel, md5);
                        }

                    } else {
                        Intent it = new Intent(this, CompleteInformationAc.class);
                        it.putExtra("phone", phTel);
                        it.putExtra("Password", md5);
                        startAc(it);
                    }
                } else {
                    showToas("验证码错误请重新输入!");
                }

                break;
            case R.id.exitpwd:
                finish();
                break;
            case R.id.getSmsCode:
                phTel = phTels.getText().toString();
                if (Tools.isMobileNO(phTel)) {
                    if (id == 1) {
                        getSmsCode(1);
                    } else {
                        getSmsCode(0);
                    }
                } else {
                    showToas("请输入正确的手机号!");
                }
                break;
        }
    }

    String phTel;
    String phTelTwo;
    String phTelCode;


    //重置密码
    private void updatePwd(String Tel, String newPwd) {
        showProgressDialog();
        addSubscription(apiStores.resetPwd(Tel, newPwd),
                new ApiCallback<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> model) {
                        showToas("重置成功");
                        finish();
                    }

                    @Override
                    public void onFailure(String msg) {
                        dismissProgressDialog();
                        if (msg.equals("200")) {
                            finish();
                        }
                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }
                });
    }

    private void getSmsCode(int index) {
        showProgressDialog();
        addSubscription(apiStores.GetIdentifyCode(phTel, index),
                new ApiCallback<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> model) {
                        time.start();
                        czPhone = phTel;
                        phTelCode = (String) model.getData();
                        phTelTwo = phTels.getText().toString();
                    }

                    @Override
                    public void onFailure(String msg) {
                        if (msg.equals("801")){
                            showToas("该手机号已注册");
                            dismissProgressDialog();
                        }
                    }

                    @Override
                    public void onFinish() {
                        dismissProgressDialog();
                    }

                });
    }


    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            getSmsCode.setClickable(false);
            getSmsCode.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            getSmsCode.setText("重发");
            getSmsCode.setClickable(true);
        }
    }
}
