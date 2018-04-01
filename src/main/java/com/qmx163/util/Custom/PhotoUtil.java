package com.qmx163.util.Custom;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.qmx163.R;
import com.qmx163.base.BaseAc;

import java.io.File;
import java.io.IOException;

/**
 * 拍摄或者选取图片工具
 * Created by lzp on 2016/10/12.
 */
public class PhotoUtil {

    public static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
    public static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    public static final int PHOTO_REQUEST_CUT = 3;// 结果
    /* 图片名称 */
    public static  String PHOTO_FILE_NAME = System.currentTimeMillis()+".jpg";

    public static void shoot(BaseAc activity) {
        shoot(activity,PHOTO_REQUEST_CAMERA);
    }

    public static void shoot(final BaseAc activity, final int request){
        PHOTO_FILE_NAME = System.currentTimeMillis()+".jpg";
        activity.requestPermission(1, Manifest.permission.CAMERA, new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    // 判断存储卡是否可以用，可用进行存储
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(Environment.getExternalStorageDirectory(), PHOTO_FILE_NAME)));
                    }
                    activity.startActivityForResult(intent, request);
                } catch (Exception e) {
                    activity.showToast(R.string.no_permission_to_use_the_camera);
                }
                return;
            }
        }, new Runnable() {
            @Override
            public void run() {
                activity.showToast(R.string.no_permission_to_use_the_camera);
            }
        });
    }

    public static void select( BaseAc activity){
        select(activity,PHOTO_REQUEST_GALLERY);
    }

    //相册选取
    public static void select( BaseAc activity,int request){
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, request);
    }

    /**
     * 剪切图片
     *
     * @param uri
     */
    public static void crop(BaseAc activity,Uri uri, int width,int height,int request ) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", width);
        intent.putExtra("outputY", height);
        // 图片格式
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", false);// 取消人脸识别
        intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
        activity.startActivityForResult(intent, request);
    }

    public static void crop(BaseAc activity,Uri uri) {
        crop(activity,uri,PHOTO_REQUEST_CUT);
    }

    /**
     * 剪切图片
     *
     * @param uri
     */
    public static void crop(BaseAc activity,Uri uri,int request){
        crop(activity,uri,DensityUtil.dip2px(activity,100),DensityUtil.dip2px(activity,100),request);
    }



    /**
     * 处理旋转后的图片
     * @param originpath 原图路径
     * @param context 上下文
     * @return 返回修复完毕后的图片路径
     */
    public static String amendRotatePhoto(String originpath, Context context) {

        // 取得图片旋转角度
        int angle = readPictureDegree(originpath);

        // 把原图压缩后得到Bitmap对象
        Bitmap bmp = getCompressPhoto(originpath);;

        // 修复图片被旋转的角度
        Bitmap bitmap = rotaingImageView(angle, bmp);

        // 保存修复后的图片并返回保存后的图片路径
        ImageUtil.saveImage(bitmap, originpath);
        return originpath;
    }


    /**
     * 读取照片旋转角度
     *
     * @param path 照片路径
     * @return 角度
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
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


    /**
     * 把原图按1/10的比例压缩
     *
     * @param path 原图的路径
     * @return 压缩后的图片
     */
    public static Bitmap getCompressPhoto(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 10;  // 图片的大小设置为原来的十分之一
        Bitmap bmp = BitmapFactory.decodeFile(path, options);
        options = null;
        return bmp;
    }



    /**
     * 旋转图片
     * @param angle 被旋转角度
     * @param bitmap 图片对象
     * @return 旋转后的图片
     */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        Bitmap returnBm = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
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
}
