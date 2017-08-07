package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class Deliver extends BaseEntity {
    /**
     * user_login_status : 1
     * shop_list : [{"preview":"","id":"93","is_verify":"0","avg_point":"0.0000","address":"新城大道90号","name":"519新城店","tel":"057788888888","peisong_fanwei":"10公里内11","distance":0,"xpoint":120.717351,"ypoint":28.00405,"juli":"0m"},{"preview":"","id":"94","is_verify":"0","avg_point":"3.9091","address":"牛山北路11221号","name":"519牛山店","tel":"057722222222","peisong_fanwei":"5公里内","distance":0,"xpoint":120.665907,"ypoint":27.991245,"juli":"0m"}]
     * ctl : cart
     * act : select_shop
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : 814k6sitbre0rkdtk8rgpl01d5
     * ref_uid : null
     */

    private int user_login_status;
    private String ctl;
    private String act;
    private int status;
    private String info;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;
    private List<ShopListBean> shop_list;

    public int getUser_login_status() {
        return user_login_status;
    }

    public void setUser_login_status(int user_login_status) {
        this.user_login_status = user_login_status;
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

    public List<ShopListBean> getShop_list() {
        return shop_list;
    }

    public void setShop_list(List<ShopListBean> shop_list) {
        this.shop_list = shop_list;
    }

    public static class ShopListBean {
        /**
         * preview :
         * id : 93
         * is_verify : 0
         * avg_point : 0.0000
         * address : 新城大道90号
         * name : 519新城店
         * tel : 057788888888
         * peisong_fanwei : 10公里内11
         * distance : 0
         * xpoint : 120.717351
         * ypoint : 28.00405
         * juli : 0m
         */

        private String preview;
        private String id;
        private String is_verify;
        private String avg_point;
        private String address;
        private String name;
        private String tel;
        private String peisong_fanwei;
        private int distance;
        private double xpoint;
        private double ypoint;
        private String juli;

        public String getPreview() {
            return preview;
        }

        public void setPreview(String preview) {
            this.preview = preview;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIs_verify() {
            return is_verify;
        }

        public void setIs_verify(String is_verify) {
            this.is_verify = is_verify;
        }

        public String getAvg_point() {
            return avg_point;
        }

        public void setAvg_point(String avg_point) {
            this.avg_point = avg_point;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPeisong_fanwei() {
            return peisong_fanwei;
        }

        public void setPeisong_fanwei(String peisong_fanwei) {
            this.peisong_fanwei = peisong_fanwei;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public double getXpoint() {
            return xpoint;
        }

        public void setXpoint(double xpoint) {
            this.xpoint = xpoint;
        }

        public double getYpoint() {
            return ypoint;
        }

        public void setYpoint(double ypoint) {
            this.ypoint = ypoint;
        }

        public String getJuli() {
            return juli;
        }

        public void setJuli(String juli) {
            this.juli = juli;
        }
    }
}
