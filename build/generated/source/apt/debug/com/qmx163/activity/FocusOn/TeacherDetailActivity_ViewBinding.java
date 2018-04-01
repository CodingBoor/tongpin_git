// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.FocusOn;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TeacherDetailActivity_ViewBinding implements Unbinder {
  private TeacherDetailActivity target;

  private View view2131230976;

  private View view2131230977;

  private View view2131231032;

  private View view2131230725;

  @UiThread
  public TeacherDetailActivity_ViewBinding(TeacherDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TeacherDetailActivity_ViewBinding(final TeacherDetailActivity target, View source) {
    this.target = target;

    View view;
    target.llWeb = Utils.findRequiredViewAsType(source, R.id.ll_web_context, "field 'llWeb'", RelativeLayout.class);
    target.teaIco = Utils.findRequiredViewAsType(source, R.id.tea_ico, "field 'teaIco'", CircleImageView.class);
    target.tvTecherName = Utils.findRequiredViewAsType(source, R.id.tv_techer_name, "field 'tvTecherName'", TextView.class);
    target.teaState = Utils.findRequiredViewAsType(source, R.id.tea_state, "field 'teaState'", TextView.class);
    target.teaSuggest = Utils.findRequiredViewAsType(source, R.id.tea_suggest, "field 'teaSuggest'", TextView.class);
    target.teaLine = Utils.findRequiredViewAsType(source, R.id.tea_line, "field 'teaLine'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.focuson_frag_coursell, "field 'focusonFragCoursell' and method 'viewOnClick'");
    target.focusonFragCoursell = Utils.castView(view, R.id.focuson_frag_coursell, "field 'focusonFragCoursell'", LinearLayout.class);
    view2131230976 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    target.teaProcess = Utils.findRequiredViewAsType(source, R.id.tea_process, "field 'teaProcess'", TextView.class);
    target.teaProline = Utils.findRequiredViewAsType(source, R.id.tea_proline, "field 'teaProline'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.focuson_frag_teacherll, "field 'focusonFragTeacherll' and method 'viewOnClick'");
    target.focusonFragTeacherll = Utils.castView(view, R.id.focuson_frag_teacherll, "field 'focusonFragTeacherll'", LinearLayout.class);
    view2131230977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    target.recyclerStuty = Utils.findRequiredViewAsType(source, R.id.recycler_stuty, "field 'recyclerStuty'", RecyclerView.class);
    target.ivIntroImg = Utils.findRequiredViewAsType(source, R.id.iv_intro_img, "field 'ivIntroImg'", ImageView.class);
    target.topLayout = Utils.findRequiredViewAsType(source, R.id.topLayout, "field 'topLayout'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'viewOnClick'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", com.rey.material.widget.ImageView.class);
    view2131231032 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.Isfocus, "field 'Isfocus' and method 'viewOnClick'");
    target.Isfocus = Utils.castView(view, R.id.Isfocus, "field 'Isfocus'", com.rey.material.widget.ImageView.class);
    view2131230725 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewOnClick(p0);
      }
    });
    target.activityDetailNew = Utils.findRequiredViewAsType(source, R.id.activity_detail_new, "field 'activityDetailNew'", RelativeLayout.class);
    target.llTeacher = Utils.findRequiredViewAsType(source, R.id.ll_teacher, "field 'llTeacher'", LinearLayout.class);
    target.mCfFooter = Utils.findRequiredViewAsType(source, R.id.cf_footer, "field 'mCfFooter'", ClassicsFooter.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TeacherDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llWeb = null;
    target.teaIco = null;
    target.tvTecherName = null;
    target.teaState = null;
    target.teaSuggest = null;
    target.teaLine = null;
    target.focusonFragCoursell = null;
    target.teaProcess = null;
    target.teaProline = null;
    target.focusonFragTeacherll = null;
    target.recyclerStuty = null;
    target.ivIntroImg = null;
    target.topLayout = null;
    target.ivBack = null;
    target.Isfocus = null;
    target.activityDetailNew = null;
    target.llTeacher = null;
    target.mCfFooter = null;

    view2131230976.setOnClickListener(null);
    view2131230976 = null;
    view2131230977.setOnClickListener(null);
    view2131230977 = null;
    view2131231032.setOnClickListener(null);
    view2131231032 = null;
    view2131230725.setOnClickListener(null);
    view2131230725 = null;
  }
}
