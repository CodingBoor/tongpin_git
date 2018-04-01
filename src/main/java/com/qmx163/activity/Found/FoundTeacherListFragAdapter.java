package com.qmx163.activity.Found;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.data.bean.Mebean.FxTeacher;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现界面 名师 列表 适配器
 * Created by lyf on 2017/7/4.
 */

public class FoundTeacherListFragAdapter extends RecyclerView.Adapter<FoundTeacherListFragAdapter.ViewHolder> {


    private RecyclerView mRecyclerView;

    private Context mContext;
    private int Index;
    private List<FxTeacher.DataBean.ListBean> dataSource = new ArrayList<>();

    //列表点击回调函数
    private OnItemClickListener mOnItemClickListener;

    //设置列表点击回调函数
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    public FoundTeacherListFragAdapter(List<FxTeacher.DataBean.ListBean> list, Context mContext,int index) {
        this.dataSource = list;
        this.mContext = mContext;
        this.Index=index;
    }

    //创建新的View 被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.fragment_foundfrag_teacher_list, parent, false));
        return holder;

    }

    //将数据与界面进行绑定操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定数据操作
            holder.tea_name.setText(dataSource.get(position).getName());
             holder.tea_organizationName.setText(dataSource.get(position).getOrganizationName());
             holder.tv_amount.setText(dataSource.get(position).getConcernAmount()+"");
             holder.tv_course_sise.setText(dataSource.get(position).getTotalLessonPeriods()+"");
        //显示缩略图
        Glide.with(mContext).load(dataSource.get(position).getImg()).dontAnimate().centerCrop()
                .error(R.mipmap.moren).crossFade().into((ImageView)holder.tea_ico );
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
        public ImageView tea_ico;
        public TextView tea_name;
        public TextView tea_organizationName;
        private TextView tv_amount;
        private TextView tv_course_sise;

        public ViewHolder(View view) {
            super(view);
            tea_ico = (ImageView) view.findViewById(R.id.tea_ico);
            tea_name = (TextView) view.findViewById(R.id.tea_name);
            tea_organizationName = (TextView) view.findViewById(R.id.tea_organizationName);
            tv_amount = (TextView) view.findViewById(R.id.tv_amount);
            tv_course_sise = (TextView) view.findViewById(R.id.tv_course_sise);

        }
    }

    //添加数据
    public void addItem(int postion, FxTeacher.DataBean.ListBean ss) {
        dataSource.add(postion, ss);
        notifyItemInserted(postion);
    }

    //添加列表数据
    public void addListItem(List<FxTeacher.DataBean.ListBean> listdata) {
        dataSource.clear();
        dataSource.addAll(listdata);
        notifyDataSetChanged();
    }

    //删除数据
    public void removeItme(int postion) {
        dataSource.remove(postion);
        notifyItemRemoved(postion);
    }
    //删除数据
    public void removeAll(List<FxTeacher.DataBean.ListBean> listdata) {
        dataSource.removeAll(listdata);
        notifyDataSetChanged();
    }
    //列表点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}


