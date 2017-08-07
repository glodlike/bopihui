package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class UserInfo {

    /**
     * id : 90
     * user_name : 新用户_90
     * user_pwd : e5a4ea461902629d64a4540dcad4433f
     * email : null
     * mobile : 13634146852
     * is_tmp : 1
     * ctl : user
     * act : dophlogin
     * status : 1
     * info : 登录成功
     * city_name : 温州
     * return : 1
     * sess_id : 7ccivbbb6i6hd5rpu8j3ppb0a2
     * ref_uid : null
     */
    private String id;
    private String user_name;
    private String user_pwd;
    private Object email;
    private String mobile;
    private String is_tmp;
    private String ctl;
    private String act;
    private int status;
    private String info;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIs_tmp() {
        return is_tmp;
    }

    public void setIs_tmp(String is_tmp) {
        this.is_tmp = is_tmp;
    }

    public String getCtl() {
        return ctl;
    }

    public void setCtl(String ctl) {
        this.ctl = ctl;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public int getReturnX() {
        return returnX;
    }

    public void setReturnX(int returnX) {
        this.returnX = returnX;
    }

    public String getSess_id() {
        return sess_id;
    }

    public void setSess_id(String sess_id) {
        this.sess_id = sess_id;
    }

    public Object getRef_uid() {
        return ref_uid;
    }

    public void setRef_uid(Object ref_uid) {
        this.ref_uid = ref_uid;
    }
}
