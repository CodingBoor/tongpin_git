package com.qmx163.data.bean.Mebean;

/**
 * Created by Administrator on 2017/9/27.
 */

public class GetRewardRules {

    /**
     * code : 200
     * data : {"Rule_01":"每日签到奖励5","Rule_02":"关注课程奖励10，每日最多奖励10次","Rule_03":"关注老师奖励10，每日最多奖励10次","Rule_04":"直播提问奖励6，每次直播最多奖励10次","Rule_05":"直播提问被发布奖励15，每次直播最多奖励10次","Rule_06":"直播提问被回复奖励2，每次直播最多奖励10次","Rule_07":"转发奖励10，每日最多奖励10次"}
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
         * Rule_01 : 每日签到奖励5
         * Rule_02 : 关注课程奖励10，每日最多奖励10次
         * Rule_03 : 关注老师奖励10，每日最多奖励10次
         * Rule_04 : 直播提问奖励6，每次直播最多奖励10次
         * Rule_05 : 直播提问被发布奖励15，每次直播最多奖励10次
         * Rule_06 : 直播提问被回复奖励2，每次直播最多奖励10次
         * Rule_07 : 转发奖励10，每日最多奖励10次
         */

        private String Rule_01;
        private String Rule_02;
        private String Rule_03;
        private String Rule_04;
        private String Rule_05;
        private String Rule_06;
        private String Rule_07;

        public String getRule_01() {
            return Rule_01;
        }

        public void setRule_01(String Rule_01) {
            this.Rule_01 = Rule_01;
        }

        public String getRule_02() {
            return Rule_02;
        }

        public void setRule_02(String Rule_02) {
            this.Rule_02 = Rule_02;
        }

        public String getRule_03() {
            return Rule_03;
        }

        public void setRule_03(String Rule_03) {
            this.Rule_03 = Rule_03;
        }

        public String getRule_04() {
            return Rule_04;
        }

        public void setRule_04(String Rule_04) {
            this.Rule_04 = Rule_04;
        }

        public String getRule_05() {
            return Rule_05;
        }

        public void setRule_05(String Rule_05) {
            this.Rule_05 = Rule_05;
        }

        public String getRule_06() {
            return Rule_06;
        }

        public void setRule_06(String Rule_06) {
            this.Rule_06 = Rule_06;
        }

        public String getRule_07() {
            return Rule_07;
        }

        public void setRule_07(String Rule_07) {
            this.Rule_07 = Rule_07;
        }
    }
}
