package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/12 0012.
 */

public class CityList extends BaseEntity {
    /**
     * city_list : [{"id":"21","name":"浙江","uname":"zhejiang","is_effect":"1","is_delete":"0","pid":"0","is_open":"1","is_default":"0","description":"","notice":"","seo_title":"","seo_keyword":"","seo_description":"","sort":"0","is_hot":"0"},{"id":"22","name":"温州","uname":"wenzhou","is_effect":"1","is_delete":"0","pid":"21","is_open":"1","is_default":"1","description":"","notice":"","seo_title":"","seo_keyword":"","seo_description":"","sort":"0","is_hot":"1"},{"id":"23","name":"上海","uname":"","is_effect":"1","is_delete":"0","pid":"0","is_open":"1","is_default":"0","description":"","notice":"","seo_title":"","seo_keyword":"","seo_description":"","sort":"0","is_hot":"0"},{"id":"24","name":"上海","uname":"sh","is_effect":"1","is_delete":"0","pid":"23","is_open":"1","is_default":"0","description":"","notice":"","seo_title":"","seo_keyword":"","seo_description":"","sort":"0","is_hot":"0"},{"id":"27","name":"福建","uname":"fujian","is_effect":"1","is_delete":"0","pid":"0","is_open":"1","is_default":"0","description":"","notice":"","seo_title":"","seo_keyword":"","seo_description":"","sort":"0","is_hot":"0"},{"id":"28","name":"福鼎","uname":"fuding","is_effect":"1","is_delete":"0","pid":"27","is_open":"1","is_default":"0","description":"","notice":"","seo_title":"","seo_keyword":"","seo_description":"","sort":"0","is_hot":"0"}]
     * hot_city : [{"id":"22","name":"温州"}]
     * page_title : 城市切换
     * ctl : city
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : qakop4bikea67801ur6733f881
     * ref_uid : null
     */
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
    private List<CityListBean> city_list;
    private List<HotCityBean> hot_city;

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

    public List<CityListBean> getCity_list() {
        return city_list;
    }

    public void setCity_list(List<CityListBean> city_list) {
        this.city_list = city_list;
    }

    public List<HotCityBean> getHot_city() {
        return hot_city;
    }

    public void setHot_city(List<HotCityBean> hot_city) {
        this.hot_city = hot_city;
    }

    public static class CityListBean {
        /**
         * id : 21
         * name : 浙江
         * uname : zhejiang
         * is_effect : 1
         * is_delete : 0
         * pid : 0
         * is_open : 1
         * is_default : 0
         * description :
         * notice :
         * seo_title :
         * seo_keyword :
         * seo_description :
         * sort : 0
         * is_hot : 0
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

    public static class HotCityBean {
        /**
         * id : 22
         * name : 温州
         */

        private String id;
        private String name;

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
    }
}
