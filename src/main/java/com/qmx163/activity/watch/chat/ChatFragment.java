package com.qmx163.activity.watch.chat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qmx163.R;
import com.qmx163.adapter.RecyclerAdapter;
import com.qmx163.adapter.RecyclerViewHolder;
import com.qmx163.base.BaseFm;
import com.qmx163.config.Constants;
import com.qmx163.data.bean.Mebean.AddQuestionEn;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.net.ApiCallback;
import com.qmx163.util.PrefUtils;
import com.qmx163.view.NoScrollListview;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天页的Fragment(学生提问)
 */
public class ChatFragment extends BaseFm {

    public static final int CHAT_EVENT_CHAT = 1;
    public static final int CHAT_EVENT_QUESTION = 2;

    public static final int CHAT_NORMAL = 0x00;
    public static final int CHAT_SURVEY = 0x01;
    private ChatContract.ChatPresenter mPresenter;
    public final int RequestLogin = 0;
    NoScrollListview lv_chat;
    RecyclerView rvQuestion;
    EditText editText;
    private com.rey.material.widget.TextView sendQuestion;
    lessonPeriodsDetail.DataBean lessonDetas;
    List<lessonPeriodsDetail.DataBean.LessonBean.LessonQuestionsBean> chatData = new ArrayList<>();
    private RecyclerAdapter<lessonPeriodsDetail.DataBean.LessonBean.LessonQuestionsBean> questionAdapter;
    //    ChatAdapter chatAdapter = new ChatAdapter();
//    QuestionAdapter questionAdapterTwo = new QuestionAdapter();
    boolean isquestion = false;
    int status = -1;

    TextView text_chat_content;

