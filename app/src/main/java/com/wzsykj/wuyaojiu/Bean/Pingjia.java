package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class Pingjia extends BaseEntity {


    /**
     * user_login_status : 1
     * item : [{"id":"23","create_time":"2016-12-13","content":"好喝","reply_content":"","point_percent":60,"point":"3","user_name":"新用户_91","images":[],"oimages":[]},{"id":"24","create_time":"2016-12-13","content":"","reply_content":"","point_percent":0,"point":"0","user_name":"新用户_91","images":[],"oimages":[]},{"id":"22","create_time":"2016-12-13","content":"","reply_content":"","point_percent":100,"point":"5","user_name":"新用户_91","images":[],"oimages":[]},{"id":"21","create_time":"2016-11-12","content":"ghfff","reply_content":"","point_percent":80,"point":"4","user_name":"test","images":[],"oimages":[]},{"id":"20","create_time":"2016-11-07","content":"","reply_content":"","point_percent":60,"point":"3","user_name":"test","images":[],"oimages":[]},{"id":"19","create_time":"2016-10-22","content":"","reply_content":"","point_percent":60,"point":"3","user_name":"test","images":[],"oimages":[]},{"id":"18","create_time":"2016-10-22","content":"","reply_content":"","point_percent":100,"point":"5","user_name":"test","images":[],"oimages":[]},{"id":"14","create_time":"2016-10-22","content":"bbbbb","reply_content":"","point_percent":100,"point":"5","user_name":"test","images":[],"oimages":[]}]
     * message_count : 8
     * name : 五粮液五粮液五粮液 2222
     * star_1 : 0
     * star_2 : 0
     * star_3 : 3
     * star_4 : 1
     * star_5 : 3
     * star_dp_width_1 : 0
     * star_dp_width_2 : 0
     * star_dp_width_3 : 37.5
     * star_dp_width_4 : 12.5
     * star_dp_width_5 : 37.5
     * buy_dp_sum : 8
     * buy_dp_avg : 3.5
     * buy_dp_width : 70
     * page : {"page":1,"page_total":1,"page_size":10,"data_total":"8"}
     * allow_dp : 1
     * type : deal
     * data_id : 189
     * page_title : 点评列表
     * ctl : dp
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : q59g5cqac8umqhfd5lt3g4pg62
     * ref_uid : null
     */

    private int user_login_status;
    private String message_count;
    private String name;
    private String star_1;
    private String star_2;
    private String star_3;
    private String star_4;
    private String star_5;
    private int star_dp_width_1;
    private int star_dp_width_2;
    private double star_dp_width_3;
    private double star_dp_width_4;
    private double star_dp_width_5;
    private String buy_dp_sum;
    private double buy_dp_avg;
    private int buy_dp_width;
    private PageBean page;
    private int allow_dp;
    private String type;
    private int data_id;
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
    private List<ItemBean> item;

    public int getUser_login_status() {
        return user_login_status;
    }

    public void setUser_login_status(int user_login_status) {
        this.user_login_status = user_login_status;
    }

    public String getMessage_count() {
        return message_count;
    }

    public void setMessage_count(String message_count) {
        this.message_count = message_count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar_1() {
        return star_1;
    }

    public void setStar_1(String star_1) {
        this.star_1 = star_1;
    }

    public String getStar_2() {
        return star_2;
    }

    public void setStar_2(String star_2) {
        this.star_2 = star_2;
    }

    public String getStar_3() {
        return star_3;
    }

    public void setStar_3(String star_3) {
        this.star_3 = star_3;
    }

    public String getStar_4() {
        return star_4;
    }

    public void setStar_4(String star_4) {
        this.star_4 = star_4;
    }

    public String getStar_5() {
        return star_5;
    }

    public void setStar_5(String star_5) {
        this.star_5 = star_5;
    }

    public int getStar_dp_width_1() {
        return star_dp_width_1;
    }

    public void setStar_dp_width_1(int star_dp_width_1) {
        this.star_dp_width_1 = star_dp_width_1;
    }

    public int getStar_dp_width_2() {
        return star_dp_width_2;
    }

    public void setStar_dp_width_2(int star_dp_width_2) {
        this.star_dp_width_2 = star_dp_width_2;
    }

    public double getStar_dp_width_3() {
        return star_dp_width_3;
    }

    public void setStar_dp_width_3(double star_dp_width_3) {
        this.star_dp_width_3 = star_dp_width_3;
    }

    public double getStar_dp_width_4() {
        return star_dp_width_4;
    }

    public void setStar_dp_width_4(double star_dp_width_4) {
        this.star_dp_width_4 = star_dp_width_4;
    }

    public double getStar_dp_width_5() {
        return star_dp_width_5;
    }

    public void setStar_dp_width_5(double star_dp_width_5) {
        this.star_dp_width_5 = star_dp_width_5;
    }

    public String getBuy_dp_sum() {
        return buy_dp_sum;
    }

    public void setBuy_dp_sum(String buy_dp_sum) {
        this.buy_dp_sum = buy_dp_sum;
    }

    public double getBuy_dp_avg() {
        return buy_dp_avg;
    }

    public void setBuy_dp_avg(double buy_dp_avg) {
        this.buy_dp_avg = buy_dp_avg;
    }

    public int getBuy_dp_width() {
        return buy_dp_width;
    }

    public void setBuy_dp_width(int buy_dp_width) {
        this.buy_dp_width = buy_dp_width;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public int getAllow_dp() {
        return allow_dp;
    }

    public void setAllow_dp(int allow_dp) {
        this.allow_dp = allow_dp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
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

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public static class PageBean {
        /**
         * page : 1
         * page_total : 1
         * page_size : 10
         * data_total : 8
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

    public static class ItemBean {
        /**
         * id : 23
         * create_time : 2016-12-13
         * content : 好喝
         * reply_content :
         * point_percent : 60
         * point : 3
         * user_name : 新用户_91
         * images : []
         * oimages : []
         */

        private String id;
        private String create_time;
        private String content;
        private String reply_content;
        private int point_percent;
        private String point;
        private String user_name;
        private List<?> images;
        private List<?> oimages;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getReply_content() {
            return reply_content;
        }

        public void setReply_content(String reply_content) {
            this.reply_content = reply_content;
        }

        public int getPoint_percent() {
            return point_percent;
        }

        public void setPoint_percent(int point_percent) {
            this.point_percent = point_percent;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public List<?> getImages() {
            return images;
        }

        public void setImages(List<?> images) {
            this.images = images;
        }

        public List<?> getOimages() {
            return oimages;
        }

        public void setOimages(List<?> oimages) {
            this.oimages = oimages;
        }
    }
}
