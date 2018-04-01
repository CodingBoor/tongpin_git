package com.qmx163.data.bean.Mebean;

/**
 * 回放json数组
 * Created by 李凯 on 2017/9/12.
 */

public class WatchPlayBackEn {

    /**
     * duration : 1327
     * create_time : 2017-09-11 20:18:03
     * webinar_id : 163895261
     * subject : 2017-09-11 19:55:34-2017-09-11 20:18:03
     * webinar_subject : 第三讲水分子
     * name : 2017-09-11 19:55:34-2017-09-11 20:18:03
     * created_at : 2017-09-11 20:18:03
     * id : 443218
     * is_default : 0
     * url : http://e.vhall.com/webinar/record/443218
     * status : 1
     */

    private int duration;
    private String create_time;
    private int webinar_id;
    private String subject;
    private String webinar_subject;
    private String name;
    private String created_at;
    private int id;
    private int is_default;
    private String url;
    private int status;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getWebinar_id() {
        return webinar_id;
    }

    public void setWebinar_id(int webinar_id) {
        this.webinar_id = webinar_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getWebinar_subject() {
        return webinar_subject;
    }

    public void setWebinar_subject(String webinar_subject) {
        this.webinar_subject = webinar_subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIs_default() {
        return is_default;
    }

    public void setIs_default(int is_default) {
        this.is_default = is_default;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
