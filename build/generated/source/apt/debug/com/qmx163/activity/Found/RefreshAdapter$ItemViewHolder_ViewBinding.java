// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Found;

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

public class RefreshAdapter$ItemViewHolder_ViewBinding implements Unbinder {
  private RefreshAdapter.ItemViewHolder target;

  @UiThread
  public RefreshAdapter$ItemViewHolder_ViewBinding(RefreshAdapter.ItemViewHolder target,
      View source) {
    this.target = target;

    target.teaIco = Utils.findRequiredViewAsType(source, R.id.tea_ico, "field 'teaIco'", ImageView.class);
    target.teacherName = Utils.findRequiredViewAsType(source, R.id.teacherName, "field 'teacherName'", TextView.class);
    target.subjectName = Utils.findRequiredViewAsType(source, R.id.subjectName, "field 'subjectName'", TextView.class);
    target.introVideoImg = Utils.findRequiredViewAsType(source, R.id.introVideoImg, "field 'introVideoImg'", ImageView.class);
    target.status = Utils.findRequiredViewAsType(source, R.id.status, "field 'status'", TextView.class);
    target.lessonName = Utils.findRequiredViewAsType(source, R.id.lessonName, "field 'lessonName'", TextView.class);
    target.newTimer = Utils.findRequiredViewAsType(source, R.id.new_timer, "field 'newTimer'", TextView.class);
    target.llNewTimer = Utils.findRequiredViewAsType(source, R.id.ll_new_timer, "field 'llNewTimer'", LinearLayout.class);
    target.lookTimer = Utils.findRequiredViewAsType(source, R.id.look_timer, "field 'lookTimer'", TextView.class);
    target.lookSize = Utils.findRequiredViewAsType(source, R.id.look_size, "field 'lookSize'", TextView.class);
    target.plCount = Utils.findRequiredViewAsType(source, R.id.pl_count, "field 'plCount'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RefreshAdapter.ItemViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.teaIco = null;
    target.teacherName = null;
    target.subjectName = null;
    target.introVideoImg = null;
    target.status = null;
    target.lessonName = null;
    target.newTimer = null;
    target.llNewTimer = null;
    target.lookTimer = null;
    target.lookSize = null;
    target.plCount = null;
  }
}
