// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Found;

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
import java.lang.IllegalStateException;
import java.lang.Override;

public class BannerDetailActivity_ViewBinding implements Unbinder {
  private BannerDetailActivity target;

  private View view2131231003;

  @UiThread
  public BannerDetailActivity_ViewBinding(BannerDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BannerDetailActivity_ViewBinding(final BannerDetailActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iamgeleft, "field 'mIamgeleft' and method 'onViewClicked'");
    target.mIamgeleft = Utils.castView(view, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    view2131231003 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mTvUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mTvUp'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mIvEmpty = Utils.findRequiredViewAsType(source, R.id.iv_empty, "field 'mIvEmpty'", ImageView.class);
    target.mTvEmpty = Utils.findRequiredViewAsType(source, R.id.tv_empty, "field 'mTvEmpty'", TextView.class);
    target.mLlEmpty = Utils.findRequiredViewAsType(source, R.id.ll_empty, "field 'mLlEmpty'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BannerDetailActivity target = this.target;
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
    target.mIvEmpty = null;
    target.mTvEmpty = null;
    target.mLlEmpty = null;

    view2131231003.setOnClickListener(null);
    view2131231003 = null;
  }
}
