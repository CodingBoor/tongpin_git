package com.qmx163.net;


import com.qmx163.base.BaseBean;
import com.qmx163.data.bean.Mebean.AccompaniesEn;
import com.qmx163.data.bean.Mebean.AddQuestionEn;
import com.qmx163.data.bean.Mebean.AppConfig;
import com.qmx163.data.bean.Mebean.ConcernLessons;
import com.qmx163.data.bean.Mebean.Feedbackes;
import com.qmx163.data.bean.Mebean.FxTeacher;
import com.qmx163.data.bean.Mebean.GetBannersEn;
import com.qmx163.data.bean.Mebean.GetMessageEn;
import com.qmx163.data.bean.Mebean.GetRewardRules;
import com.qmx163.data.bean.Mebean.GiftsAndGreetingsEn;
import com.qmx163.data.bean.Mebean.JPushMessageDetail;
import com.qmx163.data.bean.Mebean.JpushMessage;
import com.qmx163.data.bean.Mebean.MessageSize;
import com.qmx163.data.bean.Mebean.RegisteredEn;
import com.qmx163.data.bean.Mebean.Scores;
import com.qmx163.data.bean.Mebean.SocketMessage;
import com.qmx163.data.bean.Mebean.StudyCollection;
import com.qmx163.data.bean.Mebean.StudyCollectionEn;
import com.qmx163.data.bean.Mebean.StudySearchEn;
import com.qmx163.data.bean.Mebean.StudyTaskDetailEn;
import com.qmx163.data.bean.Mebean.TeachDetailEn;
import com.qmx163.data.bean.Mebean.UploadFile;
import com.qmx163.data.bean.Mebean.lessonDetail;
import com.qmx163.data.bean.Mebean.lessonPeriodsDetail;
import com.qmx163.data.bean.Mebean.subjects;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Created by Administrator on 2017/6/15.
 */

public interface WanToApi {


    //    @Headers({"Content-type:application/json;charset=UTF-8","Accept: application/json"})
    //获取验证码
    @FormUrlEncoded
    @POST("getSmsCode")
///
    Observable<BaseBean<Object>> GetIdentifyCode(@Field("loginId") String loginId,
                                                 @Field("type") int type);

    //注册
    @FormUrlEncoded
    @POST("register")
//
    Observable<RegisteredEn> Register(@Field("loginId") String loginId,
                                      @Field("password") String password,
                                      @Field("memberName") String memberName,@Field("type") String type, @Field("img") String img);

    //登录
    @FormUrlEncoded
    @POST("login")
//
    Observable<RegisteredEn> Login(@Field("loginId") String loginId,
                                   @Field("password") String password);

    //修改密码
    @FormUrlEncoded
    @POST("updatePwd")
    Observable<BaseBean<Object>> updatePwd(@Field("loginId") String loginId,
                                          @Field("oldPassword") String oldPassword,
                                          @Field("newPassword") String newPassword);


//重置密码
@FormUrlEncoded
@POST("resetPwd")
    Observable<BaseBean<Object>> resetPwd(@Field("loginId") String loginId,
                                           @Field("newPassword") String newPassword);


    //获取用户签到信息
    @FormUrlEncoded
    @POST("getSign")
//
    Observable<RegisteredEn> getSign(@Field("memberId") String loginId);


    //用户签到
    @FormUrlEncoded
    @POST("addSign")
    Observable<RegisteredEn> addSign(@Field("memberId") String loginId);


    //用户意见反馈清单
    @FormUrlEncoded
    @POST("feedbackes")
    Observable<Feedbackes> feedBackes(@Field("memberId") String loginId);

    //发送意见反馈
    @FormUrlEncoded
    @POST("addFeedback")
    Observable<BaseBean<Object>> AddfeedBackes(@Field("memberId") String loginId, @Field("title") String title, @Field("content") String content, @Field("phone") String phone, @Field("email") String email);

    //删除意见反馈
    @FormUrlEncoded
    @POST("deleteFeedback")
    Observable<BaseBean<Object>> DeletefeedBackes(@Field("id") String loginId);


