// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyDataItemActivity_ViewBinding implements Unbinder {
  private MyDataItemActivity target;

  @UiThread
  public MyDataItemActivity_ViewBinding(MyDataItemActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyDataItemActivity_ViewBinding(MyDataItemActivity target, View source) {
    this.target = target;

    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mEtText = Utils.findRequiredViewAsType(source, R.id.et_text, "field 'mEtText'", EditText.class);
    target.mIvDel = Utils.findRequiredViewAsType(source, R.id.iv_del, "field 'mIvDel'", com.rey.material.widget.ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyDataItemActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mUp = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mRlTitle = null;
    target.mEtText = null;
    target.mIvDel = null;
  }
}
