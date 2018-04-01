// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.qmx163.view.PinchImage.ToggleButton;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MySettingActivity_ViewBinding implements Unbinder {
  private MySettingActivity target;

  private View view2131231484;

  private View view2131231515;

  private View view2131231400;

  private View view2131231230;

  private View view2131231456;

  @UiThread
  public MySettingActivity_ViewBinding(MySettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MySettingActivity_ViewBinding(final MySettingActivity target, View source) {
    this.target = target;

    View view;
    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mTvUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mTvUp'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_setting, "field 'mTvSetting' and method 'onViewClicked'");
    target.mTvSetting = Utils.castView(view, R.id.tv_setting, "field 'mTvSetting'", TextView.class);
    view2131231484 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mBtPush = Utils.findRequiredViewAsType(source, R.id.bt_push, "field 'mBtPush'", ToggleButton.class);
    view = Utils.findRequiredView(source, R.id.tv_updata, "field 'mTvUpdata' and method 'onViewClicked'");
    target.mTvUpdata = Utils.castView(view, R.id.tv_updata, "field 'mTvUpdata'", TextView.class);
    view2131231515 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_about, "field 'mTvAbout' and method 'onViewClicked'");
    target.mTvAbout = Utils.castView(view, R.id.tv_about, "field 'mTvAbout'", TextView.class);
    view2131231400 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvCache = Utils.findRequiredViewAsType(source, R.id.tv_cache, "field 'mTvCache'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_clean, "field 'mRlClean' and method 'onViewClicked'");
    target.mRlClean = Utils.castView(view, R.id.rl_clean, "field 'mRlClean'", RelativeLayout.class);
    view2131231230 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_logout, "field 'mTvLogout' and method 'onViewClicked'");
    target.mTvLogout = Utils.castView(view, R.id.tv_logout, "field 'mTvLogout'", TextView.class);
    view2131231456 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.bt_switch = Utils.findRequiredViewAsType(source, R.id.bt_switch, "field 'bt_switch'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MySettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mTvUp = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mRlTitle = null;
    target.mTvSetting = null;
    target.mBtPush = null;
    target.mTvUpdata = null;
    target.mTvAbout = null;
    target.mTvCache = null;
    target.mRlClean = null;
    target.mTvLogout = null;
    target.bt_switch = null;

    view2131231484.setOnClickListener(null);
    view2131231484 = null;
    view2131231515.setOnClickListener(null);
    view2131231515 = null;
    view2131231400.setOnClickListener(null);
    view2131231400 = null;
    view2131231230.setOnClickListener(null);
    view2131231230 = null;
    view2131231456.setOnClickListener(null);
    view2131231456 = null;
  }
}
