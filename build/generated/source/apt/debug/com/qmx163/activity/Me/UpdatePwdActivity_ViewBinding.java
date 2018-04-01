// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class UpdatePwdActivity_ViewBinding implements Unbinder {
  private UpdatePwdActivity target;

  private View view2131231097;

  private View view2131231095;

  private View view2131231096;

  private View view2131231501;

  @UiThread
  public UpdatePwdActivity_ViewBinding(UpdatePwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UpdatePwdActivity_ViewBinding(final UpdatePwdActivity target, View source) {
    this.target = target;

    View view;
    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mEtOldpsw = Utils.findRequiredViewAsType(source, R.id.et_oldpsw, "field 'mEtOldpsw'", EditText.class);
    view = Utils.findRequiredView(source, R.id.ll_oldpsw, "field 'mLlOldpsw' and method 'onViewClicked'");
    target.mLlOldpsw = Utils.castView(view, R.id.ll_oldpsw, "field 'mLlOldpsw'", LinearLayout.class);
    view2131231097 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mEtNewpsw = Utils.findRequiredViewAsType(source, R.id.et_newpsw, "field 'mEtNewpsw'", EditText.class);
    view = Utils.findRequiredView(source, R.id.ll_newpsw, "field 'mLlNewpsw' and method 'onViewClicked'");
    target.mLlNewpsw = Utils.castView(view, R.id.ll_newpsw, "field 'mLlNewpsw'", LinearLayout.class);
    view2131231095 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mEtNewpswAgain = Utils.findRequiredViewAsType(source, R.id.et_newpsw_again, "field 'mEtNewpswAgain'", EditText.class);
    view = Utils.findRequiredView(source, R.id.ll_newpsw_again, "field 'mLlNewpswAgain' and method 'onViewClicked'");
    target.mLlNewpswAgain = Utils.castView(view, R.id.ll_newpsw_again, "field 'mLlNewpswAgain'", LinearLayout.class);
    view2131231096 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_sure, "field 'mTvSure' and method 'onViewClicked'");
    target.mTvSure = Utils.castView(view, R.id.tv_sure, "field 'mTvSure'", com.rey.material.widget.TextView.class);
    view2131231501 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UpdatePwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mUp = null;
    target.mRlTitle = null;
    target.mEtOldpsw = null;
    target.mLlOldpsw = null;
    target.mEtNewpsw = null;
    target.mLlNewpsw = null;
    target.mEtNewpswAgain = null;
    target.mLlNewpswAgain = null;
    target.mTvSure = null;

    view2131231097.setOnClickListener(null);
    view2131231097 = null;
    view2131231095.setOnClickListener(null);
    view2131231095 = null;
    view2131231096.setOnClickListener(null);
    view2131231096 = null;
    view2131231501.setOnClickListener(null);
    view2131231501 = null;
  }
}
