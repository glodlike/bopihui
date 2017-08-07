package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.Type;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.DensityUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import zhy.view.flowlayout.FlowLayout;
import zhy.view.flowlayout.TagAdapter;
import zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chen on 16/6/16.
 */
public class TypeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> data;
    private OnRecyclerViewItemClickListener listener;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public TypeRecyclerAdapter(Context context, List<String> entitys, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = entitys;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener = listener;
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
        if (viewType % 2 == 0) {
            contentView = mLayoutInflater.inflate(R.layout.type_recycler_img_item, parent, false);
            return new ImgViewHolder(contentView);
        } else {
            contentView = mLayoutInflater.inflate(R.layout.type_recycler_tag_item, parent, false);
            return new TagViewHolder(contentView);
        }
    }
    /**
     * 绑定ViewHolder的数据。
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
        XUtils.Get(AppHttp.BASE_URL+"index.php?ctl=cate",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {


                Type type = new Gson().fromJson(StringUtils.base64ToString(result),Type.class);
                ArrayList<String> titleList   = new ArrayList<>(); //标题
                ArrayList<String> ImageList   = new ArrayList<>(); //图片
                ArrayList<String> childtitle   = new ArrayList<>(); //子选项

                Map<String,ArrayList<String>> map = new HashMap<>();
                for (int i = 0;i<type.getBcate_list().size();i++){
                      titleList.add(type.getBcate_list().get(i).getName());
                      ImageList.add(type.getBcate_list().get(i).getCate_img());
                         }
               /* for (int j = 0; j<type.getBcate_list().get(0).getBcate_type().size();j++){
                     childtitle.add(type.getBcate_list().get(0).getBcate_type().get(j).getName());
                }*/
                if (holder instanceof ImgViewHolder) {
                    final ImgViewHolder imgViewHolder = (ImgViewHolder) holder;
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (DensityUtil.getWindowsWidth(context) * 0.375));
                    imgViewHolder.title.setText(titleList.get(position));
                    imgViewHolder.btn.setText("查看全部");
                    imgViewHolder.img.setLayoutParams(params);
                    ImageLoaderUtils.LoadImage(ImageList.get(position), imgViewHolder.img, ImageLoaderUtils.getOptionsDefault());
                    imgViewHolder.layout.setLayoutParams(params);

                } else if (holder instanceof TagViewHolder) {
                    final TagViewHolder tagViewHolder = (TagViewHolder) holder;
                    tagViewHolder.flowLayout.setAdapter(new TagAdapter<String>(childtitle) {
                        @Override
                        public View getView(FlowLayout parent, int position, String s) {
                            TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.type_flowlayout_tag_item,
                                    tagViewHolder.flowLayout, false);
                            tv.setText(s);
                            return tv;
                        }
                    });
                    tagViewHolder.flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                        @Override
                        public boolean onTagClick(View view, int position, FlowLayout parent) {
                            Toast.makeText(context, "item click" + position, Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                }
                super.onSuccess(result);
            }
        });
   /*     if (holder instanceof ImgViewHolder) {
            final ImgViewHolder imgViewHolder = (ImgViewHolder) holder;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (DensityUtil.getWindowsWidth(context) * 0.375));
            imgViewHolder.title.setText("标题");
            imgViewHolder.btn.setText("查看全部");
            imgViewHolder.img.setLayoutParams(params);
            ImageLoaderUtils.LoadImage("http://cdn.519wz.cn/public/attachment/201608/17/11/57b3db190c452.jpg", imgViewHolder.img, ImageLoaderUtils.getOptionsDefault());
            imgViewHolder.layout.setLayoutParams(params);

        } else if (holder instanceof TagViewHolder) {
            final TagViewHolder tagViewHolder = (TagViewHolder) holder;

            tagViewHolder.flowLayout.setAdapter(new TagAdapter<String>(new String[]{"百威", "博客啊", "啊啊啊啊", "博客啊", "啊啊啊", "博阿闪烁客啊", "啊啊啊", "博客啊啊啊", "洒大地啊实打实的", "博客啊", "阿啊呵阿萨德", "博客啊", "啊啊啊"}) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.type_flowlayout_tag_item,
                            tagViewHolder.flowLayout, false);
                    tv.setText(s);
                    return tv;
                }
            });
            tagViewHolder.flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    Toast.makeText(context, "item click" + position, Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * 图片视图
     */
    public class ImgViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public ImageView layout;
        public TextView title;
        public TextView btn;

        public ImgViewHolder(View contentView) {
            super(contentView);
            contentView.setBackgroundResource(R.color.clearColor);
            img = (ImageView) contentView.findViewById(R.id.img);
            layout = (ImageView) contentView.findViewById(R.id.layout);
            title = (TextView) contentView.findViewById(R.id.title);
            btn = (TextView) contentView.findViewById(R.id.btn);
        }
    }
     /**
     * 标签视图
     */
    public class TagViewHolder extends RecyclerView.ViewHolder {
        public TagFlowLayout flowLayout;

        public TagViewHolder(View contentView) {
            super(contentView);
            flowLayout = (TagFlowLayout) contentView.findViewById(R.id.flowlayout);
        }
    }


}
