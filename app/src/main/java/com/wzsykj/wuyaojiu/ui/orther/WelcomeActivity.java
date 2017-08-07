package com.wzsykj.wuyaojiu.ui.orther;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.ui.good.GoodInfoActivity;
import com.wzsykj.wuyaojiu.ui.good.GoodListActivity;
import com.wzsykj.wuyaojiu.ui.good.WebActivity;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by chen on 16/6/22.
 */
public class WelcomeActivity extends BaseActivity {
    private TextView time;
    private ImageView imageView;
    private CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        time = (TextView) findViewById(R.id.time);
        imageView = (ImageView) findViewById(R.id.image_view);
        getWel();

    }
    public  void  getWel(){
                String type = "";
                try {
                    JSONObject jsonObject = new JSONObject(getIntent().getStringExtra("start"));
                    if (jsonObject.toString().contains("start_page")||jsonObject.getJSONArray("start_page").length()==0){
                        finish();
                        Intent toMain = new Intent(WelcomeActivity.this, MainActivity.class);
                        startActivity(toMain);
                    }else {
                        timer = new CountDownTimer(3000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                time.setText((millisUntilFinished / 1000) + " 秒");
                            }
                            @Override
                            public void onFinish() {
                                time.setText("0 秒");
                                Intent toMain = new Intent(WelcomeActivity.this, MainActivity.class);
                                startActivity(toMain);
                                finish();
                            }
                        };
                    ImageLoaderUtils.LoadImage(jsonObject.getJSONArray("start_page").getJSONObject(0).getString("img"),imageView,ImageLoaderUtils.getOptionsDefault());
                    type = jsonObject.getJSONArray("start_page").getJSONObject(0).getString("type");
                    if (type.equals("12")){
                    final String tid = jsonObject.getJSONArray("start_page").getJSONObject(0).getJSONObject("data").getString("cate_id");

                        imageView.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              timer.cancel();
                              Intent intent = new Intent(WelcomeActivity.this,GoodListActivity.class);//商品列表
                              intent.putExtra("id", tid);
                              intent.putExtra("type","cate_id");
                              startActivity(intent);
                          }
                      });
                    }else if (type.equals("0")){
                        final String tid  = jsonObject.getJSONArray("start_page").getJSONObject(0).getJSONObject("data").getString("url");
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                timer.cancel();
                                Intent intent = new Intent(WelcomeActivity.this,WebActivity.class);//商品列表
                                intent.putExtra("url", tid);
                                startActivity(intent);
                            }
                        });
                    }
                    else  if (type.equals("21")){
                        final String tid  = jsonObject.getJSONArray("start_page").getJSONObject(0).getJSONObject("data").getString("item_id");
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                timer.cancel();
                                Intent intent = new Intent(WelcomeActivity.this,GoodInfoActivity.class);//商品列表
                                intent.putExtra("goodID", tid);
                                intent.putExtra("come","welcome");
                                //startActivity(intent);
                                toLeftStartActivity(intent);
                                finish();
                            }
                        });

                    }else if (type.equals("11")){
                        final String tid  = jsonObject.getJSONArray("start_page").getJSONObject(0).getJSONObject("data").getString("tid");
                        imageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                              /*  Intent intent = new Intent(WelcomeActivity.this,WebActivity.class);//商品列表
                                intent.putExtra("id", tid);
                                intent.putExtra("url","cate_id");
                                startActivity(intent);*/
                            }
                        });

                       }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

    }
}
