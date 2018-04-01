package com.qmx163.popuwindow;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qmx163.R;

/**
 *
 * Created by Administrator on 2016/8/11.
 */
public class SharePopupWindow extends DarkPopuwindow implements View.OnClickListener{
    View cancle,circle,friend,wx,wx_circle,tv_bg;

    ImageView imageView;

    private ShareViewListener listener;

    boolean isCollection = false;


    public void setCollection(boolean collection) {
        isCollection = collection;
        if (collection) {
//            imageView.setImageResource(R.mipmap.shoucang_true);
        } else {
//            imageView.setImageResource(R.mipmap.shoucan);
        }
    }

    public SharePopupWindow(Context context, int width, int height) {
        super(context, width, height, R.layout.layout_sharepopu);
    }

    @Override
    public void initView(View view) {
        cancle=view.findViewById(R.id.cancle);
        circle=view.findViewById(R.id.circle);
        friend=view.findViewById(R.id.friend);
        wx=view.findViewById(R.id.wx);
        wx_circle=view.findViewById(R.id.wx_circle);
        tv_bg = view.findViewById(R.id.tv_bg);

        cancle.setOnClickListener(this);
        circle.setOnClickListener(this);
        friend.setOnClickListener(this);
        wx.setOnClickListener(this);
        wx_circle.setOnClickListener(this);
        tv_bg.setOnClickListener(this);

    }

    private String title, content, url;
    private String logoUrl;


    /**
     * 设置标题内容
     * @param title
     * @param content
     * @return
     */
    public SharePopupWindow initContent(String title, String content) {
        return initContent(title, content, null);
    }

    public SharePopupWindow initContent(String title, String content, String logoUrl) {
        this.title = title;
        this.content = content;
        this.logoUrl = logoUrl;
        return this;

    }


    public interface ShareViewListener {

        void onFriendCircle();

        void onFriend();

        void onWXCircle();

        void onWXFriend();


    }

    public void setListener(ShareViewListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            dismiss();
            return;
        }
        if(v==circle){
            listener.onFriendCircle();
        }else if(v==friend){
            listener.onFriend();
        }else if(v==wx){
            listener.onWXFriend();
        }else if(v==wx_circle){
            listener.onWXCircle();
        }else if (v == tv_bg){
            dismiss();
        }
        dismiss();
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
