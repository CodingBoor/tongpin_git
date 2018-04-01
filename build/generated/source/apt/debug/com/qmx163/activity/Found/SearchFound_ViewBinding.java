// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Found;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.qmx163.R;
import com.rey.material.widget.ImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchFound_ViewBinding implements Unbinder {
  private SearchFound target;

  private View view2131231287;

  private View view2131231283;

  @UiThread
  public SearchFound_ViewBinding(SearchFound target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SearchFound_ViewBinding(final SearchFound target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.search_found_type, "field 'typeTv' and method 'onViewClicked'");
    target.typeTv = Utils.castView(view, R.id.search_found_type, "field 'typeTv'", TextView.class);
    view2131231287 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.keyWordEt = Utils.findRequiredViewAsType(source, R.id.search_found_keyword, "field 'keyWordEt'", EditText.class);
    view = Utils.findRequiredView(source, R.id.search_found_cancel, "field 'cancelEt' and method 'onViewClicked'");
    target.cancelEt = Utils.castView(view, R.id.search_found_cancel, "field 'cancelEt'", TextView.class);
    view2131231283 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.lineView = Utils.findRequiredView(source, R.id.search_found_line, "field 'lineView'");
    target.ivDel = Utils.findRequiredViewAsType(source, R.id.iv_del, "field 'ivDel'", ImageView.class);
    target.ll_bg = Utils.findRequiredViewAsType(source, R.id.ll_bg, "field 'll_bg'", LinearLayout.class);
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.searchFragment = Utils.findRequiredViewAsType(source, R.id.search_found_fragment, "field 'searchFragment'", FrameLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchFound target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.typeTv = null;
    target.keyWordEt = null;
    target.cancelEt = null;
    target.lineView = null;
    target.ivDel = null;
    target.ll_bg = null;
    target.mUp = null;
    target.searchFragment = null;

    view2131231287.setOnClickListener(null);
    view2131231287 = null;
    view2131231283.setOnClickListener(null);
    view2131231283 = null;
  }
}
