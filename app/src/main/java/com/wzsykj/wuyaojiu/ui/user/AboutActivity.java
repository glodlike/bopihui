package com.wzsykj.wuyaojiu.ui.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by chen on 16/8/10.
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private WebView webView;
    private String webHead = "<html>\n" +
            "<head>\n" +
            "<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;\" name=\"viewport\">\n" +
            "<style>img{max-width: 100%;}</style>\n" +
            "</head>\n" +
            "<body>\n";
    private String footStr ="</body>\n" +
            "</html>\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.about_activity, null);
        setContentView(contentView);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hiddeErrorView();
//                materialishProgress.show();
//                laodData();
            }
        });
        materialishProgress.show();
        initView();
    }

    private void initView() {
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.web_view);
        webView.setBackgroundColor(Color.parseColor("#ffffff"));
        loadData();
    }
    private void loadData() {
        showData();
    }
    private void showData() {
//        swipeLayout.setRefreshing(false);
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=notice&act=detail&id=2",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    JSONObject jsonObject1 = jsonObject.getJSONObject("result");
                    webView.loadDataWithBaseURL(null,webHead+jsonObject1.getString("content")+footStr,"text/html", "utf-8", null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                super.onFinished();
                materialishProgress.dismiss();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
