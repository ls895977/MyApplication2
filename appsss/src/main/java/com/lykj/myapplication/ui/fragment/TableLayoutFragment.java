package com.lykj.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.lykj.myapplication.R;
import com.lykj.myapplication.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author：LiShan
 * Creation time：2016/11/30 0030
 */

public class TableLayoutFragment extends BaseFragment {

    @Override
    public int initLayoutId() {
        return R.layout.fgt_table;
    }

    private TextView textView;

    @Override
    public void initView() {
        hideHeader();
        textView = getView(R.id.textView);
    }

    @Override
    public void initData() {
        textView.setText(getArguments().getString("title"));

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

    @Override
    public void sendMsg(int flag, Object obj) {

    }
}
