package com.qmx163.util.photoUtil;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;

import com.qmx163.util.BaseDateFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 保存图片
 * 
 * Created by 邓靖 on  2017/3/7  9:10
 */
public class SaveImageUtil {

    private static SaveImageUtil instance;

    public static SaveImageUtil getInstance() {
        if (instance == null) {
            instance = new SaveImageUtil();
        }
        return instance;
    }

   /**
    * 保存图片到SD卡
    * 
    * Created by 邓靖 on  2017/3/7  9:10
    */
    public String savaBitmap2SDCard(Activity mActivity, Uri originalUri, String saveFile) {
        try {
            /**
             * 获取图片的旋转角度，有些系统把拍照的图片旋转了，有的没有旋转
             */
            Log.e("info", "图片路径：" + originalUri.getPath());
            InputStream in = mActivity.getContentResolver().openInputStream(originalUri);
            int degree = ImageUtils.readPictureDegree(originalUri.getPath());
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            in.close();
            int i = 0;
            Bitmap bitmap = null;
            while (true) {
                if ((options.outWidth >> i <= 1000)
                        && (options.outHeight >> i <= 1000)) {
                    in = mActivity.getContentResolver().openInputStream(originalUri);
                    options.inSampleSize = (int) Math.pow(2.0D, i);
                    options.inJustDecodeBounds = false;
                    bitmap = BitmapFactory.decodeStream(in, null, options);
                    break;
                }
                i += 1;
            }
            /**
             * 把图片旋转为正的方向
             */
            bitmap = rotaingImageView(degree, bitmap);
            return savaBitmap2SDCard(bitmap,  BaseDateFormat.getInstance().getDate(System.currentTimeMillis(), "yyyyMMddHHmmss") + ".jpg", saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!originalUri.toString().startsWith("content")) {
                File file = new File(originalUri.getPath());
                file.delete();
            }
        }
        return "";
    }



    /**
     * 旋转图片
     * 
     * Created by 邓靖 on  2017/3/7  9:10
     */
    public Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // matrix.setRotate(angle);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bitmap;
        }
        if (bitmap != returnBm) {
            bitmap.recycle();
        }
        return returnBm;
    }

   /**
    * 存图片到sdcard
    * 
    * Created by 邓靖 on  2017/3/7  9:16
    */
    public static String savaBitmap2SDCard(Bitmap bitmap, String img_name, String saveFile) {
        File file = new File(saveFile);

        if (!file.exists()) {
            file.mkdir();
        }
        File imageFile = new File(file, img_name);

        try {
            imageFile.createNewFile();

            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos); // 降低照片质量保存
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bitmap.recycle();
        return imageFile.getPath();
    }

}
