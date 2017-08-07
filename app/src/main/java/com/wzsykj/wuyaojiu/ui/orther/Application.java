package com.wzsykj.wuyaojiu.ui.orther;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import com.baidu.mapapi.SDKInitializer;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.wzsykj.wuyaojiu.openutil.UMUtil;
import com.wzsykj.wuyaojiu.service.LocationService;
import com.wzsykj.wuyaojiu.utils.LogUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;
import com.wzsykj.wuyaojiu.R;
import org.xutils.x;
import java.util.ArrayList;


/**
 * Created by chen on 16/6/15.
 */
public class Application extends MultiDexApplication {
    public ArrayList<Context> ContextList;
    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();

        /***
         * 初始化定位sdk，在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

        ContextList = new ArrayList<>();

        x.Ext.init(this);  //初始化Xutils

        LogUtils.LOG_DEBUG = true;

        SDKInitializer.initialize(this);

        initWelCome();

        UMUtil.initUM(getApplicationContext());

        UMUtil.initPushHandlerUM(getApplicationContext(), new UmengMessageHandler() {
                    @Override
                    public void dealWithNotificationMessage(Context context, UMessage uMessage) {
                        super.dealWithNotificationMessage(context, uMessage);
                    }

                    @Override
                    public void dealWithCustomMessage(Context context, UMessage uMessage) {
                        super.dealWithCustomMessage(context, uMessage);
                    }

                    @Override
                    public Notification getNotification(Context context, UMessage msg) {
                        switch (msg.builder_id) {
                            case 1:
                                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                                RemoteViews myNotificationView = new RemoteViews(context.getPackageName(), R.layout.notification_view);
                                myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                                myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                                myNotificationView.setImageViewBitmap(R.id.notification_large_icon, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
                                myNotificationView.setImageViewResource(R.id.notification_small_icon, R.mipmap.tabbar_found_0);
//                                myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
//                                myNotificationView.setImageViewResource(R.id.notification_small_icon, getSmallIconId(context, msg));
                                builder.setContent(myNotificationView)
                                        .setSmallIcon(R.mipmap.tabbar_home_1)
                                        .setTicker(msg.ticker)
                                        .setAutoCancel(true);

                                return builder.build();

                            default:
                                //默认为0，若填写的builder_id并不存在，也使用默认。
                                return super.getNotification(context, msg);
                        }
                    }
                }
                , new UmengNotificationClickHandler() {
                    @Override
                    public void dealWithCustomAction(Context context, UMessage uMessage) {
                        super.dealWithCustomAction(context, uMessage);
                    }
                }

        );

        ImageLoaderUtils.initImageLoader(

                getApplicationContext()

        );

        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setRegisterCallback(new IUmengRegisterCallback() {
            @Override
            public void onRegistered(String s) {
                     System.out.println("================="+s);
                     //ToastUtils.show(getApplicationContext(),s);
            }
        });
       //注册推送服务，每次调用register方法都会回调该接口
       /* mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onRegistered(String s) {

            }

         *//*   @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
            }

            @Override
            public void onFailure(String s, String s1) {

            }*//*
        });*/

    }

    /**
     * 初始信息获取
     */

    private void initWelCome() {
//        AppHttp.getInstance().getRetrofitApi().getWelCome("getFlashScreen").subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io())
//                .subscribe(new Observer<WelComeEntity>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//
//                    @Override
//                    public void onNext(WelComeEntity welComeEntity) {
//                        UserManage.getInstance().saveWelcomeEntity(getApplicationContext(), welComeEntity);
//                    }
//                });
    }


}
