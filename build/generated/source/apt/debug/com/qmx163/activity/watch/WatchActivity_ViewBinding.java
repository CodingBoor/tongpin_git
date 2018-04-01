// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.watch;

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

public class WatchActivity_ViewBinding implements Unbinder {
  private WatchActivity target;

  private View view2131231090;

  private View view2131231101;

  private View view2131231106;

  private View view2131231051;

  private View view2131230920;

  @UiThread
  public WatchActivity_ViewBinding(WatchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WatchActivity_ViewBinding(final WatchActivity target, View source) {
    this.target = target;

    View view;
    target.mIvFollow = Utils.findRequiredViewAsType(source, R.id.iv_follow, "field 'mIvFollow'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.ll_follow, "field 'mLlFollow' and method 'onViewClicked'");
    target.mLlFollow = Utils.castView(view, R.id.ll_follow, "field 'mLlFollow'", LinearLayout.class);
    view2131231090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_share, "field 'mLlShare' and method 'onViewClicked'");
    target.mLlShare = Utils.castView(view, R.id.ll_share, "field 'mLlShare'", LinearLayout.class);
    view2131231101 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvBranch = Utils.findRequiredViewAsType(source, R.id.tv_branch, "field 'mTvBranch'", TextView.class);
    target.mTvBranchBreak = Utils.findRequiredViewAsType(source, R.id.tv_branch_break, "field 'mTvBranchBreak'", TextView.class);
    target.mTvCourseSise = Utils.findRequiredViewAsType(source, R.id.tv_course_sise, "field 'mTvCourseSise'", TextView.class);
    view = Utils.findRequiredView(source, R.id.ll_teacher_detail, "field 'teachDetail' and method 'onViewClicked'");
    target.teachDetail = Utils.castView(view, R.id.ll_teacher_detail, "field 'teachDetail'", LinearLayout.class);
    view2131231106 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.text1 = Utils.findRequiredViewAsType(source, R.id.tv_text1, "field 'text1'", TextView.class);
    target.text2 = Utils.findRequiredViewAsType(source, R.id.tv_text2, "field 'text2'", TextView.class);
    target.chatFrame = Utils.findRequiredViewAsType(source, R.id.chatFrame, "field 'chatFrame'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.iv_message_show, "field 'ivMessageShow' and method 'onViewClicked'");
    target.ivMessageShow = Utils.castView(view, R.id.iv_message_show, "field 'ivMessageShow'", ImageView.class);
    view2131231051 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.contentVideo, "field 'contentVideo' and method 'onViewClicked'");
    target.contentVideo = Utils.castView(view, R.id.contentVideo, "field 'contentVideo'", FrameLayout.class);
    view2131230920 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tv_amount = Utils.findRequiredViewAsType(source, R.id.tv_amount, "field 'tv_amount'", TextView.class);
    target.mRlLive = Utils.findRequiredViewAsType(source, R.id.rl_live, "field 'mRlLive'", RelativeLayout.class);
    target.mRlShare = Utils.findRequiredViewAsType(source, R.id.rl_share, "field 'mRlShare'", RelativeLayout.class);
    target.llTeacher = Utils.findRequiredViewAsType(source, R.id.ll_teacher, "field 'llTeacher'", LinearLayout.class);
    target.mBgUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mBgUp'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WatchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIvFollow = null;
    target.mLlFollow = null;
    target.mLlShare = null;
    target.mTvBranch = null;
    target.mTvBranchBreak = null;
    target.mTvCourseSise = null;
    target.teachDetail = null;
    target.text1 = null;
    target.text2 = null;
    target.chatFrame = null;
    target.ivMessageShow = null;
    target.contentVideo = null;
    target.tv_amount = null;
    target.mRlLive = null;
    target.mRlShare = null;
    target.llTeacher = null;
    target.mBgUp = null;

    view2131231090.setOnClickListener(null);
    view2131231090 = null;
    view2131231101.setOnClickListener(null);
    view2131231101 = null;
    view2131231106.setOnClickListener(null);
    view2131231106 = null;
    view2131231051.setOnClickListener(null);
    view2131231051 = null;
    view2131230920.setOnClickListener(null);
    view2131230920 = null;
  }
}