    //获取陪伴学习清单
    @FormUrlEncoded
    @POST("accompanies")
    Observable<AccompaniesEn> Accompanies(@Field("parentId") String perentId, @Field("pageNum") String pageNum);

    //添加陪伴学习
    @FormUrlEncoded
    @POST("addAccompany")
    Observable<SocketMessage> addAccompany(@Field("parentId") String perentId, @Field("childId") String childId, @Field("relation") String relation);

    //删除陪伴学习
    @FormUrlEncoded
    @POST("deleteAccompany")
    Observable<SocketMessage> DeleteAccompany(@Field("id") String perentId,@Field("memberId") String memberId);


    //查询个人资料
    @FormUrlEncoded
    @POST("memberDetail")
    Observable<RegisteredEn> SelectPer(@Field("id") String loginId);

    //保存个人资料
    @FormUrlEncoded
    @POST("updateMember")
    Observable<RegisteredEn> SavePer(@Field("id") String id, @Field("memberName") String memberName, @Field("sex") String sex, @Field("birthday") String birthday, @Field("img") String img);

    //积分清单 (V能量)
    @FormUrlEncoded
    @POST("getScores")
    Observable<Scores> getScores(@Field("pageNum") String id, @Field("memberId") String memberName);

    //课程关注列表
    @FormUrlEncoded
    @POST("myConcernLessons")
    Observable<ConcernLessons> CurriculumConcerns(@Field("memberId") String memberId, @Field("pageNum") int pageNum);

    //课程关注列表详情
    @FormUrlEncoded
    @POST("lessonDetail")
    Observable<lessonDetail> lessonDetails(@Field("memberId") String memberId, @Field("id") String id);

    //课时详情
    @FormUrlEncoded
    @POST("lessonPeriodsDetail")
    Observable<lessonPeriodsDetail> lessonPeriodsDetail(@Field("memberId") String memberId, @Field("id") String id);

    //课程发现列表详情
    @FormUrlEncoded
    @POST("searchLessonPeriodsBySubject")
    Observable<ConcernLessons> searchLessonPeriodsBySubject(@Field("subjectId") String id, @Field("memberId") String memberId, @Field("type") String type, @Field("pageNum") int pageNum);

    //课程发现列表详情
    @FormUrlEncoded
    @POST("searchLessons")
    Observable<ConcernLessons> searchLessons(@Field("memberId") String memberId, @Field("searchKey") String searchKey);

    //发现-查询教师
    @FormUrlEncoded
    @POST("searchTeacher")
    Observable<FxTeacher> searchTeacher(@Field("memberId") String memberId, @Field("subjectId") String subjectId, @Field("pageNum") int pageNum, @Field("searchKey") String searchKey);

    //关注-查询教师
    @FormUrlEncoded
    @POST("myConcernTeachers")
    Observable<FxTeacher> ConcernTeachers(@Field("memberId") String memberId, @Field("pageNum") int pageNum);

    /**
     * 课时提问详情(接口不完整,实体类不对)
     *
     * @param memberId
     * @return
     */
    @FormUrlEncoded
    @POST("lessonQuestionDetail")
    Observable<FxTeacher> lessonQuestionDetail(@Field("id") String memberId);


    /**
     * 课时提问
     *
     * @param memberId
     * @param lessonId
     * @param lessonPeriodsId
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("addLessonQuestion")
    Observable<AddQuestionEn> addLessonQuestion(@Field("memberId") String memberId, @Field("lessonId") String lessonId, @Field("lessonPeriodsId") String lessonPeriodsId, @Field("content") String content);


    //获取科目
    @POST("subjects")
    Observable<subjects> subjects(@QueryMap Map<String, String> params);

    //获取消息列表
    @FormUrlEncoded
    @POST("getMessages")
    Observable<GetMessageEn> getMessage(@Field("memberId") String memberId, @Field("pageNum") int pageNum);

    //获取科目
    @POST("giftsAndGreetings")
    Observable<GiftsAndGreetingsEn> giftsAndGreetings(@QueryMap Map<String, String> params);

    /**
     * 课程关注与取消
     *
     * @param memberId
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("lessonConcern")
    Observable<GetMessageEn> lessonConcern(@Field("memberId") String memberId, @Field("id") String id);


    /**
     * 教师关注与取消
     *
     * @param memberId
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("teacherConcern")
    Observable<GetMessageEn> teacherConcern(@Field("memberId") String memberId, @Field("id") String id);

    /**
     * 更新消息已读
     *
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("updateMessage")
    Observable<GetMessageEn> updateMessage(@Field("id") String id);


    /**
     * 教师详情
     *
     * @param memberId
     * @param id
     * @param pageNum
     * @return
     */
    @FormUrlEncoded
    @POST("teacherDetail")
    Observable<TeachDetailEn> teacherDetail(@Field("memberId") String memberId, @Field("id") String id, @Field("pageNum") int pageNum);


