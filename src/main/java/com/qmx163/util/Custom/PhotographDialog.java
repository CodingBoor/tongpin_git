package com.qmx163.util.Custom;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.qmx163.R;


/**
 *
 * @author li.zp
 * @描述:拍照dialog
 */
public class PhotographDialog implements View.OnClickListener {

    private Context mContext;
    private Dialog dialog;
    public OnBtnClickLister btnClickLister;
    public Button albumSelect;
    public Button shooting;
    Button cancle;


    public PhotographDialog(Context context) {
        mContext = context;
        initDialog();
    }

    void initDialog() {
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.photo_choose_dialog, null);
        initBtn(view);
        dialog = new Dialog(mContext, R.style.transparentFrameWindowStyle);
        dialog.setContentView(view, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = LayoutParams.MATCH_PARENT;
        wl.height = LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
    }

    void initBtn(View view) {
        albumSelect = (Button) view.findViewById(R.id.album_select);
        shooting = (Button) view.findViewById(R.id.shooting);
        cancle = (Button) view.findViewById(R.id.cancle);
        albumSelect.setOnClickListener(this);
        shooting.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }
    //拍摄文字
    public void setShootingTxt(String shootingtxt){
        shooting.setText(shootingtxt);
    }
    //拍摄文字颜色
    public void setShootingTxtColor(int color){
        shooting.setTextColor(color);
    }
    //相册选取文字
    public void setAlbumSelectTxt(String albtxt){
        albumSelect.setText(albtxt);
    }
    //拍摄文字颜色
    public void setAlbumSelectTxtColor(int color ){
        albumSelect.setTextColor(color);
    }
    //取消文字颜色
    public void setCancleTxtColor(int color ){
        cancle.setTextColor(color);
    }
    //设置监听
    public void setOnBtnClickLister(OnBtnClickLister _OnBtnClickLister) {
        btnClickLister = _OnBtnClickLister;
    }

    public boolean isShowing(){
        return dialog.isShowing();
    }

    public void show() {
        if (!isShowing()) {
            dialog.show();
        }
    }


    public void dismiss() {
        dialog.dismiss();
    }


    public interface OnBtnClickLister {
        void shooting(); // 拍摄

        void selection();// 选取

        void cancle();// 取消
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.album_select:
                if (btnClickLister != null) {
                    btnClickLister.selection();
                }
                break;
            case R.id.shooting:
                if (btnClickLister != null) {
                    btnClickLister.shooting();
                }

                break;
            case R.id.cancle:
                if (btnClickLister != null) {
                    btnClickLister.cancle();
                }
                break;
            default:
                break;
        }
        dismiss();
    }

}
