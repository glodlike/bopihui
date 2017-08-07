package com.wzsykj.wuyaojiu.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Description:
 * User: chenzheng
 * Date: 2016/12/15 0015
 * Time: 14:05
 */
public class Utils {

    /**
     * @author chenzheng
     * @since 2014-5-9
     * @Description: 获取屏幕宽度
     * @throws
     * @param context
     * @return int
     */
    public static int getScreenW(Context context) {
        return getScreenSize(context, true);
    }

    /**
     * @author chenzheng
     * @since 2014-5-9
     * @Description: 获取屏幕高度
     * @throws
     * @param context
     * @return int
     */
    public static int getScreenH(Context context) {
        return getScreenSize(context, false);
    }

    private static int getScreenSize(Context context, boolean isWidth) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return isWidth ? dm.widthPixels : dm.heightPixels;
    }
}
