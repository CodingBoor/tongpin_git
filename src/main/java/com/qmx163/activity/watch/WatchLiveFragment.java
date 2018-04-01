package com.qmx163.activity.watch;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.activity.MainActivity;
import com.qmx163.activity.watch.chat.ChatContract;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.GiftsAndGreetingsEn;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.listener.ChatFragmentListener;
import com.qmx163.listener.LiveLockListener;
import com.qmx163.listener.LivePlayListener;
import com.qmx163.listener.LiveWitchPlayListener;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.ActivityCollector;
import com.qmx163.util.PrefUtils;
import com.vhall.business.WatchLive;
import com.vhall.business.widget.ContainerLayout;
import com.vhall.uilibs.util.emoji.InputUser;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;

/**
 * 观看直播的Fragment
 */
public class WatchLiveFragment extends BaseFm implements WatchContract.LiveView, View.OnClickListener ,ChatFragmentListener{

    private WatchContract.LivePresenter mPresenter;

    private ImageView clickOrientation, clickStart;
    //            , mVrButton;
    private RadioButton radioButtonShowDEFAULT, radioButtonShowSD, radioButtonShowHD, radioButtonShowUHD;

    private RadioGroup radioChoose;
    private TextView fragmentDownloadSpeed;
    private ContainerLayout mContainerLayout;
    private RelativeLayout rl_up;
    private RelativeLayout rl_down;
    //    private ImageView btn_change_scaletype;
//    private ImageView btnChangePlayStatus;
    ImageView btn_danmaku;
    ProgressBar progressbar;

    //    private IDanmakuView mDanmakuView;
    private DanmakuContext mDanmuContext;
    private BaseDanmakuParser mParser;
    private Activity context;
    private View root;

    private TextView popuLeft, tv_title;
    private TextView popuRignt;
    private PopupWindow mPopupWindow;
    private boolean isShowPopuLeft = false;
    private boolean isShowPopuRight = false;
    private boolean isShowPopuShare = false;
    private boolean lockClick = false; //刷频开关
    public static boolean followClick = false; // 收藏状态
    private lessonPeriodsDetail.DataBean lessonDeta;
    private boolean isLine = false;
    double allPoint = 0;
    double needPoint = 0;

    TextView tv_bg;

    private RecyclerView recyclerView;
    private RelativeLayout rl_head;
    public static ImageView iv_follow;
    private ImageView iv_lock, iv_share;
    private List<GiftsAndGreetingsEn.DataBean.GiftsBean> giftList = new ArrayList<>();
    private List<GiftsAndGreetingsEn.DataBean.GreetingsBean> greetingList = new ArrayList<>();
    private RecyclerAdapter giftAdapter;
    private RecyclerAdapter greetingAdapter;
    private int checkType = 0;//判断右边popu是礼物还是问候语
    private String giftId = "";  // 礼物id
    int giftSize = 0;//礼物数量
    double giftPoint = 0;//礼物分数
    private ChatContract.ChatPresenter mKeyboardPresenter;
    private int giftCheckPosition = -1;
    private int greetingCheckPosition = -1;
    private InputUser user = null;
    private boolean watchType = false;
    private boolean pauseType = false;


    DecimalFormat df = new DecimalFormat("######0.00");

    WatchContract.WatchView watchView;

    LiveLockListener mLiveLockListener;

    LivePlayListener mLivePlayListener;

    public void setLiveLockListener(LiveLockListener liveLockListener) {
        mLiveLockListener = liveLockListener;
    }

    public void setLivePlayListener(LivePlayListener livePlayListener) {
        mLivePlayListener = livePlayListener;
    }

    @Override
    public void notShow(boolean show) {

        if (rl_up.getVisibility() == View.VISIBLE) {
            rl_up.setVisibility(View.GONE);
            rl_down.setVisibility(View.GONE);
        } else {
            rl_up.setVisibility(View.VISIBLE);
            rl_down.setVisibility(View.VISIBLE);

        }


    }

    //发送点击回调
    public interface SendMsgClickListener {
        void onSendClick(String msg, InputUser user);
    }

    SendMsgClickListener onSendClickListener;

    public void setOnSendClickListener(SendMsgClickListener onSendClickListener) {
        this.onSendClickListener = onSendClickListener;
    }


