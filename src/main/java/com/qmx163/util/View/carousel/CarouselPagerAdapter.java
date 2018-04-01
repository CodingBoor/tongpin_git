/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package com.qmx163.util.View.carousel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.application.MyApplication;
import com.qmx163.util.ToastUtil;

import java.util.List;

/**
 * AdvertImagePagerAdapter
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-23
 */
public class CarouselPagerAdapter<T> extends RecyclingPagerAdapter implements OnClickListener {

    private Context context;
    private List<T> imageIdList;

    private int size;
    private boolean isInfiniteLoop;

    public CarouselPagerAdapter(Context context, List<T> imageIdList) {
        this.context = context;
        this.imageIdList = imageIdList;
        this.size = imageIdList.size();
        isInfiniteLoop = true;
    }


    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? size * 100 : size;
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return position % size;
    }

    @Override
    public View getView(int position, View view, final ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_caoursel_item, null, false);
            holder.image = (ImageView) view.findViewById(R.id.img);
            int width = MyApplication.getInstance().getmWidth();
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(width, (int) (width * (305 / 750.0)));
            holder.image.setLayoutParams(lp);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final Carousel carousel = (Carousel) imageIdList.get(position % size);
        //默认图片  和加载错误的图片
        Glide.with(context).load(carousel.getImg()).placeholder(R.mipmap.all).error(R.mipmap.all).into(holder.image);
        holder.image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.toastShortShow(v.getContext(), "点击了轮播图...");
            }
        });
        return view;
    }

    private class ViewHolder {
        ImageView image;
    }

    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public CarouselPagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }


    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

    }
}
