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

public class PwdActivity_ViewBinding implements Unbinder {
  private PwdActivity target;

  private View view2131231003;

  private View view2131230883;

  private View view2131230960;

  private View view2131230994;

  @UiThread
  public PwdActivity_ViewBinding(PwdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PwdActivity_ViewBinding(final PwdActivity target, View source) {
    this.target = target;

    View view;
    target.ibtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'ibtnLeft'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.iamgeleft, "field 'iamgeleft' and method 'onViewClicked'");
    target.iamgeleft = Utils.castView(view, R.id.iamgeleft, "field 'iamgeleft'", ImageView.class);
    view2131231003 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'tvLeft'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.iamgeRight = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'iamgeRight'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.btn_rg_cz, "field 'btnRgCz' and method 'onViewClicked'");
    target.btnRgCz = Utils.castView(view, R.id.btn_rg_cz, "field 'btnRgCz'", com.rey.material.widget.TextView.class);
    view2131230883 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.logo = Utils.findRequiredViewAsType(source, R.id.pwd_logo, "field 'logo'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.exitpwd, "field 'exitpwd' and method 'onViewClicked'");
    target.exitpwd = Utils.castView(view, R.id.exitpwd, "field 'exitpwd'", com.rey.material.widget.TextView.class);
    view2131230960 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.activityPwd = Utils.findRequiredViewAsType(source, R.id.activity_pwd, "field 'activityPwd'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.getSmsCode, "field 'getSmsCode' and method 'onViewClicked'");
    target.getSmsCode = Utils.castView(view, R.id.getSmsCode, "field 'getSmsCode'", com.rey.material.widget.TextView.class);
    view2131230994 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.reEdits = Utils.findRequiredViewAsType(source, R.id.re_Edit, "field 'reEdits'", EditText.class);
    target.phTels = Utils.findRequiredViewAsType(source, R.id.ph_tel, "field 'phTels'", EditText.class);
    target.pwd = Utils.findRequiredViewAsType(source, R.id.pwd, "field 'pwd'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PwdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibtnLeft = null;
    target.iamgeleft = null;
    target.tvLeft = null;
    target.mUp = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.iamgeRight = null;
    target.btnRgCz = null;
    target.logo = null;
    target.exitpwd = null;
    target.activityPwd = null;
    target.getSmsCode = null;
    target.reEdits = null;
    target.phTels = null;
    target.pwd = null;

    view2131231003.setOnClickListener(null);
    view2131231003 = null;
    view2131230883.setOnClickListener(null);
    view2131230883 = null;
    view2131230960.setOnClickListener(null);
    view2131230960 = null;
    view2131230994.setOnClickListener(null);
    view2131230994 = null;
  }
}
