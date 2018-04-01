package com.qmx163.activity.Found;

import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.application.MyApplication;
import com.qmx163.base.BaseFm;
import com.qmx163.data.bean.Mebean.GetBannersEn;
import com.qmx163.data.bean.Mebean.subjects;
import com.qmx163.net.ApiCallback;
import com.qmx163.view.BannerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.qmx163.R.id.Right_img;


/**
 * 发现
 * Created by Administrator on 2017/6/13.
 * lyf on 2017.7.1
 */

public class FoundFrag extends BaseFm implements View.OnClickListener {

    // 头部状态栏
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(Right_img)
    ImageView imageRight;

    // 头部Banner
    @BindView(R.id.faxian_list_heard_banner)
    BannerView heardBanner;

    // 展示数据的ViewPage
    @BindView(R.id.found_vp)
    ViewPager foundVp;

    // 切换标签选项卡
    @BindView(R.id.found_viewpage_top_tab_new_tv)
    TextView newTv;
    @BindView(R.id.found_viewpage_top_tab_hot_tv)
    TextView hotTv;
    @BindView(R.id.found_viewpage_top_tab_teacher_tv)
    TextView teacherTv;
    // 课程相关选项
    @BindView(R.id.found_viewpage_top_tab_course_tv)
    TextView courseTv;
    @BindView(R.id.found_viewpage_top_tab_course_img)
    ImageView courseImg;
    @BindView(R.id.found_viewpage_top_tab_course_flag)
    ImageView courseImgFlag;


    //Tab的引导线
    @BindView(R.id.found_viewpage_top_tab_line_iv)
    ImageView mTabLineIv;
    @BindView(R.id.appBar)
    AppBarLayout appBar;

    //Fragment
    private FoundHotNewListFrag newFragment;
    private FoundHotNewListFrag hotFragment;
    private FoundTeacherListFrag teacherFragment;


    //存放Fragment 的list 集合
    private List<Fragment> fragmentList = new ArrayList<>();
    //FragmentList设配器
    private FoundFragmentListAdapter adapter;


    private int pageIndex;                      //ViewPager 当前选中页
    private int screenWidth;                    //屏幕的宽度

    //默认左边的距离
    private int leftWidth;
    //Tab的引导线的宽度
    private int mTabLineWidth;

