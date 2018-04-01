package com.qmx163.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;

import com.qmx163.R;

import java.util.List;

/**
 * 用于有tab切换的页面
 * Created by lzp on 2016/8/1.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> list_fragment;                         //fragment列表
    private List<String> list_Title;//tab名的列表

    public TabAdapter(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_Title = list_Title;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = list_fragment.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    //此方法用来显示tab上的名字
    @Override
    public CharSequence getPageTitle(int position) {

//        SpannableString ss = new SpannableString(list_Title.get(position % list_Title.size()));
//        ss.setSpan(new ForegroundColorSpan(Color.RED), 0, 2,
//                //setSpan时需要指定的 flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括).
//                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return  ss;

        return list_Title.get(position % list_Title.size());
    }
}