    /**
     * 获取广告轮播
     * @param type 分类：31发现 32社区 33商铺 34服务 35馋嘴喵
     * @param top  置顶：0否 1是（可为空）
     * @param searchKey 检索词（根据名称或描述模糊检索，可为空）
     * @param pageNum 分页
     * @return
     */
    @FormUrlEncoded
    @POST("getBanners")
    Observable<GetBannersEn> getBanners(@Field("type") String type,@Field("top") String top, @Field("searchKey") String searchKey, @Field("pageNum") int pageNum);


    /**
     * token登录
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST("tokenLogin")
    Observable<RegisteredEn> tokenLogin(@Field("token") String token);

    /**
     * 查询学习任务
     * @param type 分类：1 往日 0 ，当前
     * @param memberId 用户id
     * @param searchKey 检索词（根据名称或描述模糊检索，可为空）
     * @param pageNum 分页
     * @return
     */
    @FormUrlEncoded
    @POST("searchStudyTasks")
    Observable<StudySearchEn> searchStudyTasks(@Field("type") String type,@Field("memberId") String memberId, @Field("searchKey") String searchKey, @Field("pageNum") int pageNum);


    /**
     * 学习任务点赞
     * @param studyTaskId 任务id
     * @param memberId 会员id
     * @return
     */
    @FormUrlEncoded
    @POST("addStudyTaskLike")
    Observable<RegisteredEn> addStudyTaskLike(@Field("studyTaskId") String studyTaskId, @Field("memberId") String memberId);

    /**
     * 学习任务评论点赞
     * @param studyTaskCommentId 评论id
     * @param memberId 会员id
     * @return
     */
    @FormUrlEncoded
    @POST("addStudyTaskCommentLike")
    Observable<RegisteredEn> addStudyTaskCommentLike(@Field("studyTaskCommentId") String studyTaskCommentId, @Field("memberId") String memberId);

    /**
     * 学习任务详情
     * @param id 任务id
     * @param memberId 用户id
     * @param pageNum 分页
     * @return
     */
    @FormUrlEncoded
    @POST("studyTaskDetail")
    Observable<StudyTaskDetailEn> studyTaskDetail(@Field("id") String id,@Field("memberId") String memberId, @Field("pageNum") int pageNum);

/**
     * 学习任务评论
     * @param memberId 用户id
     * @param studyTaskId 任务id
     * @param content ；评论内容
     * @return
     */
    @FormUrlEncoded
    @POST("addStudyTaskComment")
    Observable<RegisteredEn> addStudyTaskComment(@Field("studyTaskId") String studyTaskId, @Field("memberId") String memberId, @Field("content") String content);


    /**
     * 学习任务复评
     * @param memberId 用户id
     * @param parentCommentId 父评论id
     * @param parentMemberId 父评论人id
     * @param content ；评论内容
     * @return
     */
    @FormUrlEncoded
    @POST("addStudyTaskRecomment")
    Observable<RegisteredEn> addStudyTaskRecomment(@Field("memberId") String memberId,@Field("parentMemberId") String parentMemberId,@Field("parentCommentId") String parentCommentId, @Field("content") String content);


 /**
     * 获取我的收藏
     * @param memberId 用户id
     * @param type 业务类表 0，学习任务 ， 1 提问
     * @param pageNum 翻页
     * @return
     */
    @FormUrlEncoded
    @POST("getMyCollect")
    Observable<StudyCollectionEn> getMyCollect(@Field("memberId") String memberId, @Field("type") String type, @Field("pageNum") int pageNum);

