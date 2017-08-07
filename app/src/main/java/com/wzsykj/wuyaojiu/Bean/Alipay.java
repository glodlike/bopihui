package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class Alipay extends BaseEntity {


    /**
     * payment_code : {"pay_info":"五粮液五粮液五粮液 2222","payment_name":"支付宝","pay_money":0.02,"class_name":"Malipay","config":{"subject":"2016122002385927","body":"五粮液五粮液五粮液 2222","total_fee":0.02,"total_fee_format":"¥0.02","out_trade_no":"2016122002400755","notify_url":"http://v2.519wz.cn/callback/payment/aliapp_notify.php","payment_type":1,"service":"mobile.securitypay.pay","_input_charset":"utf-8","partner":"2088021233320460","seller_id":"wuyaojiu519@126.com","order_spec":"partner=\"2088021233320460\"&seller_id=\"wuyaojiu519@126.com\"&out_trade_no=\"2016122002400755\"&subject=\"2016122002385927\"&body=\"五粮液五粮液五粮液 2222\"&total_fee=\"0.02\"¬ify_url=\"http://v2.519wz.cn/callback/payment/aliapp_notify.php\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"","sign":"k1pn7Ar5fl0sgsZo5tcX%2FG1GQ8fGEqKWQLdtV6CbjHMGNVAUdJhAkILu%2FmjzaLAAAIVC5fVVIVepfbc%2Fdp%2FQY%2BNsX7xCGimiatKPI39tMWp9eUlpZ87%2BWciKMSH0VzBRL%2BZQWl0dgvtrk7Z1nJ%2FyWQiepUyOxGQ7dAIi1WsrfE0%3D","sign_type":"RSA"}}
     * ctl : payment
     * act : get_payment_code
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : 90hs5egs4lcqvr2r8uaooh5sm2
     * ref_uid : null
     */

    private PaymentCodeBean payment_code;
    private String ctl;
    private String act;
    private int status;
    private String info;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;

    public PaymentCodeBean getPayment_code() {
        return payment_code;
    }

    public void setPayment_code(PaymentCodeBean payment_code) {
        this.payment_code = payment_code;
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

    public static class PaymentCodeBean {
        /**
         * pay_info : 五粮液五粮液五粮液 2222
         * payment_name : 支付宝
         * pay_money : 0.02
         * class_name : Malipay
         * config : {"subject":"2016122002385927","body":"五粮液五粮液五粮液 2222","total_fee":0.02,"total_fee_format":"¥0.02","out_trade_no":"2016122002400755","notify_url":"http://v2.519wz.cn/callback/payment/aliapp_notify.php","payment_type":1,"service":"mobile.securitypay.pay","_input_charset":"utf-8","partner":"2088021233320460","seller_id":"wuyaojiu519@126.com","order_spec":"partner=\"2088021233320460\"&seller_id=\"wuyaojiu519@126.com\"&out_trade_no=\"2016122002400755\"&subject=\"2016122002385927\"&body=\"五粮液五粮液五粮液 2222\"&total_fee=\"0.02\"¬ify_url=\"http://v2.519wz.cn/callback/payment/aliapp_notify.php\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"","sign":"k1pn7Ar5fl0sgsZo5tcX%2FG1GQ8fGEqKWQLdtV6CbjHMGNVAUdJhAkILu%2FmjzaLAAAIVC5fVVIVepfbc%2Fdp%2FQY%2BNsX7xCGimiatKPI39tMWp9eUlpZ87%2BWciKMSH0VzBRL%2BZQWl0dgvtrk7Z1nJ%2FyWQiepUyOxGQ7dAIi1WsrfE0%3D","sign_type":"RSA"}
         */

        private String pay_info;
        private String payment_name;
        private double pay_money;
        private String class_name;
        private ConfigBean config;

        public String getPay_info() {
            return pay_info;
        }

        public void setPay_info(String pay_info) {
            this.pay_info = pay_info;
        }

        public String getPayment_name() {
            return payment_name;
        }

        public void setPayment_name(String payment_name) {
            this.payment_name = payment_name;
        }

        public double getPay_money() {
            return pay_money;
        }

        public void setPay_money(double pay_money) {
            this.pay_money = pay_money;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public ConfigBean getConfig() {
            return config;
        }

        public void setConfig(ConfigBean config) {
            this.config = config;
        }

        public static class ConfigBean {
            /**
             * subject : 2016122002385927
             * body : 五粮液五粮液五粮液 2222
             * total_fee : 0.02
             * total_fee_format : ¥0.02
             * out_trade_no : 2016122002400755
             * notify_url : http://v2.519wz.cn/callback/payment/aliapp_notify.php
             * payment_type : 1
             * service : mobile.securitypay.pay
             * _input_charset : utf-8
             * partner : 2088021233320460
             * seller_id : wuyaojiu519@126.com
             * order_spec : partner="2088021233320460"&seller_id="wuyaojiu519@126.com"&out_trade_no="2016122002400755"&subject="2016122002385927"&body="五粮液五粮液五粮液 2222"&total_fee="0.02"¬ify_url="http://v2.519wz.cn/callback/payment/aliapp_notify.php"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"
             * sign : k1pn7Ar5fl0sgsZo5tcX%2FG1GQ8fGEqKWQLdtV6CbjHMGNVAUdJhAkILu%2FmjzaLAAAIVC5fVVIVepfbc%2Fdp%2FQY%2BNsX7xCGimiatKPI39tMWp9eUlpZ87%2BWciKMSH0VzBRL%2BZQWl0dgvtrk7Z1nJ%2FyWQiepUyOxGQ7dAIi1WsrfE0%3D
             * sign_type : RSA
             */

            private String subject;
            private String body;
            private double total_fee;
            private String total_fee_format;
            private String out_trade_no;
            private String notify_url;
            private int payment_type;
            private String service;
            private String _input_charset;
            private String partner;
            private String seller_id;
            private String order_spec;
            private String sign;
            private String sign_type;

            public String getSubject() {
                return subject;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public double getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(double total_fee) {
                this.total_fee = total_fee;
            }

            public String getTotal_fee_format() {
                return total_fee_format;
            }

            public void setTotal_fee_format(String total_fee_format) {
                this.total_fee_format = total_fee_format;
            }

            public String getOut_trade_no() {
                return out_trade_no;
            }

            public void setOut_trade_no(String out_trade_no) {
                this.out_trade_no = out_trade_no;
            }

            public String getNotify_url() {
                return notify_url;
            }

            public void setNotify_url(String notify_url) {
                this.notify_url = notify_url;
            }

            public int getPayment_type() {
                return payment_type;
            }

            public void setPayment_type(int payment_type) {
                this.payment_type = payment_type;
            }

            public String getService() {
                return service;
            }

            public void setService(String service) {
                this.service = service;
            }

            public String get_input_charset() {
                return _input_charset;
            }

            public void set_input_charset(String _input_charset) {
                this._input_charset = _input_charset;
            }

            public String getPartner() {
                return partner;
            }

            public void setPartner(String partner) {
                this.partner = partner;
            }

            public String getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(String seller_id) {
                this.seller_id = seller_id;
            }

            public String getOrder_spec() {
                return order_spec;
            }

            public void setOrder_spec(String order_spec) {
                this.order_spec = order_spec;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getSign_type() {
                return sign_type;
            }

            public void setSign_type(String sign_type) {
                this.sign_type = sign_type;
            }
        }
    }
}
