package com.nan.appstore.utils;


import android.content.Context;

import com.nan.appstore.App;

public class UIUtils {

    /**
     * dip转换px
     */
    public static int dip2px(int dip, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(int px, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    //判断当前的线程是不是在主线程
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == App.getMainThreadId();
    }
}


