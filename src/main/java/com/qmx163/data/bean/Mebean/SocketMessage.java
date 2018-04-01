package com.qmx163.data.bean.Mebean;

/**
 * Created by Administrator on 2017/9/8.
 */

public class SocketMessage {

    /**
     * code : MEMBER_ONLINE_TRACK
     * message :
     * data : null
     */

    private String code;
    private String bizId ;
    private String bizType ;
    private String message;
    private Object data;

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
