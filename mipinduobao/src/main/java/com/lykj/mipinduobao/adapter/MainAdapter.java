package com.lykj.mipinduobao.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.lykj.mipinduobao.common.BaseFragment;

import java.util.List;

/**
 * @author {GlowWorm}
 *         <p>
 *         2016-1-16下午2:03:59
 */
public class MainAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mFragments;

    public MainAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
