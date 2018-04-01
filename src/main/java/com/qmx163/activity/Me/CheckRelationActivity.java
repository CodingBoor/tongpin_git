package com.qmx163.activity.Me;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseAc;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by likai on 2017/7/4.
 * 选择关系界面
 */

public class CheckRelationActivity extends BaseAc {

    @BindView(R.id.iamgeleft)
    ImageView mIamgeleft;
    @BindView(R.id.tv_left)
    TextView mTvLeft;
    @BindView(R.id.ibtn_left)
    RelativeLayout mIbtnLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.Right_img)
    ImageView mRightImg;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.iv_head)
    CircleImageView mIvHead;
    @BindView(R.id.rv_relation)
    RecyclerView mRvRelation;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_bg_up)
    TextView mUp;
    private RecyclerAdapter relationAdapter;
    private List<String> relationList = new ArrayList<>();
    private String chileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_relation);
        ButterKnife.bind(this);

        //设置statusbar高度
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);

            RelativeLayout.LayoutParams linearParams =(RelativeLayout.LayoutParams) mUp.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20

            linearParams.height = statusBarHeight1;// 控件的宽强制设成30

            mUp.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

        Glide.with(this).load(PrefUtils.getString(this, Constants.USER_IMG, "")).into(mIvHead);
        mTvName.setText(PrefUtils.getString(this, Constants.USER_NAME, ""));
        mIbtnLeft.setVisibility(View.VISIBLE);
        mTvTitle.setText(R.string.me_title_check_relation);
        mRightImg.setVisibility(View.GONE);
        chileId = getIntent().getStringExtra("result");
        bindRecyclerView();

    }

    private void bindRecyclerView() {
        getDate();
        this.relationAdapter = new RecyclerAdapter<String>(this, relationList, R.layout.item_check_relation) {
            @Override
            public void convert(RecyclerViewHolder helper, String item, final int position) {
                final TextView tvRelation = helper.getView(R.id.tv_relation);
                helper.setText(R.id.tv_relation, relationList.get(position));
                tvRelation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (position != relationList.size() - 1) {

                            tvRelation.setBackgroundResource(R.drawable.shape_study_sleep);
                            tvRelation.setTextColor(Color.WHITE);
//                        Toast.makeText(CheckRelationActivity.this, relationList.get(position), Toast.LENGTH_LONG).show();
                            showProgressDialog();
                            //添加陪伴
                            addrelation(relationList.get(position));

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CheckRelationActivity.this);
                            builder.setTitle("请输入亲戚关系");
                            View view = LayoutInflater.from(CheckRelationActivity.this).inflate(R.layout.dialog_check_relation, null);
                            builder.setView(view);
                            final EditText etRelation = (EditText) view.findViewById(R.id.et_relation);
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    if (etRelation.getText().toString().equals("")) {
                                        showToas("关系不能为空");
                                    } else {
                                        addrelation(etRelation.getText().toString());
                                    }
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        }
                    }
                });

            }

//            @Override
//            public void setOnItemClickListener(OnItemClickListener listener) {
//                super.setOnItemClickListener(listener);
//
//            }
        };

        mRvRelation.setHasFixedSize(true);
        mRvRelation.setLayoutManager(new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false));
        mRvRelation.setAdapter(relationAdapter);

    }

    private void addrelation(String relation) {
        addSubscription(apiStores.addAccompany(PrefUtils.getString(CheckRelationActivity.this, Constants.USER_ID, ""),chileId, relation), new ApiCallback<SocketMessage>() {
            @Override
            public void onSuccess(SocketMessage model) {
                dismissProgressDialog();
                if (model.getCode().equals("200")) {
                    showToas(model.getMessage());
//                                    startAc(AccompanyStudyActivity.class);
                    finish();
                } else {
                    showToas(model.getMessage());
                }
            }

            @Override
            public void onFailure(String code) {
                dismissProgressDialog();
                finish();
                if (code.equals("999")){
                showToas("已添加陪伴计划");
                }
            }

            @Override
            public void onFinish() {
dismissProgressDialog();
            }
        });
    }

    private void getDate() {
        relationList.add("爸爸");
        relationList.add("妈妈");
        relationList.add("爷爷");
        relationList.add("奶奶");
        relationList.add("外公");
        relationList.add("外婆");
        relationList.add("其他");
    }
}
