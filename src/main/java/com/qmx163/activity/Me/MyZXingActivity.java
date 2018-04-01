package com.qmx163.activity.Me;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.qmx163.R;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.QRCodeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by likai on 2017/7/1.
 * 我的二维码界面
 */

public class MyZXingActivity extends BaseAc {


    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    @BindView(R.id.Right_img)
    ImageView mRightImg;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.iv_zxing)
    ImageView mIvZxing;
    Bitmap bitmapUrl;
    @BindView(R.id.iv_sex)
    ImageView mIvSex;
    @BindView(R.id.tv_status)
    TextView mTvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zxing);
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


        mRlTitle.setBackgroundColor(Color.BLACK);
        mIbtnLeft.setVisibility(View.VISIBLE);
        mRightImg.setVisibility(View.GONE);
        mTvTitle.setText(R.string.my_zxing);
        mTvTitle.setTextColor(Color.WHITE);
        if (PrefUtils.getString(MyZXingActivity.this, Constants.USER_SEX, "").equals("0")) {
            mIvSex.setImageResource(R.mipmap.me_indentily_girl);

        } else {
            mIvSex.setImageResource(R.mipmap.me_indentily_boy);
        }

        if (PrefUtils.getString(MyZXingActivity.this, Constants.USER_TYPE, "").equals("0")) {
            mTvStatus.setText("学生");
        } else {
            mTvStatus.setText("家长");
        }
        mTvName.setText(PrefUtils.getString(MyZXingActivity.this, Constants.USER_NAME, ""));
        String img = PrefUtils.getString(MyZXingActivity.this, Constants.USER_IMG, "");
        Glide.with(this).load(img).centerCrop().error(R.mipmap.flunk).crossFade().into((ImageView) mIvHead);
        Glide.with(this).load(img).asBitmap().dontAnimate().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                bitmapUrl = resource;
                if (bitmapUrl == null) {
                    Bitmap img2 = QRCodeUtil.createQRImage(MyZXingActivity.this, PrefUtils.getString(MyZXingActivity.this, Constants.USER_ID, ""), null);
                    mIvZxing.setImageBitmap(img2);
                } else {
                    Bitmap img2 = QRCodeUtil.createQRImage(MyZXingActivity.this, PrefUtils.getString(MyZXingActivity.this, Constants.USER_ID, ""), bitmapUrl);
                    mIvZxing.setImageBitmap(img2);
                }
            }
        });
    }
}
