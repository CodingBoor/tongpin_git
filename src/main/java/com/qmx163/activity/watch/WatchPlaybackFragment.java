package com.qmx163.activity.watch;

import android.content.res.Configuration;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.activity.MainActivity;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.listener.LivePlayListener;
import com.qmx163.listener.LiveWitchPlayListener;
import com.qmx163.listener.OnSelectOptionLisenter;
import com.qmx163.listener.PlaybackHomeKeyListener;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.ActivityCollector;
import com.qmx163.util.PrefUtils;
import com.qmx163.util.dialog.CirclePopuwindow;
import com.vhall.business.WatchLive;
import com.vhall.business.widget.ContainerLayout;

import java.util.ArrayList;
import java.util.List;

import static com.qmx163.activity.watch.WatchActivity.watchBackList;
import static com.qmx163.application.MyApplication.param;

/**
 * 观看回放的Fragment
 */
public class WatchPlaybackFragment extends BaseFm implements WatchContract.PlaybackView, View.OnClickListener {

    WatchContract.PlaybackPresenter mPresenter;
    ContainerLayout rl_video_container;//视频区容器
    ImageView iv_play, btn_changescaletype;
    SeekBar seekbar;
    TextView tv_current_time, tv_end_time, watch_list;
    ProgressBar pb;
    TextView tv_bg;
    private ImageView clickOrientation;
    private ImageView iv_share;
    private TextView tv_title;

    boolean playStyle = false;
    boolean isFirstPlay = true;
    List backWatchList = new ArrayList();

    CirclePopuwindow circlePopuwindow;

    public static boolean isShowToast = true;
    public static boolean followClick = false; // 收藏状态
    private boolean isShowPopuShare = false;
    private boolean pauseStyle = false;

    private boolean homeKeyDown = false;//是否按下home键

    private PopupWindow mPopupWindow;


    private RelativeLayout rl_head;
    private lessonPeriodsDetail.DataBean lessonDeta;
    public static ImageView iv_follow;

    private LinearLayout rl_actions_bottom;
    private RelativeLayout rl_up;

    LivePlayListener mLivePlayListener;

    public void setLivePlayListener(LivePlayListener livePlayListener) {
        mLivePlayListener = livePlayListener;
    }

    public static WatchPlaybackFragment newInstance() {
        WatchPlaybackFragment articleFragment = new WatchPlaybackFragment();
        return articleFragment;
    }

