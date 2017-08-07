package com.wzsykj.wuyaojiu.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by chen on 16/6/7.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private String[] tabList;
    private List<Fragment> viewList;

    public MainViewPagerAdapter(FragmentManager fm, String[] tabList, List<Fragment> viewList) {
        super(fm);
        this.tabList = tabList;
        this.viewList = viewList;
    }

    @Override
    public Fragment getItem(int position) {
        return viewList.get(position);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabList[position];
    }
}
