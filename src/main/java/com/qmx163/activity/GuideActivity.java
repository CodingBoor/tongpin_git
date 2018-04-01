package com.qmx163.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.base.BaseAcNoScroll;
import com.qmx163.util.PrefUtils;
import com.qmx163.view.WelcomeVideoView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by likai on 2017/2/22.
 * email: codingkai@163.com
 */
public class GuideActivity extends BaseAcNoScroll {
    private ImageView point;
    private ViewPager mViewPager;
    private LinearLayout llContainer;
    private ImageView ivRedPoint;
    private com.rey.material.widget.TextView btStart;
    private ArrayList<ImageView> mImageViewList;
    private int[] mImageIds = new int[]{R.mipmap.logo};
    private int mPointDis;
    @BindView(R.id.videoview)
    WelcomeVideoView videoview;

    //    String guide_img = "";
    ArrayList<String> guideList = new ArrayList<>();
    String videoPath = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
//        guide_img = getIntent().getStringExtra("guide_img");
        videoPath = getIntent().getStringExtra("video_path");
        guideList = getIntent().getStringArrayListExtra("guide_list");


        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        llContainer = (LinearLayout) findViewById(R.id.ll_container);
        ivRedPoint = (ImageView) findViewById(R.id.iv_red_point);
        btStart = (com.rey.material.widget.TextView) findViewById(R.id.bt_start);
//        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.guide_1));

        if (videoPath != null && videoPath != "") {
            videoview.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.GONE);
            btStart.setVisibility(View.VISIBLE);
            videoview.setVideoPath(videoPath);
            videoview.start();
            videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    videoview.start();
                }
            });
        }
//        videoview.setOnCompletionListener(mediaPlayer -> videoview.start());
        initData();
    }

    public void initData() {
        initRecord();
        mViewPager.setAdapter(new GuideAdapter());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int leftMargin = (int) (mPointDis * positionOffset) + position
                        * mPointDis;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint
                        .getLayoutParams();
                params.leftMargin = leftMargin;
                ivRedPoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageViewList.size() - 1) {
                    btStart.setVisibility(View.VISIBLE);
                    ivRedPoint.setVisibility(View.GONE);
                    llContainer.setVisibility(View.GONE);
                } else {
                    btStart.setVisibility(View.GONE);
                    ivRedPoint.setVisibility(View.VISIBLE);
                    llContainer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        ivRedPoint.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
//                        mPointDis = llContainer.getChildAt(1).getLeft()
//                                - llContainer.getChildAt(0).getLeft();
                    }
                });

        btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefUtils.setBoolean(getApplicationContext(), "is_first_enter", false);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        });
    }


    public void initRecord() {
        mImageViewList = new ArrayList<>();
        for (int i = 0; i < guideList.size(); i++) {
            ImageView view = new ImageView(this);
            Glide.with(this).load(guideList.get(i)).centerCrop().dontAnimate().error(R.mipmap.flunk).crossFade().into(view);
//            view.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(view);
            point = new ImageView(this);
            point.setImageResource(R.drawable.shape_message_read);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i > 0) {
                params.leftMargin = 25;
            }
            point.setLayoutParams(params);
            llContainer.addView(point);
        }
    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
