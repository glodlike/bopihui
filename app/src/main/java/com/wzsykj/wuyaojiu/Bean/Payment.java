package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class Payment extends BaseEntity {
    /**
     * order_sn : 2016121704494346
     * order_id : 501
     * cart_list : [{"id":"540","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.01,"number":"1","deal_id":"189","attr_str":"","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","max":100,"supplier_id":"0","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"}]
     * cart_list_group : {"sid_0":{"goods_list":[{"id":"540","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.01,"number":"1","deal_id":"189","attr_str":"","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","max":100,"supplier_id":"0","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"}],"supplier_id":"0","supplier":"519直营"}}
     * total_price : 5.01
     * is_coupon : 0
     * show_payment : 1
     * payment_list : [{"id":"17","name":"余额支付 (￥49001)","code":"Account","logo":""},{"id":"24","code":"Aliapp","logo":"http://v2.519wz.cn/public/attachment/201601/14/16/569760490e7ab.png","name":"支付宝支付"},{"id":"25","code":"WxApp","logo":"http://v2.519wz.cn/public/attachment/201601/14/16/56976111b061c.png","name":"微信支付"}]
     * page_title : 收银台
     * account_fee : 0.00
     * ecv_fee : 0.00
     * delivery_fee : 5.00
     * discount_price : 0.00
     * pay_money : 5.01
     * ctl : payment
     * act : done
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : p8rgbeg1ouuo3d68cg1sc9td97
     * ref_uid : null
     */

    private String order_sn;
    private int order_id;
    private CartListGroupBean cart_list_group;
    private String total_price;
    private int is_coupon;
    private int show_payment;
    private String page_title;
    private String account_fee;
    private String ecv_fee;
    private String delivery_fee;
    private String discount_price;
    private double pay_money;
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
    private List<PaymentListBean> payment_list;

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public CartListGroupBean getCart_list_group() {
        return cart_list_group;
    }

    public void setCart_list_group(CartListGroupBean cart_list_group) {
        this.cart_list_group = cart_list_group;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
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

    public String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public String getAccount_fee() {
        return account_fee;
    }

    public void setAccount_fee(String account_fee) {
        this.account_fee = account_fee;
    }

    public String getEcv_fee() {
        return ecv_fee;
    }

    public void setEcv_fee(String ecv_fee) {
        this.ecv_fee = ecv_fee;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public double getPay_money() {
        return pay_money;
    }

    public void setPay_money(double pay_money) {
        this.pay_money = pay_money;
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

    public List<PaymentListBean> getPayment_list() {
        return payment_list;
    }

    public void setPayment_list(List<PaymentListBean> payment_list) {
        this.payment_list = payment_list;
    }

    public static class CartListGroupBean {
        /**
         * sid_0 : {"goods_list":[{"id":"540","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.01,"number":"1","deal_id":"189","attr_str":"","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","max":100,"supplier_id":"0","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"}],"supplier_id":"0","supplier":"519直营"}
         */

        private Sid0Bean sid_0;

        public Sid0Bean getSid_0() {
            return sid_0;
        }

        public void setSid_0(Sid0Bean sid_0) {
            this.sid_0 = sid_0;
        }

        public static class Sid0Bean {
            /**
             * goods_list : [{"id":"540","return_score":0,"return_total_score":0,"unit_price":0.01,"total_price":0.01,"number":"1","deal_id":"189","attr_str":"","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","max":100,"supplier_id":"0","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"}]
             * supplier_id : 0
             * supplier : 519直营
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

            public static class GoodsListBean {
                /**
                 * id : 540
                 * return_score : 0
                 * return_total_score : 0
                 * unit_price : 0.01
                 * total_price : 0.01
                 * number : 1
                 * deal_id : 189
                 * attr_str :
                 * name : 五粮液五粮液五粮液 2222
                 * sub_name : 五粮液短名 2222
                 * max : 100
                 * supplier_id : 0
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

    public static class CartListBean {
        /**
         * id : 540
         * return_score : 0
         * return_total_score : 0
         * unit_price : 0.01
         * total_price : 0.01
         * number : 1
         * deal_id : 189
         * attr_str :
         * name : 五粮液五粮液五粮液 2222
         * sub_name : 五粮液短名 2222
         * max : 100
         * supplier_id : 0
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

    public static class PaymentListBean {
        /**
         * id : 17
         * name : 余额支付 (￥49001)
         * code : Account
         * logo :
         */

        private String id;
        private String name;
        private String code;
        private String logo;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
    }
}
