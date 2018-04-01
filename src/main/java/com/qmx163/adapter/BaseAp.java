package com.qmx163.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

/**
 * Created by CX4 on 2017/3/17.
 */

public abstract class BaseAp<E, T extends BaseHoler> extends BaseAdapter {

    public List<E> mDatas;

    public Context mContext;

    public BaseAp(Context context, List<E> li) {
        super();
        this.mContext = context;
        this.mDatas = li;
    }

    @Override
    public int getCount() {
        if (mDatas == null) {
            return 0;
        } else
            return mDatas.size();
    }

    public E getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        T holder = null;
        E item = this.getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(), parent, false);
            holder = bindHoler();
            holder.setConvertView(convertView);
            convertView.setTag(holder);
        } else {
            holder = (T) convertView.getTag();
        }
        bindData(holder, position, item);
        return convertView;
    }

    public abstract T bindHoler();

    public abstract int getLayoutResId();

    public abstract void bindData(T holder, int position, E item);

    public List<E> getData() {
        return this.mDatas;
    }


    /**
     * 添加新数据，listview上方
     * <p>
     * Created by 邓靖 on  2017/3/17  17:13
     */
    public void addNewData(List<E> data) {
        if (data != null) {
            this.mDatas.addAll(0, data);
            this.notifyDataSetChanged();
        }

    }

    /**
     * 添加更多数据，listview下方
     * <p>
     * Created by 邓靖 on  2017/3/17  17:14
     */
    public void addMoreData(List<E> data) {
        if (data != null) {
            this.mDatas.addAll(this.mDatas.size(), data);
            this.notifyDataSetChanged();
        }

    }

    /**
     * 设置新数据，清除listview重新添加
     * <p>
     * Created by 邓靖 on  2017/3/17  17:14
     */
    public void setData(List<E> data) {
        if (data != null) {
            this.mDatas = data;
        } else {
            this.mDatas.clear();
        }
        this.notifyDataSetChanged();
    }

    /**
     * 清空listview
     * <p>
     * Created by 邓靖 on  2017/3/17  17:14
     */
    public void clear() {
        this.mDatas.clear();
        this.notifyDataSetChanged();
    }

    /**
     * 根据下标移除单条数据
     * <p>
     * Created by 邓靖 on  2017/3/17  17:15
     */
    public void removeItem(int position) {
        this.mDatas.remove(position);
        this.notifyDataSetChanged();
    }

    /**
     * 根据值移除单条数据
     * <p>
     * Created by 邓靖 on  2017/3/17  17:15
     */
    public void removeItem(E model) {
        this.mDatas.remove(model);
        this.notifyDataSetChanged();
    }

    /**
     * 指定下标添加数据
     *
     * Created by 邓靖 on  2017/3/17  17:16
     */
    public void addItem(int position, E model) {
        this.mDatas.add(position, model);
        this.notifyDataSetChanged();
    }

    /**
     * 最前面添加一条数据
     *
     * Created by 邓靖 on  2017/3/17  17:17
     */
    public void addFirstItem(E model) {
        this.addItem(0, model);
    }

    /**
     * 最后一个位置添加数据
     *
     * Created by 邓靖 on  2017/3/17  17:19
     */
    public void addLastItem(E model) {
        this.addItem(this.mDatas.size(), model);
    }

    /**
     * 替换指定下标的数据
     *
     * Created by 邓靖 on  2017/3/17  17:19
     */
    public void setItem(int location, E newModel) {
        this.mDatas.set(location, newModel);
        this.notifyDataSetChanged();
    }

    /**
     * 替换指定数据的数据
     *
     * Created by 邓靖 on  2017/3/17  17:19
     */
    public void setItem(E oldModel, E newModel) {
        this.setItem(this.mDatas.indexOf(oldModel), newModel);
    }

    /**
     * 移动指定下标数据到指定下标
     *
     * Created by 邓靖 on  2017/3/17  17:21
     */
    public void moveItem(int fromPosition, int toPosition) {
        Collections.swap(this.mDatas, fromPosition, toPosition);
        this.notifyDataSetChanged();
    }
}
