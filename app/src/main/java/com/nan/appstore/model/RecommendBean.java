package com.nan.appstore.model;

import com.nan.appstore.model.base.BaseBean;

/**
 * Created by huannan on 2016/12/1.
 */

public class RecommendBean extends BaseBean {

    /**
     * name : QQ
     * packageName : com.tencent.research.drop
     */

    private String name;
    private String packageName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
