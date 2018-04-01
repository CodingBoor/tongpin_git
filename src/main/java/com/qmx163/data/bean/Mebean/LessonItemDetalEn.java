package com.qmx163.data.bean.Mebean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/9/8.
 */

public class LessonItemDetalEn implements Parcelable{
    /**
     * lessonId : 2b25f2ce566d11e7905400163e323696
     * catalog : null
     * name : null
     * beginTime : null
     * durationTime : null
     * videoUrl : null
     * coursewareName : null
     * coursewareUrl : null
     * status : null
     * amount : null
     * webinarId : null
     * lessonName : 高三解析几何
     * teacherId : 4193aef355ac11e7905400163e323696
     * teacherName : 张三
     * teacherImg : null
     * subjectId : f2ede8b2559411e7905400163e323696
     * subjectName : 数学
     * introVideoImg : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/9e656eb69edb40ffa82644e4af73c37b.png
     * introVideo :
     * concern : 1
     * concernAmount : 1
     * id : null
     */

    private String lessonId;
    private Object catalog;
    private Object name;
    private Object beginTime;
    private int durationTime;
    private Object videoUrl;
    private Object coursewareName;
    private Object coursewareUrl;
    private int status;
    private int amount;
    private int playbackAmount;
    private Object webinarId;
    private String lessonName;
    private String teacherId;
    private String teacherName;
    private Object teacherImg;
    private String subjectId;
    private String subjectName;
    private String introVideoImg;
    private String introVideo;
    private int concern;
    private int concernAmount;
    private String id;

    protected LessonItemDetalEn(Parcel in) {
        lessonId = in.readString();
        durationTime = in.readInt();
        status = in.readInt();
        amount = in.readInt();
        playbackAmount = in.readInt();
        lessonName = in.readString();
        teacherId = in.readString();
        teacherName = in.readString();
        subjectId = in.readString();
        subjectName = in.readString();
        introVideoImg = in.readString();
        introVideo = in.readString();
        concern = in.readInt();
        concernAmount = in.readInt();
        id = in.readString();
    }

    public static final Creator<LessonItemDetalEn> CREATOR = new Creator<LessonItemDetalEn>() {
        @Override
        public LessonItemDetalEn createFromParcel(Parcel in) {
            return new LessonItemDetalEn(in);
        }

        @Override
        public LessonItemDetalEn[] newArray(int size) {
            return new LessonItemDetalEn[size];
        }
    };

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public Object getCatalog() {
        return catalog;
    }

    public void setCatalog(Object catalog) {
        this.catalog = catalog;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Object beginTime) {
        this.beginTime = beginTime;
    }

    public int getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(int durationTime) {
        this.durationTime = durationTime;
    }

    public Object getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(Object videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Object getCoursewareName() {
        return coursewareName;
    }

    public void setCoursewareName(Object coursewareName) {
        this.coursewareName = coursewareName;
    }

    public Object getCoursewareUrl() {
        return coursewareUrl;
    }

    public void setCoursewareUrl(Object coursewareUrl) {
        this.coursewareUrl = coursewareUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPlaybackAmount() {
        return playbackAmount;
    }

    public void setPlaybackAmount(int playbackAmount) {
        this.playbackAmount = playbackAmount;
    }

    public Object getWebinarId() {
        return webinarId;
    }

    public void setWebinarId(Object webinarId) {
        this.webinarId = webinarId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Object getTeacherImg() {
        return teacherImg;
    }

    public void setTeacherImg(Object teacherImg) {
        this.teacherImg = teacherImg;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getIntroVideoImg() {
        return introVideoImg;
    }

    public void setIntroVideoImg(String introVideoImg) {
        this.introVideoImg = introVideoImg;
    }

    public String getIntroVideo() {
        return introVideo;
    }

    public void setIntroVideo(String introVideo) {
        this.introVideo = introVideo;
    }

    public int getConcern() {
        return concern;
    }

    public void setConcern(int concern) {
        this.concern = concern;
    }

    public int getConcernAmount() {
        return concernAmount;
    }

    public void setConcernAmount(int concernAmount) {
        this.concernAmount = concernAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lessonId);
        dest.writeInt(durationTime);
        dest.writeInt(status);
        dest.writeInt(amount);
        dest.writeInt(playbackAmount);
        dest.writeString(lessonName);
        dest.writeString(teacherId);
        dest.writeString(teacherName);
        dest.writeString(subjectId);
        dest.writeString(subjectName);
        dest.writeString(introVideoImg);
        dest.writeString(introVideo);
        dest.writeInt(concern);
        dest.writeInt(concernAmount);
        dest.writeString(id);
    }
}