    public static WatchLiveFragment newInstance() {
        return new WatchLiveFragment();
    }

    public static WatchLiveFragment newInstance(lessonPeriodsDetail.DataBean lessonDeta) {
//        ChatFragment chatFragment = new ChatFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("lessonDeta", lessonDeta);
//        chatFragment.setArguments(bundle);
//        return chatFragment;
        WatchLiveFragment watchLiveFragment = new WatchLiveFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lessonDeta", lessonDeta);
        watchLiveFragment.setArguments(bundle);
        return watchLiveFragment;
    }

    @Override
    public void setPresenter(WatchContract.LivePresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.context = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.watch_live_fragment, container, false);
        lessonDeta = getArguments().getParcelable("lessonDeta");//获取传递数据
        initView(root);

        ( (WatchActivity)getActivity()).setChatFragmentListener(this);

        if (lessonDeta.getStatus() == 3) {
            clickOrientation.setVisibility(View.GONE);
            clickStart.setBackgroundResource(R.drawable.vhall_icon_live_play);
            clickStart.setEnabled(false);
            showToas("直播已结束");
            progressbar.setVisibility(View.GONE);
        }else {
            mLivePlayListener.liveStart();
        }
        reFreshView();
        getGiftsAndGreet();//获取礼物和问候语数据
        //initStatue();
        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setResult(Activity.RESULT_OK);
        mPresenter.stopWatch();
    }

    private void initView(View root) {
        clickStart = (ImageView) root.findViewById(R.id.click_rtmp_watch);
        clickStart.setOnClickListener(this);
        clickOrientation = (ImageView) root.findViewById(R.id.click_rtmp_orientation);
        clickOrientation.setOnClickListener(this);
        radioChoose = (RadioGroup) root.findViewById(R.id.radio_choose);
        radioChoose.setOnCheckedChangeListener(checkListener);
        radioButtonShowDEFAULT = (RadioButton) root.findViewById(R.id.radio_btn_default);
        radioButtonShowSD = (RadioButton) root.findViewById(R.id.radio_btn_sd);
        radioButtonShowHD = (RadioButton) root.findViewById(R.id.radio_btn_hd);
        radioButtonShowUHD = (RadioButton) root.findViewById(R.id.radio_btn_uhd);
        mContainerLayout = (ContainerLayout) root.findViewById(R.id.rl_container);
        rl_up = (RelativeLayout) root.findViewById(R.id.Rl_up);
        rl_down = (RelativeLayout) root.findViewById(R.id.rl_down);
        fragmentDownloadSpeed = (TextView) root.findViewById(R.id.fragment_download_speed);

        tv_bg = (TextView) root.findViewById(R.id.tv_bg);


        rl_head = (RelativeLayout) root.findViewById(R.id.rl_head);
        iv_follow = (ImageView) root.findViewById(R.id.iv_follow);
        iv_share = (ImageView) root.findViewById(R.id.iv_share);
        iv_lock = (ImageView) root.findViewById(R.id.iv_lock);
        popuLeft = (TextView) root.findViewById(R.id.tv_popu_left);
        popuRignt = (TextView) root.findViewById(R.id.tv_popu_right);
        tv_title = (TextView) root.findViewById(R.id.tv_title);

//        mVrButton = (ImageView) root.findViewById(R.id.btn_headtracker);
//        mVrButton.setOnClickListener(this);
        btn_danmaku = (ImageView) root.findViewById(R.id.btn_danmaku);
        btn_danmaku.setImageResource(R.drawable.vhall_icon_danmaku_close);
        btn_danmaku.setOnClickListener(this);
        popuLeft.setOnClickListener(this);
        popuRignt.setOnClickListener(this);
        iv_lock.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        iv_follow.setOnClickListener(this);
        mContainerLayout.setOnClickListener(this);
//        btnChangePlayStatus = (ImageView) root.findViewById(R.id.btn_change_audio);
//        btnChangePlayStatus.setOnClickListener(this);
//        btn_change_scaletype = (ImageView) root.findViewById(btn_change_scaletype);
//        btn_change_scaletype.setOnClickListener(this);


        ((WatchActivity)getActivity()).setLiveWitchPlayListener(new LiveWitchPlayListener() {
            @Override
            public void witchType(int type) {
                if (mPresenter != null){
                    mPresenter.stopWatch();
                }
            }
        });
        tv_title.setText(lessonDeta.getName()); // 显示标题

        progressbar = (ProgressBar) root.findViewById(R.id.progressbar);
        root.findViewById(R.id.image_action_back).setOnClickListener(this);
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);

