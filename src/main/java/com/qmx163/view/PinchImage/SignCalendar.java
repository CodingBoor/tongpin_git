package com.qmx163.view.PinchImage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.qmx163.R;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/5.
 */

public class SignCalendar extends ViewFlipper implements GestureDetector.OnGestureListener{

    public static final int COLOR_BG_WEEK_TITLE = Color.parseColor("#FF0000"); // 星期标题背景颜色
    public static final int COLOR_TX_WEEK_TITLE = Color.parseColor("#FFFFFF"); // 星期标题文字颜色
    public static final int BEFORE_TODAY_BACKGROUND = Color.parseColor("#FFE4E4E4"); // 星期标题文字颜色
    public static final int COLOR_TX_THIS_MONTH_DAY = Color.parseColor("#000000"); // 当前月日历数字颜色
    public static final int COLOR_TX_OTHER_MONTH_DAY = Color.parseColor("#ff999999"); // 其他月日历数字颜色
    public static final int COLOR_TX_THIS_DAY = Color.parseColor("#00ff00"); // 当天日历数字颜色
    public static final int COLOR_BG_THIS_DAY = Color.parseColor("#ffcccccc"); // 当天日历背景颜色
    public static final int COLOR_BG_CALENDAR = Color.parseColor("#FFFFFF"); // 日历背景色

    private OnCalendarClickListener onCalendarClickListener;
    private OnCalendarDateChangedListener onCalendarDateChangedListener;


    private int ROW_TOTAL = 5;//日历行数
    private int COLS_TOTAL = 7; // 日历列数
    private String[][] dates = new String[6][7];//当前日历日期
    private float tb;

    private String[] weekday = new String[]{"SUN","MON","TUE","WEN","THU","FRI","SAT"};
    private int calendarYear; //日历年份
    private int canlendarMonth;//日历月份
    private Date thisday = new Date(); // today
    private Date calendarDay;  // 日历这个月第一天

    private LinearLayout currentCanlendar; // 当前显示的日历
    private Map<String, Integer> marksMap = new HashMap<>();//储存某个日子被标记,integer 为bitmap
    private Map<String, Integer> dayBgColorMap = new HashMap<String, Integer>(); // 储存某个日子的背景色


    public SignCalendar(Context context) {
        super(context);
        init();
    }

    public SignCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
//        setBackgroundResource(R.mipmap.ic_launcher);
        //初始化日历
        currentCanlendar = new LinearLayout(getContext());
        currentCanlendar.setOrientation(LinearLayout.VERTICAL);
        currentCanlendar.setLayoutParams(new LinearLayout.LayoutParams(-1,-1));

        //设置日历上的日子(1号)
        calendarYear = thisday.getYear()+1900;
        canlendarMonth = thisday.getMonth();
        calendarDay = new Date(calendarYear - 1900,canlendarMonth,1);

        //此处判断日历显示的行数，（方法比较low）
        if (calendarYear == 2017){
            if (canlendarMonth == 6 || canlendarMonth == 11){
                ROW_TOTAL = 6;
            }else {
                ROW_TOTAL = 5;
            }
        } else if (calendarYear == 2018){
            if (canlendarMonth == 8 || canlendarMonth == 11){
                ROW_TOTAL = 6;
            }else {
                ROW_TOTAL = 5;
            }
        } else if (calendarYear == 2019){
            if (canlendarMonth == 2 || canlendarMonth == 5){
                ROW_TOTAL = 6;
            }else {
                ROW_TOTAL = 5;
            }
        } else if (calendarYear == 2020){
            if (canlendarMonth == 4 || canlendarMonth == 7){
                ROW_TOTAL = 6;
            }else {
                ROW_TOTAL = 5;
            }
        }else {
            ROW_TOTAL = 6;
        }

        //日历加入ViewFlipper
        addView(currentCanlendar);
        //绘制线条框架
        drawFrame(currentCanlendar);

