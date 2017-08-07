package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class Type extends BaseEntity {
    /**
     * bcate_list : [{"id":"1","name":"红酒","iconfont":"","iconcolor":"","cate_img":"http://116.62.28.31/bopihui/public/attachment/201612/16/09/5853464164345.jpg"},{"id":"2","name":"啤酒","iconfont":"","iconcolor":"","cate_img":"http://116.62.28.31/bopihui/public/attachment/201612/16/09/5853464164345.jpg"}]
     * page_title : 分类
     * ctl : cate
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : c3hige4cf7g9c1h5pkcl0ipjl1
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
    private List<BcateListBean> bcate_list;

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

    public List<BcateListBean> getBcate_list() {
        return bcate_list;
    }

    public void setBcate_list(List<BcateListBean> bcate_list) {
        this.bcate_list = bcate_list;
    }

    public static class BcateListBean {
        /**
         * id : 1
         * name : 红酒
         * iconfont :
         * iconcolor :
         * cate_img : http://116.62.28.31/bopihui/public/attachment/201612/16/09/5853464164345.jpg
         */

        private String id;
        private String name;
        private String iconfont;
        private String iconcolor;
        private String cate_img;

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

        public String getIconfont() {
            return iconfont;
        }

        public void setIconfont(String iconfont) {
            this.iconfont = iconfont;
        }

        public String getIconcolor() {
            return iconcolor;
        }

        public void setIconcolor(String iconcolor) {
            this.iconcolor = iconcolor;
        }

        public String getCate_img() {
            return cate_img;
        }

        public void setCate_img(String cate_img) {
            this.cate_img = cate_img;
        }
    }
   /* *//**
     * bcate_list : [{"id":"82","name":"啤酒","iconfont":"","iconcolor":"","cate_img":"http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg","bcate_type":[{"id":"87","cate_id":"82","name":"官方活动"},{"id":"88","cate_id":"82","name":"亲子团购"},{"id":"89","cate_id":"82","name":"亲子DIY"},{"id":"90","cate_id":"82","name":"景点郊游"},{"id":"91","cate_id":"82","name":"儿童乐园"},{"id":"92","cate_id":"82","name":"景点/场馆"},{"id":"93","cate_id":"82","name":"农家乐"},{"id":"94","cate_id":"82","name":"亲子课堂"},{"id":"95","cate_id":"82","name":"游学"}]},{"id":"83","name":"白酒","iconfont":"","iconcolor":"","cate_img":"./public/attachment/201609/01/10/57c791926cee3.jpg","bcate_type":[{"id":"96","cate_id":"83","name":"五星酒店"},{"id":"97","cate_id":"83","name":"周边游"},{"id":"98","cate_id":"83","name":"度假村"},{"id":"99","cate_id":"83","name":"国内游"}]},{"id":"84","name":"老酒","iconfont":"","iconcolor":"","cate_img":"http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg","bcate_type":[{"id":"100","cate_id":"84","name":"黄浦区"},{"id":"101","cate_id":"84","name":"卢湾区"},{"id":"102","cate_id":"84","name":"静安区"}]},{"id":"85","name":"葡萄酒","iconfont":"","iconcolor":"","cate_img":"http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg","bcate_type":[{"id":"103","cate_id":"85","name":"宝宝用品"},{"id":"104","cate_id":"85","name":"妈妈用品"}]},{"id":"86","name":"红酒","iconfont":"","iconcolor":"","cate_img":"http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg","bcate_type":[{"id":"105","cate_id":"86","name":"免费活动"}]}]
     * page_title : 分类
     * ctl : cate
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : 45sv5es2iq4loi9kb4m3tmtb35
     * ref_uid : null
     *//*

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
    private List<BcateListBean> bcate_list;

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

    public List<BcateListBean> getBcate_list() {
        return bcate_list;
    }

    public void setBcate_list(List<BcateListBean> bcate_list) {
        this.bcate_list = bcate_list;
    }

    public static class BcateListBean {
        *//**
         * id : 82
         * name : 啤酒
         * iconfont :
         * iconcolor :
         * cate_img : http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg
         * bcate_type : [{"id":"87","cate_id":"82","name":"官方活动"},{"id":"88","cate_id":"82","name":"亲子团购"},{"id":"89","cate_id":"82","name":"亲子DIY"},{"id":"90","cate_id":"82","name":"景点郊游"},{"id":"91","cate_id":"82","name":"儿童乐园"},{"id":"92","cate_id":"82","name":"景点/场馆"},{"id":"93","cate_id":"82","name":"农家乐"},{"id":"94","cate_id":"82","name":"亲子课堂"},{"id":"95","cate_id":"82","name":"游学"}]
         *//*

        private String id;
        private String name;
        private String iconfont;
        private String iconcolor;
        private String cate_img;
        private List<BcateTypeBean> bcate_type;

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

        public String getIconfont() {
            return iconfont;
        }

        public void setIconfont(String iconfont) {
            this.iconfont = iconfont;
        }

        public String getIconcolor() {
            return iconcolor;
        }

        public void setIconcolor(String iconcolor) {
            this.iconcolor = iconcolor;
        }

        public String getCate_img() {
            return cate_img;
        }

        public void setCate_img(String cate_img) {
            this.cate_img = cate_img;
        }

        public List<BcateTypeBean> getBcate_type() {
            return bcate_type;
        }

        public void setBcate_type(List<BcateTypeBean> bcate_type) {
            this.bcate_type = bcate_type;
        }

        public static class BcateTypeBean {
            *//**
             * id : 87
             * cate_id : 82
             * name : 官方活动
             *//*

            private String id;
            private String cate_id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
       }*/





}
