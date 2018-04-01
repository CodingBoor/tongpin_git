package com.qmx163.util.Custom;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.qmx163.R;
import com.qmx163.config.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 图片的一些操作
 * Created by lzp on 2016/8/30.
 */
public class ImageUtil {
    public static String getPath(android.content.Intent intent, Context context) {

        Uri uri = intent.getData();

        String type = intent.getType();
        String path = "";

        if (uri != null && uri.getScheme().equals("file") && (type.contains("image/"))) {  //某些恶心的系统专用（MIUI7.3稳定版）
            path = uri.getEncodedPath();
        } else {
            path = selectImage(context, uri);
        }
        return path;
    }


    public static void setImage(Context context, String url, final ImageView imageView) {
        Glide.with(context).load(url)
                .asBitmap()
                .placeholder(R.mipmap.mtoux)
                .error(R.mipmap.mtoux)
                .centerCrop().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(resource);
            }
        });
    }

    /**
     * 获取图片路径
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath( final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = "";
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    public static void loadIge(String path, Context context, ImageView imageView) {
        Glide.with(context).load(path)
                .placeholder(R.mipmap.mtoux)
                .error(R.mipmap.mtoux)
                .into(imageView);
    }

    public static void loadImage(String path, Context context, final ImageView imageView) {
        Glide.with(context).load(path)
                .asBitmap()
                .placeholder(R.mipmap.mtoux)
                .error(R.mipmap.mtoux)
                .centerCrop()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(resource);
                    }
                });
    }


    public static String selectImage(Context context, Uri selectedImage) {
//      Log.e(TAG, selectedImage.toString());
        if (selectedImage != null) {
            String uriStr = selectedImage.toString();
            String path = uriStr.substring(10, uriStr.length());
            if (path.startsWith("com.sec.android.gallery3d")) {
//                Log.e(TAG, "It's auto backup pic path:"+selectedImage.toString());
                return null;
            }
        }
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }



    /** 保存方法 */
    public static void saveBitmap(Bitmap bm,String picName) {
//        Log.e(TAG, "保存图片");
        File f = new File("/sdcard/namecard/", picName);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("-----", "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 保存bitmap
     *
     * @param path
     */
    public static boolean saveImage(Bitmap bm, String path) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            boolean data = bm.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fileOutputStream != null) {
                try {
//                    if (!bm.isRecycled()) {
//                        bm.recycle();
//                        bm = null;
//                    }
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 如果图片还没有回收，强制回收

        }
    }

    public static String viewSaveToImage(View view) {
        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        view.setDrawingCacheBackgroundColor(Color.WHITE);

        // 把一个View转换成图片
        Bitmap cachebmp = loadBitmapFromView(view);

        FileOutputStream fos;
        File file = null;
        try {
            // 判断手机设备是否有SD卡
            boolean isHasSDCard = Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED);
            if (isHasSDCard) {
                // SD卡根目录
                File sdRoot = new File(Constants.DIR_IMG);
                if (!sdRoot.exists()) {
                    sdRoot.mkdirs();
                }
                file = new File(sdRoot, System.currentTimeMillis() + ".png");
                fos = new FileOutputStream(file);
            } else
                throw new Exception("创建文件失败!");
            cachebmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.destroyDrawingCache();
        return file == null ? "" : file.getAbsolutePath();
    }

    private static Bitmap loadBitmapFromView(View v) {
        int w = v.getWidth();
        int h = v.getHeight();

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);

        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */

        v.layout(0, 0, w, h);
        v.draw(c);

        return bmp;
    }
}
