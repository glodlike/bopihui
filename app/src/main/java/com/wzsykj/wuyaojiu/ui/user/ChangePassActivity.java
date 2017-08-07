
package com.wzsykj.wuyaojiu.ui.user;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.Register;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class ChangePassActivity extends BaseActivity implements View.OnClickListener{
    private EditText phone;
    private EditText yzm;
    private EditText newPass;
    private Button   ChangeBtn;
    private TextView yzmBtn;
    private CountDownTimer countDownTimer;
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        InitView();
    }
    private void InitView() {
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        phone = (EditText) findViewById(R.id.userloging_sj_zh_edit);
        yzm = (EditText) findViewById(R.id.userloging_sj_yzm_edit);
        yzmBtn = (TextView) findViewById(R.id.userloging_sj_yzm_btn);
        yzmBtn.setOnClickListener(this);
        ChangeBtn  = (Button) findViewById(R.id.userloging_log_btn);
        newPass = (EditText) findViewById(R.id.newpass);
        ChangeBtn.setOnClickListener(this);

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
            case R.id.userloging_sj_yzm_btn:   //获得验证码
                getYzm(phone.getText().toString().trim());
                break;
            case R.id.userloging_log_btn:     // 修改
                ChangePassWord(phone.getText().toString().trim(),yzm.getText().toString().trim(),newPass.getText().toString().trim());
                break;
            case R.id.back:
                toRightFinish();
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
                    ToastUtils.show(ChangePassActivity.this,register.getInfo());
                }else {
                    countDownTimer.start();
                    ToastUtils.show(ChangePassActivity.this,register.getInfo());
                }
                super.onSuccess(result);
            }
        });
    }
   public  void  ChangePassWord(final String phone, final String yzm, final String newPass){
       XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=user&act=phmodifypassword&mobile="+phone+"&sms_verify="+yzm+"&new_pwd="+newPass+"",null,new MyCallBack<String>(){
           @Override
           public void onSuccess(String result) {
               super.onSuccess(result);
               try {
                   JSONObject js = new JSONObject(StringUtils.base64ToString(result));
                   if (js.getInt("status")==1){
                       ToastUtils.show(ChangePassActivity.this,"修改成功");
                       finish();
                   }else {
                       ToastUtils.show(ChangePassActivity.this,js.getString("info"));
                   }

               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }
           @Override
           public void onFinished() {
               super.onFinished();
           }
       });
   }
}
