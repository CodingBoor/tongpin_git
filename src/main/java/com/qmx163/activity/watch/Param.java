package com.qmx163.activity.watch;

import com.vhall.vhalllive.CameraFilterView;

import java.io.Serializable;

/**
 * 直播参数类
 */
public class Param implements Serializable {

    //发直播相关
    public String broId = "";
    public String broToken = "";
    public int pixel_type = CameraFilterView.TYPE_HDPI;
    public int videoBitrate = 500;
    public int videoFrameRate = 20;
    //看直播相关
    public String watchId = "";
    public String key = "";
    public int bufferSecond = 4;

    //回放
    public String recordId = "";

    //用户相关
    public String userVhallId = "";
    public String userCustomId = "";
    public String userName = "";
    public String userAvatar = "";

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBroId() {
        return broId;
    }

    public void setBroId(String broId) {
        this.broId = broId;
    }

    public String getBroToken() {
        return broToken;
    }

    public void setBroToken(String broToken) {
        this.broToken = broToken;
    }

    public int getPixel_type() {
        return pixel_type;
    }

    public void setPixel_type(int pixel_type) {
        this.pixel_type = pixel_type;
    }

    public int getVideoBitrate() {
        return videoBitrate;
    }

    public void setVideoBitrate(int videoBitrate) {
        this.videoBitrate = videoBitrate;
    }

    public int getVideoFrameRate() {
        return videoFrameRate;
    }

    public void setVideoFrameRate(int videoFrameRate) {
        this.videoFrameRate = videoFrameRate;
    }

    public String getWatchId() {
        return watchId;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getBufferSecond() {
        return bufferSecond;
    }

    public void setBufferSecond(int bufferSecond) {
        this.bufferSecond = bufferSecond;
    }

    public String getUserVhallId() {
        return userVhallId;
    }

    public void setUserVhallId(String userVhallId) {
        this.userVhallId = userVhallId;
    }

    public String getUserCustomId() {
        return userCustomId;
    }

    public void setUserCustomId(String userCustomId) {
        this.userCustomId = userCustomId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
}
