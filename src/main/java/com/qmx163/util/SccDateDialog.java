package com.qmx163.util;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.WheelAdapter;
import com.bigkoo.pickerview.lib.WheelView;
import com.bigkoo.pickerview.listener.OnItemSelectedListener;
import com.qmx163.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class SccDateDialog extends DialogFragment {

    public static final String TAG = "SccDateDialog";
    private TextView confrim, cancel;
    private WheelView year, month, day;
    private List<String> years;
    private List<String> months;
    private List<String> days;


    public interface onDialogClickListener{
        void onClickView(String mStringYear, String mStringMonth, String mStringDay);
    }


    public void setOnDialogClickListener(onDialogClickListener onDialogClickListener) {
        mOnDialogClickListener = onDialogClickListener;
    }

    private onDialogClickListener mOnDialogClickListener;


    public String getStringYear() {
        return mStringYear;
    }

    public String getStringMonth() {
        return mStringMonth;
    }

    public String getStringDay() {
        return mStringDay;
    }

    private String mStringYear;
    private String mStringMonth;
    private String mStringDay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_calendar, null);
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
                    mOnDialogClickListener.onClickView(mStringYear,mStringMonth,mStringDay);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date(System.currentTimeMillis()));
        String[] strings = date.split("-");

        year.setCurrentItem(years.indexOf(strings[0]));
        Log.e(TAG, "initWheelViews: setCurrentItem(3)--->"+3 );
        mStringYear = years.get(years.indexOf(strings[0]));
        Log.e(TAG, "initWheelViews: getCurrentItem()--->"+year.getCurrentItem() );
        year.setCyclic(false);
        year.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return years.size();
            }

            @Override
            public Object getItem(int index) {
                return years.get(index);
            }

            @Override
            public int indexOf(Object o) {
                return years.indexOf(o);
            }
        });
        year.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mStringYear = years.get(year.getCurrentItem());
            }
        });
        month.setCurrentItem(months.indexOf(strings[1]));
        mStringMonth = months.get(months.indexOf(strings[1]));
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
        day.setCurrentItem(days.indexOf(strings[2]));
        mStringDay = days.get(days.indexOf(strings[2]));
        day.setCyclic(false);
        day.setAdapter(new WheelAdapter() {
            @Override
            public int getItemsCount() {
                return days.size();
            }

            @Override
            public Object getItem(int index) {
                return days.get(index);
            }

            @Override
            public int indexOf(Object o) {
                return days.indexOf(o);
            }
        });
        day.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                mStringDay = days.get(day.getCurrentItem());
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        years = new ArrayList<>();
        months = new ArrayList<>();
        days = new ArrayList<>();
        for (int i = 1900; i < 2021; i++) {
            years.add(i + "");
        }
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                months.add("0" + i);
            } else {
                months.add(i + "");
            }

        }
        for (int i = 1; i <= 31; i++) {
            if (i < 10) {
                days.add("0" + i);
            } else {
                days.add(i + "");
            }
        }
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        confrim = (TextView) getView().findViewById(R.id.confrim);
        cancel = (TextView) getView().findViewById(R.id.cancel);
        year = (WheelView) getView().findViewById(R.id.year);
        month = (WheelView) getView().findViewById(R.id.month);
        day = (WheelView) getView().findViewById(R.id.day);
    }
}
