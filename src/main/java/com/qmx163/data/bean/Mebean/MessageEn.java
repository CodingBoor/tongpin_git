package com.qmx163.data.bean.Mebean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/3.
 */

public class MessageEn implements Serializable{
    String ico;
    String time;
    String title;
    String wenben;
    private String id;
    private String memberId;
    private String bizId;
    private String content;
    private Object img;
    private int type;
    private int status;
    private String issueTime;
    private String addTime;
    private Object searchKey;


    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWenben() {
        return wenben;
    }

    public void setWenben(String wenben) {
        this.wenben = wenben;
    }

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
