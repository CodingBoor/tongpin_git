package com.qmx163.activity.FocusOn;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmx163.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */

public class TeacherIntroduListFragAdapter  extends RecyclerView.Adapter<TeacherIntroduListFragAdapter.ViewHolder> {

    private RecyclerView mRecyclerView;

    private Context mContext;

    private List<String> dataSource = new ArrayList<>();

    //列表点击回调函数
    private OnItemClickListener mOnItemClickListener;

    //设置列表点击回调函数
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public TeacherIntroduListFragAdapter(List<String> list, Context mContext) {
        this.dataSource = list;
        this.mContext = mContext;
    }

    //创建新的View 被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_teaintro, parent, false));
        return holder;

    }

    //将数据与界面进行绑定操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        // 绑定数据操作
//            holder.mTextView.setText(dataSource.get(position));

        //设置列表点击事件
        if (mOnItemClickListener != null) {
            final int finalPosition = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(v, finalPosition);
                }
            });
        }


    }

    @Override
    public int getItemCount() {

        return dataSource.size();
    }


    //自定义ViewHolder,持有每个Item的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View view) {
            super(view);
//            mTextView = (TextView) view.findViewById(R.id.);
        }
    }

    //添加数据
    public void addItem(int postion, String ss) {
        dataSource.add(postion, ss);
        notifyItemInserted(postion);
    }

    //添加列表数据
    public void addListItem(List<String> listdata) {
        dataSource.addAll(listdata);
        notifyDataSetChanged();
    }

    //删除数据
    private void removeItme(int postion) {
        dataSource.remove(postion);
        notifyItemRemoved(postion);
    }

    //列表点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}

