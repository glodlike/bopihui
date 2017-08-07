package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public class Update {


    /**
     * serverVersion : 5
     * filename : http://218.205.82.19/dd.myapp.com/16891/08F350521B9C4B0F80FF03C140987EB2.apk?mkey=575e6442a356d042&f=5e8c&c=0&fsname=com.wzsykj.qz_1.2_3.apk&p=.apk
     * android_upgrade : 版本升级
     * hasfile : 1
     * forced_upgrade : 0
     * ctl : version
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : 3688pq2vguve8jdbr1pufcenm6
     * ref_uid : null
     */

    private String serverVersion;
    private String filename;
    private String android_upgrade;
    private int hasfile;
    private int forced_upgrade;
    private String ctl;
    private String act;
    private int status;
    private String info;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAndroid_upgrade() {
        return android_upgrade;
    }

    public void setAndroid_upgrade(String android_upgrade) {
        this.android_upgrade = android_upgrade;
    }

    public int getHasfile() {
        return hasfile;
    }

    public void setHasfile(int hasfile) {
        this.hasfile = hasfile;
    }

    public int getForced_upgrade() {
        return forced_upgrade;
    }

    public void setForced_upgrade(int forced_upgrade) {
        this.forced_upgrade = forced_upgrade;
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
