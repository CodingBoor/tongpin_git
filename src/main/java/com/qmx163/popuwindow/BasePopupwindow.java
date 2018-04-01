package com.qmx163.popuwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.qmx163.R;


/**
 * 基础popupwindow
 * Created by lzp on 2016/7/25.
 */
public abstract class BasePopupwindow {
    PopupWindow popupWindow;
    Context context;

    public BasePopupwindow(Context context, int width, int height, int layoutsource){
        this.context=context;
        initPop(width,height,layoutsource);
    }

    private void initPop(int width,int height,int layoutsource){
        View view = View.inflate(context, layoutsource, null);
        popupWindow=new PopupWindow(view, width, height);
        popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(height);
        popupWindow.setAnimationStyle(R.style.main_menu_animstyle);
        popupWindow.setFocusable(false);
        //实例化一个ColorDrawable颜色为白色
        ColorDrawable dw = new ColorDrawable(ContextCompat.getColor(context,R.color.white));
        //设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(false);
        initView(view);
    }

    public void show(View view){
        if(!popupWindow.isShowing())
        popupWindow.showAtLocation(view, Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
    }

    public void dismiss(){
        popupWindow.dismiss();
    }
    public boolean isShowing(){
       return  popupWindow.isShowing();
    }

    public abstract  void initView(View view);

}

