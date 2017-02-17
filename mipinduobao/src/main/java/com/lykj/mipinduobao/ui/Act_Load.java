package com.lykj.mipinduobao.ui;
import android.view.View;

import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.common.BaseActivity;


/**
 * 启动时跳出的第一个页面
 * 2016年1月25日下午3:32:24
 */
public class Act_Load extends BaseActivity {
    @Override
    public int initLayoutId() {
        return R.layout.log_layout;
    }

    @Override
    public void initView() {
        hideHeader();
    }

    @Override
    public void initData() {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startAct(Act_GuidePage.class);
            finish();
        }).start();
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
