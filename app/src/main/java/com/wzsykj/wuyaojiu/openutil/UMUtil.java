package com.wzsykj.wuyaojiu.openutil;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.UmengRegistrar;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import java.util.List;

/**
 * UM 相关操作
 */
public class UMUtil {

    /**
     * 初始化配置
     *
     * @param context 上下文
     */
    public static void initUM(Context context) {
        //友盟统计
        MobclickAgent.setDebugMode(true);
        //友盟推送
        initPushUM(context);
        //友盟分享
        PlatformConfig.setWeixin(OpenConfig.WXAppID, OpenConfig.WXAppSecret);
        //微信 appid appsecret
        PlatformConfig.setQQZone(OpenConfig.QQAppID, OpenConfig.QQKey);
        // QQ和Qzone appid appkey
    }
    /**
     * Onresume 统计 方法
     * @param context 上下文
     *                用于页面重现时统计
     */
    public static void mobclickAgentOnResume(Context context) {
        MobclickAgent.onResume(context);//统计时长
    }

    /**
     * onPause 统计 方法
     *
     * @param context 上下文
     *                用于页面停止时统计
     */
    public static void mobclickAgentOnPause(Context context) {
        MobclickAgent.onPause(context);//统计时长
    }


    /**
     * 登录
     *
     * @param activity       上下文
     * @param platform       平台类型
     * @param umAuthListener 验证回调
     *                       注意: 参与activity 需要 重写onActivityResult()
     * @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     * super.onActivityResult(requestCode, resultCode, data);
     * mShareAPI.onActivityResult(requestCode, resultCode, data);
     * }
     */
    public static void logUM(Activity activity, SHARE_MEDIA platform, UMAuthListener umAuthListener) {
        UMShareAPI.get(activity).doOauthVerify(activity, platform, umAuthListener);
    }
    /**
     * 获取登录用户信息
     *
     * @param activity       上下文
     * @param platform       平台类型
     * @param umAuthListener 验证回调
     *                       注意: 参与activity 需要 重写onActivityResult()
     * @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     * super.onActivityResult(requestCode, resultCode, data);
     * mShareAPI.onActivityResult(requestCode, resultCode, data);
     * }
     * <p/>
     * 注意必须在调用授权接口mShareAPI.doOauthVerify(this, platform, umAuthListener)
     * 之后才可以获取用户信息,建议在doOauthVerify方法完成的onComplete方法中调用获取用户信息方法,
     * 返回的所有信息都在Map data)集合中获取
     */
    public static void getLogInfoUM(Activity activity, SHARE_MEDIA platform, UMAuthListener umAuthListener) {
        UMShareAPI.get(activity).getPlatformInfo(activity, platform, umAuthListener);
    }
    /**
     * 获取客户端是否安装
     *
     * @param activity 上下文
     * @param platform app类型
     */
    public static void getInstallAllUM(Activity activity, SHARE_MEDIA platform) {
        UMShareAPI.get(activity).isInstall(activity, platform);
    }
    /**
     * 分享
     * <p/>
     * 默认 微信好友 朋友圈 QQ QQ空间选项
     *
     * @param activity 上下文
     */
    public static void shareUM(Activity activity, String title, String body, String url, UMImage image, UMShareListener umShareListener) {
        new ShareAction(activity).setDisplayList(new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE
                })
                .withText(body)
                .withTitle(title)
                .withTargetUrl(url)
                .withMedia(image)
                .setListenerList(umShareListener)
                .open();
    }


    /**
     * 开启推送服务
     *
     * @param context 上下文
     *                可以通过接口 mPushAgent.disable(); 来关闭客户端的通知服务。
     *                通过mPushAgent.isEnabled() 来查询状态。 状态表示有没有启用/关闭推送功能， 不表示推送后台服务的运行状态。
     */
    public static void initPushUM(Context context) {
        //开启推送并设置注册的回调处理
        PushAgent.getInstance(context).enable(new IUmengRegisterCallback() {

            @Override
            public void onRegistered(final String registrationId) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        //onRegistered方法的参数registrationId即是device_token
                        System.out.println("device_token==========" + registrationId);
                    }
                });
            }

        });
        String device_token = UmengRegistrar.getRegistrationId(context);
    }
    /**
     * 推送消息绑定回调 推送点击绑定回调
     * <p/>
     * 该Handler是在BroadcastReceiver中被调用，故
     * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
     */
    public static void initPushHandlerUM(Context context, UmengMessageHandler umengMessageHandler, UmengNotificationClickHandler umengNotificationClickHandler) {
        PushAgent.getInstance(context).setMessageHandler(umengMessageHandler);
        PushAgent.getInstance(context).setNotificationClickHandler(umengNotificationClickHandler);
    }
    /**
     * 添加标签
     *
     * @param context 上下文
     * @param tag     标签 限制在1024个， 每个tag 最大256个字符。
     */
    public static void setPushTagUM(Context context, String... tag) throws Exception {
        PushAgent.getInstance(context).getTagManager().add(tag);
    }

    /**
     * 更新标签
     *
     * @param context 上下文
     * @param tag     标签 限制在1024个， 每个tag 最大256个字符。
     */
    public static void updatePushTagUM(Context context, String... tag) throws Exception {
        PushAgent.getInstance(context).getTagManager().update(tag);
    }

    /**
     * 删除指定标签
     *
     * @param context   上下文
     * @param tag==null 移除所有
     */
    public static void removePushTagUM(Context context, String... tag) throws Exception {
        if (tag == null) {
            PushAgent.getInstance(context).getTagManager().reset();
        } else {
            PushAgent.getInstance(context).getTagManager().add(tag);
        }
    }

    /**
     * 获取后台所有标签
     *
     * @param context 上下文
     */
    public static List<String> getWebAllTag(Context context) throws Exception {
        return PushAgent.getInstance(context).getTagManager().list();
    }

    /**
     * 设置别名
     *
     * @param context 上下文
     * @param alias   别名
     * @param type    三方平台类型
     */
    public static void setPushAliasUM(Context context, String alias, String type) {
        PushAgent.getInstance(context).setAlias(alias, type);
    }

    /**
     * 移除别名
     *
     * @param context 上下文
     * @param alias   别名
     * @param type    三方平台类型
     */
    public static void removePushAliasUM(Context context, String alias, String type) {
        PushAgent.getInstance(context).setAlias(alias, type);
    }

    /**
     * 统计应用启动数据
     *
     * @param context 上下文
     */
    public static void pushOnAppSatrt(Context context) {
        PushAgent.getInstance(context).onAppStart();
    }


}
