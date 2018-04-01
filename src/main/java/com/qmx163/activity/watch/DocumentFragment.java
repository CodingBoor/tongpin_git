package com.qmx163.activity.watch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.qmx163.R;
import com.qmx163.adapter.DocumentAdapter;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.util.ToastUtil;
import com.qmx163.view.NoScrollListview;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 系列课程
 */
public class DocumentFragment extends Fragment {


    lessonPeriodsDetail.DataBean lessonDetas;
    List<lessonPeriodsDetail.DataBean.LessonBean.LessonPeriodsesBean> chatData = new ArrayList<>();
    @BindView(R.id.keListView)
    NoScrollListview keListView;

    public static DocumentFragment newInstance(lessonPeriodsDetail.DataBean lessonDeta) {
        DocumentFragment articleFragment = new DocumentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lessonDeta", lessonDeta);
        articleFragment.setArguments(bundle);
        return articleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this, view);
        lessonDetas = getArguments().getParcelable("lessonDeta");
        chatData = lessonDetas.getLesson().getLessonPeriodses();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DocumentAdapter chatAdapter = new DocumentAdapter(getActivity(),chatData);
        keListView.setAdapter(chatAdapter);
        keListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.toastShortShow(getActivity(),position+"被点击");
            }
        });
//        iv_doc = (PPTView) getView().findViewById(R.id.iv_doc);
//        board = (WhiteBoardView) getView().findViewById(R.id.board);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
