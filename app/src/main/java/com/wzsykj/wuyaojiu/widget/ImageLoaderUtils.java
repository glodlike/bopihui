package com.wzsykj.wuyaojiu.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.wzsykj.wuyaojiu.R;

import java.io.File;

/**
 * Created by chen on 16/7/1.
 */
public class ImageLoaderUtils {
    public static String path = "/com.wzsykj.wyj/cache";


    public static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                path);
        //缓存文件的目录
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(100) //线程池内加载的数量
                .discCacheFileCount(100) //缓存的文件数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()) //将保存的时候的URI名称用MD5 加密
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
                .diskCacheSize(50 * 1024 * 1024)  // 50 Mb sd卡(本地)缓存的最大值
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                // 由原先的discCache -> diskCache
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();
        //全局初始化此配置
        ImageLoader.getInstance().init(config);
    }

    public static DisplayImageOptions getOptionsDefault() {
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.clearColor) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.color.clearColor) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.color.clearColor) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .delayBeforeLoading(1000)
                .bitmapConfig(Bitmap.Config.RGB_565)//设置为RGB565比起默认的ARGB_8888要节省大量的内存
                .build(); // 构建完成
        return options;
    }

    public static DisplayImageOptions getOptionsRounde(Context context, int round) {
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.clearColor) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.color.clearColor) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.color.clearColor) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(round)) // 设置成圆角图片
                .delayBeforeLoading(100)
                .bitmapConfig(Bitmap.Config.RGB_565)//设置为RGB565比起默认的ARGB_8888要节省大量的内存
                .build(); // 构建完成
        return options;
    }

    public static ImageAware getImageAware(ImageView view) {
        return new ImageViewAware(view, false);
    }

    /**
     * view add tag 确保重复加载闪烁问题
     */
    public static void LoadImage(String url, ImageView imageView, DisplayImageOptions options) {
        if (imageView.getTag() == null || !imageView.getTag().equals(url)) {
            imageView.setTag(url);
            ImageLoader.getInstance().displayImage(url, imageView, options);
        }
    }

    public static void onClearMemoryClick() {
        ImageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
    }

    public static void onClearDiskClick() {
        ImageLoader.getInstance().clearDiskCache();  // 清除本地缓存
    }
}