    // 是否已经显示弹窗
    private boolean isShowPopWindows = false;
    private PopupWindow popupWindow;
    public static SwipeRefreshLayout swipe;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.found_frag, container, false);
        ButterKnife.bind(this, view);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipe.setProgressViewOffset(true, -20, 100);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipe.setEnabled(true);
                } else {
                    swipe.setEnabled(false);
                }
            }
        });
        initViews();
        initFragment();
        initTabLineWidth();
        getSubjects();
        return view;
    }

    public void initViews() {
        imageRight.setVisibility(View.VISIBLE);
        tvTitle.setText("陪伴教育");

        getBanners();
        swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipe.setRefreshing(false);
                showToas("主fragment刷新");
            }
        });
    }


    /**
     * 加载广告轮播图
     */
    private void getBanners() {
        addSubscription(apiStores.getBanners("31", "", "", 1), new ApiCallback<GetBannersEn>() {
            @Override
            public void onSuccess(GetBannersEn model) {
                // 填充轮播图
                List<String> strList = new ArrayList<>();
                for (int i = 0; i < model.getList().size(); i++) {
                    strList.add(model.getList().get(i).getImg());
                }
                heardBanner.setList(strList);
                heardBanner.setOnBannerItemClickListener(new BannerView.OnBannerItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        Toast.makeText(getActivity(), "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });
    }


    //初始化Fragment
    private void initFragment() {
        fragmentList.clear();
        newFragment = FoundHotNewListFrag.newInstance("", "", "1", "0", SeleBy);//最新
        hotFragment = FoundHotNewListFrag.newInstance("", "", "1", "1", SeleBy);//热门
        teacherFragment = FoundTeacherListFrag.newInstance("", "", "1", SeleBy);
        fragmentList.add(newFragment);
        fragmentList.add(hotFragment);
        fragmentList.add(teacherFragment);

        adapter = new FoundFragmentListAdapter(getFragmentManager(), fragmentList);
        foundVp.setAdapter(adapter);
        foundVp.setCurrentItem(0);
        //页面滑动事件
        foundVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
            @Override
            public void onPageScrolled(int position, float offset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();

                /**
                 * 利用currentIndex(当前所在页面)和position(下一个页面)以及offset来
                 * 设置mTabLineIv的左边距 滑动场景：
                 * 记3个页面,
                 * 从左到右分别为0,1,2
                 * 0->1; 1->2; 2->1; 1->0
                 */
                if (pageIndex == 0 && position == 0)// 0->1
                {
                    lp.leftMargin = (int) (offset * mTabLineWidth) + pageIndex * mTabLineWidth + leftWidth;
                } else if (pageIndex == 1 && position == 0) // 1->0
                {
                    lp.leftMargin = (int) (-(1 - offset) * mTabLineWidth) + pageIndex * mTabLineWidth + leftWidth;
                } else if (pageIndex == 1 && position == 1) // 1->2
                {
                    lp.leftMargin = (int) (offset * mTabLineWidth) + pageIndex * mTabLineWidth + leftWidth;
                } else if (pageIndex == 2 && position == 1) // 2->1
                {
                    lp.leftMargin = (int) (-(1 - offset) * mTabLineWidth) + pageIndex * mTabLineWidth + leftWidth;
                }
                mTabLineIv.setLayoutParams(lp);
                Log.e("lyf", "这是实时监听");
            }

            @Override
            public void onPageSelected(int position) {
                initSelectPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    //初始化滑动条宽度
    private void initTabLineWidth() {

        int screenWidth = MyApplication.getInstance().getmWidth();            // 获取屏幕宽度

        //设置控件的宽度
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();

        mTabLineWidth = screenWidth / 4;

        lp.width = mTabLineWidth;
        lp.leftMargin = leftWidth;

        mTabLineIv.setLayoutParams(lp);


//        //初始化Tab引导线宽度
//        final LinearLayout ll = (LinearLayout) view.findViewById(R.id.id_switch_tab_ll);
//
//        // region  嵌入CoordinatorLayout 后，该方式失效 用下面的方式
////        ViewTreeObserver vto2 = ll.getViewTreeObserver();
////        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
////            @Override
////            public void onGlobalLayout() {
////                ll.getViewTreeObserver().removeGlobalOnLayoutListener(this);
////                int llWidth = ll.getMeasuredWidth();
////
////                //获取屏幕的宽度
////                DisplayMetrics dpMetrics = new DisplayMetrics();
////                getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
////                screenWidth = dpMetrics.widthPixels;
////                leftWidth = (screenWidth - llWidth) / 2;
////
////                //设置控件的宽度
////                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
////
////                mTabLineWidth = llWidth / 3;
////
////                lp.width = mTabLineWidth;
////                lp.leftMargin = leftWidth;
////
////                mTabLineIv.setLayoutParams(lp);
////                Log.e("lyf", "引导线宽度：" + mTabLineWidth);
////            }
////        });
//
//        //endregion
//
//        //measure的参数为0即可
//
//        ll.measure(0, 0);
//        //获取组件的宽度
//        int llWidth = ll.getMeasuredWidth();
//
//        //获取屏幕的宽度
//        DisplayMetrics dpMetrics = new DisplayMetrics();
//        getActivity().getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
//        screenWidth = dpMetrics.widthPixels;
//        // 距离左边的距离
//        leftWidth = (screenWidth - llWidth) / 2;
//
//        //设置控件的宽度
//        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
//
//        // 下划线宽度为1/4
//        mTabLineWidth = llWidth / 4;
//
//        lp.width = mTabLineWidth;
//        lp.leftMargin = leftWidth;
//
//        mTabLineIv.setLayoutParams(lp);
//        Log.e("lyf", "引导线宽度：" + mTabLineWidth);

    }

    //设置Tab位置
    private void initTabLineLocation(int index) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();

        lp.leftMargin = index * mTabLineWidth + leftWidth;
        mTabLineIv.setLayoutParams(lp);

    }

    //修改选选中的标签状态
    private void initSelectPage(int position) {
        resetTextView();
        switch (position) {
            case 0:
                newTv.setTextColor(ContextCompat.getColor(getActivity(), R.color.juhuan1));
                break;
            case 1:
                hotTv.setTextColor(ContextCompat.getColor(getActivity(), R.color.juhuan1));
                break;
            case 2:
                teacherTv.setTextColor(ContextCompat.getColor(getActivity(), R.color.juhuan1));
                break;
        }
        pageIndex = position;
    }

    // 重置Tab颜色
    private void resetTextView() {
        newTv.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_deep));
        hotTv.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_deep));
        teacherTv.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_deep));
    }

    int index = 0;

    @OnClick({Right_img, R.id.found_viewpage_top_tab_new_tv, R.id.found_viewpage_top_tab_hot_tv,
            R.id.found_viewpage_top_tab_teacher_tv, R.id.found_viewpage_top_tab_course_tv})
    public void onClick(View v) {
        switch (v.getId()) {
            case Right_img:
                startAc(SearchFound.class);
                break;
            case R.id.found_viewpage_top_tab_new_tv:
                index = 0;
                //修改选中标签
                initSelectPage(0);
                // 修改标签下的下划线
                initTabLineLocation(0);
                // 设置显示的ViewPage
                foundVp.setCurrentItem(0);
                break;
            case R.id.found_viewpage_top_tab_hot_tv:
                index = 1;
                //修改选中标签
                initSelectPage(1);
                // 修改标签下的下划线
                initTabLineLocation(1);
                // 设置显示的ViewPage
                foundVp.setCurrentItem(1);
                break;
            case R.id.found_viewpage_top_tab_teacher_tv:
                index = 2;
                //修改选中标签
                initSelectPage(2);
                // 修改标签下的下划线
                initTabLineLocation(2);
                // 设置显示的ViewPage
                foundVp.setCurrentItem(2);
                break;
            case R.id.found_viewpage_top_tab_course_tv:
//                getSubjects();
                showPopWindows();
                break;
        }
    }

    List<subjects.DataBean> listSub = new ArrayList<>();
    private RecyclerAdapter energyAdapter;
    RecyclerView recyclerView;

    /**
     * 所有科目返回
     */
    private void getSubjects() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("rq", "/app/subjects");
        addSubscription(apiStores.subjects(params), new ApiCallback<subjects>() {
            @Override
            public void onSuccess(subjects model) {
                if ("200".equals(model.getCode())) {
                    listSub.clear();
                    subjects.DataBean bean = new subjects.DataBean();
                    bean.setId("");
                    bean.setName("学科");
                    listSub.add(bean);
                    listSub.addAll(model.getData());
                }
            }

            @Override
            public void onFailure(String code) {

            }

            @Override
            public void onFinish() {

            }
        });
    }

    String SeleBy = "";

    /**
     * 初始化recyclerview
     */
    private void bindRecyclerView() {
        energyAdapter = new RecyclerAdapter<subjects.DataBean>(getActivity(), listSub, R.layout.sub_found) {
            @Override
            public void convert(RecyclerViewHolder helper, subjects.DataBean item, int position) {
                helper.setText(R.id.sub_name, item.getName());
            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.energyAdapter);
        energyAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View parent, int position) {
                SeleBy = listSub.get(position).getId();
                if (index == 0) {
                    newFragment.getDateFx("0", SeleBy);
                } else if (index == 1) {
                    hotFragment.getDateFx("1", SeleBy);
                } else {
                    teacherFragment.bindReceiverViewFx(SeleBy, "");
                }
                courseTv.setText(listSub.get(position).getName());
                popupWindow.dismiss();
            }
        });
    }


    //显示或关闭课程选择弹窗
    private void showPopWindows() {
        if (isShowPopWindows) {
            return;
        }
        isShowPopWindows = true;
        // 修改右边的小图标
        courseImgFlag.setImageDrawable(getResources().getDrawable(R.mipmap.drop_down_open3));
        View view = getActivity().getLayoutInflater().inflate(R.layout.found_course_popwin, null);

        popupWindow = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popUpWindow 弹窗可点击。下面两句话必须添加，并且是true
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new PaintDrawable());

        // 显示的位置
        popupWindow.showAsDropDown(mTabLineIv, 0, 0);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isShowPopWindows = false;
                // 修改右边的小图标
                courseImgFlag.setImageDrawable(getResources().getDrawable(R.mipmap.drop_down_close3));
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        bindRecyclerView();
//        LinearLayout disciplineLl = (LinearLayout) view.findViewById(R.id.found_course_popwin_discipline_ll);
//        LinearLayout chineseLl = (LinearLayout) view.findViewById(R.id.found_course_popwin_chinese_ll);
//        LinearLayout mathLl = (LinearLayout) view.findViewById(R.id.found_course_popwin_math_ll);
//        LinearLayout englishLl = (LinearLayout) view.findViewById(R.id.found_course_popwin_english_ll);
//
//        // 学科点击事件
//        disciplineLl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                courseImg.setImageDrawable(getResources().getDrawable(R.mipmap.discipline_small));
//                courseTv.setText("学科");
//                courseTv.setTextColor(getResources().getColor(R.color.color_course_green));
//                popupWindow.dismiss();
//            }
//        });
//        // 语文点击事件
//        chineseLl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                courseImg.setImageDrawable(getResources().getDrawable(R.mipmap.chinses_small));
//                courseTv.setText("语文");
//                courseTv.setTextColor(getResources().getColor(R.color.color_course_red));
//                popupWindow.dismiss();
//            }
//        });
//        // 数学点击事件
//        mathLl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                courseImg.setImageDrawable(getResources().getDrawable(R.mipmap.math_small));
//                courseTv.setText("数学");
//                courseTv.setTextColor(getResources().getColor(R.color.color_course_blue));
//                popupWindow.dismiss();
//            }
//        });
//        // 英语点击事件
//        englishLl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                courseImg.setImageDrawable(getResources().getDrawable(R.mipmap.english_small));
//                courseTv.setText("英语");
//                courseTv.setTextColor(getResources().getColor(R.color.color_course_yellow));
//                popupWindow.dismiss();
//            }
//        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
