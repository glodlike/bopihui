package com.wzsykj.wuyaojiu.utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by CHP on 2016/8/16.
 */
public class XUtils {
    /**
     * 发送get请求
     * @param <T>
     */
    public static <T> Callback.Cancelable Get(String url, Map<String,String> map, Callback.CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);

        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
    /**
     * 发送get请求,添加头信息
     * @param <T>
     */
    public static <T> Callback.Cancelable GetWithToken(String url,String TokenValue, Map<String,String> map, Callback.CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.setCharset("utf-8");
        params.addHeader("token",TokenValue);
        if(null!=map){
            for(Map.Entry<String, String> entry : map.entrySet()){
                params.addQueryStringParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
    /**
     * 发送post请求
     * @param <T>
     */
    public static <T> Callback.Cancelable Post(String url, Map<String,Object> map, Callback.CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    /**
     * 待token的post请求方式
     * @param url
     * @param map
     * @param callback
     * @param <T>
     * @return
     */
    public static <T> Callback.Cancelable PostWithToken(String url,String TokenValue, Map<String,Object> map, Callback.CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.addHeader("token",TokenValue);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        Callback.Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }
    /**
     * 上传文件
     * @param <T>
     */
    public static <T> Callback.Cancelable UpLoadFile(String url,String TokenValue, Map<String,Object> map, Callback.CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        params.addHeader("token",TokenValue);
        if(null!=map){
            for(Map.Entry<String, Object> entry : map.entrySet()){
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
    /**
     * 下载文件
     * @param <T>
     */
    public static <T> Callback.Cancelable DownLoadFile(String url, String filepath, Callback.CommonCallback<T> callback){
        RequestParams params=new RequestParams(url);
        //设置断点续传
        params.setAutoResume(true);
        params.setSaveFilePath(filepath);
        Callback.Cancelable cancelable = x.http().get(params, callback);
        return cancelable;
    }
}


