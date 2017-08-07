package com.wzsykj.wuyaojiu.utils;

import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;

/***
 * dialog文件下载
 * 
 * 
 * 
 */
public class DownloadDialog extends Dialog implements
		View.OnClickListener {
	private static final int DOWNLOAD_PREPARE = 0;
	private static final int DOWNLOAD_WORK = 1;
	private static final int DOWNLOAD_OK = 2;
	private static final int DOWNLOAD_ERROR = 3;
	private static final String TAG = "IndexActivity";
	private static Context mContext;

	private Button bt,qx;
	private ProgressBar pb;
	/** 下载过程中不能点击 */
	private boolean isClick = false;
	private boolean downloadOk = false;
	private TextView tv;
	/**
	 * 下载的url
	 */
	private String url = null;
	private String filePath;

	/**
	 * 文件大小
	 */
	int fileSize = 0;

	/**
	 * 下载的大小
	 */
	int downloadSize = 0;

	/**
	 * handler
	 */
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWNLOAD_PREPARE:
				pb.setVisibility(ProgressBar.VISIBLE);
				Log.e(TAG, "文件大小:" + fileSize);
				pb.setMax(fileSize);
				break;
			case DOWNLOAD_WORK:
				Log.e(TAG, "已经下载:" + downloadSize);
				pb.setProgress(downloadSize);
				int res = downloadSize * 100 / fileSize;
				tv.setText("已下载：" + res + "%");
				/*bt.setText(FileUtil.FormetFileSize(downloadSize) + "/"
						+ FileUtil.FormetFileSize(fileSize));*/
				break;
			case DOWNLOAD_OK:
				downloadOk = true;
				bt.setText("安装新版本");
				downloadSize = 0;
				fileSize = 0;
				break;
			case DOWNLOAD_ERROR:
				downloadSize = 0;
				fileSize = 0;
				break;
			}
			super.handleMessage(msg);
		}
	};


	public DownloadDialog(Context context, String url) {

		super(context, R.style.Theme_CustomDialog);
		mContext = context;
		this.url = url;
		filePath = FileUtil.getPath(mContext, url);
	}
	@Override
	public void cancel() {
		super.cancel();
	}

	/**
	 * 下载文件
	 */
	private void downloadFile() {
		try {
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			InputStream is = conn.getInputStream();
			fileSize = conn.getContentLength();
			if (fileSize < 1 || is == null) {
				sendMessage(DOWNLOAD_ERROR);
			} else {
				sendMessage(DOWNLOAD_PREPARE);
				FileOutputStream fos = new FileOutputStream(filePath);
				byte[] bytes = new byte[1024];
				int len = -1;
				while ((len = is.read(bytes)) != -1) {
					fos.write(bytes, 0, len);
					fos.flush();
					downloadSize += len;
					sendMessage(DOWNLOAD_WORK);
				}
				sendMessage(DOWNLOAD_OK);
				is.close();
				fos.close();
			}
		} catch (Exception e) {
			sendMessage(DOWNLOAD_ERROR);
			e.printStackTrace();
		}
	}
	/***
	 * 得到APK的路径
	 * 
	 * @return
	 */
	public String getFilePath() {
		return filePath;
	}
	private void init() {
		bt = (Button) this.findViewById(R.id.down_bt);
		qx=(Button)this.findViewById(R.id.quxiao);
		bt.setOnClickListener(this);
		qx.setOnClickListener(this);
		tv = (TextView) this.findViewById(R.id.down_tv);
		pb = (ProgressBar) this.findViewById(R.id.down_pb);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.down_bt:{
			if (isClick) {
				// 启动一个线程下载文件
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						downloadFile();
					}
				});
				thread.start();
				isClick = false;
			}

			if (downloadOk) // 下载完成后 ，打开APK
			{
				Intent intent = new Intent(Intent.ACTION_VIEW); 
				 intent.setDataAndType(Uri.fromFile(new File(getFilePath())), "application/vnd.android.package-archive"); 
				  DownloadDialog.mContext.startActivity(intent);

				cancel();
			}
			break;
		}case R.id.quxiao :{
			cancel();
		}
		}
}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.download_layuot);
		init();
	}

	/**
	 * @param what
	 */
	private void sendMessage(int what) {
		Message m = new Message();
		m.what = what;
		handler.sendMessage(m);
	}


	@Override
	public void show() {
		isClick = true;
		downloadOk = false;
		super.show();
	}

}
