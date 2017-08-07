package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class OrderDetailBean extends BaseEntity {

    /**
     * user_login_status : 1
     * items_refund_status : 1
     * shop_info : {"name":"牛山店","address":"牛山北路50号","tel":"86191919"}
     * pay_status : 0
     * keyi_dianpin : false
     * refund_status : 0
     * order_id : 93
     * order_status : 0
     * order_sn : 2016122905024310
     * create_time : 1482973363
     * pay_time : null
     * total_price : 0.01
     * pay_amount : 0.00
     * deal_total_price : 0.01
     * delivery_fee : 0.00
     * account_money : 0.00
     * consignee : 浩韵控股集团
     * mobile : 18271681932
     * memo :
     * address : 紫荆花路48号南都研发中心A座八楼
     * delivery_time : 1481225400
     * delivery_name : 到店提货
     * deal_order_item : [{"sub_name":"泰谷啤酒（易拉罐）250ml*24罐","attr_str":"","number":"1","unit_price":"0.01","deal_icon":"http://www.bphapp.com/bopihui/public/attachment/201612/15/13/58522ddadf43e_244x148.jpg"}]
     * order_status_str : 未付款
     * page_title : 订单详情
     * ctl : uc_order
     * act : view
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : k7o0jqosh2c7slb6vgfjnq9m06
     * ref_uid : null
     */

    private int user_login_status;
    private int items_refund_status;
    private ShopInfoBean shop_info;
    private String pay_status;
    private boolean keyi_dianpin;
    private String refund_status;
    private String order_id;
    private String order_status;
    private String order_sn;
    private String create_time;
    private String pay_time;
    private double total_price;
    private String pay_amount;
    private String deal_total_price;
    private String delivery_fee;
    private String account_money;
    private String consignee;
    private String mobile;
    private String memo;
    private String address;
    private String delivery_time;
    private String delivery_name;
    private String order_status_str;
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
    private List<DealOrderItemBean> deal_order_item;

    public int getUser_login_status() {
        return user_login_status;
    }

    public void setUser_login_status(int user_login_status) {
        this.user_login_status = user_login_status;
    }

    public int getItems_refund_status() {
        return items_refund_status;
    }

    public void setItems_refund_status(int items_refund_status) {
        this.items_refund_status = items_refund_status;
    }

    public ShopInfoBean getShop_info() {
        return shop_info;
    }

    public void setShop_info(ShopInfoBean shop_info) {
        this.shop_info = shop_info;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public boolean isKeyi_dianpin() {
        return keyi_dianpin;
    }

    public void setKeyi_dianpin(boolean keyi_dianpin) {
        this.keyi_dianpin = keyi_dianpin;
    }

    public String getRefund_status() {
        return refund_status;
    }

    public void setRefund_status(String refund_status) {
        this.refund_status = refund_status;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(String pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getDeal_total_price() {
        return deal_total_price;
    }

    public void setDeal_total_price(String deal_total_price) {
        this.deal_total_price = deal_total_price;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getAccount_money() {
        return account_money;
    }

    public void setAccount_money(String account_money) {
        this.account_money = account_money;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getDelivery_name() {
        return delivery_name;
    }

    public void setDelivery_name(String delivery_name) {
        this.delivery_name = delivery_name;
    }

    public String getOrder_status_str() {
        return order_status_str;
    }

    public void setOrder_status_str(String order_status_str) {
        this.order_status_str = order_status_str;
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

    public List<DealOrderItemBean> getDeal_order_item() {
        return deal_order_item;
    }

    public void setDeal_order_item(List<DealOrderItemBean> deal_order_item) {
        this.deal_order_item = deal_order_item;
    }

    public static class ShopInfoBean {
        /**
         * name : 牛山店
         * address : 牛山北路50号
         * tel : 86191919
         */

        private String name;
        private String address;
        private String tel;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }

    public static class DealOrderItemBean {
        /**
         * sub_name : 泰谷啤酒（易拉罐）250ml*24罐
         * attr_str :
         * number : 1
         * unit_price : 0.01
         * deal_icon : http://www.bphapp.com/bopihui/public/attachment/201612/15/13/58522ddadf43e_244x148.jpg
         */

        private String sub_name;
        private String attr_str;
        private String number;
        private String unit_price;
        private String deal_icon;

        public String getSub_name() {
            return sub_name;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        public String getAttr_str() {
            return attr_str;
        }

        public void setAttr_str(String attr_str) {
            this.attr_str = attr_str;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public String getDeal_icon() {
            return deal_icon;
        }

        public void setDeal_icon(String deal_icon) {
            this.deal_icon = deal_icon;
        }
    }
}
