package com.wzsykj.wuyaojiu.ui.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.TypeListAdapter;
import com.wzsykj.wuyaojiu.base.BaseFragment;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.good.GoodListActivity;
import com.wzsykj.wuyaojiu.ui.good.SelectActivity;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by chen on 16/6/7.
 */
public class TypeFragment extends BaseFragment {
    private ArrayList<String> imgList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> typeList ;
    private ArrayList<String> cateIdList ;

    private Map<String,ArrayList<String>> map = new HashMap<>();
    private Map<String,ArrayList<String>> Idmap = new HashMap<>();
    private ArrayList<String> IdList = new ArrayList<>();
     private SwipeRefreshLayout swipeLayout;
    //private RecyclerView recyclerView;
    private ListView listView;
    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.main_type_fragment, null);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        initView(contentView);
        return contentView;
    }
    private void initView(View contentView) {
        listView = (ListView) contentView.findViewById(R.id.text);
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cate&city_id="+new SharePerfenceUtils(getContext()).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    if (jsonObject.getInt("status")==1){
                        JSONArray cateList = jsonObject.getJSONArray("bcate_list");
                        for (int i = 0;i<cateList.length();i++){
                            IdList.add(cateList.getJSONObject(i).getString("id"));
                            imgList.add(cateList.getJSONObject(i).getString("cate_img"));
                            nameList.add(cateList.getJSONObject(i).getString("name"));
                            typeList = new ArrayList<>();
                            cateIdList = new ArrayList<>();
                            if (cateList.getJSONObject(i).toString().contains("bcate_type")){
                                for (int j = 0;j < cateList.getJSONObject(i).getJSONArray("bcate_type").length();j++){
                                    typeList.add(cateList.getJSONObject(i).getJSONArray("bcate_type").getJSONObject(j).getString("name"));
                                    cateIdList.add(cateList.getJSONObject(i).getJSONArray("bcate_type").getJSONObject(j).getString("id"));
                                }
                                Idmap.put(String.valueOf(i),cateIdList);
                                map.put(String.valueOf(i),typeList);
                            }
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TypeListAdapter adapter = new TypeListAdapter(imgList,nameList,map,Idmap,getContext());
                listView.setAdapter(adapter);

                //listView 的监听事件
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(getContext(),GoodListActivity.class);
                        intent.putExtra("id",IdList.get(i));
                        intent.putExtra("type","cate_id");
                        startActivity(intent);
                    }
                });
                super.onSuccess(result);
            }
        });
        contentView.findViewById(R.id.select).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toInfo = new Intent(getActivity(), SelectActivity.class);
                toLeftStartActivity(toInfo);
            }
        });
        swipeLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.swipe_layout);
        swipeLayout.setProgressBackgroundColor(R.color.white);
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //下拉刷新
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
              public void onRefresh() {

              }
        });
    }
}