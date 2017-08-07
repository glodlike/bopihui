package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class PayforCart extends BaseEntity {

    /**
     * user_login_status : 1
     * cart_list : [{"id":"275","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.01,"number":"1","deal_id":"112","attr_str":"1瓶,1号","name":"53°飞天茅台500ml 3333","sub_name":"53°飞天茅台500ml 短名33333","max":100,"supplier_id":"92","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"},{"id":"276","return_score":0,"return_total_score":0,"unit_price":12,"total_price":12,"number":"1","deal_id":"190","attr_str":"","name":"53°飞天茅台500ml 11111111111","sub_name":"53°飞天茅台500ml 1111111","max":100,"supplier_id":"92","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"}]
     * cart_list_group : {"sid_92":{"goods_list":[{"id":"275","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.01,"number":"1","deal_id":"112","attr_str":"1瓶,1号","name":"53°飞天茅台500ml 3333","sub_name":"53°飞天茅台500ml 短名33333","max":100,"supplier_id":"92","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"},{"id":"276","return_score":0,"return_total_score":0,"unit_price":12,"total_price":12,"number":"1","deal_id":"190","attr_str":"","name":"53°飞天茅台500ml 11111111111","sub_name":"53°飞天茅台500ml 1111111","max":100,"supplier_id":"92","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"}],"supplier_id":"92","supplier":"519自营"}}
     * total_data : {"total_price":12.01,"return_total_score":0}
     * is_score : 0
     * is_delivery : 1
     * delivery_time : [[["16:45",1486428300],["17:00",1486429200],["17:15",1486430100],["17:30",1486431000],["17:45",1486431900],["18:00",1486432800],["18:15",1486433700],["18:30",1486434600],["18:45",1486435500],["19:00",1486436400],["19:15",1486437300],["19:30",1486438200],["19:45",1486439100],["20:00",1486440000],["20:15",1486440900],["20:30",1486441800]],[["09:00",1486486800],["09:15",1486487700],["09:30",1486488600],["09:45",1486489500],["10:00",1486490400],["10:15",1486491300],["10:30",1486492200],["10:45",1486493100],["11:00",1486494000],["11:15",1486494900],["11:30",1486495800],["11:45",1486496700],["12:00",1486497600],["12:15",1486498500],["12:30",1486499400],["12:45",1486500300],["13:00",1486501200],["13:15",1486502100],["13:30",1486503000],["13:45",1486503900],["14:00",1486504800],["14:15",1486505700],["14:30",1486506600],["14:45",1486507500],["15:00",1486508400],["15:15",1486509300],["15:30",1486510200],["15:45",1486511100],["16:00",1486512000],["16:15",1486512900],["16:30",1486513800],["16:45",1486514700],["17:00",1486515600],["17:15",1486516500],["17:30",1486517400],["17:45",1486518300],["18:00",1486519200],["18:15",1486520100],["18:30",1486521000],["18:45",1486521900],["19:00",1486522800],["19:15",1486523700],["19:30",1486524600],["19:45",1486525500],["20:00",1486526400],["20:15",1486527300],["20:30",1486528200]]]
     * consignee_count : 2
     * consignee_info : {"id":"45","user_id":"90","region_lv1":"1","region_lv2":"2","region_lv3":"52","region_lv4":"509","address":"爱可可","mobile":"13634146852","zip":"","consignee":"成功","is_default":"1","region_lv1_name":"中国","region_lv2_name":"北京","region_lv3_name":"北京","region_lv4_name":"门头沟区"}
     * delivery_list : [{"id":"8","name":"顺风快递","description":"顺风快递,福州地区2元","first_fee":"5.00","allow_default":"1","sort":"1","first_weight":"0.00","continue_weight":"0.00","continue_fee":"0.00","weight_id":"1","is_effect":"1"},{"id":"9","name":"商家配送","description":"","first_fee":"5.00","allow_default":"1","sort":"2","first_weight":"1.00","continue_weight":"0.00","continue_fee":"0.00","weight_id":"1","is_effect":"1"},{"id":"10","name":"到店提货","description":"","first_fee":"0.00","allow_default":"1","sort":"3","first_weight":"0.00","continue_weight":"0.00","continue_fee":"0.00","weight_id":"1","is_effect":"1"}]
     * is_coupon : 0
     * show_payment : 1
     * has_ecv : 1
     * voucher_list : []
     * has_account : 1
     * buynow_id : null
     * buynow_attr : null
     * buynow_number : null
     * page_title : 提交订单
     * account_money : 1987.99
     * contact_name : null
     * contact_mobile : 13634146852
     * baby_name : null
     * ctl : cart
     * act : check
     * status : 1
     * city_name : 温州
     * return : 1
     * sess_id : 23nppjkhmpkg93pa4bve8hmve2
     * ref_uid : null
     */

    private int user_login_status;
    private CartListGroupBean cart_list_group;
    private TotalDataBean total_data;
    private int is_score;
    private int is_delivery;
    private int consignee_count;
    private ConsigneeInfoBean consignee_info;
    private int is_coupon;
    private int show_payment;
    private int has_ecv;
    private int has_account;
    private Object buynow_id;
    private Object buynow_attr;
    private Object buynow_number;
    private String page_title;
    private double account_money;
    private Object contact_name;
    private String contact_mobile;
    private Object baby_name;
    private String ctl;
    private String act;
    private int status;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;
    private List<CartListBean> cart_list;
    private List<List<List<String>>> delivery_time;
    private List<DeliveryListBean> delivery_list;
    private List<?> voucher_list;

    public int getUser_login_status() {
        return user_login_status;
    }

    public void setUser_login_status(int user_login_status) {
        this.user_login_status = user_login_status;
    }

    public CartListGroupBean getCart_list_group() {
        return cart_list_group;
    }

    public void setCart_list_group(CartListGroupBean cart_list_group) {
        this.cart_list_group = cart_list_group;
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

    public int getIs_delivery() {
        return is_delivery;
    }

    public void setIs_delivery(int is_delivery) {
        this.is_delivery = is_delivery;
    }
    public int getConsignee_count() {
        return consignee_count;
    }

    public void setConsignee_count(int consignee_count) {
        this.consignee_count = consignee_count;
    }

    public ConsigneeInfoBean getConsignee_info() {
        return consignee_info;
    }

    public void setConsignee_info(ConsigneeInfoBean consignee_info) {
        this.consignee_info = consignee_info;
    }

    public int getIs_coupon() {
        return is_coupon;
    }

    public void setIs_coupon(int is_coupon) {
        this.is_coupon = is_coupon;
    }

    public int getShow_payment() {
        return show_payment;
    }

    public void setShow_payment(int show_payment) {
        this.show_payment = show_payment;
    }

    public int getHas_ecv() {
        return has_ecv;
    }

    public void setHas_ecv(int has_ecv) {
        this.has_ecv = has_ecv;
    }

    public int getHas_account() {
        return has_account;
    }

    public void setHas_account(int has_account) {
        this.has_account = has_account;
    }

    public Object getBuynow_id() {
        return buynow_id;
    }

    public void setBuynow_id(Object buynow_id) {
        this.buynow_id = buynow_id;
    }

    public Object getBuynow_attr() {
        return buynow_attr;
    }

    public void setBuynow_attr(Object buynow_attr) {
        this.buynow_attr = buynow_attr;
    }

    public Object getBuynow_number() {
        return buynow_number;
    }

    public void setBuynow_number(Object buynow_number) {
        this.buynow_number = buynow_number;
    }

    public String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public double getAccount_money() {
        return account_money;
    }

    public void setAccount_money(double account_money) {
        this.account_money = account_money;
    }

    public Object getContact_name() {
        return contact_name;
    }

    public void setContact_name(Object contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public Object getBaby_name() {
        return baby_name;
    }

    public void setBaby_name(Object baby_name) {
        this.baby_name = baby_name;
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

    public List<CartListBean> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<CartListBean> cart_list) {
        this.cart_list = cart_list;
    }

    public List<List<List<String>>> getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(List<List<List<String>>> delivery_time) {
        this.delivery_time = delivery_time;
    }

    public List<DeliveryListBean> getDelivery_list() {
        return delivery_list;
    }

    public void setDelivery_list(List<DeliveryListBean> delivery_list) {
        this.delivery_list = delivery_list;
    }

    public List<?> getVoucher_list() {
        return voucher_list;
    }

    public void setVoucher_list(List<?> voucher_list) {
        this.voucher_list = voucher_list;
    }

    public static class CartListGroupBean extends BaseEntity{
        /**
         * sid_92 : {"goods_list":[{"id":"275","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.01,"number":"1","deal_id":"112","attr_str":"1瓶,1号","name":"53°飞天茅台500ml 3333","sub_name":"53°飞天茅台500ml 短名33333","max":100,"supplier_id":"92","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"},{"id":"276","return_score":0,"return_total_score":0,"unit_price":12,"total_price":12,"number":"1","deal_id":"190","attr_str":"","name":"53°飞天茅台500ml 11111111111","sub_name":"53°飞天茅台500ml 1111111","max":100,"supplier_id":"92","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"}],"supplier_id":"92","supplier":"519自营"}
         */

        private Sid92Bean sid_92;

        public Sid92Bean getSid_92() {
            return sid_92;
        }

        public void setSid_92(Sid92Bean sid_92) {
            this.sid_92 = sid_92;
        }

        public static class Sid92Bean extends BaseEntity {
            /**
             * goods_list : [{"id":"275","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.01,"number":"1","deal_id":"112","attr_str":"1瓶,1号","name":"53°飞天茅台500ml 3333","sub_name":"53°飞天茅台500ml 短名33333","max":100,"supplier_id":"92","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"},{"id":"276","return_score":0,"return_total_score":0,"unit_price":12,"total_price":12,"number":"1","deal_id":"190","attr_str":"","name":"53°飞天茅台500ml 11111111111","sub_name":"53°飞天茅台500ml 1111111","max":100,"supplier_id":"92","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"}]
             * supplier_id : 92
             * supplier : 519自营
             */

            private String supplier_id;
            private String supplier;
            private List<GoodsListBean> goods_list;

            public String getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(String supplier_id) {
                this.supplier_id = supplier_id;
            }

            public String getSupplier() {
                return supplier;
            }

            public void setSupplier(String supplier) {
                this.supplier = supplier;
            }

            public List<GoodsListBean> getGoods_list() {
                return goods_list;
            }

            public void setGoods_list(List<GoodsListBean> goods_list) {
                this.goods_list = goods_list;
            }

            public static class GoodsListBean extends BaseEntity {
                /**
                 * id : 275
                 * return_score : 0
                 * return_total_score : 0
                 * unit_price : 0.01
                 * total_price : 0.01
                 * number : 1
                 * deal_id : 112
                 * attr_str : 1瓶,1号
                 * name : 53°飞天茅台500ml 3333
                 * sub_name : 53°飞天茅台500ml 短名33333
                 * max : 100
                 * supplier_id : 92
                 * icon : http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg
                 */

                private String id;
                private int return_score;
                private int return_total_score;
                private double unit_price;
                private double total_price;
                private String number;
                private String deal_id;
                private String attr_str;
                private String name;
                private String sub_name;
                private int max;
                private String supplier_id;
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

                public String getSupplier_id() {
                    return supplier_id;
                }

                public void setSupplier_id(String supplier_id) {
                    this.supplier_id = supplier_id;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }
        }
    }

    public static class TotalDataBean extends BaseEntity {
        /**
         * total_price : 12.01
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

    public static class ConsigneeInfoBean extends BaseEntity {
        /**
         * id : 45
         * user_id : 90
         * region_lv1 : 1
         * region_lv2 : 2
         * region_lv3 : 52
         * region_lv4 : 509
         * address : 爱可可
         * mobile : 13634146852
         * zip :
         * consignee : 成功
         * is_default : 1
         * region_lv1_name : 中国
         * region_lv2_name : 北京
         * region_lv3_name : 北京
         * region_lv4_name : 门头沟区
         */

        private String id;
        private String user_id;
        private String region_lv1;
        private String region_lv2;
        private String region_lv3;
        private String region_lv4;
        private String address;
        private String mobile;
        private String zip;
        private String consignee;
        private String is_default;
        private String region_lv1_name;
        private String region_lv2_name;
        private String region_lv3_name;
        private String region_lv4_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getRegion_lv1() {
            return region_lv1;
        }

        public void setRegion_lv1(String region_lv1) {
            this.region_lv1 = region_lv1;
        }

        public String getRegion_lv2() {
            return region_lv2;
        }

        public void setRegion_lv2(String region_lv2) {
            this.region_lv2 = region_lv2;
        }

        public String getRegion_lv3() {
            return region_lv3;
        }

        public void setRegion_lv3(String region_lv3) {
            this.region_lv3 = region_lv3;
        }

        public String getRegion_lv4() {
            return region_lv4;
        }

        public void setRegion_lv4(String region_lv4) {
            this.region_lv4 = region_lv4;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getRegion_lv1_name() {
            return region_lv1_name;
        }

        public void setRegion_lv1_name(String region_lv1_name) {
            this.region_lv1_name = region_lv1_name;
        }

        public String getRegion_lv2_name() {
            return region_lv2_name;
        }

        public void setRegion_lv2_name(String region_lv2_name) {
            this.region_lv2_name = region_lv2_name;
        }

        public String getRegion_lv3_name() {
            return region_lv3_name;
        }

        public void setRegion_lv3_name(String region_lv3_name) {
            this.region_lv3_name = region_lv3_name;
        }

        public String getRegion_lv4_name() {
            return region_lv4_name;
        }

        public void setRegion_lv4_name(String region_lv4_name) {
            this.region_lv4_name = region_lv4_name;
        }
    }

    public static class CartListBean extends BaseEntity {
        /**
         * id : 275
         * return_score : 0
         * return_total_score : 0
         * unit_price : 0.01
         * total_price : 0.01
         * number : 1
         * deal_id : 112
         * attr_str : 1瓶,1号
         * name : 53°飞天茅台500ml 3333
         * sub_name : 53°飞天茅台500ml 短名33333
         * max : 100
         * supplier_id : 92
         * icon : http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg
         */

        private String id;
        private int return_score;
        private int return_total_score;
        private double unit_price;
        private double total_price;
        private String number;
        private String deal_id;
        private String attr_str;
        private String name;
        private String sub_name;
        private int max;
        private String supplier_id;
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

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class DeliveryListBean extends BaseEntity {
        /**
         * id : 8
         * name : 顺风快递
         * description : 顺风快递,福州地区2元
         * first_fee : 5.00
         * allow_default : 1
         * sort : 1
         * first_weight : 0.00
         * continue_weight : 0.00
         * continue_fee : 0.00
         * weight_id : 1
         * is_effect : 1
         */

        private String id;
        private String name;
        private String description;
        private String first_fee;
        private String allow_default;
        private String sort;
        private String first_weight;
        private String continue_weight;
        private String continue_fee;
        private String weight_id;
        private String is_effect;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFirst_fee() {
            return first_fee;
        }

        public void setFirst_fee(String first_fee) {
            this.first_fee = first_fee;
        }

        public String getAllow_default() {
            return allow_default;
        }

        public void setAllow_default(String allow_default) {
            this.allow_default = allow_default;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getFirst_weight() {
            return first_weight;
        }

        public void setFirst_weight(String first_weight) {
            this.first_weight = first_weight;
        }

        public String getContinue_weight() {
            return continue_weight;
        }

        public void setContinue_weight(String continue_weight) {
            this.continue_weight = continue_weight;
        }

        public String getContinue_fee() {
            return continue_fee;
        }

        public void setContinue_fee(String continue_fee) {
            this.continue_fee = continue_fee;
        }

        public String getWeight_id() {
            return weight_id;
        }

        public void setWeight_id(String weight_id) {
            this.weight_id = weight_id;
        }

        public String getIs_effect() {
            return is_effect;
        }

        public void setIs_effect(String is_effect) {
            this.is_effect = is_effect;
        }
    }
}
