package com.qmx163.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qmx163.R;
import com.zhy.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本adapter
 * Created by lzp on 2016/10/20.
 */

public abstract class BaseAdapter<T> extends CommonAdapter<T> {
    public BaseAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }
    public BaseAdapter(Context context, int layoutId) {
        this(context, layoutId, new ArrayList());
    }


    //加载图片，设置默认图片
    public void loadImage(ImageView imageView,String url,int res){
        Glide.with(mContext).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(res).error(res).into(imageView);

    }

    public void loadImage(ImageView imageView,String url){
        loadImage(imageView,url, R.mipmap.logo);
    }

    public void addData(T data){
        if (mDatas==null){
            mDatas=new ArrayList();
        }
        mDatas.add(data);
        notifyItemInserted(mDatas.size()-1);
    }

    public void addDatas(List<T> datas){
        if (mDatas==null){
            mDatas=new ArrayList();
        }
        mDatas.addAll(datas);
//        notifyItemRangeInserted(mDatas.size()-datas.size(),datas.size());
        notifyDataSetChanged();
    }

    public void remove(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
        if(position != mDatas.size()){      // 这个判断的意义就是如果移除的是最后一个，就不用管它了
            notifyItemRangeChanged(position, mDatas.size() - position);
        }
    }

    public void clear(){
        if(mDatas!=null){
            notifyItemRangeRemoved(0,mDatas.size());
            mDatas.clear();
        }
    }
}