//        mDanmakuView = (IDanmakuView) root.findViewById(R.id.sv_danmaku);
//        mDanmakuView.hide();
        mDanmuContext = DanmakuContext.create();
        mDanmuContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(2.2f).setScaleTextSize(1.2f)
//                .setCacheStuffer(new SimpleTextCacheStuffer(), null)
                .setCacheStuffer(new SpannedCacheStuffer(), null) // 图文混排使用SpannedCacheStuffer
//        .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);
//        if (mDanmakuView != null) {
//            mParser = new BaseDanmakuParser() {
//                @Override
//                protected IDanmakus parse() {
//                    return new Danmakus();
//                }
//            };
//            mDanmakuView.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
//                @Override
//                public void updateTimer(DanmakuTimer timer) {
//                }
//
//                @Override
//                public void drawingFinished() {
//
//                }
//
//                @Override
//                public void danmakuShown(BaseDanmaku danmaku) {
//                }
//
//                @Override
//                public void prepared() {
//                    mDanmakuView.start();
//                }
//            });
//            mDanmakuView.prepare(mParser, mDanmuContext);
//            mDanmakuView.showFPS(false);
//            mDanmakuView.enableDanmakuDrawingCache(true);
//        }


        if (mPresenter != null) {
            mPresenter.start();
            clickStart.setBackgroundResource(R.drawable.vhall_icon_live_pause);
//            mPresenter.onWatchBtnClick();
        }

    }

    @Override
    public ContainerLayout getWatchLayout() {
        return mContainerLayout;
    }

    @Override
    public void setPlayPicture(boolean state) {
        if (state) {
            ((WatchActivity)getActivity()).setPlayType(1);
            clickStart.setBackgroundResource(R.drawable.vhall_icon_live_pause);
            if (watchType!=state){
                watchType = state;
            mLivePlayListener.liveStart();
            }
        } else {
            clickStart.setBackgroundResource(R.drawable.vhall_icon_live_play);
            if (watchType!=state){
                watchType = state;
            mLivePlayListener.livePause();
            }
        }
    }

    @Override
    public void setDownSpeed(String text) {
        fragmentDownloadSpeed.setText(text);
    }

    @Override
    public void showLoading(boolean isShow) {
        if (isShow)
            progressbar.setVisibility(View.VISIBLE);
        else
            tv_bg.setVisibility(View.GONE);
        progressbar.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.click_rtmp_watch) {
            mPresenter.onWatchBtnClick();
        } else if (i == R.id.click_rtmp_orientation) {
            mPresenter.changeOriention();
        }
//        else if (i == btn_change_scaletype) {
//            mPresenter.setScaleType();
//        }
//        else if (i == R.id.btn_headtracker) {
//            mPresenter.setHeadTracker();
//            LogManager.innerLog("HeadTracker", " HeadTracker == " + mPresenter.isHeadTracker());
//        }
        else if (i == R.id.image_action_back) {
            if (ActivityCollector.activities.size() == 1) {
                startAc(MainActivity.class);
                getActivity().onBackPressed();
            } else {
                getActivity().onBackPressed();
            }
        }
