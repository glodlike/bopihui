package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */

public class GoodInfo extends BaseEntity {
    /**
     * id : 189
     * name : 五粮液五粮液五粮液 2222
     * sub_name : 五粮液短名 2222
     * brief :
     * current_price : 0.01
     * origin_price : 128
     * icon : http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg
     * begin_time : 1476209831
     * end_time : 1483121840
     * time_status : 1
     * now_time : 1481934134
     * buy_count : 38
     * buy_type : 0
     * is_shop : 1
     * deal_attr : [{"id":"36","input_type":"0","name":"商品","attr_list":[{"id":"1966","name":"小","price":"0.00"},{"id":"1965","name":"大","price":"0.00"}]},{"id":"37","input_type":"0","name":"日期","attr_list":[{"id":"1968","name":"2号","price":"0.00"},{"id":"1967","name":"1号","price":"0.00"}]}]
     * avg_point : 3.75
     * dp_count : 12
     * supplier_location_count : 2
     * last_time : 1187706
     * last_time_format : 13天以上
     * deal_tags : [{"k":2,"v":"多套餐"},{"k":6,"v":"随时退"},{"k":7,"v":"七天退"},{"k":8,"v":"免运费"},{"k":9,"v":"满立减"}]
     * images : ["http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"]
     * oimages : ["http://519wz.cn/public/attachment/201601/21/19/56a0c238a2f1e.jpg"]
     * description :
     * notes :
     * share_url : http://v2.519wz.cn/index.php?ctl=deal&act=189&r=OTE=
     * ypoint : 27.991245
     * xpoint : 120.665907
     * buyin_app : 0
     * is_fx : 0
     * is_my_fx : 0
     * is_collect : 1
     * supplier_location_list : [{"id":"93","name":"519新城店","address":"新城大道90号","tel":"057788888888","xpoint":"120.717351","ypoint":"28.00405"},{"id":"94","name":"519牛山店","address":"牛山北路11221号","tel":"057722222222","xpoint":"120.665907","ypoint":"27.991245"}]
     * supplier_phone : 057788888888
     * supplier_info : {"id":"92","name":"519自营"}
     * short_content :
     * label : false
     * dp_list : [{"id":"26","create_time":"2016-12-14","content":"ggggggggg","reply_content":"","point":"3","user_name":"新用户_92","user_avatar":"http://v2.519wz.cn/public/avatar/000/00/00/92virtual_avatar_middle.jpg","images":[],"oimages":[]},{"id":"27","create_time":"2016-12-14","content":"111111111211212","reply_content":"","point":"4","user_name":"新用户_92","user_avatar":"http://v2.519wz.cn/public/avatar/000/00/00/92virtual_avatar_middle.jpg","images":[],"oimages":[]},{"id":"28","create_time":"2016-12-14","content":"llllllll","reply_content":"","point":"5","user_name":"新用户_92","user_avatar":"http://v2.519wz.cn/public/avatar/000/00/00/92virtual_avatar_middle.jpg","images":[],"oimages":[]},{"id":"25","create_time":"2016-12-14","content":"折酒真的很划算，物超所值","reply_content":"","point":"5","user_name":"新用户_91","user_avatar":"http://v2.519wz.cn/public/avatar/000/00/00/91virtual_avatar_middle.jpg","images":[],"oimages":[]},{"id":"23","create_time":"2016-12-13","content":"好喝","reply_content":"","point":"3","user_name":"新用户_91","user_avatar":"http://v2.519wz.cn/public/avatar/000/00/00/91virtual_avatar_middle.jpg","images":[],"oimages":[]}]
     * page_title : 商品详情
     * ctl : deal
     * act : index
     * status : 1
     * info :
     * city_name : 温州
     * return : 1
     * sess_id : p8rgbeg1ouuo3d68cg1sc9td97
     * ref_uid : null
     */

    private String id;
    private String name;
    private String sub_name;
    private String brief;
    private double current_price;
    private double origin_price;
    private String icon;
    private String begin_time;
    private String end_time;
    private int time_status;
    private int now_time;
    private String buy_count;
    private String buy_type;
    private String is_shop;
    private double avg_point;
    private String dp_count;
    private String supplier_location_count;
    private int last_time;
    private String last_time_format;
    private String description;
    private String notes;
    private String share_url;
    private double ypoint;
    private double xpoint;
    private int buyin_app;
    private int is_fx;
    private int is_my_fx;
    private int is_collect;
    private String supplier_phone;
    private SupplierInfoBean supplier_info;
    private String short_content;
    private boolean label;
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
    private List<DealAttrBean> deal_attr;
    private List<DealTagsBean> deal_tags;
    private List<String> images;
    private List<String> oimages;
    private List<SupplierLocationListBean> supplier_location_list;
    private List<DpListBean> dp_list;

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

    public double getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(double current_price) {
        this.current_price = current_price;
    }

