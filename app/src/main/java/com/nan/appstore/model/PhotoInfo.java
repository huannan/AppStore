package com.nan.appstore.model;

import com.nan.appstore.model.base.BaseBean;

import java.util.List;

/**
 * Created by huannan on 2016/12/1.
 */

public class PhotoInfo extends BaseBean {

    List<String> mImageUrls;

    public PhotoInfo(List<String> imageUrls) {
        this.mImageUrls = imageUrls;
    }

    public List<String> getmImageUrls() {
        return mImageUrls;
    }

    public void setmImageUrls(List<String> mImageUrls) {
        this.mImageUrls = mImageUrls;
    }
}