//        else if (i == R.id.btn_change_audio) {
//            if (mPresenter.getCurrentPixel() == WatchLive.DPI_DEFAULT) {
//                mPresenter.onSwitchPixel(WatchLive.DPI_AUDIO);
//                btnChangePlayStatus.setImageResource(R.drawable.audio_open);
//            } else if (mPresenter.getCurrentPixel() == WatchLive.DPI_AUDIO) {
//                mPresenter.onSwitchPixel(WatchLive.DPI_DEFAULT);
//                btnChangePlayStatus.setImageResource(R.drawable.audio_close);
//            }
//        }
//        else if (i == R.id.btn_danmaku) {
//            if (mDanmakuView == null || !mDanmakuView.isPrepared())
//                return;
//            if (mDanmakuView.isShown()) {
//                mDanmakuView.hide();
//                btn_danmaku.setImageResource(R.drawable.vhall_icon_danmaku_close);
//            } else {
//                mDanmakuView.show();
//                btn_danmaku.setImageResource(R.drawable.vhall_icon_danmaku_open);
//            }
//
//        }
        else if (i == R.id.tv_popu_left) {
            showLeftPopWindow();
        } else if (i == R.id.tv_popu_right) {
            showRigntPopWindows();
        } else if (i == R.id.iv_share) {
            showSharePopWindows();
        } else if (i == R.id.iv_lock) {
            if (lockClick) {
                lockClick = false;
                iv_lock.setImageResource(R.mipmap.live_lock_close);
                mContainerLayout.setClickable(false);
                popuRignt.setClickable(false);
                rl_up.setVisibility(View.GONE);
                rl_down.setVisibility(View.GONE);
                mLiveLockListener.LockClick(true);
            } else {
                lockClick = true;
                iv_lock.setImageResource(R.mipmap.live_lock);
                mContainerLayout.setClickable(true);
                popuRignt.setClickable(true);
                mLiveLockListener.LockClick(false);
            }
        } else if (i == R.id.iv_follow) {
            addSubscription(apiStores.lessonConcern(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonDeta.getLessonId()), new ApiCallback<GetMessageEn>() {
                @Override
                public void onSuccess(GetMessageEn model) {
                    if ("200".equals(model.getCode())) {
                        showToas(model.getMessage());
                        int starSize = Integer.parseInt(WatchActivity.teaSzie.getText().toString());

                        if (model.getMessage().equals("取消关注成功")) {
                            starSize--;
                            followClick = false;
                            iv_follow.setImageResource(R.mipmap.live_follow_no);
                        } else if (model.getMessage().equals("关注成功")) {
                            starSize++;

                            followClick = true;
                            iv_follow.setImageResource(R.mipmap.live_follow);
                        }
                        WatchActivity.teaSzie.setText(starSize + "");
                    } else {
                        showToas(model.getMessage());
                    }
                }

                @Override
                public void onFailure(String code) {

                }

                @Override
                public void onFinish() {
                }
            });


            if (followClick) {
                followClick = false;
                iv_follow.setImageResource(R.mipmap.live_follow_no);
            } else {
                followClick = true;
                iv_follow.setImageResource(R.mipmap.live_follow);
            }
        } else if (i == R.id.rl_container) {
            mLiveLockListener.isLock(true);

        }
    }


    /**
     * 分享弹窗
     */
    private void showSharePopWindows() {
        if (isShowPopuShare) {
            return;
        }
        isShowPopuShare = true;
        // 修改右边的小图标
        View view = getActivity().getLayoutInflater().inflate(R.layout.live_share_popu, null);
        mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
        // 设置popUpWindow 弹窗可点击。下面两句话必须添加，并且是true
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new PaintDrawable());
        mPopupWindow.showAtLocation(getActivity().getLayoutInflater().inflate(R.layout.watch_activity, null), Gravity.RIGHT, 0, 500);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isShowPopuShare = false;
            }
        });

        final TextView share_qq = (TextView) view.findViewById(R.id.share_qq);
        final TextView share_weixin = (TextView) view.findViewById(R.id.share_weixin);
        final TextView share_weixin_friend = (TextView) view.findViewById(R.id.share_weixin_friend);
        final TextView share_sina = (TextView) view.findViewById(R.id.share_sina);
        share_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToas("分享1");
                mPopupWindow.dismiss();

            }
        });
        share_weixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToas("分享2");
                mPopupWindow.dismiss();

            }
        });
        share_weixin_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToas("分享3");
                mPopupWindow.dismiss();
            }
        });
        share_sina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToas("分享5");
                mPopupWindow.dismiss();

            }
        });
    }


    /**
     * 左边消息弹窗
     */
    private void showLeftPopWindow() {
        if (isShowPopuLeft) {
            return;
        }
        isShowPopuLeft = true;
        // 修改右边的小图标
        View view = getActivity().getLayoutInflater().inflate(R.layout.live_left_popwin, null);
        mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
        // 设置popUpWindow 弹窗可点击。下面两句话必须添加，并且是true
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new PaintDrawable());
        mPopupWindow.showAtLocation(getActivity().getLayoutInflater().inflate(R.layout.watch_activity, null), Gravity.LEFT, 0, 500);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isShowPopuLeft = false;
            }
        });

        TextView sendMessage = (TextView) view.findViewById(R.id.tv_message);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                watchView.showChatView(false, null, 0);
