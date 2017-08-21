package com.nan.appstore.model;

import com.nan.appstore.model.base.BaseBean;

import java.util.List;

/**
 * Created by huannan on 2016/11/26.
 */

public class HomeBean extends BaseBean {

    private List<BannerBean> picture;
    private List<AppBean> list;

    public List<BannerBean> getPicture() {
        return picture;
    }

    public void setPicture(List<BannerBean> picture) {
        this.picture = picture;
    }

    public List<AppBean> getList() {
        return list;
    }

    public void setList(List<AppBean> list) {
        this.list = list;
    }


}
