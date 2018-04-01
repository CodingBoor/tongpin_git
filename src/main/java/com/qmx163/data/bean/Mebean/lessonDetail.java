package com.qmx163.data.bean.Mebean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */

public class lessonDetail implements Parcelable {

    /**
     * code : 200
     * data : {"id":"86a4f86056fb11e7905400163e323696","name":"高二化学","type":1,"beginTime":"2017-06-26 09:00","endTime":"2017-07-26 18:00","gradeId":"d5fa9113558d11e7905400163e323696","gradeName":"高二","teacherId":"4994595656fb11e7905400163e323696","teacherName":"李四","teacherImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/d275e04cf95d45d2a6f567eb413b4458.png","teacherTitle":"高级","teacherOrganization":"复旦大学附属中学","subjectId":"06af54c8559511e7905400163e323696","subjectName":"化学","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/a239487aff5b49cb96267b69ceca7509.png","content":"<p>高二化学-有机化学<\/p><p><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/05747a49503f4f3b84800a0bc8ee2188.png\" style=\"max-width:100%;\"><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/cd9f4b44a6ae41da9e90963f801c42c6.png\" style=\"line-height: 20px; max-width: 100%;\"><img src=\"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/b3b4eb431ee84cf1b7751e68223a72f6.png\" style=\"line-height: 20px; max-width: 100%;\"><br><\/p>","introVideoImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/0cb59c4d9abf4cd086be35edd76cdd3a.png","introVideo":"","sort":null,"status":1,"top":0,"newest":0,"addTime":"2017-06-22 11:33:11","updateTime":null,"searchKey":null,"concern":0,"lessonPeriodses":[{"id":"d79f6c4b5b2d11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","catalog":"第二讲","name":"无机物研究","beginTime":"2017-06-30 14:00","durationTime":60,"videoUrl":"","coursewareName":null,"coursewareUrl":"","sort":null,"status":0,"amount":0,"addTime":"2017-06-27 19:43:24","updateTime":"2017-06-28 10:19:19","webinarId":327081473,"searchKey":null,"lesson":null},{"id":"840334c85b2c11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","catalog":"第一讲","name":"无机物应用研究","beginTime":"2017-06-30 10:00","durationTime":60,"videoUrl":"","coursewareName":"第一讲无机物应用研究.pdf","coursewareUrl":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201707/b3e4f262ca7b4523b7e010c6742de023.pdf","sort":null,"status":0,"amount":0,"addTime":"2017-06-27 19:33:54","updateTime":"2017-07-05 15:09:09","webinarId":345763205,"searchKey":null,"lesson":null}],"lessonQuestions":[],"lessonCoursewares":[{"lessonPeriodsId":"d79f6c4b5b2d11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","coursewareName":null,"coursewareUrl":""},{"lessonPeriodsId":"840334c85b2c11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","coursewareName":"第一讲无机物应用研究.pdf","coursewareUrl":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201707/b3e4f262ca7b4523b7e010c6742de023.pdf"}]}
     * message : 成功
     */

    private String code;
    private DataBean data;
    private String message;

    protected lessonDetail(Parcel in) {
        code = in.readString();
        data = in.readParcelable(DataBean.class.getClassLoader());
        message = in.readString();
    }

