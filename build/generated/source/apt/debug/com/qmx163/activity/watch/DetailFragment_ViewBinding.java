// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.watch;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailFragment_ViewBinding implements Unbinder {
  private DetailFragment target;

  @UiThread
  public DetailFragment_ViewBinding(DetailFragment target, View source) {
    this.target = target;

    target.keListView = Utils.findRequiredViewAsType(source, R.id.keListView, "field 'keListView'", ListView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.keListView = null;
  }
}