        //填充展示日历
        setCalendarDate();

    }

    private void setCalendarDate() {
        //根据日历的日子获取这一天是星期几
        int weekday = calendarDay.getDay();
        //每个月第一天
        int firstDay = 1;
        //每个月中间号,根据循环会自动++
        int day = firstDay;
        //每个月的最后一天
        int lastDay = getDateNum(calendarDay.getYear(),calendarDay.getMonth());
        //下个月第一天
        int nextMonthDay = 1;
        int lastMonthDay = 1;

        // 填充每一个空格
        for (int i = 0; i < ROW_TOTAL; i++) {
            for (int j = 0; j < COLS_TOTAL; j++) {
                // 这个月第一天不是礼拜天,则需要绘制上个月的剩余几天
                if (i == 0 && j == 0 && weekday != 0) {
                    int year = 0;
                    int month = 0;
                    int lastMonthDays = 0;
                    // 如果这个月是1月，上一个月就是去年的12月
                    if (calendarDay.getMonth() == 0) {
                        year = calendarDay.getYear() - 1;
                        month = Calendar.DECEMBER;
                    } else {
                        year = calendarDay.getYear();
                        month = calendarDay.getMonth() - 1;
                    }
                    // 上个月的最后一天是几号
                    lastMonthDays = getDateNum(year, month);
                    // 第一个格子展示的是几号
                    int firstShowDay = lastMonthDays - weekday + 1;
                    // 上月
                    for (int k = 0; k < weekday; k++) {
                        lastMonthDay = firstShowDay + k;
                        RelativeLayout group = getDateView(0, k);
                        group.setGravity(Gravity.TOP);
                        TextView view = null;
                        if (group.getChildCount() > 0) {
                            view = (TextView) group.getChildAt(0);
                        } else {
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -1);
                            view = new TextView(getContext());
                            view.setLayoutParams(params);
                            view.setGravity(Gravity.CENTER);
                            group.addView(view);
                        }
                        view.setText(Integer.toString(lastMonthDay));
                        view.setTextColor(COLOR_TX_OTHER_MONTH_DAY);
                        dates[0][k] = format(new Date(year, month, lastMonthDay));
                        // 设置日期背景色
                        if (dayBgColorMap.get(dates[0][k]) != null) {
                            view.setBackgroundResource(dayBgColorMap.get(dates[0][k]));
                        } else {
                            view.setBackgroundColor(Color.TRANSPARENT);
                        }
                        // 设置标记
                        setMarker(group, 0, k);
                    }
                    j = weekday - 1;
                    // 这个月第一天是礼拜天，不用绘制上个月的日期，直接绘制这个月的日期
                } else {
                    RelativeLayout group = getDateView(i, j);
                    group.setGravity(Gravity.TOP);
                    TextView view = null;
                    if (group.getChildCount() > 0) {
                        view = (TextView) group.getChildAt(0);
                    } else {
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -1);
                        view = new TextView(getContext());
                        view.setLayoutParams(params);
                        view.setGravity(Gravity.CENTER);
                        group.addView(view);
                    }

                    // 本月
                    if (day <= lastDay) {
                        dates[i][j] = format(new Date(calendarDay.getYear(), calendarDay.getMonth(), day));
                        view.setText(Integer.toString(day));
                        // 当天
                        if (thisday.getDate() == day && thisday.getMonth() == calendarDay.getMonth()
                                && thisday.getYear() == calendarDay.getYear()) {
                            // view.setText("今天");
//                            view.setTextColor(COLOR_TX_THIS_DAY);
                            view.setBackgroundResource(R.drawable.shape_study_sleep);
                            // view.setBackgroundResource(R.drawable.bg_sign_today);
                        } else if (thisday.getMonth() == calendarDay.getMonth()
                                && thisday.getYear() == calendarDay.getYear()) {
                            // 绘制本月的颜色
                            view.setTextColor(COLOR_TX_THIS_MONTH_DAY);
                        } else {
                            // 其他日期
                            view.setTextColor(COLOR_TX_OTHER_MONTH_DAY);
                        }
                        // 上面首先设置了一下默认的"当天"背景色，当有特殊需求时，才给当日填充背景色
                        // 设置日期背景色
                        if (dayBgColorMap.get(dates[i][j]) != null) {
                            // view.setTextColor(Color.WHITE);
                            // view.setBackgroundResource(dayBgColorMap.get(dates[i][j]));
                        }
                        // 设置标记
                        setMarker(group, i, j);
                        day++;
                        // 下个月
                    } else {
                        if (calendarDay.getMonth() == Calendar.DECEMBER) {
                            dates[i][j] = format(new Date(calendarDay.getYear() + 1, Calendar.JANUARY, nextMonthDay));
                        } else {
                            dates[i][j] = format(
                                    new Date(calendarDay.getYear(), calendarDay.getMonth() + 1, nextMonthDay));
                        }
                        view.setText(Integer.toString(nextMonthDay));
                        view.setTextColor(COLOR_TX_OTHER_MONTH_DAY);
                        // 设置日期背景色
                        if (dayBgColorMap.get(dates[i][j]) != null) {
                            // view.setBackgroundResource(dayBgColorMap
                            // .get(dates[i][j]));
                        } else {
                            view.setBackgroundColor(Color.TRANSPARENT);
                        }
                        // 设置标记
                        setMarker(group, i, j);
                        nextMonthDay++;
                    }
                }
            }
        }
    }

    private void drawFrame(LinearLayout oneCalendar) {
        //添加周末线性布局
        LinearLayout title = new LinearLayout(getContext());
        title.setBackgroundColor(COLOR_BG_WEEK_TITLE);
        title.setVisibility(GONE); //由于不需要， 先隐藏
        title.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(MarginLayoutParams.MATCH_PARENT,
                MarginLayoutParams.WRAP_CONTENT, 0.5f);
        Resources res = getResources();
        tb = res.getDimension(R.dimen.historyscore_tb);
        // layout.setMargins(0, 0, 0, (int) (tb * 1.2));
        title.setLayoutParams(layout);
        oneCalendar.addView(title);

        //添加周末textview
        for (int i = 0; i < COLS_TOTAL; i++) {
            TextView view = new TextView(getContext());
            view.setGravity(Gravity.CENTER);
            view.setPadding(0,10,0,10);
            view.setText(weekday[i]);
            view.setTextColor(Color.WHITE);
            view.setLayoutParams(new LinearLayout.LayoutParams(0,-1,1));
            title.addView(view);
        }

        //添加日期布局
        LinearLayout content = new LinearLayout(getContext());
        content.setOrientation(LinearLayout.VERTICAL);
        content.setLayoutParams(new LinearLayout.LayoutParams(-1,0,7f));
        currentCanlendar.addView(content);

        //添加日期textview
        for (int i = 0; i < ROW_TOTAL; i++) {
            LinearLayout row = new LinearLayout(getContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1));
            content.addView(row);
            //绘制日历上的列
            for (int j = 0; j < COLS_TOTAL; j++) {
                RelativeLayout col = new RelativeLayout(getContext());
                col.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
                col.setBackgroundResource(R.drawable.shape_sign_day);
//                col.setPadding(3,3,3,3);
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) col.getLayoutParams();
                lp.leftMargin = 5;
                lp.rightMargin = 5;
                lp.topMargin = 5;
                lp.bottomMargin = 5;

//                二、如果这个空间是我们new出来的，就会会发现用上面的方法就会有空指针报错了。然后我们用另一种方法
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                layoutParams.setMargins(10,10,10,10);//4个参数按顺序分别是左上右下
//                textview.setLayoutParams(layoutParams);

                col.setLayoutParams(lp);
                col.setClickable(false);
                row.addView(col);

                //给每个日期加上监听
                col.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ViewGroup parent = (ViewGroup) view.getParent();
                        int row = 0,col = 0;
                        //获取列坐标
                        for (int i = 0; i < parent.getChildCount(); i++) {
                            if (view.equals(parent.getChildAt(i))){
                                col = i;
                                break;
                            }
                        }
                        //获取行坐标
                        ViewGroup parentRow = (ViewGroup) parent.getParent();
                        for (int i = 0; i < parentRow.getChildCount(); i++) {
                            if (parent.equals(parentRow.getChildAt(i))){
                                row = i;
                                break;
                            }
                        }
//                        UIHelper.toastMessage(getContext(),row+"+"+col);
                        if (onCalendarClickListener != null){
                            onCalendarClickListener.onCalendarClick(row,col,dates[row][col]);
                        }
                    }
                });

            }
        }

    }




    /**
     * 在日历上做一组标记
     *
     * @param date
     *            日期
     * @param id
     *            bitmap res id
     */
    public void addMarks(List<String> date, int id) {
        for (int i = 0; i < date.size(); i++) {
            marksMap.put(date.get(i), id);
        }
        setCalendarDate();
    }

    /***********************************************
     * private methods
     **********************************************/
    // 设置标记
    private void setMarker(RelativeLayout group,int i, int j){
        int childCount = group.getChildCount();
        // dates[i][j] = 2005-12-20等为要对比的日期, marksMap中包括了dates[i][j]时就进入下面的if语句
        if (marksMap.get(dates[i][j])!=null){
            if (childCount < 2){
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.setMargins(0,0,1,1);
                params.addRule(RelativeLayout.CENTER_IN_PARENT);
                TextView markView = new TextView(getContext());
//                markView.setPadding(4,4,0,0);
                markView.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
//                markView.setGravity(Gravity.RIGHT);
                markView.setBackgroundResource(marksMap.get(dates[i][j]));
//                markView.setText("+1");
                markView.setLayoutParams(params);

                //标记图片, 可自定义
                markView.setBackgroundResource(R.mipmap.me_sigin_already);
                group.addView(markView);
            }
        }
    }

    /**
     * 计算某年某月有多少天
     * @param year
     * @param month
     * @return
     */
    private int getDateNum(int year, int month){
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR,year+1900);
        time.set(Calendar.MONTH,month);
        return time.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 根据行列号获取包装每个日子的Linearlayout
     * @param row
     * @param col
     * @return
     */
    private RelativeLayout getDateView(int row,int col){
        return (RelativeLayout) ((LinearLayout)((LinearLayout)currentCanlendar.getChildAt(1)).getChildAt(row)).getChildAt(col);
    }

    /**
     * 将Date转化成字符串--> 2017-6-6
     * @param date
     * @return
     */
    private String format(Date date){
        return addZero(date.getYear()+1900,4)+"-"+addZero(date.getMonth()+1,2)+"-"+addZero(date.getDate(),2);
    }


    /**
     * 2或者4
     * @param i
     * @param count
     * @return
     */
    private static String addZero(int i, int count){
        if (count == 2){
            if (i<10){
                return "0"+i;
            }
        }else if (count == 4){
            if (i<10){
                return "000"+i;
            }else if (i<100&&i>10){
                return "00"+i;
            }else if (i<1000&&i>100){
                return "0"+i;
            }
        }
        return ""+i;
    }


    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    /**
     * onClick 接口回调
     */
    public interface OnCalendarClickListener {
        void onCalendarClick(int row, int col, String dateFormat);
    }

    /**
     * ondateChage接口回调
     */
    public interface OnCalendarDateChangedListener{
        void onCalendarDateChanger(int year, int month);
    }


    public OnCalendarClickListener getOnCalendarClickListener() {
        return onCalendarClickListener;
    }

    public void setOnCalendarClickListener(OnCalendarClickListener onCalendarClickListener) {
        this.onCalendarClickListener = onCalendarClickListener;
    }

    public OnCalendarDateChangedListener getOnCalendarDateChangedListener() {
        return onCalendarDateChangedListener;
    }

    public void setOnCalendarDateChangedListener(OnCalendarDateChangedListener onCalendarDateChangedListener) {
        this.onCalendarDateChangedListener = onCalendarDateChangedListener;
    }
}

