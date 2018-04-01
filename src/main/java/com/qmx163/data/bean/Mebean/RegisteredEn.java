package com.qmx163.data.bean.Mebean;

import java.io.Serializable;

/**
 * Created by CX4 on 2017/6/19.
 */

public class RegisteredEn implements Serializable {


    /**
     * code : 200
     * data : {"id":"420a276654b311e7905400163e323696","loginId":"13312341235","memberName":"张三","sex":0,"status":0,"type":0,"vip":0,"birthday":"1990-01-01","img":"","occupation":"","hobby":"","signature":"","addTime":"2017-06-19 13:50:54","province":"","city":"","district":"","street":"","longitude":"","latitude":"","medal":null,"auth":null}
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

    public static class DataBean implements Serializable {
        /**
         * id : 420a276654b311e7905400163e323696
         * loginId : 13312341235
         * memberName : 张三
         * sex : 0
         * status : 0
         * type : 0
         * vip : 0
         * birthday : 1990-01-01
         * img :
         * occupation :
         * hobby :
         * signature :
         * addTime : 2017-06-19 13:50:54
         * province :
         * city :
         * district :
         * street :
         * longitude :
         * latitude :
         * medal : null
         * auth : null
         */

        private int guideType;
        private String personalBackground;
        private String guidePage;
        private String appVersion;
        private String appDownload;


        private double studyTime;
        private int studyDay;
        private int readCount;
        private int encourageAmount;


        private int studyLessonCount;
        private int studyLessonPeriodsCount;
        private int studyDurationTime;
        private Object distance;
        private String level;
        private Object levelImg;

        private String id;
        private String loginId;
        private String memberName;
        private int sex;
        private int status;
        private int type;
        private int vip;
        private String birthday;
        private String img;
        private String occupation;
        private String hobby;
        private String signature;
        private String addTime;
        private String province;
        private String city;
        private String district;
        private String street;
        private String longitude;
        private String latitude;
        private Object medal;
        private Object auth;
        private String signHistory;
        private String signCount;
        private String lastModifyTime;
        private int score;
        private String content;
        private String weihouId;
        private String token;
        private String msg;
        private int likeCount;


        public int getGuideType() {
            return guideType;
        }

        public void setGuideType(int guideType) {
            this.guideType = guideType;
        }

        public String getPersonalBackground() {
            return personalBackground;
        }

        public void setPersonalBackground(String personalBackground) {
            this.personalBackground = personalBackground;
        }

        public String getGuidePage() {
            return guidePage;
        }

        public void setGuidePage(String guidePage) {
            this.guidePage = guidePage;
        }

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getAppDownload() {
            return appDownload;
        }

        public void setAppDownload(String appDownload) {
            this.appDownload = appDownload;
        }

        public double getStudyTime() {
            return studyTime;
        }

        public void setStudyTime(double studyTime) {
            this.studyTime = studyTime;
        }

        public int getStudyDay() {
            return studyDay;
        }

        public void setStudyDay(int studyDay) {
            this.studyDay = studyDay;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public int getEncourageAmount() {
            return encourageAmount;
        }

        public void setEncourageAmount(int encourageAmount) {
            this.encourageAmount = encourageAmount;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getStudyLessonCount() {
            return studyLessonCount;
        }

        public void setStudyLessonCount(int studyLessonCount) {
            this.studyLessonCount = studyLessonCount;
        }

        public int getStudyLessonPeriodsCount() {
            return studyLessonPeriodsCount;
        }

        public void setStudyLessonPeriodsCount(int studyLessonPeriodsCount) {
            this.studyLessonPeriodsCount = studyLessonPeriodsCount;
        }

        public int getStudyDurationTime() {
            return studyDurationTime;
        }

        public void setStudyDurationTime(int studyDurationTime) {
            this.studyDurationTime = studyDurationTime;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Object getLevelImg() {
            return levelImg;
        }

        public void setLevelImg(Object levelImg) {
            this.levelImg = levelImg;
        }

        public String getWeihouId() {
            return weihouId;
        }

        public void setWeihouId(String weihouId) {
            this.weihouId = weihouId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getOccupation() {
            return occupation;
        }

        public void setOccupation(String occupation) {
            this.occupation = occupation;
        }

        public String getHobby() {
            return hobby;
        }

        public void setHobby(String hobby) {
            this.hobby = hobby;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public Object getMedal() {
            return medal;
        }

        public void setMedal(Object medal) {
            this.medal = medal;
        }

        public Object getAuth() {
            return auth;
        }

        public void setAuth(Object auth) {
            this.auth = auth;
        }

        public String getSignHistory() {
            return signHistory;
        }

        public void setSignHistory(String signHistory) {
            this.signHistory = signHistory;
        }

        public String getSignCount() {
            return signCount;
        }

        public void setSignCount(String signCount) {
            this.signCount = signCount;
        }

        public String getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(String lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
