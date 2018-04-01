// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Found;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.qmx163.view.BannerView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FoundFragment_ViewBinding implements Unbinder {
  private FoundFragment target;

  private View view2131231003;

  private View view2131230738;

  @UiThread
  public FoundFragment_ViewBinding(final FoundFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iamgeleft, "field 'mIamgeleft' and method 'onViewClicked'");
    target.mIamgeleft = Utils.castView(view, R.id.iamgeleft, "field 'mIamgeleft'", ImageView.class);
    view2131231003 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'mTvLeft'", TextView.class);
    target.mIbtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'mIbtnLeft'", RelativeLayout.class);
    target.mTvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'mTvTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.Right_img, "field 'mRightImg' and method 'onViewClicked'");
    target.mRightImg = Utils.castView(view, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    view2131230738 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mFaxianListHeardBanner = Utils.findRequiredViewAsType(source, R.id.faxian_list_heard_banner, "field 'mFaxianListHeardBanner'", BannerView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
    target.nestScrollview = Utils.findRequiredViewAsType(source, R.id.nest_scrollview, "field 'nestScrollview'", NestedScrollView.class);
    target.ivEmpty = Utils.findRequiredViewAsType(source, R.id.iv_empty, "field 'ivEmpty'", ImageView.class);
    target.tvEmpty = Utils.findRequiredViewAsType(source, R.id.tv_empty, "field 'tvEmpty'", TextView.class);
    target.tvBgup = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'tvBgup'", TextView.class);
    target.llEmpty = Utils.findRequiredViewAsType(source, R.id.ll_empty, "field 'llEmpty'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FoundFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mRlTitle = null;
    target.mFaxianListHeardBanner = null;
    target.mRecyclerView = null;
    target.nestScrollview = null;
    target.ivEmpty = null;
    target.tvEmpty = null;
    target.tvBgup = null;
    target.llEmpty = null;

    view2131231003.setOnClickListener(null);
    view2131231003 = null;
    view2131230738.setOnClickListener(null);
    view2131230738 = null;
  }
}
