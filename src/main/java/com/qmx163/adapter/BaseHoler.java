package com.qmx163.adapter;

import android.view.View;

import butterknife.ButterKnife;

/**
 * 初始ButterKnife
 *
 * Created by 邓靖 on  2017/3/17  17:21
 */
public abstract class BaseHoler {

    private View convertView;

    public void setConvertView(View convertView) {
        this.convertView = convertView;
        ButterKnife.bind(this, convertView);
    }

    public View getConvertView() {
        return convertView;
    }

    public int getid(){
        return getlayoutid();
    }

    public abstract int getlayoutid();
}
