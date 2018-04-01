// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CompleteInformationAc_ViewBinding implements Unbinder {
  private CompleteInformationAc target;

  private View view2131231449;

  private View view2131230727;

  private View view2131231150;

  private View view2131231115;

  @UiThread
  public CompleteInformationAc_ViewBinding(CompleteInformationAc target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CompleteInformationAc_ViewBinding(final CompleteInformationAc target, View source) {
    this.target = target;

    View view;
    target.imageleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'imageleft'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.tv_left, "field 'tvLeft' and method 'onViewClicked'");
    target.tvLeft = Utils.castView(view, R.id.tv_left, "field 'tvLeft'", TextView.class);
    view2131231449 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ibtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'ibtnLeft'", RelativeLayout.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.imageRight = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'imageRight'", ImageView.class);
    target.avatar = Utils.findRequiredViewAsType(source, R.id.avatar, "field 'avatar'", CircleImageView.class);
    target.activityCompleteInformation = Utils.findRequiredViewAsType(source, R.id.activity_complete_information, "field 'activityCompleteInformation'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.Lay_avatar, "field 'LayAvatar' and method 'onViewClicked'");
    target.LayAvatar = Utils.castView(view, R.id.Lay_avatar, "field 'LayAvatar'", LinearLayout.class);
    view2131230727 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.off, "field 'off' and method 'onViewClicked'");
    target.off = Utils.castView(view, R.id.off, "field 'off'", ImageView.class);
    view2131231150 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.loginGo, "field 'loginGo' and method 'onViewClicked'");
    target.loginGo = Utils.castView(view, R.id.loginGo, "field 'loginGo'", com.rey.material.widget.TextView.class);
    view2131231115 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etName = Utils.findRequiredViewAsType(source, R.id.et_name, "field 'etName'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CompleteInformationAc target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageleft = null;
    target.tvLeft = null;
    target.ibtnLeft = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.mUp = null;
    target.imageRight = null;
    target.avatar = null;
    target.activityCompleteInformation = null;
    target.LayAvatar = null;
    target.off = null;
    target.loginGo = null;
    target.etName = null;

    view2131231449.setOnClickListener(null);
    view2131231449 = null;
    view2131230727.setOnClickListener(null);
    view2131230727 = null;
    view2131231150.setOnClickListener(null);
    view2131231150 = null;
    view2131231115.setOnClickListener(null);
    view2131231115 = null;
  }
}
