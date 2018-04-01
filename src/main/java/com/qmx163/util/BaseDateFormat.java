
package com.qmx163.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *  时间(时间戳)工具类
 *
 * date: 2016年4月6日 下午3:17:10
 */
public class BaseDateFormat {
	private static BaseDateFormat mZyDateUtil;

	/**
	 * 截止"天"格式串(分割线)
	 */
	public static final String FORMAT_DAY_LINE = "yyyy-MM-dd";

	/**
	 * 截止"分"格式串(分割线)
	 */
	public static final String FORMAT_MINUTE_LINE = "yyyy-MM-dd HH:mm";

	/**
	 * 截止"秒"格式串(分割线)
	 */
	public static final String FORMAT_SECOND_LINE = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 截止"天"格式串(反斜杠)
	 */
	public static final String FORMAT_DAY_SLASH = "yyyy/MM/dd";

	/**
	 * 截止"分"格式串(反斜杠)
	 */
	public static final String FORMAT_MINUTE_SLASH = "yyyy/MM/dd HH:mm";

	/**
	 * 截止"秒"格式串(反斜杠)
	 */
	public static final String FORMAT_SECOND_SLASH = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 截止"天"格式串(汉字)
	 */
	public static final String FORMAT_DAY_WORD = "yyyy年MM月dd日";

	/**
	 * 截止"分"格式串(汉字)
	 */
	public static final String FORMAT_MINUTE_WORD = "yyyy年MM月dd日 HH:mm";

	/**
	 * 截止"秒"格式串(汉字)
	 */
	public static final String FORMAT_SECOND_WORD = "yyyy年MM月dd日 HH:mm:ss";

	/**
	 * 单例模式
	 * 
	 * @return 类实例
	 * 
	 */
	public static BaseDateFormat getInstance() {
		if (null == mZyDateUtil) {
			mZyDateUtil = new BaseDateFormat();
		}

		return mZyDateUtil;
	}

	/**
	 * 依格式串获取时间戳对应的时间
	 * 
	 * @param time
	 *            long 13位时间戳
	 * @param format
	 *            String 格式串
	 * @return String 时间字符串
	 * 
	 */
	public String getDate(Long timestamp, String format) {
		if (timestamp.toString().length() <= 10) {
			timestamp *= 1000;
		}

		return new SimpleDateFormat(format, Locale.getDefault()).format(timestamp);
	}

	/**
	 * 依格式串获取时间对应的时间戳
	 * 
	 * @param date
	 *            String 时间
	 * @param format
	 *            String 格式串
	 * @return long 13位时间戳
	 * 
	 */
	public long getTimestamp(String date, String format) {
		try {
			return new SimpleDateFormat(format, Locale.getDefault()).parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param date0
	 *            String 时间0
	 * @param date1
	 *            String 时间1
	 * @param format
	 *            格式串
	 * @return long <br>
	 *         如果data0大于date1,返回值大于0 <br>
	 *         如果data0小于date1,返回值小于0 <br>
	 *         如果data0等于date1,返回值等于0
	 * 
	 */
	public long compareDate(String date0, String date1, String format) {
		return compareTimestamp(getTimestamp(date0, format), getTimestamp(date1, format));
	}

	/**
	 * 比较两个时间戳的大小
	 * 
	 * @param t0
	 *            Long 13位时间戳0
	 * @param t1
	 *            Long 13位时间戳1
	 * @param format
	 *            格式串
	 * @return long <br>
	 *         如果t0大于t1,返回值大于0 <br>
	 *         如果t0小于t1,返回值小于0 <br>
	 *         如果t0等于t1,返回值等于0
	 * 
	 */
	public long compareTimestamp(Long t0, Long t1) {
		if (t0.toString().length() <= 10) {
			t0 *= 1000;
		}

		if (t1.toString().length() <= 10) {
			t1 *= 1000;
		}

		return t0 - t1;
	}
}
