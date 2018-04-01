package com.qmx163.activity.watch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 课程课件
 * &#x8be6;&#x60c5;&#x9875;&#x7684;Fragment
 */
public class DetailFragment extends Fragment implements WatchContract.DetailView {
    @BindView(R.id.keListView)
    ListView keListView;
    lessonPeriodsDetail.DataBean lessonDetas;
    List<lessonPeriodsDetail.DataBean.LessonBean.LessonCoursewaresBean> chatData = new ArrayList<>();
    //.LessonBean.LessonCoursewaresBean
    public static DetailFragment newInstance(lessonPeriodsDetail.DataBean lessonDeta) {
        DetailFragment articleFragment = new DetailFragment();
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
        chatData=lessonDetas.getLesson().getLessonCoursewares();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ChatAdapter chatAdapter=new ChatAdapter();
        keListView.setAdapter(chatAdapter);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setPresenter(com.qmx163.activity.watch.BasePresenter presenter) {

    }


    class ChatAdapter extends BaseAdapter {


        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getCount() {
            return chatData.size();
        }

        @Override
        public Object getItem(int position) {
            return chatData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            DetailFragment.ViewHolder viewHolder;
            lessonPeriodsDetail.DataBean.LessonBean.LessonCoursewaresBean data = chatData.get(position);
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.detail_frag, null);
                viewHolder = new ViewHolder();
                viewHolder.pdfName = (TextView) convertView.findViewById(R.id.pdf_name);
                viewHolder.readPdf = (TextView) convertView.findViewById(R.id.read_pdf);
                viewHolder.sendPdf = (TextView) convertView.findViewById(R.id.send_pdf);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (DetailFragment.ViewHolder) convertView.getTag();
            }
            String cName= (String) data.getCoursewareName();
            if(TextUtils.isEmpty(cName))
                 viewHolder.pdfName.setText("");
                else
                 viewHolder.pdfName.setText(data.getCoursewareName()+"");

            //打开pdf
            viewHolder.readPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.toastShortShow(getActivity(),"打开pdf");
                }
            });

            //发送邮件
            viewHolder.sendPdf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.toastShortShow(getActivity(),"发送pdf");
                }
            });
            return convertView;
        }
    }
    static class ViewHolder {
        TextView pdfName;
        TextView readPdf;
        TextView sendPdf;
    }


}
