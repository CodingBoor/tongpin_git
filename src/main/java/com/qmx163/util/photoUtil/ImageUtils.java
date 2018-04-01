package com.qmx163.util.photoUtil;

import android.media.ExifInterface;

import java.io.IOException;

/**
 * Created by sunsiyuan on 16/2/18.
 */
class ImageUtils {

   /**
    * 读取图片属性：旋转的角度
    *
    * Created by 邓靖 on  2017/3/7  9:11
    */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {// /storage/sdcard0/tempImage/20151122162052.jpg
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

}
