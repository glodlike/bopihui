package com.wzsykj.wuyaojiu.ui.good;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.utils.WebHost;

public class JsShareActivity extends BaseActivity {
    private WebView webView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_share);
        webView = (WebView) findViewById(R.id.web_view);

        imageButton = (ImageButton) findViewById(R.id.back);
        WebSettings set = webView.getSettings();
        // ֧��JS
        set.setJavaScriptEnabled(true);
        set.setSaveFormData(false);
        set.setSavePassword(false);
        set.setSupportZoom(false);
        webView.loadUrl(getIntent().getStringExtra("url"));
        webView.addJavascriptInterface(new WebHost(JsShareActivity.this), "WyjJS");
    }
}
