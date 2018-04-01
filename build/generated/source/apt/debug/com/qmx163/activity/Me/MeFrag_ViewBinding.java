// Generated code from Butter Knife. Do not modify!
package com.qmx163.activity.Me;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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

public class MeFrag_ViewBinding implements Unbinder {
  private MeFrag target;

  @UiThread
  public MeFrag_ViewBinding(MeFrag target, View source) {
    this.target = target;

    target.imageleft = Utils.findRequiredViewAsType(source, R.id.iamgeleft, "field 'imageleft'", ImageView.class);
    target.tvLeft = Utils.findRequiredViewAsType(source, R.id.tv_left, "field 'tvLeft'", TextView.class);
    target.ibtnLeft = Utils.findRequiredViewAsType(source, R.id.ibtn_left, "field 'ibtnLeft'", RelativeLayout.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'tvTitle'", TextView.class);
    target.tvRight = Utils.findRequiredViewAsType(source, R.id.tv_right, "field 'tvRight'", TextView.class);
    target.RightImg = Utils.findRequiredViewAsType(source, R.id.Right_img, "field 'RightImg'", ImageView.class);
    target.yhName = Utils.findRequiredViewAsType(source, R.id.yh_Name, "field 'yhName'", TextView.class);
    target.yhSex = Utils.findRequiredViewAsType(source, R.id.yh_sexs, "field 'yhSex'", ImageView.class);
    target.yhJs = Utils.findRequiredViewAsType(source, R.id.yh_js, "field 'yhJs'", TextView.class);
    target.mTvLevel = Utils.findRequiredViewAsType(source, R.id.tv_level, "field 'mTvLevel'", TextView.class);
    target.mTvCollection = Utils.findRequiredViewAsType(source, R.id.tv_collection, "field 'mTvCollection'", TextView.class);
    target.mTvStar = Utils.findRequiredViewAsType(source, R.id.tv_star, "field 'mTvStar'", TextView.class);
    target.mStudyTime = Utils.findRequiredViewAsType(source, R.id.study_time, "field 'mStudyTime'", TextView.class);
    target.mReadSize = Utils.findRequiredViewAsType(source, R.id.read_size, "field 'mReadSize'", TextView.class);
    target.mStartSize = Utils.findRequiredViewAsType(source, R.id.start_size, "field 'mStartSize'", TextView.class);
    target.mTextLine1 = Utils.findRequiredViewAsType(source, R.id.text_line1, "field 'mTextLine1'", TextView.class);
    target.mTvStartSize = Utils.findRequiredViewAsType(source, R.id.tv_star_size, "field 'mTvStartSize'", TextView.class);
    target.mTvItemStudy = Utils.findRequiredViewAsType(source, R.id.tv_item_study, "field 'mTvItemStudy'", TextView.class);
    target.llStudyTime = Utils.findRequiredViewAsType(source, R.id.ll_study_time, "field 'llStudyTime'", LinearLayout.class);
    target.meBg = Utils.findRequiredViewAsType(source, R.id.iv_me_bg, "field 'meBg'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MeFrag target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageleft = null;
    target.tvLeft = null;
    target.ibtnLeft = null;
    target.tvTitle = null;
    target.tvRight = null;
    target.RightImg = null;
    target.yhName = null;
    target.yhSex = null;
    target.yhJs = null;
    target.mTvLevel = null;
    target.mTvCollection = null;
    target.mTvStar = null;
    target.mStudyTime = null;
    target.mReadSize = null;
    target.mStartSize = null;
    target.mTextLine1 = null;
    target.mTvStartSize = null;
    target.mTvItemStudy = null;
    target.llStudyTime = null;
    target.meBg = null;
  }
}
