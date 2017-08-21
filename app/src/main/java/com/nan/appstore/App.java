package com.nan.appstore;

import android.app.Application;
import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nan.appstore.net.API;
import com.nan.appstore.utils.PreferenceUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huannan on 2016/11/26.
 */

public class App extends Application {

    private static Retrofit retrofit;
    private static API api;
    private static Context mCtx;

    @Override
    public void onCreate() {
        super.onCreate();
        mCtx = this;
        initRetrofit(getServerIp());
    }

    public static void initRetrofit(String ip) {
        retrofit = new Retrofit.Builder()
                .baseUrl(Const.URL_PER + ip + Const.URL_SERVER_APP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }

    public static API getApi() {
        return api;
    }


    public static String getServerIp() {
        return PreferenceUtils.getInstance(mCtx).getServerIP();
    }


    public static int getMainThreadId(){
        return android.os.Process.myTid();
    }


    public static Context getContext() {
        return mCtx;
    }
}
