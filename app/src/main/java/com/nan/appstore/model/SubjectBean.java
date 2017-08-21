package com.nan.appstore.model;

import com.nan.appstore.model.base.BaseBean;

/**
 * Created by huannan on 2016/11/27.
 */

public class SubjectBean extends BaseBean {


    /**
     * des : 一周新锐游戏精选
     * url : recommend_01.jpg
     */

    private String des;
    private String url;
    private String packageName;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