//                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
////                imm.showSoftInput(root,InputMethodManager.SHOW_FORCED);//强制显示软键盘
////                imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }


    /**
     * 右边送礼弹窗
     */
    private void showRigntPopWindows() {
        if (isShowPopuRight) {
            return;
        }
        isShowPopuRight = true;
        // 修改右边的小图标
        View view = getActivity().getLayoutInflater().inflate(R.layout.live_right_popwin, null);
        mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
        // 设置popUpWindow 弹窗可点击。下面两句话必须添加，并且是true
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new PaintDrawable());
        mPopupWindow.showAtLocation(getActivity().getLayoutInflater().inflate(R.layout.watch_activity, null), Gravity.RIGHT, 0, 500);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isShowPopuRight = false;
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_popu);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_popu);
        RadioButton radioButtonOne = (RadioButton) view.findViewById(R.id.rb_popu_one);
        RadioButton radioButtonTwo = (RadioButton) view.findViewById(R.id.rb_popu_two);
        TextView sizeAdd = (TextView) view.findViewById(R.id.tv_add);
        TextView sizeDel = (TextView) view.findViewById(R.id.tv_del);
        final TextView tv_all_point = (TextView) view.findViewById(R.id.tv_all_point);
        final TextView tv_need_point = (TextView) view.findViewById(R.id.tv_need_point);
        final TextView size = (TextView) view.findViewById(R.id.tv_size);
        final TextView giftGive = (TextView) view.findViewById(R.id.tv_give);
        getDatePoint(tv_all_point);
//        sizeAdd.setOnClickListener(this);
//        sizeDel.setOnClickListener(this);
//        giftGive.setOnClickListener(this);
        giftSize = Integer.parseInt(size.getText().toString());


        if (checkType == 0) {
            radioButtonOne.setChecked(true);
        } else {
            radioButtonTwo.setChecked(true);
        }
        if (radioButtonOne.isChecked()) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(giftAdapter);
        } else {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(greetingAdapter);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_popu_one) {
                    checkType = 0;
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(giftAdapter);

                    greetingCheckPosition = -1;
                    greetingAdapter.notifyDataSetChanged();

                } else if (checkedId == R.id.rb_popu_two) {
                    checkType = 1;
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(greetingAdapter);

                    giftCheckPosition = -1;
                    giftAdapter.notifyDataSetChanged();
                }
                size.setText("1");
                giftSize = 1;
                giftPoint = 0.0;
                tv_need_point.setText(giftPoint + "");
                needPoint = giftPoint;
            }
        });
//        bindRecyclerView();
//        getGiftsAndGreet();

        /**
         * 礼物条目点击事件
         */
        giftAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                giftPoint = giftList.get(position).getPrice();
//                giftPoint = 1;
                tv_need_point.setText(giftPoint + "");
                needPoint = giftPoint;
                size.setText("1");
                giftSize = 1;
                giftCheckPosition = position;
                greetingCheckPosition = -1;
                giftAdapter.notifyDataSetChanged();
            }
        });

        /**
         * 问候语条目点击事件
         */
        greetingAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                giftPoint = greetingList.get(position).getPrice();
