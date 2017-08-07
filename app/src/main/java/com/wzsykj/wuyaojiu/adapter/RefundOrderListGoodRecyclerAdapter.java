package com.wzsykj.wuyaojiu.adapter;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.DoRefund;
import com.wzsykj.wuyaojiu.Bean.OrderListInfo;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.Refund;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 16/6/16.
 */
public class RefundOrderListGoodRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<OrderListInfo.ItemBean.DealOrderItemBean> data;
    private OnRecyclerViewItemClickListener listener;
    private Map<Integer,Boolean> map;
    private Map<Integer,String> reason; //退货理由
    private TextView Submit;
    private EditText content;
    private ArrayList<String> userList;
    private Map<Integer,String> itemsIdmap;
    private Refund refund;
    private String resonStr;
    private ArrayList<String> idsList;
    private ArrayList<String> resList;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public List<OrderListInfo.ItemBean.DealOrderItemBean> getData() {
        return data;
    }

    public void setData(List<OrderListInfo.ItemBean.DealOrderItemBean> data) {
        this.data = data;
    }

    public RefundOrderListGoodRecyclerAdapter(Context context,  Refund refund,List<OrderListInfo.ItemBean.DealOrderItemBean> data, Map<Integer,Boolean> map, TextView Submit, EditText content, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.map  = map;
        this.Submit = Submit;
        this.refund  = refund;
        this.content = content;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener = listener;
        reason  = new HashMap<>();
        userList= new SharePerfenceUtils(context).getUserInfo();
        itemsIdmap =new HashMap<>();
    }

    /**
     * 决定元素的布局使用哪种类型
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 渲染具体的ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = null;
        contentView = mLayoutInflater.inflate(R.layout.refundorderlist_good_recycler_item, parent, false);
        return new ItemViewHolder(contentView);
    }
    /**
     * 绑定ViewHolder的数据。
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final OrderListInfo.ItemBean.DealOrderItemBean itemBean = data.get(position);
        ImageLoaderUtils.LoadImage(itemBean.getDeal_icon(),itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
        itemViewHolder.title.setText(itemBean.getName());
        itemViewHolder.number.setText("X"+itemBean.getNumber());
        itemViewHolder.info.setText(itemBean.getAttr_str());
        itemViewHolder.price.setText("￥"+itemBean.getTotal_price());
        if (itemBean.getRefund_status()==1){
            itemViewHolder.ll_status.setVisibility(View.VISIBLE);
            itemViewHolder.status.setText("退款申请中");
            itemViewHolder.status.setVisibility(View.VISIBLE);
            itemViewHolder.checkBox.setVisibility(View.INVISIBLE);
            itemViewHolder.editText.setVisibility(View.GONE);
            itemViewHolder.line.setVisibility(View.INVISIBLE);
        }
        for (int i = 0;i<refund.getItems().size();i++){
            map.put(i,false);
            itemsIdmap.put(i,refund.getItems().get(i).getId());
            reason.put(i,"");
        }
        itemViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                map.put(position,b);
            }
        });
        itemViewHolder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                new SharePerfenceUtils(context).keepCity(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {
                reason.put(position,new SharePerfenceUtils(context).getCity());
            }
        });
        /**
         * 提交退款申请
         */
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                idsList = new ArrayList<>();
                resList = new ArrayList<>();
                String str = "";
                String reasonStr = "";
                for (int i =0;i<map.size();i++){

                    if (map.get(i)){
                        idsList.add(itemsIdmap.get(i));
                        resList.add(reason.get(i));
                    }
                }
                for (int i=0;i<idsList.size();i++){
                    System.out.println(idsList.get(i));
                    if (i==idsList.size()-1){
                        str = str+"\""+idsList.get(i)+"\"";
                        reasonStr = reasonStr+"\""+resList.get(i)+"\"";
                    }else {
                        str = str+"\""+idsList.get(i)+"\""+",";
                        reasonStr = reasonStr+"\""+resList.get(i)+"\""+",";
                    }
                }
                Refund(str,reasonStr);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;
        public TextView price;
        public TextView number,status;
        public CheckBox checkBox;
        public EditText editText;
        private LinearLayout ll_status;
        private View line;
        public ItemViewHolder(View contentView) {
            super(contentView);
            ll_status = (LinearLayout) contentView.findViewById(R.id.ll_status);
            status = (TextView) contentView.findViewById(R.id.start);
            img =   (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            info =  (TextView) contentView.findViewById(R.id.info);
            price = (TextView) contentView.findViewById(R.id.price);
            number =(TextView) contentView.findViewById(R.id.number);
            checkBox = (CheckBox) contentView.findViewById(R.id.checkbox);
            editText = (EditText) contentView.findViewById(R.id.content);
            line     = contentView.findViewById(R.id.line);
        }
    }
    /**
     * 执行退款申请
     */
    public  void  Refund(final String itemid, final String content) {
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_order&act=do_refund&email="+userList.get(0)+"&pwd="+userList.get(1)+"&item_ids=["+itemid+"]&contents=["+StringUtils.StringToGBK(content)+"]",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=uc_order&act=do_refund&email="+userList.get(0)+"&pwd="+userList.get(1)+"&item_ids=["+itemid+"]&contents=["+content+"]&shop_id="+new SharePerfenceUtils(context).getShop_id()[0]+"");
                DoRefund doRefund = new Gson().fromJson(StringUtils.base64ToString(result),DoRefund.class);
                if (doRefund.getStatus()==1){
                    ToastUtils.show(context,"提交成功");
                    Intent intent = new Intent();
                    intent.setAction(BracastUtil.ACTION_FINNISH_RE); // 说明动作
                    context.sendBroadcast(intent);// 该函数用于发送广播
                }else{
                    ToastUtils.show(context,doRefund.getInfo());
                }
                super.onSuccess(result);
            }
        });
    }
}
