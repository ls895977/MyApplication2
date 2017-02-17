package com.lykj.myapplication.ui;

import android.view.View;

import com.lykj.myapplication.R;
import com.lykj.myapplication.common.BaseActivity;
import com.lykj.myapplication.retrofits.Service;

/**
 * author：LiShan
 * Creation time
 */

public class RetrofitAct extends BaseActivity {
    @Override
    public int initLayoutId() {
        return R.layout.act_retrofit;
    }

    @Override
    public void initView() {
        hideHeader();
        setOnClickListener(R.id.but1);
    }

    @Override
    public void initData() {

    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onViewClick(View v) {
            switch (v.getId()){
                case R.id.but1:
                    Service.getInstance().postSS();
                    break;
            }
    }
}
