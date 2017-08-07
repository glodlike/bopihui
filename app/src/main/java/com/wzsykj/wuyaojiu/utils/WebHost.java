package com.wzsykj.wuyaojiu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.wzsykj.wuyaojiu.Bean.GoodInfo;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.openutil.UMUtil;
import com.wzsykj.wuyaojiu.ui.good.GoodInfoActivity;
import com.wzsykj.wuyaojiu.ui.orther.TestActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/5/24 0024.
 */

public class WebHost {

    public Context context;
    private PopupWindow popupWindow;
    private LinearLayout ll;

    public WebHost(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void app_share(final String url, final String img, final String title, final String content) {

        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=deal&id=" + 181 + "&city_id=" + new SharePerfenceUtils(context).getShop_id()[0] + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    UMUtil.shareUM((Activity) context, title, content, url,
                            new UMImage(context, img), new UMShareListener() {
                                @Override
                                public void onResult(SHARE_MEDIA share_media) {

                                }
                                @Override
                                public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                                }
                                @Override
                                public void onCancel(SHARE_MEDIA share_media) {

                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
        });
    }
    @JavascriptInterface

    public  void  app_qr(String url){
        Intent intent = new Intent(context, TestActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
        //showPopwindows();
    }

    private void showPopwindows() {
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(context).inflate(
                    R.layout.code_layout, null);
            ll = (LinearLayout) contentView.findViewById(R.id.ll);
            popupWindow = new PopupWindow(contentView,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            popupWindow.setTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                }
            });
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
        }
        popupWindow.showAsDropDown(ll);
    }
}
