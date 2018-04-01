package com.qmx163.util.manager;

import android.content.Context;

import com.qmx163.adapter.BaseAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.List;

/**
 * RecycleView数据管理
 * Created by lzp on 2016/10/20.
 */
public abstract class BaseRecycleViewMannager<T> {
   protected Context context;
    BaseAdapter adapter;

    public BaseRecycleViewMannager(Context context) {
        this.context = context;
        adapter = initAdapter();
    }


    public abstract BaseAdapter initAdapter();

    public void addData(T data) {
        adapter.addData(data);
    }

    public void addDatas(List<T> datas) {
        adapter.addDatas(datas);
    }

    public void clear() {
        adapter.clear();
    }

    public List<T> getDatas() {
        return adapter.getDatas();
    }

    public void setOnItemClickListener (MultiItemTypeAdapter.OnItemClickListener onItemClickListener){
        adapter.setOnItemClickListener(onItemClickListener);
    }

}
