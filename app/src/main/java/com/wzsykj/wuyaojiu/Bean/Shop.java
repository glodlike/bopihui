package com.wzsykj.wuyaojiu.Bean;

import com.google.gson.annotations.SerializedName;
import com.wzsykj.wuyaojiu.entity.BaseEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/22 0022.
 */

public class Shop extends BaseEntity{

    /**
     * shop_list : [{"preview":"http://img.bphapp.com/public/attachment/201705/10/10/59127f5ee123e.JPG?x-oss-process=image/resize,m_lfit,h_200,w_200/quality,Q_80","id":"1","is_verify":"1","avg_point":"4.5133","address":"牛山北路50号","name":"总店（牛山店）","tel":"86191919","open_time":"9点到21点","peisong_fanwei":"全国","distance":34.731708423344,"xpoint":120.665908,"ypoint":27.99123,"juli":"35m"},{"preview":"","id":"3","is_verify":"1","avg_point":"4.9474","address":"温州市鹿城区划龙桥路龙汇广场东龙路109号-6","name":"划龙桥店","tel":"88171919","open_time":"","peisong_fanwei":"鹿城区","distance":973.37907880712,"xpoint":120.672833,"ypoint":27.997098,"juli":"973m"},{"preview":"","id":"7","is_verify":"1","avg_point":"4.8000","address":"温州市鹿城区下吕浦春晖路122号","name":"下吕浦店","tel":"88331919","open_time":"","peisong_fanwei":"鹿城区","distance":2222.7824100288,"xpoint":120.687742,"ypoint":27.995811,"juli":"2.22km"},{"preview":"","id":"6","is_verify":"0","avg_point":"5.0000","address":"温州市鹿城区小南门虞师里212号","name":"小南门店","tel":"13957785125","open_time":"","peisong_fanwei":"小南门","distance":2480.7052224689,"xpoint":120.665993,"ypoint":28.013213,"juli":"2.48km"},{"preview":"","id":"42","is_verify":"0","avg_point":"0.0000","address":"瓯海区新桥镇六虹桥路1141号","name":"六虹桥路店","tel":"15990771919","open_time":"9：00-21：00","peisong_fanwei":"新桥","distance":2967.557838322,"xpoint":120.635981,"ypoint":27.986853,"juli":"2.97km"},{"preview":"http://img.bphapp.com/public/attachment/201701/03/13/586b3cacd45d5.JPG?x-oss-process=image/resize,m_lfit,h_200,w_200/quality,Q_80","id":"2","is_verify":"1","avg_point":"4.1724","address":"车站大道540号，泰安大厦一楼（黎明立交桥旁边）","name":"黎明店","tel":"88171717","open_time":"9：00-21：00","peisong_fanwei":"全国","distance":3768.842683312,"xpoint":120.689504,"ypoint":28.017552,"juli":"3.77km"},{"preview":"","id":"34","is_verify":"0","avg_point":"5.0000","address":"江滨路后垟巷142号（中午12点到晚上8点）","name":"江滨路店","tel":"88299976","open_time":"中午12点到晚上8点","peisong_fanwei":"温州市","distance":4160.7463810325,"xpoint":120.660564,"ypoint":28.028017,"juli":"4.16km"},{"preview":"","id":"13","is_verify":"1","avg_point":"5.0000","address":"温州鹿城区新城汇源路137号","name":"新城店","tel":"13676775566","open_time":"","peisong_fanwei":"鹿城区","distance":5609.5871295124,"xpoint":120.719487,"ypoint":28.008062,"juli":"5.61km"},{"preview":"","id":"9","is_verify":"0","avg_point":"0.0000","address":"温州市龙湾区新城汤家桥雁荡西路190号","name":"汤家桥店","tel":"88573311","open_time":"","peisong_fanwei":"汤家桥","distance":5613.8275192168,"xpoint":120.722853,"ypoint":27.993482,"juli":"5.61km"},{"preview":"","id":"10","is_verify":"1","avg_point":"4.8571","address":"温州市瓯海区郭溪镇温巨路547号","name":"温巨店(浦西)","tel":"13587888495","open_time":"","peisong_fanwei":"浦西","distance":5741.2901991842,"xpoint":120.607839,"ypoint":27.997197,"juli":"5.74km"},{"preview":"","id":"11","is_verify":"1","avg_point":"5.0000","address":"温州市江滨街道新田园聚源路6号","name":"新田园","tel":"88208819","open_time":"","peisong_fanwei":"新田园","distance":6389.5436561646,"xpoint":120.72796,"ypoint":28.007773,"juli":"6.39km"},{"preview":"","id":"27","is_verify":"1","avg_point":"5.0000","address":"潘桥站南电子商务大厦1-300号","name":"潘桥谢和店","tel":"15968708880","open_time":"","peisong_fanwei":"温州市","distance":7583.0970244459,"xpoint":120.594357,"ypoint":27.965279,"juli":"7.58km"},{"preview":"","id":"31","is_verify":"0","avg_point":"5.0000","address":"瓯海区郭溪街道塘下社区景德东路351号","name":"郭溪塘下店","tel":"13396981919","open_time":"","peisong_fanwei":"郭溪镇","distance":8235.4063838247,"xpoint":120.5823,"ypoint":27.996838,"juli":"8.24km"},{"preview":"","id":"8","is_verify":"1","avg_point":"5.0000","address":"温州市龙湾区状元镇新街街道龙飞路23号","name":"状元店","tel":"15868586062","open_time":"","peisong_fanwei":"龙湾状元","distance":10444.666638073,"xpoint":120.770717,"ypoint":27.97604,"juli":"10.44km"},{"preview":"","id":"4","is_verify":"1","avg_point":"4.7188","address":"温州市瓯海区瞿溪街道会昌路98号（瞿溪文化宫对面）","name":"瞿溪店","tel":"86151597","open_time":"","peisong_fanwei":"瓯海区","distance":12795.648932127,"xpoint":120.535991,"ypoint":27.999397,"juli":"12.8km"}]
     * page_title : 选择门店
     * ctl : shop
     * act : index
     * status : 1
     * city_name : 温州
     * return : 1
     * sess_id : 72hb1plf2jchob1ora0dt91uq1
     * ref_uid : null
     */

    private String page_title;
    private String ctl;
    private String act;
    private int status;
    private String city_name;
    @SerializedName("return")
    private int returnX;
    private String sess_id;
    private Object ref_uid;
    private List<ShopListBean> shop_list;

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

    public static class ShopListBean  extends BaseEntity{
        /**
         * preview : http://img.bphapp.com/public/attachment/201705/10/10/59127f5ee123e.JPG?x-oss-process=image/resize,m_lfit,h_200,w_200/quality,Q_80
         * id : 1
         * is_verify : 1
         * avg_point : 4.5133
         * address : 牛山北路50号
         * name : 总店（牛山店）
         * tel : 86191919
         * open_time : 9点到21点
         * peisong_fanwei : 全国
         * distance : 34.731708423344
         * xpoint : 120.665908
         * ypoint : 27.99123
         * juli : 35m
         */
        private String preview;
        private String id;
        private String is_verify;
        private String avg_point;
        private String address;
        private String name;
        private String tel;
        private String open_time;
        private String peisong_fanwei;
        private double distance;
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

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public String getPeisong_fanwei() {
            return peisong_fanwei;
        }

        public void setPeisong_fanwei(String peisong_fanwei) {
            this.peisong_fanwei = peisong_fanwei;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
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
