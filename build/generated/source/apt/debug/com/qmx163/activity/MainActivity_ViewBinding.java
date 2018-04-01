// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.qmx163.bottomNavigation.BottomNavigationBar;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.rlFragment = Utils.findRequiredViewAsType(source, R.id.rl_fragment, "field 'rlFragment'", RelativeLayout.class);
    target.bottomNavigationBar = Utils.findRequiredViewAsType(source, R.id.bottom_navigation_bar, "field 'bottomNavigationBar'", BottomNavigationBar.class);
    target.activityMain = Utils.findRequiredViewAsType(source, R.id.activity_main, "field 'activityMain'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rlFragment = null;
    target.bottomNavigationBar = null;
    target.activityMain = null;
  }
}
