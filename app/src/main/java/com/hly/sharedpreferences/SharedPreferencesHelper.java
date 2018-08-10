package com.hly.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/**
 * ~~~~~~文件描述:保存信息配置类~~~~~~
 * ~~~~~~作者:huleiyang~~~~~~
 * ~~~~~~创建时间:2018/8/9~~~~~~
 * ~~~~~~更改时间:2018/8/9~~~~~~
 * ~~~~~~版本号:1~~~~~~
 */
public class SharedPreferencesHelper {

    private  SharedPreferences sharedPreferences;

    private   SharedPreferences.Editor editor;

    public SharedPreferencesHelper(Context context, String FILE_NAME) {
        //实例化SharePreference对象，使用的是get方法，而不是new创建
        //第一个参数是文件的名字
        //第二个参数是存储的模式，一般都是使用私有方式：Context.MODE_PRIVATE
        sharedPreferences = context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    /**
     * 存储
     */
    public  void saveData(String key, Object object) {
        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.commit();
    }

    /**
     * 获取保存的数据
     */
    public Object getData(String key, Object defaultObject) {
        if (defaultObject instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public Boolean contain(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 返回所有的键值对
     */
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }
}