    public static WatchPlaybackFragment newInstance(lessonPeriodsDetail.DataBean lessonDeta) {
//        ChatFragment chatFragment = new ChatFragment();
//        Bundle bundle = new Bundle();
//        bundle.putParcelable("lessonDeta", lessonDeta);
//        chatFragment.setArguments(bundle);
//        return chatFragment;
        WatchPlaybackFragment watchBackFragment = new WatchPlaybackFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lessonDeta", lessonDeta);
        watchBackFragment.setArguments(bundle);
        return watchBackFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        lessonDeta = getArguments().getParcelable("lessonDeta");//获取传递数据
        return inflater.inflate(R.layout.watch_playback_fragment, null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rl_video_container = (ContainerLayout) getView().findViewById(R.id.rl_video_container);
        btn_changescaletype = (ImageView) getView().findViewById(R.id.btn_change_scale_type);
        btn_changescaletype.setOnClickListener(this);
        pb = (ProgressBar) getView().findViewById(R.id.pb);
        iv_play = (ImageView) getView().findViewById(R.id.iv_play);
        tv_bg = (TextView) getView().findViewById(R.id.tv_bg);
        watch_list = (TextView) getView().findViewById(R.id.watch_list);
        watch_list.setOnClickListener(this);
        iv_play.setOnClickListener(this);
        getView().findViewById(R.id.iv_fullscreen).setOnClickListener(this);
        seekbar = (SeekBar) getView().findViewById(R.id.seekbar);
        tv_current_time = (TextView) getView().findViewById(R.id.tv_current_time);
        tv_end_time = (TextView) getView().findViewById(R.id.tv_end_time);
        iv_follow = (ImageView) getView().findViewById(R.id.iv_follow);
        rl_head = (RelativeLayout) getView().findViewById(R.id.rl_head);
        tv_title = (TextView) getView().findViewById(R.id.tv_title);
        iv_share = (ImageView) getView().findViewById(R.id.iv_share);
        clickOrientation = (ImageView) getView().findViewById(R.id.iv_fullscreen);

        rl_up = (RelativeLayout) getView().findViewById(R.id.rl_up);
        rl_actions_bottom = (LinearLayout) getView().findViewById(R.id.rl_actions_bottom);

        rl_video_container.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        iv_follow.setOnClickListener(this);
        getView().findViewById(R.id.image_action_back).setOnClickListener(this);

        ((WatchActivity)getActivity()).setPlaybackHomeKeyListener(new PlaybackHomeKeyListener() {
            @Override
            public void keyDown(boolean show) {
                homeKeyDown = show;
            }
        });

        for (int j = 0; j < watchBackList.size(); j++) {
            if (watchBackList.get(j).getStatus()==1){
            backWatchList.add(watchBackList.get(j).getSubject());
            }
        }
        if (backWatchList.size() == 0){
            watch_list.setVisibility(View.GONE);
        }


        ((WatchActivity)getActivity()).setLiveWitchPlayListener(new LiveWitchPlayListener() {
            @Override
            public void witchType(int type) {
                if (mPresenter != null){
                    mPresenter.paushPlay();
                }
            }
        });


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mPresenter.onProgressChanged(seekBar, progress, fromUser);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mPresenter.onStopTrackingTouch(seekBar);
            }
        });

        tv_title.setText(lessonDeta.getName()); // 显示标题


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


    @Override
    public void setPresenter(WatchContract.PlaybackPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setPlayIcon(boolean isStop) {
        if (isStop) {
            playStyle = false;
            iv_play.setImageResource(R.drawable.vhall_icon_live_play);
            mLivePlayListener.livePause();
        } else {
            playStyle = true;
            iv_play.setImageResource(R.drawable.vhall_icon_live_pause);
            ((WatchActivity)getActivity()).setPlayType(1);
            mLivePlayListener.liveStart();
        }
    }

    @Override
    public void setProgressLabel(String currentTime, String max) {
        String k = currentTime.substring(0,2);
        if (currentTime.length() > 6 && currentTime.substring(0, 2).equals("00")) {
            tv_current_time.setText(currentTime.substring(3, currentTime.length()));

        } else {
            tv_current_time.setText(currentTime);
        }
        tv_end_time.setText(max);
        if (!"00:00".equals(tv_current_time.getText())){

        tv_bg.setVisibility(View.GONE);
        }else {
            tv_bg.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void setSeekbarMax(int max) {
        seekbar.setMax(max);
    }

    @Override
    public void setSeekbarCurrentPosition(int position) {
        seekbar.setProgress(position);
    }

    @Override
    public void showProgressbar(boolean show) {
        if (show) {
//            tv_bg.setVisibility(View.VISIBLE);
            pb.setVisibility(View.VISIBLE);

        } else {
//            tv_bg.setVisibility(View.GONE);
            pb.setVisibility(View.GONE);

        }
    }

    @Override
    public ContainerLayout getContainer() {
        return rl_video_container;
    }

    @Override
    public void setScaleTypeText(int text) {
        switch (text) {
            case WatchLive.FIT_DEFAULT:
                btn_changescaletype.setBackground(getResources().getDrawable(R.drawable.fit_default));
                break;
            case WatchLive.FIT_CENTER_INSIDE:
                btn_changescaletype.setBackground(getResources().getDrawable(R.drawable.fit_center));
                break;
            case WatchLive.FIT_X:
                btn_changescaletype.setBackground(getResources().getDrawable(R.drawable.fit_x));
                break;
            case WatchLive.FIT_Y:
                btn_changescaletype.setBackground(getResources().getDrawable(R.drawable.fit_y));
                break;
            case WatchLive.FIT_XY:
                btn_changescaletype.setBackground(getResources().getDrawable(R.drawable.fit_xy));
                break;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        pauseStyle = playStyle;

//            tv_bg.setVisibility(View.VISIBLE);
        if (playStyle) {

//            mPresenter.onPlayClick();
            if (!homeKeyDown){
                mPresenter.paushPlay();
            }else {
//                tv_bg.setVisibility(View.VISIBLE);
            }
//                    mPresenter.saveCurrentPosition(false);
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isFirstPlay) {
                    if (backWatchList.size()>=1){
                        isFirstPlay = false;
//                    mPresenter.onFragmentStop();
//                        mPresenter.onFragmentDestory();
//                        param.setRecordId(watchBackList.get(0).getId() + "");
//                    mPresenter.onPlayClick();
                        mPresenter.onInitWatch();
                        mLivePlayListener.liveStart();

//                initPopu();
                    }else {
                        pb.setVisibility(View.GONE);
                        iv_play.setImageResource(R.drawable.vhall_icon_live_play);

                        showToas("暂无回看视频");

                    }
                } else {
//                    mPresenter.onPlayClick();
                }

            }
        }, 100);

        if (pauseStyle) {
            if (homeKeyDown){
                                tv_bg.setVisibility(View.VISIBLE);
                mPresenter.saveCurrentPosition(false);

                new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (homeKeyDown){
                    homeKeyDown = false;
                    mPresenter.onPlayClick();
                }
            }
        },1000);
            }else {
                homeKeyDown = false;
            mPresenter.startPlay();

            }
        }else {
            if (homeKeyDown){
                                tv_bg.setVisibility(View.VISIBLE);
                mPresenter.saveCurrentPosition(false);
            }else {
//                tv_bg.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
//        mPresenter.onFragmentStop();

//        if (playStyle) {
//            mPresenter.onPlayClick();
//
//        }
homeKeyDown = true;
        tv_bg.setVisibility(View.VISIBLE);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onFragmentDestory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (WatchActivity.isHorScreen) {//横屏
            watch_list.setVisibility(View.GONE);
            rl_head.setVisibility(View.VISIBLE);
            clickOrientation.setVisibility(View.GONE);
        } else {
            if (backWatchList.size() != 0){
            watch_list.setVisibility(View.GONE);
            }else {
                watch_list.setVisibility(View.GONE);

            }
            rl_head.setVisibility(View.GONE);
            clickOrientation.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.image_action_back) {
//            getActivity().finish();
            if (ActivityCollector.activities.size() == 1){
                startAc(MainActivity.class);
                getActivity().onBackPressed();
            }else {
            getActivity().onBackPressed();
            }
        } else if (i == R.id.iv_share) {
            showSharePopWindows();
        } else if (i == R.id.iv_play) {
            if (isFirstPlay) {
                if (backWatchList.size()>=1){
                    isFirstPlay = false;
//                    mPresenter.onFragmentStop();
                    mPresenter.onFragmentDestory();
                    param.setRecordId(watchBackList.get(0).getId() + "");
//                    mPresenter.onPlayClick();
                    mPresenter.onInitWatch();
                    mLivePlayListener.liveStart();

//                initPopu();
                }else {
                    pb.setVisibility(View.GONE);
                    iv_play.setImageResource(R.drawable.vhall_icon_live_play);
                    showToas("暂无回看视频");

                }
            } else {
                mPresenter.onPlayClick();
            }


//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_recyclerview_noswipe, null);
//            final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
//            popupWindow.setOutsideTouchable(true);
//            View parent = LayoutInflater.from(getActivity()).inflate(R.layout.watch_playback_fragment, null);
//            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            //popupWindow在弹窗的时候背景半透明
//            final WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
//            params.alpha = 0.5f;
//            getActivity().getWindow().setAttributes(params);
//            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//                @Override
//                public void onDismiss() {
//                    getActivity().getWindow().setAttributes(params);
//                }
//            });

//            mPresenter.onPlayClick();
        } else if (i == R.id.iv_fullscreen) {
            mPresenter.changeScreenOri();
        } else if (i == R.id.btn_change_scale_type) {
            mPresenter.changeScaleType();
        } else if (i == R.id.watch_list) {
            if (circlePopuwindow.isShowing()) {
                circlePopuwindow.dismiss();
            }else {
                initPopu();
            }

//            List backWatchList = new ArrayList();
//            for (int j = 0; j < watchBackList.size(); j++) {
//                backWatchList.add(watchBackList.get(j).getSubject());
//            }
//            if (circlePopuwindow == null) {
//                circlePopuwindow = new CirclePopuwindow(getActivity(), LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,backWatchList);
//
////                mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
////                // 设置popUpWindow 弹窗可点击。下面两句话必须添加，并且是true
////                mPopupWindow.setFocusable(true);
////                mPopupWindow.setBackgroundDrawable(new PaintDrawable());
////                mPopupWindow.showAtLocation(getActivity().getLayoutInflater().inflate(R.layout.watch_activity, null), Gravity.RIGHT, 0, 500);
//                circlePopuwindow.setOnSelectOptionLisenter(new OnSelectOptionLisenter() {
//                    @Override
//                    public void selectOPtion(int option) {
//                        mPresenter.onFragmentStop();
//                        param.setRecordId(watchBackList.get(option).getId() + "");
////                        mPresenter.changeScaleType();
//                        mPresenter.startPlay();
//                    }
//                });
////                        circlePopuwindow.setData(list);
//            }
//            if (circlePopuwindow.isShowing()) {
//                circlePopuwindow.dismiss();
//            } else {
//                View parent = LayoutInflater.from(getActivity()).inflate(R.layout.watch_playback_fragment, null);
//                circlePopuwindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
////                circlePopuwindow.showAsDropDown(watch_list, MyApplication.getInstance().getmWidth(), 0);
//            }
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
                        WatchActivity.teaSzie.setText(starSize+"");
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
        }else if (i == R.id.rl_video_container){
            if (rl_up.getVisibility()== View.VISIBLE){
                rl_up.setVisibility(View.GONE);
                rl_actions_bottom.setVisibility(View.GONE);
            }else {
                rl_up.setVisibility(View.VISIBLE);
                rl_actions_bottom.setVisibility(View.VISIBLE);
            }
        }
    }

    private void initPopu() {
        if (circlePopuwindow == null) {
            circlePopuwindow = new CirclePopuwindow(getActivity(), LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, backWatchList);

//                mPopupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.FILL_PARENT, true);
//                // 设置popUpWindow 弹窗可点击。下面两句话必须添加，并且是true
//                mPopupWindow.setFocusable(true);
//                mPopupWindow.setBackgroundDrawable(new PaintDrawable());
//                mPopupWindow.showAtLocation(getActivity().getLayoutInflater().inflate(R.layout.watch_activity, null), Gravity.RIGHT, 0, 500);
            circlePopuwindow.setOnSelectOptionLisenter(new OnSelectOptionLisenter() {
                @Override
                public void selectOPtion(int option) {
                    isFirstPlay = false;
//                    mPresenter.onFragmentStop();
                    mPresenter.onFragmentDestory();
                    param.setRecordId(watchBackList.get(option).getId() + "");
//                    mPresenter.onPlayClick();
                    mPresenter.onInitWatch();
mLivePlayListener.liveStart();

                }
            });
//                        circlePopuwindow.setData(list);
        }
        if (circlePopuwindow.isShowing()) {
            circlePopuwindow.dismiss();
        } else {
            View parent = LayoutInflater.from(getActivity()).inflate(R.layout.watch_playback_fragment, null);
            circlePopuwindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
//                circlePopuwindow.showAsDropDown(watch_list, MyApplication.getInstance().getmWidth(), 0);
        }
    }
}
