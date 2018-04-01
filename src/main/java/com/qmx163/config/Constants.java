
package com.qmx163.config;

import android.os.Environment;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.qmx163.data.bean.Mebean.SocketLogin;

import java.io.File;

/**
 * 全局常量
 *
 * date: 2016年4月6日 下午3:02:36
 */
public class Constants {

	/**
	 * socket连接成功给服务器的传参
	 * @param lessonId  课程id
	 * @param periodsId  课时id
	 * @param memberId   会员ID
     * @return
     */
	public static String socketLogin(String lessonId, String periodsId, String memberId) {
		SocketLogin model = new SocketLogin();
		model.setCode("ONLINE_LOGIN");
		model.setMessage("登录");
		SocketLogin.DataBean dataModel = new SocketLogin.DataBean();
		dataModel.setLessonId(TextUtils.isEmpty(lessonId) ? "" : lessonId);
		dataModel.setLessonPeriodsId(TextUtils.isEmpty(periodsId) ? "" : periodsId);
		dataModel.setMemberId(TextUtils.isEmpty(memberId) ? "" : memberId);
		model.setData(dataModel);
		Gson gson = new Gson();
		return gson.toJson(model);
	}


	/**
	 * socket每两分钟给服务器发送消息
	 * @param memberId  用户id
	 * @return
	 */
	public static String socketNewMessage(String memberId) {
		SocketLogin model = new SocketLogin();
		model.setCode("NEW_MESSAGE");
		model.setMessage("");
		SocketLogin.DataBean dataModel = new SocketLogin.DataBean();
		dataModel.setMemberId(TextUtils.isEmpty(memberId) ? "" : memberId);
		model.setData(dataModel);
		Gson gson = new Gson();
		return gson.toJson(model);
	}

	/**
	 * socket点击播放
	 * @param memberId   会员ID
	 * @param lessonId  课程id
	 * @param periodsId  课时id
	 * @param webinarId  微吼id
	 * @return
	 */
	public static String socketPlay(String lessonId, String periodsId, String memberId,String webinarId) {
		SocketLogin model = new SocketLogin();
		model.setCode("MEMBER_ONLINE_TRACK");
//		model.setMessage("播放");
		SocketLogin.DataBean dataModel = new SocketLogin.DataBean();
		dataModel.setLessonId(TextUtils.isEmpty(lessonId) ? "" : lessonId);
		dataModel.setLessonPeriodsId(TextUtils.isEmpty(periodsId) ? "" : periodsId);
		dataModel.setMemberId(TextUtils.isEmpty(memberId) ? "" : memberId);
		dataModel.setWebinarId(TextUtils.isEmpty(memberId) ? "" : webinarId);
		model.setData(dataModel);
		Gson gson = new Gson();
		return gson.toJson(model);
	}

	/**
	 * socket点击播放
	 * @param id  课程id
	 * @return
	 */
	public static String socketPlayPause(String id) {
		SocketLogin model = new SocketLogin();
		model.setCode("MEMBER_ONLINE_TRACK");
//		model.setMessage("播放");
		SocketLogin.DataBean dataModel = new SocketLogin.DataBean();
		dataModel.setId(TextUtils.isEmpty(id) ? "" : id);
		model.setData(dataModel);
		Gson gson = new Gson();
		return gson.toJson(model);
	}


	/**
	 * socket断开给服务器的传参
	 * @return
     */
	public static String socketLoginOut(){
		SocketLogin model = new SocketLogin();
		model.setCode("LOGOUT");
		model.setMessage("退出登录");
		model.setData(null);
		Gson gson = new Gson();
		return gson.toJson(model);
	}

	/**
	 * socket参数
	 */
	public final static String SOCKET_SUCCESS = "CONNECT_SUCCESS";  //连接成功
	public final static String SOCKET_QUESTION = "HEARBEAT_ONLINE_QUESTION";  //提问信息
	public final static String SOCKET_QUESTION_CAN = "HEARBEAT_ONLINE_STATUS";  //是否允许提问和送礼
	public final static String SOCKET_LIVE_MESSAGE = "HEARBEAT_ONLINE_MESSAGES";  //直播消息
	public final static String SOCKET_LIVE_PLAY = "MEMBER_ONLINE_TRACK";  //点击直播返回code
	public final static String SOCKET_NEW_MESSAGE = "NEW_MESSAGE";  //未读消息总数
	public final static String SOCKET_MEMBER_ONLINE_TRACK = "MEMBER_ONLINE_TRACK";  //直播开始收到id号




	/** app文件夹内存卡存储路径 */
//	public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + "GUGU";

	/* ************************** start userSPF所用KEY ************************** */
	/** 用户信息 */
	public static final String USER_INFO = "user_info";
	/** 用户ID */
	public static final String USER_ID = "user_id";
	/* *************************** end userSPF所用KEY ************************** */
	/**
	 * 程序在手机SDK中的主缓存目录.
	 */
	public static final String APP_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator
			+ "LiveVideo/"; // TODO 路径有待修改
	/**
	 *
	 */
	public static final String LogKey = "rizhi";
	/**
	 * 程序在手机SDK中的图片缓存目录.
	 */
	public static final String DIR_IMG = APP_PATH + "image";
	/**
	 * 程序在手机SDK中的视频缓存目录.
	 */
	public static final String DIR_VIDEO = APP_PATH + "video/";
	/**
	 * 程序在手机SDK中的音频缓存目录.
	 */
	public static final String DIR_AUDIO = APP_PATH + "audio/";
//log TAG
	public final static String HTTP_ERROR = "http error";

	/**
	 * sp参数
	 */
	public final static String USER_NAME = "user_name"; //用户姓名：
	public final static String USER_SCORE = "user_score"; //用户积分：
	public final static String USER_LEVEL = "user_level"; //用户等级：
	public final static String USER_LOGIN_ID = "user_login_id";//用户手机：
	public final static String USER_IMG = "user_img";//用户有头像url：
	public final static String USER_BIRTHDAY = "user_birthday";//用户生日：
	public final static String USER_SEX = "user_sex";//用户性别：
	public final static String PARENT_ID = "parent_id";//家长id：
	public final static String USER_TOKEN = "user_token";//token：
	public final static String USER_PASSWORD = "user_password";//密码：
	public final static String USER_PUSH = "user_push";//推送状态：
	public final static String USER_TYPE = "user_type";//用户身份： 1,家长， 0 ，学生
	public final static String FIRST_ON = "firsh_on";//是否第一次登录

	/**
	 * 传递参数
	 */
	public final static String STUDY_DASK_ID = "study_task_id";//学习任务id：
	public final static String STUDY_CONTENT = "study_content";//学习任务内容：
	public final static String STUDY_TITLE = "study_title";//学习任务内容：
	public final static String STUDY_COMMENT_COUNT = "study_comment_count";//学习任务品论条数：

	/**
	 * app参数
	 */
	public final static String APP_STUDY_BG = "personal_background";//学习任务id：
	public final static String APP_VERSION = "app_version";//学习任务id：


}
