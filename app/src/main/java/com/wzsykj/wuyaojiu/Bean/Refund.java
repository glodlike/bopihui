package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class Refund extends BaseEntity{
    /**
     * user_login_status : 1
     * page_title : 退款申请
     * items : [{"id":"467","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","number":"1","unit_price":0.01,"total_price":0.01,"consume_count":1,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0,"keyi_refund":true}]
     * ctl : uc_order
     * act : refund
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : 5ip8ctvqkidv8i323n3mb3kdf6
     * ref_uid : null
     */
    private int user_login_status;
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
    private List<ItemsBean> items;

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

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean extends BaseEntity{
        /**
         * id : 467
         * deal_id : 189
         * deal_icon : http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg
         * name : 五粮液五粮液五粮液 2222
         * sub_name : 五粮液短名 2222
         * number : 1
         * unit_price : 0.01
         * total_price : 0.01
         * consume_count : 1
         * dp_id : 0
         * delivery_status : 0
         * is_arrival : 0
         * is_refund : 1
         * refund_status : 0
         * keyi_refund : true
         */

        private String id;
        private String deal_id;
        private String deal_icon;
        private String name;
        private String sub_name;
        private String number;
        private double unit_price;
        private double total_price;
        private int consume_count;
        private int dp_id;
        private int delivery_status;
        private int is_arrival;
        private int is_refund;
        private int refund_status;
        private boolean keyi_refund;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeal_id() {
            return deal_id;
        }

        public void setDeal_id(String deal_id) {
            this.deal_id = deal_id;
        }

        public String getDeal_icon() {
            return deal_icon;
        }

        public void setDeal_icon(String deal_icon) {
            this.deal_icon = deal_icon;
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

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
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

        public int getConsume_count() {
            return consume_count;
        }

        public void setConsume_count(int consume_count) {
            this.consume_count = consume_count;
        }

        public int getDp_id() {
            return dp_id;
        }

        public void setDp_id(int dp_id) {
            this.dp_id = dp_id;
        }

        public int getDelivery_status() {
            return delivery_status;
        }

        public void setDelivery_status(int delivery_status) {
            this.delivery_status = delivery_status;
        }

        public int getIs_arrival() {
            return is_arrival;
        }

        public void setIs_arrival(int is_arrival) {
            this.is_arrival = is_arrival;
        }

        public int getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(int is_refund) {
            this.is_refund = is_refund;
        }

        public int getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(int refund_status) {
            this.refund_status = refund_status;
        }

        public boolean isKeyi_refund() {
            return keyi_refund;
        }

        public void setKeyi_refund(boolean keyi_refund) {
            this.keyi_refund = keyi_refund;
        }
    }
}
