package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class CartInfo {

    /**
     * user_login_status : 1
     * cart_list : [{"id":"36","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.02,"number":"2","deal_id":"20","attr":"","attr_str":"","name":"泰谷啤酒（易拉罐）250ml*24罐","sub_name":"泰谷啤酒（易拉罐）250ml*24罐","max":100,"icon":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522ddadf43e_280x170.jpg"},{"id":"37","return_score":0,"return_total_score":0,"unit_price":165,"total_price":1320,"number":"8","deal_id":"17","attr":"","attr_str":"","name":"奥丁格 小麦白啤酒 500mlX24罐","sub_name":"奥丁格 小麦白啤酒 500mlX24罐","max":100,"icon":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522852ebae5_280x170.jpg"},{"id":"38","return_score":0,"return_total_score":0,"unit_price":128,"total_price":128,"number":"1","deal_id":"7","attr":"","attr_str":"","name":"格鲁尔干红葡萄酒","sub_name":"格鲁尔干红葡萄酒","max":100,"icon":"http://116.62.28.31/bopihui/public/attachment/201612/07/13/58479cf2d3568_280x170.jpg"}]
     * total_data : {"total_price":1448.02,"return_total_score":0}
     * is_score : 0
     * has_mobile : 1
     * page_title : 购物车
     * ctl : cart
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : 53j56k9peb0p09sgdhsvrfrnh0
     * ref_uid : null
     */

    private int user_login_status;
    private TotalDataBean total_data;
    private int is_score;
    private int has_mobile;
    private String page_title;
    private String ctl;
    private String act;
    private int status;
    private String info;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;
    private List<CartListBean> cart_list;

    public int getUser_login_status() {
        return user_login_status;
    }

    public void setUser_login_status(int user_login_status) {
        this.user_login_status = user_login_status;
    }

    public TotalDataBean getTotal_data() {
        return total_data;
    }

    public void setTotal_data(TotalDataBean total_data) {
        this.total_data = total_data;
    }

    public int getIs_score() {
        return is_score;
    }

    public void setIs_score(int is_score) {
        this.is_score = is_score;
    }

    public int getHas_mobile() {
        return has_mobile;
    }

    public void setHas_mobile(int has_mobile) {
        this.has_mobile = has_mobile;
    }

    public String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
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

    public List<CartListBean> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<CartListBean> cart_list) {
        this.cart_list = cart_list;
    }

    public static class TotalDataBean extends BaseEntity {
        /**
         * total_price : 1448.02
         * return_total_score : 0
         */

        private double total_price;
        private int return_total_score;

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public int getReturn_total_score() {
            return return_total_score;
        }

        public void setReturn_total_score(int return_total_score) {
            this.return_total_score = return_total_score;
        }
    }

    public static class CartListBean extends BaseEntity {
        /**
         * id : 36
         * return_score : 0
         * return_total_score : 0
         * unit_price : 0.01
         * total_price : 0.02
         * number : 2
         * deal_id : 20
         * attr :
         * attr_str :
         * name : 泰谷啤酒（易拉罐）250ml*24罐
         * sub_name : 泰谷啤酒（易拉罐）250ml*24罐
         * max : 100
         * icon : http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522ddadf43e_280x170.jpg
         */

        private String id;
        private int return_score;
        private int return_total_score;
        private double unit_price;
        private double total_price;
        private String number;
        private String deal_id;
        private String attr;
        private String attr_str;
        private String name;
        private String sub_name;
        private int max;
        private String icon;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getReturn_score() {
            return return_score;
        }

        public void setReturn_score(int return_score) {
            this.return_score = return_score;
        }

        public int getReturn_total_score() {
            return return_total_score;
        }

        public void setReturn_total_score(int return_total_score) {
            this.return_total_score = return_total_score;
        }

        public double getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(double unit_price) {
            this.unit_price = unit_price;
        }

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getDeal_id() {
            return deal_id;
        }

        public void setDeal_id(String deal_id) {
            this.deal_id = deal_id;
        }

        public String getAttr() {
            return attr;
        }

        public void setAttr(String attr) {
            this.attr = attr;
        }

        public String getAttr_str() {
            return attr_str;
        }

        public void setAttr_str(String attr_str) {
            this.attr_str = attr_str;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSub_name() {
            return sub_name;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}
