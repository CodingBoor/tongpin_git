// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131230979;

  private View view2131230737;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.ibtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'ibtnLeft'", RelativeLayout.class);
    target.imageleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'imageleft'", ImageView.class);
    target.tvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'tvLeft'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.imageRight = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'imageRight'", ImageView.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.imageView, "field 'imageView'", ImageView.class);
    target.etUserName = Utils.findRequiredViewAsType(source, R.id.etUserName, "field 'etUserName'", EditText.class);
    target.login = Utils.findRequiredViewAsType(source, R.id.login, "field 'login'", com.rey.material.widget.TextView.class);
    view = Utils.findRequiredView(source, R.id.forgetPwd, "field 'forgetPwd' and method 'onViewClicked'");
    target.forgetPwd = Utils.castView(view, R.id.forgetPwd, "field 'forgetPwd'", com.rey.material.widget.TextView.class);
    view2131230979 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.Regist, "field 'Regist' and method 'onViewClicked'");
    target.Regist = Utils.castView(view, R.id.Regist, "field 'Regist'", TextView.class);
    view2131230737 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.edpwd = Utils.findRequiredViewAsType(source, R.id.edpwd, "field 'edpwd'", EditText.class);
    target.bg = Utils.findRequiredViewAsType(source, R.id.iv_bg, "field 'bg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibtnLeft = null;
    target.imageleft = null;
    target.tvLeft = null;
    target.tvTitle = null;
    target.mUp = null;
    target.tvRight = null;
    target.imageRight = null;
    target.imageView = null;
    target.etUserName = null;
    target.login = null;
    target.forgetPwd = null;
    target.Regist = null;
    target.edpwd = null;
    target.bg = null;

    view2131230979.setOnClickListener(null);
    view2131230979 = null;
    view2131230737.setOnClickListener(null);
    view2131230737 = null;
  }
}
