package com.wzsykj.wuyaojiu.adapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wzsykj.wuyaojiu.Bean.CartInfo;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;
import com.wzsykj.wuyaojiu.widget.NumberButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chen on 16/6/16.
 */
public class CarRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Map<String,Integer> upmap = new HashMap<>() ;
    private boolean flag = false;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<CartInfo.CartListBean> data;
    private OnRecyclerViewItemClickListener listener;
    private ArrayList<String> userList;
    private CartInfo cartInfo;
    private TextView all_price;
    private LinearLayout ll_jiesuan,ll_car;
    private boolean isEdit = false;
    private TextView editTv;
    private Map<String,Integer> map = new HashMap<>();

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);

    }
    public List<CartInfo.CartListBean> getData() {
        return data;
    }

    public void setData(List<CartInfo.CartListBean> data) {
        this.data = data;
    }

    public CarRecyclerAdapter(Context context, List<CartInfo.CartListBean> data,CartInfo cartInfo,TextView all_price ,LinearLayout ll_jiesuan,LinearLayout ll_car,TextView editTv,OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.editTv = editTv;
        this.data = data;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener   = listener;
        this.cartInfo   = cartInfo;
        this.ll_jiesuan = ll_jiesuan;
        this.ll_car     = ll_car;
        this.all_price  = all_price;
        userList        = new SharePerfenceUtils(context).getUserInfo();
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public CarRecyclerAdapter(Map<String, Integer> map) {

        this.map = map;

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
        contentView = mLayoutInflater.inflate(R.layout.car_good_recycler_item, parent, false);
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
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        final CartInfo.CartListBean bean =data.get(position);
        //已失效
        /*  if (position == 2) {
            itemViewHolder.deleteLayout.setVisibility(View.VISIBLE);
        } else {
            itemViewHolder.deleteLayout.setVisibility(View.INVISIBLE);*/
        if (isEdit) {
            itemViewHolder.editLayout.setVisibility(View.VISIBLE);
            flag = true;
        } else {
            itemViewHolder.editLayout.setVisibility(View.INVISIBLE);
        }
        // }
        itemViewHolder.info.setText  (bean.getAttr_str());
        itemViewHolder.title.setText(bean.getSub_name());
        itemViewHolder.number.setText("X"+bean.getNumber());
        itemViewHolder.price.setText ("￥"+bean.getTotal_price());
        itemViewHolder.numberBtn.setCurrentNumber(Integer.parseInt(bean.getNumber()));
        ImageLoaderUtils.LoadImage(bean.getIcon(), itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
        itemViewHolder.numberBtn.setOnAmountChangeListener(new NumberButton.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Intent intent = new Intent();
                intent.setAction(BracastUtil.COUNT_REFRESH); // 说明动作
                context.sendBroadcast(intent);// 该函数用于发送广播
                if (isEdit){
                    upmap.put(bean.getId(), amount);
                }
                java.util.Iterator it = upmap.entrySet().iterator();
                while (it.hasNext()) {
                    java.util.Map.Entry entry = (java.util.Map.Entry) it.next();
                    entry.getValue();
                    changeNum(entry.getKey().toString(), entry.getValue().toString());
                }
                flag = false;
            }
        });
        // }
        itemViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("确定要将该商品从您的购物车中删除吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                                deleteDoodsFronCart(bean.getId());
                                data.remove(bean);
                                CarRecyclerAdapter.this.notifyDataSetChanged();
                                all_price.setText(String.valueOf(Double.parseDouble(all_price.getText().toString())-bean.getTotal_price()));
                                if (all_price.getText().toString().equals("0.0")||all_price.getText().toString().equals("0.00")||all_price.getText().toString().equals("0")){
                                    ll_jiesuan.setVisibility(View.GONE);
                                    ll_car.setVisibility(View.VISIBLE);
                                    editTv.setVisibility(View.GONE);
                                }else {
                                    editTv.setVisibility(View.VISIBLE);
                                }
                                Intent intent = new Intent();
                                intent.setAction(BracastUtil.COUNT_REFRESH); // 说明动作
                                context.sendBroadcast(intent);// 该函数用于发送广播
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
        private LinearLayout deleteLayout;
        private LinearLayout editLayout;
        public ImageView img;
        public TextView title;
        public TextView info;
        public TextView price;
        public TextView number;
        private NumberButton numberBtn;
        private TextView delete;

        public ItemViewHolder(View contentView) {
            super(contentView);
            deleteLayout = (LinearLayout) contentView.findViewById(R.id.delete_layout);
            editLayout = (LinearLayout) contentView.findViewById(R.id.edit_layout);

            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            info = (TextView) contentView.findViewById(R.id.info);
            price = (TextView) contentView.findViewById(R.id.price);
            number = (TextView) contentView.findViewById(R.id.number);
            numberBtn = (NumberButton) contentView.findViewById(R.id.numberBtn);
            delete    = (TextView) contentView.findViewById(R.id.delete_goods);

        }
    }
    /**
     *从购物车删除商品
     */
    public  void  deleteDoodsFronCart(final String id){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=del&id="+id+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=cart&act=del&id="+id+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"");
                super.onSuccess(result);
            }
        });
    }
    public void changeNum(final String id, final String number){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=update&id="+id+"&num="+number+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(context).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
}