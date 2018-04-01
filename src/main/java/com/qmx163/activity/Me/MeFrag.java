package com.qmx163.activity.Me;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qmx163.R;
import com.qmx163.activity.FocusOn.FocusOnActivity;
import com.qmx163.activity.LoginActivity;
import com.qmx163.activity.StudyToday.StudyCollectionActivity;
import com.qmx163.application.MyApplication;
import com.qmx163.base.BaseAc;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


/**
 * 我的
 * Created by Administrator on 2017/6/13.
 */

public class MeFrag extends BaseFm implements View.OnClickListener {
    Unbinder unbinder;
    @BindView(R.id.iamgeleft)
    ImageView imageleft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.ibtn_left)
    RelativeLayout ibtnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.Right_img)
    ImageView RightImg;
    @BindView(R.id.yh_Name)
    TextView yhName;
    @BindView(R.id.yh_sexs)
    ImageView yhSex;
    @BindView(R.id.yh_js)
    TextView yhJs;
    @BindView(R.id.tv_level)
    TextView mTvLevel;
    @BindView(R.id.tv_collection)
    TextView mTvCollection;
    @BindView(R.id.tv_star)
    TextView mTvStar;
    //    @BindView(R.id.rl_title)
//    RelativeLayout mRlTitle;
//    @BindView(R.id.iv_head)
//    CircleImageView mIvHead;
    @BindView(R.id.study_time)
    TextView mStudyTime;
    @BindView(R.id.read_size)
    TextView mReadSize;
    @BindView(R.id.start_size)
    TextView mStartSize;
    @BindView(R.id.text_line1)
    TextView mTextLine1;
    @BindView(R.id.tv_star_size)
    TextView mTvStartSize;
    @BindView(R.id.tv_item_study)
    TextView mTvItemStudy;
    @BindView(R.id.ll_study_time)
    LinearLayout llStudyTime;
    @BindView(R.id.iv_me_bg)
    ImageView meBg;
    private CircleImageView ivHead;
    private TextView tvZxing;
    private TextView tvEnergy;
    private TextView tvAbout;
    private TextView tvIdea;
    private TextView tvChange;
    private ImageView ivTozxing;
    private ImageView ivSet;
    private TextView tvStudy;
    private ImageView ivSign;
    private PopupWindow mPopupWindow;
    public static final int REQUEST_CODE = 0;
    public static final int REQUEST_MY_DATA = 1;
    RegisteredEn.DataBean registEn;

    private int type;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment, container, false);
        tvZxing = (TextView) view.findViewById(R.id.tv_zxing);
        tvEnergy = (TextView) view.findViewById(R.id.tv_energy);
        tvAbout = (TextView) view.findViewById(R.id.tv_about);
        tvChange = (TextView) view.findViewById(R.id.tv_change);
        tvIdea = (TextView) view.findViewById(R.id.tv_idea);
        tvStudy = (TextView) view.findViewById(R.id.tv_study);
        ivHead = (CircleImageView) view.findViewById(R.id.iv_head);
        ivSet = (ImageView) view.findViewById(R.id.iv_set);
        ivTozxing = (ImageView) view.findViewById(R.id.iv_tozxing);
        ivSign = (ImageView) view.findViewById(R.id.iv_sign);
        unbinder = ButterKnife.bind(this, view);
        RightImg.setVisibility(View.VISIBLE);
        tvLeft.setText("陪伴教育");

        if (PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "").equals("") || PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "") == null) {
//            startAc(LoginActivity.class);
//            getActivity().finish();
        } else {
            initView();
            tvZxing.setOnClickListener(this);
            mTvItemStudy.setOnClickListener(this);
            ivHead.setOnClickListener(this);
            tvEnergy.setOnClickListener(this);
            tvAbout.setOnClickListener(this);
            tvIdea.setOnClickListener(this);
            ivSet.setOnClickListener(this);
            ivTozxing.setOnClickListener(this);
            tvStudy.setOnClickListener(this);
            tvChange.setOnClickListener(this);
            ivSign.setOnClickListener(this);
            mTvCollection.setOnClickListener(this);
            mTvStar.setOnClickListener(this);
        }
        return view;
    }

    public void initView() {
        registEn = PrefUtils.getObjectFromShare(getActivity(), "data");
        String bgUrl = PrefUtils.getString(getActivity(), Constants.APP_STUDY_BG, "");
        Glide.with(getActivity()).load(bgUrl).dontAnimate().error(R.mipmap.me_background).into(meBg);
//        if (registEn != null) {
//            Glide.with(getActivity()).load(PrefUtils.getString(getActivity(),"IMG","")).dontAnimate().centerCrop().error(R.mipmap.flunk)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).crossFade().into((ImageView) ivHead);
//            yhName.setText(registEn.getMemberName());
        setUserData();
        type = Integer.parseInt(PrefUtils.getString(getActivity(), Constants.USER_TYPE, "-1"));
//            type = registEn.getType();
//            int sex = registEn.getSex();
        if (type == 0) {
            yhJs.setText("学生");
                ivTozxing.setVisibility(View.GONE);
            tvZxing.setVisibility(View.VISIBLE);
            mTvStartSize.setVisibility(View.VISIBLE);
        } else {
            tvZxing.setVisibility(View.GONE);
            ivTozxing.setVisibility(View.VISIBLE);
            mTextLine1.setVisibility(View.VISIBLE);
            mTvItemStudy.setVisibility(View.VISIBLE);
            llStudyTime.setVisibility(View.GONE);
            yhJs.setText("家长");
        }
//            if (sex == 0)
//                yhSex.setImageResource(R.mipmap.me_indentily_girl);
//            else
//                yhSex.setImageResource(R.mipmap.me_indentily_boy);
    }