    private Activity mActivity;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    public static ChatFragment newInstance(int status, lessonPeriodsDetail.DataBean lessonDeta) {
        ChatFragment chatFragment = new ChatFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("lessonDeta", lessonDeta);
        chatFragment.setArguments(bundle);
        return chatFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.chat_fragment, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        lv_chat = (NoScrollListview) getView().findViewById(R.id.lv_chat);
        rvQuestion = (RecyclerView) getView().findViewById(R.id.rv_question);
        editText = (EditText) getView().findViewById(R.id.text_chat_content);
        sendQuestion = (com.rey.material.widget.TextView) getView().findViewById(R.id.ll_send_question);
//        getView().findViewById(R.id.text_chat_content).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPresenter.showChatView(false, null, 0);
//            }
//        });
//        btnFresh = (Button) getView().findViewById(R.id.btn_fresh);
//        btnFresh.setOnClickListener(this);

        lessonDetas = getArguments().getParcelable("lessonDeta");
        chatData = lessonDetas.getLesson().getLessonQuestions();

        questionAdapter = new RecyclerAdapter<lessonPeriodsDetail.DataBean.LessonBean.LessonQuestionsBean>(getActivity(), chatData, R.layout.chat_question_item) {
            @Override
            public void convert(RecyclerViewHolder helper, final lessonPeriodsDetail.DataBean.LessonBean.LessonQuestionsBean item, final int position) {
                Glide.with(getActivity()).load(item.getMemberImg()).into((ImageView) helper.getView(R.id.iv_head));
                final com.rey.material.widget.TextView tv_same_question = helper.getView(R.id.tv_same_question);
                LinearLayout ll_question = helper.getView(R.id.ll_question);
                helper.setText(R.id.tv_name, item.getMemberName());
                helper.setText(R.id.fabu, "发布: " + item.getAddTime());
                helper.setText(R.id.title, item.getContent());
                helper.setText(R.id.huifu, item.getUserName());
                helper.setText(R.id.huifuzhi, item.getAnswerContent());
                tv_same_question.setText("同问 " + item.getQuestionSameCount());

                com.rey.material.widget.RelativeLayout rlMingbai = helper.getView(R.id.rl_question_mingbai);
                com.rey.material.widget.RelativeLayout rlSoso = helper.getView(R.id.rl_question_soso);
                com.rey.material.widget.RelativeLayout rlInnocent = helper.getView(R.id.rl_question_innocent);


                //判断同问按钮是否点亮
                if (item.getQuestionSame() == 1) {
                    tv_same_question.setTextColor(Color.WHITE);
                    tv_same_question.setBackgroundResource(R.drawable.text_corner_orange);
                } else {
                    tv_same_question.setTextColor(getResources().getColor(R.color.hintbg));
                    tv_same_question.setBackgroundResource(R.drawable.shape_live_question_gray);
                }
                //判断是否显示评论按钮
                if (item.getUserName() == null) {
                    helper.getView(R.id.item_same).setVisibility(View.GONE);
                }else {
                    helper.getView(R.id.item_same).setVisibility(View.VISIBLE);
                }

                try {
                    //判断提问状态
                    if (item.getQuestionScore()!=null){

                    if (TextUtils.equals("0",item.getQuestionScore())){
                        rlInnocent.setVisibility(View.INVISIBLE);
                        rlSoso.setVisibility(View.INVISIBLE);
                        rlMingbai.setVisibility(View.VISIBLE);
                        rlMingbai.setEnabled(false);
                    }else if (TextUtils.equals("1",item.getQuestionScore())){
                        rlInnocent.setVisibility(View.INVISIBLE);
                        rlMingbai.setVisibility(View.INVISIBLE);
                        rlSoso.setVisibility(View.VISIBLE);
                        rlSoso.setEnabled(false);
                    }else if (TextUtils.equals("2",item.getQuestionScore())){
                        rlMingbai.setVisibility(View.INVISIBLE);
                        rlSoso.setVisibility(View.INVISIBLE);
                        rlInnocent.setVisibility(View.VISIBLE);
                        rlInnocent.setEnabled(false);
                    }else {
                        rlMingbai.setVisibility(View.VISIBLE);
                        rlSoso.setVisibility(View.VISIBLE);
                        rlInnocent.setVisibility(View.VISIBLE);

                    }
                    }else {
                        rlMingbai.setVisibility(View.VISIBLE);
                        rlSoso.setVisibility(View.VISIBLE);
                        rlInnocent.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                //同问
                tv_same_question.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.getQuestionSame() == 1) {
                            showToas("已提问");
                        } else {
                            addSubscription(apiStores.addLessonQuestionSame(item.getId(), PrefUtils.getString(getActivity(), Constants.USER_ID, "")), new ApiCallback<SocketMessage>() {
                                @Override
                                public void onSuccess(SocketMessage model) {
                                    item.setQuestionSame(1);
                                    item.setQuestionSameCount(item.getQuestionSameCount() + 1);
                                    tv_same_question.setTextColor(Color.WHITE);
                                    tv_same_question.setBackgroundResource(R.drawable.text_corner_orange);
                                    tv_same_question.setText("同问 " + item.getQuestionSameCount());
                                    showToas(model.getMessage());
                                }

                                @Override
                                public void onFailure(String code) {

                                }

                                @Override
                                public void onFinish() {

                                }
                            });

                        }
                    }
                });
                helper.getView(R.id.rl_question_mingbai).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addQuestionSame(item.getId(), "0",position);
                    }
                });
                helper.getView(R.id.rl_question_soso).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addQuestionSame(item.getId(), "1",position);
                    }
                });
                helper.getView(R.id.rl_question_innocent).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addQuestionSame(item.getId(), "2",position);
                    }
                });
            }
        };
        rvQuestion.setHasFixedSize(true);
        rvQuestion.setNestedScrollingEnabled(false);
        rvQuestion.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false));
        rvQuestion.setAdapter(this.questionAdapter);


        sendQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String context = String.valueOf(editText.getText());
                if (context.isEmpty()) {
                    showToas("输入不能为空");
                } else {
                    sendQuestion.setClickable(false);
                    String lessonId = lessonDetas.getLessonId();
                    String lessonPeriodsId = lessonDetas.getId();
                    addSubscription(apiStores.addLessonQuestion(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonId, lessonPeriodsId, context), new ApiCallback<AddQuestionEn>() {
                        @Override
                        public void onSuccess(AddQuestionEn model) {
                            sendQuestion.setClickable(true);
                            editText.setText("");
                            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
//                imm.showSoftInput(root,InputMethodManager.SHOW_FORCED);//强制显示软键盘
                            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0); //强制隐藏键盘
                            if ("200".equals(model.getCode())) {
                                showToas("发送成功");
                                getDate(lessonDetas.getId());//品论成功重新获取数据刷新页面
                            } else {
                                showToas(model.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(String code) {

                        }

                        @Override
                        public void onFinish() {
                            sendQuestion.setClickable(true);
                        }
                    });
                }
            }
        });
