package com.lykj.mipinduobao.ui.act;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;

import java.util.HashMap;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * author：LiShan
 * Creation time：2017/1/19 0019
 */

public class Act_RegisterWebView extends BaseActivity {
    private WebView myWebView;
    private String userPhone;
    private ImageView iv_left, iv_right;

    @Override
    public int initLayoutId() {
        return R.layout.act_registerwebview;
    }

    @Override
    public void initView() {
        hideHeader();
        myWebView = getView(R.id.registerWebView_myWeb);
        iv_left = getViewAndClick(R.id.iv_left);
        iv_right = getViewAndClick(R.id.iv_right);
        iv_left.setVisibility(View.VISIBLE);
        iv_right.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        userPhone = getIntent().getStringExtra("phone");
        HashMap<String,String> map=new HashMap<>();
        map.put("Cookie","isapp=1");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.loadUrl(ComantUtils.HttpUrl + "user/mobilecheck/" + userPhone,map);
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
                case R.id.iv_left:
                    finish();
                    break;
            }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
