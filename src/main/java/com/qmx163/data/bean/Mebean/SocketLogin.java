package com.qmx163.data.bean.Mebean;

/**
 * socket 发消息实体类
 * Created by likai on 2017/8/31.
 */

public class SocketLogin {


    /**
     * code : LOGIN
     * message : 登录
     * data : {"memberId":"2e38f69754ba11e7905400163e323696","lessonId":"86a4f86056fb11e7905400163e323696","lessonPeriodsId":"d79f6c4b5b2d11e7905400163e323696"}
     */

    private String code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * memberId : 2e38f69754ba11e7905400163e323696
         * lessonId : 86a4f86056fb11e7905400163e323696
         * lessonPeriodsId : d79f6c4b5b2d11e7905400163e323696
         */

        private String memberId;
        private String lessonId;
        private String lessonPeriodsId;
        private String webinarId;
        private String id;

        public String getWebinarId() {
            return webinarId;
        }

        public void setWebinarId(String webinarId) {
            this.webinarId = webinarId;
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
