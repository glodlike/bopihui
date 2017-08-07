package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/10 0010.
 */

public class OrderListInfo extends BaseEntity {

    /**
     * user_login_status : 1
     * item : [{"id":"439","order_sn":"2016121005393299","order_status":"0","pay_status":"2","delivery_status":"0","refund_status":"0","create_time":"2016-12-10 17:39:32","pay_amount":0.01,"total_price":0.01,"items_refund_status":1,"deal_order_item":[{"id":"467","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"大,1号","number":"1","order_id":"439","unit_price":0.01,"total_price":0.01,"consume_count":1,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":1,"keyi_dianpin":false,"status":"已付款"},{"id":"438","order_sn":"2016121005260694","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-10 17:26:06","pay_amount":0,"total_price":94,"items_refund_status":1,"deal_order_item":[{"id":"466","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"","number":"1","order_id":"438","unit_price":89,"total_price":89,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":0,"keyi_dianpin":false,"status":"未付款"},{"id":"435","order_sn":"2016120910055157","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-09 10:05:51","pay_amount":0,"total_price":183,"items_refund_status":1,"deal_order_item":[{"id":"463","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"","number":"2","order_id":"435","unit_price":89,"total_price":178,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":2,"consume_count":0,"keyi_dianpin":false,"status":"未付款"},{"id":"434","order_sn":"2016120910002135","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-09 10:00:21","pay_amount":0,"total_price":94,"items_refund_status":1,"deal_order_item":[{"id":"462","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"","number":"1","order_id":"434","unit_price":89,"total_price":89,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":0,"keyi_dianpin":false,"status":"未付款"},{"id":"433","order_sn":"2016120909575324","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-09 09:57:53","pay_amount":0,"total_price":94,"items_refund_status":1,"deal_order_item":[{"id":"461","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"","number":"1","order_id":"433","unit_price":89,"total_price":89,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":0,"keyi_dianpin":false,"status":"未付款"},{"id":"431","order_sn":"2016120909504927","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-09 09:50:49","pay_amount":0,"total_price":119,"items_refund_status":1,"deal_order_item":[{"id":"459","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"小,1号","number":"1","order_id":"431","unit_price":114,"total_price":114,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":0,"keyi_dianpin":false,"status":"未付款"},{"id":"430","order_sn":"2016120909503668","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-09 09:50:36","pay_amount":0,"total_price":119,"items_refund_status":1,"deal_order_item":[{"id":"458","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"小,1号","number":"1","order_id":"430","unit_price":114,"total_price":114,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":0,"keyi_dianpin":false,"status":"未付款"},{"id":"429","order_sn":"2016120909483471","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-09 09:48:34","pay_amount":0,"total_price":119,"items_refund_status":1,"deal_order_item":[{"id":"457","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"小,1号","number":"1","order_id":"429","unit_price":114,"total_price":114,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":0,"keyi_dianpin":false,"status":"未付款"},{"id":"428","order_sn":"2016120909445236","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-09 09:44:52","pay_amount":0,"total_price":119,"items_refund_status":1,"deal_order_item":[{"id":"456","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"小,1号","number":"1","order_id":"428","unit_price":114,"total_price":114,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":0,"keyi_dianpin":false,"status":"未付款"},{"id":"427","order_sn":"2016120909243314","order_status":"0","pay_status":"0","delivery_status":"0","refund_status":"0","create_time":"2016-12-09 09:24:33","pay_amount":0,"total_price":124,"items_refund_status":1,"deal_order_item":[{"id":"455","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"大,1号","number":"1","order_id":"427","unit_price":119,"total_price":119,"consume_count":0,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}],"c":1,"consume_count":0,"keyi_dianpin":false,"status":"未付款"}]
     * page : {"page":1,"page_total":7,"page_size":10,"data_total":"65"}
     * status : 1
     * page_title : 全部订单
     * ctl : uc_order
     * act : index
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : pra6sc8dh4bn9b23j6dovpfpj1
     * ref_uid : null
     */

    private int user_login_status;
    private PageBean page;
    private int status;
    private String page_title;
    private String ctl;
    private String act;
    private String info;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;
    private List<ItemBean> item;

