package com.qmx163.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 没完善   处理json之类的方法
 * Created by wym on 2016/7/7.
 */
public class JsonHelper<T> {


    /**
     * 非静态调用
     * @param str
     * @param
     * @return
     */
    public List<T> parseToList(String str) {
        List<T> info = new Gson().fromJson(str, new TypeToken<T>(){}.getType());
        return info;
    }

    /**
     *  解析成对象列表
     * @param <T>
     * @param json
     * @param cls
     * @return
     */
    public static  <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            mList.add(new Gson().fromJson(elem, cls));
        }
        return mList;
    }


    public static Object parseToObject (String jsonString, Class _class){
        if(TextUtils.isEmpty(jsonString)){
            return null;
        }
        Gson gson = new Gson();
        Object obj= gson.fromJson(jsonString, _class);
        return obj;
    }
    public static Object parseToObjectAll (String jsonString, Type type){
        if(TextUtils.isEmpty(jsonString)){
            return null;
        }
        Gson gson = new Gson();
        Object obj= gson.fromJson(jsonString, type);
        return obj;
    }
}
