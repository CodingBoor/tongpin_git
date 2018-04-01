package com.qmx163.activity.Me;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.base.BaseAc;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyDataItemActivity extends BaseAc implements View.OnClickListener {

    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
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
    @BindView(R.id.et_text)
    EditText mEtText;
    @BindView(R.id.iv_del)
    com.rey.material.widget.ImageView mIvDel;
    public final static int RESULT_CODE = 10;
    String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_data_item);
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
        mTvRight.setVisibility(View.VISIBLE);
        mRightImg.setVisibility(View.GONE);
        mTvRight.setTextColor(Color.BLACK);
        mTvRight.setText("确定");
        mTvRight.setOnClickListener(this);
        mIvDel.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String mText = bundle.getString("str");
        mTitle = bundle.getString("title");
        mTvTitle.setText(mTitle);
        mEtText.setText(mText);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_del:
                mEtText.setText("");
                break;
            case R.id.tv_right:
                String text = "";
                text = mEtText.getText().toString();
                if (mTitle.equals("性别")) {
                    text = mEtText.getText().toString();
                    if (!text.equals("男") && !text.equals("女")) {
                        showToas("性别请填写男,女");
                        return;
                    }
                }
                if (text.equals("")||text == null) {
                    showToas("姓名不能为空");
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("back", mEtText.getText().toString());
                    intent.putExtra("title", mTvTitle.getText());
                    setResult(RESULT_CODE, intent);
                    finish();
                }
                break;
        }

    }
}
