package com.wzsykj.wuyaojiu.ui.user;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.HeadUrl;
import com.wzsykj.wuyaojiu.Bean.UserCenter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.manage.UserManage;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.http.RequestParams;
import org.xutils.x;
import java.io.File;
import java.util.ArrayList;
/**
 * Created by chen on 16/8/10.
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private ArrayList<String> userList;
    private LinearLayout img, name, pwd, phone;
    private ImageView userImg;
    private AlertDialog.Builder selectImgDialog;
    private Uri mOutPutFileUri;//拍照设置的照片存储预置路径
    private String userImgPath;//全局图片路径
    private TextView phoneTV;
    private EditText nicheng;
    private ImageView edit;
    private  boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.userinfo_activity, null);
        setContentView(contentView);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hiddeErrorView();
//                materialishProgress.show();
//                laodData();
            }
        });
        userList = new SharePerfenceUtils(this).getUserInfo();
        //materialishProgress.show();
        initView();
        getUserInfo();
    }
    @Override
    public void onResume() {
        getUserInfo();
        super.onResume();
    }

    private void initView() {

        edit   = (ImageView) findViewById(R.id.edit);
        edit.setOnClickListener(this);


        nicheng = (EditText) findViewById(R.id.nicheng);
        phoneTV = (TextView) findViewById(R.id.phoneTV);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);

        img = (LinearLayout)findViewById(R.id.img);
        img.setOnClickListener(this);
        name = (LinearLayout) findViewById(R.id.name);
        name.setOnClickListener(this);
        pwd = (LinearLayout) findViewById(R.id.pwd);
        pwd.setOnClickListener(this);
        phone = (LinearLayout) findViewById(R.id.phone);
        phone.setOnClickListener(this);

        userImg = (ImageView) findViewById(R.id.user_img);

        selectImgDialog = new AlertDialog.Builder(this);
        selectImgDialog.setItems(new String[]{"相册选择", "相机拍照"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectImg(which);
            }
        });
        loadData();
    }
    private void loadData() {

        showData();
    }

    private void showData() {
       // materialishProgress.dismiss();

    }
    //图片选择方式
    private void selectImg(int which) {
        switch (which) {
            case 0:
                Intent toXC = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(toXC, UserManage.OPEN_ALNUM);
                break;
            case 1:
                Intent toXJ = new Intent(
                        MediaStore.ACTION_IMAGE_CAPTURE);
                // 文件夹aaaa
                String path = Environment.getExternalStorageDirectory()
                        .toString() + "/sykjqz/";
                File path1 = new File(path);
                if (!path1.exists()) {
                    path1.mkdirs();
                }
                File file = new File(path1, System.currentTimeMillis()
                        + ".jpg");
                mOutPutFileUri = Uri.fromFile(file);
                toXJ.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
                startActivityForResult(toXJ, UserManage.OPEN_CAMERA);
                break;
        }
    }
    //图片上传
    private void uploadImg(String file)   {
        RequestParams params = new RequestParams(AppHttp.BASE_URL_NEW);
        params.addBodyParameter("ctl","uc_account");
        params.addBodyParameter("act","upload_avatar");
        params.addBodyParameter("email",userList.get(0));
        params.addBodyParameter("pwd",userList.get(1));
        params.addBodyParameter("file",new File(file));
        x.http().post(params,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {

                HeadUrl headUrl = new Gson().fromJson(StringUtils.base64ToString(result),HeadUrl.class);
                if (headUrl.getStatus() ==1){
                    ToastUtils.show(UserInfoActivity.this,"上传成功");
                    ImageLoaderUtils.LoadImage(headUrl.getMiddle_url(),userImg, ImageLoaderUtils.getOptionsRounde(UserInfoActivity.this, userImg.getLayoutParams().width / 2));
                }else {
                    System.out.println(StringUtils.base64ToString(result));
                    ToastUtils.show(UserInfoActivity.this,headUrl.getInfo()+"请稍后再试");
                }
                super.onSuccess(result);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == UserManage.OPEN_ALNUM) {
            if (resultCode == RESULT_OK && data != null) {
                final Uri uri = data.getData();
                String[] pojo = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(uri, pojo, null, null, null);
                if (cursor != null) {
                    ContentResolver cr = this.getContentResolver();
                    int colunm_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    userImgPath = cursor.getString(colunm_index);
                    //Toast.makeText(UserInfoActivity.this,userImgPath,Toast.LENGTH_SHORT).show();
                    //上传
                    uploadImg(userImgPath);
                }
            } else {
                Toast.makeText(UserInfoActivity.this, "未操作", Toast.LENGTH_SHORT)
                        .show();
                    }
        } else if (requestCode == UserManage.OPEN_CAMERA) {
            if (resultCode == RESULT_OK) {
                userImgPath = mOutPutFileUri.getPath();
                //Toast.makeText(UserInfoActivity.this,userImgPath,Toast.LENGTH_SHORT).show();
                uploadImg(mOutPutFileUri.getPath());
                //上传
            } else {
                Toast.makeText(UserInfoActivity.this, "未操作", Toast.LENGTH_SHORT)
                        .show();
                   }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.img:
                selectImgDialog.show();
                break;
            case R.id.name:
                break;
            case R.id.pwd:
                toLeftStartActivity(new Intent(UserInfoActivity.this,ChangePassActivity.class));
                break;
            case R.id.phone:
                break;
            case R.id.edit:
                if (!flag){
                    edit.setImageResource(R.mipmap.save);
                    nicheng.setEnabled(true);
                    flag  = true;
                }else{
                    materialishProgress.show();
                    edit.setImageResource(R.mipmap.edit);
                    nicheng.setEnabled(false);
                    flag  = false;
                    setNewName(nicheng);
                }
                break;
            default:
                break;
        }
    }
    public  void  getUserInfo(){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=user_center&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                UserCenter userCenter = new Gson().fromJson(StringUtils.base64ToString(result),UserCenter.class);
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=user_center&email="+userList.get(0)+"&pwd="+userList.get(1)+"");
                ImageLoaderUtils.LoadImage(userCenter.getUser_avatar(),userImg, ImageLoaderUtils.getOptionsRounde(UserInfoActivity.this, userImg.getLayoutParams().width / 2));
                String mobile = userCenter.getMobile();
                String str = "";
                for (int i = 0; i < mobile.length(); i++) {
                    if (i == mobile.length()-11) {
                        str += mobile.charAt(i);
                    } else if(i == mobile.length()-10) {
                        str += mobile.charAt(i);
                    }else if(i == mobile.length()-9) {
                        str += mobile.charAt(i);}
                        else if(i == mobile.length()-4) {
                            str += mobile.charAt(i);
                    }else if(i == mobile.length()-3) {
                        str += mobile.charAt(i);
                    }else if(i == mobile.length()-2) {
                        str += mobile.charAt(i);
                    }else if(i == mobile.length()-1) {
                        str += mobile.charAt(i);
                    }else {
                        str += "*";
                    }
                }
                phoneTV.setText(str);  // 手机号
                nicheng.setText(userCenter.getUser_name());
                super.onSuccess(result);
            }
        });
    }
    /**
     *修改昵称
     */
    public  void  setNewName(EditText editText){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_account&act=set_username&new_name="+ StringUtils.StringToGBK(editText.getText().toString().trim())+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject js = new JSONObject(StringUtils.base64ToString(result));
                    if (js.getInt("status")==1){
                       ToastUtils.show(UserInfoActivity.this,"修改成功");
                      } else {
                        ToastUtils.show(UserInfoActivity.this,js.getString("info"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }

            @Override
            public void onFinished() {
                materialishProgress.dismiss();
                super.onFinished();
            }
        });
    }
}
