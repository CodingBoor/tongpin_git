// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AccompanyStudyActivity_ViewBinding implements Unbinder {
  private AccompanyStudyActivity target;

  @UiThread
  public AccompanyStudyActivity_ViewBinding(AccompanyStudyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AccompanyStudyActivity_ViewBinding(AccompanyStudyActivity target, View source) {
    this.target = target;

    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mIvAdd = Utils.findRequiredViewAsType(source, R.id.iv_add, "field 'mIvAdd'", ImageView.class);
    target.mRvStudy = Utils.findRequiredViewAsType(source, R.id.rv_study, "field 'mRvStudy'", RecyclerView.class);
    target.mLlIsEmpty = Utils.findRequiredViewAsType(source, R.id.ll_is_empty, "field 'mLlIsEmpty'", LinearLayout.class);
    target.mRlContext = Utils.findRequiredViewAsType(source, R.id.rl_context, "field 'mRlContext'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AccompanyStudyActivity target = this.target;
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
    target.mIvAdd = null;
    target.mRvStudy = null;
    target.mLlIsEmpty = null;
    target.mRlContext = null;
  }
}
