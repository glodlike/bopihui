package com.wzsykj.wuyaojiu.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;


/**
 * Created by chen on 16/8/10.
 */
public class ShareActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.share_activity, null);
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
            default:
                break;
        }
    }
}