//        lv_chat.setAdapter(questionAdapterTwo);
        init();
    }

    //明白，一般， 不懂
    private void addQuestionSame(String id, final String score, final int position) {
        showProgressDialog();
        addSubscription(apiStores.addLessonQuestionScore(id, PrefUtils.getString(getActivity(), Constants.USER_ID, ""), score), new ApiCallback<SocketMessage>() {
            @Override
            public void onSuccess(SocketMessage model) {
//                                if (model.getData() == null){
//                                    Gson gson= new Gson();
//                                }
                chatData.get(position).setQuestionScore(score);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                questionAdapter.notifyDataSetChanged();

                    }
                },0);
                showToas(model.getMessage());
            }

            @Override
            public void onFailure(String code) {
                dismissProgressDialog();
            }

            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        });
    }


    /**
     * 课时关注详情
     */
    private lessonPeriodsDetail.DataBean lessonDeta;


    /**
     * 获取数据(由于获取提问详情的接口没有好, 这里直接调用获取课程接口)
     *
     * @param lessonId
     */
    private void getDate(String lessonId) {
        addSubscription(apiStores.lessonPeriodsDetail(PrefUtils.getString(getActivity(), Constants.USER_ID, ""), lessonId), new ApiCallback<lessonPeriodsDetail>() {
            @Override
            public void onSuccess(lessonPeriodsDetail model) {
                if ("200".equals(model.getCode())) {
                    lessonDeta = (lessonPeriodsDetail.DataBean) model.getData();
                    chatData.clear();
//                    chatData = lessonDeta.getLesson().getLessonQuestions();
                    chatData.addAll(lessonDeta.getLesson().getLessonQuestions());
//                    questionAdapterTwo.notifyDataSetChanged();
                    questionAdapter.notifyDataSetChanged();
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

    private void init() {
//        if (status == VhallUtil.WATCH_PLAYBACK) {
//            btnFresh.setVisibility(View.VISIBLE);
//        }
    }


//    @Override
//    public void notifyDataChanged(ChatServer.ChatInfo data) {
//
//    }
//
//    @Override
//    public void notifyDataChanged(List<ChatServer.ChatInfo> list) {
//
//    }
//
//    @Override
//    public void showToast(String content) {
//        if (this.isAdded())
//            Toast.makeText(getActivity(), content, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void clearChatData() {
//        if (chatData != null) {
//            chatData.clear();
//        }
//    }
//
//    @Override
//    public void performSend(String content, int chatEvent) {
//        switch (status) {
//            case VhallUtil.BROADCAST://直播界面只能发聊天
//                mPresenter.sendChat(content);
//                break;
//            case VhallUtil.WATCH_LIVE://观看直播界面发聊天和问答
//                if (chatEvent == ChatFragment.CHAT_EVENT_CHAT) {
//                    mPresenter.sendChat(content);
//                } else if (chatEvent == ChatFragment.CHAT_EVENT_QUESTION) {
//                    mPresenter.sendQuestion(content);
//                }
//                break;
//            case VhallUtil.WATCH_PLAYBACK://回放界面只能发评论(发评论必须保证登陆)
//                mPresenter.sendChat(content);
//                break;
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RequestLogin == requestCode) {
            if (resultCode == getActivity().RESULT_OK) {
                mPresenter.onLoginReturn();
            }
        }
    }

//    @Override
//    public void setPresenter(ChatContract.ChatPresenter presenter) {
//        mPresenter = presenter;
//    }

//    class ChatAdapter extends BaseAdapter {
//
//        @Override
//        public int getItemViewType(int position) {
//            if ("survey".equals(chatData.get(position).event)) {
//                return CHAT_SURVEY;
//            } else {
//                return CHAT_NORMAL;
//            }
//        }
//
//        @Override
//        public int getViewTypeCount() {
//            return 2;
//        }
//
//        @Override
//        public int getCount() {
//            return chatData.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return chatData.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            ViewHolder viewHolder;
//            ChatSurveyHolder surveyHolder;
//            final ChatServer.ChatInfo data = chatData.get(position);
//            switch (getItemViewType(position)) {
//                case CHAT_NORMAL:
//                    if (convertView == null) {
//                        convertView = View.inflate(getActivity(), R.layout.chat_item, null);
//                        viewHolder = new ViewHolder();
//                        viewHolder.iv_chat_avatar = (ImageView) convertView.findViewById(R.id.iv_chat_avatar);
//                        viewHolder.tv_chat_content = (TextView) convertView.findViewById(R.id.tv_chat_content);
//                        viewHolder.tv_chat_name = (TextView) convertView.findViewById(R.id.tv_chat_name);
//                        viewHolder.tv_chat_time = (TextView) convertView.findViewById(R.id.tv_chat_time);
//                        convertView.setTag(viewHolder);
//                    } else {
//                        viewHolder = (ViewHolder) convertView.getTag();
//                    }
//                    Glide.with(getActivity()).load(data.avatar).placeholder(R.drawable.icon_vhall).into(viewHolder.iv_chat_avatar);
//                    switch (data.event) {
//                        case ChatServer.eventMsgKey:
//                            viewHolder.tv_chat_content.setVisibility(View.VISIBLE);
//                            viewHolder.tv_chat_content.setText(EmojiUtils.getEmojiText(mActivity, data.msgData.text), TextView.BufferType.SPANNABLE);
//                            viewHolder.tv_chat_name.setText(data.user_name);
//                            break;
//                        case ChatServer.eventOnlineKey:
//                            viewHolder.tv_chat_name.setText(data.user_name + "上线了！");
//                            viewHolder.tv_chat_content.setVisibility(View.INVISIBLE);
//                            break;
//                        case ChatServer.eventOfflineKey:
//                            viewHolder.tv_chat_name.setText(data.user_name + "下线了！");
//                            viewHolder.tv_chat_content.setVisibility(View.INVISIBLE);
//                            break;
//                    }
//                    viewHolder.tv_chat_time.setText(data.time);
//                    break;
//                case CHAT_SURVEY:
//                    if (convertView == null) {
//                        convertView = View.inflate(getActivity(), R.layout.chat_item_survey, null);
//                        surveyHolder = new ChatSurveyHolder();
//                        surveyHolder.tv_join = (TextView) convertView.findViewById(R.id.tv_join);
//                        convertView.setTag(surveyHolder);
//                    } else {
//                        surveyHolder = (ChatSurveyHolder) convertView.getTag();
//                    }
//                    surveyHolder.tv_join.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mPresenter.showSurvey(data.id);
//                        }
//                    });
//                    break;
//            }
//            return convertView;
//        }
//    }

//    class QuestionAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return chatData.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return chatData.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            Holder viewHolder;
//            if (convertView == null) {
//                convertView = View.inflate(getActivity(), R.layout.chat_question_item, null);
//                viewHolder = new Holder();
//                viewHolder.iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
//                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
//                viewHolder.fabu = (TextView) convertView.findViewById(R.id.fabu);
//                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
//                viewHolder.huifu = (TextView) convertView.findViewById(R.id.huifu);
//                viewHolder.huifuzhi = (TextView) convertView.findViewById(R.id.huifuzhi);
//                viewHolder.item_same = (LinearLayout) convertView.findViewById(R.id.item_same);
//                viewHolder.tv_same_question = (TextView) convertView.findViewById(R.id.tv_same_question);
//                viewHolder.rl_question_innocent = (RelativeLayout) convertView.findViewById(R.id.rl_question_innocent);
//                viewHolder.rl_question_mingbai = (RelativeLayout) convertView.findViewById(R.id.rl_question_mingbai);
//                viewHolder.rl_question_soso = (RelativeLayout) convertView.findViewById(R.id.rl_question_soso);
//
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (Holder) convertView.getTag();
//            }
//            lessonPeriodsDetail.DataBean.LessonBean.LessonQuestionsBean data = chatData.get(position);
//            Glide.with(getActivity()).load(data.getMemberImg()).dontAnimate().into(viewHolder.iv_head);
//            if (data.getUserName() == null) {
//                viewHolder.item_same.setVisibility(View.GONE);
//            }
//
//            viewHolder.tv_name.setText(data.getMemberName());
//            viewHolder.fabu.setText("发布: " + data.getAddTime());
//            viewHolder.title.setText(data.getContent());
//            viewHolder.huifu.setText(data.getUserName());
//            viewHolder.huifuzhi.setText(data.getAnswerContent());
//
//            viewHolder.tv_same_question.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showToas("tongwen");
//                }
//            });
//            viewHolder.rl_question_mingbai.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showToas("mingbai");
//                }
//            });
//            viewHolder.rl_question_soso.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showToas("yiban");
//                }
//            });
//            viewHolder.rl_question_innocent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    showToas("budong");
//                }
//            });
//
//
//            return convertView;
//        }
//    }
//
//    static class Holder {
//        LinearLayout item_same;
//        ImageView iv_head;
//        TextView tv_name;
//        TextView fabu;
//        TextView title;
//        TextView huifu;
//        TextView huifuzhi;
//        TextView tv_same_question;
//        RelativeLayout rl_question_mingbai;
//        RelativeLayout rl_question_soso;
//        RelativeLayout rl_question_innocent;
//    }

}
