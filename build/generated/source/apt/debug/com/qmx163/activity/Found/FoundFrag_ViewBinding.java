// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Found;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.qmx163.view.BannerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FoundFrag_ViewBinding implements Unbinder {
  private FoundFrag target;

  private View view2131230738;

  private View view2131230986;

  private View view2131230984;

  private View view2131230987;

  private View view2131230983;

  @UiThread
  public FoundFrag_ViewBinding(final FoundFrag target, View source) {
    this.target = target;

    View view;
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.Right_img, "field 'imageRight' and method 'onClick'");
    target.imageRight = Utils.castView(view, R.id.Right_img, "field 'imageRight'", ImageView.class);
    view2131230738 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.heardBanner = Utils.findRequiredViewAsType(source, R.id.faxian_list_heard_banner, "field 'heardBanner'", BannerView.class);
    target.foundVp = Utils.findRequiredViewAsType(source, R.id.found_vp, "field 'foundVp'", ViewPager.class);
    view = Utils.findRequiredView(source, R.id.found_viewpage_top_tab_new_tv, "field 'newTv' and method 'onClick'");
    target.newTv = Utils.castView(view, R.id.found_viewpage_top_tab_new_tv, "field 'newTv'", TextView.class);
    view2131230986 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.found_viewpage_top_tab_hot_tv, "field 'hotTv' and method 'onClick'");
    target.hotTv = Utils.castView(view, R.id.found_viewpage_top_tab_hot_tv, "field 'hotTv'", TextView.class);
    view2131230984 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.found_viewpage_top_tab_teacher_tv, "field 'teacherTv' and method 'onClick'");
    target.teacherTv = Utils.castView(view, R.id.found_viewpage_top_tab_teacher_tv, "field 'teacherTv'", TextView.class);
    view2131230987 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.found_viewpage_top_tab_course_tv, "field 'courseTv' and method 'onClick'");
    target.courseTv = Utils.castView(view, R.id.found_viewpage_top_tab_course_tv, "field 'courseTv'", TextView.class);
    view2131230983 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    target.courseImg = Utils.findRequiredViewAsType(source, R.id.found_viewpage_top_tab_course_img, "field 'courseImg'", ImageView.class);
    target.courseImgFlag = Utils.findRequiredViewAsType(source, R.id.found_viewpage_top_tab_course_flag, "field 'courseImgFlag'", ImageView.class);
    target.mTabLineIv = Utils.findRequiredViewAsType(source, R.id.found_viewpage_top_tab_line_iv, "field 'mTabLineIv'", ImageView.class);
    target.appBar = Utils.findRequiredViewAsType(source, R.id.appBar, "field 'appBar'", AppBarLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FoundFrag target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvTitle = null;
    target.imageRight = null;
    target.heardBanner = null;
    target.foundVp = null;
    target.newTv = null;
    target.hotTv = null;
    target.teacherTv = null;
    target.courseTv = null;
    target.courseImg = null;
    target.courseImgFlag = null;
    target.mTabLineIv = null;
    target.appBar = null;

    view2131230738.setOnClickListener(null);
    view2131230738 = null;
    view2131230986.setOnClickListener(null);
    view2131230986 = null;
    view2131230984.setOnClickListener(null);
    view2131230984 = null;
    view2131230987.setOnClickListener(null);
    view2131230987 = null;
    view2131230983.setOnClickListener(null);
    view2131230983 = null;
  }
}
