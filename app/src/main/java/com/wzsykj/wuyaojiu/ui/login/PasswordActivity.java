package com.wzsykj.wuyaojiu.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;


/**
 * Created by chen on 16/8/10.
 */
public class PasswordActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;

    private CountDownTimer countDownTimer;
    private TextView yzmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.password_activity, null);
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

        yzmBtn=(TextView)findViewById(R.id.userpwd_yzm_btn);
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.userpwd_yzm_btn:
                countDownTimer.start();
                break;
            default:
                break;
        }
    }
}
