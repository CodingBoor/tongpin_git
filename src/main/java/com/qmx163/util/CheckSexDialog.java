package com.qmx163.util;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.qmx163.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class CheckSexDialog extends DialogFragment {

    public static final String TAG = "SccDateDialog";
    private TextView confrim, cancel;
    private WheelView  month;
    private List<String> months;


    public interface onDialogClickListener{
        void onClickView(String mStringMonth);
    }


    public void setOnDialogClickListener(onDialogClickListener onDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener;
    }

    private onDialogClickListener mOnDialogClickListener;

    public String getStringMonth() {
        return mStringMonth;
    }

    private String mStringMonth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_check_sex, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initDatas();
        initWheelViews();
        getSelected();
    }


    /**
     * 弹出选择的时间
     */
    private void getSelected() {
        confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnDialogClickListener!=null){
                    mOnDialogClickListener.onClickView(mStringMonth);
                }
//                Toast.makeText(getActivity(), mStringYear + "-" + mStringMonth + "-" + mStringDay, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    /**
     * 给WheelView添加数据
     */
    private void initWheelViews() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String date = sdf.format(new Date(System.currentTimeMillis()));
//        String[] strings = date.split("-");


        month.setCurrentItem(months.size());
        mStringMonth = months.get(1);
        month.setCyclic(false);
        month.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return months.size();
            }

            @Override
            public Object getItem(int index) {
                return months.get(index);
            }

            @Override
            public int indexOf(Object o) {
                return months.indexOf(o);
            }
        });
        month.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mStringMonth = months.get(month.getCurrentItem());
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initDatas() {

        months = new ArrayList<>();


        for (int i = 1; i <= 2; i++) {
            if (i ==1) {
                months.add("男");
            } else {
                months.add("女");
            }

        }
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        confrim = (TextView) getView().findViewById(R.id.confrim);
        cancel = (TextView) getView().findViewById(R.id.cancel);
        month = (WheelView) getView().findViewById(R.id.month);
    }
}
