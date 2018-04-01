package com.qmx163.data.bean.Mebean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户
 * Created by lzp on 2016/11/24.
 */
public class User implements Parcelable {
    String id;
    String img;
    String name;
    String userId;
    String memberId;
    private int questionCount;
    private String loginId;
    private double studyDurationTime;
    private int sex;
    private int studyLessonCount;
    private String memberName;
    private int type;
    private int score;
    private Object webinarId;
    private int lessonPeriodsStatus;
    private int status;
    private Object lessonPeriodsId;
    public User(){}

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public User(String id, String img, String name, String userId,String memberId) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.userId = userId;
        this.memberId = memberId;
    }

    protected User(Parcel in) {
        id = in.readString();
        img = in.readString();
        name = in.readString();
        userId = in.readString();
        memberId = in.readString();

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public double getStudyDurationTime() {
        return studyDurationTime;
    }

    public void setStudyDurationTime(double studyDurationTime) {
        this.studyDurationTime = studyDurationTime;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStudyLessonCount() {
        return studyLessonCount;
    }

    public void setStudyLessonCount(int studyLessonCount) {
        this.studyLessonCount = studyLessonCount;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Object getWebinarId() {
        return webinarId;
    }

    public void setWebinarId(Object webinarId) {
        this.webinarId = webinarId;
    }

    public int getLessonPeriodsStatus() {
        return lessonPeriodsStatus;
    }

    public void setLessonPeriodsStatus(int lessonPeriodsStatus) {
        this.lessonPeriodsStatus = lessonPeriodsStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getLessonPeriodsId() {
        return lessonPeriodsId;
    }

    public void setLessonPeriodsId(Object lessonPeriodsId) {
        this.lessonPeriodsId = lessonPeriodsId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(img);
        dest.writeString(name);
        dest.writeString(userId);
        dest.writeString(memberId);

    }
}
