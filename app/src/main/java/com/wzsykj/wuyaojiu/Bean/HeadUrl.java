package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class HeadUrl  {


    /**
     * small_url : http://v2.519wz.cn/public/avatar/000/00/00/90virtual_avatar_small.jpg
     * middle_url : http://v2.519wz.cn/public/avatar/000/00/00/90virtual_avatar_middle.jpg
     * big_url : http://v2.519wz.cn/public/avatar/000/00/00/90virtual_avatar_big.jpg
     * ctl : uc_account
     * act : upload_avatar
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : i224c2mh72ngbl0kd8poltt8i7
     * ref_uid : null
     */

    private String small_url;
    private String middle_url;
    private String big_url;
    private String ctl;
    private String act;
    private int status;
    private String info;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;

    public String getSmall_url() {
        return small_url;
    }

    public void setSmall_url(String small_url) {
        this.small_url = small_url;
    }

    public String getMiddle_url() {
        return middle_url;
    }

    public void setMiddle_url(String middle_url) {
        this.middle_url = middle_url;
    }

    public String getBig_url() {
        return big_url;
    }

    public void setBig_url(String big_url) {
        this.big_url = big_url;
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