//                giftPoint = 1;
                tv_need_point.setText(giftPoint + "");
                needPoint = giftPoint;
                size.setText("1");
                giftSize = 1;
                greetingCheckPosition = position;
                giftCheckPosition = -1;
                greetingAdapter.notifyDataSetChanged();
            }
        });

        sizeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (giftCheckPosition == -1 && greetingCheckPosition == -1) {
                    showToas("请选择礼物");
                } else {
                    giftSize++;
                    tv_need_point.setText(df.format(giftPoint * giftSize) + "");
                    needPoint = giftPoint * giftSize;
                    size.setText(giftSize + "");
                }
            }
        });

        sizeDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (giftSize > 1) {

                    if (giftCheckPosition == -1 && greetingCheckPosition == -1) {
                        showToas("请选择礼物");
                    } else {
                        giftSize--;
                        tv_need_point.setText(df.format(giftPoint * giftSize) + "");
                        needPoint = giftPoint * giftSize;
                        size.setText(giftSize + "");
                    }
                }
            }
        });

        giftGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String giftId = "";
                if (checkType == 0) {
                    if (giftCheckPosition == -1) {
                        showToas("请选择礼物");
                        return;
                    }
                    giftId = giftList.get(giftCheckPosition).getId();
                } else {
                    if (greetingCheckPosition == -1) {
                        showToas("请选择礼物");
                        return;
                    }
                    giftId = greetingList.get(greetingCheckPosition).getId();
                }
                if (!isLine){
                    showToas("直播连接失败");
                    return;
                }
                if (needPoint>allPoint){
                    showToas("V能量不足");
                    return;
                }

                addSubscription(apiStores.sendGiftUseScore(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), giftId, giftSize + ""), new ApiCallback<SocketMessage>() {
                    @Override
                    public void onSuccess(SocketMessage model) {


                        mLiveLockListener.giftSend(true);
                        /*
                        如果是问候语句，则要显示在消息面板
                         */
                        if (checkType == 1) {

                            if (onSendClickListener != null) {
                                String msg = greetingList.get(greetingCheckPosition).getName() + "！";
//                            if (msg.contains("@") && msg.contains(":")) {
//                                msg = msg.substring(msg.indexOf(":")+1);
//                            }
//                            if (user!=null) {
//                                String text = "@" + user.userName + ":";
//                                msg = text+msg;
//                            }
                                onSendClickListener.onSendClick(msg, user);
                            }
                        } else if (checkType == 0) {
                            if (onSendClickListener != null) {

                                String msg = "送了 " + giftList.get(giftCheckPosition).getName() +  " x "+giftSize;
                                onSendClickListener.onSendClick(msg, user);

                            }
                        }
                        showToas(model.getMessage());
                    }

                    @Override
                    public void onFailure(String code) {

                    }

                    @Override
                    public void onFinish() {

                    }
                });
                getDatePoint(tv_all_point);

            }
        });


