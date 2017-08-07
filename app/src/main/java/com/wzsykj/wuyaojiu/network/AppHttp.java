package com.wzsykj.wuyaojiu.network;

import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chen on 16/6/20.
 */
public class AppHttp {
    //新泊啤汇
    //public static String BASE_URL = "http://www.bphapp.com/bopihui/cxb_api/";
    //public static String BASE_URL_NEW = "http://www.bphapp.com/bopihui/cxb_api/index.php?";
    // 519
    public static String BASE_URL = "http://v2.519wz.cn/cxb_api/";
    public static String BASE_URL_NEW = "http://v2.519wz.cn/cxb_api/index.php?";
    public  static AppHttp appHttp;
    private static Retrofit retrofit;
    public  static RetrofitApi retrofitApi;
    //private static OkHttpClient client;
    public interface AppHttpCallBack {
        void returnEntity(Object object, boolean isFromCache, Request request, Response response);
    }
     /**
      * apphttp 单例
      */
    public static AppHttp getInstance() {
        if (appHttp == null) {
            appHttp = new AppHttp();
        }
        return appHttp;
    }
    /**
     * retrofit 单例
     */
    private static Retrofit getRetrofit() {
        if (retrofit == null) {
//            if (client == null) {
//                client = new OkHttpClient.Builder()
//                        .addInterceptor(new Interceptor() {
//                            @Override
//                            public Response intercept(Chain chain) throws IOException {
//                                Request request = chain.request()
//                                        .newBuilder()
//                                        .addHeader("User-agent", "acvideo core")
//                                        .addHeader("productId", "2000")
//                                        .addHeader("deviceType", "1")
//                                        .addHeader("uid", "0")
//                                        .addHeader("appVersion", "4.1.8")
//                                        .addHeader("resolution", "1080x1776")
//                                        .addHeader("udid", "9627719a-6aa9-383b-bf57-ccdc2b12bd8e")
//                                        .addHeader("Host", "api.aixifan.com")
//                                        .addHeader("market", "tencent")
//                                        .addHeader("Connection", "Keep-Alive")
//                                        .addHeader("Accept-Encoding", "gzip")
//                                        .build();
//                                return chain.proceed(request);
//                            }
//
//                        })
//                        .build();
//            }
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .client(client)
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }

    /**
     * retrofitapi inteface 单例
     */
    public static RetrofitApi getRetrofitApi() {
        if (retrofitApi == null) {
            retrofitApi = getRetrofit().create(RetrofitApi.class);
        }
        return retrofitApi;
    }
    public interface RetrofitApi {

        /**
         * 欢迎页面 图片
         */
//        @GET("flashScreens/{getFlashScreen}")
//        Observable<WelComeEntity> getWelCome(@Path("getFlashScreen") String getFlashScreen);
//
//        /**
//         * 主页 数据
//         */
//        @GET("regions?")
//        Observable<TuiJianEntity> getTuijian(@Query("belong") String belong, @Query("loadCount") String loadCount);

        //http://api.aixifan.com/plays/3809354
        //http://api.aixifan.com/videos/2891772
        //http://danmu.aixifan.com/V3/3809354/1/500


        /**
         * 视频信息  http://api.aixifan.com/videos/2892241
         * */


        /**
         * 视频相关推荐  http://search.acfun.tv/like?id=ac2892241&pageSize=6&pageNo=1&type=1
         * */

        /**
         * 视频评论  http://mobile.acfun.tv/comment/content/list?version=4&contentId=2891772&pageSize=50&pageNo=1
         * */

    }


}
