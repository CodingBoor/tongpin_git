package com.qmx163.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.qmx163.R;
import com.qmx163.base.BaseAcNoScroll;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.AppConfig;
import com.qmx163.interpolator.CustomInterpolatorFactory;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.DownloadUtil;
import com.qmx163.util.PrefUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * Created by CX4 on 2017/3/10.
 */

public class SplashAc extends BaseAcNoScroll {

    private ScaleAnimation scaleAnimation;
    @BindView(R.id.image)
    ImageView image;
    int loadPage = 0;
    ArrayList<String> loadList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * 处理部分手机 按下home键之后 点击应用图标进入app时运行第一个activit界面的问题
         */
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(R.layout.ac_splash);
        ButterKnife.bind(this);
//        VhallSDK.init(SplashAc.this, "48e6c98b2a9cfcae09d4d831bf22dc96", "6ff5b14649485c2af03b62e5b40af885");

/**
 * 判断是否为第一次启动
 * 是则启动引导页
 */
        if (PrefUtils.getBoolean(SplashAc.this, Constants.FIRST_ON, true)) {
            PrefUtils.setBoolean(SplashAc.this, Constants.FIRST_ON, false);
//            jPushTag();
            getGuide();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startAc(MainActivity.class);
                    finish();
                }
            }, 1000);
        }

        /*Glide.with(this)
                .load(R.mipmap.start1)
                .into( new GlideDrawableImageViewTarget(image) {
                    @Override
                    public void onResourceReady(GlideDrawable drawable, GlideAnimation anim) {
                        super.onResourceReady(drawable, anim);
                        //在这里添加一些图片加载完成的操作
                     //   initAnim();
                    }
                });*/

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashAc.this, MainActivity.class));
//                finish();
//            }
//
//        }, 300);


    }


    private void jPushTag() {
        //极光推送 设置alias别名
        if (JPushInterface.isPushStopped(SplashAc.this)) {
            JPushInterface.resumePush(SplashAc.this);
        }

        JPushInterface.setAliasAndTags(getApplicationContext(), PrefUtils.getString(this,Constants.USER_ID,""), null, new TagAliasCallback() {

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


    /**
     * 获取引导页
     */
    private void getGuide() {
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
                            if (config.getGuideType() == 1) {  // 下载图片
                                String loadImg = config.getGuidePage();
//                                String loadImg = "http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/182c90574780414b9a7aefc5404b7dfb.png,http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/5b95ae74887a4a1c8992eef6210fa05f.png,http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/6017e157848b45c58c5afb4411c9af19.png";
                                String[] loadArray = loadImg.split(",");
                                for (int i = 0; i < loadArray.length; i++) {
                                    loadList.add("0");
                                }
                                for (int i = 0; i < loadArray.length; i++) {
                                    downloadImg(loadArray[i], i);
                                }
                            } else {  //下载动画
                                downloadVideo(config.getGuidePage());
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
                showToas(getResources().getString(R.string.no_permission_to_use_the_read_external));
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startAc(MainActivity.class);
                            }
                        },1000);
            }
        });
    }


    public void downloadVideo(String URLStr) {
        DownloadUtil.get().download(URLStr, "download", new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(final File file) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //progressBar.setVisibility(View.GONE);
                        Toast.makeText(SplashAc.this, "下载完成", Toast.LENGTH_SHORT).show();
                        Log.e("apkpath", file.getAbsolutePath());
                        Intent intent = new Intent(SplashAc.this, GuideActivity.class);
                        intent.putExtra("video_path", file.getAbsolutePath());
                        startActivity(intent);
                        finish();
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//                        startActivity(intent);
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(MySettingActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public void downloadImg(String URLStr, final int page) {
        DownloadUtil.get().download(URLStr, "download", new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(final File file) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //progressBar.setVisibility(View.GONE);
                        loadList.set(page, file.getAbsolutePath());
                        loadPage++;
                        if (loadPage == loadList.size()) {
//                            Toast.makeText(SplashAc.this, "下载完成", Toast.LENGTH_SHORT).show();
                            Log.e("apkpath", file.getAbsolutePath());
                            Intent intent = new Intent(SplashAc.this, GuideActivity.class);
                            intent.putStringArrayListExtra("guide_list", loadList);
//                            intent.putExtra("guide_img", file.getAbsolutePath());
                            startActivity(intent);
                            finish();
                        }
//                        Log.e("apkpath", file.getAbsolutePath());
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//                        startActivity(intent);
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(MySettingActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void initAnim() {
        scaleAnimation = new ScaleAnimation(0.8f, 1.1f, 0.8f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(300);
        //scaleAnimation.setInterpolator(CustomInterpolatorFactory.getAnticipateInterpolator());
        //scaleAnimation.setInterpolator(CustomInterpolatorFactory.getAnticipateOverShootInterpolator());
        //scaleAnimation.setInterpolator(CustomInterpolatorFactory.getJellyInterpolator());
        scaleAnimation.setInterpolator(CustomInterpolatorFactory.getOverShootInterpolator());
        //缩放动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(SplashAc.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        image.setAnimation(scaleAnimation);
        scaleAnimation.start();
    }
}
