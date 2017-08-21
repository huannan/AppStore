package com.nan.appstore;

/**
 * Created by huannan on 2016/11/26.
 */

public class Const {

    public static final String URL_PER = "http://";
    public static final String URL_SERVER_APP = "/AppStore/";
    public static String URL_IP_DEFAULT = "39.108.9.48";

    public static final String URL_BASE = URL_PER + App.getServerIp() + URL_SERVER_APP;
    public static final String URL_IMAGE = URL_BASE + "WebInfos/image/";
    public static final String URL_DOWNLOAD = URL_BASE + "WebInfos/";
    public static final String URL_IMAGE_APP = URL_BASE + "WebInfos/";

    //用于记录当前是何种状态，在请求完数据之后根据不同的状态进行不同的操作
    public static final int STATE_INIT = 0;
    public static final int STATE_REFRESH = 1;
    public static final int STATE_LOAD_MORE = 2;

    public static final String EXTRA_APP_BEAN = "EXTRA_APP_BEAN";
    public static final String EXTRA_PACKAGE_NAME = "EXTRA_PACKAGE_NAME";
    public static final String EXTRA_IMAGE_INFOS = "EXTRA_IMAGE_INFOS";
    public static final String EXTRA_IMAGE_URL = "EXTRA_IMAGE_URL";

}
