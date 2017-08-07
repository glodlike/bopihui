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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.Dianping;
import com.wzsykj.wuyaojiu.Bean.OrderListInfo;
import com.wzsykj.wuyaojiu.Bean.Refund;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;
import com.wzsykj.wuyaojiu.widget.RatingBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 16/6/16.
 */
public class PJOrderListGoodRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<OrderListInfo.ItemBean.DealOrderItemBean> data;
    private OnRecyclerViewItemClickListener listener;
    private Map<Integer,Boolean> map;
    private Map<Integer,String> reason; //评价
    private Map<Integer,String> pointMap;
    private TextView Submit;
    private EditText content;
    private ArrayList<String> userList;
    private Map<Integer,String> itemsIdmap;
    private Refund refund;
    private String resonStr;
    private ArrayList<String> idsList;
    private ArrayList<String> resList;
    private ArrayList<String> pointList;
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
    public PJOrderListGoodRecyclerAdapter(Context context, Refund refund, List<OrderListInfo.ItemBean.DealOrderItemBean> data, Map<Integer,Boolean> map, TextView Submit, EditText content, OnRecyclerViewItemClickListener listener) {
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
        pointMap = new HashMap<>();
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
        contentView = mLayoutInflater.inflate(R.layout.pjorderlist_good_recycler_item, parent, false);
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
        if (itemBean.getDp_id()!=0){
            itemViewHolder.ll_status.setVisibility(View.VISIBLE);
            itemViewHolder.status.setText("点评完成");
            itemViewHolder.status.setVisibility(View.VISIBLE);
            itemViewHolder.checkBox.setVisibility(View.INVISIBLE);
            itemViewHolder.editText.setVisibility(View.GONE);
            itemViewHolder.line.setVisibility(View.INVISIBLE);
            itemViewHolder.line2.setVisibility(View.VISIBLE);
            itemViewHolder.line1.setVisibility(View.INVISIBLE);
            itemViewHolder.ll_rating.setVisibility(View.INVISIBLE);
            //itemViewHolder.checkBox.setChecked(true);
        }
        for (int i = 0;i<refund.getItems().size();i++){
            //map.put(i,false);
            itemsIdmap.put(i,refund.getItems().get(i).getId());
            reason.put(i,"false");
            pointMap.put(i,"false");
            map.put(i,true);
        }
       /* itemViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                map.put(position,true);
            }
        });*/
        itemViewHolder.ratingBar.setOnRatingListener(new RatingBarView.OnRatingListener() {
            @Override
            public void onRating(Object bindObject, int RatingScore) {
                //ToastUtils.show(context,RatingScore+"");
                pointMap.put(position,String.valueOf(RatingScore));
            }
        });
        itemViewHolder.editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().equals("")){
                    new SharePerfenceUtils(context).keepCity(charSequence.toString());
                }else {
                    new SharePerfenceUtils(context).keepCity("false");
                }

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
                pointList = new ArrayList<>();
                String str = "";
                String reasonStr = "";
                String pointStr = "";
                for (int i =0;i<map.size();i++){
                    if (map.get(i)){
                        idsList.add(itemsIdmap.get(i));
                        resList.add(reason.get(i));
                        pointList.add(pointMap.get(i));
                    }
                }
                for (int i=0;i<idsList.size();i++){
                    System.out.println(idsList.get(i));
                    if (i==idsList.size()-1){
                        str = str+"\""+idsList.get(i)+"\"";
                        reasonStr = reasonStr+"\""+resList.get(i)+"\"";
                        pointStr = pointStr+"\""+pointList.get(i)+"\"";
                    }else {
                        str = str+"\""+idsList.get(i)+"\""+",";
                        reasonStr = reasonStr+"\""+resList.get(i)+"\""+",";
                        pointStr = pointStr+"\""+pointList.get(i)+"\""+",";
                    }
                }
                if (reasonStr.contains("false")||pointStr.contains("false")){
                    Toast.makeText(context,"参数不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    SunbitPJ(str,reasonStr,pointStr);
                }
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
        private LinearLayout ll_status,ll_rating;
        private View line,line1,line2;
        private RatingBarView ratingBar;
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
            line1     = contentView.findViewById(R.id.line1);
            line2     = contentView.findViewById(R.id.line2);
            ll_rating = (LinearLayout) contentView.findViewById(R.id.ll_rating);
            ratingBar = (RatingBarView) contentView.findViewById(R.id.custom_ratingbar);
        }
    }
    /**
     * 评论
     */
    public  void  SunbitPJ(final String itemid, final String contentStr, final String points){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=dp&act=add_order_dp&email="+userList.get(0)+"&pwd="+userList.get(1)+"&ids=["+itemid+"]&contents=["+contentStr+"]&points=["+points+"]&shop_id="+new SharePerfenceUtils(context).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=add_dp&act=add_order_dp&email="+userList.get(0)+"&pwd="+userList.get(1)+"&ids=["+itemid+"]&contents=["+contentStr+"]&points=["+points+"]");
                Dianping dianping = new Gson().fromJson(StringUtils.base64ToString(result),Dianping.class);
                if (dianping.getStatus()==1){
                    ToastUtils.show(context,"评价成功");
                    Intent intent = new Intent();
                    intent.setAction(BracastUtil.ACTION_FINNISH_RE); // 说明动作
                    context.sendBroadcast(intent);// 该函数用于发送广播
                }else{
                    Toast.makeText(context,dianping.getInfo(),Toast.LENGTH_SHORT).show();
                }
                super.onSuccess(result);
            }
        });
    }
}
