package com.wzsykj.wuyaojiu.ui.good;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.WebHost;

@SuppressLint("SetJavaScriptEnabled")
public class WebActivity extends BaseActivity {
    private WebView webView;
    private TextView txtTitle;
    private ImageButton imageButton;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.web_view);
        imageButton = (ImageButton) findViewById(R.id.back);
        url  = getIntent().getStringExtra("url");
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addCookies(WebActivity.this,url,getIntent().getStringExtra("pwd"),getIntent().getStringExtra("username"));
                webView.loadUrl(url);
                txtTitle = (TextView) findViewById(R.id.Title);
                WebSettings set = webView.getSettings();
                //JS
                set.setJavaScriptEnabled(true);
                set.setSaveFormData(false);
                set.setSavePassword(false);
                set.setSupportZoom(false);

                webView.addJavascriptInterface(new WebHost(WebActivity.this), "WyjJS");
                //
                String ua = set.getUserAgentString();
                set.setUserAgentString(ua + "WuyAoJiU519_");

                WebChromeClient wvcc = new WebChromeClient() {
                    @Override
                    public void onReceivedTitle(WebView view, String title) {
                        super.onReceivedTitle(view, title);
                        txtTitle.setText(title);
                    }
                };
                webView.setWebChromeClient(wvcc);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        //view.loadUrl(url);
                        if (url.contains("ctl=item")) {                                          //商品详情
                            Intent intent = new Intent(WebActivity.this, GoodInfoActivity.class);
                            intent.putExtra("come", "other");
                            intent.putExtra("goodID", url.split("=")[2]);
                            startActivity(intent);
                            return true;
                        }
                        return false;
                    }
                });
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
      }
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        } else {
            finish();
        }
        return false;
    }
    @Override
    protected void onDestroy()  {
        removeCookie(this);
        super.onDestroy();
    }
    public static void addCookies(Context context, String url, String user_pwd, String user_name) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(url,"user_pwd="+user_pwd+"");
        cookieManager.setCookie(url,"user_name="+user_name+"");
        CookieSyncManager.getInstance().sync();
    }

    private void removeCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }

}