 /**
     * 添加收藏
     * @param memberId 用户id
     * @param type 业务类表 0，学习任务 ， 1 提问
     * @param bizId 收藏id
     * @return
     */
    @FormUrlEncoded
    @POST("addCollect")
    Observable<StudyCollection> addCollect(@Field("memberId") String memberId, @Field("type") String type, @Field("bizId") String bizId);

/**
     * 取消收藏
     * @param id 收藏id
     * @return
     */
    @FormUrlEncoded
    @POST("delCollect")
    Observable<RegisteredEn> delCollect(@Field("id") String id);
/**
     * 陪伴学习添加鼓励
     * @param childId 小孩id
     * @param parentId 监护人id
     * @return
     */
    @FormUrlEncoded
    @POST("addEncourage")
    Observable<SocketMessage> addEncourage(@Field("parentId") String parentId,@Field("childId") String childId);


/**
     * 课时提问评分
     * @param questionId 问题id
     * @param memberId 用户id
     * @param score  评分 0明白 1一般 2不懂
     * @return
     */
    @FormUrlEncoded
    @POST("addLessonQuestionScore")
    Observable<SocketMessage> addLessonQuestionScore(@Field("questionId") String questionId,@Field("memberId") String memberId,@Field("score") String score);
/**
     * 同问
     * @param questionId 问题id
     * @param memberId 用户id
     * @return
     */
    @FormUrlEncoded
    @POST("addLessonQuestionSame")
    Observable<SocketMessage> addLessonQuestionSame(@Field("questionId") String questionId, @Field("memberId") String memberId);

/**
     * 获取未读消息总数
     * @param memberId 用户id
     * @return
     */
    @FormUrlEncoded
    @POST("getNotReadMessageCount")
    Observable<MessageSize> getNotReadMessageCount(@Field("memberId") String memberId);

/**
     * app属性
     * @return
     */
    //获取科目
    @POST("getAppConfig")
    Observable<RegisteredEn> getAppConfig(@QueryMap Map<String, String> params);

/**
     * 积分奖励规则清单
     * @return
     */
    @POST("getRewardRules")
    Observable<GetRewardRules> getRewardRules(@QueryMap Map<String, String> params);



/**
     * 消息详情
     * @param id 推送id
     * @return
     */
    @FormUrlEncoded
    @POST("getMessageDetail")
    Observable<JpushMessage> getMessageDetail(@Field("id") String id);


    /**
     * 极光推送消息详情
     * @param bizId 推送id
     * @param bizType 0 ，2，3都走这个接口
     * @return
     */
    @FormUrlEncoded
    @POST("getJPushMessageDetail")
    Observable<JPushMessageDetail> getJPushMessageDetail(@Field("bizId") String bizId,@Field("bizType") String bizType,@Field("memberId") String memberId);


    /**
     * 送礼物
     * @param memberId 会员id
     * @param giftId 礼物id
     * @param num 礼物数量
     * @return
     */
    @FormUrlEncoded
    @POST("sendGiftUseScore")
    Observable<SocketMessage> sendGiftUseScore(@Field("memberId") String memberId, @Field("giftId") String giftId, @Field("num") String num);

    /**
     * 检查版本
     * @return
     */
    @POST("getAppConfig")
    Observable<AppConfig> getAppConfig();

    @Streaming
    @GET
    Call<ResponseBody> download(@Url String fileUrl);

    /**
     * 当消息类型为2 的时候
     * @return
     */
    @POST("getJPushMessageDetail")
    Observable<JPushMessageDetail> getJPushMessageDetail(@QueryMap Map<String, String> params);


//    @POST("/file")
//    @Multipart
//    Observable<UploadFile> uploadFile(@Part("file\"; filename=\"avatar.png\"") RequestBody file);


    @POST("/file")
    @Multipart
    Observable<UploadFile> uploadFile(@Part("file\"; filename=\"avatar.png\"") RequestBody file,@Part("nickName") RequestBody nickName);
}
