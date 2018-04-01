
package com.qmx163.net;

/**
 * 接口请求方法定义
 *
 * date: 2016年4月6日 下午3:04:18
 */
public class NetField {

	// =======================start 接口站点==========================

	/** 测试服务器 */
	static final String SITE_TEST = "http://47.94.208.70/tongpin/app/";

	/** 正式服务器 */
//	public static final String SITE_OFFICAL = "";

	//-------------------阿里OSS begin------------------
	public static final String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
	public static final String ACCESSKEYID = "LTAIBt1qaydB2fJ4";
	public static final String ACCESSKEYSECRET = "LuvqAD2juVj2jlMQD5ZOrQ0dFclyB7";
	public static final String BUCKET = "bucket-tplh";
	public static final String DOWNLOADURL = "http://bucket-tplh.oss-cn-beijing.aliyuncs.com";
	//-------------------阿里OSS end-------------------bucket-tplh.oss-cn-beijing-internal.aliyuncs.com
//	public static final String ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";
//	public static final String ACCESSKEYID = "LTAIGbTVgWGSywzI";
//	public static final String ACCESSKEYSECRET = "DuXefbrckJIQDdB8ledRk8db9oYm9u";
//	public static final String BUCKET = "wanto";
//	public static final String DOWNLOADURL = "http://wanto.oss-cn-hangzhou.aliyuncs.com";


	public static final String SITE = SITE_TEST;
	// =======================end 接口站点==========================
	/**
	 * 上传成功
	 */
	public static final String UPLOADSUCCESS = "success";

	/**
	 * 上传失败
	 */
	public static final String UPLOADFAIL = "fail";



}
