package com.lykj.myapplication;

import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lykj.aextreme.afinal.view.WaveView;
import com.lykj.myapplication.common.BaseActivity;
import com.lykj.myapplication.httprequst.ApiCallback;
import com.lykj.myapplication.httprequst.ApiCallbackFiles;
import com.lykj.myapplication.ui.Main2Activity;
import com.lykj.myapplication.ui.RXJAvaAct;
import com.lykj.myapplication.ui.RetrofitAct;
import com.lykj.myapplication.ui.TablayoutActivity;
import com.lykj.myapplication.ui.ViewAct;
import com.lykj.myapplication.ui.ViewActivity;


public class MainActivity extends BaseActivity implements ApiCallback, ApiCallbackFiles {
    private WaveView waveView3;
    private ImageView imageView;

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setOnClickListener(R.id.button);
        setOnClickListener(R.id.butt2);
        setTitleback(R.string.tableLayout_title);
        setOnClickListener(R.id.my_rxjava);
        waveView3 = getView(R.id.wave_view);
        imageView = getView(R.id.image);
        setOnClickListener(R.id.bt_retrofit);
        setOnClickListener(R.id.bt_view);
        final FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(-2, -2);
        lp.gravity = Gravity.BOTTOM | Gravity.CENTER;
        waveView3.setOnWaveAnimationListener(new WaveView.OnWaveAnimationListener() {
            @Override
            public void OnWaveAnimation(float y) {
                lp.setMargins(0, 0, 0, (int) y + 2);
                imageView.setLayoutParams(lp);
            }
        });

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
        switch (v.getId()) {
            case R.id.button://tableLayout
                startAct(TablayoutActivity.class);
                break;
            case R.id.butt2:
                startAct(Main2Activity.class);
                break;
            case R.id.my_rxjava:
                startAct(RXJAvaAct.class);
                break;
            case R.id.bt_retrofit:
                startAct(RetrofitAct.class);
                break;
            case R.id.bt_view:
                startAct(ViewAct.class);
                break;
        }
    }

    @Override
    public void onApiSuccess(int status, Object resultData) {

    }

    @Override
    public void onApiProgress(int what, int progress) {

    }

    @Override
    public void onApiError(int status, String errors) {

    }

    public void lodingCode(View view) {
            startAct(ViewActivity.class);

    }
}
