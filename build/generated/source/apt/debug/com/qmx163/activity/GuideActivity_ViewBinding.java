// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.qmx163.view.WelcomeVideoView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GuideActivity_ViewBinding implements Unbinder {
  private GuideActivity target;

  @UiThread
  public GuideActivity_ViewBinding(GuideActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GuideActivity_ViewBinding(GuideActivity target, View source) {
    this.target = target;

    target.videoview = Utils.findRequiredViewAsType(source, R.id.videoview, "field 'videoview'", WelcomeVideoView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GuideActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.videoview = null;
  }
}
