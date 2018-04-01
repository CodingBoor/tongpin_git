package com.qmx163.activity.watch;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.widget.PlayerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qmx163.R;
import com.qmx163.activity.FocusOn.TeacherDetailActivity;
import com.qmx163.activity.MainActivity;
import com.qmx163.activity.watch.chat.ChatFragment;
import com.qmx163.activity.watch.chat.ChatLiveFragment;
import com.qmx163.aidl.IBackService;
import com.qmx163.application.MyApplication;
import com.qmx163.base.BaseAcNoScroll;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.data.bean.Mebean.WatchPlayBackEn;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.listener.ChatFragmentListener;
import com.qmx163.listener.LiveLockListener;
import com.qmx163.listener.LivePlayListener;
import com.qmx163.listener.LivePlayViewListener;
import com.qmx163.listener.LiveWitchPlayListener;
import com.qmx163.listener.PlaybackHomeKeyListener;
import com.qmx163.listener.PlayerScreenChangeListener;
import com.qmx163.net.ApiCallback;
import com.qmx163.popuwindow.SharePopupWindow;
import com.qmx163.service.BackService;
import com.qmx163.util.ActivityCollector;
import com.qmx163.util.AdvancedCountdownTimer;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.dialog.CirclePopuwindow;
import com.vhall.business.MessageServer;
import com.vhall.business.data.Survey;
import com.vhall.uilibs.util.ActivityUtils;
import com.vhall.uilibs.util.ShowLotteryDialog;
import com.vhall.uilibs.util.SignInDialog;
import com.vhall.uilibs.util.SurveyPopu;
import com.vhall.uilibs.util.VhallUtil;
import com.vhall.uilibs.util.emoji.InputUser;
import com.vhall.uilibs.util.emoji.InputView;
import com.vhall.uilibs.util.emoji.KeyBoardManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;


/**
 * 观看页的Activity
 */
public class WatchActivity extends BaseAcNoScroll implements WatchContract.WatchView, PlatformActionListener {

    @BindView(R.id.iv_follow)
    ImageView mIvFollow;
    @BindView(R.id.ll_follow)
    LinearLayout mLlFollow;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    @BindView(R.id.tv_branch)
    TextView mTvBranch;
    @BindView(R.id.tv_branch_break)
    TextView mTvBranchBreak;
    @BindView(R.id.tv_course_sise)
    TextView mTvCourseSise;
    @BindView(R.id.ll_teacher_detail)
    LinearLayout teachDetail;
    @BindView(R.id.tv_text1)
    TextView text1;
    @BindView(R.id.tv_text2)
    TextView text2;
    @BindView(R.id.chatFrame)
    FrameLayout chatFrame;
    @BindView(R.id.iv_message_show)
    ImageView ivMessageShow;
    @BindView(R.id.contentVideo)
    FrameLayout contentVideo;
    @BindView(R.id.tv_amount)
    TextView tv_amount;
    @BindView(R.id.rl_live)
    RelativeLayout mRlLive;
    @BindView(R.id.rl_share)
    RelativeLayout mRlShare;
    @BindView(R.id.ll_teacher)
    LinearLayout llTeacher;
    @BindView(R.id.tv_bg_up)
    TextView mBgUp;

    CirclePopuwindow circlePopuwindow;

    private FrameLayout contentDoc, contentDetail, contentChat, contentQuestion;
    private RadioGroup radio_tabs;
    private RadioButton questionBtn, chatBtn;
    private LinearLayout ll_detail;
    private Param param;
    private int type;
    public WatchPlaybackFragment playbackFragment;
    public WatchLiveFragment liveFragment;  //观看直播界面fragment
    public IntroductionFragment introductionFragment;
    public ChatFragment questionFragment;
    private lessonPeriodsDetail.DataBean lessonDeta;
    public int chatEvent = ChatLiveFragment.CHAT_EVENT_CHAT;
    private SurveyPopu popu;
    private SignInDialog signInDialog;
    private ShowLotteryDialog lotteryDialog;
    private SharePopupWindow mSharePopupWindow;
    private boolean followClick = false; //收藏状态
    private String jPushBizId = "";
    private boolean isLiving = false; // 是否详情页跳转过来

    private ImageView ico_img, img_Go;
    private TextView teaTelTxt, teaName, teaDz;
    public static TextView teaSzie;
    private boolean isBack = false;//是否拦截fan

    private String vhLiveId = "";
    private long liveTime = 0;

    private IBackService mIBackService;
//    private Intent mServiceIntent;

    public static PlayerView player;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;

    private List backWatchList = new ArrayList(); //回拨课程列表
    public static List<WatchPlayBackEn> watchBackList = new ArrayList<>(); //回播数据集合

    private PlaybackHomeKeyListener mPlaybackHomeKeyListener;

    public static boolean isHorScreen = false;//播放器是否横屏的静态常量
    WatchContract.WatchPresenter mPresenter;

    ChatLiveFragment mChatLiveFragment; //消息fragment
    InputView inputView; //消息发送栏

    private boolean backLock = false;

    ChatFragmentListener mChatFragmentListener;

    LiveWitchPlayListener liveWitchPlayListener;
    LivePlayViewListener livePlayViewListener;

    public void setLivePlayViewListener(LivePlayViewListener livePlayViewListener) {
        this.livePlayViewListener = livePlayViewListener;
    }

    public void setLiveWitchPlayListener(LiveWitchPlayListener liveWitchPlayListener) {
        this.liveWitchPlayListener = liveWitchPlayListener;
    }

    public void setChatFragmentListener(ChatFragmentListener chatFragmentListener) {
        mChatFragmentListener = chatFragmentListener;
    }

    public void setPlaybackHomeKeyListener(PlaybackHomeKeyListener playbackHomeKeyListener) {
        mPlaybackHomeKeyListener = playbackHomeKeyListener;
    }

    AdvancedCountdownTimer mAdvancedCountdownTimer = new AdvancedCountdownTimer(100000000, 1000) {
        @Override
        public void onTick(long millisUntilFinished, int percent) {
            liveTime = millisUntilFinished;
            Log.e("++++", "onTick: " + millisUntilFinished + "--" + percent);
        }

        @Override
        public void onFinish() {

        }
    };

    /**
     * 监听home键广播
     */
    private BroadcastReceiver homeKeyReceiver;


