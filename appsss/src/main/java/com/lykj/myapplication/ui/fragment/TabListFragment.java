package com.lykj.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lykj.myapplication.R;
import com.lykj.myapplication.ui.base.BaseFragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

/**
 * 作者：Marshon.Chen on 2016/9/8 09:43
 * 邮箱：itmarshon@163.com
 * 功能描述：slidingtab模板
 */
public class TabListFragment extends BaseFragment {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.table11;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bindViews();

    }

    private void bindViews() {
        mRefreshLayout = find(R.id.mRefreshLayout);
        mRecyclerView = find(R.id.mRecyclerView);
    }

}
