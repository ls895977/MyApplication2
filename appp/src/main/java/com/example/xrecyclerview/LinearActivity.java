package com.example.xrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

public class LinearActivity extends AppCompatActivity {
    private XRecyclerView mRecyclerView;
    private AnimationAdapter mAdapter;
    private ArrayList<String> listData;
    private int refreshTime = 0;
    private int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (XRecyclerView) this.findViewById(R.id.recyclerview);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
//        View header = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup) findViewById(android.R.id.content), false);
//        mRecyclerView.addHeaderView(header);
//        View header1 = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header1);
//        header1.setBackgroundColor(0xff556B2F);
//        View header2 = LayoutInflater.from(this).inflate(R.layout.recyclerview_header, (ViewGroup)findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header2);
//        header2.setBackgroundColor(0xff1874CD);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;
                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listData.clear();
                        for (int i = 0; i < 15; i++) {
                            listData.add("item" + i + "after " + refreshTime + " times of refresh");
                        }
                        MyAdapter adapter = new MyAdapter(listData);
                        mAdapter =new  ScaleInAnimationAdapter(adapter);
                        mAdapter.setFirstOnly(true);
                        mAdapter.setDuration(500);
                        mAdapter.setInterpolator(new OvershootInterpolator(.5f));
                        mRecyclerView.setAdapter(mAdapter);
//                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 15; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 9; i++) {
                                listData.add("item" + (1 + listData.size()));
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });

        listData = new ArrayList<String>();
        MyAdapter adapter = new MyAdapter(listData);
        mAdapter =new  ScaleInAnimationAdapter(adapter);
        mAdapter.setFirstOnly(true);
        mAdapter.setDuration(500);
        mAdapter.setInterpolator(new OvershootInterpolator(.5f));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
