package com.lykj.mipinduobao.ui.act;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.lykj.mipinduobao.adapter.PayAdapter;
import com.lykj.mipinduobao.bean.CartListBean;
import com.lykj.mipinduobao.bean.CartlistPay;
import com.lykj.mipinduobao.bean.WXPayBean;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.common.ShopingTool;
import com.lykj.mipinduobao.dialog.LoadingDialog;
import com.lykj.mipinduobao.httprequst.VolleyController;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author：LiShan
 * Creation time：2017/1/23 0023
 */
public class Act_Pay extends BaseActivity {
    private ImageView left, right;
    private TextView title, tv_money, tv_money1, pintai;
    private ListView myListview;
    private RadioButton payRadio;
    private ACache aCache;
    private float myMoney, moneyAll;
    private LoadingDialog loading;

    @Override
    public int initLayoutId() {
        return R.layout.act_pay;
    }

    @Override
    public void initView() {
        hideHeader();
        right = getView(R.id.iv_right);
        right.setVisibility(View.GONE);
        left = getViewAndClick(R.id.iv_left);
        left.setVisibility(View.VISIBLE);
        title = getView(R.id.tv_title);
        title.setText("结算支付");
        myListview = getView(R.id.my_payList);
        tv_money = getView(R.id.pay_allMoney);
        tv_money1 = getView(R.id.pay_Money);
        pintai = getView(R.id.pingtai);
        payRadio = getView(R.id.pay_button);
        aCache = ACache.get(this);
        setOnClickListener(R.id.pay_pay);
    }

    static List<CartListBean> datas;

    public void setDatas(List<CartListBean> datas) {
        this.datas = datas;
    }

    @Override
    public void initData() {
        loading = new LoadingDialog(context);
        if (getIntent().getStringExtra("data") != null) {

        } else {
            datas = ShopingTool.getShopinInstance(this).shopingAll();
        }
        if (datas == null) {
            return;
        }
        for (int i = 0; i < datas.size(); i++) {
            int num = datas.get(i).getNum();
            float jiage = Float.valueOf(datas.get(i).getYunjiage());
            moneyAll += (num * jiage);
        }
        myMoney = Float.valueOf(aCache.getAsString("money"));
        if (myMoney < moneyAll) {
            payRadio.setChecked(true);
        } else {
            payRadio.setChecked(false);
        }
        if (payRadio.isChecked()) {
            pintai.setText("选择微信支付 ");
        } else {
            pintai.setText("选择平台支付 ");
        }
        tv_money1.setText(String.valueOf(moneyAll));
        tv_money.setText(String.valueOf(moneyAll));
        PayAdapter adapter = new PayAdapter(context, datas);
        myListview.setAdapter(adapter);
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
            case R.id.pay_pay://支付
                loading.show("支付中...");
                if (payRadio.isChecked()) {//微信
                    WXPay();
                } else {//平台支付
                    pinTaiPay();
                }
                break;
        }
    }

    /**
     * 平台支付
     */
    public void pinTaiPay() {
        CartlistPay cartlistPay = new CartlistPay();
        List<CartlistPay.CartlistBean> data = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < datas.size(); i++) {
            CartlistPay.CartlistBean pay = new CartlistPay.CartlistBean();
            pay.setGoods_id(String.valueOf(datas.get(i).getId()));
            pay.setGoods_num(String.valueOf(datas.get(i).getNum()));
            pay.setMoney(String.valueOf(datas.get(i).getYunjiage()));
            pay.setShenyu(String.valueOf(datas.get(i).getShenyurenshu()));
            data.add(pay);
            cartlistPay.setCartlist(data);
        }
        Map<String, String> parameters = new ArrayMap<>();
        Map<String, String> hader = new ArrayMap<>();
        parameters.put("uid", ACache.get(this).getAsString("uid"));
        parameters.put("account", "10");
        parameters.put("Cartlist", gson.toJson(cartlistPay));
        hader.put("Cookie", getCookies());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, "https://m.mingpinduobao.com/index.php/mobile/cart/app_paysubmit2",
                response -> {
                    Debug.e(response);
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        int state = jsonObject.getInt("state");
                        String message = jsonObject.getString("message");
                        if (state == 0) {
                            ShopingTool.getShopinInstance(this).deletAllShopin();
                            MyToast.show(this, "支付成功！");
                            loading.dismiss();
                            finish();
                            return;
                        }
                        MyToast.show(this, message);
                    } catch (JSONException e) {
                        e.printStackTrace();
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
     * 微信支付
     */
    public void WXPay() {
        CartlistPay cartlistPay = new CartlistPay();
        List<CartlistPay.CartlistBean> data = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < datas.size(); i++) {
            CartlistPay.CartlistBean pay = new CartlistPay.CartlistBean();
            pay.setGoods_id(String.valueOf(datas.get(i).getId()));
            pay.setGoods_num(String.valueOf(datas.get(i).getNum()));
            pay.setMoney(String.valueOf(datas.get(i).getYunjiage()));
            pay.setShenyu(String.valueOf(datas.get(i).getShenyurenshu()));
            data.add(pay);
            cartlistPay.setCartlist(data);
        }
        Map<String, String> parameters = new ArrayMap<>();
        Map<String, String> hader = new ArrayMap<>();
        parameters.put("uid", ACache.get(this).getAsString("uid"));
        parameters.put("account", "10");
        parameters.put("Cartlist", gson.toJson(cartlistPay));
        hader.put("Cookie", getCookies());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, "https://m.mingpinduobao.com/index.php/mobile/cart/app_paysubmit2",
                response -> {
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
