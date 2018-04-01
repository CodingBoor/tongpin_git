// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Message;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.qmx163.util.ArrowTextView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JpushMsgListActivity_ViewBinding implements Unbinder {
  private JpushMsgListActivity target;

  private View view2131231244;

  private View view2131231245;

  private View view2131231243;

  @UiThread
  public JpushMsgListActivity_ViewBinding(JpushMsgListActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public JpushMsgListActivity_ViewBinding(final JpushMsgListActivity target, View source) {
    this.target = target;

    View view;
    target.iamgeleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'iamgeleft'", ImageView.class);
    target.tvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'tvLeft'", TextView.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.ibtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'ibtnLeft'", RelativeLayout.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.RightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'RightImg'", ImageView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.rlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'rlTitle'", RelativeLayout.class);
    target.tvContent = Utils.findRequiredViewAsType(source, R.id.tv_content, "field 'tvContent'", TextView.class);
    target.timer = Utils.findRequiredViewAsType(source, R.id.timer, "field 'timer'", TextView.class);
    target.mImgIc = Utils.findRequiredViewAsType(source, R.id.imgIc, "field 'mImgIc'", CircleImageView.class);
    target.mArrowText = Utils.findRequiredViewAsType(source, R.id.arrowText, "field 'mArrowText'", ArrowTextView.class);
    target.mLlCheck = Utils.findRequiredViewAsType(source, R.id.ll_check, "field 'mLlCheck'", LinearLayout.class);
    target.mHuifu = Utils.findRequiredViewAsType(source, R.id.huifu, "field 'mHuifu'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rl_question_mingbai, "field 'mRlQuestionMingbai' and method 'onViewClicked'");
    target.mRlQuestionMingbai = Utils.castView(view, R.id.rl_question_mingbai, "field 'mRlQuestionMingbai'", com.rey.material.widget.RelativeLayout.class);
    view2131231244 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_question_soso, "field 'mRlQuestionSoso' and method 'onViewClicked'");
    target.mRlQuestionSoso = Utils.castView(view, R.id.rl_question_soso, "field 'mRlQuestionSoso'", com.rey.material.widget.RelativeLayout.class);
    view2131231245 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rl_question_innocent, "field 'mRlQuestionInnocent' and method 'onViewClicked'");
    target.mRlQuestionInnocent = Utils.castView(view, R.id.rl_question_innocent, "field 'mRlQuestionInnocent'", com.rey.material.widget.RelativeLayout.class);
    view2131231243 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mHuifuzhi = Utils.findRequiredViewAsType(source, R.id.huifuzhi, "field 'mHuifuzhi'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    JpushMsgListActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.iamgeleft = null;
    target.tvLeft = null;
    target.mUp = null;
    target.ibtnLeft = null;
    target.tvTitle = null;
    target.RightImg = null;
    target.tvRight = null;
    target.rlTitle = null;
    target.tvContent = null;
    target.timer = null;
    target.mImgIc = null;
    target.mArrowText = null;
    target.mLlCheck = null;
    target.mHuifu = null;
    target.mRlQuestionMingbai = null;
    target.mRlQuestionSoso = null;
    target.mRlQuestionInnocent = null;
    target.mHuifuzhi = null;

    view2131231244.setOnClickListener(null);
    view2131231244 = null;
    view2131231245.setOnClickListener(null);
    view2131231245 = null;
    view2131231243.setOnClickListener(null);
    view2131231243 = null;
  }
}
