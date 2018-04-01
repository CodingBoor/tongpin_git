// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.StudyToday;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudyCollectionActivity_ViewBinding implements Unbinder {
  private StudyCollectionActivity target;

  @UiThread
  public StudyCollectionActivity_ViewBinding(StudyCollectionActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StudyCollectionActivity_ViewBinding(StudyCollectionActivity target, View source) {
    this.target = target;

    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    target.ivEmpty = Utils.findRequiredViewAsType(source, R.id.iv_empty, "field 'ivEmpty'", ImageView.class);
    target.tvEmpty = Utils.findRequiredViewAsType(source, R.id.tv_empty, "field 'tvEmpty'", TextView.class);
    target.llEmpty = Utils.findRequiredViewAsType(source, R.id.ll_empty, "field 'llEmpty'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudyCollectionActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvLeft = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mUp = null;
    target.mRecyclerView = null;
    target.ivEmpty = null;
    target.tvEmpty = null;
    target.llEmpty = null;
  }
}
