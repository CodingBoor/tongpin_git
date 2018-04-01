// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.StudyToday;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudyHistoryFragment_ViewBinding implements Unbinder {
  private StudyHistoryFragment target;

  @UiThread
  public StudyHistoryFragment_ViewBinding(StudyHistoryFragment target, View source) {
    this.target = target;

    target.ivEmpty = Utils.findRequiredViewAsType(source, R.id.iv_empty, "field 'ivEmpty'", ImageView.class);
    target.tvEmpty = Utils.findRequiredViewAsType(source, R.id.tv_empty, "field 'tvEmpty'", TextView.class);
    target.llEmpty = Utils.findRequiredViewAsType(source, R.id.ll_empty, "field 'llEmpty'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudyHistoryFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivEmpty = null;
    target.tvEmpty = null;
    target.llEmpty = null;
  }
}