//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.tv_add:
//                        giftSize ++;
//                        giftGive.setText(giftSize+"");
//                        break;
//                    case R.id.tv_del:
//                        if (giftSize>=1){
//                            giftSize --;
//                            giftGive.setText(giftSize+"");
//                        }
//                        break;
//                    case R.id.tv_give:
//                showToas("送礼物");
//                        break;
//                }
//            }
//        });
    }

    private void bindRecyclerView() {
        giftAdapter = new RecyclerAdapter<GiftsAndGreetingsEn.DataBean.GiftsBean>(getActivity(), giftList, R.layout.item_live_gifts) {
            @Override
            public void convert(RecyclerViewHolder helper, GiftsAndGreetingsEn.DataBean.GiftsBean item, int position) {
                if (position == giftCheckPosition) {
                    helper.getView(R.id.ll_check).setBackgroundResource(R.drawable.live_right_popu_check);
                } else {
                    helper.getView(R.id.ll_check).setBackgroundColor(Color.WHITE);
                }
                helper.setText(R.id.tv_point, item.getPrice() + "");
                helper.setImageUrl(getActivity(), R.id.iv_gifts, item.getImg());
                helper.setText(R.id.tv_gift, item.getName());
            }
        };
        greetingAdapter = new RecyclerAdapter<GiftsAndGreetingsEn.DataBean.GreetingsBean>(getActivity(), greetingList, R.layout.item_live_greetings) {
            @Override
            public void convert(RecyclerViewHolder helper, GiftsAndGreetingsEn.DataBean.GreetingsBean item, int position) {
                if (position == greetingCheckPosition) {
                    helper.getView(R.id.rl_check).setBackgroundResource(R.drawable.live_right_popu_check);
                } else {
                    helper.getView(R.id.rl_check).setBackgroundColor(Color.WHITE);
                }
                helper.setText(R.id.tv_point, item.getPrice() + "");
                helper.setText(R.id.tv_greeting, item.getName());
            }
        };
        giftAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                giftId = giftList.get(position).getId();
            }
        });

        greetingAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                giftId = greetingList.get(position).getId();
            }
        });

    }

    //获取礼物和问候语数据
    private void getGiftsAndGreet() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("rq", "/app/giftsAndGreetings");
        addSubscription(apiStores.giftsAndGreetings(params), new ApiCallback<GiftsAndGreetingsEn>() {
            @Override
            public void onSuccess(GiftsAndGreetingsEn model) {
                if ("200".equals(model.getCode())) {
                    giftList.clear();
                    greetingList.clear();
                    GiftsAndGreetingsEn.DataBean bean = model.getData();
                    giftList = bean.getGifts();
                    greetingList = bean.getGreetings();
//                    greetingList.addAll(bean.getGreetings());
//                    greetingList.addAll(bean.getGreetings());
//                    giftList.addAll(bean.getGifts());
//                    greetingList.addAll(bean.getGreetings());
                    bindRecyclerView();
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


    /**
     * 获取数据
     */
    private void getDatePoint(final TextView mTvscore) {
        addSubscription(apiStores.SelectPer(PrefUtils.getString(getActivity(), Constants.USER_ID, "")), new ApiCallback<RegisteredEn>() {
            @Override
            public void onSuccess(RegisteredEn model) {
                if ("200".equals(model.getCode())) {
                    RegisteredEn.DataBean data = model.getData();
                    mTvscore.setText("余 " + data.getScore());
                    allPoint = data.getScore();
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

    /**
     * 切换分辨率
     *
     * @param map 0 : 无效不可用  1 ：有效可用
     */
    @Override
    public void showRadioButton(HashMap map) {
        if (map == null)
            return;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();
            switch (key) {
                case "A":
//                    if (value == 1)
//                        btnChangePlayStatus.setVisibility(View.VISIBLE);
//                    else
//                        btnChangePlayStatus.setVisibility(View.GONE);
                    break;
                case "SD":
                    if (value == 1)
                        radioButtonShowSD.setVisibility(View.VISIBLE);
                    else
                        radioButtonShowSD.setVisibility(View.GONE);
                    break;
                case "HD":
                    if (value == 1)
                        radioButtonShowHD.setVisibility(View.VISIBLE);
                    else
                        radioButtonShowHD.setVisibility(View.GONE);
                    break;
                case "UHD":
                    if (value == 1)
                        radioButtonShowUHD.setVisibility(View.VISIBLE);
                    else
                        radioButtonShowUHD.setVisibility(View.GONE);
                    break;
            }
        }
    }

    @Override
    public void setScaleButtonText(int type) {
//        switch (type) {
//            case WatchLive.FIT_DEFAULT:
//                btn_change_scaletype.setBackground(getResources().getDrawable(R.drawable.fit_default));
//                break;
//            case WatchLive.FIT_CENTER_INSIDE:
//                btn_change_scaletype.setBackground(getResources().getDrawable(R.drawable.fit_center));
//                break;
//            case WatchLive.FIT_X:
//                btn_change_scaletype.setBackground(getResources().getDrawable(R.drawable.fit_x));
//                break;
//            case WatchLive.FIT_Y:
//                btn_change_scaletype.setBackground(getResources().getDrawable(R.drawable.fit_y));
//                break;
//            case WatchLive.FIT_XY:
//                btn_change_scaletype.setBackground(getResources().getDrawable(R.drawable.fit_xy));
//                break;
//        }
    }

    @Override
    public void addDanmu(String danmu) {
//        BaseDanmaku danmaku = mDanmuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
//        if (danmaku == null || mDanmakuView == null) {
//            return;
//        }
//        Spannable spannable = EmojiUtils.getEmojiText(context, danmu);
//        danmaku.text = spannable;
//        danmaku.padding = 5;
//        danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
//        danmaku.isLive = true;
//        danmaku.setTime(mDanmakuView.getCurrentTime() + 1200);
//        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
//        danmaku.textColor = Color.WHITE;
////        danmaku.textShadowColor = Color.WHITE;
//        // danmaku.underlineColor = Color.GREEN;
//        danmaku.borderColor = Color.TRANSPARENT;
//        mDanmakuView.addDanmaku(danmaku);
    }

    @Override
    public void isLineOk(boolean on) {
        isLine = on;
        if (isLine){
//            mLivePlayListener.liveStart();
        }

    }

    @Override
    public void reFreshView() {
        if (mPresenter != null) {
//            if (mPresenter.getCurrentPixel() == WatchLive.DPI_DEFAULT) {
//                btnChangePlayStatus.setBackground(getResources().getDrawable(R.drawable.audio_close));
//            } else if (mPresenter.getCurrentPixel() == WatchLive.DPI_AUDIO) {
//                btnChangePlayStatus.setBackground(getResources().getDrawable(R.drawable.audio_open));
//            }
            setScaleButtonText(mPresenter.getScaleType());
//            if (mPresenter.isHeadTracker()) {
//                mVrButton.setImageDrawable(getResources().getDrawable(R.drawable.vhall_icon_headtracker_checked));
//            } else {
//                mVrButton.setImageDrawable(getResources().getDrawable(R.drawable.vhall_icon_headtracker));
//            }
        }
    }

//    private void addDanmaKuShowTextAndImage(boolean islive) {
//        BaseDanmaku danmaku = mDanmuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
//        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
//        drawable.setBounds(0, 0, 100, 100);
//        SpannableStringBuilder spannable = createSpannable(drawable);
//        danmaku.text = spannable;
//        danmaku.padding = 5;
//        danmaku.priority = 1;  // 一定会显示, 一般用于本机发送的弹幕
//        danmaku.isLive = islive;
//        danmaku.setTime(mDanmakuView.getCurrentTime() + 1200);
//        danmaku.textSize = 25f * (mParser.getDisplayer().getDensity() - 0.6f);
//        danmaku.textColor = Color.RED;
//        danmaku.textShadowColor = 0; // 重要：如果有图文混排，最好不要设置描边(设textShadowColor=0)，否则会进行两次复杂的绘制导致运行效率降低
//        danmaku.underlineColor = Color.GREEN;
//        mDanmakuView.addDanmaku(danmaku);
//    }

    private SpannableStringBuilder createSpannable(Drawable drawable) {
        String text = "bitmap";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ImageSpan span = new ImageSpan(drawable);//ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("图文混排");
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor("#8A2233B1")), 0, spannableStringBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableStringBuilder;
    }


    /**
     * 横竖屏切换监听
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (WatchActivity.isHorScreen) {
            iv_lock.setVisibility(View.VISIBLE);
            rl_head.setVisibility(View.VISIBLE);
            popuLeft.setVisibility(View.GONE);
            popuRignt.setVisibility(View.VISIBLE);
            clickOrientation.setVisibility(View.GONE);
        } else {
            iv_lock.setVisibility(View.GONE);
            rl_head.setVisibility(View.GONE);
            popuLeft.setVisibility(View.GONE);
            popuRignt.setVisibility(View.GONE);
            clickOrientation.setVisibility(View.VISIBLE);
        }
    }

    public RadioGroup.OnCheckedChangeListener checkListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == R.id.radio_btn_default) {
                mPresenter.onSwitchPixel(WatchLive.DPI_DEFAULT);
            } else if (i == R.id.radio_btn_sd) {
                mPresenter.onSwitchPixel(WatchLive.DPI_SD);
            } else if (i == R.id.radio_btn_hd) {
                mPresenter.onSwitchPixel(WatchLive.DPI_HD);
            } else if (i == R.id.radio_btn_uhd) {
                mPresenter.onSwitchPixel(WatchLive.DPI_UHD);
            } else {
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        pauseType = watchType;
        mPresenter.stopWatch();
        watchType = false;
//        if (mDanmakuView != null && mDanmakuView.isPrepared()) {
//            mDanmakuView.pause();
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (pauseType){
        mPresenter.startWatch();
        }
//        if (mDanmakuView != null && mDanmakuView.isPrepared() && mDanmakuView.isPaused()) {
//            mDanmakuView.resume();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
//        if (mDanmakuView != null) {
//            // dont forget release!
//            mDanmakuView.release();
//            mDanmakuView = null;
//        }
    }



}
