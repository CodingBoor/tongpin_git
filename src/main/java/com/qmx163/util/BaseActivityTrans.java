
package com.qmx163.util;

import android.app.Activity;
import android.content.Intent;

import com.qmx163.R;


/**
 * Activity 跳转动画
 *
 * date: 2016年4月6日 下午3:16:25
 */
public class BaseActivityTrans {

	/**
	 * 无请求码
	 */
	public static final int REQUEST_CODE_NULL = -123455;

	/**
	 * 无结果码
	 */
	public static final int RESUTL_CODE_NULL = -123456;

	/**
	 * 无动画
	 */
	public static final int TRANS_NULL = -1;

	/**
	 * 移动动画
	 */
	public static final int TRANS_MOVE = 0;

	/**
	 * 缩放
	 */
	public static final int TRANS_SCALE = 1;

	/**
	 * 启动目标Activity 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param clzz
	 *            目标Activity
	 * 
	 */
	public static void startMove(Activity activity, Class<?> clzz) {
		startMove(activity, new Intent(activity, clzz));
	}

	/**
	 * 启动目标Activity 有参数 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            目标Intent
	 * 
	 */
	public static void startMove(Activity activity, Intent intent) {
		startMove(activity, intent, REQUEST_CODE_NULL);
	}

	/**
	 * 启动目标Activity 有参数 有请求码 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            目标Intent
	 * 
	 */
	public static void startMove(Activity activity, Intent intent, int reques_code) {
		start(activity, intent, reques_code, TRANS_MOVE);
	}

	/**
	 * 启动目标Activity 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param clzz
	 *            目标Activity
	 * 
	 */
	public static void startScale(Activity activity, Class<?> clzz) {
		startMove(activity, new Intent(activity, clzz));
	}

	/**
	 * 启动目标Activity 有参数 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            目标Intent
	 * 
	 */
	public static void startScale(Activity activity, Intent intent) {
		startMove(activity, intent, REQUEST_CODE_NULL);
	}

	/**
	 * 启动目标Activity 有参数 有请求码 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            目标Intent
	 * 
	 */
	public static void startScale(Activity activity, Intent intent, int request_code) {
		start(activity, intent, request_code, TRANS_SCALE);
	}

	/**
	 * 启动目标Activity 有参数 有请求码 区分动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            Intent
	 * @param request_code
	 *            请求码
	 * @param trans
	 *            动画方式
	 * 
	 */
	public static void start(Activity activity, Intent intent, int request_code, int trans) {
		if (REQUEST_CODE_NULL == request_code) {
			activity.startActivity(intent);
		} else {
			activity.startActivityForResult(intent, request_code);
		}
		if (TRANS_MOVE == trans) {
			//activity.overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
			activity.overridePendingTransition(R.anim.push_right_in, R.anim.slide_right_out);//平移进入退出
		} else if (TRANS_SCALE == trans) {
			activity.overridePendingTransition(R.anim.alpha_scale_in, R.anim.alpha_scale_out);
		} else {
			activity.overridePendingTransition(0, 0);
		}
	}

	/**
	 * finish当前Activity 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * 
	 */
	public static void finishMove(Activity activity) {
		finishMove(activity, null, RESUTL_CODE_NULL);
	}

	/**
	 * finish当前Activity 有参数 移动动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            参数
	 * @param result_code
	 *            结果码
	 * 
	 */
	public static void finishMove(Activity activity, Intent intent, int result_code) {
		finish(activity, intent, result_code, TRANS_MOVE);
	}

	/**
	 * finish当前Activity 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * 
	 */
	public static void finishScale(Activity activity) {
		finishScale(activity, null, RESUTL_CODE_NULL);
	}

	/**
	 * finish当前Activity 缩放动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            参数
	 * @param result_code
	 *            结果码
	 * 
	 */
	public static void finishScale(Activity activity, Intent intent, int result_code) {
		finish(activity, intent, result_code, TRANS_SCALE);
	}

	/**
	 * finish当前Activity 有参数 区分动画
	 * 
	 * @param activity
	 *            当前Activity
	 * @param intent
	 *            Intent
	 * @param result_code
	 *            结果码
	 * @param trans
	 *            动画方式
	 * 
	 */
	public static void finish(Activity activity, Intent intent, int result_code, int trans) {
		if (null != intent) {
			activity.setResult(result_code, intent);
		}
		activity.finish();
		if (TRANS_MOVE == trans) {
			//activity.overridePendingTransition(R.anim.alpha_scale_in, R.anim.push_right_out);
			activity.overridePendingTransition(R.anim.push_right_in, R.anim.slide_right_out);//平移进入退出
		} else if (TRANS_SCALE == trans) {
			activity.overridePendingTransition(R.anim.alpha_scale_in, R.anim.alpha_scale_out);
		} else {
			activity.overridePendingTransition(0, 0);
		}
	}
}
