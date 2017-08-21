package com.nan.appstore.model;

import com.nan.appstore.model.base.BaseBean;

/**
 * Created by huannan on 2016/12/1.
 */

public class BannerBean extends BaseBean {


    /**
     * imgUrl : home01.jpg
     * packageName : com.jingdong.app.mall
     */

    private String imgUrl;
    private String packageName;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
