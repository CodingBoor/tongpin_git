//package com.retrofit.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.ashokvarma.bottomnavigation.BottomNavigationBar;
//import com.retrofit.R;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//
//public class TestAdapter extends BaseAdapter {
//    Context con;
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder v;
//        if (view == null) {
//            view = LayoutInflater.from(con).inflate(R.layout.activity_main, null);
//            v = new ViewHolder(view);
//            view.setTag(v);
//        } else {
//            v = (ViewHolder) view.getTag();
//        }
//
//        v.button1.setText("");
//        return view;
//    }
//
//
//    static class ViewHolder {
//        @BindView(R.id.iamgeleft)
//        ImageView iamgeleft;
//        @BindView(R.id.tv_left)
//        TextView tvLeft;
//        @BindView(R.id.ibtn_left)
//        RelativeLayout ibtnLeft;
//        @BindView(R.id.tv_title)
//        TextView tvTitle;
//        @BindView(R.id.tv_right)
//        TextView tvRight;
//        @BindView(R.id.tv1)
//        TextView tv1;
//        @BindView(R.id.button1)
//        Button button1;
//        @BindView(R.id.button2)
//        Button button2;
//        @BindView(R.id.editText)
//        EditText editText;
//        @BindView(R.id.editText1)
//        EditText editText1;
//        @BindView(R.id.button3)
//        Button button3;
//        @BindView(R.id.button4)
//        Button button4;
//        @BindView(R.id.button6)
//        Button button6;
//        @BindView(R.id.button7)
//        Button button7;
//        @BindView(R.id.button9)
//        Button button9;
//        @BindView(R.id.button10)
//        Button button10;
//        @BindView(R.id.tv2)
//        TextView tv2;
//        @BindView(R.id.image)
//        ImageView image;
//        @BindView(R.id.NestedScrollView)
//        android.support.v4.widget.NestedScrollView NestedScrollView;
//        @BindView(R.id.bottom_navigation_bar)
//        BottomNavigationBar bottomNavigationBar;
////        @BindView(R.id.activity_main)
////        CoordinatorLayout activityMain;
//
//        ViewHolder(View view) {
//            ButterKnife.bind(this, view);
//        }
//    }
//}
