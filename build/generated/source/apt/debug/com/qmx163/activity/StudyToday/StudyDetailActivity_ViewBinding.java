// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.StudyToday;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import java.lang.IllegalStateException;
import java.lang.Override;

public class StudyDetailActivity_ViewBinding implements Unbinder {
  private StudyDetailActivity target;

  private View view2131231003;

  private View view2131231424;

  private View view2131231038;

  private View view2131231060;

  private View view2131231037;

  @UiThread
  public StudyDetailActivity_ViewBinding(StudyDetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public StudyDetailActivity_ViewBinding(final StudyDetailActivity target, View source) {
    this.target = target;

    View view;
    target.llWeb = Utils.findRequiredViewAsType(source, R.id.ll_web_context, "field 'llWeb'", RelativeLayout.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
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
    target.mUp = Utils.findRequiredViewAsType(source, R.id.tv_bg_up, "field 'mUp'", TextView.class);
    target.mRightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'mRightImg'", ImageView.class);
    target.mTvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'mTvRight'", TextView.class);
    target.mRlTitle = Utils.findRequiredViewAsType(source, R.id.rl_title, "field 'mRlTitle'", RelativeLayout.class);
    target.mActivityStudyDetail = Utils.findRequiredViewAsType(source, R.id.activity_study_detail, "field 'mActivityStudyDetail'", LinearLayout.class);
    target.mTvLook = Utils.findRequiredViewAsType(source, R.id.tv_look, "field 'mTvLook'", TextView.class);
    target.mTvStar = Utils.findRequiredViewAsType(source, R.id.tv_star, "field 'mTvStar'", TextView.class);
    target.mTvCommentSize = Utils.findRequiredViewAsType(source, R.id.tv_comment_size, "field 'mTvCommentSize'", TextView.class);
    view = Utils.findRequiredView(source, R.id.tv_comment_write, "field 'mTvCommentWrite' and method 'onViewClicked'");
    target.mTvCommentWrite = Utils.castView(view, R.id.tv_comment_write, "field 'mTvCommentWrite'", TextView.class);
    view2131231424 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_comment, "field 'mIvComment' and method 'onViewClicked'");
    target.mIvComment = Utils.castView(view, R.id.iv_comment, "field 'mIvComment'", ImageView.class);
    view2131231038 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_star, "field 'mIvStar' and method 'onViewClicked'");
    target.mIvStar = Utils.castView(view, R.id.iv_star, "field 'mIvStar'", ImageView.class);
    view2131231060 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_collection, "field 'mIvCollection' and method 'onViewClicked'");
    target.mIvCollection = Utils.castView(view, R.id.iv_collection, "field 'mIvCollection'", ImageView.class);
    view2131231037 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.ll_star = Utils.findRequiredViewAsType(source, R.id.ll_star, "field 'll_star'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    StudyDetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llWeb = null;
    target.mRecyclerView = null;
    target.mIamgeleft = null;
    target.mTvLeft = null;
    target.mIbtnLeft = null;
    target.mTvTitle = null;
    target.mUp = null;
    target.mRightImg = null;
    target.mTvRight = null;
    target.mRlTitle = null;
    target.mActivityStudyDetail = null;
    target.mTvLook = null;
    target.mTvStar = null;
    target.mTvCommentSize = null;
    target.mTvCommentWrite = null;
    target.mIvComment = null;
    target.mIvStar = null;
    target.mIvCollection = null;
    target.ll_star = null;

    view2131231003.setOnClickListener(null);
    view2131231003 = null;
    view2131231424.setOnClickListener(null);
    view2131231424 = null;
    view2131231038.setOnClickListener(null);
    view2131231038 = null;
    view2131231060.setOnClickListener(null);
    view2131231060 = null;
    view2131231037.setOnClickListener(null);
    view2131231037 = null;
  }
}
