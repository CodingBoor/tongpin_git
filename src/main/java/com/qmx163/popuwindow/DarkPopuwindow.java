package com.qmx163.popuwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * 窗体变暗Popu
 * Created by lzo on 2016/8/29.
 */
public abstract class DarkPopuwindow extends BasePopupwindow {

    public DarkPopuwindow(Context context, int width, int height, int layoutsource) {
        super(context, width, height, layoutsource);
        addDarkLisenter();
    }

    private void addDarkLisenter(){
        //设置弹出窗体的背景
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams params=((Activity)context).getWindow().getAttributes();
                params.alpha=1f;
                ((Activity)context).getWindow().setAttributes(params);
            }
        });
    }

    @Override
    public void show(View view) {
        if(!popupWindow.isShowing()){
            final Activity activity= (Activity) context;
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    //execute the task
                    WindowManager.LayoutParams params=activity.getWindow().getAttributes();
                    params.alpha=0.4f;
//                    activity. getWindow().setAttributes(params);  //先注释此行代码，
                }
            }, 10);
        }
        super.show(view);

    }
}
