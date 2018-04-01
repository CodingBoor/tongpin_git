package com.qmx163.util;

import android.text.TextUtils;

import java.security.MessageDigest;

/**
 * @description: MD5加密工具
 * @author LiJing
 * @date 2017年4月14日 上午12:01:34
 */
public class MD5Util {

	private final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}

		return resultSb.toString();
	}

	/**
	 * 转换byte到16进制
	 * 
	 * @param b
	 * @return
	 */
	private String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n += 256;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 字符串MD5加密
	 * 
	 * @param source 原字符串
	 * @param encoding 编码类型
	 * @return
	 */
	public String string2MD5(String source, String encoding) {
		String result = null;
		try {
			result = new String(source);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (TextUtils.isEmpty(encoding)) {
				result = byteArrayToHexString(md.digest(result.getBytes()));
			} else {
				result = byteArrayToHexString(md.digest(result.getBytes(encoding)));
			}
		} catch (Exception exception) {
		}
		return result;
	}

	/**
	 * 字符串转换成16进制字符串
	 * 
	 * @param source
	 * @return
	 */
	public String string2HexString(String source) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < source.length(); i++) {
			int ch = (int) source.charAt(i);
			String strHex = Integer.toHexString(ch);
			hexString.append(strHex);
		}
		return hexString.toString();
	}
	
	/**
	 * MD5加密字符串
	 * @param source 加密字符串
	 * @param upperCase 是否转大写
	 * @return
	 */
	public String MD5Encode(String source, boolean upperCase) {
		String encoding = "UTF-8";
		String result = string2MD5(source, encoding);
		result = string2HexString(result);
		result = string2MD5(result, encoding);
		return upperCase ? result.toUpperCase() : result;
	}
	

	
}
