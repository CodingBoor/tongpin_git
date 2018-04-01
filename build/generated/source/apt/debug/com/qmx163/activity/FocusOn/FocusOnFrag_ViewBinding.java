// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.FocusOn;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FocusOnFrag_ViewBinding implements Unbinder {
  private FocusOnFrag target;

  private View view2131230976;

  private View view2131230977;

  @UiThread
  public FocusOnFrag_ViewBinding(final FocusOnFrag target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.mTvUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mTvUp'", TextView.class);
    target.imageRight = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'imageRight'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.focuson_frag_coursell, "field 'coursell' and method 'viewOnClick'");
    target.coursell = Utils.castView(view, R.id.focuson_frag_coursell, "field 'coursell'", LinearLayout.class);
    view2131230976 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.focuson_frag_teacherll, "field 'teacherll' and method 'viewOnClick'");
    target.teacherll = Utils.castView(view, R.id.focuson_frag_teacherll, "field 'teacherll'", LinearLayout.class);
    view2131230977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    target.teaKc = Utils.findRequiredViewAsType(source, R.id.tea_kc, "field 'teaKc'", TextView.class);
    target.teaText = Utils.findRequiredViewAsType(source, R.id.tea_text, "field 'teaText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FocusOnFrag target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.mTvUp = null;
    target.imageRight = null;
    target.coursell = null;
    target.teacherll = null;
    target.teaKc = null;
    target.teaText = null;

    view2131230976.setOnClickListener(null);
    view2131230976 = null;
    view2131230977.setOnClickListener(null);
    view2131230977 = null;
  }
}
