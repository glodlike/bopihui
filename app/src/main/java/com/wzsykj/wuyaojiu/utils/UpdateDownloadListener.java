package com.wzsykj.wuyaojiu.utils;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/12/14 0014
 * Time: 16:23
 */
public interface UpdateDownloadListener {

    public void onStarted();
    public void onProgressChanged(int progress, String downloadUrl);
    public void onFinished(float completeSize, String downloadUrl);
    public void onFailure();
}
