// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyEnergyActivity_ViewBinding implements Unbinder {
  private MyEnergyActivity target;

  @UiThread
  public MyEnergyActivity_ViewBinding(MyEnergyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyEnergyActivity_ViewBinding(MyEnergyActivity target, View source) {
    this.target = target;

    target.mRvEnergy = Utils.findRequiredViewAsType(source, R.id.rv_energy, "field 'mRvEnergy'", RecyclerView.class);
    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mTvScore = Utils.findRequiredViewAsType(source, R.id.tv_score, "field 'mTvScore'", TextView.class);
    target.tvBgUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'tvBgUp'", TextView.class);
    target.mTvOne = Utils.findRequiredViewAsType(source, R.id.tv_one, "field 'mTvOne'", TextView.class);
    target.mTvTwo = Utils.findRequiredViewAsType(source, R.id.tv_two, "field 'mTvTwo'", TextView.class);
    target.mTvThree = Utils.findRequiredViewAsType(source, R.id.tv_three, "field 'mTvThree'", TextView.class);
    target.mTvFout = Utils.findRequiredViewAsType(source, R.id.tv_fout, "field 'mTvFout'", TextView.class);
    target.mTvFive = Utils.findRequiredViewAsType(source, R.id.tv_five, "field 'mTvFive'", TextView.class);
    target.mTvSix = Utils.findRequiredViewAsType(source, R.id.tv_six, "field 'mTvSix'", TextView.class);
    target.mTvServen = Utils.findRequiredViewAsType(source, R.id.tv_serven, "field 'mTvServen'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyEnergyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRvEnergy = null;
    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mRlTitle = null;
    target.mTvScore = null;
    target.tvBgUp = null;
    target.mTvOne = null;
    target.mTvTwo = null;
    target.mTvThree = null;
    target.mTvFout = null;
    target.mTvFive = null;
    target.mTvSix = null;
    target.mTvServen = null;
  }
}
