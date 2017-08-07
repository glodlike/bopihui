package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;
/**
 * Created by Administrator on 2016/12/7 0007.
 */
public class Address  extends BaseEntity{

    /**
     * user_login_status : 1
     * consignee_list : [{"id":"11","user_id":"91","region_lv1":"1","region_lv2":"31","region_lv3":"391","region_lv4":"3305","address":"牛山北路 50 号","mobile":"13634146852","zip":"","consignee":"爱可可","is_default":"0","region_lv1_name":"中国","region_lv2_name":"浙江","region_lv3_name":"温州","region_lv4_name":"鹿城区"},{"id":"12","user_id":"91","region_lv1":"1","region_lv2":"31","region_lv3":"383","region_lv4":"3229","address":"荷花苑","mobile":"15888738918","zip":"","consignee":"爱可可","is_default":"1","region_lv1_name":"中国","region_lv2_name":"浙江","region_lv3_name":"杭州","region_lv4_name":"西湖区"}]
     * page_title : 配送地址列表
     * ctl : uc_address
     * act : index
     * status : 1
     * info :
     * city_name : 福鼎
     * return : 1
     * sess_id : 17udb785l8ebkkufg1e5461uv7
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
    private List<ConsigneeListBean> consignee_list;

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

    public List<ConsigneeListBean> getConsignee_list() {
        return consignee_list;
    }

    public void setConsignee_list(List<ConsigneeListBean> consignee_list) {
        this.consignee_list = consignee_list;
    }

    public static class ConsigneeListBean extends BaseEntity{
        /**
         * id : 11
         * user_id : 91
         * region_lv1 : 1
         * region_lv2 : 31
         * region_lv3 : 391
         * region_lv4 : 3305
         * address : 牛山北路 50 号
         * mobile : 13634146852
         * zip :
         * consignee : 爱可可
         * is_default : 0
         * region_lv1_name : 中国
         * region_lv2_name : 浙江
         * region_lv3_name : 温州
         * region_lv4_name : 鹿城区
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
}
