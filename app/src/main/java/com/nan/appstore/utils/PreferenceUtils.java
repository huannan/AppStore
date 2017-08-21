package com.nan.appstore.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.nan.appstore.Const;

public class PreferenceUtils {
    public static final String PREFERENCE_NAME = "saveInfo";
    private static SharedPreferences mSharedPreferences;
    private static PreferenceUtils mPreferenceUtils;
    private static SharedPreferences.Editor editor;

    private static final String SERVER_IP = "SERVER_IP";


    private PreferenceUtils(Context cxt) {
        mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public static PreferenceUtils getInstance(Context cxt) {
        if (mPreferenceUtils == null) {
            mPreferenceUtils = new PreferenceUtils(cxt);
        }
        editor = mSharedPreferences.edit();
        return mPreferenceUtils;
    }

    public void setServerIP(String ip) {
        editor.putString(SERVER_IP, ip);
        editor.commit();
    }

    public String getServerIP() {
        return mSharedPreferences.getString(SERVER_IP, Const.URL_IP_DEFAULT);
    }

}
