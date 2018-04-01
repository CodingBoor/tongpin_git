package com.qmx163.activity.Me;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.qmx163.R;
import com.qmx163.activity.LoginActivity;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.AppConfig;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.DataCleanUtils;
import com.qmx163.util.DownloadUtil;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.dialog.TBAlertDialog;
import com.qmx163.view.PinchImage.ToggleButton;

import java.io.File;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static com.qmx163.R.id.tv_cache;
import static com.qmx163.activity.Me.MeFrag.REQUEST_MY_DATA;

/**
 * Created by likai on 2017/7/3.
 * 我的设置界面
 */

public class MySettingActivity extends BaseAc {

    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.tv_bg_up)
    TextView mTvUp;
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
    @BindView(R.id.tv_setting)
    TextView mTvSetting;
    @BindView(R.id.bt_push)
    ToggleButton mBtPush;
    @BindView(R.id.tv_updata)
    TextView mTvUpdata;
    @BindView(R.id.tv_about)
    TextView mTvAbout;
    @BindView(tv_cache)
    TextView mTvCache;
    @BindView(R.id.rl_clean)
    RelativeLayout mRlClean;
    @BindView(R.id.tv_logout)
    TextView mTvLogout;
    @BindView(R.id.bt_switch)
    TextView bt_switch;
    ProgressDialog dialog;
    private boolean myStitch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_setting);
        ButterKnife.bind(this);
        mBtPush.setChecked(PrefUtils.getBoolean(MySettingActivity.this, Constants.USER_PUSH, true));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBtPush.setVisibility(View.GONE);
            }
        }, 80);
        mIbtnLeft.setVisibility(View.VISIBLE);
        mRightImg.setVisibility(View.GONE);
        mTvTitle.setText(R.string.my_setting);


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

        try {
            mTvCache.setText(DataCleanUtils.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //推送开关设置
        mBtPush.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(ToggleButton toggleButton, boolean on) {
                if (on) {
                    PrefUtils.setBoolean(MySettingActivity.this, Constants.USER_PUSH, true);
                    jPushTag(PrefUtils.getString(MySettingActivity.this, Constants.USER_ID, ""));
                } else {
                    PrefUtils.setBoolean(MySettingActivity.this, Constants.USER_PUSH, false);
                    jPushTag("");
                }
            }
        });

        if (PrefUtils.getBoolean(MySettingActivity.this, Constants.USER_PUSH, true)) {
            bt_switch.setBackgroundResource(R.mipmap.me_swtich_on);
            myStitch = true;
        } else {
            bt_switch.setBackgroundResource(R.mipmap.me_swtich_off);
            myStitch = false;
        }

        bt_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!myStitch) {
                    myStitch = true;
                    bt_switch.setBackgroundResource(R.mipmap.me_swtich_on);
                    PrefUtils.setBoolean(MySettingActivity.this, Constants.USER_PUSH, true);
                    jPushTag(PrefUtils.getString(MySettingActivity.this, Constants.USER_ID, ""));
                } else {
                    myStitch = false;
                    bt_switch.setBackgroundResource(R.mipmap.me_swtich_off);
                    PrefUtils.setBoolean(MySettingActivity.this, Constants.USER_PUSH, false);
                    jPushTag("");
                }
            }
        });

    }

    @OnClick({R.id.tv_setting, R.id.tv_updata, R.id.tv_about, R.id.rl_clean, R.id.tv_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_setting:
                Intent intent = new Intent(this, UserSettingActivity.class);
                startActivityForResult(intent, REQUEST_MY_DATA);
                break;
            case R.id.tv_updata:
                requestPermission(2, Manifest.permission.READ_EXTERNAL_STORAGE, new Runnable() {
                    @Override
                    public void run() {
//                        requestPermission(3, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS, new Runnable() {
//                            @Override
//                            public void run() {

                        addSubscription(apiStores.getAppConfig(), new ApiCallback<AppConfig>() {
                            @Override
                            public void onSuccess(AppConfig model) {
                                if (TextUtils.equals(model.getCode(), "200")) {
                                    final AppConfig.Config config = model.getData();
                                    String versionName = getAppVersionName(getBaseContext());
                                    try {
                                        int i = compareVersion(config.getAppVersion(), versionName);
                                        if (i > 0) {
                                            new TBAlertDialog(MySettingActivity.this).builder().setTitle("提示")
                                                    .setMsg("发现新版,是否下载？").setPositiveButton("确认", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    dialog = new ProgressDialog(MySettingActivity.this);
                                                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 设置进度条的形式为圆形转动的进度条
                                                    dialog.setCancelable(false);// 设置是否可以通过点击Back键取消
                                                    dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
                                                    // 设置提示的title的图标，默认是没有的，如果没有设置title的话只设置Icon是不会显示图标的
                                                    dialog.setMessage("正在下载最新版。。。");
                                                    dialog.show();
                                                    downloadApk(config.getAppDownload());
                                                }
                                            }).setNegativeButton("取消", new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                }
                                            }).show();
                                        } else {
                                            showToas("已是最新版本");
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
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
//                        }, new Runnable() {
//                            @Override
//                            public void run() {
//                                showToas(getResources().getString(R.string.no_permission_to_use_the_read_external));
//                            }
//                        });
//                    }
                }, new Runnable() {
                    @Override
                    public void run() {
                        showToas(getResources().getString(R.string.no_permission_to_use_the_camera));
                    }
                });


                break;
            case R.id.tv_about:
                startAc(AboutUsActivity.class);
                break;
            case R.id.rl_clean:
                if (mTvCache.getText().equals("0KB")) {
                    showToas("暂无缓存");
                } else {
                    new TBAlertDialog(MySettingActivity.this).builder().setTitle("提示")
                            .setMsg("确定清除缓存？").setPositiveButton("确认", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DataCleanUtils.clearAllCache(MySettingActivity.this);
                            mTvCache.setText("0KB");
                            showToas("清除成功");
                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();

                }


//                        .setPositiveButton("确认", v -> {
//                            DataCleanUtils.clearAllCache(this);
//                            mTvCache.setText("0KB");
//                showToas("清除成功");
//                        }).setNegativeButton("取消", v -> {
//                }).show();

                break;
            case R.id.tv_logout:
                new AlertDialog.Builder(this)
                        .setMessage(R.string.me_check_user)
                        .setNegativeButton(R.string.me_sure, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((BaseAc) MySettingActivity.this).clearActivity();
                                PrefUtils.setString(MySettingActivity.this, Constants.USER_ID, "");
                                PrefUtils.setString(MySettingActivity.this, Constants.USER_TOKEN, "");
//                                PrefUtils.setBoolean(MySettingActivity.this, Constants.USER_PUSH, false);
                                jPushTag("");

                                Intent intent2 = new Intent(MySettingActivity.this, LoginActivity.class);
                                intent2.putExtra("check_user", true);
                                intent2.putExtra("check_out_user", true);
//                                ActivityCollector.finishAll(); //   清除activity任务战
                                startAc(intent2);
                                finish();
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
        }
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        int versioncode;
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    public void downloadApk(String URLStr) {
        DownloadUtil.get().download(URLStr, "download", new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(final File file) {
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(MySettingActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                        Log.e("apkpath", file.getAbsolutePath());
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onDownloading(final int progress) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       /* progressBar.setVisibility(View.VISIBLE);
                        progressBar.setProgress(progress);*/
                    }
                });

            }

            @Override
            public void onDownloadFailed() {
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MySettingActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void jPushTag(String tagId) {
        //极光推送 设置alias别名
        if (JPushInterface.isPushStopped(MySettingActivity.this)) {
            JPushInterface.resumePush(MySettingActivity.this);
        }

        JPushInterface.setAliasAndTags(getApplicationContext(), tagId, null, new TagAliasCallback() {

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
}
