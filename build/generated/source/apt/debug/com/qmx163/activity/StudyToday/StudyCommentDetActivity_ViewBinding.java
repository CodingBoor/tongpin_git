// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.StudyToday;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudyCommentDetActivity_ViewBinding implements Unbinder {
  private StudyCommentDetActivity target;

  @UiThread
  public StudyCommentDetActivity_ViewBinding(StudyCommentDetActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StudyCommentDetActivity_ViewBinding(StudyCommentDetActivity target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudyCommentDetActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
    target.mIbtnLeft = null;
    target.mIamgeleft = null;
    target.mTvTitle = null;
    target.mUp = null;
  }
}
