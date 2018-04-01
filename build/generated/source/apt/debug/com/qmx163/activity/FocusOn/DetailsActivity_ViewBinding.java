// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.FocusOn;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailsActivity_ViewBinding implements Unbinder {
  private DetailsActivity target;

  private View view2131230976;

  private View view2131230977;

  private View view2131230725;

  private View view2131231032;

  @UiThread
  public DetailsActivity_ViewBinding(DetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailsActivity_ViewBinding(final DetailsActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.focuson_frag_coursell, "field 'focusonFragCoursell' and method 'viewOnClick'");
    target.focusonFragCoursell = Utils.castView(view, R.id.focuson_frag_coursell, "field 'focusonFragCoursell'", LinearLayout.class);
    view2131230976 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.focuson_frag_teacherll, "field 'focusonFragTeacherll' and method 'viewOnClick'");
    target.focusonFragTeacherll = Utils.castView(view, R.id.focuson_frag_teacherll, "field 'focusonFragTeacherll'", LinearLayout.class);
    view2131230977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    target.focusonFragContent = Utils.findRequiredViewAsType(source, R.id.focuson_frag_content, "field 'focusonFragContent'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.Isfocus, "field 'Isfocus' and method 'viewOnClick'");
    target.Isfocus = Utils.castView(view, R.id.Isfocus, "field 'Isfocus'", ImageView.class);
    view2131230725 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    target.teaSuggest = Utils.findRequiredViewAsType(source, R.id.tea_suggest, "field 'teaSuggest'", TextView.class);
    target.teaProcess = Utils.findRequiredViewAsType(source, R.id.tea_process, "field 'teaProcess'", TextView.class);
    target.teaLine = Utils.findRequiredViewAsType(source, R.id.tea_line, "field 'teaLine'", ImageView.class);
    target.teaProline = Utils.findRequiredViewAsType(source, R.id.tea_proline, "field 'teaProline'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'mIvBack' and method 'viewOnClick'");
    target.mIvBack = Utils.castView(view, R.id.iv_back, "field 'mIvBack'", ImageView.class);
    view2131231032 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    target.mTeaIco = Utils.findRequiredViewAsType(source, R.id.tea_ico, "field 'mTeaIco'", ImageView.class);
    target.mTeaState = Utils.findRequiredViewAsType(source, R.id.tea_state, "field 'mTeaState'", TextView.class);
    target.mTecherName = Utils.findRequiredViewAsType(source, R.id.tv_techer_name, "field 'mTecherName'", TextView.class);
    target.mCoordinatorLayout = Utils.findRequiredViewAsType(source, R.id.coordinatorLayout, "field 'mCoordinatorLayout'", CoordinatorLayout.class);
    target.mTopLayout = Utils.findRequiredViewAsType(source, R.id.topLayout, "field 'mTopLayout'", LinearLayout.class);
    target.mAppBarLayout = Utils.findRequiredViewAsType(source, R.id.appBarLayout, "field 'mAppBarLayout'", AppBarLayout.class);
    target.mSwipe = Utils.findRequiredViewAsType(source, R.id.swipe, "field 'mSwipe'", SwipeRefreshLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.focusonFragCoursell = null;
    target.focusonFragTeacherll = null;
    target.focusonFragContent = null;
    target.Isfocus = null;
    target.teaSuggest = null;
    target.teaProcess = null;
    target.teaLine = null;
    target.teaProline = null;
    target.mIvBack = null;
    target.mTeaIco = null;
    target.mTeaState = null;
    target.mTecherName = null;
    target.mCoordinatorLayout = null;
    target.mTopLayout = null;
    target.mAppBarLayout = null;
    target.mSwipe = null;

    view2131230976.setOnClickListener(null);
    view2131230976 = null;
    view2131230977.setOnClickListener(null);
    view2131230977 = null;
    view2131230725.setOnClickListener(null);
    view2131230725 = null;
    view2131231032.setOnClickListener(null);
    view2131231032 = null;
  }
}
