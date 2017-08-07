package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class UserCenter {

    /**
     * user_login_status : 1
     * page_title : 会员中心
     * uid : 8160
     * user_name : Android测试
     * mobile : 13634146852
     * user_money_format : ¥0
     * user_score : 130
     * user_score_format : 130积分
     * user_avatar : http://cdn-v2.519wz.cn/public/avatar/noavatar_big.gif?x-oss-process=image/quality,Q_80
     * unpay_order_count : 8
     * paid_order_count : 1
     * review_order_count : 0
     * refund_order_count : 0
     * ctl : user_center
     * act : index
     * status : 1
     * city_name : 温州市
     * return : 1
     * sess_id : h4kfqvlo74m70kojdl9mr83f05
     * ref_uid : null
     */

    private int user_login_status;
    private String page_title;
    private String uid;
    private String user_name;
    private String mobile;
    private String user_money_format;
    private int user_score;
    private String user_score_format;
    private String user_avatar;
    private String unpay_order_count;
    private String paid_order_count;
    private String review_order_count;
    private String refund_order_count;
    private String ctl;
    private String act;
    private int status;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;

    public int getUser_login_status() {
        return user_login_status;
    }

    public void setUser_login_status(int user_login_status) {
        this.user_login_status = user_login_status;
    }

    public String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_money_format() {
        return user_money_format;
    }

    public void setUser_money_format(String user_money_format) {
        this.user_money_format = user_money_format;
    }

    public int getUser_score() {
        return user_score;
    }

    public void setUser_score(int user_score) {
        this.user_score = user_score;
    }

    public String getUser_score_format() {
        return user_score_format;
    }

    public void setUser_score_format(String user_score_format) {
        this.user_score_format = user_score_format;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getUnpay_order_count() {
        return unpay_order_count;
    }

    public void setUnpay_order_count(String unpay_order_count) {
        this.unpay_order_count = unpay_order_count;
    }

    public String getPaid_order_count() {
        return paid_order_count;
    }

    public void setPaid_order_count(String paid_order_count) {
        this.paid_order_count = paid_order_count;
    }

    public String getReview_order_count() {
        return review_order_count;
    }

    public void setReview_order_count(String review_order_count) {
        this.review_order_count = review_order_count;
    }

    public String getRefund_order_count() {
        return refund_order_count;
    }

    public void setRefund_order_count(String refund_order_count) {
        this.refund_order_count = refund_order_count;
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
