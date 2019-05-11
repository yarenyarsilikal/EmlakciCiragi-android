package com.example.emlakciciragi.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Yaren YARŞILIKAL on 11.05.2019.
 */
public class PreferencesUtil {
    private static final String KEY_SHARED_FILE = "APP_FILE";

    private final SharedPreferences sharedPreferences;



    public PreferencesUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY_SHARED_FILE, Context.MODE_PRIVATE);
    }

    public String getData(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void saveData(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void removeData(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}
