// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Found;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RefreshAdapter$FooterViewHolder_ViewBinding implements Unbinder {
  private RefreshAdapter.FooterViewHolder target;

  @UiThread
  public RefreshAdapter$FooterViewHolder_ViewBinding(RefreshAdapter.FooterViewHolder target,
      View source) {
    this.target = target;

    target.mPbLoad = Utils.findRequiredViewAsType(source, R.id.pbLoad, "field 'mPbLoad'", ProgressBar.class);
    target.mTvLoadText = Utils.findRequiredViewAsType(source, R.id.tvLoadText, "field 'mTvLoadText'", TextView.class);
    target.mLoadLayout = Utils.findRequiredViewAsType(source, R.id.loadLayout, "field 'mLoadLayout'", LinearLayout.class);
    target.rlView = Utils.findRequiredViewAsType(source, R.id.rl_view, "field 'rlView'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RefreshAdapter.FooterViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mPbLoad = null;
    target.mTvLoadText = null;
    target.mLoadLayout = null;
    target.rlView = null;
  }
}
