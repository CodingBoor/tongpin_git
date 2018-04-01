// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SplashAc_ViewBinding implements Unbinder {
  private SplashAc target;

  @UiThread
  public SplashAc_ViewBinding(SplashAc target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SplashAc_ViewBinding(SplashAc target, View source) {
    this.target = target;

    target.image = Utils.findRequiredViewAsType(source, R.id.image, "field 'image'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SplashAc target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.image = null;
  }
}
