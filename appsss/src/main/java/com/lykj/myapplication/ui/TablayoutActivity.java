package com.lykj.myapplication.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lykj.myapplication.R;
import com.lykj.myapplication.common.BaseActivity;
import com.lykj.myapplication.common.BaseFragment;
import com.lykj.myapplication.ui.adapter.TableLayoutAdapter;
import com.lykj.myapplication.ui.fragment.TableLayoutFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author：LiShan
 * Creation time：2016/11/30 0030
 */

public class TablayoutActivity extends BaseActivity {
    private ViewPager info_viewpager;
    private String[] titles = new String[]{"全部", "氪TV", "O2O", "新硬件", "Fun!!", "企业服务", "Fit&Health", "在线教育", "互联网金融", "大公司", "专栏", "新产品"};
    private android.support.design.widget.TabLayout tab_layout;
    private List<BaseFragment> fragments;

    @Override
    public int initLayoutId() {
        return R.layout.act_tablayout;
    }

    @Override
    public void initView() {
        initHeaderBack(R.string.tableLayout_title, 0);
        tab_layout = getView(R.id.tab_layout);
        info_viewpager = getView(R.id.info_viewpager);
    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            TableLayoutFragment fragment = new TableLayoutFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", titles[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        TableLayoutAdapter adapter = new TableLayoutAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(titles);
        info_viewpager.setAdapter(adapter);
        tab_layout.setupWithViewPager(info_viewpager);
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onViewClick(View v) {

    }
}
