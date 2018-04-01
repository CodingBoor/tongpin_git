package com.qmx163.activity.Found;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.data.bean.Mebean.LessonItemDetalEn;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现界面 最新和热门 列表 适配器  关注
 * Created by lyf on 2017/7/4.
 */

public class FoundHotNewListFragAdapter<T> extends RecyclerView.Adapter<FoundHotNewListFragAdapter.ViewHolder> {


    private RecyclerView mRecyclerView;

    private View VIEW_HEADER;
    private View VIEW_FOOTER;

    private int TYPE_NORMAL = 1000;
    private int TYPE_HEADER = 1001;
    private int TYPE_FOOTER = 1002;

    private Context mContext;

    private List<T> dataSource = new ArrayList<>();

    //列表点击回调函数
    private OnItemClickListener mOnItemClickListener;
    //头部点击回调事件
    private OnHearderViewClickListener mOnHearderViewClickListener;
    //底部点击回调事件
    private OnFooterViewClickListener mOnFooterViewClickListener;

    //设置列表点击回调函数
    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    //设置头部点击回调函数
    public void setmOnHearderViewClickListener(OnHearderViewClickListener mOnHearderViewClickListener) {
        this.mOnHearderViewClickListener = mOnHearderViewClickListener;
    }

    //设置底部点击事件
    public void setmOnFooterViewClickListener(OnFooterViewClickListener mOnFooterViewClickListener) {
        this.mOnFooterViewClickListener = mOnFooterViewClickListener;
    }

    public FoundHotNewListFragAdapter(List<T> list, Context mContext) {
        this.dataSource = list;
        this.mContext = mContext;
    }

