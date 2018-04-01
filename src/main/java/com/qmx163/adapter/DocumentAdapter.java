package com.qmx163.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmx163.R;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;

import java.util.List;

/**
 * 系列课程
 * Created by Administrator on 2017/7/26.
 */

public class DocumentAdapter extends android.widget.BaseAdapter {
    Context contexts;
    List<lessonPeriodsDetail.DataBean.LessonBean.LessonPeriodsesBean> chatData;

    public DocumentAdapter(Context context, List datas) {
        this.chatData=datas;
        this.contexts=context;
    }

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
        ViewHolder viewHolder;
        lessonPeriodsDetail.DataBean.LessonBean.LessonPeriodsesBean data = chatData.get(position);
        if (convertView == null) {
            convertView = View.inflate(contexts, R.layout.docu_frag, null);
            viewHolder = new ViewHolder();
            viewHolder.PerName = (TextView) convertView.findViewById(R.id.PerName);
            viewHolder.prestatus = (TextView) convertView.findViewById(R.id.prestatus);
            viewHolder.preTimer = (TextView) convertView.findViewById(R.id.preTimer);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.PerName.setText(data.getCatalog() + "");
        int statusId = data.getStatus();
        if (statusId == 1)
            viewHolder.prestatus.setBackgroundResource(R.mipmap.zhibo);
        else if (statusId == 2)
            viewHolder.prestatus.setBackgroundResource(R.mipmap.yugao);
        else if (statusId == 5)
            viewHolder.prestatus.setBackgroundResource(R.mipmap.huikan);
        if(TextUtils.isEmpty(data.getUpdateTime()))
                 viewHolder.preTimer.setText("");
            else
                viewHolder.preTimer.setText(data.getUpdateTime() + "");
        return convertView;
    }
    class ViewHolder {
        TextView PerName;
        TextView prestatus;
        TextView preTimer;
    }
}

