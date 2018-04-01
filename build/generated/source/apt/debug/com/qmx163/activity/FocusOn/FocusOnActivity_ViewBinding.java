// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.FocusOn;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FocusOnActivity_ViewBinding implements Unbinder {
  private FocusOnActivity target;

  private View view2131230976;

  private View view2131230977;

  @UiThread
  public FocusOnActivity_ViewBinding(FocusOnActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FocusOnActivity_ViewBinding(final FocusOnActivity target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.iBtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'iBtnLeft'", RelativeLayout.class);
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
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.mFocusonFragContent = Utils.findRequiredViewAsType(source, R.id.focuson_frag_content, "field 'mFocusonFragContent'", FrameLayout.class);
    target.mFocusonFragContentTeach = Utils.findRequiredViewAsType(source, R.id.focuson_frag_content_teach, "field 'mFocusonFragContentTeach'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FocusOnActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.iBtnLeft = null;
    target.imageRight = null;
    target.coursell = null;
    target.teacherll = null;
    target.teaKc = null;
    target.teaText = null;
    target.mUp = null;
    target.mFocusonFragContent = null;
    target.mFocusonFragContentTeach = null;

    view2131230976.setOnClickListener(null);
    view2131230976 = null;
    view2131230977.setOnClickListener(null);
    view2131230977 = null;
  }
}
