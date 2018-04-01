package com.qmx163.listener;

/**
 * 评论弹窗点击发送监听
 * Created by likai on 2017/9/6.
 */

public interface LiveLockListener {
    void isLock(boolean lock);
    void LockClick(boolean click);
    void giftSend(boolean send);
}
