// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyDateActivity_ViewBinding implements Unbinder {
  private MyDateActivity target;

  @UiThread
  public MyDateActivity_ViewBinding(MyDateActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyDateActivity_ViewBinding(MyDateActivity target, View source) {
    this.target = target;

    target.iamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'iamgeleft'", ImageView.class);
    target.tvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'tvLeft'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.ibtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'ibtnLeft'", RelativeLayout.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.RightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'RightImg'", ImageView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.mCivHead = Utils.findRequiredViewAsType(source, R.id.civ_head, "field 'mCivHead'", CircleImageView.class);
    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'mTvName'", TextView.class);
    target.mRlName = Utils.findRequiredViewAsType(source, R.id.rl_name, "field 'mRlName'", RelativeLayout.class);
    target.mTvSex = Utils.findRequiredViewAsType(source, R.id.tv_sex, "field 'mTvSex'", TextView.class);
    target.mRlSex = Utils.findRequiredViewAsType(source, R.id.rl_sex, "field 'mRlSex'", RelativeLayout.class);
    target.mTvBirthday = Utils.findRequiredViewAsType(source, R.id.tv_birthday, "field 'mTvBirthday'", TextView.class);
    target.mRlBirthday = Utils.findRequiredViewAsType(source, R.id.rl_birthday, "field 'mRlBirthday'", RelativeLayout.class);
    target.mTvPhone = Utils.findRequiredViewAsType(source, R.id.tv_phone, "field 'mTvPhone'", TextView.class);
    target.mRlPhone = Utils.findRequiredViewAsType(source, R.id.rl_phone, "field 'mRlPhone'", RelativeLayout.class);
    target.mTvNameLeft = Utils.findRequiredViewAsType(source, R.id.tv_name_left, "field 'mTvNameLeft'", TextView.class);
    target.mTvSexLeft = Utils.findRequiredViewAsType(source, R.id.tv_sex_left, "field 'mTvSexLeft'", TextView.class);
    target.mTvBirthdayLeft = Utils.findRequiredViewAsType(source, R.id.tv_birthday_left, "field 'mTvBirthdayLeft'", TextView.class);
    target.mTvPhoneLeft = Utils.findRequiredViewAsType(source, R.id.tv_phone_left, "field 'mTvPhoneLeft'", TextView.class);
    target.mRlHead = Utils.findRequiredViewAsType(source, R.id.rl_head, "field 'mRlHead'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyDateActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iamgeleft = null;
    target.tvLeft = null;
    target.mUp = null;
    target.ibtnLeft = null;
    target.tvTitle = null;
    target.RightImg = null;
    target.tvRight = null;
    target.mCivHead = null;
    target.mTvName = null;
    target.mRlName = null;
    target.mTvSex = null;
    target.mRlSex = null;
    target.mTvBirthday = null;
    target.mRlBirthday = null;
    target.mTvPhone = null;
    target.mRlPhone = null;
    target.mTvNameLeft = null;
    target.mTvSexLeft = null;
    target.mTvBirthdayLeft = null;
    target.mTvPhoneLeft = null;
    target.mRlHead = null;
  }
}
