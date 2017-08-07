package com.wzsykj.wuyaojiu.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzsykj.wuyaojiu.R;

/**
 * Created by Mikes on 2016-5-12.
 */
public class ToastUtils {

    private ToastUtils() {
    }

    /**
     * Show a toast.
     *
     * @param context
     * @param resId
     */
    public static void show(Context context, int resId) {
        show(context, context.getApplicationContext().getString(resId));
    }

    /**
     * Show a toast.
     * default at center
     *
     * @param context
     * @param content
     */
    public static void show(Context context, String content) {
        if (context == null || content == null) {
            return;
        }
        Toast toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_global_toast, null);
        TextView textView = (TextView) view.findViewById(R.id.id_global_toast);
        textView.setText(content);
        toast.setView(view);
        toast.show();
    }

    /**
     * 显示toast信息
     *
     * @param context
     * @param message
     */
    private static Toast mToast;

    public static void showToast(Context context, String message) {
        if (mToast == null) {
            mToast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_global_toast, null);
            TextView textView = (TextView) view.findViewById(R.id.id_global_toast);
            textView.setText(message);
            mToast.setView(view);
            mToast.setGravity(Gravity.CENTER, 0, 0);// 居中显示
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_global_toast, null);
            TextView textView = (TextView) view.findViewById(R.id.id_global_toast);
            textView.setText(message);
            mToast.setView(view);
            mToast.setGravity(Gravity.CENTER, 0, 0);// 居中显示
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public static void showToast(Context context, int resid) {
        showToast(context, context.getApplicationContext().getString(resid));
    }

    /**
     * Show a toast.
     *
     * @param context
     * @param content
     */
    public static void showAtBottom(Context context, String content) {
        if (context == null || content == null) {
            return;
        }
        Toast toast = Toast.makeText(context.getApplicationContext(), content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_global_toast, null);
        TextView textView = (TextView) view.findViewById(R.id.id_global_toast);
        textView.setText(content);
        toast.setView(view);
        toast.show();
    }

    /**
     * Show a toast.
     *
     * @param context
     * @param resId
     */
    public static void showOnlyImage(Context context, int resId) {
        if (context == null || resId == -1) {
            return;
        }
        Toast toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_global_toast_only_image, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.id_global_toast_image);
        imageView.setImageResource(resId);
        toast.setView(view);
        toast.show();
    }

    private static final int SHOW_OFFSET = 2000;
    private static long lastShowTime = 0;

    /**
     * Show a toast and avoid to display too frequent.
     *
     * @param context
     * @param resId   the content will be displayed.
     * @param limit   whether limit the display moment.
     */
    public static void show(Context context, int resId, boolean limit) {
        show(context.getApplicationContext(), context.getApplicationContext().getString(resId), limit);
    }

    /**
     * Show a toast and avoid to display too frequent.
     *
     * @param context
     * @param content the content will be displayed.
     * @param limit   whether limit the display moment.
     */
    public static void show(Context context, String content, boolean limit) {
        long now = System.currentTimeMillis();
        long offset = now - lastShowTime;
        if (limit && offset > SHOW_OFFSET) {
            lastShowTime = now;
            show(context, content);
        } else if (!limit) {
            show(context, content);
        }
    }

}
