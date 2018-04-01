package com.qmx163.data.bean.Mebean;

/**
 * Created by Administrator on 2017/9/8.
 */

public class SocketData {
    /**
     * memberId : 2e38f69754ba11e7905400163e323696
     * lessonId : 86a4f86056fb11e7905400163e323696
     * lessonPeriodsId : d79f6c4b5b2d11e7905400163e323696
     */

    private String memberId;
    private String lessonId;
    private String lessonPeriodsId;
    private String webinarId;

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
}
