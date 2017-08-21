package com.nan.appstore.model;

import com.nan.appstore.model.base.BaseBean;

/**
 * Created by huannan on 2016/11/27.
 */

public class UserBean extends BaseBean {

    /**
     * name : 3113002620 13电信2班 吴焕楠
     * sex : 男
     * email : 1435378192@qq.com
     * avatar : user_avatar.png
     * avatarBlur : user_avatar_blur.png
     * school : 广东工业大学 电子信息工程
     */

    private String name;
    private String sex;
    private String email;
    private String avatar;
    private String avatarBlur;
    private String school;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarBlur() {
        return avatarBlur;
    }

    public void setAvatarBlur(String avatarBlur) {
        this.avatarBlur = avatarBlur;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
