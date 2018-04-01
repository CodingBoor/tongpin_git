package com.qmx163.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.qmx163.R;
import com.qmx163.activity.Me.PwdActivity;
import com.qmx163.activity.watch.Param;
import com.qmx163.application.MyApplication;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.ActivityCollector;
import com.qmx163.util.MD5Util;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.ToastUtil;
import com.qmx163.util.Tools;
import com.vhall.business.VhallSDK;
import com.vhall.business.data.UserInfo;
import com.vhall.business.data.source.UserInfoDataSource;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * 登录页面
 */
public class LoginActivity extends BaseAc implements View.OnClickListener {

    // 标题栏
    @BindView(R.id.ibtn_left)
    RelativeLayout ibtnLeft;
    @BindView(R.id.iamgeleft)
    ImageView imageleft;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.Right_img)
    ImageView imageRight;


    // 页面基础信息
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.login)
    com.rey.material.widget.TextView login;
    @BindView(R.id.forgetPwd)
    com.rey.material.widget.TextView forgetPwd;
    @BindView(R.id.Regist)
    TextView Regist;
    @BindView(R.id.edpwd)
    EditText edpwd;
    @BindView(R.id.iv_bg)
    ImageView bg;
    private static final String TAG = "LoginActivity";
    private boolean checkUser = false; //tockon为空时弹出,无需销毁主页
    private boolean checkOutUser = false; //切换用户
    private String userId = "";
    private String userToken = "";
    private boolean otherLogin = false;

    private boolean jpushComment = false;
    private boolean jpushmeeage = false;
//    int loadTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SwipeBackLayout mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEnableGesture(false);
        ButterKnife.bind(this);

        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) mUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        jpushComment = getIntent().getBooleanExtra("jpush_comment", false);
        jpushmeeage = getIntent().getBooleanExtra("jpush_message", false);
//        if (jpushComment|| jpushmeeage){
//            loadTime = 0;
//        }

        try {
            checkUser = getIntent().getBooleanExtra("check_user", false);
            checkOutUser = getIntent().getBooleanExtra("check_out_user", false);
            otherLogin = getIntent().getBooleanExtra("type", false);
            userToken = PrefUtils.getString(this, Constants.USER_TOKEN, "");
            userId = PrefUtils.getString(this, Constants.USER_ID, "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 判断token是否为空，不为空则用token登录
        if (!TextUtils.equals(userToken, "") && userToken != null) {
//            initWelcomLogo();
            tokenLogin(userToken);
        } else {

            if (checkUser) {
//                bg.setVisibility(View.GONE);
                if (checkOutUser) {
                    ibtnLeft.setVisibility(View.GONE);
                    for (int i = 0; i < ActivityCollector.activities.size(); i++) {
                        if (i != ActivityCollector.activities.size() - 1) {
                            ActivityCollector.removeActivity(ActivityCollector.activities.get(i));
                            i--;
//                    ActivityCollector.activities.remove(i);
                        }

                    }
                } else {
                    if (otherLogin) {
                        ibtnLeft.setVisibility(View.GONE);
                        ToastUtil.toastShortShow(this, "您的账号已经在其他地方登录");
                        for (int i = 0; i < ActivityCollector.activities.size(); i++) {
                            if (i != ActivityCollector.activities.size() - 1) {
                                ActivityCollector.removeActivity(ActivityCollector.activities.get(i));
                                i--;
//                    ActivityCollector.activities.remove(i);
                            }

                        }
                    } else {
                        ibtnLeft.setVisibility(View.VISIBLE);

                    }
                }
            } else {
//            initWelcomLogo();
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
                if (!TextUtils.equals(userId, "") && userId != null) {

                    //设置极光推送tag
                    if (PrefUtils.getString(LoginActivity.this, Constants.USER_ID, "") != null) {
                        if (PrefUtils.getBoolean(LoginActivity.this, Constants.USER_PUSH, true)) {
                            jPushTag();
                        }
                    }
//                            startAc(MainActivity.class);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("islogin", true);
                    startAc(intent);
                    finish();
                } else {
//                            bg.setVisibility(View.GONE);
                }
                for (int i = 0; i < ActivityCollector.activities.size(); i++) {
                    if (i != ActivityCollector.activities.size() - 1) {
                        ActivityCollector.removeActivity(ActivityCollector.activities.get(i));
                        i--;
//                    ActivityCollector.activities.remove(i);
                    }

                }
            }
//                }, loadTime);
//            }


        }


        ibtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        VhallSDK.init(LoginActivity.this, "48e6c98b2a9cfcae09d4d831bf22dc96", "6ff5b14649485c2af03b62e5b40af885");
        tvTitle.setText("登录");
        etUserName.setHintTextColor(this.getResources().getColor(R.color.hintbg));
        edpwd.setHintTextColor(this.getResources().getColor(R.color.hintbg));
        login.setOnClickListener(this);
        imageRight.setVisibility(View.GONE);
        try {
            etUserName.setText(PrefUtils.getString(this, Constants.USER_LOGIN_ID, ""));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        etUserName.setText(PrefUtils.getString(this, Constants.USER_LOGIN_ID, ""));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String name = etUserName.getText().toString();
                String pwd = edpwd.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    ToastUtil.toastShortShow(this, "用户名不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    ToastUtil.toastShortShow(this, "密码不能为空");
                    return;
                }
                if (!Tools.isPsw(edpwd.getText().toString())) {
                    return;
                }
                //yang123
                MD5Util md5Util = new MD5Util();
                final String md5 = md5Util.MD5Encode(pwd, false);
//                PrefUtils.setString(LoginActivity.this, Constants.USER_ID, "b368c9f05ba711e7905400163e323696");//测试id

                addSubscription(apiStores.Login(name, md5),
//                addSubscription(apiStores.Login("15813337262", "da75f4ce2b03dba36da3256653950485"),//测试号
                        new ApiCallback<RegisteredEn>() {
                            @Override
                            public void onSuccess(RegisteredEn model) {
                                if ("200".equals(model.getCode())) {

                                    //设置为调试模式，具体发布的时候可以直接设置为false
                                    JPushInterface.setDebugMode(false);
                                    JPushInterface.init(LoginActivity.this);

//                                PrefUtils.setString(LoginActivity.this,"ID","a3b6cccd737911e78d8900163e06d055");//测试id
                                    PrefUtils.setString(LoginActivity.this, Constants.PARENT_ID, "");//测试家长id  b368c9f05ba711e7905400163e323696
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_PASSWORD, md5);//用户密码
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_ID, model.getData().getId());//测试id
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_NAME, model.getData().getMemberName());
//                                    PrefUtils.setString(LoginActivity.this, "ICON", model.getData().getImg());
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_LOGIN_ID, model.getData().getLoginId());
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_IMG, model.getData().getImg());
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_LEVEL, model.getData().getLevel());
                                    PrefUtils.setInt(LoginActivity.this, Constants.USER_SCORE, model.getData().getScore());
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_TOKEN, model.getData().getToken());
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_SEX, String.valueOf(model.getData().getSex()));
                                    PrefUtils.setString(LoginActivity.this, Constants.USER_TYPE, String.valueOf(model.getData().getType()));
                                    PrefUtils.saveObjectToShare(LoginActivity.this, "data", model.getData());
