package com.nan.appstore.model;

import com.nan.appstore.model.base.BaseBean;

import java.util.List;

/**
 * Created by huannan on 2016/11/27.
 */

public class DetailBean extends BaseBean {


    /**
     * id : 1525490
     * name : 有缘网
     * packageName : com.youyuan.yyhl
     * iconUrl : app/com.youyuan.yyhl/icon.jpg
     * stars : 4
     * downloadNum : 200万+
     * version : 4.1.9
     * date : 2014-04-24
     * size : 3876203
     * downloadUrl : app/com.youyuan.yyhl/com.youyuan.yyhl.apk
     * des : 产品介绍：
     有缘是时下最受大众单身男女亲睐的婚恋交友软件。有缘网专注于通过轻松、易用的大众婚恋交友服务，帮助中国最广泛的单身男女找到理想对象。目前已有1亿3632万单身男女在有缘网上找对象。免费下载，立即注册，更拥有同城交友、自定义搜索、异性推荐等贴心、便捷、高效的婚恋交友服务！找对象，上有缘网！人多机会多，还怕找不到？
     * author : 有缘网
     * screen : ["app/com.youyuan.yyhl/screen0.jpg","app/com.youyuan.yyhl/screen1.jpg","app/com.youyuan.yyhl/screen2.jpg","app/com.youyuan.yyhl/screen3.jpg","app/com.youyuan.yyhl/screen4.jpg"]
     * safe : [{"safeUrl":"app/com.youyuan.yyhl/safeIcon0.jpg","safeDesUrl":"app/com.youyuan.yyhl/safeDesUrl0.jpg","safeDes":"已通过安智市场官方认证，是正版软件","safeDesColor":0},{"safeUrl":"app/com.youyuan.yyhl/safeIcon1.jpg","safeDesUrl":"app/com.youyuan.yyhl/safeDesUrl1.jpg","safeDes":"已通过安智市场安全检测，请放心使用","safeDesColor":0},{"safeUrl":"app/com.youyuan.yyhl/safeIcon2.jpg","safeDesUrl":"app/com.youyuan.yyhl/safeDesUrl2.jpg","safeDes":"无任何形式的广告","safeDesColor":0}]
     */

    private long id;
    private String name;
    private String packageName;
    private String iconUrl;
    private float stars;
    private String downloadNum;
    private String version;
    private String date;
    private long size;
    private String downloadUrl;
    private String des;
    private String author;
    private List<String> screen;
    private List<SafeBean> safe;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public String getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(String downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<String> getScreen() {
        return screen;
    }

    public void setScreen(List<String> screen) {
        this.screen = screen;
    }

    public List<SafeBean> getSafe() {
        return safe;
    }

    public void setSafe(List<SafeBean> safe) {
        this.safe = safe;
    }

    public static class SafeBean {
        /**
         * safeUrl : app/com.youyuan.yyhl/safeIcon0.jpg
         * safeDesUrl : app/com.youyuan.yyhl/safeDesUrl0.jpg
         * safeDes : 已通过安智市场官方认证，是正版软件
         * safeDesColor : 0
         */

        private String safeUrl;
        private String safeDesUrl;
        private String safeDes;
        private int safeDesColor;

        public String getSafeUrl() {
            return safeUrl;
        }

        public void setSafeUrl(String safeUrl) {
            this.safeUrl = safeUrl;
        }

        public String getSafeDesUrl() {
            return safeDesUrl;
        }

        public void setSafeDesUrl(String safeDesUrl) {
            this.safeDesUrl = safeDesUrl;
        }

        public String getSafeDes() {
            return safeDes;
        }

        public void setSafeDes(String safeDes) {
            this.safeDes = safeDes;
        }

        public int getSafeDesColor() {
            return safeDesColor;
        }

        public void setSafeDesColor(int safeDesColor) {
            this.safeDesColor = safeDesColor;
        }
    }


    @Override
    public String toString() {
        return "DetailBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", stars=" + stars +
                ", downloadNum='" + downloadNum + '\'' +
                ", version='" + version + '\'' +
                ", date='" + date + '\'' +
                ", size=" + size +
                ", downloadUrl='" + downloadUrl + '\'' +
                ", des='" + des + '\'' +
                ", author='" + author + '\'' +
                ", screen=" + screen +
                ", safe=" + safe +
                '}';
    }
}
