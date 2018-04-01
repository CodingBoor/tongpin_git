// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.qmx163.view.PinchImage.SignCalendar;
import com.rey.material.widget.ImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignActivity_ViewBinding implements Unbinder {
  private SignActivity target;

  @UiThread
  public SignActivity_ViewBinding(SignActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignActivity_ViewBinding(SignActivity target, View source) {
    this.target = target;

    target.mIvDel = Utils.findRequiredViewAsType(source, R.id.iv_del, "field 'mIvDel'", ImageView.class);
    target.mRlCalendar = Utils.findRequiredViewAsType(source, R.id.rl_calendar, "field 'mRlCalendar'", RelativeLayout.class);
    target.mTvSign = Utils.findRequiredViewAsType(source, R.id.tv_sign, "field 'mTvSign'", ImageView.class);
    target.mTvPoint = Utils.findRequiredViewAsType(source, R.id.tv_point, "field 'mTvPoint'", TextView.class);
    target.mRlBackground = Utils.findRequiredViewAsType(source, R.id.rl_background, "field 'mRlBackground'", RelativeLayout.class);
    target.mSignCalendar = Utils.findRequiredViewAsType(source, R.id.sign_calendar, "field 'mSignCalendar'", SignCalendar.class);
    target.mTvSignDay = Utils.findRequiredViewAsType(source, R.id.tv_sign_day, "field 'mTvSignDay'", TextView.class);
    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'mTvName'", TextView.class);
    target.mTvGetpoint = Utils.findRequiredViewAsType(source, R.id.tv_getpoint, "field 'mTvGetpoint'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SignActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvDel = null;
    target.mRlCalendar = null;
    target.mTvSign = null;
    target.mTvPoint = null;
    target.mRlBackground = null;
    target.mSignCalendar = null;
    target.mTvSignDay = null;
    target.mTvName = null;
    target.mTvGetpoint = null;
  }
}
