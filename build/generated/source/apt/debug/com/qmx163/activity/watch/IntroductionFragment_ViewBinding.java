// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.watch;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IntroductionFragment_ViewBinding implements Unbinder {
  private IntroductionFragment target;

  @UiThread
  public IntroductionFragment_ViewBinding(IntroductionFragment target, View source) {
    this.target = target;

    target.mIvIntro = Utils.findRequiredViewAsType(source, R.id.iv_intro, "field 'mIvIntro'", ImageView.class);
    target.mScrollView = Utils.findRequiredViewAsType(source, R.id.scroll_view, "field 'mScrollView'", ScrollView.class);
    target.ivPlayStart = Utils.findRequiredViewAsType(source, R.id.iv_play_start, "field 'ivPlayStart'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    IntroductionFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvIntro = null;
    target.mScrollView = null;
    target.ivPlayStart = null;
  }
}
