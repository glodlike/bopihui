package com.wzsykj.wuyaojiu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;

/**
 * Created by CHP on 2016/8/18.
 */

public class SharePerfenceUtils {
    public Context mcontext;

    /**
     * 得到sharePerfence文件里面得到的数据,  token
     *
     * @return
     */
    public SharePerfenceUtils(Context context) {
        mcontext = context;
    }




    public String getToken() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("token", "");
        return token;
    }

    public String getToken1() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML1", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("token", "");
        return token;
    }
    public String getToken2() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML2", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("token", "");
        return token;
    }
    public String getToken3() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML3", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("token", "");
        return token;
    }

    public String getToken4() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML4", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("token", "");
        return token;
    }

    public String getToken5() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML5", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("token", "");
        return token;
    }

    public String getToken6() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML6", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("token", "");
        return token;
    }


    //用于判断用户是否第一次登陆
    public boolean tokenIsnull() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("token", "");
        boolean isfrist = token.equals("");
        return isfrist;
    }

    //用于判断用户是否添加过账号
    public String IsAddaccount() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("AccountXml", Context.MODE_PRIVATE); //私有数据
        String IsAddaccount = sharedPreferences.getString("AccountXml", "");
        return IsAddaccount;
    }

    public void keepToken(String token) {//保存token
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("token", token);
        //提交保存
        editor.commit();
    }
    public void keepToken1(String token) {//保存token
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML1", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("token", token);
        //提交保存
        editor.commit();
    }

    public void keepToken2(String token) {//保存token
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML2", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("token", token);
        //提交保存
        editor.commit();
    }
    public void keepToken3(String token) { //保存token
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML3", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("token", token);
        //提交保存
        editor.commit();
    }

    public void keepToken4(String token) {//保存token
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML4", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("token", token);
        //提交保存
        editor.commit();
    }


    public void keepToken5(String token) {//保存token
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML5", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("token", token);
        //提交保存
        editor.commit();
    }


    public void keepToken6(String token) {//保存token
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("tokenXML6", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("token", token);
        //提交保存
        editor.commit();
    }




    public void keepCity(String city) {//保存城市信息
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("CityXML", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("city", city);
        //提交保存
        editor.commit();
    }

    public String getCity() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("CityXML", Context.MODE_PRIVATE); //私有数据
        String token = sharedPreferences.getString("city", "");
        return token;
    }

    public void keepRecord(String AccountType, String accountNo) {//保存报价信息
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("OrderXML", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("type", AccountType);
        editor.putString("number", accountNo);
        //提交保存
        editor.commit();
    }

    public String[] getAccount() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("OrderXML", Context.MODE_PRIVATE); //私有数据
        String accountType = sharedPreferences.getString("type", "");
        String accountNo = sharedPreferences.getString("number", "");
        String[] Account = {accountType, accountNo};
        return Account;
    }

    public void keepInviter(String... params) {//保存邀请人信息
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("InviterInfo", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("name", params[0]);
        editor.putString("headurl", params[1]);
        editor.putString("phoneno", params[2]);
        editor.putString("userid", params[3]);
        //提交保存
        editor.commit();
    }
    public ArrayList<String> getInviterInfo() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("InviterInfo", Context.MODE_PRIVATE); //私有数据
        String name = sharedPreferences.getString("name", "");
        String headurl = sharedPreferences.getString("headurl", "");
        String phoneno = sharedPreferences.getString("phoneno", "");
        String userid = sharedPreferences.getString("userid", "");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(name);
        arrayList.add(headurl);
        arrayList.add(phoneno);
        arrayList.add(userid);
        return arrayList;
    }
    /**
     * 保存用户名和密码
     * @param username
     * @param passWord
     */
    public void keepUserInfo(String username,String passWord) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("userNameInfo", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("name", username);
        editor.putString("pass", passWord);
        //提交保存
        editor.commit();
    }
     /**
     * 取得账号 密码
     * @return
     */
    public ArrayList<String> getUserInfo() {
        ArrayList<String> userInfoList = new ArrayList<>();
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("userNameInfo", Context.MODE_PRIVATE); //私有数据
        String name = sharedPreferences.getString("name",""); //账号
        String pass = sharedPreferences.getString("pass","");  //密码
        userInfoList.add(name);
        userInfoList.add(pass);
        return userInfoList;
    }
    /**
     * 保存经纬度
     * @param
     * @param
     */
    public void keepUserAddress(String nlatitude,String nradius,String citys ) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("Uaddress", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("nlatitude", nlatitude);
        editor.putString("nradius", nradius);
        editor.putString("citys", citys);
        //提交保存
        editor.commit();
    }

    /**
     * 取得经纬度
     * @return
     */
    public ArrayList<String> getUserAddress() {
        ArrayList<String> userInfoList = new ArrayList<>();
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("Uaddress", Context.MODE_PRIVATE); //私有数据
        String name = sharedPreferences.getString("nlatitude",""); //经度
        String pass = sharedPreferences.getString("nradius","");   //纬度
        String citys = sharedPreferences.getString("citys","");   //纬度
        userInfoList.add(name);
        userInfoList.add(pass);
        userInfoList.add(citys);
        return userInfoList;
    }
    /**
     * @param b
     */
    public void Keepshop_id(String b,String name) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("shop", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("shopId",b);
        editor.putString("shop_name",name);
        //提交保存
        editor.commit();
    }
    public String[] getShop_id() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("shop", Context.MODE_PRIVATE); //私有数据
        String[] shop_info = {sharedPreferences.getString("shopId",""),sharedPreferences.getString("shop_name","")};
        return   shop_info  ;
    }
    /**
     *
     * @param b
     */
    public void keeppeisong_id(String b) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("peisong", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("shopId",b);
        //提交保存
        editor.commit();
    }
    public String getpeisong_id() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("peisong", Context.MODE_PRIVATE); //私有数据
        String   shop_info = sharedPreferences.getString("shopId","");
        return   shop_info  ;
    }
    /**
     * 是否处于编辑状态
     * @param b
     */
    public void KeepisEdit(Boolean b) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("isEdit", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putBoolean("isEdit",b);
        //提交保存
        editor.commit();
    }
    public Boolean getIsEdit() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("isEdit", Context.MODE_PRIVATE); //私有数据
        return  sharedPreferences.getBoolean("isEdit",false);
    }
    /**
     *
     * @param b
     */
    public void keep_city_id(String b) {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("city_id_xml", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putString("city_id",b);
        //提交保存
        editor.commit();
    }
    public String get_city_id() {
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("city_id_xml", Context.MODE_PRIVATE); //私有数据
        String   city_id = sharedPreferences.getString("city_id","");
        return   city_id  ;
    }
}
