//package com.retrofit.adapter;
//
//import android.content.Context;
//
//import java.util.List;
//
///**
// * 通用适配器
// *
// * Created by 邓靖 on  2017/3/17  16:45
// */
//public abstract class UniversalAdapter<E,T extends BaseHoler> extends BaseAp<E,T > {
//
//    private int id;
//    private T tt;
//
//    public UniversalAdapter(Context context, List<E> li,T t) {
//        super(context,li);
//       this.id= t.getid();
//        this.tt=t;
//    }
//
//    @Override
//    public T bindHoler() {
//        return tt;
//    }
//
//    @Override
//    public int getLayoutResId() {
//        return id;
//    }
//
//
//
//}
