package com.qmx163.data.bean.Mebean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */

public class Feedbackes {
    /**
     * code : 200
     * data : [{"id":"a1d7dec762be11e7905400163e323696","memberId":"d887b387621811e7905400163e323696","memberName":"张三","title":"闪退","content":"闪退","phone":"","email":"","status":0,"addTime":"2017-07-07 10:47:35","searchKey":null},{"id":"933359db679411e7905400163e323696","memberId":"d887b387621811e7905400163e323696","memberName":"张三","title":"闪退","content":"闪退","phone":"15021682336","email":"","status":0,"addTime":"2017-07-13 14:29:08","searchKey":null}]
     * message : 成功
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : a1d7dec762be11e7905400163e323696
         * memberId : d887b387621811e7905400163e323696
         * memberName : 张三
         * title : 闪退
         * content : 闪退
         * phone :
         * email :
         * status : 0
         * addTime : 2017-07-07 10:47:35
         * searchKey : null
         */

        private String id;
        private String memberId;
        private String memberName;
        private String title;
        private String content;
        private String phone;
        private String email;
        private int status;
        private String addTime;
        private Object searchKey;

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

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public Object getSearchKey() {
            return searchKey;
        }

        public void setSearchKey(Object searchKey) {
            this.searchKey = searchKey;
        }
    }
}
