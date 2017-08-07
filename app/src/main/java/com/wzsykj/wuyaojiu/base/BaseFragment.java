package com.wzsykj.wuyaojiu.base;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;
import com.wzsykj.wuyaojiu.openutil.UMUtil;
import com.wzsykj.wuyaojiu.utils.DensityUtil;
import com.wzsykj.wuyaojiu.utils.LogUtils;
import com.wzsykj.wuyaojiu.R;

/**
 * Created by chen on 16/6/15.
 */
public abstract class BaseFragment extends Fragment {
    private LinearLayout baseErrorView, baseErrorContentView;
    private ImageView baseErrorImg;
    private TextView baseErrorMsg;
    private Button baseErrorBut;

    public Dialog materialishProgress;
    public static String LogTga = "BaseFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogTga = getClass().toString();
        initMaterialishProgress();
        UMUtil.pushOnAppSatrt(this.getActivity());
        return getContentView(inflater, container, savedInstanceState);
    }

    //获取布局文件ID
    protected abstract View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    /**
     * 子类主动调用方法初始化 error view
     */
    public void initErrorView(View view, View.OnClickListener onClickListener) {
        baseErrorView = (LinearLayout) view.findViewById(R.id.base_error_view);
        baseErrorContentView = (LinearLayout) view.findViewById(R.id.base_error_content_view);
        baseErrorImg = (ImageView) view.findViewById(R.id.base_error_img);
        baseErrorMsg = (TextView) view.findViewById(R.id.base_error_msg);
        baseErrorBut = (Button) view.findViewById(R.id.base_error_but);
        LinearLayout.LayoutParams baseErrorConLayoutParams = new LinearLayout.LayoutParams(DensityUtil.getWindowsWidth(this.getActivity()), DensityUtil.getWindowsHeight(this.getActivity()));
        baseErrorContentView.setLayoutParams(baseErrorConLayoutParams);
        baseErrorBut.setOnClickListener(onClickListener);
    }
    /**
     * 子类主动调用 显示error view
     */
    public void showErrorView(int img, String msg, String butTitle) {
        baseErrorView.setVisibility(View.VISIBLE);
        baseErrorImg.setImageResource(img);
        baseErrorMsg.setText(msg);
        baseErrorBut.setText(butTitle);
    }
    /**
     * 子类主动调用 隐藏error view
     */
    public void hiddeErrorView() {
        this.baseErrorView.setVisibility(View.GONE);
    }

    /**
     * 父类主动初始化 进度条
     */
    private void initMaterialishProgress() {
        materialishProgress = new Dialog(this.getActivity(),
                R.style.Dialog_Fullscreen_NoTitle);
        View contentView = LayoutInflater.from(this.getActivity()).inflate(
                R.layout.base_dialog, null);
        ProgressWheel progressWheel = (ProgressWheel) contentView.findViewById(R.id.progress_wheel);
        materialishProgress.requestWindowFeature(Window.FEATURE_NO_TITLE);
        materialishProgress.setContentView(contentView);
    }

    // 左边 进入
    public void toLeftStartActivity(Intent intent) {
        super.startActivity(intent);
        //参数一 是当前即将进入屏幕的view 的动画，参数二是当前view 的动画
        getActivity().overridePendingTransition(R.anim.push_a, R.anim.push_b);
    }

    //右边 消失
    public void toRightFinish() {
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.pop_a, R.anim.pop_b);
    }

    /**
     * 日志
     */
    public void log(int logType, String tagStr, Object objects) {
        LogUtils.TAG = LogTga;
        LogUtils.log(logType, tagStr, objects);
    }

    @Override
    public void onResume() {
        super.onResume();
        UMUtil.mobclickAgentOnResume(this.getActivity());
    }
    @Override
    public void onPause() {
        super.onPause();
        UMUtil.mobclickAgentOnPause(this.getActivity());
    }
}
