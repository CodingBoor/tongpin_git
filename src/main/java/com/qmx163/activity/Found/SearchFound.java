package com.qmx163.activity.Found;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.base.BaseAc;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索界面
 * 改 lyf on 2017.7.1
 */
public class SearchFound extends BaseAc {

    @BindView(R.id.search_found_type)
    TextView typeTv;
    @BindView(R.id.search_found_keyword)
    EditText keyWordEt;
    @BindView(R.id.search_found_cancel)
    TextView cancelEt;
    @BindView(R.id.search_found_line)
    View lineView;
    @BindView(R.id.iv_del)
    com.rey.material.widget.ImageView ivDel;
    @BindView(R.id.ll_bg)
    LinearLayout ll_bg;
    @BindView(R.id.tv_bg_up)
    TextView mUp;

    @BindView(R.id.search_found_fragment)
    FrameLayout searchFragment;

    // 是否已经显示弹窗
    private boolean isShowPopWindows = false;
    private PopupWindow popupWindow = null;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private static final int COURSE = 0X0001;
    private static final int TEACHER = 0X0002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_found);
        ButterKnife.bind(this);

        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            LinearLayout.LayoutParams linearParams =(LinearLayout.LayoutParams) mUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        showFragment(COURSE);

//        //编辑是触发
//        keyWordEt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//          点击软键盘 搜索 触发
        keyWordEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = v.getText().toString();
                    if (text.equals("")) {
                        showToas(getString(R.string.search_toast));
                    } else {
                        // 隐藏键盘
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                        if (isKc) {
                            foundHotNew.getDateFxSear(text);
                        } else {
                            foundTeacher.bindReceiverViewFx("", text);
                        }

                    }
                }
                return false;
            }
        });


        keyWordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    ivDel.setVisibility(View.VISIBLE);
                } else {
                    ivDel.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyWordEt.setText("");
            }
        });


//        searchFragment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//            }
//        });
    }

    boolean isKc = true;

    @OnClick({R.id.search_found_cancel, R.id.search_found_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_found_cancel:
                finish();
                break;
            case R.id.search_found_type:
                showPopWindows();
                break;
        }

    }

    //显示或关闭课程选择弹窗
    private void showPopWindows() {

        // 隐藏键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        if (isShowPopWindows) {
            return;
        }
        isShowPopWindows = true;
        // 修改右边的小图标
        Drawable drawable = getResources().getDrawable(R.mipmap.drop_down_open2);
        // 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        typeTv.setCompoundDrawables(null, null, drawable, null);


        View view = getLayoutInflater().inflate(R.layout.search_course_teacher_popwin, null);

        popupWindow = new PopupWindow(view,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popUpWindow 弹窗可点击。下面两句话必须添加，并且是true
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

        // 显示的位置
        popupWindow.showAsDropDown(lineView, 50, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isShowPopWindows = false;
                // 修改右边的小图标
                Drawable drawable = getResources().getDrawable(R.mipmap.drop_down_close2);
                // 这一步必须要做,否则不会显示.
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                typeTv.setCompoundDrawables(null, null, drawable, null);
            }
        });

        TextView courseTv = (TextView) view.findViewById(R.id.search_course_teacher_coursetv);
        TextView teacherTv = (TextView) view.findViewById(R.id.search_course_teacher_trachertv);

        // 课程 点击事件
        courseTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isKc = true;
                typeTv.setText("课程");
                keyWordEt.setHint("请输入课程名称");
                popupWindow.dismiss();
                showFragment(COURSE);
            }
        });
        // 老师 点击事件
        teacherTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isKc = false;
                typeTv.setText("老师");
                keyWordEt.setHint("请输入老师姓名");
                popupWindow.dismiss();
                showFragment(TEACHER);
            }
        });


    }

    FoundHotNewListFrag foundHotNew;
    FoundTeacherListFrag foundTeacher;

    // 切换显示的内容
    private void showFragment(int type) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        foundHotNew = FoundHotNewListFrag.newInstance("", "", "1", "3", keyWordEt.getText().toString());
        if (type == COURSE) {
            ft.replace(R.id.search_found_fragment, foundHotNew);
        } else {
            foundTeacher = FoundTeacherListFrag.newInstance("", "", "1", keyWordEt.getText().toString());
            ft.replace(R.id.search_found_fragment, foundTeacher);
        }
        ft.commit();

    }

}