//                                 login(model.getData().getId(),md5);
                                    Log.e(TAG, "---------------token: " + model.getData().getToken());


                                    //设置极光推送tag
                                    if (PrefUtils.getString(LoginActivity.this, Constants.USER_ID, "") != null) {
                                        if (PrefUtils.getBoolean(LoginActivity.this, Constants.USER_PUSH, true)) {
                                            jPushTag();
                                        }
                                    }
//                                    startAc(MainActivity.class);
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("islogin", true);
                                    startAc(intent);
                                    finish();
                                } else {
                                    showToas(model.getMessage());
                                }
                            }

                            @Override
                            public void onFailure(String code) {

                                if (code.equals("806")) {
                                    showToas("密码错误");
                                }
                            }

                            @Override
                            public void onFinish() {

                            }
                        });
//                startAc(MainActivity.class);
                break;
        }
    }

    @OnClick({R.id.forgetPwd, R.id.Regist})
    public void onViewClicked(View view) {
        Intent it = new Intent(this, PwdActivity.class);
        switch (view.getId()) {
            case R.id.forgetPwd:
                it.putExtra("name", "忘记密码");
                it.putExtra("id", 1);
                startAc(it);
                break;
            case R.id.Regist:
                it.putExtra("name", "注册");
                it.putExtra("id", 0);
                startAc(it);
                break;
        }
    }

    /**
     * 加载启动页的背景图，处理图标拉伸问题
     */
    private void initWelcomLogo() {
        // 获取屏幕的高宽
        Point outSize = new Point();
        this.getWindow().getWindowManager().getDefaultDisplay().getSize(outSize);

        // 解析将要被处理的图片
        Bitmap resourceBitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.login_bg1);

        if (resourceBitmap == null) {
            return;
        }

        // 开始对图片进行拉伸或者缩放

        // 使用图片的缩放比例计算将要放大的图片的高度
        int bitmapScaledHeight = Math.round(resourceBitmap.getHeight() * outSize.x * 1.0f / resourceBitmap.getWidth());

        // 以屏幕的宽度为基准，如果图片的宽度比屏幕宽，则等比缩小，如果窄，则放大
        final Bitmap scaledBitmap = Bitmap.createScaledBitmap(resourceBitmap, outSize.x, bitmapScaledHeight, false);

        bg.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressLint("NewApi")
            @Override
            public void onGlobalLayout() {
                // 当UI绘制完毕，我们对图片进行处理
                int viewHeight = bg.getMeasuredHeight();

                // 计算将要裁剪的图片的顶部以及底部的便宜量
                int offset = (scaledBitmap.getHeight() - viewHeight) / 2;

                // 对图片以中心进行裁剪，裁剪出的图片就是非常适合做引导页的图片了
                Bitmap finallyBitmap = Bitmap.createBitmap(scaledBitmap, 0, offset, scaledBitmap.getWidth(),
                        scaledBitmap.getHeight() - offset * 2);

                // 设置图片显示
                bg.setBackground(new BitmapDrawable(getResources(), finallyBitmap));
            }
        });
    }

    /**
     * token 验证登录
     */
    public void tokenLogin(String token) {
        addSubscription(apiStores.tokenLogin(token),
                new ApiCallback<RegisteredEn>() {
                    @Override
                    public void onSuccess(RegisteredEn model) {
                        if ("200".equals(model.getCode())) {
                            PrefUtils.setString(LoginActivity.this, Constants.PARENT_ID, "");//测试家长id  b368c9f05ba711e7905400163e323696
                            PrefUtils.setString(LoginActivity.this, Constants.USER_ID, model.getData().getId());//测试id
                            PrefUtils.setString(LoginActivity.this, Constants.USER_NAME, model.getData().getMemberName());
                            PrefUtils.setString(LoginActivity.this, Constants.USER_LOGIN_ID, model.getData().getLoginId());
                            PrefUtils.setString(LoginActivity.this, Constants.USER_IMG, model.getData().getImg());
                            PrefUtils.setString(LoginActivity.this, Constants.USER_LEVEL, model.getData().getLevel());
                            PrefUtils.setInt(LoginActivity.this, Constants.USER_SCORE, model.getData().getScore());
                            PrefUtils.setString(LoginActivity.this, Constants.USER_TOKEN, model.getData().getToken());
                            PrefUtils.setString(LoginActivity.this, Constants.USER_TYPE, model.getData().getType() + "");
                            Log.e(TAG, "---------------token: " + model.getData().getToken());
                            PrefUtils.saveObjectToShare(LoginActivity.this, "data", model.getData());
//                                 login(model.getData().getId(),md5);


                            //设置极光推送tag
                            if (PrefUtils.getString(LoginActivity.this, Constants.USER_ID, "") != null) {
                                if (PrefUtils.getBoolean(LoginActivity.this, Constants.USER_PUSH, true)) {
                                    jPushTag();
                                }
                            }
//                            startAc(MainActivity.class);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("islogin", true);
                            startAc(intent);
                            finish();
                        } else {
                            showToas(model.getMessage());
//                            bg.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(String code) {
//                        bg.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }

    //微吼登录
    public void login(String username, String userpass) {
        VhallSDK.getInstance().login(username, userpass, new UserInfoDataSource.UserInfoCallback() {
            @Override
            public void onSuccess(UserInfo userInfo) {
                Toast.makeText(LoginActivity.this, "---" + R.string.login_success, Toast.LENGTH_SHORT).show();
                if (userInfo != null) {
                    Param param = MyApplication.param;
                    param.userAvatar = userInfo.avatar;
                    param.userVhallId = userInfo.user_id;
                    param.userCustomId = userInfo.account;
                    param.userName = userInfo.nick_name;
                    MyApplication.setParam(param);
                }
            }

            @Override
            public void onError(int errorCode, String reason) {
                Toast.makeText(LoginActivity.this, "" + reason + "---", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "errorCode:" + errorCode + " errorReason:" + reason);
            }
        });
//
    }

    private void jPushTag() {
        //极光推送 设置alias别名
        if (JPushInterface.isPushStopped(LoginActivity.this)) {
            JPushInterface.resumePush(LoginActivity.this);
        }

        JPushInterface.setAliasAndTags(getApplicationContext(), PrefUtils.getString(this, Constants.USER_ID, ""), null, new TagAliasCallback() {

            @Override
            public void gotResult(int code, String alias, Set<String> tags) {
                switch (code) {
                    case 0:
                        LogUtils.i(">>>>>>>>>>LoginActivity:Jpush set tag and alias success");
                        break;
                    case 6002:
                        LogUtils.i(">>>>>>>>>>LoginActivity:Jpush failed to set alias and tags due to timeout. Try again after 60s.");
                        break;
                    default:
                        LogUtils.i(">>>>>>>>>>LoginActivity:Jpush failed with errorCode = " + code);
                }
            }

        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//                                        ActivityCollector.finishAll(); //   清除activity任务战

    }
}
