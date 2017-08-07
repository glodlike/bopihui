package com.wzsykj.wuyaojiu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.UserInfo;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.Register;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;


/**
 * Created by chen on 16/8/10.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private Button LoginBtn;
    private CountDownTimer countDownTimer;
    private TextView yzmBtn;
    private EditText  telEditText;   // 手机号输入框
    private EditText  yzEditText ;   // 验证码输入框
    private EditText  pswEditText;   // 密码输入框
    private CheckBox isCansee;   // 设置密码是否可见


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.register_activity, null);
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
        LoginBtn  = (Button) findViewById(R.id.userregister_zc_btn);


        telEditText = (EditText) findViewById(R.id.userregister_zh_edit);
        yzEditText  = (EditText) findViewById(R.id.userregister_yzm_edit);
        pswEditText = (EditText) findViewById(R.id.userregister_mm_edit);
        isCansee    = (CheckBox) findViewById(R.id.userregister_mm_btn);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        LoginBtn.setOnClickListener(this);

        yzmBtn=(TextView)findViewById(R.id.userregister_yzm_btn);
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
          // swipeLayout.setRefreshing(false);
          materialishProgress.dismiss();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.userregister_yzm_btn:
                getYzm(telEditText.getText().toString().trim());
                break;
            case R.id.userregister_zc_btn:
                //登录
                RegisterAndLogin(telEditText.getText().toString().trim(),yzEditText.getText().toString().trim(),pswEditText.getText().toString().trim());
               /* Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                intent.putExtra("id","back");
                startActivity(intent);
                Toast.makeText(RegisterActivity.this,"回去",Toast.LENGTH_SHORT).show();*/
                break;
            default:
                break;
        }
    }
    /**
     * 获取验证码
     * @param phoneNum
     */
    //http://v2.519wz.cn/cxb_api/index.php?ctl=sms&act=send_sms_code&mobile=13634146852&unique=1
    public  void  getYzm(String phoneNum){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=sms&act=send_sms_code&mobile="+phoneNum+"&unique=1",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                Register register = new Gson().fromJson(StringUtils.base64ToString(result),Register.class);
                if (register.getStatus()==0){
                    Toast.makeText(RegisterActivity.this,register.getInfo(),Toast.LENGTH_SHORT).show();
                }else {
                    countDownTimer.start();
                    Toast.makeText(RegisterActivity.this, register.getInfo(),Toast.LENGTH_SHORT).show();
                      }
                super.onSuccess(result);
            }
        });
    }
    /**
     * 注册并登录
     * @param phoneNum
     * @param yzm
     * @param pass
     */
    public void RegisterAndLogin(String phoneNum,String yzm,String pass){
          XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=user&act=dophlogin&mobile="+phoneNum+"&sms_verify="+yzm+"&user_pwd="+pass+"&is_reg=1",null,new MyCallBack<String>(){
               @Override
               public void onSuccess(String result) {
                  UserInfo userInfo = new Gson().fromJson(StringUtils.base64ToString(result),UserInfo.class);
                    if (userInfo.getStatus()==1){ // 成功注册
                        close();
                        //保存用户名和密码
                        new SharePerfenceUtils(RegisterActivity.this).keepUserInfo(userInfo.getUser_name(),userInfo.getUser_pwd());
                        Intent  intent = new Intent();
                        //把返回数据存入Intent
                        //设置返回数据
                        RegisterActivity.this.setResult(RESULT_OK, intent);
                        //关闭Activity
                        RegisterActivity.this.finish();  //直接返回我的界面
                    }else {
                         Toast.makeText(RegisterActivity.this,userInfo.getInfo(),Toast.LENGTH_SHORT).show();
                    }
                         super.onSuccess(result);
               }
           });
       }
    public void close() {
        Intent intent = new Intent();
        intent.setAction("reg"); // 说明动作
        sendBroadcast(intent);// 该函数用于发送广播
    }

}
