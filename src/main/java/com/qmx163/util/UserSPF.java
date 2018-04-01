//
//
//package com.livevideo.util;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//
//import com.livevideo.config.Constants;
//
//
///**
// * SharedPreferences 本地文件保存
// *
// * Created by 邓靖 on  2017/3/14  9:20
// */
//public class UserSPF {
//	/**
//	 * 单例
//	 */
//	private static UserSPF userSpf;
//
//	/**
//	 * sharedPreferences
//	 */
//	private SharedPreferences mSPF;
//	private Editor mEditor;
//
//	public static UserSPF getInstance() {
//		if (null == userSpf) {
//			userSpf = new UserSPF();
//		}
//		return userSpf;
//	}
//
//	/**
//	 * 初始化
//	 *
//	 * Created by 邓靖 on  2017/3/14  9:22
//	 */
//	public void init(Context context) {
//		mSPF = context.getSharedPreferences(Constants.USER_INFO, Context.MODE_PRIVATE);
//		mEditor = mSPF.edit();
//	}
//
//
//	/**
//	 *  删除文件
//	 *
//	 * Created by 邓靖 on  2017/3/14  9:22
//	 */
//	public void clear() {
//		mSPF.edit().clear().commit();
//	}
//
//
//
//	/**
//	 * 保存用户ID
//	 *
//	 * Created by 邓靖 on  2017/3/14  9:22
//	 */
//	public UserSPF setUserId(String user_id) {
//		mEditor.putString(Constants.USER_ID, user_id).commit();
//		return userSpf;
//	}
//
//
//
//
//	/**
//	 * 获取用户ID
//	 *
//	 * Created by 邓靖 on  2017/3/14  9:23
//	 */
//	public String getUserId() {
//		String user_id = mSPF.getString(Constants.USER_ID, null);
//		if (TextUtils.isEmpty(user_id)) {
//			return "null";
//		}
//		return user_id;
//	}
//
//
//
//}
