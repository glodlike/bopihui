package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class Good extends BaseEntity{

    /**
     * filter_group : [{"id":"14","name":"日期","cate_id":"0","sort":"4","is_effect":"1","filter_list":[{"id":"48","name":"1号","filter_group_id":"14","sort":"0","ref":"f14"},{"id":"49","name":"2号","filter_group_id":"14","sort":"0","ref":"f14"},{"name":"全部","id":0,"act":1,"ref":"f14"}],"active_name":"全部"},{"id":"13","name":"活动","cate_id":"0","sort":"3","is_effect":"1","filter_list":[{"id":"47","name":"双11活动","filter_group_id":"13","sort":"0","ref":"f13"},{"name":"全部","id":0,"act":1,"ref":"f13"}],"active_name":"全部"},{"id":"12","name":"年龄","cate_id":"82","sort":"2","is_effect":"1","filter_list":[{"id":"44","name":"2-3岁","filter_group_id":"12","sort":"0","ref":"f12"},{"id":"45","name":"aaa","filter_group_id":"12","sort":"0","ref":"f12"},{"id":"46","name":"bbb","filter_group_id":"12","sort":"0","ref":"f12"},{"name":"全部","id":0,"act":1,"ref":"f12"}],"active_name":"全部"},{"id":"11","name":"1111","cate_id":"82","sort":"1","is_effect":"1","filter_list":[{"id":"43","name":"100","filter_group_id":"11","sort":"0","ref":"f11"},{"name":"全部","id":0,"act":1,"ref":"f11"}],"active_name":"全部"}]
     * bid : 0
     * cate_id : 82
     * city_id : 22
     * order_type :
     * page_title : 商品列表
     * page : {"page":1,"page_total":1,"page_size":10,"data_total":"5"}
     * item : [{"id":"191","distance":0,"ypoint":27.991245,"xpoint":120.665907,"name":"茅台4444","sub_name":"茅台4444","brief":"茅台4444","buy_count":"4","current_price":80,"origin_price":100,"icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","image":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","end_time_format":"2016-12-31 15:04:00","begin_time_format":"2016-12-06 16:56:10","begin_time":"1480985770","end_time":"1483139040","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0},{"id":"190","distance":0,"ypoint":27.991245,"xpoint":120.665907,"name":"53°飞天茅台500ml 11111111111","sub_name":"53°飞天茅台500ml 1111111","brief":"","buy_count":"4","current_price":12,"origin_price":15,"icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","image":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","end_time_format":"","begin_time_format":"","begin_time":"0","end_time":"0","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0},{"id":"189","distance":0,"ypoint":27.991245,"xpoint":120.665907,"name":"五粮液五粮液五粮液 2222","sub_name":"五粮液短名 2222","brief":"","buy_count":"45","current_price":0.01,"origin_price":128,"icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","image":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","end_time_format":"2016-12-31 10:17:20","begin_time_format":"2016-10-12 10:17:11","begin_time":"1476209831","end_time":"1483121840","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0},{"id":"112","distance":0,"ypoint":27.991245,"xpoint":120.665907,"name":"53°飞天茅台500ml 3333","sub_name":"53°飞天茅台500ml 短名33333","brief":"酒精度：53%vol、\r\n规格：500ml*6、\r\n产地：贵州、\r\n（两瓶起免费配送）、","buy_count":"11","current_price":69,"origin_price":1000,"icon":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","image":"http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg","end_time_format":"","begin_time_format":"","begin_time":"0","end_time":"0","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0},{"id":"192","distance":0,"ypoint":31.459131,"xpoint":121.458954,"name":"商家发布","sub_name":"商家发布","brief":"","buy_count":"0","current_price":80,"origin_price":122,"icon":"http://v2.519wz.cn/public/attachment/201611/03/16/b80cb1d3a8b6eb00c0268713362f63f120.jpg","image":"http://v2.519wz.cn/public/attachment/201611/03/16/93335765227b3c459fd49661fd20138492_280x210.jpg","end_time_format":"","begin_time_format":"","begin_time":"0","end_time":"0","time_status":"1","auto_order":"0","is_lottery":"0","is_refund":"1","deal_score":0,"buyin_app":0,"is_today":0}]
     * bcate_list : [{"id":"82","name":"啤酒","iconfont":"","iconcolor":"","cate_img":"http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg","bcate_type":[{"id":"87","cate_id":"82","name":"官方活动"},{"id":"88","cate_id":"82","name":"亲子团购"},{"id":"89","cate_id":"82","name":"亲子DIY"},{"id":"90","cate_id":"82","name":"景点郊游"},{"id":"91","cate_id":"82","name":"儿童乐园"},{"id":"92","cate_id":"82","name":"景点/场馆"},{"id":"93","cate_id":"82","name":"农家乐"},{"id":"94","cate_id":"82","name":"亲子课堂"},{"id":"95","cate_id":"82","name":"游学"}]},{"id":"83","name":"白酒","iconfont":"","iconcolor":"","cate_img":"./public/attachment/201609/01/10/57c791926cee3.jpg","bcate_type":[{"id":"97","cate_id":"83","name":"周边游"},{"id":"98","cate_id":"83","name":"度假村"},{"id":"99","cate_id":"83","name":"国内游"}]},{"id":"84","name":"老酒","iconfont":"","iconcolor":"","cate_img":"http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg","bcate_type":[{"id":"100","cate_id":"84","name":"黄浦区"},{"id":"101","cate_id":"84","name":"卢湾区"},{"id":"102","cate_id":"84","name":"静安区"}]},{"id":"85","name":"葡萄酒","iconfont":"","iconcolor":"","cate_img":"http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg","bcate_type":[{"id":"103","cate_id":"85","name":"宝宝用品"},{"id":"104","cate_id":"85","name":"妈妈用品"}]},{"id":"86","name":"红酒","iconfont":"","iconcolor":"","cate_img":"http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg","bcate_type":[{"id":"105","cate_id":"86","name":"免费活动"}]}]
     * brand_list : [{"id":0,"name":"全部"}]
     * navs : [{"name":"默认","code":"default"},{"name":"好评","code":"avg_point"},{"name":"最新","code":"newest"},{"name":"销量","code":"buy_count"},{"name":"价格最低","code":"price_asc"},{"name":"价格最高","code":"price_desc"}]
     * ctl : goods
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : c9pfdccspn5viv5c1bek0sl3o5
     * ref_uid : null
     */

    private int bid;
    private int cate_id;
    private int city_id;
    private String order_type;
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
    private List<FilterGroupBean> filter_group;
    private List<ItemBean> item;
    private List<BcateListBean> bcate_list;
    private List<BrandListBean> brand_list;
    private List<NavsBean> navs;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
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

    public List<FilterGroupBean> getFilter_group() {
        return filter_group;
    }

    public void setFilter_group(List<FilterGroupBean> filter_group) {
        this.filter_group = filter_group;
    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

    public List<BcateListBean> getBcate_list() {
        return bcate_list;
    }

    public void setBcate_list(List<BcateListBean> bcate_list) {
        this.bcate_list = bcate_list;
    }

    public List<BrandListBean> getBrand_list() {
        return brand_list;
    }

    public void setBrand_list(List<BrandListBean> brand_list) {
        this.brand_list = brand_list;
    }

    public List<NavsBean> getNavs() {
        return navs;
    }

    public void setNavs(List<NavsBean> navs) {
        this.navs = navs;
    }

    public static class PageBean {
        /**
         * page : 1
         * page_total : 1
         * page_size : 10
         * data_total : 5
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

    public static class FilterGroupBean {
        /**
         * id : 14
         * name : 日期
         * cate_id : 0
         * sort : 4
         * is_effect : 1
         * filter_list : [{"id":"48","name":"1号","filter_group_id":"14","sort":"0","ref":"f14"},{"id":"49","name":"2号","filter_group_id":"14","sort":"0","ref":"f14"},{"name":"全部","id":0,"act":1,"ref":"f14"}]
         * active_name : 全部
         */

        private String id;
        private String name;
        private String cate_id;
        private String sort;
        private String is_effect;
        private String active_name;
        private List<FilterListBean> filter_list;

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

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getIs_effect() {
            return is_effect;
        }

        public void setIs_effect(String is_effect) {
            this.is_effect = is_effect;
        }

        public String getActive_name() {
            return active_name;
        }

        public void setActive_name(String active_name) {
            this.active_name = active_name;
        }

        public List<FilterListBean> getFilter_list() {
            return filter_list;
        }

        public void setFilter_list(List<FilterListBean> filter_list) {
            this.filter_list = filter_list;
        }

        public static class FilterListBean {
            /**
             * id : 48
             * name : 1号
             * filter_group_id : 14
             * sort : 0
             * ref : f14
             * act : 1
             */

            private String id;
            private String name;
            private String filter_group_id;
            private String sort;
            private String ref;
            private int act;

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

            public String getFilter_group_id() {
                return filter_group_id;
            }

            public void setFilter_group_id(String filter_group_id) {
                this.filter_group_id = filter_group_id;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getRef() {
                return ref;
            }

            public void setRef(String ref) {
                this.ref = ref;
            }

            public int getAct() {
                return act;
            }

            public void setAct(int act) {
                this.act = act;
            }
        }
    }

    public static class ItemBean {
        /**
         * id : 191
         * distance : 0
         * ypoint : 27.991245
         * xpoint : 120.665907
         * name : 茅台4444
         * sub_name : 茅台4444
         * brief : 茅台4444
         * buy_count : 4
         * current_price : 80
         * origin_price : 100
         * icon : http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg
         * image : http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg
         * end_time_format : 2016-12-31 15:04:00
         * begin_time_format : 2016-12-06 16:56:10
         * begin_time : 1480985770
         * end_time : 1483139040
         * time_status : 1
         * auto_order : 0
         * is_lottery : 0
         * is_refund : 1
         * deal_score : 0
         * buyin_app : 0
         * is_today : 0
         */

        private String id;
        private int distance;
        private double ypoint;
        private double xpoint;
        private String name;
        private String sub_name;
        private String brief;
        private String buy_count;
        private double current_price;
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

        public double getCurrent_price() {
            return current_price;
        }

        public void setCurrent_price(double current_price) {
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
    }

    public static class BcateListBean {
        /**
         * id : 82
         * name : 啤酒
         * iconfont :
         * iconcolor :
         * cate_img : http://img10.jiuxian.com/bill/2016/1024/4a174c98aa474117b1db910c3f875b9b.jpg
         * bcate_type : [{"id":"87","cate_id":"82","name":"官方活动"},{"id":"88","cate_id":"82","name":"亲子团购"},{"id":"89","cate_id":"82","name":"亲子DIY"},{"id":"90","cate_id":"82","name":"景点郊游"},{"id":"91","cate_id":"82","name":"儿童乐园"},{"id":"92","cate_id":"82","name":"景点/场馆"},{"id":"93","cate_id":"82","name":"农家乐"},{"id":"94","cate_id":"82","name":"亲子课堂"},{"id":"95","cate_id":"82","name":"游学"}]
         */

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
            /**
             * id : 87
             * cate_id : 82
             * name : 官方活动
             */

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
    }

    public static class BrandListBean {
        /**
         * id : 0
         * name : 全部
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class NavsBean {
        /**
         * name : 默认
         * code : default
         */

        private String name;
        private String code;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
