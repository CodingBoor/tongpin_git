package com.qmx163;

import com.qmx163.activity.StudyToday.StudyCommentManager;
import com.qmx163.util.DataUtil;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by likai on 2017/11/23.
 * email: codingkai@163.com
 */
public class StudyCommentManagerTest {

    private StudyCommentManager studyCommentManager;
    @Before
    public void setUp() throws Exception {
        studyCommentManager = new StudyCommentManager(DataUtil.commentList());
    }

    @Test
    public void getCommentByIndex() throws Exception {
        int size = studyCommentManager.size();
        for (int i = 0; i < size; i++) {
            StudyCommentManager.CommentProvider commentProvider = studyCommentManager.getCommentByIndex(i);
            System.out.println(commentProvider.getComment().getContent());
        }
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(25, studyCommentManager.size());
    }

    @Test
    public void main() throws Exception {

    }

}