    public static final Creator<lessonDetail> CREATOR = new Creator<lessonDetail>() {
        @Override
        public lessonDetail createFromParcel(Parcel in) {
            return new lessonDetail(in);
        }

        @Override
        public lessonDetail[] newArray(int size) {
            return new lessonDetail[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeParcelable(data, flags);
        dest.writeString(message);
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 86a4f86056fb11e7905400163e323696
         * name : 高二化学
         * type : 1
         * beginTime : 2017-06-26 09:00
         * endTime : 2017-07-26 18:00
         * gradeId : d5fa9113558d11e7905400163e323696
         * gradeName : 高二
         * teacherId : 4994595656fb11e7905400163e323696
         * teacherName : 李四
         * teacherImg : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/d275e04cf95d45d2a6f567eb413b4458.png
         * teacherTitle : 高级
         * teacherOrganization : 复旦大学附属中学
         * subjectId : 06af54c8559511e7905400163e323696
         * subjectName : 化学
         * img : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/a239487aff5b49cb96267b69ceca7509.png
         * content : <p>高二化学-有机化学</p><p><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/05747a49503f4f3b84800a0bc8ee2188.png" style="max-width:100%;"><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/cd9f4b44a6ae41da9e90963f801c42c6.png" style="line-height: 20px; max-width: 100%;"><img src="http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/b3b4eb431ee84cf1b7751e68223a72f6.png" style="line-height: 20px; max-width: 100%;"><br></p>
         * introVideoImg : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201706/0cb59c4d9abf4cd086be35edd76cdd3a.png
         * introVideo :
         * sort : null
         * status : 1
         * top : 0
         * newest : 0
         * addTime : 2017-06-22 11:33:11
         * updateTime : null
         * searchKey : null
         * concern : 0
         * lessonPeriodses : [{"id":"d79f6c4b5b2d11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","catalog":"第二讲","name":"无机物研究","beginTime":"2017-06-30 14:00","durationTime":60,"videoUrl":"","coursewareName":null,"coursewareUrl":"","sort":null,"status":0,"amount":0,"addTime":"2017-06-27 19:43:24","updateTime":"2017-06-28 10:19:19","webinarId":327081473,"searchKey":null,"lesson":null},{"id":"840334c85b2c11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","catalog":"第一讲","name":"无机物应用研究","beginTime":"2017-06-30 10:00","durationTime":60,"videoUrl":"","coursewareName":"第一讲无机物应用研究.pdf","coursewareUrl":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201707/b3e4f262ca7b4523b7e010c6742de023.pdf","sort":null,"status":0,"amount":0,"addTime":"2017-06-27 19:33:54","updateTime":"2017-07-05 15:09:09","webinarId":345763205,"searchKey":null,"lesson":null}]
         * lessonQuestions : []
         * lessonCoursewares : [{"lessonPeriodsId":"d79f6c4b5b2d11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","coursewareName":null,"coursewareUrl":""},{"lessonPeriodsId":"840334c85b2c11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","coursewareName":"第一讲无机物应用研究.pdf","coursewareUrl":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201707/b3e4f262ca7b4523b7e010c6742de023.pdf"}]
         */

        private String id;
        private String name;
        private int type;
        private String beginTime;
        private String endTime;
        private String gradeId;
        private String gradeName;
        private String teacherId;
        private String teacherName;
        private String teacherImg;
        private String teacherTitle;
        private String teacherOrganization;
        private String subjectId;
        private String subjectName;
        private String img;
        private String content;
        private String introVideoImg;
        private String introVideo;
        private Object sort;
        private int status;
        private int top;
        private int newest;
        private String addTime;
        private Object updateTime;
        private Object searchKey;
        private int concern;
        private List<LessonPeriodsesBean> lessonPeriodses;
        private List<?> lessonQuestions;
        private List<LessonCoursewaresBean> lessonCoursewares;

        protected DataBean(Parcel in) {
            id = in.readString();
            name = in.readString();
            type = in.readInt();
            beginTime = in.readString();
            endTime = in.readString();
            gradeId = in.readString();
            gradeName = in.readString();
            teacherId = in.readString();
            teacherName = in.readString();
            teacherImg = in.readString();
            teacherTitle = in.readString();
            teacherOrganization = in.readString();
            subjectId = in.readString();
            subjectName = in.readString();
            img = in.readString();
            content = in.readString();
            introVideoImg = in.readString();
            introVideo = in.readString();
            status = in.readInt();
            top = in.readInt();
            newest = in.readInt();
            addTime = in.readString();
            concern = in.readInt();
            lessonPeriodses = in.createTypedArrayList(LessonPeriodsesBean.CREATOR);
            lessonCoursewares = in.createTypedArrayList(LessonCoursewaresBean.CREATOR);
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getGradeId() {
            return gradeId;
        }

        public void setGradeId(String gradeId) {
            this.gradeId = gradeId;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
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

        public String getTeacherImg() {
            return teacherImg;
        }

        public void setTeacherImg(String teacherImg) {
            this.teacherImg = teacherImg;
        }

        public String getTeacherTitle() {
            return teacherTitle;
        }

        public void setTeacherTitle(String teacherTitle) {
            this.teacherTitle = teacherTitle;
        }

        public String getTeacherOrganization() {
            return teacherOrganization;
        }

        public void setTeacherOrganization(String teacherOrganization) {
            this.teacherOrganization = teacherOrganization;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getNewest() {
            return newest;
        }

        public void setNewest(int newest) {
            this.newest = newest;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getSearchKey() {
            return searchKey;
        }

        public void setSearchKey(Object searchKey) {
            this.searchKey = searchKey;
        }

        public int getConcern() {
            return concern;
        }

        public void setConcern(int concern) {
            this.concern = concern;
        }

        public List<LessonPeriodsesBean> getLessonPeriodses() {
            return lessonPeriodses;
        }

        public void setLessonPeriodses(List<LessonPeriodsesBean> lessonPeriodses) {
            this.lessonPeriodses = lessonPeriodses;
        }

        public List<?> getLessonQuestions() {
            return lessonQuestions;
        }

        public void setLessonQuestions(List<?> lessonQuestions) {
            this.lessonQuestions = lessonQuestions;
        }

        public List<LessonCoursewaresBean> getLessonCoursewares() {
            return lessonCoursewares;
        }

        public void setLessonCoursewares(List<LessonCoursewaresBean> lessonCoursewares) {
            this.lessonCoursewares = lessonCoursewares;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(name);
            dest.writeInt(type);
            dest.writeString(beginTime);
            dest.writeString(endTime);
            dest.writeString(gradeId);
            dest.writeString(gradeName);
            dest.writeString(teacherId);
            dest.writeString(teacherName);
            dest.writeString(teacherImg);
            dest.writeString(teacherTitle);
            dest.writeString(teacherOrganization);
            dest.writeString(subjectId);
            dest.writeString(subjectName);
            dest.writeString(img);
            dest.writeString(content);
            dest.writeString(introVideoImg);
            dest.writeString(introVideo);
            dest.writeInt(status);
            dest.writeInt(top);
            dest.writeInt(newest);
            dest.writeString(addTime);
            dest.writeInt(concern);
            dest.writeTypedList(lessonPeriodses);
            dest.writeTypedList(lessonCoursewares);
        }

        public static class LessonPeriodsesBean implements Parcelable {
            /**
             * id : d79f6c4b5b2d11e7905400163e323696
             * lessonId : 86a4f86056fb11e7905400163e323696
             * catalog : 第二讲
             * name : 无机物研究
             * beginTime : 2017-06-30 14:00
             * durationTime : 60
             * videoUrl :
             * coursewareName : null
             * coursewareUrl :
             * sort : null
             * status : 0
             * amount : 0
             * addTime : 2017-06-27 19:43:24
             * updateTime : 2017-06-28 10:19:19
             * webinarId : 327081473
             * searchKey : null
             * lesson : null
             */

            private String id;
            private String lessonId;
            private String catalog;
            private String name;
            private String beginTime;
            private int durationTime;
            private String videoUrl;
            private Object coursewareName;
            private String coursewareUrl;
            private Object sort;
            private int status;
            private int amount;
            private String addTime;
            private String updateTime;
            private int webinarId;
            private Object searchKey;
            private Object lesson;

            protected LessonPeriodsesBean(Parcel in) {
                id = in.readString();
                lessonId = in.readString();
                catalog = in.readString();
                name = in.readString();
                beginTime = in.readString();
                durationTime = in.readInt();
                videoUrl = in.readString();
                coursewareUrl = in.readString();
                status = in.readInt();
                amount = in.readInt();
                addTime = in.readString();
                updateTime = in.readString();
                webinarId = in.readInt();
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeString(lessonId);
                dest.writeString(catalog);
                dest.writeString(name);
                dest.writeString(beginTime);
                dest.writeInt(durationTime);
                dest.writeString(videoUrl);
                dest.writeString(coursewareUrl);
                dest.writeInt(status);
                dest.writeInt(amount);
                dest.writeString(addTime);
                dest.writeString(updateTime);
                dest.writeInt(webinarId);
            }

            @Override
            public int describeContents() {
                return 0;
            }

            public static final Creator<LessonPeriodsesBean> CREATOR = new Creator<LessonPeriodsesBean>() {
                @Override
                public LessonPeriodsesBean createFromParcel(Parcel in) {
                    return new LessonPeriodsesBean(in);
                }

                @Override
                public LessonPeriodsesBean[] newArray(int size) {
                    return new LessonPeriodsesBean[size];
                }
            };

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLessonId() {
                return lessonId;
            }

            public void setLessonId(String lessonId) {
                this.lessonId = lessonId;
            }

            public String getCatalog() {
                return catalog;
            }

            public void setCatalog(String catalog) {
                this.catalog = catalog;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public int getDurationTime() {
                return durationTime;
            }

            public void setDurationTime(int durationTime) {
                this.durationTime = durationTime;
            }

            public String getVideoUrl() {
                return videoUrl;
            }

            public void setVideoUrl(String videoUrl) {
                this.videoUrl = videoUrl;
            }

            public Object getCoursewareName() {
                return coursewareName;
            }

            public void setCoursewareName(Object coursewareName) {
                this.coursewareName = coursewareName;
            }

            public String getCoursewareUrl() {
                return coursewareUrl;
            }

            public void setCoursewareUrl(String coursewareUrl) {
                this.coursewareUrl = coursewareUrl;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
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

            public String getAddTime() {
                return addTime;
            }

            public void setAddTime(String addTime) {
                this.addTime = addTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getWebinarId() {
                return webinarId;
            }

            public void setWebinarId(int webinarId) {
                this.webinarId = webinarId;
            }

            public Object getSearchKey() {
                return searchKey;
            }

            public void setSearchKey(Object searchKey) {
                this.searchKey = searchKey;
            }

            public Object getLesson() {
                return lesson;
            }

            public void setLesson(Object lesson) {
                this.lesson = lesson;
            }
        }

        public static class LessonCoursewaresBean implements Parcelable {
            /**
             * lessonPeriodsId : d79f6c4b5b2d11e7905400163e323696
             * lessonId : 86a4f86056fb11e7905400163e323696
             * coursewareName : null
             * coursewareUrl :
             */

            private String lessonPeriodsId;
            private String lessonId;
            private Object coursewareName;
            private String coursewareUrl;

            protected LessonCoursewaresBean(Parcel in) {
                lessonPeriodsId = in.readString();
                lessonId = in.readString();
                coursewareUrl = in.readString();
            }

            public static final Creator<LessonCoursewaresBean> CREATOR = new Creator<LessonCoursewaresBean>() {
                @Override
                public LessonCoursewaresBean createFromParcel(Parcel in) {
                    return new LessonCoursewaresBean(in);
                }

                @Override
                public LessonCoursewaresBean[] newArray(int size) {
                    return new LessonCoursewaresBean[size];
                }
            };

            public String getLessonPeriodsId() {
                return lessonPeriodsId;
            }

            public void setLessonPeriodsId(String lessonPeriodsId) {
                this.lessonPeriodsId = lessonPeriodsId;
            }

            public String getLessonId() {
                return lessonId;
            }

            public void setLessonId(String lessonId) {
                this.lessonId = lessonId;
            }

            public Object getCoursewareName() {
                return coursewareName;
            }

            public void setCoursewareName(Object coursewareName) {
                this.coursewareName = coursewareName;
            }

            public String getCoursewareUrl() {
                return coursewareUrl;
            }

            public void setCoursewareUrl(String coursewareUrl) {
                this.coursewareUrl = coursewareUrl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(lessonPeriodsId);
                dest.writeString(lessonId);
                dest.writeString(coursewareUrl);
            }
        }
    }
}
