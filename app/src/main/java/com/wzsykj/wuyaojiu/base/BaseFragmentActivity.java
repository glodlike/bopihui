package com.wzsykj.wuyaojiu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.wzsykj.wuyaojiu.openutil.UMUtil;
import com.wzsykj.wuyaojiu.utils.LogUtils;

/**
 * Created by chen on 16/6/15.
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    public static String LogTga = "BaseFragmentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogTga = getClass().toString();
        UMUtil.pushOnAppSatrt(this);
    }


    public void log(int logType, String tagStr, Object objects) {
        LogUtils.TAG = LogTga;
        LogUtils.log(logType, tagStr, objects);
    }


    @Override
    public void onResume() {
        super.onResume();
        UMUtil.mobclickAgentOnResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        UMUtil.mobclickAgentOnPause(this);
    }
}
