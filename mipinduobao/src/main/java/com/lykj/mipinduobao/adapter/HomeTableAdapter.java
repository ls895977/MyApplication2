package com.lykj.mipinduobao.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.common.BaseFragment;

import java.util.List;

/**
 * author：LiShan
 * Creation time：2017/1/13 0013
 */

public class HomeTableAdapter extends FragmentStatePagerAdapter {
    private int [] imageviewId = {R.drawable.send_selector,
            R.drawable.send_selector1,
            R.drawable.send_selector2,
            R.drawable.send_selector3};

    private List<BaseFragment> fragments;
    private Context context;
    public void setContext(Context con){
        this.context=con;
    }
    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public HomeTableAdapter(FragmentManager fm) {
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
    public CharSequence getPageTitle(int position) {
        return null;
    }
    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_tab, null);
        TextView textview = (TextView) view.findViewById(R.id.tab_tv1);
        ImageView imageview = (ImageView) view.findViewById(R.id.tab_iv1);
        textview.setText(titles[position]);
        imageview.setImageResource(imageviewId[position]);
        return view;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    private String[] titles;
}