    public void setPlayType(int who){
        if (who == 1){
            livePlayViewListener.witchType(who);
        }else if (who == 2){
        liveWitchPlayListener.witchType(who);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
        setContentView(R.layout.watch_activity);
        ButterKnife.bind(this);
        registerReceiver();


        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) mBgUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mBgUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        jPushBizId = getIntent().getStringExtra("biz_id");
        if (jPushBizId != null && jPushBizId != "") {
            getDate(jPushBizId);
        } else {
            shareClick();//点击分享
//            NettyClient hetty = new NettyClient();
            param = (Param) getIntent().getSerializableExtra("param");
            isLiving = getIntent().getBooleanExtra("living", false);
            type = getIntent().getIntExtra("type", VhallUtil.WATCH_LIVE);
            lessonDeta = (lessonPeriodsDetail.DataBean) getIntent().getParcelableExtra("lessonDeta");


            if (lessonDeta.getStatus() == 5) {

                //home键监听广播
                homeKeyReceiver = new BroadcastReceiver() {
                    String SYSTEM_REASON = "reason";
                    String SYSTEM_HOME_KEY = "homekey";
                    String SYSTEM_HOME_KEY_LONG = "recentapps";

                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String action = intent.getAction();
                        if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                            String reason = intent.getStringExtra(SYSTEM_REASON);
                            if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                                //表示按了home键,程序到了后台
                                if (playbackFragment != null) {
                                    mPlaybackHomeKeyListener.keyDown(true);
                                }
                            } else if (TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)) {
                                //表示长按home键,显示最近使用的程序列表
                                if (playbackFragment != null) {
                                    mPlaybackHomeKeyListener.keyDown(true);
                                }
                            }
                        }
                    }
                };
                registerReceiver(homeKeyReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));


                tv_amount.setText("回看 " + lessonDeta.getPlaybackAmount() + "次");
            } else {

                tv_amount.setText("播放 " + lessonDeta.getAmount() + "次");
            }
            /**
             * socket
             */
//            socketLine();

            initViewTP();
//            NettyClient nettyClient = new NettyClient();
            liveFragment = (WatchLiveFragment) getSupportFragmentManager().findFragmentById(R.id.contentVideo);

            playbackFragment = (WatchPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.contentVideo);
            //课程介绍
            introductionFragment = (IntroductionFragment) getSupportFragmentManager().findFragmentById(R.id.contentChat);
            //系列课程
            DocumentFragment docFragment = (DocumentFragment) getSupportFragmentManager().findFragmentById(R.id.contentDoc);
            //课程课件
            DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentDetail);
            //学生提问
            questionFragment = (ChatFragment) getSupportFragmentManager().findFragmentById(R.id.contentQuestion);
            //聊天的fragment
            mChatLiveFragment = (ChatLiveFragment) getSupportFragmentManager().findFragmentById(R.id.chatFrame);
            initView();


            if (mChatLiveFragment == null) {
                mChatLiveFragment = ChatLiveFragment.newInstance(VhallUtil.WATCH_LIVE, false);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        mChatLiveFragment, R.id.chatFrame);
            }

            if (introductionFragment == null) {
                introductionFragment = IntroductionFragment.newInstance(lessonDeta);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        introductionFragment, R.id.contentChat);

//            introductionFragment.player.setOnFullscreenlistener(new OnFullscreenlistener() {
//                @Override
//                public void isFullscreen(boolean isscreen) {
//                    if (isscreen){
//                        llTeacher.setVisibility(View.VISIBLE);
//                        mRlLive.setVisibility(View.VISIBLE);
//                        mRlShare.setVisibility(View.VISIBLE);
//                    }else {
//                        llTeacher.setVisibility(View.GONE);
//                        mRlLive.setVisibility(View.GONE);
//                        mRlShare.setVisibility(View.GONE);
//                    }
//                }
//            });
            }
            if (docFragment == null) {
                docFragment = DocumentFragment.newInstance(lessonDeta);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        docFragment, R.id.contentDoc);
            }

//        if (questionFragment == null && type == VhallUtil.WATCH_LIVE) {  //原始代码
            if (questionFragment == null) {
                questionFragment = ChatFragment.newInstance(type, lessonDeta);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        questionFragment, R.id.contentQuestion);
            }

            if (detailFragment == null) {
                detailFragment = DetailFragment.newInstance(lessonDeta);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        detailFragment, R.id.contentDetail);
            }


            //观看直播
            if (liveFragment == null && type == VhallUtil.WATCH_LIVE) {
                    mAdvancedCountdownTimer.start();
                //进入界面就传播放参数给服务器
//                socketLineStart();
                Log.e("watchactivity------", "createlive: ");


                liveFragment = WatchLiveFragment.newInstance(lessonDeta);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        liveFragment, R.id.contentVideo);
                new WatchLivePresenter(liveFragment, mChatLiveFragment, mChatLiveFragment, this, param);

                liveFragment.setOnSendClickListener(new WatchLiveFragment.SendMsgClickListener() {
                    @Override
                    public void onSendClick(String msg, InputUser user) {
                        if (mChatLiveFragment != null && chatEvent == ChatLiveFragment.CHAT_EVENT_CHAT) {
                            mChatLiveFragment.performSend(PrefUtils.getString(WatchActivity.this, Constants.USER_NAME, "") + ":" + msg, chatEvent);
                        }
                    }
                });

                liveFragment.setLiveLockListener(new LiveLockListener() {
                    @Override
                    public void isLock(boolean lock) {
                        if (chatFrame.getVisibility() == View.VISIBLE) {
                            chatFrame.setVisibility(View.GONE);
                            ivMessageShow.setVisibility(View.VISIBLE);
                        } else {
                            mChatFragmentListener.notShow(true);
                        }
                    }

                    @Override
                    public void LockClick(boolean click) {
                        if (click) {
                            ivMessageShow.setClickable(false);
                            backLock = true;
                        } else {
                            ivMessageShow.setClickable(true);
                            backLock = false;
                        }
                    }

                    @Override
                    public void giftSend(boolean send) {
                        chatFrame.setVisibility(View.VISIBLE);
                        ivMessageShow.setVisibility(View.GONE);
                    }
                });

                liveFragment.setLivePlayListener(new LivePlayListener() {
                    @Override
                    public void liveStart() {
                        socketLine();
                    }

                    @Override
                    public void livePause() {
                        socketLinePause();
                    }
                });
            }