    public int getUser_login_status() {
        return user_login_status;
    }

    public void setUser_login_status(int user_login_status) {
        this.user_login_status = user_login_status;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class PageBean extends BaseEntity {
        /**
         * page : 1
         * page_total : 7
         * page_size : 10
         * data_total : 65
         */

        private int page;
        private int page_total;
        private int page_size;
        private String data_total;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getPage_total() {
            return page_total;
        }

        public void setPage_total(int page_total) {
            this.page_total = page_total;
        }

        public int getPage_size() {
            return page_size;
        }

        public void setPage_size(int page_size) {
            this.page_size = page_size;
        }

        public String getData_total() {
            return data_total;
        }

        public void setData_total(String data_total) {
            this.data_total = data_total;
        }
    }

    public static class ItemBean  extends BaseEntity{
        /**
         * id : 439
         * order_sn : 2016121005393299
         * order_status : 0
         * pay_status : 2
         * delivery_status : 0
         * refund_status : 0
         * create_time : 2016-12-10 17:39:32
         * pay_amount : 0.01
         * total_price : 0.01
         * items_refund_status : 1
         * deal_order_item : [{"id":"467","deal_id":"189","deal_icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","attr_str":"大,1号","number":"1","order_id":"439","unit_price":0.01,"total_price":0.01,"consume_count":1,"dp_id":0,"delivery_status":0,"is_arrival":0,"is_refund":1,"refund_status":0}]
         * c : 1
         * consume_count : 1
         * keyi_dianpin : false
         * status : 已付款
         */

        private String id;
        private String order_sn;
        private String order_status;
        private String pay_status;
        private String delivery_status;
        private String refund_status;
        private String create_time;
        private double pay_amount;
        private double total_price;
        private int items_refund_status;
        private int c;
        private int consume_count;
        private boolean keyi_dianpin;
        private String status;
        private List<DealOrderItemBean> deal_order_item;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getDelivery_status() {
            return delivery_status;
        }

        public void setDelivery_status(String delivery_status) {
            this.delivery_status = delivery_status;
        }

        public String getRefund_status() {
            return refund_status;
        }

        public void setRefund_status(String refund_status) {
            this.refund_status = refund_status;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public double getPay_amount() {
            return pay_amount;
        }

        public void setPay_amount(double pay_amount) {
            this.pay_amount = pay_amount;
        }

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public int getItems_refund_status() {
            return items_refund_status;
        }

        public void setItems_refund_status(int items_refund_status) {
            this.items_refund_status = items_refund_status;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public int getConsume_count() {
            return consume_count;
        }

        public void setConsume_count(int consume_count) {
            this.consume_count = consume_count;
        }

        public boolean isKeyi_dianpin() {
            return keyi_dianpin;
        }

        public void setKeyi_dianpin(boolean keyi_dianpin) {
            this.keyi_dianpin = keyi_dianpin;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<DealOrderItemBean> getDeal_order_item() {
            return deal_order_item;
        }

        public void setDeal_order_item(List<DealOrderItemBean> deal_order_item) {
            this.deal_order_item = deal_order_item;
        }

        public static class DealOrderItemBean extends BaseEntity{
            /**
             * id : 467
             * deal_id : 189
             * deal_icon : http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg
             * name : 五粮液五粮液五粮液 2222
             * sub_name : 五粮液短名 2222
             * attr_str : 大,1号
             * number : 1
             * order_id : 439
             * unit_price : 0.01
             * total_price : 0.01
             * consume_count : 1
             * dp_id : 0
             * delivery_status : 0
             * is_arrival : 0
             * is_refund : 1
             * refund_status : 0
             */

            private String id;
            private String deal_id;
            private String deal_icon;
            private String name;
            private String sub_name;
            private String attr_str;
            private String number;
            private String order_id;
            private double unit_price;
            private double total_price;
            private int consume_count;
            private int dp_id;
            private int delivery_status;
            private int is_arrival;
            private int is_refund;
            private int refund_status;

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

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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
        }
    }
}
