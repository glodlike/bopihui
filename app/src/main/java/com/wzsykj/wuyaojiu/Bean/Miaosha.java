package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/26 0026.
 */

public class Miaosha extends BaseEntity {


    /**
     * nowtime : 1482711438
     * bid : null
     * cate_id : null
     * city_id : 22
     * page_title : 限时折扣
     * page : {"page":null,"page_total":null,"page_size":null,"data_total":null}
     * qianggou_deal_list : [{"id":"21","distance":0,"ypoint":27.991796,"xpoint":120.666231,"name":"唯德 红橙小麦白啤酒 330mlX24瓶","sub_name":"唯德 红橙小麦白啤酒 330mlX24瓶","brief":"原产国：德国 。  原料与辅料：小麦白啤酒（水 、小麦麦芽 、啤酒花  、酵母 ） 、红橙汁、阿斯巴甜（含苯丙氨酸）、抗坏血酸  。  酒精度 ：2.6 %vol ，原麦汁浓度 ：6.0 P 。  贮藏方式：常温 ，避光保存  。保质期：12个月 。","buy_count":"0","current_price":288,"origin_price":384,"icon":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522e5fda8dc.JPG","image":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522e6f8c8ca_280x210.jpg","end_time_format":"2016-12-31 11:47:28","begin_time_format":"2016-12-12 11:47:25","begin_time":"1481485645","end_time":"1483127248","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0,"buyper":0,"max_bought":"100"},{"id":"20","distance":0,"ypoint":27.991796,"xpoint":120.666231,"name":"泰谷啤酒（易拉罐）250ml*24罐","sub_name":"泰谷啤酒（易拉罐）250ml*24罐","brief":"原产国：西班牙 。 原料与辅料：水 、大麦麦芽 、玉米 、大麦 、啤酒花 。 酒精度 ：4.8%vol ，原麦汁浓度 ：10.7 P 。 贮藏条件：置于阴凉处 。 保质期：18个月 。","buy_count":"32","current_price":96,"origin_price":120,"icon":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522ddadf43e.JPG","image":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522de56d758_280x210.jpg","end_time_format":"2016-12-31 10:04:00","begin_time_format":"2016-12-20 10:04:04","begin_time":"1482170644","end_time":"1483121040","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0,"buyper":26,"max_bought":"89"},{"id":"19","distance":0,"ypoint":27.991796,"xpoint":120.666231,"name":"费尔德堡黑啤酒500mlX24罐","sub_name":"费尔德堡黑啤酒500mlX24罐","brief":"原产国：德国 。  原料与辅料：水 、大麦芽 、啤酒花 。  酒精度 ：5.0%vol ，原麦汁浓度 ：11.2 P 。  贮藏条件：常温保存 ，置于阴凉处  。","buy_count":"0","current_price":96,"origin_price":180,"icon":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522cd5c593d.JPG","image":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522cdfabaa1_280x210.jpg","end_time_format":"2016-12-31 11:47:39","begin_time_format":"2016-12-05 11:47:36","begin_time":"1480880856","end_time":"1483127259","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0,"buyper":0,"max_bought":"100"},{"id":"18","distance":0,"ypoint":27.991796,"xpoint":120.666231,"name":"费尔德皮尔森啤酒500mlX24罐","sub_name":"费尔德皮尔森啤酒500mlX24罐","brief":"原产国：德国 。  原料与辅料：水 、小麦芽、啤酒花 。  酒精度 ：4.9%vol ，原麦汁浓度 ：11.2 P 。  贮藏条件：常温避光保存 ，置于阴凉干燥处  。","buy_count":"4","current_price":69.6,"origin_price":180,"icon":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522c43be1df.JPG","image":"http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522c6940cf4_280x210.jpg","end_time_format":"2016-12-31 11:47:51","begin_time_format":"2016-12-13 11:47:48","begin_time":"1481572068","end_time":"1483127271","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0,"buyper":4,"max_bought":"96"}]
     * ctl : miaosha
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : eolksgef3vm9g025a9di7bnis0
     * ref_uid : null
     */

    private int nowtime;
    private Object bid;
    private Object cate_id;
    private int city_id;
    private String page_title;
    private PageBean page;
    private String ctl;
    private String act;
    private int status;
    private String info;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;
    private List<QianggouDealListBean> qianggou_deal_list;

    public int getNowtime() {
        return nowtime;
    }

    public void setNowtime(int nowtime) {
        this.nowtime = nowtime;
    }

    public Object getBid() {
        return bid;
    }

    public void setBid(Object bid) {
        this.bid = bid;
    }

    public Object getCate_id() {
        return cate_id;
    }

