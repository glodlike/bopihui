package com.wzsykj.wuyaojiu.ui.login;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.Register;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

public class WeiXinLoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText phone;
    private EditText yzm;
    private TextView yzmBtn;
    private Button   login;
    private String   userInfo;
    private String   unionid;
    private String   avatar;
    private String   user_name;
    private JSONObject js;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_xin_login);
        InitView();
        try {
            js   = new JSONObject(getIntent().getStringExtra("userinfo"));
            unionid    =  js.getString("unionid");
            avatar     =  js.getString("profile_image_url");
            user_name  =  js.getString("screen_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void InitView() {
           phone = (EditText) findViewById(R.id.userloging_sj_zh_edit);
           yzm = (EditText) findViewById(R.id.userloging_sj_yzm_edit);
           yzmBtn = (TextView) findViewById(R.id.userloging_sj_yzm_btn);
           yzmBtn.setOnClickListener(this);
           login  = (Button) findViewById(R.id.userloging_log_btn);
           login.setOnClickListener(this);

        countDownTimer=new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                yzmBtn.setClickable(false);
                yzmBtn.setText((millisUntilFinished / 1000) +"秒");
            }
            @Override
            public void onFinish() {
                yzmBtn.setClickable(true);
                yzmBtn.setText("发送验证码");
               }
            };
         }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.userloging_log_btn:     // 绑定登录
                Login(phone.getText().toString().trim(),yzm.getText().toString().trim(),unionid,avatar,user_name);
                break;
            case R.id.userloging_sj_yzm_btn:  //获取验证码
                getYzm(phone.getText().toString().trim());
                break;
        }
    }
    /**
     * 获取验证码
     * @param phoneNum
     */
    public  void  getYzm(String phoneNum){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=sms&act=send_sms_code&mobile="+phoneNum+"&unique=0",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                Register register = new Gson().fromJson(StringUtils.base64ToString(result),Register.class);
                if (register.getStatus()==0){
                    //Toast.makeText(LoginActivity.this,register.getInfo(),Toast.LENGTH_SHORT).show();
                    ToastUtils.show(WeiXinLoginActivity.this,register.getInfo());
                }else {
                    countDownTimer.start();
                    ToastUtils.show(WeiXinLoginActivity.this,register.getInfo());
                }
                super.onSuccess(result);
            }
        });
    }
    private void Login(final String s, final String trim, final String unionid, final String avatar, final String user_name) {
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=user&act=dophbind_register&mobile="+s+"&sms_verify="+trim+"&unionid="+unionid+"&avatar="+avatar+"&user_name="+StringUtils.StringToUTF(user_name)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    if (jsonObject.getInt("status")==1){
                        new SharePerfenceUtils(WeiXinLoginActivity.this).keepUserInfo(jsonObject.getString("mobile"),jsonObject.getString("user_pwd"));
                        finish();
                    }else{
                        ToastUtils.show(WeiXinLoginActivity.this,jsonObject.getString("info"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
        });
    }
}
