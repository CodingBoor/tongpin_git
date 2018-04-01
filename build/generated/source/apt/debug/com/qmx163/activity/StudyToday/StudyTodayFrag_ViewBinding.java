// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.StudyToday;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudyTodayFrag_ViewBinding implements Unbinder {
  private StudyTodayFrag target;

  private View view2131231003;

  private View view2131231487;

  @UiThread
  public StudyTodayFrag_ViewBinding(final StudyTodayFrag target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iamgeleft, "field 'mIamgeleft' and method 'onViewClicked'");
    target.mIamgeleft = Utils.castView(view, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    view2131231003 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mRbTitleLeft = Utils.findRequiredViewAsType(source, R.id.rb_title_left, "field 'mRbTitleLeft'", RadioButton.class);
    target.mRbTitleRight = Utils.findRequiredViewAsType(source, R.id.rb_title_right, "field 'mRbTitleRight'", RadioButton.class);
    target.mRgTitle = Utils.findRequiredViewAsType(source, R.id.rg_title, "field 'mRgTitle'", RadioGroup.class);
    target.mStudyToday = Utils.findRequiredViewAsType(source, R.id.study_today, "field 'mStudyToday'", FrameLayout.class);
    target.mStudyHistory = Utils.findRequiredViewAsType(source, R.id.study_history, "field 'mStudyHistory'", FrameLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_sigin, "field 'tvSigin' and method 'onViewClicked'");
    target.tvSigin = Utils.castView(view, R.id.tv_sigin, "field 'tvSigin'", TextView.class);
    view2131231487 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.appBar = Utils.findRequiredViewAsType(source, R.id.appBarLayout, "field 'appBar'", AppBarLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudyTodayFrag target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mUp = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mRlTitle = null;
    target.mRbTitleLeft = null;
    target.mRbTitleRight = null;
    target.mRgTitle = null;
    target.mStudyToday = null;
    target.mStudyHistory = null;
    target.tvSigin = null;
    target.appBar = null;

    view2131231003.setOnClickListener(null);
    view2131231003 = null;
    view2131231487.setOnClickListener(null);
    view2131231487 = null;
  }
}
