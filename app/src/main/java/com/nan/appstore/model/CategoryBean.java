package com.nan.appstore.model;

import com.nan.appstore.model.base.BaseBean;

import java.util.List;

/**
 * Created by huannan on 2016/11/27.
 */

public class CategoryBean  extends BaseBean {

    /**
     * title : 应用
     * infos : [{"url1":"image/category_app_0.jpg","url2":"image/category_app_1.jpg","url3":"image/category_app_2.jpg","name1":"浏览器","name2":"输入法","name3":"健康"},{"url1":"image/category_app_3.jpg","url2":"image/category_app_4.jpg","url3":"image/category_app_5.jpg","name1":"效率","name2":"教育","name3":"理财"},{"url1":"image/category_app_6.jpg","url2":"image/category_app_7.jpg","url3":"image/category_app_8.jpg","name1":"阅读","name2":"个性化","name3":"购物"},{"url1":"image/category_app_9.jpg","url2":"image/category_app_10.jpg","url3":"image/category_app_11.jpg","name1":"资讯","name2":"生活","name3":"工具"},{"url1":"image/category_app_12.jpg","url2":"image/category_app_13.jpg","url3":"image/category_app_14.jpg","name1":"出行","name2":"通讯","name3":"拍照"},{"url1":"image/category_app_15.jpg","url2":"image/category_app_16.jpg","url3":"image/category_app_17.jpg","name1":"社交","name2":"影音","name3":"安全"}]
     */

    private String title;
    private List<InfosBean> infos;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<InfosBean> getInfos() {
        return infos;
    }

    public void setInfos(List<InfosBean> infos) {
        this.infos = infos;
    }

    public static class InfosBean {
        /**
         * url1 : image/category_app_0.jpg
         * url2 : image/category_app_1.jpg
         * url3 : image/category_app_2.jpg
         * name1 : 浏览器
         * name2 : 输入法
         * name3 : 健康
         */

        private String url1;
        private String url2;
        private String url3;
        private String name1;
        private String name2;
        private String name3;
        private String packageName1;
        private String packageName2;
        private String packageName3;

        public String getUrl1() {
            return url1;
        }

        public void setUrl1(String url1) {
            this.url1 = url1;
        }

        public String getUrl2() {
            return url2;
        }

        public void setUrl2(String url2) {
            this.url2 = url2;
        }

        public String getUrl3() {
            return url3;
        }

        public void setUrl3(String url3) {
            this.url3 = url3;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }

        public String getName3() {
            return name3;
        }

        public void setName3(String name3) {
            this.name3 = name3;
        }

        public String getPackageName1() {
            return packageName1;
        }

        public void setPackageName1(String packageName1) {
            this.packageName1 = packageName1;
        }

        public String getPackageName2() {
            return packageName2;
        }

        public void setPackageName2(String packageName2) {
            this.packageName2 = packageName2;
        }

        public String getPackageName3() {
            return packageName3;
        }

        public void setPackageName3(String packageName3) {
            this.packageName3 = packageName3;
        }
    }
}
