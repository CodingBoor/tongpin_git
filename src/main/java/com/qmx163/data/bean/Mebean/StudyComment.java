
package com.qmx163.data.bean.Mebean;

import java.io.Serializable;
import java.util.List;

/**
 * 评论实体类
 * Created by likai on 2017/8/29.
 */

public class StudyComment implements Serializable{

    private String addTime;
    private int commentLikeCount;
    private String content;
    private String headImg;
    private String id;
    private String memberId;
    private String memberName;
    private String more;
    private boolean like;
    private List<StudyComment> childComments;

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getCommentLikeCount() {
        return commentLikeCount;
    }

    public void setCommentLikeCount(int commentLikeCount) {
        this.commentLikeCount = commentLikeCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<StudyComment> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<StudyComment> childComments) {
        this.childComments = childComments;
    }
}