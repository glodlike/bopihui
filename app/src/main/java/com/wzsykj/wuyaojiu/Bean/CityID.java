package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/1/16 0016.
 */

public class CityID {

    /**
     * current_city : {"id":"22","name":"温州","uname":"wenzhou","is_effect":"1","is_delete":"0","pid":"21","is_open":"1","is_default":"1","description":"","notice":"","seo_title":"","seo_keyword":"","seo_description":"","sort":"0","is_hot":"1"}
     * ctl : city
     * act : get_city
     * status : 1
     * city_name : 温州
     * return : 1
     * sess_id : 4j3g67pfms41ceb23t9cefak11
     * ref_uid : null
     */

    private CurrentCityBean current_city;
    private String ctl;
    private String act;
    private int status;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;

    public CurrentCityBean getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(CurrentCityBean current_city) {
        this.current_city = current_city;
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

    public static class CurrentCityBean {
        /**
         * id : 22
         * name : 温州
         * uname : wenzhou
         * is_effect : 1
         * is_delete : 0
         * pid : 21
         * is_open : 1
         * is_default : 1
         * description :
         * notice :
         * seo_title :
         * seo_keyword :
         * seo_description :
         * sort : 0
         * is_hot : 1
         */

        private String id;
        private String name;
        private String uname;
        private String is_effect;
        private String is_delete;
        private String pid;
        private String is_open;
        private String is_default;
        private String description;
        private String notice;
        private String seo_title;
        private String seo_keyword;
        private String seo_description;
        private String sort;
        private String is_hot;

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

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getIs_effect() {
            return is_effect;
        }

        public void setIs_effect(String is_effect) {
            this.is_effect = is_effect;
        }

        public String getIs_delete() {
            return is_delete;
        }

        public void setIs_delete(String is_delete) {
            this.is_delete = is_delete;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getIs_open() {
            return is_open;
        }

        public void setIs_open(String is_open) {
            this.is_open = is_open;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getSeo_title() {
            return seo_title;
        }

        public void setSeo_title(String seo_title) {
            this.seo_title = seo_title;
        }

        public String getSeo_keyword() {
            return seo_keyword;
        }

        public void setSeo_keyword(String seo_keyword) {
            this.seo_keyword = seo_keyword;
        }

        public String getSeo_description() {
            return seo_description;
        }

        public void setSeo_description(String seo_description) {
            this.seo_description = seo_description;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIs_hot() {
            return is_hot;
        }

        public void setIs_hot(String is_hot) {
            this.is_hot = is_hot;
        }
    }
}
