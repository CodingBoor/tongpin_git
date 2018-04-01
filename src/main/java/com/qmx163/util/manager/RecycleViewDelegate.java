package com.qmx163.util.manager;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qmx163.R;
import com.qmx163.adapter.BaseAdapter;
import com.qmx163.base.BaseFm;


/**
 *  recycle 基本配置
 * Created by lzp on 2016/10/20.
 */
public abstract class RecycleViewDelegate extends BaseRecycleViewMannager {
   protected RecyclerView recyclerView;

    public RecycleViewDelegate(AppCompatActivity activity) {
        this(activity, R.id.recycler_view);
    }

    public RecycleViewDelegate(AppCompatActivity activity, int idres) {
        super(activity);
        recyclerView = (RecyclerView) activity.findViewById(idres);
        initRecycleView();
    }

    public RecycleViewDelegate(BaseFm fragment) {
        this(fragment, R.id.recycler_view);
    }

    public RecycleViewDelegate(BaseFm fragment, int idres) {
        super(fragment.getContext());
        View view = fragment.getView();
        recyclerView = (RecyclerView) view.findViewById(idres);
        initRecycleView();
    }

    public RecycleViewDelegate(Context context, View view, int idres) {
        super(context);
        recyclerView = (RecyclerView) view.findViewById(idres);
        initRecycleView();
    }

    public RecycleViewDelegate(Context context, RecyclerView recyclerView) {
        super(context);
        this.recyclerView = recyclerView;
        initRecycleView();
    }


    protected void initRecycleView() {
        recyclerView.setLayoutManager(getLayoutManager());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(getItemDecoration());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public BaseAdapter getAdapter(){
        return adapter;
    }
    public RecyclerView getRecyclerView(){
        return recyclerView;
    }
    /**
     * 指定 recycleview 的LayoutManager 可重写
     *
     * @return
     */
    protected LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(context);
    }

    protected RecyclerView.ItemDecoration getItemDecoration(int ...res) {
        return new HorizontalDividerItemDecoration.Builder(context)
                .color(res.length==0? ContextCompat.getColor(context, R.color.color_ee): ContextCompat.getColor(context, res[0]))
                .size(2)
                .build();
    }
}
