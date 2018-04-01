package com.qmx163.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qmx163.application.MyApplication;
import com.qmx163.config.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常用方法工具
 * 
 * 
 */
@SuppressLint("SimpleDateFormat")
public class Tools {

	public static String local = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/";

	/**
	 * 格式化价格
	 * 
	 * @param argStr
	 * @param format
	 * @return
	 */
	public static String formatPrice(String argStr, String format) {
		double arg = Double.parseDouble(argStr);
		DecimalFormat fnum = new DecimalFormat(format);
		return fnum.format(arg);
	}

	/**
	 * showToast
	 *
	 * @param context
	 */
	public static void showToast(Context context, String content) {
		if (content != null && content.length() > 0) {
			Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 获取版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVerCode(Context context) {
		int verCode = 0;
		try {
			verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}

	/**
	 * 获取版本号
	 *
	 * @param context
	 * @return
	 */
	public static String getVerName(Context context) {
		String verCode = null;
		try {
			verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}

	/**
	 * 获取mac地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getMacAdd(Context context) {
		WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return manager.getConnectionInfo().getMacAddress();
	}

	/**
	 * 判断 多个字段的值否为空
	 * 
	 * @return true为null或空; false不null或空
	 */
	public static boolean isNull(String... ss) {
		for (int i = 0; i < ss.length; i++) {
			if (null == ss[i] || ss[i].equals("") || ss[i].equalsIgnoreCase("null")) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 判断 一个字段的值否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNull(String s) {
		if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	/**
	 * 将dp类型的尺寸转换成px类型的尺寸
	 * 
	 * @param size
	 * @param context
	 * @return
	 */
	public static int DPtoPX(int size, Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
		return (int) ((float) size * metrics.density + 0.5);
	}

	/**
	 * 验证身份证号码
	 * 
	 * @author TangWei
	 * @param idCard
	 * @return
	 */
	public static boolean validateIdCard(String idCard) {
		if (isNull(idCard))
			return false;
		String pattern = "^[0-9]{17}[0-9|xX]{1}$";
		return idCard.matches(pattern);
	}

	/**
	 * 验证手机号码
	 * 
	 * @author TangWei
	 * @return
	 */
	public static boolean validatePhone(String mobiles) {
		/*if (isNull(phone))
			return false;
		String pattern = "^(1(([35][0-9])|(47)|[8][0126789]))\\d{8}$";
		return phone.matches(pattern);*/

		if (!isNull(mobiles)) {
			Pattern p = Pattern
					.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[0-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			return m.matches();
		}
		return false;

	}
	/**
	 * 验证手机格式
	 */
	public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
		String telRegex = "[1][345789]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles)) return false;
		else return mobiles.matches(telRegex);
	}
	/**
	 * 验证邮箱
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {
		if (isNull(email))
			return false;
		String pattern = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		return email.matches(pattern);
	}

	/**
	 * 判断字符串是邮箱还是手机号码
	 * 
	 * @param str
	 * @return 1-手机号码，2-邮箱，如果都不是则返回0
	 */
	public static int validatePhoneOrEmail(String str) {
		if (validatePhone(str))
			return 1;
		if (validateEmail(str))
			return 2;
		return 0;
	}

	/**
	 * 截屏
	 * 
	 * @param view
	 * @return
	 */
	public static String saveFile(View view, Context context) {
		Date date = new Date();
		String path = Tools.parse(String.valueOf(date.getTime()), "yyyyMMddHHmm") + ".png";
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		int[] size = getScreenSize(context);
		Bitmap bitmap = Bitmap.createBitmap(size[0], size[1], Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		view.draw(canvas);
		return saveFile(bitmap, local, path);
	}

	/**
	 * 保存bitmap
	 * 
	 * @param bitmap
	 * @param local
	 * @param path
	 * @return
	 * 
	 */
	public static String saveFile(Bitmap bitmap, String local, String path) {
		File file = new File(local);
		if (!file.exists()) {
			file.mkdirs();
		}
		File out = new File(local + path);
		try {
			FileOutputStream fos = new FileOutputStream(out);
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return local + path;
	}

	/**
	 * 获取屏幕像素尺寸
	 * 
	 * @return 数组：0-宽，1-高
	 */
	public static int[] getScreenSize(Context context) {
		int[] size = new int[2];
		DisplayMetrics metrics = new DisplayMetrics();
		((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
		size[0] = metrics.widthPixels;
		size[1] = metrics.heightPixels;
		return size;
	}

	/**
	 * 根据给定的格式化参数，将给定的时间戳转换为需要的字符串
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return java.util.Dconxinkaishiate
	 */
	public static String parse(String dateString, String dateFormat) {
		if ("".equals(dateString.trim()) || dateString == null) {
			return null;
		}
		long da = Long.parseLong(dateString);
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = new Date(da);
		return sdf.format(date);
	}

	/**
	 * 判断是否有网络
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		return (mNetworkInfo != null && mNetworkInfo.isAvailable());
	}

	/**
	 * 判断当前网络是否是wifi网络.
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		return (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI);
	}

	/**
	 * 判断当前网络是否是3G网络.
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		return (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE);
	}

	/**
	 * MD5加密
	 * 
	 * @param srcStr
	 *            需要加密的字符串
	 * @return 加密后的字符串
	 * 
	 */
	public static String encodeMD5(String srcStr) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(srcStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			return buf.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}


	/**
	 * 将Map整理为Json
	 *
	 * Created by 邓靖 on  2016/5/3  15:06.
	 */
	public static String map2Json(Map<String, String> map)  {
		JSONObject jsonObject = new JSONObject();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			try {
				jsonObject.put(entry.getKey(), entry.getValue());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return jsonObject.toString();
	}


	/***实体转json
	 *
	 * @author 邓靖
	 *
	 * Date:2016年6月13日下午3:23:29
	 */
	public static String entityTojson(Object object) {
		Gson gson=new Gson();
		return gson.toJson(object).toString();
	}

//
//	/**
//	 * 整理map并加密
//	 *
//	 * Created by 邓靖 on  2016/5/3  16:46.
//	 */
//	public static Map<String, String> mapTojson(Map<String, String> has){
//		for (String key : has.keySet()) {
//				Log.d("gugu", key + " = " + has.get(key));
//		}
//		Map<String, String> ha=new HashMap<String, String>();
//		ha.put("methodcode", has.get("methodcode"));
//		has.remove("methodcode");
//		try {
//			ha.put("data", URLEncoder.encode(Des3Util.encrypt(map2Json(has), Constants.KRY)));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		for (String key : ha.keySet()) {
//		//	Log.d("gugu", key + " = " + ha.get(key));
//		}
//		return ha;
//	}



	/**
	 * dip转为 px
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 *  px 转为 dip
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}


	/**
	 * 是否有SD卡
	 *
	 * Created by 邓靖 on  2016/7/4  13:14.
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}
//
//	/**
//	 * 拼接web地址
//	 *
//	 * Created by 邓靖 on  2016/7/25  10:13
//	 */
//	public static String geturl(HashMap<String,String> ss){
//		try {
//			return "data="+ URLEncoder.encode(Des3Util.encrypt(Tools.map2Json(ss), Constants.KRY));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	/**
	 * 根据图片名称获取id 不要后缀
	 *  imageView12.setImageResource(Tools.getimageid(mCurActivity,"home_3"));
	 * Created by 邓靖 on  2016/7/26  13:21
	 */
	public static int getimageid(Context con, String name){
		return con.getResources().getIdentifier(name, "mipmap",con.getPackageName());
	}


	/**
	 * 根据图片名字获取bitmap
	 * 
	 * Created by 邓靖 on  2016/7/26  14:42
	 */
	public static Bitmap IdgetBitmap(Context con, String name) {
		int resID = getimageid(con,name);
		return BitmapFactory.decodeResource(con.getResources(),resID);
	}

	public static void debugLog(String str) {
		Log.d(Constants.LogKey, str);
	}


	public static boolean isEnglish(String charaString){

		return charaString.matches("^[a-zA-Z]*");

	}

	/**
	 * 是否包含中文
	 *
	 * Created by 邓靖 on  2017/3/6  15:01
	 */
	public static boolean isChinese(String str){

		String regEx = "[\\u4e00-\\u9fa5]+";

		Pattern p = Pattern.compile(regEx);

		Matcher m = p.matcher(str);

		if(m.find())

			return true;

		else

			return false;

	}


	/**
	 * 判断是否为数字
	 * @param str
	 * @return
     */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}


	//密码格式
//	1.密码必须至少有8哥字符。
//			2.密码只能包括字母和数字。
//			3.密码必须至少有2个数字。
	public static boolean isPsw(String password) {
		if(password.length() < 6) {
			showToast(MyApplication.getContext(),"密码不能少于六位");
			return false;
		} else {
			int numberCounter = 0;
			for(int i = 0; i < password.length(); i++) {
				char c = password.charAt(i);
				if(!Character.isLetterOrDigit(c)) {
					showToast(MyApplication.getContext(),"密码必须由数字和字母组成");
					return false;
				}
				if(Character.isDigit(c)) {
					numberCounter++;
				}
			}
			if(numberCounter < 0) {
				return false;
			}
		}
		return true;
	}

}