    //创建新的View 被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            return new ViewHolder(VIEW_FOOTER);
        } else if (viewType == TYPE_HEADER) {
            return new ViewHolder(VIEW_HEADER);
        } else {
            View view = getLayout(R.layout.fragment_foundfrag_hot_new_list_item);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            return new ViewHolder(view);
        }
    }

    //将数据与界面进行绑定操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定头部点击事件
        if (isHeaderView(position)) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnHearderViewClickListener != null) {
                        mOnHearderViewClickListener.onHearderClick(v);
                    }
                }
            });
        }
        //绑定底部点击事件
        if (isFooterView(position)) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnFooterViewClickListener != null) {
                        mOnFooterViewClickListener.onFooterClick(v);
                    }
                }
            });
        }

        //绑定列表数据
        if (!isHeaderView(position) && !isFooterView(position)) {
            if (haveHeaderView()) {
                //如果有头部，列表所在的索引都减1
                position--;
            }
            // 绑定数据操作
            LessonItemDetalEn listBean= (LessonItemDetalEn) dataSource.get(position);
            holder.lessonName.setText(listBean.getLessonName());
            holder.teacherName.setText(listBean.getTeacherName());
            holder.subjectName.setText(listBean.getSubjectName());
            holder.new_timer.setText(listBean.getBeginTime()+"");
            holder.look_size.setText(listBean.getAmount()+"人观看");
            holder.look_timer.setText(listBean.getDurationTime()+"分钟");
            holder.pl_count.setText(listBean.getConcernAmount()+"");
            // 状态 -1删除 0预告 1直播 2回放
            int status= (int) listBean.getStatus();
            if(status==0) {
                holder.status.setText("预告");
            }else if(status==1){
                holder.status.setText("直播");
            }else if(status==2){
                holder.status.setText("回访");
            }
            //头像teacherImg
            Glide.with(mContext).load(listBean.getTeacherImg()).centerCrop().placeholder(R.mipmap.moren).error(R.mipmap.moren).crossFade().into((ImageView)holder.tea_ico );
            //显示缩略图
            Glide.with(mContext).load(listBean.getIntroVideoImg()).centerCrop().placeholder(R.mipmap.moren).error(R.mipmap.moren).crossFade().into((ImageView)holder.introVideoImg );
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


    }

    @Override
    public int getItemCount() {
        int count = (dataSource == null ? 0 : dataSource.size());
        if (VIEW_FOOTER != null) {
            count++;
        }
        if (VIEW_HEADER != null) {
            count++;
        }
        return count;
    }

    /**
     * 重写这个方法，很重要，是加入Header和Footer的关键，我们通过判断item的类型，从而绑定不同的view    *
     */
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)) {
            return TYPE_HEADER;
        } else if (isFooterView(position)) {
            return TYPE_FOOTER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        try {
            if (mRecyclerView == null && mRecyclerView != recyclerView) {
                mRecyclerView = recyclerView;
            }
            ifGridLayoutManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据编号获取布局视图
    private View getLayout(int layoutId) {
        return LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    //自定义ViewHolder,持有每个Item的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lessonName;
        public TextView teacherName;
        public TextView subjectName;
        public ImageView introVideoImg;
        public ImageView tea_ico;
        public TextView new_timer;
        public TextView look_timer;
        public TextView look_size;
        public TextView pl_count;
        public TextView status;
        public ViewHolder(View view) {
            super(view);
            status= (TextView) view.findViewById(R.id.status);
            tea_ico= (ImageView) view.findViewById(R.id.tea_ico);
            lessonName = (TextView) view.findViewById(R.id.lessonName);
            new_timer = (TextView) view.findViewById(R.id.new_timer);
            look_timer = (TextView) view.findViewById(R.id.look_timer);
            look_size = (TextView) view.findViewById(R.id.look_size);
            pl_count = (TextView) view.findViewById(R.id.pl_count);
            teacherName = (TextView) view.findViewById(R.id.teacherName);
            subjectName = (TextView) view.findViewById(R.id.subjectName);
            introVideoImg = (ImageView) view.findViewById(R.id.introVideoImg);
        }
    }

    //添加数据
    public void addItem(int postion, T ss) {
        dataSource.add(postion, ss);
        notifyItemInserted(postion);
    }

    //添加列表数据
    public void addListItem(List<T> listdata) {

        dataSource.addAll(listdata);
        notifyDataSetChanged();
    }

    //删除数据
    public void removeItme(int postion) {
        dataSource.remove(postion);
        notifyItemRemoved(postion);
    }
    //删除全部数据
    public void removeAll(List<T> listdata) {
        dataSource.removeAll(listdata);
        notifyDataSetChanged();
    }
    //列表点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    //头部点击事件
    public interface OnHearderViewClickListener {
        void onHearderClick(View view);
    }

    //底部点击事件
    public interface OnFooterViewClickListener {
        void onFooterClick(View view);
    }

    //添加头部控件
    public void addHearderView(View hearderView) {
        if (haveHeaderView()) {
            throw new IllegalStateException("头部已经存在");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            hearderView.setLayoutParams(params);
            VIEW_HEADER = hearderView;
            ifGridLayoutManager();
            notifyItemRemoved(0);
        }
    }

    //添加底部控件
    public void addFooterView(View footerView) {
        if (haveFooterView()) {
            throw new IllegalStateException("底部已经存在");
        } else {
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            footerView.setLayoutParams(params);
            VIEW_FOOTER = footerView;
            ifGridLayoutManager();
            notifyItemInserted(getItemCount() - 1);
        }
    }

    //是否是GridLaout布局
    private void ifGridLayoutManager() {
        if (mRecyclerView == null) return;
        final RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager.SpanSizeLookup originalSpanSizeLookup = ((GridLayoutManager) layoutManager).getSpanSizeLookup();
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
        }
    }

    //是否已经包含头部
    private boolean haveHeaderView() {
        return VIEW_HEADER != null;
    }

    //是否已经包含底部
    private boolean haveFooterView() {
        return VIEW_FOOTER != null;
    }

    //是否是头部
    private boolean isHeaderView(int position) {
        return haveHeaderView() && position == 0;
    }

    //是否是底部
    private boolean isFooterView(int position) {
        return haveFooterView() && position == getItemCount() - 1;
    }

}


