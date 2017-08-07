package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class CollectList extends BaseEntity {


    /**
     * user_login_status : 1
     * goods_list : [{"id":"112","cid":"269","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","sub_name":"53°飞天茅台500ml 短名33333","origin_price":"1000.00","current_price":"69.00","buy_count":"11","brief":"酒精度：53%vol、\r\n规格：500ml*6、\r\n产地：贵州、\r\n（两瓶起免费配送）、"},{"id":"191","cid":"268","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","sub_name":"茅台4444","origin_price":"100.00","current_price":"80.00","buy_count":"0","brief":"茅台4444"},{"id":"190","cid":"267","icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","sub_name":"53°飞天茅台500ml 1111111","origin_price":"15.00","current_price":"12.00","buy_count":"4","brief":""}]
     * page : {"page":1,"page_total":1,"page_size":10,"data_total":"3"}
     * page_title : 收藏夹
     * ctl : uc_collect
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : ei1358prps2etj4iv9ene8eco7
     * ref_uid : null
     */

    private int user_login_status;
    private PageBean page;
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
    private List<GoodsListBean> goods_list;

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

    public List<GoodsListBean> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(List<GoodsListBean> goods_list) {
        this.goods_list = goods_list;
    }

    public static class PageBean  extends  BaseEntity{
        /**
         * page : 1
         * page_total : 1
         * page_size : 10
         * data_total : 3
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

    public static class GoodsListBean  extends  BaseEntity{
        /**
         * id : 112
         * cid : 269
         * icon : http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg
         * sub_name : 53°飞天茅台500ml 短名33333
         * origin_price : 1000.00
         * current_price : 69.00
         * buy_count : 11
         * brief : 酒精度：53%vol、
         规格：500ml*6、
         产地：贵州、
         （两瓶起免费配送）、
         */

        private String id;
        private String cid;
        private String icon;
        private String sub_name;
        private String origin_price;
        private String current_price;
        private String buy_count;
        private String brief;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSub_name() {
            return sub_name;
        }

        public void setSub_name(String sub_name) {
            this.sub_name = sub_name;
        }

        public String getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(String origin_price) {
            this.origin_price = origin_price;
        }

        public String getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(String current_price) {
            this.current_price = current_price;
        }

        public String getBuy_count() {
            return buy_count;
        }

        public void setBuy_count(String buy_count) {
            this.buy_count = buy_count;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }
    }
}
