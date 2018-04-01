package com.qmx163.util.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.listener.OnSelectOptionLisenter;

import java.util.ArrayList;
import java.util.List;


/**
 * 回看列表popu
 * Created by likai on 2017/8/8.
 */
public class CirclePopuwindow extends PopupWindow  {
    Context activity;
    RecyclerAdapter popuAdapter;
    List<String> popuList = new ArrayList<>();
    public CirclePopuwindow(Context context) {
        super(context);
        this.activity=  context;
        initView(context);
    }


    public CirclePopuwindow(Context context, int width, int height,List popuList) {
        super(width, height);
        this.activity=  context;
        this.popuList = popuList;
        initView(context);
    }

    private void initView(Context context) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_recyclerview_noswipe,null,false);
        setContentView(view);
        RecyclerView popuRecycler = (RecyclerView) view.findViewById(R.id.recycler_view);
        TextView tv_dismis = (TextView) view.findViewById(R.id.tv_dismis);

        tv_dismis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //设置点击窗口外边窗口消失
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        setFocusable(true);
        popuAdapter = new RecyclerAdapter<String>(context,popuList,R.layout.circle_popupwindow) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, int position) {
                helper.setText(R.id.name,item);
            }

        };
        popuRecycler.setHasFixedSize(true);
        popuRecycler.setNestedScrollingEnabled(false);
        popuRecycler.setLayoutManager(new GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false));
        popuRecycler.setAdapter(this.popuAdapter);
        popuAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                dismiss();
                if(onselectoptionLisenter!=null){
                    onselectoptionLisenter.selectOPtion(position);
                }
            }
        });
    }
    OnSelectOptionLisenter onselectoptionLisenter;
    public void setOnSelectOptionLisenter(OnSelectOptionLisenter onselectoptionLisenter){
        this.onselectoptionLisenter=onselectoptionLisenter;
    }



//    @Override
//    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//        List<Option> datas = delegate.getDatas();
//        for (int i=0;i<datas.size();i++){
//            Option option= datas.get(i);
//            if(i==position){
//                option.setColor(ContextCompat.getColor(activity,R.color.gray_deep));
//            }else{
//                option.setColor(ContextCompat.getColor(activity,R.color.white));
//            }
//        }
//        delegate.getAdapter().notifyDataSetChanged();
//        dismiss();
//        if(onselectoptionLisenter!=null){
////            onselectoptionLisenter.selectOPtion(datas.get(position));
//        }
//    }

//    public void setData(List<String> popuList){
//        if(popuAdapter!=null){
//            this.popuList = popuList;
//            popuAdapter.notifyDataSetChanged();
//        }
//    }



//    class PopupwidnowDelegate extends RecycleViewDelegate {
//
//        public PopupwidnowDelegate(Context context, View view, int idres) {
//            super(context, view, idres);
//        }
//
//        @Override
//        public BaseAdapter initAdapter() {
//            return new BaseAdapter<Option>(activity,R.layout.circle_popupwindow, DateUtil.makeCircleData(activity)) {
//
//                @Override
//                protected void convert(ViewHolder holder, Option option, int position) {
//                    holder.setText(R.id.name,option.getContent());
//                    holder.setTextColor(R.id.name,option.getColor());
//                }
//            };
//        }
//
//        @Override
//        protected RecyclerView.ItemDecoration getItemDecoration(int ...res) {
//            return new HorizontalDividerItemDecoration.Builder(activity)
//                    .color(ContextCompat.getColor(activity, R.color.white))
//                    .size(DensityUtil.dip2px(activity, 1))
//                    .build();
//        }
//    }
}
