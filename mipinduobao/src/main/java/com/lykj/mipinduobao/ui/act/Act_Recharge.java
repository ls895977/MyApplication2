package com.lykj.mipinduobao.ui.act;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.CartlistPay;
import com.lykj.mipinduobao.bean.WXPayBean;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.dialog.LoadingDialog;
import com.lykj.mipinduobao.httprequst.VolleyController;
import com.lykj.mipinduobao.wxapi.MD5Utils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 充值界面pager页
 */
public class Act_Recharge extends BaseActivity {
    private LoadingDialog loading;
    ImageView back, huiyuan;
    TextView tv_10, tv_20, tv_30, tv_100, tv_200, recharge_chongzhi_text, t2, t3;
    EditText ed_n;
    private Double amount = 0.01;
    private String order_number;
    Map<String, String> resultunifiedorder;
    private String prepay_id;
    // 微信申请appid
    String Appid = "wxbc0f9aeb945c7eff";
    // 商户id
    String shopingId = "1293836501";
    // 微信后台生成订单接口地址
    String path = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 微信平台软件生成
    String qianmin = "c6b60cf42a0649227d01c050357029ae";

    // 客服端向服务器发起订单详细
    String url = "http://m.mingpinduobao.com/?/mobile/cart/app_addmoney/200/10";
    RadioButton wxchose;

    @Override
    public int initLayoutId() {
        return R.layout.act_recharge_layout;
    }

