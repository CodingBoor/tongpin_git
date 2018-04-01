// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.watch;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.qmx163.view.NoScrollListview;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DocumentFragment_ViewBinding implements Unbinder {
  private DocumentFragment target;

  @UiThread
  public DocumentFragment_ViewBinding(DocumentFragment target, View source) {
    this.target = target;

    target.keListView = Utils.findRequiredViewAsType(source, R.id.keListView, "field 'keListView'", NoScrollListview.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DocumentFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.keListView = null;
  }
}
