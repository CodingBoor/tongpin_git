package com.qmx163.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.qmx163.application.MyApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * 文件夹操作
 * @author 邓靖
 *
 * date: 2016年4月6日 下午3:08:23
 */
public class FileUtil {

	private static FileUtil fileUtil;

	/**
	 * 单例模式
	 * 
	 * Created by 邓靖 on  2017/3/14  11:14
	 */
	public static FileUtil getInstance() {
		if (null == fileUtil) {
			fileUtil = new FileUtil();
		}
		return fileUtil;
	}


	/**
	 * 获取文件的大小
	 * 
	 * Created by 邓靖 on  2017/3/14  10:59
	 */
	public static long getFileSize(File file) {
		long size = 0;//初始大小为0
		//如果此文件是一个文件夹，遍历大小
		if (file.isDirectory()) {
			String[] paths = file.list();
			File file1;
			for (String path : paths) {
				file1 = new File(file.getAbsolutePath() + "/" + path);
                /*如果是目录，递归调用*/
				if (!file1.isDirectory()) {
					size += file1.length();
				} else {
					size += getFileSize(file1);
				}
			}
		} else {
            /*如果是文件，返回其大小*/
			size = file.length();
		}
		return size;
	}



//	/**
//	 * 创建指定名称的文件夹
//	 *
//	 */
//	public void createFiles(String name) {
//		String path = Constants.SDCARD_PATH;
//		if (!Tools.isNull(name)) {
//			path = Constants.SDCARD_PATH + File.separator + name;
//		}
//
//		File file = new File(path);
//		if (!file.exists()) {
//			file.mkdirs();
//		}
//	}

	
	/**
	 * 文件复制
	 * 
	 * Created by 邓靖 on  2017/3/14  11:01
	 */
	public static void copyfile(String srFile, String dtFile) {
		try {
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);
			OutputStream out = new FileOutputStream(f2);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//
//	/**
//	 * 获取指定位置的文件
//	 *
//	 * Created by 邓靖 on  2017/3/14  11:01
//	 */
//	public Object getObject(String name) {
//		Object obj = null;
//		try {
//			File file = new File(Constants.SDCARD_PATH + File.separator + name);
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//
//			FileInputStream fis = new FileInputStream(Constants.SDCARD_PATH + File.separator + name);
//			ObjectInputStream ois = new ObjectInputStream(fis);
//			obj = ois.readObject();
//			ois.close();
//			fis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return obj;
//	}

//	/**
//	 * 保存文件到指定位置
//	 *
//	 * Created by 邓靖 on  2017/3/14  11:01
//	 */
//	 public void saveObject(Object obj, String name) {
//		try {
//			File file = new File(Constants.SDCARD_PATH + File.separator + name);
//			if (!file.exists()) {
//				file.createNewFile();
//			}
//
//			FileOutputStream fos = new FileOutputStream(Constants.SDCARD_PATH + File.separator + name);
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			oos.writeObject(obj);
//			oos.close();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 删除指定文件
	 * 
	 * Created by 邓靖 on  2017/3/14  11:01
	 */
	public boolean deleteObject(String name) {
		try {
			File file = new File(name);
			file.deleteOnExit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	public static boolean isNetworkConnected() {
		if (MyApplication.getInstance() != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) MyApplication.getInstance()
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
}
