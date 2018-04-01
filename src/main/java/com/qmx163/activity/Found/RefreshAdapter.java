package com.qmx163.activity.Found;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.data.bean.Mebean.LessonItemDetalEn;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 刘楠 on 2016/9/10 0010.18:06
 */
public class RefreshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    LayoutInflater mInflater;
    List<LessonItemDetalEn> mDatas;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 0;
    //正在加载中
    public static final int LOADING_MORE = 1;
    //没有加载更多 隐藏
    public static final int NO_LOAD_MORE = 2;


    //上拉加载更多状态-默认为0
    private int mLoadMoreStatus = 2;
    //列表点击回调函数
    private FoundHotNewListFragAdapter.OnItemClickListener mOnItemClickListener;

    public RefreshAdapter(Context context, List<LessonItemDetalEn> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    //设置列表点击回调函数
    public void setmOnItemClickListener(FoundHotNewListFragAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View itemView = mInflater.inflate(R.layout.fragment_foundfrag_hot_new_list_item, parent, false);
            return new ItemViewHolder(itemView);
        } else if (viewType == TYPE_FOOTER) {
            View itemView = mInflater.inflate(R.layout.load_more_footview_layout, parent, false);
            return new FooterViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, int position) {

        if (holders instanceof ItemViewHolder) {
            ItemViewHolder holder = (ItemViewHolder) holders;
            // 绑定数据操作
            LessonItemDetalEn listBean = (LessonItemDetalEn) mDatas.get(position);
            if (TextUtils.equals(listBean.getName()+"","")||listBean.getName() == null){
                holder.lessonName.setText(listBean.getLessonName());
            }else {
            holder.lessonName.setText(listBean.getLessonName()+"【"+listBean.getName()+"】");
            }
            holder.teacherName.setText(listBean.getTeacherName());
            holder.subjectName.setText(listBean.getSubjectName());
            if (listBean.getBeginTime()== null){
                holder.llNewTimer.setVisibility(View.GONE);
            }else {
            holder.newTimer.setText(listBean.getBeginTime() + "");

            }
            holder.lookTimer.setText(listBean.getDurationTime() + "分钟");
            holder.plCount.setText(listBean.getConcernAmount() + "");
            // 状态 -1删除 1直播 2预告 3结束 4点播 5回放
            int status = (int) listBean.getStatus();
            if (status == 5){
                holder.lookSize.setText(listBean.getPlaybackAmount() + "人回看");
            }else {

            holder.lookSize.setText(listBean.getAmount() + "人观看");
            }
            if (status == 2) {
                holder.status.setText("预告");
                holder.status.setBackgroundResource(R.drawable.text_corner_red);
            } else if (status == 1) {
                holder.status.setText("直播中..");
                holder.status.setBackgroundResource(R.drawable.text_corner_green);
            } else if (status == 5) {
                holder.status.setText("回看");
                holder.status.setBackgroundResource(R.drawable.text_corner_orange);
            }else if (status == 3) {
                holder.status.setText("结束");
                holder.status.setBackgroundResource(R.drawable.text_corner_gray);
            }else if (status == 4) {
                holder.status.setText("点播");
                holder.status.setBackgroundResource(R.drawable.text_corner_red);
            }else {
                holder.status.setText("预告");
                holder.status.setBackgroundResource(R.drawable.text_corner_red);
            }
            //头像teacherImg
            Glide.with(mContext).load(listBean.getTeacherImg()).centerCrop().dontAnimate().error(R.mipmap.moren).crossFade().into((ImageView) holder.teaIco);
            //显示缩略图
            Glide.with(mContext).load(listBean.getIntroVideoImg()).centerCrop().dontAnimate().error(R.mipmap.moren).crossFade().into((ImageView) holder.introVideoImg);
            if (mOnItemClickListener != null) {
                final int finalPosition = position;
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClickListener.onItemClick(v, finalPosition);
                    }
                });
            }
        } else if (holders instanceof FooterViewHolder) {


            FooterViewHolder footerViewHolder = (FooterViewHolder) holders;


            switch (mLoadMoreStatus) {
                case PULLUP_LOAD_MORE:
                    footerViewHolder.mTvLoadText.setText("上拉加载更多...");
                    footerViewHolder.rlView.setVisibility(View.VISIBLE);
                    break;
                case LOADING_MORE:
                    footerViewHolder.mTvLoadText.setText("正加载更多...");
                    footerViewHolder.rlView.setVisibility(View.VISIBLE);
                    break;
                case NO_LOAD_MORE:
                    //隐藏加载更多
                    footerViewHolder.rlView.setVisibility(View.GONE);
                    break;

            }
        }

    }

    @Override
    public int getItemCount() {
        //RecyclerView的count设置为数据总条数+ 1（footerView）
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (position + 1 == getItemCount()) {
//            //最后一个item设置为footerView
//            return TYPE_FOOTER;
//        } else {
//            return TYPE_ITEM;
//        }
        return TYPE_ITEM;

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tea_ico)
        ImageView teaIco;
        @BindView(R.id.teacherName)
        TextView teacherName;
        @BindView(R.id.subjectName)
        TextView subjectName;
        @BindView(R.id.introVideoImg)
        ImageView introVideoImg;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.lessonName)
        TextView lessonName;
        @BindView(R.id.new_timer)
        TextView newTimer;
        @BindView(R.id.ll_new_timer)
        LinearLayout llNewTimer;
        @BindView(R.id.look_timer)
        TextView lookTimer;
        @BindView(R.id.look_size)
        TextView lookSize;
        @BindView(R.id.pl_count)
        TextView plCount;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            initListener(itemView);
        }

        private void initListener(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "poistion " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pbLoad)
        ProgressBar mPbLoad;
        @BindView(R.id.tvLoadText)
        TextView mTvLoadText;
        @BindView(R.id.loadLayout)
        LinearLayout mLoadLayout;
        @BindView(R.id.rl_view)
        RelativeLayout rlView;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    //删除全部数据
    public void removeAll(List<LessonItemDetalEn> listdata) {
        mDatas.removeAll(listdata);
        notifyDataSetChanged();
    }

    //添加列表数据
    public void addListItem(List<LessonItemDetalEn> listdata) {
        mDatas.addAll(listdata);
        notifyDataSetChanged();
    }

    /**
     * 更新加载更多状态
     *
     * @param status
     */
    public void changeMoreStatus(int status) {
        mLoadMoreStatus = status;
        notifyDataSetChanged();
    }
}
