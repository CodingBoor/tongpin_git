package com.qmx163.data.bean.Mebean;

/**
 * Created by Administrator on 2017/9/25.
 */

public class JpushMessage {


    /**
     * code : 200
     * data : {"id":"b1a69730a4fc11e7949000163e06d055","memberId":"a3b6cccd737911e78d8900163e06d055","bizId":"b1a1e7d7a4fc11e7949000163e06d055","title":"22","content":"22","img":null,"type":0,"status":0,"issueTime":"2017-09-29","addTime":"2017-09-29 17:58:07","searchKey":null}
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
         * id : b1a69730a4fc11e7949000163e06d055
         * memberId : a3b6cccd737911e78d8900163e06d055
         * bizId : b1a1e7d7a4fc11e7949000163e06d055
         * title : 22
         * content : 22
         * img : null
         * type : 0
         * status : 0
         * issueTime : 2017-09-29
         * addTime : 2017-09-29 17:58:07
         * searchKey : null
         */

        private String id;
        private String memberId;
        private String bizId;
        private String title;
        private String content;
        private Object img;
        private int type;
        private int status;
        private String issueTime;
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

        public String getBizId() {
            return bizId;
        }

        public void setBizId(String bizId) {
            this.bizId = bizId;
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

        public Object getImg() {
            return img;
        }

        public void setImg(Object img) {
            this.img = img;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIssueTime() {
            return issueTime;
        }

        public void setIssueTime(String issueTime) {
            this.issueTime = issueTime;
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
