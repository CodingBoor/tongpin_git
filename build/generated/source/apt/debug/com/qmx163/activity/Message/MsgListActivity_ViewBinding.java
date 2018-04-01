// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Message;

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

public class MsgListActivity_ViewBinding implements Unbinder {
  private MsgListActivity target;

  @UiThread
  public MsgListActivity_ViewBinding(MsgListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MsgListActivity_ViewBinding(MsgListActivity target, View source) {
    this.target = target;

    target.iamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'iamgeleft'", ImageView.class);
    target.tvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'tvLeft'", TextView.class);
    target.mTvUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mTvUp'", TextView.class);
    target.ibtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'ibtnLeft'", RelativeLayout.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.RightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'RightImg'", ImageView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.rlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'rlTitle'", RelativeLayout.class);
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MsgListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iamgeleft = null;
    target.tvLeft = null;
    target.mTvUp = null;
    target.ibtnLeft = null;
    target.tvTitle = null;
    target.RightImg = null;
    target.tvRight = null;
    target.rlTitle = null;
    target.recyclerView = null;
  }
}
