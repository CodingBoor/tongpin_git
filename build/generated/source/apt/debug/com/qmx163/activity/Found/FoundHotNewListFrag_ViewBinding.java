// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Found;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FoundHotNewListFrag_ViewBinding implements Unbinder {
  private FoundHotNewListFrag target;

  @UiThread
  public FoundHotNewListFrag_ViewBinding(FoundHotNewListFrag target, View source) {
    this.target = target;

    target.ivEmpty = Utils.findRequiredViewAsType(source, R.id.iv_empty, "field 'ivEmpty'", ImageView.class);
    target.tvEmpty = Utils.findRequiredViewAsType(source, R.id.tv_empty, "field 'tvEmpty'", TextView.class);
    target.llEmpty = Utils.findRequiredViewAsType(source, R.id.ll_empty, "field 'llEmpty'", LinearLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.fragment_foundfrag_hot_new_list_rlv, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FoundHotNewListFrag target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivEmpty = null;
    target.tvEmpty = null;
    target.llEmpty = null;
    target.recyclerView = null;
  }
}
