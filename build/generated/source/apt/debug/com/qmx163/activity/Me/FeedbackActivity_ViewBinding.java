// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FeedbackActivity_ViewBinding implements Unbinder {
  private FeedbackActivity target;

  private View view2131231482;

  @UiThread
  public FeedbackActivity_ViewBinding(FeedbackActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FeedbackActivity_ViewBinding(final FeedbackActivity target, View source) {
    this.target = target;

    View view;
    target.mIamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mRvFeedback = Utils.findRequiredViewAsType(source, R.id.rv_feedback, "field 'mRvFeedback'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.tv_send, "field 'tvSend' and method 'onViewClicked'");
    target.tvSend = Utils.castView(view, R.id.tv_send, "field 'tvSend'", TextView.class);
    view2131231482 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlPhone = Utils.findRequiredViewAsType(source, R.id.rl_phone, "field 'rlPhone'", EditText.class);
    target.contxt = Utils.findRequiredViewAsType(source, R.id.contxt, "field 'contxt'", EditText.class);
    target.textSize = Utils.findRequiredViewAsType(source, R.id.textSize, "field 'textSize'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FeedbackActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mRlTitle = null;
    target.mRvFeedback = null;
    target.tvSend = null;
    target.rlPhone = null;
    target.contxt = null;
    target.textSize = null;

    view2131231482.setOnClickListener(null);
    view2131231482 = null;
  }
}
