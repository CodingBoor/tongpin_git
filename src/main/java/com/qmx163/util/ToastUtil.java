package com.qmx163.util; /**
 * Project Name:ThreeTiProject
 * Package Name:com.threeti.threetiproject.util
 * File Name:ToastUtil.java
 * Function: Toast提示框工具类. <br/>
 * Description: NULL. <br/>
 * Date:2014年10月9日下午9:43:12
 * Copyright:Copyright (c) 2014, 翔傲科技（上海）有限公司 All Rights Reserved.
 */

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * ClassName:ToastUtil
 * Function:提示框工具类. <br/>
 * Description: 提示框工具类，用于弹出一个简单的文本提示；该工具类主要用于防止重复提示问题. <br/>
 * Date:2014年10月9日
 *
 * @since[ThreeTi/Android Project]
 * @author BaoHang
 * @version V1.0
 */
public class ToastUtil {
    /**
     * 提醒框实例
     */
    private static Toast toast = null;

    /**
     *
     * toastShortShow:显示一个短暂的提示框. <br/>
     *
     * @author BaoHang
     * @param context
     *            上下文实例
     * @param text
     *            要显示的文字，不能为空
     */
    public static void toastShortShow(Context context, CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            toastShow(context, text, Toast.LENGTH_SHORT);
        }
    }
    /**
     *
     * toastShortShow:显示一个短暂的提示框. <br/>
     *
     * @author BaoHang
     * @param context
     *            上下文实例
     * @param textResId
     *            要显示的文字的资源id
     */
    public static void toastShortShow(Context context, int textResId) {
        toastShow(context, textResId, Toast.LENGTH_SHORT);
    }

    /**
     *
     * toastShow:显示一个提示框. <br/>
     *
     * @author BaoHang
     * @param context
     *            上下文实例
     * @param textResId
     *            要显示的文字的资源id
     * @param duration
     *            时长
     */
    public static void toastShow(Context context, int textResId, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, textResId, duration);
        } else {
            toast.setText(textResId);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     *
     * toastShow:显示一个提示框. <br/>
     *
     * @author BaoHang
     * @param context
     *            内容器,上下文实例
     * @param msg
     *            提示框显示消息文字内容
     * @param duration
     *            显示时长，How long to display the message. Either LENGTH_SHORT or
     *            LENGTH_LONG
     */
    public static void toastShow(Context context, CharSequence msg, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, duration);
        } else {
            toast.setText(msg);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
