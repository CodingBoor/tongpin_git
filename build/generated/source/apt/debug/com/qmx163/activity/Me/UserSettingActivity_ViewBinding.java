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
import java.lang.IllegalStateException;
import java.lang.Override;

public class UserSettingActivity_ViewBinding implements Unbinder {
  private UserSettingActivity target;

  private View view2131231516;

  @UiThread
  public UserSettingActivity_ViewBinding(UserSettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public UserSettingActivity_ViewBinding(final UserSettingActivity target, View source) {
    this.target = target;

    View view;
    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.tv_updata_psw, "field 'mTvUpdataPsw' and method 'onViewClicked'");
    target.mTvUpdataPsw = Utils.castView(view, R.id.tv_updata_psw, "field 'mTvUpdataPsw'", TextView.class);
    view2131231516 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    UserSettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mUp = null;
    target.mRlTitle = null;
    target.mTvUpdataPsw = null;

    view2131231516.setOnClickListener(null);
    view2131231516 = null;
  }
}