//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head: //跳转个人资料界面
//                ToastUtil.toastShortShow(getActivity(), PrefUtils.getString(getActivity(), "ID", ""));
//                startAc(MyDateActivity.class);
showProgressDialog();
                addSubscription(apiStores.SelectPer(PrefUtils.getString(getActivity(), Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
                    @Override
                    public void onSuccess(RegisteredEn model) {
                        if ("200".equals(model.getCode())) {
                            RegisteredEn.DataBean data = model.getData();
                            Intent intent = new Intent(getActivity(), MyDateActivity.class);
                            intent.putExtra("dataa",data);
                            startActivityForResult(intent, REQUEST_MY_DATA);
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


//                if (!NetUtil.isNetworkConnected(getActivity())) {
//                    showToas(getString(R.string.no_net));
//                } else {
//                    Intent intent = new Intent(getActivity(), MyDateActivity.class);
//                    startActivityForResult(intent, REQUEST_MY_DATA);
//                }
                break;
            case R.id.tv_about: //跳转关于我们界面
                startAc(AboutUsActivity.class);
                break;
            case R.id.tv_idea: //跳转意见反馈界面
                startAc(FeedbackActivity.class);
                break;
            case R.id.tv_energy://跳转我的能量界面
                startAc(MyEnergyActivity.class);
                break;
            case R.id.tv_change: //切换用户界面
//                startAc(CheckRelationActivity.class);
                new AlertDialog.Builder(v.getContext())
                        .setMessage(R.string.me_check_user)
                        .setNegativeButton(R.string.me_sure, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((BaseAc) getActivity()).clearActivity();
                                PrefUtils.setString(getActivity(), Constants.USER_ID, "");
                                PrefUtils.setString(getActivity(), Constants.USER_TOKEN, "");
                                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                                intent2.putExtra("check_user", true);
                                startAc(intent2);
                            }
                        })
                        .setNeutralButton(R.string.me_cancle, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();

                break;
            case R.id.tv_zxing://跳转二维码界面
                startAc(MyZXingActivity.class);
                break;
            case R.id.iv_tozxing://跳转扫描二维码界面
                requestPermission(1, Manifest.permission.CAMERA, new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getActivity(), CaptureActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivityForResult(intent, REQUEST_CODE);

                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        showToas(getResources().getString(R.string.no_permission_to_use_the_camera));
                    }
                });

                break;
            case R.id.iv_set://跳转设置界面
                startAc(MySettingActivity.class);
                break;
            case R.id.tv_item_study://跳转陪伴学习界面`
                startAc(AccompanyStudyActivity.class);
                break;
            case R.id.iv_sign://跳转日历签到界面
//                signPopu();
                Intent intent1 = new Intent(getActivity(), SignActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_star: //我的关注界面
                startAc(FocusOnActivity.class);
                break;
            case R.id.tv_collection:  //跳转我的收藏页面
                startAc(StudyCollectionActivity.class);
                break;

        }
    }

    private void signPopu() {
//        View popuView = View.inflate(getActivity(),R.layout.popu_sign,null);
//        int width = getResources().getDisplayMetrics().widthPixels;
//        int height = getResources().getDisplayMetrics().heightPixels;
//        mPopupWindow = new PopupWindow(popuView,width,height);
//        mPopupWindow.setAnimationStyle(R.anim.photo_dialog_in_anim);
//        mPopupWindow.setFocusable(true);
//        mPopupWindow.setOutsideTouchable(true);
//        ColorDrawable dw = new ColorDrawable(0x30fafafa);
//        mPopupWindow.setBackgroundDrawable(dw);
//        int[] location = new int[2];
//        ivSign.getLocationOnScreen(location);
//        mPopupWindow.showAtLocation(null, Gravity.NO_GRAVITY, location[0], location[1] - mPopupWindow.getHeight());


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) { //RESULT_OK = -1

            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(getActivity(), scanResult, Toast.LENGTH_LONG).show();
        }
        if (requestCode == REQUEST_MY_DATA && resultCode == RESULT_OK) {
            setUserData();
        }
    }

    //设置用户信息
    private void setUserData() {
        Glide.with(getActivity()).load(PrefUtils.getString(getActivity(), Constants.USER_IMG, "")).dontAnimate().centerCrop().error(R.mipmap.flunk)
                .diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).crossFade().into((ImageView) ivHead);
        yhName.setText(PrefUtils.getString(getActivity(), Constants.USER_NAME, ""));
        mTvLevel.setText(PrefUtils.getString(getActivity(), Constants.USER_LEVEL, "学员"));
        if (TextUtils.equals(PrefUtils.getString(getActivity(), Constants.USER_SEX, ""), "0"))
            yhSex.setImageResource(R.mipmap.me_indentily_girl);
        else if (TextUtils.equals(PrefUtils.getString(getActivity(), Constants.USER_SEX, ""), "1")) {
            yhSex.setImageResource(R.mipmap.me_indentily_boy);

        } else {
            yhSex.setVisibility(View.GONE);
        }
    }

    /**
     * 请求权限
     *
     * @param id                   请求授权的id 唯一标识即可
     * @param permission           请求的权限
     * @param allowableRunnable    同意授权后的操作
     * @param disallowableRunnable 禁止权限后的操作
     */
    public void requestPermission(int id, String permission, Runnable allowableRunnable, Runnable disallowableRunnable) {
        if (allowableRunnable == null) {
            throw new IllegalArgumentException("allowableRunnable == null");
        }

        MyApplication.allowable(id, allowableRunnable);
        if (disallowableRunnable != null) {
            MyApplication.disallowable(id, disallowableRunnable);
        }

        //版本判断
        if (Build.VERSION.SDK_INT >= 23) {
            //减少是否拥有权限
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getActivity(), permission);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                //弹出对话框接收权限
                ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, id);
                return;
            } else {
                allowableRunnable.run();
            }
        } else {
            allowableRunnable.run();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Runnable allowRun = MyApplication.getallowable().get(requestCode);
            allowRun.run();
        } else {
            Runnable disallowRun = MyApplication.getdesallowable().get(requestCode);
            disallowRun.run();
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        if (PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "").equals("") || PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "") == null) {
            return;
        } else {
            String k = PrefUtils.getString(getActivity(), Constants.USER_ID, "");
            addSubscription(apiStores.SelectPer(PrefUtils.getString(getActivity(), Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
                @Override
                public void onSuccess(RegisteredEn model) {
                    if ("200".equals(model.getCode())) {
                        RegisteredEn.DataBean data = model.getData();
                        mStudyTime.setText("学习时间：" + data.getStudyTime() + "小时");
                        mReadSize.setText("阅读篇数：" + data.getReadCount());
                        mStartSize.setText("累计学习：" + data.getStudyDay() + "天");
                        mTvStartSize.setText(data.getEncourageAmount() + "");
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
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && type == 0) {

            if (PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "").equals("") || PrefUtils.getString(getActivity(), Constants.USER_TOKEN, "") == null) {
                return;
            } else {

                addSubscription(apiStores.SelectPer(PrefUtils.getString(getActivity(), Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
                    @Override
                    public void onSuccess(RegisteredEn model) {
                        if ("200".equals(model.getCode())) {
                            RegisteredEn.DataBean data = model.getData();
                            mStudyTime.setText("学习时间：" + data.getStudyTime() + "小时");
                            mReadSize.setText("阅读篇数：" + data.getReadCount());
                            mStartSize.setText("累计学习：" + data.getStudyDay() + "天");
                            mTvStartSize.setText(data.getEncourageAmount() + "");
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

        }
    }
}
