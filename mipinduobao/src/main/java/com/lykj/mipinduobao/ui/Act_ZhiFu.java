package com.lykj.mipinduobao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.ArrayMap;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.httprequst.VolleyController;

import java.util.Map;

/**
 * author：LiShan
 * Creation time：2017/1/20 0020
 */

public class Act_ZhiFu extends BaseActivity {
    @Override
    public int initLayoutId() {
        return R.layout.act_zhifu;
    }

    @Override
    public void initView() {
        hideHeader();
        setOnClickListener(R.id.zhifu);
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
        post();
    }
    public void post() {
        Map<String, String> parameters = new ArrayMap<>();
        Map<String, String> hader = new ArrayMap<>();
        hader.put("Cookie", getCookies());
        parameters.put("submit","5");
        parameters.put("account","10");
        final StringRequest request = new StringRequest(Request.Method.POST, ComantUtils.HttpUrl + "cart/app_paysubmit2",
                response -> {//成功
                    Debug.e(response.toString());

                },
                error -> {//失败

                    Debug.e(error);
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return parameters;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                return hader;
            }
        };
        VolleyController.getInstance(context).addToRequestQueue(request);
    }

    /**
     * 获取存取的cookie信息
     */
    public String getCookies() {
        SharedPreferences shar = getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        String cookies = shar.getString("kookie", "");
        return cookies;
    }
}
