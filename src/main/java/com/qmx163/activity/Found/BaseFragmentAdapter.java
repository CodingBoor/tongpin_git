package com.qmx163.activity.Found;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.qmx163.R;
import com.qmx163.base.BaseFm;
import com.qmx163.data.bean.Mebean.DataFactory;
import com.qmx163.data.bean.Mebean.User;
import com.qmx163.util.manager.RecycleViewDelegate;
import com.qmx163.util.manager.VerticalDividerItemDecoration;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;


/**
 * 服务
 * Created by lzp on 2016/12/21.
 */
public class BaseFragmentAdapter extends BaseFm {
    ServiceDelegate delegate;
    XRefreshView xRefreshview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recyclerview_noswipe, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        xRefreshview= (XRefreshView) view.findViewById(R.id.xrefreshview);
        delegate=new ServiceDelegate(this);
        delegate.addDatas(makeUserData());
        delegate.getAdapter().notifyDataSetChanged();
        initXrefreshView();
    }

    private void initXrefreshView() {
        //设置刷新完成以后，headerview固定的时间
        xRefreshview.setPinnedTime(800);
        // 设置是否可以下拉刷新
        xRefreshview.setPullRefreshEnable(false);
        // 设置是否可以上拉加载
        xRefreshview.setPullLoadEnable(false);
        xRefreshview.setMoveForHorizontal(false);
        xRefreshview.setHideFooterWhenComplete(false);
        xRefreshview.setAutoRefresh(false);
//        xRefreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
//            @Override
//            public void onRefresh() {
//                super.onRefresh();
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
//
//            }
//        });
    }


    private class ServiceDelegate extends RecycleViewDelegate {

        public ServiceDelegate(BaseFm fragment) {
            super(fragment);
        }

        @Override
        protected RecyclerView.ItemDecoration getItemDecoration(int... res) {
            return new VerticalDividerItemDecoration.Builder(context).size(0).build();
        }

        @Override
        protected LinearLayoutManager getLayoutManager() {
            return new GridLayoutManager(context,1);
        }

        @Override
        public com.qmx163.adapter.BaseAdapter initAdapter() {
            return new com.qmx163.adapter.BaseAdapter<User>(context, R.layout.adapter_service_fragment) {
                @Override
                protected void convert(ViewHolder holder, User user, int position) {
                    holder.setText(R.id.position, position + "");
                    holder.setText(R.id.title, user.getName());
                    ImageView img= holder.getView(R.id.img);
                    loadImage(img, DataFactory.avatar);
                }
            };
        }
    }


    public static ArrayList<User> makeUserData() {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            User user = new User();
            user.setUserId(i + "");
            user.setImg("http://www.pp3.cn/uploads/20120306aa/110626162203-77.jpg");
            switch (i) {
                case 1:
                    user.setName("一个像秋天，一个像夏天");
                    break;
                case 2:
                    user.setName("啊速度与");
                    break;
                case 3:
                    user.setName("反馈感觉");
                    break;
                case 4:
                    user.setName("发达国家看来");
                    break;
                case 5:
                    user.setName("一来");
                    break;
                default:
                    user.setName("地方");
            }

            users.add(user);
        }
        return users;
    }

}
