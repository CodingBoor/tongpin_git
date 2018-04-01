package com.qmx163.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by likai on 2016/11/28.
 * email: codingkai@163.com
 * 对SharePreference的封装
 */

public class PrefUtils {
    private static String PreferenceName = "user_info";
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static void setString(Context ctx, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void setInt(Context ctx, String key, int value) {
        SharedPreferences sp = ctx.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context ctx, String key, int defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PreferenceName,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }





    /**
     * 储存复杂的数据字段对象
     *
     * @param context
     * @param key
     * @param t
     * @return
     */
    public static <T> boolean saveObjectToShare(Context context, String key, T t) {
        return saveObjectToShare(context, PreferenceName, key, t);
    }

    /**
     *
     * @param context
     * @param name
     * @param key
     * @param t
     * @return
     */

    public static <T> boolean saveObjectToShare(Context context, String name, String key, T t) {
        try {
            SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            // 存储
            SharedPreferences.Editor editor = sp.edit();
            if (t == null) {
                editor.putString(key, "");
                editor.commit();
                return true;
            }
            ByteArrayOutputStream toByte = new ByteArrayOutputStream();
            ObjectOutputStream oos;

            oos = new ObjectOutputStream(toByte);
            oos.writeObject(t);
            // 对byte[]进行Base64编码
            String payCityMapBase64 = new String(Base64.encode(toByte.toByteArray(), Base64.DEFAULT));

            editor.putString(key, payCityMapBase64);
            editor.commit();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 得到复杂的数据对象
     *
     * @param context
     * @param key
     * @return
     */
    public static <T> T getObjectFromShare(Context context, String key) {
        return getObjectFromShare(context, PreferenceName, key);
    }

    /**
     * 得到复杂的数据对象
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public static <T> T getObjectFromShare(Context context, String name, String key) {
        try {
            SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
            String payCityMapBase64 = sp.getString(key, "");
            if (payCityMapBase64.length() == 0) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(payCityMapBase64, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
