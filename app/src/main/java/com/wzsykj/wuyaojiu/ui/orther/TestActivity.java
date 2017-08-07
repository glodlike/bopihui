package com.wzsykj.wuyaojiu.ui.orther;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.utils.EncodingHandler;
import com.wzsykj.wuyaojiu.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestActivity extends BaseActivity {
    private ImageView erweima;
    private Bitmap qrcodeBitmap;
    private Button save;
    private Button giveup;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_layout);
        erweima = (ImageView) findViewById(R.id.erweima);
        save = (Button) findViewById(R.id.resetBtn);
        giveup = (Button) findViewById(R.id.sureBtn);
        try {
            qrcodeBitmap = EncodingHandler.createQRCode(getIntent().getStringExtra("url"), 400, null);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        erweima.setImageBitmap(qrcodeBitmap);

        giveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(TestActivity.this, "没有检测到内存卡", Toast.LENGTH_LONG).show();
                    return;
                }
                File file = new File("/sdcard/ErWeiCode/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File imageFile = new File(file, "二维码.png");

                try {
                    imageFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(imageFile);
                    erweima.setDrawingCacheEnabled(true);
                    Bitmap obmp = Bitmap.createBitmap(erweima.getDrawingCache());
                    erweima.setDrawingCacheEnabled(false);
                    obmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    fos.flush();
                    fos.close();
                    ToastUtils.show(TestActivity.this, "图片已经保存至" + imageFile);
                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
