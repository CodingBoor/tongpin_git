package com.qmx163.data.bean.Mebean;

/**
 * Created by DEV-81 on 2017/9/28.
 */

public class JPushMessageDetail {

    /**
     * code : 200
     * data : {"userImg":null,"releaseTime":"2017-09-27 14:42:22","addTime":"2017-09-27 12:01:55","answerTime":null,"lesson":null,"lessonId":"bcef6b4f9dab11e78cee00163e06d055","memberName":"点点点","memberImg":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Android/201709/fb9025d0-3a65-4cfb-a765-13756132d84a.jpg","updateTime":"2017-09-27 14:42:23","searchKey":null,"userName":null,"userId":null,"content":"凝聚力","questionSameCount":2,"lessonPeriods":null,"answerContent":null,"lessonQuestionSames":null,"member":null,"id":"9a38cc64a33811e7949000163e06d055","user":null,"lessonPeriodsId":"1e27d0259dac11e78cee00163e06d055","memberId":"a1cfe5db9c3711e78cee00163e06d055","status":9}
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
         * userImg : null
         * releaseTime : 2017-09-27 14:42:22
         * addTime : 2017-09-27 12:01:55
         * answerTime : null
         * lesson : null
         * lessonId : bcef6b4f9dab11e78cee00163e06d055
         * memberName : 点点点
         * memberImg : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Android/201709/fb9025d0-3a65-4cfb-a765-13756132d84a.jpg
         * updateTime : 2017-09-27 14:42:23
         * searchKey : null
         * userName : null
         * userId : null
         * content : 凝聚力
         * questionSameCount : 2
         * lessonPeriods : null
         * answerContent : null
         * lessonQuestionSames : null
         * member : null
         * id : 9a38cc64a33811e7949000163e06d055
         * user : null
         * lessonPeriodsId : 1e27d0259dac11e78cee00163e06d055
         * memberId : a1cfe5db9c3711e78cee00163e06d055
         * status : 9
         */

        private String userImg;
        private String releaseTime;
        private String addTime;
        private String answerTime;
        private String lesson;
        private String lessonId;
        private String memberName;
        private String memberImg;
        private String updateTime;
        private String searchKey;
        private String userName;
        private String userId;
        private String content;
        private int questionSameCount;
        private String lessonPeriods;
        private String answerContent;
        private String lessonQuestionSames;
        private String member;
        private String id;
        private String user;
        private String lessonPeriodsId;
        private String memberId;
        private int status;
        private String questionScore;

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getAnswerTime() {
            return answerTime;
        }

        public void setAnswerTime(String answerTime) {
            this.answerTime = answerTime;
        }

        public String getLesson() {
            return lesson;
        }

        public void setLesson(String lesson) {
            this.lesson = lesson;
        }

        public String getLessonId() {
            return lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getMemberImg() {
            return memberImg;
        }

        public void setMemberImg(String memberImg) {
            this.memberImg = memberImg;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getSearchKey() {
            return searchKey;
        }

        public void setSearchKey(String searchKey) {
            this.searchKey = searchKey;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getQuestionSameCount() {
            return questionSameCount;
        }

        public void setQuestionSameCount(int questionSameCount) {
            this.questionSameCount = questionSameCount;
        }

        public String getQuestionScore() {
            return questionScore;
        }

        public void setQuestionScore(String questionScore) {
            this.questionScore = questionScore;
        }

        public String getLessonPeriods() {
            return lessonPeriods;
        }

        public void setLessonPeriods(String lessonPeriods) {
            this.lessonPeriods = lessonPeriods;
        }

        public String getAnswerContent() {
            return answerContent;
        }

        public void setAnswerContent(String answerContent) {
            this.answerContent = answerContent;
        }

        public String getLessonQuestionSames() {
            return lessonQuestionSames;
        }

        public void setLessonQuestionSames(String lessonQuestionSames) {
            this.lessonQuestionSames = lessonQuestionSames;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getLessonPeriodsId() {
            return lessonPeriodsId;
        }

        public void setLessonPeriodsId(String lessonPeriodsId) {
            this.lessonPeriodsId = lessonPeriodsId;
        }

        public String getMemberId() {
            return memberId;
        }

        public void setMemberId(String memberId) {
            this.memberId = memberId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
