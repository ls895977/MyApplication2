package com.lykj.myapplication.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lykj.aextreme.afinal.adapter.ViewPagerAdapter;
import com.lykj.myapplication.common.BaseFragment;

import java.util.List;

/**
 * author：LiShan
 * Creation time：2016/11/30 0030
 */

public class TableLayoutAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> fragments;

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    private String[] titles;

    public TableLayoutAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseFragment fragment = null;
        try {
            fragment= (BaseFragment) super.instantiateItem(container, position);
        }catch (Exception baseFrament){}
        return fragment;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position%titles.length];
    }
    public List<BaseFragment> getFragments() {
        return fragments;
    }



    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }
}
