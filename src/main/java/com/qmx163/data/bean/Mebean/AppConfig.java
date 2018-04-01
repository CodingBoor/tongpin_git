package com.qmx163.data.bean.Mebean;

/**
 * Created by DEV-81 on 2017/9/28.
 */

public class AppConfig {

    /**
     * code : 200
     * data : {"id":"3915c0ba8c8211e78d8900163e06d055","guideType":1,"guidePage":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/182c90574780414b9a7aefc5404b7dfb.png","personalBackground":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/5b95ae74887a4a1c8992eef6210fa05f.png","appVersion":"1.3.3","appDownload":"http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/42e50cd6dae041d6827d1569dffe404b.mp4"}
     * message : 成功
     */

    private String code;
    private Config data;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Config getData() {
        return data;
    }

    public void setData(Config data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Config {
        /**
         * id : 3915c0ba8c8211e78d8900163e06d055
         * guideType : 1
         * guidePage : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/182c90574780414b9a7aefc5404b7dfb.png
         * personalBackground : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/5b95ae74887a4a1c8992eef6210fa05f.png
         * appVersion : 1.3.3
         * appDownload : http://bucket-tplh.oss-cn-beijing.aliyuncs.com/Java/201709/42e50cd6dae041d6827d1569dffe404b.mp4
         */

        private String id;
        private int guideType;
        private String guidePage;
        private String personalBackground;
        private String appVersion;
        private String appDownload;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getGuideType() {
            return guideType;
        }

        public void setGuideType(int guideType) {
            this.guideType = guideType;
        }

        public String getGuidePage() {
            return guidePage;
        }

        public void setGuidePage(String guidePage) {
            this.guidePage = guidePage;
        }

        public String getPersonalBackground() {
            return personalBackground;
        }

        public void setPersonalBackground(String personalBackground) {
            this.personalBackground = personalBackground;
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
    }
}