    @Override
    public void initView() {
        hideHeader();
        loading = new LoadingDialog(context);
        ed_n = getViewAndClick(R.id.recharge_chongzhi_shuru);
        tv_10 = getViewAndClick(R.id.recharge_chongzhi_10);
        tv_20 = getViewAndClick(R.id.recharge_chongzhi_20);
        tv_30 = getViewAndClick(R.id.recharge_chongzhi_30);
        tv_100 = getViewAndClick(R.id.recharge_chongzhi_100);
        tv_200 = getViewAndClick(R.id.recharge_chongzhi_200);
        setOnClickListener(R.id.butt1);
        t2 = getView(R.id.recharge_chongzhi_text1);
        t3 = getView(R.id.recharge_chongzhi_text2);
        ed_n.addTextChangedListener(addEit);
        back = getViewAndClick(R.id.iv_left);
        back.setVisibility(View.VISIBLE);
        huiyuan = getViewAndClick(R.id.iv_right);
        recharge_chongzhi_text = getView(R.id.recharge_chongzhi_text);
        setOnClickListener(R.id.iv_right);//会员
        setOnClickListener(R.id.butt2);
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
            case R.id.recharge_chongzhi_10://10
                setbackground(1);
                recharge_chongzhi_text.setText(String.valueOf(10));
                t2.setText(String.valueOf(10));
                t3.setText(String.valueOf(10));
                amount = (double) 10;
                break;
            case R.id.recharge_chongzhi_20://20
                setbackground(2);
                recharge_chongzhi_text.setText(String.valueOf(20));
                t2.setText(String.valueOf(20));
                t3.setText(String.valueOf(20));
                amount = (double) 20;
                break;
            case R.id.recharge_chongzhi_30://30
                setbackground(3);
                recharge_chongzhi_text.setText(String.valueOf(30));
                t2.setText(String.valueOf(30));
                t3.setText(String.valueOf(30));
                amount = (double) 30;
                break;
            case R.id.recharge_chongzhi_100://100
                setbackground(4);
                recharge_chongzhi_text.setText(String.valueOf(100));
                t2.setText(String.valueOf(100));
                t3.setText(String.valueOf(100));
                amount = (double) 100;
                break;
            case R.id.recharge_chongzhi_200://200
                setbackground(5);
                recharge_chongzhi_text.setText(String.valueOf(200));
                t2.setText(String.valueOf(200));
                t3.setText(String.valueOf(200));
                amount = (double) 200;
                break;
            case R.id.recharge_chongzhi_shuru:
                setbackground(6);
                break;
            case R.id.iv_right://会员
                setResult(5);
                finish();
                break;
            case R.id.iv_left://返回
                finish();
                break;
            case R.id.butt2://我有充值卡
                Intent intent = new Intent();
                intent.putExtra("stats", "autolottery");
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.butt1://充值
                genPayReq();
                break;
        }

    }

    TextWatcher addEit = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (ed_n.getText().toString().length() > 0) {
                recharge_chongzhi_text.setText(ed_n.getText().toString());
                t2.setText(String.valueOf(ed_n.getText().toString()));
                t3.setText(String.valueOf(ed_n.getText().toString()));
                amount = (Double) Double.valueOf(ed_n.getText().toString());
            } else {
                recharge_chongzhi_text.setText(String.valueOf(Integer.valueOf(0)));
                t2.setText(String.valueOf(String.valueOf(0)));
                t3.setText(String.valueOf(String.valueOf(0)));
                amount = (Double) Double.valueOf(0);
            }
        }
    };

    private String genNonceStr() {
        Random random = new Random();
        return MD5Utils.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    private String genOutTradNo() {
        Random random = new Random();
        return MD5Utils.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    /**
     * 设置相应参数
     */
    private void genPayReq() {
        CartlistPay cartlistPay = new CartlistPay();
        List<CartlistPay.CartlistBean> data = new ArrayList<>();
        Gson gson = new Gson();
        CartlistPay.CartlistBean pay = new CartlistPay.CartlistBean();
        pay.setGoods_id("001");
        pay.setGoods_num("1");
        pay.setMoney("" + amount);
        pay.setShenyu("10000");
        data.add(pay);
        cartlistPay.setCartlist(data);
        Map<String, String> parameters = new ArrayMap<>();
        Map<String, String> hader = new ArrayMap<>();
        parameters.put("uid", ACache.get(this).getAsString("uid"));
        parameters.put("account", "10");
        parameters.put("Cartlist", gson.toJson(cartlistPay));
        hader.put("Cookie", getCookies());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, "https://m.mingpinduobao.com/index.php/mobile/cart/app_paysubmit2",
                response -> {
                    Debug.e("=="+response);
                    if (response.contains("appid")) {
                        statWX(response);
                    } else {
                        MyToast.show(this, "支付失败！");
                    }
                    loading.dismiss();
                },
                volleyError -> {
                    loading.dismiss();
                    parameters.clear();
                    if (volleyError instanceof NoConnectionError) {// 指示在执行一个请求时没有任何连接的错误
                        MyToast.show(context, "未能找到指定主机服务器");
                        Debug.e("NoConnectionError：" + volleyError.getMessage());
                    } else if (volleyError instanceof NetworkError) {// 指示在执行一个请求时出现网络错误
                        MyToast.show(context, "网络错误");
                    } else if (volleyError instanceof AuthFailureError) {// 指示在执行请求时有一个验证失败的错误
                        MyToast.show(context, "请求验证失败");
                        Debug.e("AuthFailureError：" + volleyError.getMessage());
                    } else if (volleyError instanceof ParseError) {// 指示服务器的响应不能被解析
                        MyToast.show(context, "服务器的响应不能被解析");
                    } else if (volleyError instanceof TimeoutError) {// 指示连接或套接字超时
                        MyToast.show(context, "连接服务器超时");
                    } else {//404 Unexpected response code 404
                        MyToast.show(context, "404未知");
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return hader;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return parameters;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(30 * 1000, 0, 0f));
        VolleyController.getInstance(context).addToRequestQueue(stringRequest);
    }


    /**
     * 为某个设置外框
     */
    public void setbackground(int indext) {
        switch (indext) {
            case 1:
                tv_10.setBackgroundResource(R.drawable.b11);
                tv_20.setBackgroundResource(R.drawable.image22);
                tv_30.setBackgroundResource(R.drawable.image22);
                tv_100.setBackgroundResource(R.drawable.image22);
                tv_200.setBackgroundResource(R.drawable.image22);
                ed_n.setBackgroundResource(R.drawable.image22);
                break;
            case 2:
                tv_10.setBackgroundResource(R.drawable.image22);
                tv_20.setBackgroundResource(R.drawable.b11);
                tv_30.setBackgroundResource(R.drawable.image22);
                tv_100.setBackgroundResource(R.drawable.image22);
                tv_200.setBackgroundResource(R.drawable.image22);
                ed_n.setBackgroundResource(R.drawable.image22);
                break;
            case 3:
                tv_10.setBackgroundResource(R.drawable.image22);
                tv_20.setBackgroundResource(R.drawable.image22);
                tv_30.setBackgroundResource(R.drawable.b11);
                tv_100.setBackgroundResource(R.drawable.image22);
                tv_200.setBackgroundResource(R.drawable.image22);
                ed_n.setBackgroundResource(R.drawable.image22);
                break;
            case 4:
                tv_10.setBackgroundResource(R.drawable.image22);
                tv_20.setBackgroundResource(R.drawable.image22);
                tv_30.setBackgroundResource(R.drawable.image22);
                tv_100.setBackgroundResource(R.drawable.b11);
                tv_200.setBackgroundResource(R.drawable.image22);
                ed_n.setBackgroundResource(R.drawable.image22);
                break;
            case 5:
                tv_10.setBackgroundResource(R.drawable.image22);
                tv_20.setBackgroundResource(R.drawable.image22);
                tv_30.setBackgroundResource(R.drawable.image22);
                tv_100.setBackgroundResource(R.drawable.image22);
                tv_200.setBackgroundResource(R.drawable.b11);
                ed_n.setBackgroundResource(R.drawable.image22);
                break;
            case 6:
                tv_10.setBackgroundResource(R.drawable.image22);
                tv_20.setBackgroundResource(R.drawable.image22);
                tv_30.setBackgroundResource(R.drawable.image22);
                tv_100.setBackgroundResource(R.drawable.image22);
                tv_200.setBackgroundResource(R.drawable.image22);
                ed_n.setBackgroundResource(R.drawable.b11);
                break;
        }
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

    /**
     * 调起微信
     */
    public void statWX(String json) {
        Debug.e(json);
        Gson gson = new Gson();
        WXPayBean bean = gson.fromJson(json, WXPayBean.class);
        IWXAPI api = WXAPIFactory.createWXAPI(this, ComantUtils.APP_ID_WX);
        api.registerApp(ComantUtils.APP_ID_WX);
        PayReq request = new PayReq();
        request.appId = bean.getAppid();
        request.partnerId = bean.getPartnerid();
        request.prepayId = bean.getPrepayid();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = bean.getNoncestr();
        request.timeStamp = bean.getTimestamp();
        request.sign = bean.getSign();
        api.sendReq(request);
    }
}
