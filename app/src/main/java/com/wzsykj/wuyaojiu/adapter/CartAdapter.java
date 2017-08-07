package com.wzsykj.wuyaojiu.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.NumberButton;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class CartAdapter extends BaseAdapter {
    private Handler mHandler;
    private Context      context;
    private List<String> iconList ;
    private List<String> nameList ;
    private List<String> guigeList;
    private List<String> jiageList ;
    private Map<Integer,Integer> numerList;
    private List<String> orderIdList;
    private ArrayList<String> userList ;
    private TextView all_order_price;
    private static HashMap<Integer, Boolean> isSelected;


    private TextView edit;
    private Boolean isEdit = false;
    public CartAdapter(Context context, List<String> orderIdList, List<String> iconList, List<String> nameList, List<String> guigeList, List<String> jiageList, Map<Integer,Integer> numerList, TextView edit, Boolean isEdit,TextView all_order_price) {
        this.context   = context;
        this.iconList  = iconList;
        this.nameList  = nameList;
        this.guigeList = guigeList;
        this.jiageList = jiageList;
        this.numerList = numerList;
        this.edit      = edit;
        this.orderIdList = orderIdList;
        this.isEdit    = isEdit;
        this.all_order_price = all_order_price;
        isSelected = new HashMap<>();
        initDate();
    }

    private void initDate() {
        for (int i = 0; i < iconList.size(); i++) {
            getIsSelected().put(i, false);
        }
    }
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        CartAdapter.isSelected = isSelected;
    }
    @Override
    public int getCount() {
        return iconList.size();
    }
    @Override
    public Object getItem(int i) {
        return i;
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        viewHorder horder;
        if (convertView == null) {
            horder = new viewHorder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.car_good_recycler_item, null);
            userList =new SharePerfenceUtils(context).getUserInfo();
            horder.img = (ImageView)convertView.findViewById(R.id.img);
            horder.title = (TextView) convertView.findViewById(R.id.title);
            horder.price = (TextView) convertView.findViewById(R.id.price);
            horder.guige = (TextView) convertView.findViewById(R.id.info);
            horder.numberButton = (NumberButton) convertView.findViewById(R.id.numberBtn);
            horder.delete        = (TextView) convertView.findViewById(R.id.delete_goods);
            horder.Number        = (TextView) convertView.findViewById(R.id.number);
            horder.editlayout    = (LinearLayout) convertView.findViewById(R.id.edit_layout);

            final viewHorder finalHorder = horder;
            if (isEdit){
                finalHorder.editlayout.setVisibility(View.VISIBLE);
            }
            else{
                finalHorder.editlayout.setVisibility(View.GONE);
            }
            if (isEdit){
                horder.editlayout.setVisibility(View.VISIBLE);
            }else{
                horder.editlayout.setVisibility(View.GONE);
            }
            convertView.setTag(horder);
        }
        horder = (viewHorder) convertView.getTag();
        horder.title.setText(nameList.get(i));
        horder.price.setText("￥"+jiageList.get(i));
        ImageLoaderUtils.LoadImage(iconList.get(i),horder.img, ImageLoaderUtils.getOptionsDefault());
        horder.guige.setText(guigeList.get(i));
        horder.Number.setText("X "+ numerList.get(i));
        horder.numberButton.setCurrentNumber(numerList.get(i));
        int all_price = 0;
        for (int j=0;j<jiageList.size();j++){
            all_price = all_price+Integer.parseInt(jiageList.get(j));
        }
        all_order_price.setText(String.valueOf(all_price));
        /**
         * 苁购物车当中删除
         */
        horder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("确定要从您的购物车删除此项？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                                deleteGoodFromCart(orderIdList.get(i));
                                iconList.remove(i);
                                nameList.remove(i);
                                guigeList.remove(i);
                                jiageList.remove(i);
                                numerList.remove(i);
                                CartAdapter.this.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dlg = builder.create();
                dlg.show();

            }
        });
        return convertView;
    }
    static class viewHorder {
        public  ImageView img;
        public  TextView  title;
        public  TextView  price;
        public  TextView  guige;
        public  TextView Number;
        public  TextView delete;
        public NumberButton numberButton;
        private LinearLayout editlayout;
    }
    public  void  deleteGoodFromCart(String id ){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=del&id="+id+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(StringUtils.base64ToString(result));
                super.onSuccess(result);
            }
        });
    }

}