//         直播回放
            if (playbackFragment == null && type == VhallUtil.WATCH_PLAYBACK) {

                //进入界面就传播放参数给服务器
//                socketLineStart();


//                playbackFragment = WatchPlaybackFragment.newInstance();
                playbackFragment = WatchPlaybackFragment.newInstance(lessonDeta);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        playbackFragment, R.id.contentVideo);
                new WatchPlaybackPresenter(playbackFragment, mChatLiveFragment, this, param);


                if (lessonDeta.getWebinarRecords() != null) {
                    Gson gson = new Gson();
                    watchBackList = gson.fromJson(lessonDeta.getWebinarRecords(), new TypeToken<List<WatchPlayBackEn>>() {
                    }.getType());
                    for (int i = 0; i < watchBackList.size(); i++) {
                        if (watchBackList.get(i).getStatus() == 1) {
                            backWatchList.add(watchBackList.get(i).getSubject());
                        }
                    }
                    if (backWatchList.size()>0){
                        mAdvancedCountdownTimer.start();
                    }

//            param.setRecordId(watchBackList.get(0).getId() + "");
                }

                playbackFragment.setLivePlayListener(new LivePlayListener() {
                    @Override
                    public void liveStart() {
                        socketLine();//回看暂时不传
                    }

                    @Override
                    public void livePause() {
                        socketLinePause();
                    }
                });

            }
            //课程详情播放监听
            introductionFragment.setPlayerScreenChangeListener(new PlayerScreenChangeListener() {
                @Override
                public void isFullScreen(boolean full) {
                    if (full) {
                        mBgUp.setVisibility(View.VISIBLE);
                        llTeacher.setVisibility(View.VISIBLE);
                        mRlLive.setVisibility(View.VISIBLE);
                        mRlShare.setVisibility(View.VISIBLE);
                    } else {
                        mBgUp.setVisibility(View.GONE);
                        llTeacher.setVisibility(View.GONE);
                        if (introductionFragment.rlWebContent.getVisibility() != View.VISIBLE) {

                            mRlLive.setVisibility(View.GONE);
                        }
                        mRlShare.setVisibility(View.GONE);
                    }
                }
            });


        }


    }

    private void socketLineStart() {
        String sendMessage2 = Constants.socketLogin(lessonDeta.getLessonId(), lessonDeta.getId(), PrefUtils.getString(getActivity(), Constants.USER_ID, ""));
        try {
            Log.i("---------", "登录发送消息：" + sendMessage2);
            if (MainActivity.mIBackService == null) {
                Toast.makeText(getActivity(),
                        "socket连接失败", Toast.LENGTH_SHORT).show();
            } else {
                boolean isSend = MainActivity.mIBackService.sendMessage(sendMessage2);
//                        Toast.makeText(getActivity(),
//                                isSend ? "success" : "fail", Toast.LENGTH_SHORT)
//                                .show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    /**
     * 播放本地视频
     */

    private String getLocalVideoPath(String name) {
        String sdCard = Environment.getExternalStorageDirectory().getPath();
        String uri = sdCard + File.separator + name;
        return uri;
    }


    /**
     * socket长连接
     */
    private void socketLine() {

        socketLineStart();
//        mServiceIntent = new Intent(this, BackService.class);
        /**
         * 连接成功发送socket登录信息
         */
        String sendMessage = Constants.socketPlay(lessonDeta.getLessonId(), lessonDeta.getId(), PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonDeta.getWebinarId() + "");
        try {
            if (MainActivity.mIBackService == null) {
                Toast.makeText(getActivity(),
                        "socket连接失败", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("---------", "直播间发送开播消息：" + sendMessage);
                boolean isSend = MainActivity.mIBackService.sendMessage(sendMessage);
//                        Toast.makeText(getActivity(),
//                                isSend ? "success" : "fail", Toast.LENGTH_SHORT)
//                                .show();
                if (isSend) {
                    if (mAdvancedCountdownTimer != null) {
                        mAdvancedCountdownTimer.resume();//播放时间定时器
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    /**
     * socket长连接播放暂停
     */
    private void socketLinePause() {
//        mServiceIntent = new Intent(this, BackService.class);
        /**
         * 连接成功发送socket登录信息
         */
        if (TextUtils.equals("", vhLiveId)) {
            return;
        }
        String sendMessage = Constants.socketPlayPause(vhLiveId);
        try {
            if (MainActivity.mIBackService == null) {
                Toast.makeText(getActivity(),
                        "socket连接失败", Toast.LENGTH_SHORT).show();
            } else {
                Log.i("---------", "直播间发送暂停消息：" + sendMessage);
                boolean isSend = MainActivity.mIBackService.sendMessage(sendMessage);
//                        Toast.makeText(getActivity(),
//                                isSend ? "success" : "fail", Toast.LENGTH_SHORT)
//                                .show();
//                vhLiveId = "";// 由于服务器不是每次都传这个id 所以暂时注销掉
                if (isSend) {
                    if (mAdvancedCountdownTimer != null) {

                        mAdvancedCountdownTimer.pause();
                    }
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }


    private void shareClick() {
        mSharePopupWindow = new SharePopupWindow(this, MyApplication.getInstance().getmWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
        mSharePopupWindow.setListener(new SharePopupWindow.ShareViewListener() {
            @Override
            public void onFriendCircle() {
                QQ.ShareParams qqShare = new QQ.ShareParams();
                qqShare.setTitle("我是分享qq");
                qqShare.setTitleUrl(PrefUtils.getString(WatchActivity.this, Constants.USER_ID, "")); // 标题的超链接
                qqShare.setText("测试分享");
                qqShare.setImageUrl(PrefUtils.getString(WatchActivity.this, Constants.USER_ID, ""));//分享网络图片
                Platform zone = ShareSDK.getPlatform(QQ.NAME);
                if (zone.isClientValid()) {
                    System.out.println("安装了qq");
                    zone.setPlatformActionListener(WatchActivity.this); // 设置分享事件回调
                    // 执行分享
                    zone.share(qqShare);
                } else {
                    showToas("您没有安装QQ");
                }

            }

            @Override
            public void onFriend() {
                showToas("分享微信");

            }

            @Override
            public void onWXCircle() {
                showToas("分享微信朋友圈");

            }

            @Override
            public void onWXFriend() {
                showToas("分享新浪微博");

            }
        });
    }


    public void initViewTP() {
//        teaTelImg = (ImageView) findViewById(teaTelImg);
        ico_img = (ImageView) findViewById(R.id.ico_img);
        teaTelTxt = (TextView) findViewById(R.id.teaTelTxt);
        teaName = (TextView) findViewById(R.id.teaName);
        teaDz = (TextView) findViewById(R.id.teaDz);
        teaSzie = (TextView) findViewById(R.id.teaSzie);
//        Glide.with(this).load(lessonDeta.getLesson().getIntroVideoImg()).centerCrop().placeholder(R.mipmap.loding).error(R.mipmap.flunk).crossFade().into((ImageView) teaTelImg);
        teaTelTxt.setText(lessonDeta.getLesson().getName());
        Glide.with(this).load(lessonDeta.getLesson().getImg()).centerCrop().dontAnimate().error(R.mipmap.flunk).crossFade().into((ImageView) ico_img);
        teaName.setText(lessonDeta.getLesson().getTeacherName());
        if (TextUtils.equals(lessonDeta.getLesson().getTeacherOrganization(), null)) {
            teaDz.setVisibility(View.GONE);
        } else {
            teaDz.setVisibility(View.VISIBLE);
        }
        teaDz.setText(lessonDeta.getLesson().getTeacherOrganization() + "");
        teaSzie.setText(lessonDeta.getLesson().getConcernAmount() + "");
        mTvCourseSise.setText(lessonDeta.getLesson().getTotalLessonPeriods() + "");
        mTvBranch.setText(lessonDeta.getLesson().getSubjectName());
        mTvBranchBreak.setText(lessonDeta.getLesson().getSubjectName());

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (inputView != null) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN && inputView.getContentView().getVisibility() == View.VISIBLE) {
                boolean isDismiss = isShouldHideInput(inputView.getContentView(), ev);
                if (isDismiss) {
                    inputView.dismiss();
                    return false;
                } else {
                    return super.dispatchTouchEvent(ev);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isShouldHideInput(View view, MotionEvent event) {
        if (view.getVisibility() == View.GONE)
            return false;
        int[] leftTop = {0, 0};
        //获取输入框当前的location位置
        inputView.getContentView().getLocationInWindow(leftTop);
        int left = leftTop[0];
        int top = leftTop[1];
        int bottom = top + inputView.getContentView().getHeight();
        int right = left + inputView.getContentView().getWidth();
        return !(event.getX() > left && event.getX() < right
                && event.getY() > top && event.getY() < bottom);
    }

    private void initView() {
        if (lessonDeta.getLesson().getConcern() == 1) {
            followClick = true;
            mIvFollow.setImageResource(R.mipmap.live_follow);
        } else {
            followClick = false;
            mIvFollow.setImageResource(R.mipmap.live_follow_no);
        }


//        inputView = new InputView(this, KeyBoardManager.getKeyboardHeight(this), KeyBoardManager.getKeyboardHeightLandspace(this));
//        inputView.add2Window(this);
//        inputView.setClickCallback(new InputView.ClickCallback() {
//            @Override
//            public void onEmojiClick() {
//
//            }
//        });
//        inputView.setOnSendClickListener(new InputView.SendMsgClickListener() {
//            @Override
//            public void onSendClick(String msg, InputUser user) {
//                if (mChatLiveFragment != null && chatEvent == ChatLiveFragment.CHAT_EVENT_CHAT) {
//                    mChatLiveFragment.performSend(PrefUtils.getString(WatchActivity.this, Constants.USER_NAME, "") + ":" + msg, chatEvent);
//                }
//            }
//        });
//
//        inputView.setInputBacklistener(new InputView.InputBacklistener() {
//            @Override
//            public void inputBack() {
//                if (inputView != null) {
//                    inputView.dismiss();
//                    isBack = true;
//                }
//            }
//        });
//
//        inputView.setOnHeightReceivedListener(new InputView.KeyboardHeightListener() {
//            @Override
//            public void onHeightReceived(int screenOri, int height) {
//                if (screenOri == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    KeyBoardManager.setKeyboardHeight(WatchActivity.this, height);
//                } else {
//
//                    KeyBoardManager.setKeyboardHeightLandspace(WatchActivity.this, height);
//                }
//            }
//        });

//        inputView.setOnHeightReceivedListener(new InputView.KeyboardHeightListener() {
//            @Override
//            public void onHeightReceived(int screenOri, int height) {
//                if (screenOri == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
//                    KeyBoardManager.setKeyboardHeight(WatchActivity.this, height);
//                } else {
//                    KeyBoardManager.setKeyboardHeightLandspace(WatchActivity.this, height);
//                }
//            }
//        });
        ll_detail = (LinearLayout) this.findViewById(R.id.ll_detail);
        contentDoc = (FrameLayout) findViewById(R.id.contentDoc);
        contentDetail = (FrameLayout) findViewById(R.id.contentDetail);
        contentChat = (FrameLayout) findViewById(R.id.contentChat);
        contentQuestion = (FrameLayout) findViewById(R.id.contentQuestion);

        questionBtn = (RadioButton) this.findViewById(R.id.rb_question);
        chatBtn = (RadioButton) this.findViewById(R.id.rb_chat);

//        tv_notice = (ExtendTextView) this.findViewById(R.id.tv_notice);
//        tv_notice.setDrawableClickListener(new ExtendTextView.DrawableClickListener() {
//            @Override
//            public void onDrawableClick(int position) {
//                switch (position) {
//                    case ExtendTextView.DRAWABLE_RIGHT:
//                        dismissNotice();
//                        break;
//                }
//            }
//        });
//        if (type == VhallUtil.WATCH_LIVE) {
//            questionBtn.setVisibility(View.VISIBLE);
//            contentChat.setVisibility(View.VISIBLE);
//            chatBtn.setText("聊天");
//        }
//        if (type == VhallUtil.WATCH_PLAYBACK) {
//            chatBtn.setText("评论");
//            contentChat.setVisibility(View.VISIBLE);
//        }
        radio_tabs = (RadioGroup) findViewById(R.id.radio_tabs);
        initTab();
        radio_tabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_chat) {
                    contentChat.setVisibility(View.VISIBLE);
                    contentDoc.setVisibility(View.GONE);
                    contentDetail.setVisibility(View.GONE);
                    contentQuestion.setVisibility(View.GONE);
                } else if (checkedId == R.id.rb_doc) {
                    contentDoc.setVisibility(View.GONE);
                    contentChat.setVisibility(View.GONE);
                    contentQuestion.setVisibility(View.GONE);
                    contentDetail.setVisibility(View.VISIBLE);
                } else if (checkedId == R.id.rb_question) {
                    contentDoc.setVisibility(View.GONE);
                    contentDetail.setVisibility(View.GONE);
                    contentQuestion.setVisibility(View.VISIBLE);
                    contentChat.setVisibility(View.GONE);
                } else if (checkedId == R.id.rb_detail) {
                    contentDoc.setVisibility(View.VISIBLE);
                    contentChat.setVisibility(View.GONE);
                    contentDetail.setVisibility(View.GONE);
                    contentQuestion.setVisibility(View.GONE);
                }
            }
        });
    }

    public void initTab() {
        contentChat.setVisibility(View.VISIBLE);
        contentDoc.setVisibility(View.GONE);
        contentDetail.setVisibility(View.GONE);
        contentQuestion.setVisibility(View.GONE);
    }

    public void showChatView(boolean isShowEmoji, InputUser user, int contentLengthLimit) {
        if (contentLengthLimit > 0)
            inputView.setLimitNo(contentLengthLimit);
        inputView.show(isShowEmoji, user);
    }

    @Override
    public void showSignIn(String signId, int startTime) {
        if (signInDialog == null) {
            signInDialog = new SignInDialog(this);
        }
        signInDialog.setSignInId(signId);
        signInDialog.setCountDownTime(startTime);
        signInDialog.setOnSignInClickListener(new SignInDialog.OnSignInClickListener() {
            @Override
            public void signIn(String signId) {
                mPresenter.signIn(signId);
            }
        });
        signInDialog.show();
    }

    @Override
    public void dismissSignIn() {
        if (signInDialog != null)
            signInDialog.dismiss();
    }

    @Override
    public void showSurvey(Survey survey) {
        if (popu == null) {
            popu = new SurveyPopu(this);
            popu.setOnSubmitClickListener(new SurveyPopu.OnSubmitClickListener() {
                @Override
                public void onSubmitClick(Survey survey1, String result) {
                    mPresenter.submitSurvey(survey1, result);
                }
            });
        }
        popu.setSurvey(survey);
        popu.showAtLocation(getWindow().getDecorView().findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, 0);
    }

    @Override
    public void dismissSurvey() {
        if (popu != null)
            popu.dismiss();
    }


    /**
     * 横竖屏切换监听
     *
     * @return
     */
    @Override
    public int changeOrientation() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            isHorScreen = true;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    inputView = new InputView(WatchActivity.this, KeyBoardManager.getKeyboardHeight(WatchActivity.this), KeyBoardManager.getKeyboardHeightLandspace(WatchActivity.this));
                    inputView.add2Window(WatchActivity.this);
                    inputView.setClickCallback(new InputView.ClickCallback() {
                        @Override
                        public void onEmojiClick() {

                        }
                    });
                    inputView.setOnSendClickListener(new InputView.SendMsgClickListener() {
                        @Override
                        public void onSendClick(String msg, InputUser user) {
                            if (mChatLiveFragment != null && chatEvent == ChatLiveFragment.CHAT_EVENT_CHAT) {
                                mChatLiveFragment.performSend(PrefUtils.getString(WatchActivity.this, Constants.USER_NAME, "") + ":" + msg, chatEvent);
                            }
                        }
                    });

                    inputView.setInputBacklistener(new InputView.InputBacklistener() {
                        @Override
                        public void inputBack() {
                            if (inputView != null) {
                                inputView.dismiss();
                                isBack = true;
                            }
                        }
                    });

                    inputView.setOnHeightReceivedListener(new InputView.KeyboardHeightListener() {
                        @Override
                        public void onHeightReceived(int screenOri, int height) {
                            if (screenOri == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                                KeyBoardManager.setKeyboardHeight(WatchActivity.this, height);
                            } else {

                                KeyBoardManager.setKeyboardHeightLandspace(WatchActivity.this, height);
                            }
                        }
                    });
                }
            }, 1000);


            if (type == VhallUtil.WATCH_LIVE) {
                if (followClick) {
                    WatchLiveFragment.followClick = true;
                    WatchLiveFragment.iv_follow.setImageResource(R.mipmap.live_follow);
                } else {
                    WatchLiveFragment.followClick = false;
                    WatchLiveFragment.iv_follow.setImageResource(R.mipmap.live_follow_no);
                }
                ivMessageShow.setVisibility(View.VISIBLE);

            } else if (type == VhallUtil.WATCH_PLAYBACK) {
                if (followClick) {
                    WatchPlaybackFragment.followClick = true;
                    WatchPlaybackFragment.iv_follow.setImageResource(R.mipmap.live_follow);
                } else {
                    WatchPlaybackFragment.followClick = false;
                    WatchPlaybackFragment.iv_follow.setImageResource(R.mipmap.live_follow_no);
                }
            }
            ll_detail.setVisibility(View.GONE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            isHorScreen = false;
            if (inputView != null) {
                inputView.dismiss();
            }
            if (type == VhallUtil.WATCH_LIVE) {

                if (WatchLiveFragment.followClick) {
                    followClick = true;
                    mIvFollow.setImageResource(R.mipmap.live_follow);
                } else {
                    followClick = false;
                    mIvFollow.setImageResource(R.mipmap.live_follow_no);
                }

                ivMessageShow.setVisibility(View.GONE);
                chatFrame.setVisibility(View.GONE);
            } else if (type == VhallUtil.WATCH_PLAYBACK) {
                if (WatchPlaybackFragment.followClick) {
                    followClick = true;
                    mIvFollow.setImageResource(R.mipmap.live_follow);
                } else {
                    followClick = false;
                    mIvFollow.setImageResource(R.mipmap.live_follow_no);
                }
            }
            ll_detail.setVisibility(View.VISIBLE);
        }
        return getRequestedOrientation();
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(int toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void showLottery(MessageServer.MsgInfo messageInfo) {
        if (lotteryDialog == null) {
            lotteryDialog = new ShowLotteryDialog(this);
        }
        lotteryDialog.setMessageInfo(messageInfo);
        lotteryDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        lotteryDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        lotteryDialog.show();
    }

    @Override
    public void showNotice(String content) {
        if (TextUtils.isEmpty(content))
            return;
//        tv_notice.setText(content);
//        tv_notice.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissNotice() {
//        tv_notice.setVisibility(View.GONE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (inputView != null) {
            inputView.dismiss();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (lessonDeta != null) {
            if (lessonDeta.getStatus() == 5) {
                if (keyCode == KeyEvent.KEYCODE_MENU) {
                    mPlaybackHomeKeyListener.keyDown(true);
                }

            }
        }

        if ((keyCode == KeyEvent.KEYCODE_BACK) && isBack) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBack = false;

                }
            }, 40);
            return false;
        } else if (backLock) {
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    public void onBackPressed() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            changeOrientation();
            if (inputView != null) {
                inputView.dismiss();
            }
            return;
        }

        /**demo的内容，恢复设备亮度状态*/
//        if (wakeLock != null) {
//            wakeLock.release();
//        }
        super.onBackPressed();
    }

    @Override
    protected void onUserLeaveHint() {
        if (null != inputView) {
            inputView.dismiss();
        }
        super.onUserLeaveHint();
    }


    // 注册广播

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BackService.HEART_BEAT_ACTION);
        intentFilter.addAction(BackService.MESSAGE_ACTION);
        intentFilter.addAction(BackService.CONNECT_SUCCESS);
        intentFilter.addAction(BackService.LIVE_ID);
        registerReceiver(mReceiver, intentFilter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra("message");
            // 消息广播
            if (action.equals(BackService.MESSAGE_ACTION)) {
//                text1.setText(stringExtra);
                /**
                 * 连接成功发送socket登录信息
                 */
                Gson gson = new Gson();
                SocketMessage message = gson.fromJson(stringExtra, SocketMessage.class);
                if (TextUtils.equals(Constants.SOCKET_SUCCESS, message.getCode())) {
//                    String sendMessage = Constants.socketLogin(lessonDeta.getLessonId(), lessonDeta.getId(), PrefUtils.getString(getActivity(), Constants.USER_ID, ""));
//                    try {
//                        Log.i("---------", "发送消息为：" + sendMessage);
//                        if (mIBackService == null) {
//                            Toast.makeText(getActivity(),
//                                    "socket连接失败", Toast.LENGTH_SHORT).show();
//                        } else {
//                            boolean isSend = mIBackService.sendMessage(sendMessage);
////                        Toast.makeText(getActivity(),
////                                isSend ? "success" : "fail", Toast.LENGTH_SHORT)
////                                .show();
//                        }
//                    } catch (RemoteException e) {
//                        e.printStackTrace();
//                    }

                }


            } else if (action.equals(BackService.CONNECT_SUCCESS)) { //连接成功

            } else if (action.equals(BackService.HEART_BEAT_ACTION)) {// 心跳广播
                text1.setText("正常心跳");
            } else if (action.equals(BackService.LIVE_ID)) { //直播开始获取id号
                Gson gson = new Gson();
                SocketMessage message = gson.fromJson(stringExtra, SocketMessage.class);
                if (TextUtils.equals("null", message.getData().toString()) || TextUtils.equals("", message.getData().toString()) || message.getData() == null) {
                } else {
                    vhLiveId = message.getData().toString();

                }
            }
        }
    };

//    private ServiceConnection conn = new ServiceConnection() {
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            // 未连接为空
//            mIBackService = null;
//        }
//
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            // 已连接
//            mIBackService = IBackService.Stub.asInterface(service);
//        }
//    };


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        Log.e("watchactivity------", "onDestroy: ");
        if (homeKeyReceiver != null) {
            unregisterReceiver(homeKeyReceiver);

        }
        if (mAdvancedCountdownTimer != null) {
            mAdvancedCountdownTimer.cancel();
        }
        super.onDestroy();
    }

    @Override
    public void finish() {
        Log.e("watchactivity------", "finish: ");

        if (lessonDeta.getStatus() == 1) {
            ActivityUtils.delFragmentToActivity(getSupportFragmentManager(),
                    liveFragment);
        }
        super.finish();
    }

    @Override
    public void setPresenter(WatchContract.WatchPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        bindService(mServiceIntent, conn, BIND_AUTO_CREATE);
        // 开始服务
        registerReceiver();
//        if (lessonDeta != null) {
//            if (lessonDeta.getStatus() == 5) {
//                registerReceiver(homeKeyReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
//            }
//        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        socketLinePause();


        if (mReceiver != null) {
            // 注销广播 最好在onPause上注销
            unregisterReceiver(mReceiver);

        }

    }

    @OnClick({R.id.ll_follow, R.id.ll_share, R.id.ll_teacher_detail, R.id.iv_message_show, R.id.contentVideo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_follow:
//                showToas("关注");
                showProgressDialog();
                addSubscription(apiStores.lessonConcern(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonDeta.getLessonId()), new ApiCallback<GetMessageEn>() {
                    @Override
                    public void onSuccess(GetMessageEn model) {
                        dismissProgressDialog();
                        if ("200".equals(model.getCode())) {
                            showToas(model.getMessage());
                            int starSize = Integer.parseInt(teaSzie.getText().toString());
                            if (model.getMessage().equals("取消关注成功")) {
                                starSize--;
                                followClick = false;
                                mIvFollow.setImageResource(R.mipmap.live_follow_no);
                            } else if (model.getMessage().equals("关注成功")) {
                                starSize++;
                                followClick = true;
                                mIvFollow.setImageResource(R.mipmap.live_follow);
                            }
                            teaSzie.setText(starSize + "");
                        } else {
                            showToas(model.getMessage());
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

                break;
            case R.id.ll_share:
                mSharePopupWindow.show(getWindow().getDecorView());
                break;

            //点击跳转教师详情
            case R.id.ll_teacher_detail:
                //销毁前面的直播界面
                if (ActivityCollector.activities.size() >= 3 && isLiving) {
                    for (int i = 0; i < ActivityCollector.activities.size(); i++) {
                        if (i == ActivityCollector.activities.size() - 2) {
                            ActivityCollector.removeActivity(ActivityCollector.activities.get(i));
//                                ActivityCollector.activities.remove(i);
                            break;
                        }

                    }
                }
                Intent intent = new Intent(this, TeacherDetailActivity.class);
                intent.putExtra("teach_id", lessonDeta.getLesson().getTeacherId());
                intent.putExtra("living", true);
                startAc(intent);
                break;
            //点击显示聊天框
            case R.id.iv_message_show:
                chatFrame.setVisibility(View.VISIBLE);
                ivMessageShow.setVisibility(View.GONE);
                break;
            //点击直播界面
            case R.id.contentVideo:
//                if (chatFrame.getVisibility() == View.VISIBLE) {
//                    chatFrame.setVisibility(View.GONE);
//                    ivMessageShow.setVisibility(View.VISIBLE);
//                }
                break;
        }
    }


    /**
     * 分享回调
     *
     * @param arg0
     * @param arg1
     * @param arg2
     */

    @Override
    public void onError(Platform arg0, int arg1, Throwable arg2) {//回调的地方是子线程，进行UI操作要用handle处理
        arg2.printStackTrace();
        Message msg = new Message();
        msg.what = 3;
        msg.obj = arg2.getMessage();
        handler.sendMessage(msg);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showToas("分享成功");
                    break;
                case 2:
                    showToas("取消分享");
                    break;
                case 3:
                    showToas("分享失败");
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    public void onCancel(Platform arg0, int arg1) {//回调的地方是子线程，进行UI操作要用handle处理
        handler.sendEmptyMessage(2);

    }

    @Override
    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {//回调的地方是子线程，进行UI操作要用handle处理
        if (arg0.getName().equals(QQ.NAME)) {// 判断成功的平台是不是QQ
            handler.sendEmptyMessage(1);
        }
        if (arg0.getName().equals(SinaWeibo.NAME)) {
            handler.sendEmptyMessage(1);
        }
        if (arg0.getName().equals(WechatMoments.NAME)
                || arg0.getName().equals(Wechat.NAME)) {
            handler.sendEmptyMessage(1);
        }

    }


    private void getDate(String lessonPushId) {
        showProgressDialog();
        addSubscription(apiStores.lessonPeriodsDetail(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonPushId), new ApiCallback<lessonPeriodsDetail>() {
            @Override
            public void onSuccess(lessonPeriodsDetail model) {
//                lessonPeriodsDetail.DataBean lessonPushDeta;
                if ("200".equals(model.getCode())) {
                    lessonDeta = (lessonPeriodsDetail.DataBean) model.getData();
                    MyApplication.param.setKey(PrefUtils.getString(getActivity(), Constants.USER_PASSWORD, ""));
                    MyApplication.param.setUserName(PrefUtils.getString(getActivity(), Constants.USER_NAME, ""));
                    MyApplication.param.setWatchId(model.getData().getWebinarId() + "");
                    MyApplication.param.setUserVhallId(PrefUtils.getString(getActivity(), Constants.USER_TOKEN, ""));


                    //获取回看直播路径
                    if (model.getData().getWebinarRecords() != null) {
                        Gson gson = new Gson();
                        List<WatchPlayBackEn> watchBackList = gson.fromJson(model.getData().getWebinarRecords(), new TypeToken<List<WatchPlayBackEn>>() {
                        }.getType());
                        for (int i = 0; i < watchBackList.size(); i++) {
                            if (watchBackList.get(i).getStatus() == 1) {
                                MyApplication.param.setRecordId(watchBackList.get(i).getId() + "");
                                break;
                            }
                        }

                    }
                    //status;// 状态 -1删除 1直播 2预告 3结束 4点播 5回放
                    switch (model.getData().getStatus()) {
                        case 1:
                            type = VhallUtil.WATCH_LIVE;
                            tv_amount.setText("播放 " + lessonDeta.getAmount() + "次");
                            break;
                        case 2:
                            type = VhallUtil.WATCH_LIVE;
                            tv_amount.setText("播放 " + lessonDeta.getAmount() + "次");
                            break;
                        case 3:
                            type = VhallUtil.WATCH_LIVE;
                            tv_amount.setText("播放 " + lessonDeta.getAmount() + "次");
                            showToas("直播已结束");
                            break;
                        case 4:
                            type = VhallUtil.WATCH_PLAYBACK;
                            tv_amount.setText("播放 " + lessonDeta.getAmount() + "次");
                            break;
                        case 5:
                            type = VhallUtil.WATCH_PLAYBACK;
                            tv_amount.setText("回播 " + lessonDeta.getPlaybackAmount() + "次");

                            //home键监听广播
                            homeKeyReceiver = new BroadcastReceiver() {
                                String SYSTEM_REASON = "reason";
                                String SYSTEM_HOME_KEY = "homekey";
                                String SYSTEM_HOME_KEY_LONG = "recentapps";

                                @Override
                                public void onReceive(Context context, Intent intent) {
                                    String action = intent.getAction();
                                    if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                                        String reason = intent.getStringExtra(SYSTEM_REASON);
                                        if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
                                            //表示按了home键,程序到了后台
                                            if (playbackFragment != null) {
                                                mPlaybackHomeKeyListener.keyDown(true);
                                            }
                                        } else if (TextUtils.equals(reason, SYSTEM_HOME_KEY_LONG)) {
                                            //表示长按home键,显示最近使用的程序列表
                                            String k = "";
                                            if (playbackFragment != null) {
                                                mPlaybackHomeKeyListener.keyDown(true);
                                            }
                                        }
                                    }
                                }
                            };
                            registerReceiver(homeKeyReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));

                            break;
                    }

                    shareClick();//点击分享

//                    NettyClient hetty = new NettyClient();
//                    param = (Param) getIntent().getSerializableExtra("param");
                    param = MyApplication.param;

//                    type = getIntent().getIntExtra("type", VhallUtil.WATCH_LIVE);
//                    lessonPushDeta = (lessonPeriodsDetail.DataBean) getIntent().getParcelableExtra("lessonDeta");

//                    tv_amount.setText("播放 " + lessonDeta.getAmount() + "次");
                    /**
                     * socket
                     */
//                    socketLine();


                    ico_img = (ImageView) findViewById(R.id.ico_img);
                    teaTelTxt = (TextView) findViewById(R.id.teaTelTxt);
                    teaName = (TextView) findViewById(R.id.teaName);
                    teaDz = (TextView) findViewById(R.id.teaDz);
                    teaSzie = (TextView) findViewById(R.id.teaSzie);
//        Glide.with(this).load(lessonDeta.getLesson().getIntroVideoImg()).centerCrop().placeholder(R.mipmap.loding).error(R.mipmap.flunk).crossFade().into((ImageView) teaTelImg);
                    teaTelTxt.setText(lessonDeta.getLesson().getName());
                    Glide.with(WatchActivity.this).load(lessonDeta.getLesson().getImg()).centerCrop().dontAnimate().error(R.mipmap.flunk).crossFade().into((ImageView) ico_img);
                    teaName.setText(lessonDeta.getLesson().getTeacherName());
                    teaDz.setText(lessonDeta.getLesson().getTeacherOrganization() + "");
                    teaSzie.setText(lessonDeta.getLesson().getConcernAmount() + "");
                    mTvCourseSise.setText(lessonDeta.getLesson().getNewest() + "");
                    mTvBranch.setText(lessonDeta.getLesson().getSubjectName());
                    mTvBranchBreak.setText(lessonDeta.getLesson().getSubjectName());


//                    NettyClient nettyClient = new NettyClient();
                    liveFragment = (WatchLiveFragment) getSupportFragmentManager().findFragmentById(R.id.contentVideo);

                    playbackFragment = (WatchPlaybackFragment) getSupportFragmentManager().findFragmentById(R.id.contentVideo);
                    //课程介绍
                    introductionFragment = (IntroductionFragment) getSupportFragmentManager().findFragmentById(R.id.contentChat);
                    //系列课程
                    DocumentFragment docFragment = (DocumentFragment) getSupportFragmentManager().findFragmentById(R.id.contentDoc);
                    //课程课件
                    DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentDetail);
                    //学生提问
                    questionFragment = (ChatFragment) getSupportFragmentManager().findFragmentById(R.id.contentQuestion);
                    //聊天的fragment
                    mChatLiveFragment = (ChatLiveFragment) getSupportFragmentManager().findFragmentById(R.id.chatFrame);
                    initView();


                    if (mChatLiveFragment == null) {
                        mChatLiveFragment = ChatLiveFragment.newInstance(VhallUtil.WATCH_LIVE, false);
                        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                mChatLiveFragment, R.id.chatFrame);
                    }

                    if (introductionFragment == null) {
                        introductionFragment = IntroductionFragment.newInstance(lessonDeta);
                        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                introductionFragment, R.id.contentChat);

//            introductionFragment.player.setOnFullscreenlistener(new OnFullscreenlistener() {
//                @Override
//                public void isFullscreen(boolean isscreen) {
//                    if (isscreen){
//                        llTeacher.setVisibility(View.VISIBLE);
//                        mRlLive.setVisibility(View.VISIBLE);
//                        mRlShare.setVisibility(View.VISIBLE);
//                    }else {
//                        llTeacher.setVisibility(View.GONE);
//                        mRlLive.setVisibility(View.GONE);
//                        mRlShare.setVisibility(View.GONE);
//                    }
//                }
//            });
                    }
                    if (docFragment == null) {
                        docFragment = DocumentFragment.newInstance(lessonDeta);
                        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                docFragment, R.id.contentDoc);
                    }

//        if (questionFragment == null && type == VhallUtil.WATCH_LIVE) {  //原始代码
                    if (questionFragment == null) {
                        questionFragment = ChatFragment.newInstance(type, lessonDeta);
                        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                questionFragment, R.id.contentQuestion);
                    }

                    if (detailFragment == null) {
                        detailFragment = DetailFragment.newInstance(lessonDeta);
                        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                detailFragment, R.id.contentDetail);
                    }


                    //观看直播
                    if (liveFragment == null && type == VhallUtil.WATCH_LIVE) {

                        //进入界面就传播放参数给服务器
//                        socketLineStart();
                            mAdvancedCountdownTimer.start();

                        liveFragment = WatchLiveFragment.newInstance(lessonDeta);
                        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                liveFragment, R.id.contentVideo);
                        new WatchLivePresenter(liveFragment, mChatLiveFragment, mChatLiveFragment, WatchActivity.this, param);

                        liveFragment.setOnSendClickListener(new WatchLiveFragment.SendMsgClickListener() {
                            @Override
                            public void onSendClick(String msg, InputUser user) {
                                if (mChatLiveFragment != null && chatEvent == ChatLiveFragment.CHAT_EVENT_CHAT) {
                                    mChatLiveFragment.performSend(PrefUtils.getString(WatchActivity.this, Constants.USER_NAME, "") + ":" + msg, chatEvent);
                                }
                            }
                        });

                        liveFragment.setLiveLockListener(new LiveLockListener() {
                            @Override
                            public void isLock(boolean lock) {
                                if (chatFrame.getVisibility() == View.VISIBLE) {
                                    chatFrame.setVisibility(View.GONE);
                                    ivMessageShow.setVisibility(View.VISIBLE);
                                } else {
                                    mChatFragmentListener.notShow(true);
                                }
                            }

                            @Override
                            public void LockClick(boolean click) {
                                if (click) {
                                    ivMessageShow.setClickable(false);
                                    backLock = true;
                                } else {
                                    ivMessageShow.setClickable(true);
                                    backLock = false;
                                }
                            }

                            @Override
                            public void giftSend(boolean send) {
                                chatFrame.setVisibility(View.VISIBLE);
                                ivMessageShow.setVisibility(View.GONE);
                            }
                        });

                        liveFragment.setLivePlayListener(new LivePlayListener() {
                            @Override
                            public void liveStart() {
                                socketLine();
                            }

                            @Override
                            public void livePause() {
                                socketLinePause();
                            }
                        });
                    }


//         直播回放
                    if (playbackFragment == null && type == VhallUtil.WATCH_PLAYBACK) {

                        //进入界面就传播放参数给服务器
//                        socketLineStart();

                        playbackFragment = WatchPlaybackFragment.newInstance(lessonDeta);
                        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                                playbackFragment, R.id.contentVideo);
                        new WatchPlaybackPresenter(playbackFragment, mChatLiveFragment, WatchActivity.this, param);


                        if (lessonDeta.getWebinarRecords() != null) {
                            Gson gson = new Gson();
                            watchBackList = gson.fromJson(lessonDeta.getWebinarRecords(), new TypeToken<List<WatchPlayBackEn>>() {
                            }.getType());
                            for (int i = 0; i < watchBackList.size(); i++) {
                                if (watchBackList.get(i).getStatus() == 1) {

                                    backWatchList.add(watchBackList.get(i).getSubject());
                                }
                            }

                            if (backWatchList.size()>0){
                                mAdvancedCountdownTimer.start();
                            }
//            param.setRecordId(watchBackList.get(0).getId() + "");
                        }


                        playbackFragment.setLivePlayListener(new LivePlayListener() {
                            @Override
                            public void liveStart() {
                                socketLine();//回看暂时不传
                            }

                            @Override
                            public void livePause() {
                                socketLinePause();
                            }
                        });


                    }
                    //课程详情播放监听
                    introductionFragment.setPlayerScreenChangeListener(new PlayerScreenChangeListener() {
                        @Override
                        public void isFullScreen(boolean full) {
                            if (full) {
                                mBgUp.setVisibility(View.VISIBLE);
                                llTeacher.setVisibility(View.VISIBLE);
                                mRlLive.setVisibility(View.VISIBLE);
                                mRlShare.setVisibility(View.VISIBLE);
                            } else {
                                mBgUp.setVisibility(View.GONE);
                                llTeacher.setVisibility(View.GONE);
                                if (introductionFragment.rlWebContent.getVisibility() != View.VISIBLE) {

                                    mRlLive.setVisibility(View.GONE);
                                }
                                mRlShare.setVisibility(View.GONE);
                            }
                        }
                    });


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

    @Override
    protected void onStop() {
        Log.e("watchactivity------", "stop: ");

        int timeMinus = (int) (liveTime / 60000);

//        if (timeMinus > 0) {
            Intent intent = new Intent();
            intent.setAction("com.liveFragment"); // 设置你这个广播的action
            intent.putExtra("id", lessonDeta.getLessonId());
            intent.putExtra("live_time", timeMinus);
            sendBroadcast(intent);
//        }

        super.onStop();
    }
}
