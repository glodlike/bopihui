package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

/**
 * Created by Administrator on 2016/12/8 0008.
 */

public class WXinpay extends BaseEntity {

    /**
     * payment_code : {"pay_info":"五粮液五粮液五粮液 2222","payment_name":"微信支付","pay_money":0.01,"class_name":"WxApp","config":{"appid":"wxa4902dd8e786bb5f","noncestr":"iivyi7jp7ivfpow4o9o447k3pn3tb0es","package":"prepay_id=wx201612201444317603534fe10185239144","partnerid":"1288959501","prepayid":"wx201612201444317603534fe10185239144","timestamp":1482187471,"sign":"3540e0cea52575ed2a5b9124540f2b64","ios":{"appid":"wxa4902dd8e786bb5f","noncestr":"iivyi7jp7ivfpow4o9o447k3pn3tb0es","package":"Sign=Wxpay","partnerid":"1288959501","prepayid":"wx201612201444317603534fe10185239144","timestamp":1482187471,"sign":"e8fd77fce2f0ba4974f494c70783ee2c"},"packagevalue":"prepay_id=wx201612201444317603534fe10185239144","subject":"2016122002440970","body":"五粮液五粮液五粮液 2222","total_fee":0.01,"total_fee_format":"¥0.01","out_trade_no":"2016122002443133","notify_url":"http://v2.519wz.cn/callback/payment/wxapp_notify.php","key":"4wChgUuFZFCnVhYZhncCaYodxzrHtwyj","secret":"d4624c36b6795d1d99dcf0547af5443d"}}
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
         * payment_name : 微信支付
         * pay_money : 0.01
         * class_name : WxApp
         * config : {"appid":"wxa4902dd8e786bb5f","noncestr":"iivyi7jp7ivfpow4o9o447k3pn3tb0es","package":"prepay_id=wx201612201444317603534fe10185239144","partnerid":"1288959501","prepayid":"wx201612201444317603534fe10185239144","timestamp":1482187471,"sign":"3540e0cea52575ed2a5b9124540f2b64","ios":{"appid":"wxa4902dd8e786bb5f","noncestr":"iivyi7jp7ivfpow4o9o447k3pn3tb0es","package":"Sign=Wxpay","partnerid":"1288959501","prepayid":"wx201612201444317603534fe10185239144","timestamp":1482187471,"sign":"e8fd77fce2f0ba4974f494c70783ee2c"},"packagevalue":"prepay_id=wx201612201444317603534fe10185239144","subject":"2016122002440970","body":"五粮液五粮液五粮液 2222","total_fee":0.01,"total_fee_format":"¥0.01","out_trade_no":"2016122002443133","notify_url":"http://v2.519wz.cn/callback/payment/wxapp_notify.php","key":"4wChgUuFZFCnVhYZhncCaYodxzrHtwyj","secret":"d4624c36b6795d1d99dcf0547af5443d"}
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
             * appid : wxa4902dd8e786bb5f
             * noncestr : iivyi7jp7ivfpow4o9o447k3pn3tb0es
             * package : prepay_id=wx201612201444317603534fe10185239144
             * partnerid : 1288959501
             * prepayid : wx201612201444317603534fe10185239144
             * timestamp : 1482187471
             * sign : 3540e0cea52575ed2a5b9124540f2b64
             * ios : {"appid":"wxa4902dd8e786bb5f","noncestr":"iivyi7jp7ivfpow4o9o447k3pn3tb0es","package":"Sign=Wxpay","partnerid":"1288959501","prepayid":"wx201612201444317603534fe10185239144","timestamp":1482187471,"sign":"e8fd77fce2f0ba4974f494c70783ee2c"}
             * packagevalue : prepay_id=wx201612201444317603534fe10185239144
             * subject : 2016122002440970
             * body : 五粮液五粮液五粮液 2222
             * total_fee : 0.01
             * total_fee_format : ¥0.01
             * out_trade_no : 2016122002443133
             * notify_url : http://v2.519wz.cn/callback/payment/wxapp_notify.php
             * key : 4wChgUuFZFCnVhYZhncCaYodxzrHtwyj
             * secret : d4624c36b6795d1d99dcf0547af5443d
             */

            private String appid;
            private String noncestr;
            @SerializedName("package")
            private String packageX;
            private String partnerid;
            private String prepayid;
            private int timestamp;
            private String sign;
            private IosBean ios;
            private String packagevalue;
            private String subject;
            private String body;
            private double total_fee;
            private String total_fee_format;
            private String out_trade_no;
            private String notify_url;
            private String key;
            private String secret;

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public IosBean getIos() {
                return ios;
            }

            public void setIos(IosBean ios) {
                this.ios = ios;
            }

            public String getPackagevalue() {
                return packagevalue;
            }

            public void setPackagevalue(String packagevalue) {
                this.packagevalue = packagevalue;
            }

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

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }

            public static class IosBean {
                /**
                 * appid : wxa4902dd8e786bb5f
                 * noncestr : iivyi7jp7ivfpow4o9o447k3pn3tb0es
                 * package : Sign=Wxpay
                 * partnerid : 1288959501
                 * prepayid : wx201612201444317603534fe10185239144
                 * timestamp : 1482187471
                 * sign : e8fd77fce2f0ba4974f494c70783ee2c
                 */

                private String appid;
                private String noncestr;
                @SerializedName("package")
                private String packageX;
                private String partnerid;
                private String prepayid;
                private int timestamp;
                private String sign;

                public String getAppid() {
                    return appid;
                }

                public void setAppid(String appid) {
                    this.appid = appid;
                }

                public String getNoncestr() {
                    return noncestr;
                }

                public void setNoncestr(String noncestr) {
                    this.noncestr = noncestr;
                }

                public String getPackageX() {
                    return packageX;
                }

                public void setPackageX(String packageX) {
                    this.packageX = packageX;
                }

                public String getPartnerid() {
                    return partnerid;
                }

                public void setPartnerid(String partnerid) {
                    this.partnerid = partnerid;
                }

                public String getPrepayid() {
                    return prepayid;
                }

                public void setPrepayid(String prepayid) {
                    this.prepayid = prepayid;
                }

                public int getTimestamp() {
                    return timestamp;
                }

                public void setTimestamp(int timestamp) {
                    this.timestamp = timestamp;
                }

                public String getSign() {
                    return sign;
                }
                public void setSign(String sign) {
                    this.sign = sign;
                }
            }
        }
    }
}
