package com.wzsykj.wuyaojiu.ui.tab;


import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.wzsykj.wuyaojiu.Bean.Update;
import com.wzsykj.wuyaojiu.Bean.UserCenter;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.ui.good.JsShareActivity;
import com.wzsykj.wuyaojiu.ui.good.WebActivity;
import com.wzsykj.wuyaojiu.ui.orther.LogoActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.UpdateService;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.adapter.UserMenuRecyclerAdapter;
import com.wzsykj.wuyaojiu.base.BaseFragment;
import com.wzsykj.wuyaojiu.entity.UserMenuEntity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.user.AboutActivity;
import com.wzsykj.wuyaojiu.ui.user.AddressActivity;
import com.wzsykj.wuyaojiu.ui.user.CollectionActivity;
import com.wzsykj.wuyaojiu.ui.login.LoginActivity;
import com.wzsykj.wuyaojiu.ui.user.OrderListActivity;
import com.wzsykj.wuyaojiu.ui.user.UserInfoActivity;
import com.wzsykj.wuyaojiu.utils.AppUtils;
import com.wzsykj.wuyaojiu.utils.FileUtils;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.BadgeView;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by chen on 16/6/7.
 */

public class UserFragment extends BaseFragment implements View.OnClickListener {
    private ImageView unLoginImg;
    private TextView userOut;
    private ArrayList<String> userList;
    private ImageView userImg;
    private TextView money;
    private TextView jifen;
    private TextView name;
    private LinearLayout userInfo;
    private RelativeLayout re_unlogin;
    private TextView kefu;
    private Boolean flag;
    private Boolean isfrist = true;
    private BadgeView badgeView1,badgeView2,badgeView3,badgeView4;
    private DownloadManager downloadManager;
    private SharedPreferences prefs;
    private static final String DL_ID = "downloadId";
    //private int[] icons = new int[]{R.mipmap.wodeshoucang_icon, R.mipmap.dizhiguanli_icon, 0, R.mipmap.fenxiangyouli_icon, R.mipmap.wodefanli_icon, 0, R.mipmap.qingchuhuancun_icon, R.mipmap.jianchagengxin_icon, R.mipmap.guanyuwomen_icon};
    //private String[] titles = new String[]{"我的收藏", "地址管理", "", "分享有礼", "我的返利", "", "清空缓存", "检查更新", "关于519"};
    private String[] titles = new String[]{"我的收藏", "地址管理", "", "清空缓存", "检查更新", "","邀请好友","关于519"};
    private int[] icons = new int[]{R.mipmap.wodeshoucang_icon, R.mipmap.dizhiguanli_icon, 0, R.mipmap.qingchuhuancun_icon, R.mipmap.jianchagengxin_icon, 0, R.mipmap.fenxiangyouli_icon,R.mipmap.guanyuwomen_icon};
    private ImageView user_img;
    private RecyclerView recyclerView;
    private LinearLayout allOrderLayout, dfkOrderLayout, dpsOrderLayout, dpOrderLayout, shOrderLayout;

    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.main_user_fragment, null);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             }
        });
        userList = new SharePerfenceUtils(getActivity()).getUserInfo();
        initView(contentView);
        cheakUserIsLogin();
        return contentView;
        }
    @Override
    public void onResume() {
        userList = new SharePerfenceUtils(getActivity()).getUserInfo();
        cheakUserIsLogin();
        //getUserInfo();
        if (flag){
            Intent intent = new Intent();
            intent.setAction(BracastUtil.COUNT_REFRESH); // 说明动作
            getContext().sendBroadcast(intent);          // 该函数用于发送广播
            getUserOrderInfo();
        }else {
            badgeView1.setVisibility(View.GONE);
            badgeView2.setVisibility(View.GONE);
            badgeView3.setVisibility(View.GONE);
            badgeView4.setVisibility(View.GONE);
        }
        super.onResume();
    }
    private void initView(View contentView) {
        badgeView1 = (BadgeView) contentView.findViewById(R.id.badgeView1);
        badgeView1.setBackground(10, getResources().getColor(R.color.colorPrimary));

        badgeView2 = (BadgeView) contentView.findViewById(R.id.badgeView2);
        badgeView2.setBackground(10, getResources().getColor(R.color.colorPrimary));

        badgeView3 = (BadgeView) contentView.findViewById(R.id.badgeView3);
        badgeView3.setBackground(10, getResources().getColor(R.color.colorPrimary));

        badgeView4 = (BadgeView) contentView.findViewById(R.id.badgeView4);
        badgeView4.setBackground(10, getResources().getColor(R.color.colorPrimary));

        downloadManager = (DownloadManager)getActivity().getSystemService(DOWNLOAD_SERVICE);
        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        kefu = (TextView) contentView.findViewById(R.id.kefu);
        kefu.setOnClickListener(this);
        userImg = (ImageView) contentView.findViewById(R.id.user_img);
        money = (TextView) contentView.findViewById(R.id.money);
        jifen = (TextView) contentView.findViewById(R.id.textView);
        name = (TextView) contentView.findViewById(R.id.user_name);

        unLoginImg = (ImageView) contentView.findViewById(R.id.user_icon);

        re_unlogin = (RelativeLayout) contentView.findViewById(R.id.re_unlogin);

        userOut = (TextView) contentView.findViewById(R.id.user_out);
        userOut.setOnClickListener(this);

        userInfo = (LinearLayout) contentView.findViewById(R.id.userinfo);
        userInfo.setOnClickListener(this);

        user_img = (ImageView) contentView.findViewById(R.id.user_img);

        recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        //滑动平滑不抢事件
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);

        allOrderLayout = (LinearLayout) contentView.findViewById(R.id.all_order);
        allOrderLayout.setOnClickListener(this);
        dfkOrderLayout = (LinearLayout) contentView.findViewById(R.id.dfk_order);
        dfkOrderLayout.setOnClickListener(this);
        dpsOrderLayout = (LinearLayout) contentView.findViewById(R.id.dps_order);
        dpsOrderLayout.setOnClickListener(this);
        dpOrderLayout = (LinearLayout) contentView.findViewById(R.id.dpj_order);
        dpOrderLayout.setOnClickListener(this);
        shOrderLayout = (LinearLayout) contentView.findViewById(R.id.sh_order);
        shOrderLayout.setOnClickListener(this);
        re_unlogin.setOnClickListener(this);
        laodData();
    }
    private void laodData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showData();
            }
        }, 1000);
    }
    private void showData() {
        //getUserOrderInfo();
        List<UserMenuEntity> data = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            UserMenuEntity entity = new UserMenuEntity();
            entity.setTiitle(titles[i]);
            entity.setIcon(icons[i]);
            if (titles[i].equals("清空缓存")) {
                try {
                    entity.setInfo(new DecimalFormat("0.0").format(FileUtils.getFolderSize(StorageUtils.getOwnCacheDirectory(getActivity(),
                            ImageLoaderUtils.path)) / 1024 / 1024) + "MB");
                } catch (Exception e) {
                    entity.setInfo("");
                }
            } else if (titles[i].equals("检查更新")) {
                entity.setInfo("v" + AppUtils.getAppVersionName(getActivity()));
            } else {
                entity.setInfo("");
            }
            data.add(entity);
        }
        recyclerView.setAdapter(new UserMenuRecyclerAdapter(getActivity(), data,
                new UserMenuRecyclerAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        switch (position) {
                            case 0:
                                if (!flag) {
                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    toLeftStartActivity(new Intent(getActivity(), CollectionActivity.class));
                                }
                                break;
                            case 1:
                                if (!flag) {
                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    toLeftStartActivity(new Intent(getActivity(), AddressActivity.class));
                                }
                                break;
                            case 3:
                                materialishProgress.show();
                                ImageLoaderUtils.onClearDiskClick();
                                ImageLoaderUtils.onClearMemoryClick();
                                materialishProgress.dismiss();
                                if (isfrist){
                                    showData();
                                    isfrist = false;
                                }
                                break;
                            case 4:
                                updateAPK();
                                break;
                            case 6:
                                if (!flag) {
                                    toLeftStartActivity(new Intent(getActivity(), LoginActivity.class));
                                } else {
                                    Intent intent = new Intent(getContext(), WebActivity.class);
                                    intent.putExtra("pwd",userList.get(1));
                                    intent.putExtra("username",userList.get(0));
                                    intent.putExtra("url","http://v2.519wz.cn/wap/index.php?ctl=invite_friend");
                                    startActivity(intent);
                                }
                                break;
                            case 7:
                                toLeftStartActivity(new Intent(getActivity(), AboutActivity.class));
                                break;
                        }
                    }
                }
        ));
        recyclerView.addItemDecoration(new
                DividerItemDecoration(this.getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
                ImageLoaderUtils.LoadImage("http://v2.519wz.cn/public/avatar/noavatar_middle.gif", unLoginImg, ImageLoaderUtils.getOptionsRounde(
                getActivity(), user_img.getLayoutParams().width / 2));
    }
    private void goOrderInfo(int orderType) {
        Intent intent = new Intent(getActivity(), OrderListActivity.class);
        intent.putExtra("index", orderType);
        toLeftStartActivity(intent);
    }

    private void setBadge(View view, int i) {
        BadgeView badgeView = new BadgeView(this.getActivity());
        badgeView.setBackground(10, getResources().getColor(R.color.colorPrimary));
        badgeView.setTargetView(view);
        badgeView.setBadgeCount(i);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_unlogin:
                toLeftStartActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.user_out:
                if (userOut.getText().equals("登录")) {
                    toLeftStartActivity(new Intent(getActivity(), LoginActivity.class));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("确定要退出当前用户吗？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialogInterface, int index) {
                                    LoginOut();
                                    Intent intent = new Intent();
                                    intent.setAction(BracastUtil.LOGINOUT); // 说明动作
                                    getContext().sendBroadcast(intent);// 该函数用于发送广播
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                      });
                    AlertDialog dlg = builder.create();
                    dlg.show();
                }
                break;
            case R.id.all_order:
                if (!flag) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    goOrderInfo(0);
                }
                break;
            case R.id.dfk_order:
                if (!flag) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    goOrderInfo(1);
                }
                break;
            case R.id.dps_order:
                if (!flag) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    goOrderInfo(2);
                }
                break;
            case R.id.dpj_order:

                if (!flag) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    goOrderInfo(3);
                }

                break;
            case R.id.sh_order:
                if (!flag) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    goOrderInfo(4);
                }
                break;
            case R.id.userinfo:
                toLeftStartActivity(new Intent(getActivity(), UserInfoActivity.class));
                break;
            case R.id.kefu:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://" + "0577-88871919"));
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    /**
     * 檢查用戶是否登錄
     */
    public void cheakUserIsLogin() {
        //如果用戶名和密碼任意一個為空，即可判斷未登錄
        if (new SharePerfenceUtils(getContext()).getUserInfo().get(0).equals("") || new SharePerfenceUtils(getContext()).getUserInfo().get(0).equals("false") || new SharePerfenceUtils(getContext()).getUserInfo().get(1).equals("false")) {
            userInfo.setVisibility(View.GONE);
            re_unlogin.setVisibility(View.VISIBLE);
            userOut.setText("登录");
            flag = false;
        } else {
            userInfo.setVisibility(View.VISIBLE);
            re_unlogin.setVisibility(View.GONE);
            userOut.setText("退出");
            getUserInfo();
            flag = true;
        }
    }
    /**
     *
     */
    public  void  LoginOut(){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=user&act=loginout&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject js  = new JSONObject(StringUtils.base64ToString(result));
                    if (js.getInt("status")==1){
                        new SharePerfenceUtils(getActivity()).keepUserInfo("false", "false");
                        toLeftStartActivity(new Intent(getActivity(), LoginActivity.class));
                    }else {
                        ToastUtils.show(getContext(),js.getString("info"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                super.onSuccess(result);
            }
        });
    }
    public void getUserInfo() {
        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=user_center&email=" + userList.get(0) + "&pwd=" + userList.get(1) + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    if (jsonObject.getInt("status")==1){
                        name.setText("名字:" +  jsonObject.getString("user_name"));
                        jifen.setText("积分:" + jsonObject.getString("user_score_format"));
                        money.setText("余额:" + jsonObject.getString("user_money_format"));
                        ImageLoaderUtils.LoadImage(jsonObject.getString("user_avatar"), user_img, ImageLoaderUtils.getOptionsRounde(
                                getActivity(), user_img.getLayoutParams().width / 2));
                    }else {
                        Intent intent = new Intent(getContext(),LoginActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
        });
    }
  public  void updateAPK(){
    XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=version&dev_type=android&version="+AppUtils.getAppVersionName(getActivity())+"",null,new MyCallBack<String>(){
        @Override
        public void onSuccess(String result) {
            materialishProgress.show();
            final Update update = new Gson().fromJson(StringUtils.base64ToString(result),Update.class);
            if (!AppUtils.getAppVersionName(getActivity()).equals(update.getServerVersion())&&(update.getHasfile()==1)){
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("软件升级")
                        .setMessage(update.getInfo())
                        .setPositiveButton("立即更新",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                        Intent intent = new Intent(getContext(), UpdateService.class);
                                        intent.putExtra("apkUrl", update.getFilename());
                                        getContext().startService(intent);
                                    }
                                })
                        .setNegativeButton("下次再说",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                });
                alert.create().show();
            }else{
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.show(getContext(), "当前版本：V" + AppUtils.getAppVersionName(getActivity()) + "，为最新版本");
                    }
                }, 1000);
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

  public void  getUserOrderInfo(){
      XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=user_center&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
          @Override
          public void onSuccess(String result) {
              UserCenter userCenter = new Gson().fromJson(StringUtils.base64ToString(result),UserCenter.class);
              badgeView1.setBadgeCount(Integer.parseInt( userCenter.getUnpay_order_count()));
              badgeView2.setBadgeCount(Integer.parseInt( userCenter.getPaid_order_count()));
              badgeView3.setBadgeCount(Integer.parseInt( userCenter.getReview_order_count()));
              badgeView4.setBadgeCount(Integer.parseInt( userCenter.getRefund_order_count()));
              super.onSuccess(result);
          }
      });
  }
}