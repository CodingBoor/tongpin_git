// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Found;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FoundHotNewListFragment_ViewBinding implements Unbinder {
  private FoundHotNewListFragment target;

  @UiThread
  public FoundHotNewListFragment_ViewBinding(FoundHotNewListFragment target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.fragment_foundfrag_hot_new_list_rlv, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FoundHotNewListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
