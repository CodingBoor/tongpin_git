package com.qmx163.util.photoUtil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * 获取图片的辅助类
 *
 * @author sunsy 2016年3月9日
 */
public class GetImageUtil {

    private String path;
    private Activity activity;
    private String local;
    private File file;

    /**
     * 单例
     */
    private static GetImageUtil instance;

    /**
     * 单例
     *
     * @return
     */
    public static GetImageUtil getInstance() {
        if (instance == null) {
            instance = new GetImageUtil();
        }
        return instance;
    }
//
//    /**
//     * 初始化
//     * <p>
//     * Created by 邓靖 on  2017/3/7  9:07
//     */
//    public void init(Activity activity) {
//        this.activity = activity;
//        local = Constants.SDCARD_PATH + "/tempImage/";
//        file = new File(local);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//    }

    public String getLocal() {
        return local;
    }


    public File getLocalFile() {
        return file;
    }

   /* public void clearCache() {
        File file = getLocalFile();
        FileUtil.deleteFile(file);
        if (!file.exists()) {
            file.mkdir();
        }
    }*/

    /**
     * 初始化
     * <p>
     * Created by 邓靖 on  2017/3/7  9:07
     */
    private GetImageUtil() {

    }

    /**
     * 设置图片name
     * <p>
     * Created by 邓靖 on  2017/3/7  9:08
     */
    private void first() {
        path = local + System.currentTimeMillis() + ".jpg";
    }

    public String getPath() {
        return path;
    }

    public Uri getPathUri() {
        return Uri.parse("file://" + path);
    }

    /**
     * 获得图片
     * <p>
     * Created by 邓靖 on  2017/3/7  9:08
     */
    public void getPhoto(int code) {
        switch (code) {
            case Config.GOTO_CAMERA:
                first();
                gotoCamera();
                break;
            case Config.GOTO_ALBUM:
                goToGallery();
                break;
            case Config.GOTO_CROP:
                cropImageUri();
                break;
        }
    }

    /**
     * 去相机
     * <p>
     * Created by 邓靖 on  2017/3/7  9:08
     */
    private void gotoCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse("file://" + path));
        activity.startActivityForResult(intent, Config.GOTO_CAMERA);
    }

    /**
     * 去相册
     * <p>
     * Created by 邓靖 on  2017/3/7  9:08
     */
    private void goToGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, Config.GOTO_ALBUM);
    }

    /**
     * 处理从相机获得的图片
     * <p>
     */
    public String saveBitmapFromCamera() {
        String temp = path;
        path = SaveImageUtil.getInstance().savaBitmap2SDCard(activity, Uri.parse("file://" + path), local);
        /* 重新保存之后，删除源文件 */
        File file = new File(temp);
        if (file.exists()) {
            file.delete();
        }
        return path;
    }

    /**
     * 保存本地图片
     * <p>
     * Created by 邓靖 on  2017/3/7  9:09
     */
    public String saveBitmapFromCamera(String path) {
        return SaveImageUtil.getInstance().savaBitmap2SDCard(activity, Uri.parse("file://" + path), local);
    }

    /**
     * 从相册获得的图片要保存到SD卡
     * <p>
     * Created by 邓靖 on  2017/3/7  9:09
     */
    public String saveBitmapFromGallery(Intent intent) {
        Uri originalUri = intent.getData();
        try {
            //        if (originalUri.toString().startsWith("content")) {
//            originalUri = Uri.parse(getFileByUrl(originalUri));
//        }
            path = SaveImageUtil.getInstance().savaBitmap2SDCard(activity, originalUri, local);
            return path;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 从相册获得的图片要保存到SD卡
     * <p>
     * Created by 邓靖 on  2017/3/7  9:09
     */
    private String getFileByUrl(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = activity.managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        return img_path;
    }

    /**
     * 裁剪图片,裁剪之前，必须调用保存到本地的方法
     * <p>
     * Created by 邓靖 on  2017/3/7  9:11
     */
    private void cropImageUri() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.parse("file://" + path), "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", false);
        intent.putExtra("aspectX", 300);
        intent.putExtra("aspectY", 300);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse("file://" + path));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        activity.startActivityForResult(intent, Config.GOTO_CROP);
    }


    public class Config {
        public static final int GOTO_CAMERA = 0X147852;
        public static final int GOTO_ALBUM = 0X1478963;
        public static final int GOTO_CROP = 0X258963;
    }

}
