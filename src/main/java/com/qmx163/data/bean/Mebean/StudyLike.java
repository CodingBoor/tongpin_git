package com.qmx163.data.bean.Mebean;

/**
 * Created by Administrator on 2017/8/29.
 */

public class StudyLike {

    /**
     * id : 7774b2498c8911e78d8900163e06d055
     * pId : 39e73fd0894111e78d8900163e06d055
     * memberId : a72ee26977e911e78d8900163e06d055
     * addTime : 2017-08-29 15:12:50
     * member : {"id":"a72ee26977e911e78d8900163e06d055","loginId":"15813337260","memberName":"张三","sex":1,"status":0,"type":0,"vip":0,"birthday":"1990-01-01","img":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Android/201708/a26ceda9-53bb-4d39-9603-c3ffd342d62e.jpg","occupation":"","hobby":"","signature":"","addTime":"2017-08-03 09:18:27","province":"北京市","city":"北京市市辖区","district":"房山区","street":"","longitude":"","latitude":"","medal":null,"auth":null,"score":298,"studyLessonCount":0,"studyLessonPeriodsCount":0,"studyDurationTime":0,"weihouId":19991194,"distance":null,"level":null,"levelImg":null,"token":null}
     * memberName : 张三
     * memberImg : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Android/201708/a26ceda9-53bb-4d39-9603-c3ffd342d62e.jpg
     */

    private String id;
    private String pId;
    private String memberId;
    private String addTime;
    private MemberBean member;
    private String memberName;
    private String memberImg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public MemberBean getMember() {
        return member;
    }

    public void setMember(MemberBean member) {
        this.member = member;
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

    public static class MemberBean {
        /**
         * id : a72ee26977e911e78d8900163e06d055
         * loginId : 15813337260
         * memberName : 张三
         * sex : 1
         * status : 0
         * type : 0
         * vip : 0
         * birthday : 1990-01-01
         * img : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Android/201708/a26ceda9-53bb-4d39-9603-c3ffd342d62e.jpg
         * occupation :
         * hobby :
         * signature :
         * addTime : 2017-08-03 09:18:27
         * province : 北京市
         * city : 北京市市辖区
         * district : 房山区
         * street :
         * longitude :
         * latitude :
         * medal : null
         * auth : null
         * score : 298
         * studyLessonCount : 0
         * studyLessonPeriodsCount : 0
         * studyDurationTime : 0
         * weihouId : 19991194
         * distance : null
         * level : null
         * levelImg : null
         * token : null
         */

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
        private int score;
        private int studyLessonCount;
        private int studyLessonPeriodsCount;
        private int studyDurationTime;
        private int weihouId;
        private Object distance;
        private Object level;
        private Object levelImg;
        private Object token;

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

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
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

        public int getWeihouId() {
            return weihouId;
        }

        public void setWeihouId(int weihouId) {
            this.weihouId = weihouId;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
            this.level = level;
        }

        public Object getLevelImg() {
            return levelImg;
        }

        public void setLevelImg(Object levelImg) {
            this.levelImg = levelImg;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }
    }
}