    public void setCate_id(Object cate_id) {
        this.cate_id = cate_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getPage_title() {
        return page_title;
    }

    public void setPage_title(String page_title) {
        this.page_title = page_title;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
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

    public List<QianggouDealListBean> getQianggou_deal_list() {
        return qianggou_deal_list;
    }

    public void setQianggou_deal_list(List<QianggouDealListBean> qianggou_deal_list) {
        this.qianggou_deal_list = qianggou_deal_list;
    }

    public static class PageBean extends  BaseEntity{
        /**
         * page : null
         * page_total : null
         * page_size : null
         * data_total : null
         */

        private Object page;
        private Object page_total;
        private Object page_size;
        private Object data_total;

        public Object getPage() {
            return page;
        }

        public void setPage(Object page) {
            this.page = page;
        }

        public Object getPage_total() {
            return page_total;
        }

        public void setPage_total(Object page_total) {
            this.page_total = page_total;
        }

        public Object getPage_size() {
            return page_size;
        }

        public void setPage_size(Object page_size) {
            this.page_size = page_size;
        }

        public Object getData_total() {
            return data_total;
        }

        public void setData_total(Object data_total) {
            this.data_total = data_total;
        }
    }

    public static class QianggouDealListBean extends BaseEntity{
        /**
         * id : 21
         * distance : 0
         * ypoint : 27.991796
         * xpoint : 120.666231
         * name : 唯德 红橙小麦白啤酒 330mlX24瓶
         * sub_name : 唯德 红橙小麦白啤酒 330mlX24瓶
         * brief : 原产国：德国 。  原料与辅料：小麦白啤酒（水 、小麦麦芽 、啤酒花  、酵母 ） 、红橙汁、阿斯巴甜（含苯丙氨酸）、抗坏血酸  。  酒精度 ：2.6 %vol ，原麦汁浓度 ：6.0 P 。  贮藏方式：常温 ，避光保存  。保质期：12个月 。
         * buy_count : 0
         * current_price : 288
         * origin_price : 384
         * icon : http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522e5fda8dc.JPG
         * image : http://116.62.28.31/bopihui/public/attachment/201612/15/13/58522e6f8c8ca_280x210.jpg
         * end_time_format : 2016-12-31 11:47:28
         * begin_time_format : 2016-12-12 11:47:25
         * begin_time : 1481485645
         * end_time : 1483127248
         * time_status : 1
         * auto_order : 0
         * is_lottery : 0
         * is_refund : 1
         * deal_score : 0
         * buyin_app : 0
         * is_today : 0
         * buyper : 0
         * max_bought : 100
         */

        private String id;
        private int distance;
        private double ypoint;
        private double xpoint;
        private String name;
        private String sub_name;
        private String brief;
        private String buy_count;
        private int current_price;
        private int origin_price;
        private String icon;
        private String image;
        private String end_time_format;
        private String begin_time_format;
        private String begin_time;
        private String end_time;
        private String time_status;
        private String auto_order;
        private String is_lottery;
        private String is_refund;
        private int deal_score;
        private int buyin_app;
        private int is_today;
        private int buyper;
        private String max_bought;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public double getYpoint() {
            return ypoint;
        }

        public void setYpoint(double ypoint) {
            this.ypoint = ypoint;
        }

        public double getXpoint() {
            return xpoint;
        }

        public void setXpoint(double xpoint) {
            this.xpoint = xpoint;
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

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getBuy_count() {
            return buy_count;
        }

        public void setBuy_count(String buy_count) {
            this.buy_count = buy_count;
        }

        public int getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(int current_price) {
            this.current_price = current_price;
        }

        public int getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(int origin_price) {
            this.origin_price = origin_price;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getEnd_time_format() {
            return end_time_format;
        }

        public void setEnd_time_format(String end_time_format) {
            this.end_time_format = end_time_format;
        }

        public String getBegin_time_format() {
            return begin_time_format;
        }

        public void setBegin_time_format(String begin_time_format) {
            this.begin_time_format = begin_time_format;
        }

        public String getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(String begin_time) {
            this.begin_time = begin_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getTime_status() {
            return time_status;
        }

        public void setTime_status(String time_status) {
            this.time_status = time_status;
        }

        public String getAuto_order() {
            return auto_order;
        }

        public void setAuto_order(String auto_order) {
            this.auto_order = auto_order;
        }

        public String getIs_lottery() {
            return is_lottery;
        }

        public void setIs_lottery(String is_lottery) {
            this.is_lottery = is_lottery;
        }

        public String getIs_refund() {
            return is_refund;
        }

        public void setIs_refund(String is_refund) {
            this.is_refund = is_refund;
        }

        public int getDeal_score() {
            return deal_score;
        }

        public void setDeal_score(int deal_score) {
            this.deal_score = deal_score;
        }

        public int getBuyin_app() {
            return buyin_app;
        }

        public void setBuyin_app(int buyin_app) {
            this.buyin_app = buyin_app;
        }

        public int getIs_today() {
            return is_today;
        }

        public void setIs_today(int is_today) {
            this.is_today = is_today;
        }

        public int getBuyper() {
            return buyper;
        }

        public void setBuyper(int buyper) {
            this.buyper = buyper;
        }

        public String getMax_bought() {
            return max_bought;
        }

        public void setMax_bought(String max_bought) {
            this.max_bought = max_bought;
        }
    }
}
