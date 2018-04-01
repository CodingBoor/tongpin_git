package com.qmx163.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Created by likai on 2017/8/26.
 */
public class BackEditext extends EditText {
    public BackEditext(Context context) {
        super(context);
    }

    public BackEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BackEditext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface BackListener {
        void back();
    }



    private BackListener listener;

    public void setBackListener(BackListener listener) {
        this.listener = listener;
    }



    @Override

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (listener != null) {
                listener.back();
            }
        }
        return false;
    }
}
