package com.qmx163.data.bean.Mebean;

/**
 * Created by Administrator on 2017/7/11.
 */

public class MemberDetail {

    /**
     * code : 200
     * data : {"id":"60a1853e5bf811e7905400163e323696","loginId":"15021682336","memberName":"张三1","password":null,"sex":0,"status":0,"type":0,"vip":0,"birthday":"1990-01-01","img":"","occupation":"","hobby":"","signature":"","addTime":"2017-06-28 19:53:18","updateTime":null,"province":"","city":"","district":"","street":"","longitude":"","latitude":"","medal":null,"auth":null,"score":null,"weihouId":19366409,"confirmPassword":null,"distance":null,"searchKey":null}
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
         * id : 60a1853e5bf811e7905400163e323696
         * loginId : 15021682336
         * memberName : 张三1
         * password : null
         * sex : 0
         * status : 0
         * type : 0
         * vip : 0
         * birthday : 1990-01-01
         * img :
         * occupation :
         * hobby :
         * signature :
         * addTime : 2017-06-28 19:53:18
         * updateTime : null
         * province :
         * city :
         * district :
         * street :
         * longitude :
         * latitude :
         * medal : null
         * auth : null
         * score : null
         * weihouId : 19366409
         * confirmPassword : null
         * distance : null
         * searchKey : null
         */

        private String id;
        private String loginId;
        private String memberName;
        private Object password;
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
        private Object updateTime;
        private String province;
        private String city;
        private String district;
        private String street;
        private String longitude;
        private String latitude;
        private Object medal;
        private Object auth;
        private Object score;
        private int weihouId;
        private Object confirmPassword;
        private Object distance;
        private Object searchKey;

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

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
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

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
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

        public Object getScore() {
            return score;
        }

        public void setScore(Object score) {
            this.score = score;
        }

        public int getWeihouId() {
            return weihouId;
        }

        public void setWeihouId(int weihouId) {
            this.weihouId = weihouId;
        }

        public Object getConfirmPassword() {
            return confirmPassword;
        }

        public void setConfirmPassword(Object confirmPassword) {
            this.confirmPassword = confirmPassword;
        }

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }

        public Object getSearchKey() {
            return searchKey;
        }

        public void setSearchKey(Object searchKey) {
            this.searchKey = searchKey;
        }
    }
}
