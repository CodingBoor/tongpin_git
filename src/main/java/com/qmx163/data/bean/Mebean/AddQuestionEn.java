package com.qmx163.data.bean.Mebean;

/**
 * 课时提问model
 * Created by Administrator on 2017/8/14.
 */

public class AddQuestionEn {

    /**
     * code : 200
     * data : {"id":"138638ab80d711e78d8900163e06d055","memberId":"a72ee26977e911e78d8900163e06d055","lessonId":"86a4f86056fb11e7905400163e323696","lessonPeriodsId":"e65f38a2737711e78d8900163e06d055","content":"测试提问","userId":null,"answerContent":null,"answerTime":null,"status":null,"releaseTime":null,"addTime":"2017-08-14 17:58:09","updateTime":null,"searchKey":null,"user":null,"member":null,"lesson":null,"lessonPeriods":null,"questionSameCount":null,"lessonQuestionSames":null}
     * message : 成功
     */

    private String code;
    private DataBean data;
    private String message;

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

    public static class DataBean {
        /**
         * id : 138638ab80d711e78d8900163e06d055
         * memberId : a72ee26977e911e78d8900163e06d055
         * lessonId : 86a4f86056fb11e7905400163e323696
         * lessonPeriodsId : e65f38a2737711e78d8900163e06d055
         * content : 测试提问
         * userId : null
         * answerContent : null
         * answerTime : null
         * status : null
         * releaseTime : null
         * addTime : 2017-08-14 17:58:09
         * updateTime : null
         * searchKey : null
         * user : null
         * member : null
         * lesson : null
         * lessonPeriods : null
         * questionSameCount : null
         * lessonQuestionSames : null
         */

        private String id;
        private String memberId;
        private String lessonId;
        private String lessonPeriodsId;
        private String content;
        private Object userId;
        private Object answerContent;
        private Object answerTime;
        private Object status;
        private Object releaseTime;
        private String addTime;
        private Object updateTime;
        private Object searchKey;
        private Object user;
        private Object member;
        private Object lesson;
        private Object lessonPeriods;
        private Object questionSameCount;
        private Object lessonQuestionSames;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public String getLessonId() {
            return lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
        }

        public String getLessonPeriodsId() {
            return lessonPeriodsId;
        }

        public void setLessonPeriodsId(String lessonPeriodsId) {
            this.lessonPeriodsId = lessonPeriodsId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getUserId() {
            return userId;
        }

        public void setUserId(Object userId) {
            this.userId = userId;
        }

        public Object getAnswerContent() {
            return answerContent;
        }

        public void setAnswerContent(Object answerContent) {
            this.answerContent = answerContent;
        }

        public Object getAnswerTime() {
            return answerTime;
        }

        public void setAnswerTime(Object answerTime) {
            this.answerTime = answerTime;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(Object releaseTime) {
            this.releaseTime = releaseTime;
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

        public Object getUser() {
            return user;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public Object getMember() {
            return member;
        }

        public void setMember(Object member) {
            this.member = member;
        }

        public Object getLesson() {
            return lesson;
        }

        public void setLesson(Object lesson) {
            this.lesson = lesson;
        }

        public Object getLessonPeriods() {
            return lessonPeriods;
        }

        public void setLessonPeriods(Object lessonPeriods) {
            this.lessonPeriods = lessonPeriods;
        }

        public Object getQuestionSameCount() {
            return questionSameCount;
        }

        public void setQuestionSameCount(Object questionSameCount) {
            this.questionSameCount = questionSameCount;
        }

        public Object getLessonQuestionSames() {
            return lessonQuestionSames;
        }

        public void setLessonQuestionSames(Object lessonQuestionSames) {
            this.lessonQuestionSames = lessonQuestionSames;
        }
    }
}