    public double getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(double origin_price) {
        this.origin_price = origin_price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public int getTime_status() {
        return time_status;
    }

    public void setTime_status(int time_status) {
        this.time_status = time_status;
    }

    public int getNow_time() {
        return now_time;
    }

    public void setNow_time(int now_time) {
        this.now_time = now_time;
    }

    public String getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(String buy_count) {
        this.buy_count = buy_count;
    }

    public String getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(String buy_type) {
        this.buy_type = buy_type;
    }

    public String getIs_shop() {
        return is_shop;
    }

    public void setIs_shop(String is_shop) {
        this.is_shop = is_shop;
    }

    public double getAvg_point() {
        return avg_point;
    }

    public void setAvg_point(double avg_point) {
        this.avg_point = avg_point;
    }

    public String getDp_count() {
        return dp_count;
    }

    public void setDp_count(String dp_count) {
        this.dp_count = dp_count;
    }

    public String getSupplier_location_count() {
        return supplier_location_count;
    }

    public void setSupplier_location_count(String supplier_location_count) {
        this.supplier_location_count = supplier_location_count;
    }

    public int getLast_time() {
        return last_time;
    }

    public void setLast_time(int last_time) {
        this.last_time = last_time;
    }

    public String getLast_time_format() {
        return last_time_format;
    }

    public void setLast_time_format(String last_time_format) {
        this.last_time_format = last_time_format;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
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

    public int getBuyin_app() {
        return buyin_app;
    }

    public void setBuyin_app(int buyin_app) {
        this.buyin_app = buyin_app;
    }

    public int getIs_fx() {
        return is_fx;
    }

    public void setIs_fx(int is_fx) {
        this.is_fx = is_fx;
    }

    public int getIs_my_fx() {
        return is_my_fx;
    }

    public void setIs_my_fx(int is_my_fx) {
        this.is_my_fx = is_my_fx;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public String getSupplier_phone() {
        return supplier_phone;
    }

    public void setSupplier_phone(String supplier_phone) {
        this.supplier_phone = supplier_phone;
    }

    public SupplierInfoBean getSupplier_info() {
        return supplier_info;
    }

    public void setSupplier_info(SupplierInfoBean supplier_info) {
        this.supplier_info = supplier_info;
    }

    public String getShort_content() {
        return short_content;
    }

    public void setShort_content(String short_content) {
        this.short_content = short_content;
    }

    public boolean isLabel() {
        return label;
    }

    public void setLabel(boolean label) {
        this.label = label;
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

    public List<DealAttrBean> getDeal_attr() {
        return deal_attr;
    }

    public void setDeal_attr(List<DealAttrBean> deal_attr) {
        this.deal_attr = deal_attr;
    }

    public List<DealTagsBean> getDeal_tags() {
        return deal_tags;
    }

    public void setDeal_tags(List<DealTagsBean> deal_tags) {
        this.deal_tags = deal_tags;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getOimages() {
        return oimages;
    }

    public void setOimages(List<String> oimages) {
        this.oimages = oimages;
    }

    public List<SupplierLocationListBean> getSupplier_location_list() {
        return supplier_location_list;
    }

    public void setSupplier_location_list(List<SupplierLocationListBean> supplier_location_list) {
        this.supplier_location_list = supplier_location_list;
    }

    public List<DpListBean> getDp_list() {
        return dp_list;
    }

    public void setDp_list(List<DpListBean> dp_list) {
        this.dp_list = dp_list;
    }

    public static class SupplierInfoBean extends BaseEntity {
        /**
         * id : 92
         * name : 519自营
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

    public static class DealAttrBean extends BaseEntity {
        /**
         * id : 36
         * input_type : 0
         * name : 商品
         * attr_list : [{"id":"1966","name":"小","price":"0.00"},{"id":"1965","name":"大","price":"0.00"}]
         */

        private String id;
        private String input_type;
        private String name;
        private List<AttrListBean> attr_list;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInput_type() {
            return input_type;
        }

        public void setInput_type(String input_type) {
            this.input_type = input_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AttrListBean> getAttr_list() {
            return attr_list;
        }

        public void setAttr_list(List<AttrListBean> attr_list) {
            this.attr_list = attr_list;
        }

        public static class AttrListBean extends BaseEntity {
            /**
             * id : 1966
             * name : 小
             * price : 0.00
             */

            private String id;
            private String name;
            private String price;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }

    public static class DealTagsBean  extends BaseEntity{
        /**
         * k : 2
         * v : 多套餐
         */

        private int k;
        private String v;

        public int getK() {
            return k;
        }

        public void setK(int k) {
            this.k = k;
        }

        public String getV() {
            return v;
        }

        public void setV(String v) {
            this.v = v;
        }
    }

    public static class SupplierLocationListBean extends BaseEntity {
        /**
         * id : 93
         * name : 519新城店
         * address : 新城大道90号
         * tel : 057788888888
         * xpoint : 120.717351
         * ypoint : 28.00405
         */

        private String id;
        private String name;
        private String address;
        private String tel;
        private String xpoint;
        private String ypoint;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getXpoint() {
            return xpoint;
        }

        public void setXpoint(String xpoint) {
            this.xpoint = xpoint;
        }

        public String getYpoint() {
            return ypoint;
        }

        public void setYpoint(String ypoint) {
            this.ypoint = ypoint;
        }
    }

    public static class DpListBean  extends BaseEntity{
        /**
         * id : 26
         * create_time : 2016-12-14
         * content : ggggggggg
         * reply_content :
         * point : 3
         * user_name : 新用户_92
         * user_avatar : http://v2.519wz.cn/public/avatar/000/00/00/92virtual_avatar_middle.jpg
         * images : []
         * oimages : []
         */

        private String id;
        private String create_time;
        private String content;
        private String reply_content;
        private String point;
        private String user_name;
        private String user_avatar;
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

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
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
