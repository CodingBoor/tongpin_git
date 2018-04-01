package com.qmx163.view;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.bumptech.glide.Glide;
import com.qmx163.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义banner 参考（http://blog.csdn.net/a910626/article/details/50624420）
 */
public class BannerView extends RelativeLayout {

    // 当前上下文环境
    private Context mContext;
    // ViewPager 切换控件
    private ViewPager mViewPager;
    // 线性布局存放点
    private LinearLayout mLinearLayout;

    private List<String> mList = new ArrayList<>();

    //存放点的图片集合
    private ImageView[] mIndicator;
    //显示几个点
    private int mItemCount;
    //Banner点击事件
    private OnBannerItemClickListener mOnBannerItemClickListener;

    private Handler mHandler = new Handler();

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            mHandler.postDelayed(mRunnable, 5000);
        }
    };


    //定义接口，用来回调BannerItem 的点击事件
    public interface OnBannerItemClickListener {
        void onClick(int position);
    }

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    //初始化布局
    private void init() {
        //获取布局和控件
        View view = View.inflate(mContext, R.layout.view_bannerview, this);
        mViewPager = (ViewPager) view.findViewById(R.id.banner_viewpager);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_points);
    }

    //给Banner 中的viewpager设置数据
    public void setList(List<String> list) {
        if (mList.size() == 0) {
            mList.addAll(list);
            mItemCount = mList.size();
            initView();
        }
    }

    //Banner item的监听事件
    public void setOnBannerItemClickListener(OnBannerItemClickListener onBannerItemClickListener) {
        mOnBannerItemClickListener = onBannerItemClickListener;
    }

    //初始化视图
    private void initView() {
        //给ViewPager设置Adapter
        BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(mList, mContext);
        mViewPager.setAdapter(bannerPagerAdapter);
        //初始化底部点指示器
        initIndicator(mList, mContext);

        mViewPager.setCurrentItem(500 * mItemCount);

        //给ViewPager 设置滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switchIndicator(position % mItemCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                cancelRecycle();
                break;
            case MotionEvent.ACTION_CANCEL:

            case MotionEvent.ACTION_UP:
                startRecycle();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    //初始化底部点指示器
    private void initIndicator(List<String> mList, Context mContext) {
        mIndicator = new ImageView[mItemCount];
        // 获取点图片并设置参数
        for (int i = 0; i < mList.size(); i++) {
            //设置圆点的大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(8, 0, 8, 0);
//            params.gravity = Gravity.CENTER;
            ImageView imageView = new ImageView(mContext);
            mIndicator[i] = imageView;
            if (i == 0) {
                mIndicator[i].setBackgroundResource(R.mipmap.point_selected);
            } else {
                mIndicator[i].setBackgroundResource(R.mipmap.point_normal);
            }
            mLinearLayout.addView(imageView, params);
        }
        if (mItemCount == 1) {
            mLinearLayout.setVisibility(View.GONE);
        } else {
            mLinearLayout.setVisibility(View.VISIBLE);
        }
    }

    //切换底部指示点
    private void switchIndicator(int selectItems) {
        for (int i = 0; i < mIndicator.length; i++) {
            if (i == selectItems) {
                mIndicator[i].setBackgroundResource(R.mipmap.point_selected);
            } else {
                mIndicator[i].setBackgroundResource(R.mipmap.point_normal);
            }
        }
    }


    private void startRecycle() {
        mHandler.postDelayed(mRunnable, 5000);
    }

    private void cancelRecycle() {
        mHandler.removeCallbacks(mRunnable);
    }


    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        if (visibility == VISIBLE) {
            startRecycle();
        } else {
            cancelRecycle();
        }
    }

    //Banner 自定义适配器
    private class BannerPagerAdapter extends PagerAdapter {
        private List<String> imagesUrl;
        private Context mContext;

        public BannerPagerAdapter(List<String> imagesUrl, Context context) {
            this.imagesUrl = imagesUrl;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mItemCount == 1 ? 1 : Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            Log.e("lyf", "开始");
            Log.e("lyf", "开始" + position);

            View ret = null;
            ImageView imageView = new ImageView(mContext);
            //图片拉伸
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            final int location = position % mItemCount;
            //联网取图片，根据自己的情况修改
            Glide.with(mContext).load(imagesUrl.get(location)).placeholder(R.mipmap.sample_img).into(imageView);
            ret = imageView;
            container.addView(ret);
            ret.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnBannerItemClickListener.onClick(location);
                }
            });
            return ret;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
