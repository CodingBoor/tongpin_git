package com.qmx163.activity.watch;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnFullscreenlistener;
import com.dou361.ijkplayer.listener.OnPlayClickListener;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.qmx163.R;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.listener.LivePlayViewListener;
import com.qmx163.listener.PlayerScreenChangeListener;
import com.qmx163.util.MediaUtils;
import com.qmx163.util.ToastUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 课程介绍
 */

public class IntroductionFragment extends Fragment {
    //    @BindView(R.id.webview)
//    WebView webview;
    @BindView(R.id.iv_intro)
    ImageView mIvIntro;
    @BindView(R.id.scroll_view)
    ScrollView mScrollView;
    //    @BindView(R.id.web_content)
//    public static WebView webContent;
    public static RelativeLayout rlWebContent;
    WebView mWebView;
    @BindView(R.id.iv_play_start)
    ImageView ivPlayStart;
    lessonPeriodsDetail.DataBean lessonDetas;

    public static PlayerView player;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;

    /**
     * 屏幕切换监听
     *
     * @param playerScreenChangeListener
     */
    public void setPlayerScreenChangeListener(PlayerScreenChangeListener playerScreenChangeListener) {
        mPlayerScreenChangeListener = playerScreenChangeListener;
    }

    PlayerScreenChangeListener mPlayerScreenChangeListener;

    public static IntroductionFragment newInstance(lessonPeriodsDetail.DataBean lessonDeta) {
        IntroductionFragment articleFragment = new IntroductionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lessonDeta", lessonDeta);
        articleFragment.setArguments(bundle);
        return articleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.int_fragment, container, false);
//        webContent = (WebView) view.findViewById(R.id.web_content);
        rlWebContent = (RelativeLayout) view.findViewById(R.id.ll_web_context);
        ButterKnife.bind(this, view);

        ((WatchActivity) getActivity()).setLivePlayViewListener(new LivePlayViewListener() {
            @Override
            public void witchType(int type) {
                player.pausePlay();
            }
        });




//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WebView.setWebContentsDebuggingEnabled(true);
//        }
        lessonDetas = getArguments().getParcelable("lessonDeta");


        mWebView = new WebView(getActivity());
        rlWebContent.addView(mWebView);

        mWebView.loadDataWithBaseURL(null, lessonDetas.getLesson().getContent(), "text/html", "UTF-8", null);
        Glide.with(this).load(lessonDetas.getLesson().getIntroVideoImg()).centerCrop().dontAnimate().error(R.mipmap.flunk).crossFade().into(mIvIntro);
        if (lessonDetas.getLesson().getIntroVideo().equals("") || lessonDetas.getLesson().getIntroVideo() == null) {
            ivPlayStart.setVisibility(View.GONE);
        } else {
            ivPlayStart.setVisibility(View.VISIBLE);
        }


        /**虚拟按键的隐藏方法*/
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                //比较Activity根布局与当前布局的大小
                int heightDiff = view.getRootView().getHeight() - view.getHeight();
                if (heightDiff > 100) {
                    //大小超过100时，一般为显示虚拟键盘事件
                    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                } else {
                    //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
                    view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                }
            }
        });

        /**常亮*/
        PowerManager pm = (PowerManager) getActivity().getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();
        list = new ArrayList<VideoijkBean>();
        //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
        //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
        String url1 = getLocalVideoPath("my_video.mp4");
        if (!new File(url1).exists()) {
            url1 = lessonDetas.getLesson().getIntroVideo();
        }
        VideoijkBean m1 = new VideoijkBean();
        m1.setStream("标清");
        m1.setUrl(url1);
        list.add(m1);

        player = new PlayerView(getActivity(), view) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                hideSteam(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
            }

            @Override
            public PlayerView setPlaySource(List<VideoijkBean> list) {
                return super.setPlaySource(list);
            }
        };
        player.setForbidDoulbeUp(true);

        ivPlayStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WatchActivity) getActivity()).setPlayType(2);
                if (lessonDetas.getLesson().getIntroVideo().equals("")) {
                    ToastUtil.toastShortShow(getActivity(), "视频格式不正确");
                } else {
                    mIvIntro.setVisibility(View.GONE);
                    ivPlayStart.setVisibility(View.GONE);

                    player.setTitle("")
                            .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                            .setScaleType(PlayStateParams.fillparent)
                            .forbidTouch(false)
                            .hideSteam(true)
                            .hideCenterPlayer(true)
                            .showThumbnail(new OnShowThumbnailListener() {
                                @Override
                                public void onShowThumbnail(ImageView ivThumbnail) {
//                        Glide.with(getActivity())
//                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
//                                .placeholder(R.color.red_gray)
//                                .error(R.color.red_gray)
//                                .into(ivThumbnail);
                                }
                            })
                            .setPlaySource(list)
                            .setChargeTie(true, 60).startPlay();


                }
            }
        });

        player.setOnPlayClickListener(new OnPlayClickListener() {
            @Override
            public void isPlaying(boolean isPlaying) {
                if (isPlaying){
                    ((WatchActivity)getActivity()).setPlayType(2);
                }
            }
        });

        player.setOnFullscreenlistener(new OnFullscreenlistener() {
            @Override
            public void isFullscreen(boolean isscreen) {
                if (isscreen) {
                    mPlayerScreenChangeListener.isFullScreen(isscreen);
                    rlWebContent.setVisibility(View.VISIBLE);
//                    ToastUtil.toastShortShow(getActivity(), "全转半");
                } else {
                    mPlayerScreenChangeListener.isFullScreen(isscreen);
                    rlWebContent.setVisibility(View.GONE);
//                    ToastUtil.toastShortShow(getActivity(), "半");

                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 播放本地视频
     */

    private String getLocalVideoPath(String name) {
        String sdCard = Environment.getExternalStorageDirectory().getPath();
        String uri = sdCard + File.separator + name;
        return uri;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(getActivity(), true);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(getActivity(), false);
        /**demo的内容，激活设备常亮状态*/
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mWebView != null) {
            mWebView.setVisibility(View.GONE);
            mWebView.removeAllViews();
            mWebView.destroy();
        }

        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }
}