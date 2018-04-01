package com.qmx163.activity.FocusOn;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.activity.watch.WatchPlaybackFragment;
import com.qmx163.base.BaseFm;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 老师详情-----老师介绍
 * Created by Administrator on 2017/7/9.
 */

public class TeaIntroductionFrag extends BaseFm {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public WatchPlaybackFragment playbackFragment;
    private String mParam1;
    private String mParam2;
    private ImageView ivIntroImg;
//    private TextView tvIntro;
    private WebView webContent;
    private NestedScrollView mScrollView;
//    private SwipeRefreshLayout swipe;



    private TeacherIntroduListFragAdapter mAdapter;
    private List<String> listData = new ArrayList<>();


    public TeaIntroductionFrag() {
    }

    public static TeaIntroductionFrag newInstance(String param1, String param2) {

        TeaIntroductionFrag fragment = new TeaIntroductionFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teafrag, container, false);
        ButterKnife.bind(this, view);
        ivIntroImg = (ImageView) view.findViewById(R.id.iv_intro_img);
//        tvIntro = (TextView) view.findViewById(R.id.tv_intro);
        Glide.with(this).load(mParam1).centerCrop().dontAnimate().error(R.mipmap.defaultimg).crossFade().into(ivIntroImg);
        webContent = (WebView) view.findViewById(R.id.web_content);
        mScrollView = (NestedScrollView) view.findViewById(R.id.scroll_view);
//        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
//        tvIntro.setText(mParam2);


        DetailsActivity.swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DetailsActivity.swipe.setRefreshing(false);
                    }
                },1000);
            }
        });

        /**
         * 处理 scrollview和 swipRefresh冲突
         */
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new  ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
//                DetailsActivity.swipe.setEnabled(mScrollView.getScrollY()==0);
//                log.e("TAG","="+mScrollView.getScrollY());
            }
        });
        webContent.loadDataWithBaseURL(null,mParam2,"text/html", "UTF-8",null);
        initsView();
        return view;
    }
    // 初始化页面列表布局
    private void initsView() {

    }



}
