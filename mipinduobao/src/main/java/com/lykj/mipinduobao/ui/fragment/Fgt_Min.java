package com.lykj.mipinduobao.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshBase;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshScrollView;
import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.UserDataBean;
import com.lykj.mipinduobao.common.BaseFragment;
import com.lykj.mipinduobao.dialog.LoadingDialog;
import com.lykj.mipinduobao.httprequst.ApiHttp;
import com.lykj.mipinduobao.httprequst.VolleyController;
import com.lykj.mipinduobao.ui.act.Act_MyWeb;
import com.lykj.mipinduobao.ui.act.Act_Recharge;

import java.util.Map;

/**
 * 会员中心 2016年1月20日下午5:39:26
 */
public class Fgt_Min extends BaseFragment {
    ImageView img;
    TextView tv1, tv2, tv3, tv4;
    private ACache aCache;
    PullToRefreshScrollView myScrollview;
    private LoadingDialog loading;
    private ApiHttp apiHttp;
    String url = "http://m.mingpinduobao.com/index.php/mobile/mobile/app_member_info";

    @Override
    public int initLayoutId() {
        return R.layout.fgt_min;
    }

    @Override
    public void initView() {
        apiHttp = new ApiHttp(context);
        hideHeader();
        setOnClickListener(R.id.mybaby_member_listview_button);
        setOnClickListener(R.id.mybaby_member_chongzhi);
        img = getView(R.id.mybaby_member_image);
        tv1 = getView(R.id.mybaby_member_pager_tv1);
        tv2 = getView(R.id.mybaby_member_duobaob_number);
        tv3 = getView(R.id.mybaby_member_doubao_jinyan_text);
        tv4 = getView(R.id.mybaby_member_duobaob_number_yue);
        myScrollview = getView(R.id.min_upUser);
        setOnClickListener(R.id.member_item1);
        setOnClickListener(R.id.member_item2);
        setOnClickListener(R.id.member_item3);
        setOnClickListener(R.id.member_item4);
        setOnClickListener(R.id.member_item5);
        setOnClickListener(R.id.member_item6);
        setOnClickListener(R.id.member_item7);
        setOnClickListener(R.id.member_item8);
        setOnClickListener(R.id.member_item9);
        myScrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                loading.show("数据更新中...");
                setPost();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
            }
        });
    }

    @Override
    public void initData() {
        loading = new LoadingDialog(context);
        updateUI();
    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {
        aCache = ACache.get(context);
        // 设置用户名称:
        tv1.setText(aCache.getAsString("username"));
        // 可用夺宝币
        tv2.setText(aCache.getAsString("score"));
        // 经验值：
        tv3.setText(aCache.getAsString("jingyan"));
        // 余额
        tv4.setText("￥" + aCache.getAsString("money"));
        // 获取图片
        Glide.with(context).load(aCache.getAsString("img")).into(img);
    }

    String stats;

    @Override
    public void onViewClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.mybaby_member_listview_button://退出登录
                ACache.get(context).clear();
                onbuttonCurrent.setOnClick(0);
                MyToast.show(context, "退出成功！");
                break;
            case R.id.mybaby_member_chongzhi://充值
                startAct(Act_Recharge.class);
                break;
            case R.id.member_item1://我的.任务中心
                stats = "wrenwu";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.member_item2://充值转盘
                stats = "lottery";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.member_item3://我的晒单
                stats = "singlelist";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.member_item4://邀请管理
                stats = "invite";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.member_item5://我的名品记录
                stats = "userbuylist";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.member_item6://我的收货地址
                stats = "upadd";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.member_item7://获得的商品
                stats = "orderlist";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.member_item8://账户明细
                stats = "userbalance";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.member_item9://修改密码
                stats = "password";
                intent.putExtra("stats", stats);
                startAct(intent, Act_MyWeb.class);
                break;
        }
    }

    @Override
    public void sendMsg(int flag, Object obj) {

    }

    //此接口定义实现控制替换当前页面
    private Fgt_Home.OnButtonCurrent onbuttonCurrent;

    public void setHomeButonCurrent(Fgt_Home.OnButtonCurrent onButtonClick) {
        onbuttonCurrent = onButtonClick;
    }

    public void setPost() {
        Map<String, String> parameters = new ArrayMap<>();
        Map<String, String> hader = new ArrayMap<>();
        parameters.put("uid", ACache.get(context).getAsString("uid"));
//        parameters.put("cookie", getCookies());
        hader.put("Cookie", getCookies());
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                response -> {
                    Gson gson = new Gson();
                    UserDataBean bean = gson.fromJson(response, UserDataBean.class);
                    loading.dismiss();
                    myScrollview.onRefreshComplete();
                    if(bean.getState()==1){
                        MyToast.show(context,bean.getMsg());
                        return;
                    }
                    MyToast.show(context,bean.getMsg());
                    aCache.put("uid", bean.getUser().getUid());
                    aCache.put("username", bean.getUser().getUsername());
                    aCache.put("mobile", bean.getUser().getMobile());
                    aCache.put("score", bean.getUser().getScore());
                    aCache.put("jingyan", bean.getUser().getJingyan());
                    aCache.put("money", bean.getUser().getMoney());
                    aCache.put("img", bean.getUser().getImg());
                    updateUI();
                },
                volleyError -> {
                    myScrollview.onRefreshComplete();
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
        SharedPreferences shar = context.getSharedPreferences("test",
                Activity.MODE_PRIVATE);
        String cookies = shar.getString("kookie", "");
        return cookies;
    }
}
