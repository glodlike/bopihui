package com.wzsykj.wuyaojiu.ui.login;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.wzsykj.wuyaojiu.Bean.UserInfo;
import com.wzsykj.wuyaojiu.ui.user.ForgetPassActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.Register;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.openutil.UMUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * Created by chen on 16/8/10.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private String id;
    private ImageButton back;

    private RadioGroup radioGroup;
    private RadioButton dlRadio, sjRadio;
    private LinearLayout dlLayout, sjLayout;

    private CountDownTimer countDownTimer;
    private TextView yzmBtn;

    private TextView wjmmBtn;
    private Button zcBtn;

    private Button Login;

    private ImageView weixin, qq;

    private EditText phoneEditText;    // 手机号   手机号加密码登录
    private EditText pwdEditText;      // 密码

    private EditText moblieEditText;   // 手机号  手机号加验证码登录
    private EditText YamEditText;      // 验证码   手机号加验证码登录

    private CheckBox isCansee;         // 密码是否可见
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this);
            ((Activity) context).finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.login_activity, null);
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

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("reg");
        registerReceiver(this.broadcastReceiver, filter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        isCansee   = (CheckBox) findViewById(R.id.userloging_mm_btn);

        Login   = (Button) findViewById(R.id.userloging_log_btn);
        Login.setOnClickListener(this);

        phoneEditText = (EditText) findViewById(R.id.userloging_zh_edit);
        pwdEditText   = (EditText) findViewById(R.id.userloging_mm_edit);

        moblieEditText = (EditText)findViewById(R.id.userloging_sj_zh_edit);
        YamEditText    = (EditText) findViewById(R.id.userloging_sj_yzm_edit);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        dlRadio = (RadioButton) findViewById(R.id.userloging_dl_radio);
        sjRadio = (RadioButton) findViewById(R.id.userloging_sj_radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.userloging_dl_radio:
                        dlLayout.setVisibility(View.VISIBLE);
                        sjLayout.setVisibility(View.GONE);
                        break;
                    case R.id.userloging_sj_radio:
                        dlLayout.setVisibility(View.GONE);
                        sjLayout.setVisibility(View.VISIBLE);
                        break;
                    default:
                        break;
                }
            }
        });
        isCansee.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (b){
                             pwdEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                          }
                        else {
                            //pwdEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            pwdEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        }
                   }
        });
        dlLayout = (LinearLayout) findViewById(R.id.userloging_dl_linear);
        sjLayout = (LinearLayout) findViewById(R.id.userloging_sj_linear);

        wjmmBtn = (TextView) findViewById(R.id.userloging_wjmm_btn);
        wjmmBtn.setOnClickListener(this);
        zcBtn = (Button) findViewById(R.id.userloging_zc_btn);
        zcBtn.setOnClickListener(this);

        weixin = (ImageView) findViewById(R.id.weixin);
        weixin.setOnClickListener(this);
        qq = (ImageView) findViewById(R.id.qq);
        qq.setOnClickListener(this);

        yzmBtn=(TextView)findViewById(R.id.userloging_sj_yzm_btn);
        yzmBtn.setOnClickListener(this);
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
        loadData();
    }

    private void loadData() {
        showData();
    }
    private void showData() {
//        swipeLayout.setRefreshing(false);
        materialishProgress.dismiss();
    }
    private void umLogin(SHARE_MEDIA type) {
        UMUtil.logUM(LoginActivity.this, type, new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, final Map<String, String> map) {

                UMUtil.getLogInfoUM(LoginActivity.this, share_media, new UMAuthListener() {
                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, final Map<String, String> map) {
                        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=synclogin&act=weixin&unionid="+map.get("unionid")+"",null,new MyCallBack<String>(){
                            @Override
                            public void onSuccess(String result) {
                                try {
                                    JSONObject userInfo = new JSONObject(StringUtils.base64ToString(result));
                                    if (userInfo.getInt("status")==0){
                                        Intent intent = new Intent(LoginActivity.this,WeiXinLoginActivity.class);
                                        intent.putExtra("userinfo",new Gson().toJson(map));
                                        startActivity(intent);
                                        finish();
                                    }
                                    else  {
                                        new SharePerfenceUtils(LoginActivity.this).keepUserInfo(userInfo.getString("mobile"),userInfo.getString("user_pwd"));
                                        finish();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                super.onSuccess(result);
                            }
                        });

                    }
                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {


                    }
                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });


             ToastUtils.show(LoginActivity.this,map.get("nickname"));
                final String userInfo = new Gson().toJson(map);
                try {
                    final JSONObject jsonObject = new JSONObject(userInfo);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
              ToastUtils.show(LoginActivity.this,"服务器繁忙，请稍后再试");

            }
            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                ToastUtils.show(LoginActivity.this,"取消授权");
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.userloging_zc_btn:
                startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class),001);
                //toLeftStartActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.userloging_wjmm_btn:
                toLeftStartActivity(new Intent(LoginActivity.this, ForgetPassActivity.class));
                break;
            case R.id.weixin:
                umLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.qq:
                umLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.userloging_sj_yzm_btn:

                getYzm(moblieEditText.getText().toString().trim());
                break;

            case R.id.userloging_log_btn:
                 if (dlRadio.isChecked()){
                        DophoneLogin(phoneEditText.getText().toString().trim(),pwdEditText.getText().toString().trim());           //账号密码登录
                    }else {
                        RegisterAndLogin(moblieEditText.getText().toString().trim(),YamEditText.getText().toString().trim(),null);  //手机号加验证码登录
                      }
                 break;
            default:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 验证码登录
     * @param phoneNum
     * @param yzm
     * @param pass
     */
    public void RegisterAndLogin(String phoneNum,String yzm,String pass){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=user&act=dophlogin&mobile="+phoneNum+"&sms_verify="+yzm+"&user_pwd="+pass+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                UserInfo userInfo = new Gson().fromJson(StringUtils.base64ToString(result),UserInfo.class);
                if (userInfo.getStatus()==1){ // 成功注册
                    //保存用户名和密码
                    new SharePerfenceUtils(LoginActivity.this).keepUserInfo(userInfo.getMobile(),userInfo.getUser_pwd());
                    finish();
                }else {
                    ToastUtils.show(LoginActivity.this,userInfo.getInfo());
                }
                super.onSuccess(result);
            }
        });
    }
    /**
     * 密码登录
     * @param phoneNum
     * @param yzm
     */
    public void DophoneLogin(final String phoneNum, final String yzm){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=user&act=dologin&user_key="+phoneNum+"&user_pwd="+yzm+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println("========"+AppHttp.BASE_URL_NEW+"ctl=user&act=dologin&user_key="+phoneNum+"&user_pwd="+yzm+"");
                UserInfo userInfo = new Gson().fromJson(StringUtils.base64ToString(result),UserInfo.class);
                if (userInfo.getStatus()==1){ // 成功注册
                    //保存用户名和密码
                    new SharePerfenceUtils(LoginActivity.this).keepUserInfo(userInfo.getMobile(),userInfo.getUser_pwd());
                    finish();
                }else {
                    //Toast.makeText(LoginActivity.this,,Toast.LENGTH_SHORT).show();
                    ToastUtils.show(LoginActivity.this,userInfo.getInfo());
                }
                super.onSuccess(result);
            }
        });
    }
    /**
     * 获取验证码
     * @param phoneNum
     */
    //http://v2.519wz.cn/cxb_api/index.php?ctl=sms&act=send_sms_code&mobile=13634146852&unique=1
    public  void  getYzm(String phoneNum){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=sms&act=send_sms_code&mobile="+phoneNum+"&unique=0",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                Register register = new Gson().fromJson(StringUtils.base64ToString(result),Register.class);
                if (register.getStatus()==0){
                    //Toast.makeText(LoginActivity.this,register.getInfo(),Toast.LENGTH_SHORT).show();
                    ToastUtils.show(LoginActivity.this,register.getInfo());
                }else {
                    countDownTimer.start();
                    ToastUtils.show(LoginActivity.this,register.getInfo());
                }
                super.onSuccess(result);
            }
        });
    }
}
