package com.wzsykj.wuyaojiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.wzsykj.wuyaojiu.Bean.Shop;
import com.wzsykj.wuyaojiu.R;
import java.util.List;
/**
 * Created by Administrator on 2016/12/6 0006.
 */
public class DeliverAdapter extends BaseAdapter {
    private Context context;
    private List<Shop.ShopListBean> listBeen;

    public DeliverAdapter(Context context, List<Shop.ShopListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }
    @Override
    public int getCount() {
        return listBeen.size();
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
       viewHorder horder;
        if (convertView == null) {
            horder = new viewHorder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.mendian_item_layout, null);
            horder.mendian = (TextView) convertView.findViewById(R.id.mendian);
            horder.zhizhi = (TextView) convertView.findViewById(R.id.detail);
            horder.dianhua = (TextView) convertView.findViewById(R.id.phone);
            horder.fanwei = (TextView) convertView.findViewById(R.id.fanwei);
            horder.jili = (TextView) convertView.findViewById(R.id.juli);
            convertView.setTag(horder);
        }
        horder = (viewHorder) convertView.getTag();
        final Shop.ShopListBean bean = listBeen.get(i);
        horder.mendian.setText(bean.getName());
        horder.zhizhi.setText(bean.getAddress());
        horder.dianhua.setText(bean.getTel());
        horder.jili.setText("距离 ："+bean.getJuli());
        if (bean.getPeisong_fanwei().equals("")){
            horder.fanwei.setText("配送范围 ：");
        }else{
            horder.fanwei.setText("配送范围 : "+bean.getPeisong_fanwei());
        }
        horder.dianhua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://"+bean.getTel()+""));
                context.startActivity(intent);
            }
        });
        return convertView;
    }
    class viewHorder {
            public TextView mendian;
            public TextView zhizhi;
            public TextView fanwei;
            public TextView dianhua;
            public TextView jili;
        }
    }