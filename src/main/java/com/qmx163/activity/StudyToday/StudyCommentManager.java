package com.qmx163.activity.StudyToday;

import com.qmx163.data.bean.Mebean.StudyComment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by likai on 2017/11/23.
 * email: codingkai@163.com
 */

public class StudyCommentManager {

    private List<StudyComment> orgData;
    private List<CommentProvider> data = new ArrayList<>();

    public StudyCommentManager(List<StudyComment> orgData) {
        this.orgData = orgData;
        if (orgData == null || orgData.size() == 0) {
            return;
        }
        for (StudyComment studyComment : orgData) {
            data.add(new CommentParent(studyComment));
            List<StudyComment> childComments = studyComment.getChildComments();
            if (childComments != null) {
                for (StudyComment childComment : childComments) {
                    data.add(new CommentChild(childComment, studyComment));
                }
            }
        }
    }

    public boolean isLike(CommentParent parent) {
        return parent.getComment().isLike();
    }

    public void setLike(int index, boolean like) {
        CommentProvider commentProvider = data.get(index);
        if (commentProvider.getType() != CommentProvider.TYPE_PARENT) return;
        commentProvider.getComment().setLike(like);
    }

    public void addChildComment(int parentIndex, StudyComment comment) {
        CommentProvider parentCommentProvider = data.get(parentIndex);
        int type = parentCommentProvider.getType();
        if (type != CommentProvider.TYPE_PARENT) return;
        CommentParent commentParent = (CommentParent) parentCommentProvider;
        commentParent.childCount++;
        //添加child comment 到主评论中
        List<StudyComment> childComments = commentParent.getComment().getChildComments();
        if (childComments == null) {
            List<StudyComment> list = new ArrayList<>(1);
            list.add(comment);
            commentParent.getComment().setChildComments(list);
        } else {
            childComments.add(comment);
        }
        //改变data中的数据
        data.add(parentIndex + commentParent.childCount, new CommentChild(comment,commentParent.comment));
    }

    public CommentProvider getCommentByIndex(int index) {
        return data.get(index);
    }

    public void addList(List<StudyComment> list){
        orgData.addAll(list);
        for (StudyComment studyComment : list) {
            data.add(new CommentParent(studyComment));
            List<StudyComment> childComments = studyComment.getChildComments();
            if (childComments != null) {
                for (StudyComment childComment : childComments) {
                    data.add(new CommentChild(childComment, studyComment));
                }
            }
        }
    }

    public void cleanManager(){
        orgData.clear();
        data.clear();
    }

    public int size() {
        return data.size();
    }

    public class CommentParent implements CommentProvider {

        private StudyComment comment;
        private int childCount;

        public CommentParent(StudyComment comment) {
            this.comment = comment;
            if (comment.getChildComments() != null) {
                childCount = comment.getChildComments().size();
            }
        }

        @Override
        public StudyComment getComment() {
            return comment;
        }

        @Override
        public int getType() {
            return CommentProvider.TYPE_PARENT;
        }
    }

    public class CommentChild implements CommentProvider {

        private StudyComment comment;
        private StudyComment parent;

        public CommentChild(StudyComment comment, StudyComment parent) {
            this.comment = comment;
            this.parent = parent;
        }

        @Override
        public StudyComment getComment() {
            return comment;
        }

        @Override
        public int getType() {
            return CommentProvider.TYPE_CHILD;
        }
    }

    public interface CommentProvider {

        int TYPE_PARENT = 0;
        int TYPE_CHILD = 1;

        StudyComment getComment();

        int getType();
    }
}
