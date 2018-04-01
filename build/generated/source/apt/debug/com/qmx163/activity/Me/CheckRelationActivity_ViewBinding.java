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
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CheckRelationActivity_ViewBinding implements Unbinder {
  private CheckRelationActivity target;

  @UiThread
  public CheckRelationActivity_ViewBinding(CheckRelationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CheckRelationActivity_ViewBinding(CheckRelationActivity target, View source) {
    this.target = target;

    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mIvHead = Utils.findRequiredViewAsType(source, R.id.iv_head, "field 'mIvHead'", CircleImageView.class);
    target.mRvRelation = Utils.findRequiredViewAsType(source, R.id.rv_relation, "field 'mRvRelation'", RecyclerView.class);
    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'mTvName'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CheckRelationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mRlTitle = null;
    target.mIvHead = null;
    target.mRvRelation = null;
    target.mTvName = null;
    target.mUp = null;
  }
}
