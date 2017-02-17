package com.lykj.mipinduobao.ui.act;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;

import java.util.HashMap;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by lishan on 2017/1/19.
 */

public class Act_MyWeb extends BaseActivity {
    private WebView myWebView;
    private TextView title;
    private ImageView ivLeft;

    @Override
    public int initLayoutId() {
        return R.layout.act_myweb;
    }

    @Override
    public void initView() {
        hideHeader();
        ivLeft = getViewAndClick(R.id.iv_left);
        setOnClickListener(R.id.iv_right);
        title = getView(R.id.tv_title);
        myWebView = getView(R.id.myweb_ID);
        ivLeft.setVisibility(View.VISIBLE);
    }

    String stats;

    @Override
    public void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("deviceType", "1");
        if (ACache.get(context).getAsString("uid") != null) {
            hashMap.put("Cookie", getCookies());
            Debug.e(getCookies()+"-----------");
        } else {
            hashMap.put("Cookie", "isapp=1");
        }
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient());
        stats = getIntent().getStringExtra("stats");
        switch (stats) {
            case "shaidan"://最新晒单
                title.setText("最新晒单");
                myWebView.loadUrl(ComantUtils.HttpUrl + "shaidan/", hashMap);
                break;
            case "duobao"://夺宝记录
                title.setText("我的名品记录");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/userbuylist", hashMap);
                break;
            case "jiexiaojg"://揭晓结果
                String id = getIntent().getStringExtra("id");
                title.setText("揭晓结果");
                myWebView.loadUrl(ComantUtils.HttpUrl + "mobile/dataserver/" + id, hashMap);
                break;
            case "item"://首页banna
                String itemId = getIntent().getStringExtra("id");
                title.setText("提示");
                myWebView.loadUrl(ComantUtils.HttpUrl + "mobile/item/" + itemId, hashMap);
                break;
            case "wrenwu"://任务中心
                title.setText("任务中心");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/wrenwu", hashMap);
                break;
            case "lottery"://充值大抽奖
                title.setText("充值大抽奖");
                myWebView.loadUrl(ComantUtils.HttpUrl + "lottery", hashMap);
                break;
            case "singlelist"://我的晒单
                title.setText("我的晒单");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/singlelist", hashMap);
                break;
            case "invite"://邀请管理
                title.setText("邀请管理");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/invite", hashMap);
                break;
            case "userbuylist"://我的名品记录
                title.setText("我的名品记录");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/userbuylist", hashMap);
                break;
            case "upadd"://我的收货地址
                title.setText("我的收货地址");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/upadd", hashMap);
                break;
            case "orderlist"://获得的商品
                title.setText("获得的商品");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/orderlist", hashMap);
                break;
            case "userbalance"://账户明细
                title.setText("账户明细");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/userbalance", hashMap);
                break;
            case "password"://修改密码
                title.setText("修改密码");
                myWebView.loadUrl(ComantUtils.HttpUrl + "home/password", hashMap);
                break;
            case "autolottery"://我有充值卡
                title.setText("充值");
                myWebView.loadUrl(ComantUtils.HttpUrl + "autolottery", hashMap);
                break;
            case "buyrecords":
                String id1 = getIntent().getStringExtra("id");
                title.setText("所有购买记录");
                myWebView.loadUrl(ComantUtils.HttpUrl + "mobile/buyrecords/"+id1, hashMap);
                break;
            case "goodsdesc":
                String id2 = getIntent().getStringExtra("id");
                title.setText("图文详情");
                myWebView.loadUrl(ComantUtils.HttpUrl + "mobile/goodsdesc/"+id2, hashMap);
                break;
            case "goodspost":
                String id3 = getIntent().getStringExtra("id");
                title.setText("晒单评论");
                myWebView.loadUrl(ComantUtils.HttpUrl + "mobile/goodspost/"+id3, hashMap);
                break;

        }

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
            case R.id.iv_left:
                finish();
                break;
            case R.id.iv_right:
                setResult(5);
                finish();
                break;
        }
    }

    /**
     * 获取存取的cookie信息
     */
    public String getCookies() {
        SharedPreferences shar = context.getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        String cookies = shar.getString("kookie", "");

        return cookies;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
