package com.qmx163.popuwindow;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.listener.CommentSendListener;
import com.qmx163.util.ToastUtil;
import com.qmx163.view.BackEditext;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by likai on 2017/9/6.
 */

public class CommentDialog extends Dialog{
    private BackEditext comment;
    private com.rey.material.widget.TextView send;
    private CommentSendListener mCommentSendListener;

    public void setCommentSendListener(CommentSendListener commentSendListener) {
        mCommentSendListener = commentSendListener;
    }

    public CommentDialog(Context context) {
        super(context);
        initView(context);
    }
    public CommentDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected CommentDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }


    private void initView(final Context context) {
        setContentView(R.layout.comment_dialog);
        comment=(BackEditext) findViewById(R.id.comment);
        TextView textView = (TextView) findViewById(R.id.scrollview);
        send=(com.rey.material.widget.TextView) findViewById(R.id.send);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=comment.getText().toString();
                if(TextUtils.isEmpty(message.trim())){
                    ToastUtil.toastShortShow(context,"嗯!想了一下，还是先写评论吧!");
                }else{

                    mCommentSendListener.sendOk(message);
                    /*隐藏软键盘*/
                    InputMethodManager imm = (InputMethodManager) comment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm.isActive()) {
                        imm.hideSoftInputFromWindow(comment.getApplicationWindowToken(), 0);
                    }
                    comment.setText("");
                    dismiss();
                }
            }
        });
        comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0){
                    send.setTextColor(ContextCompat.getColor(context, R.color.juhuan1));
                }else{
                    send.setTextColor(ContextCompat.getColor(context,R.color.bg));
                }
            }
        });
    }

    public void setBackLisenter(BackEditext.BackListener backLisenter){
        comment.setBackListener(backLisenter);
    }

    @Override
    public void show() {
        super.show();
        comment.setFocusable(true);
        comment.setFocusableInTouchMode(true);
        comment.requestFocus();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run(){
                InputMethodManager inputManager = (InputMethodManager) comment.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(comment, 0);
            }
        },200);
    }
}
