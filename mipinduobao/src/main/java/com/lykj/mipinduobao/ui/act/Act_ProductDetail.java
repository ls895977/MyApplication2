package com.lykj.mipinduobao.ui.act;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.CartListBean;
import com.lykj.mipinduobao.bean.ProuctDetallBean;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.common.ShopingTool;
import com.lykj.mipinduobao.httprequst.ApiCallback;
import com.lykj.mipinduobao.httprequst.ApiHttp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品详情 2016-1-21下午11:14:20
 */
public class Act_ProductDetail extends BaseActivity implements ApiCallback {
    private ApiHttp apiHttp;
    TextView qishu;
    TextView content;
    TextView price;
    ProgressBar pb;
    TextView join;
    TextView all;
    TextView have;
    ImageView iv;
    ScrollView mScrollView;

    @Override
    public int initLayoutId() {
        return R.layout.act_product_detail_layout;
    }

    @Override
    public void initView() {
        hideHeader();
        qishu = getView(R.id.detail_qishu);
        content = getView(R.id.detail_content);
        price = getView(R.id.detail_price);
        pb = getView(R.id.detail_pb);
        join = getView(R.id.detail_jion);
        all = getView(R.id.detail_all);
        have = getView(R.id.detail_have);
        iv = getView(R.id.detail_pager_iv);
        mScrollView = getView(R.id.product_detail_scrollview);
        setOnClickListener(R.id.detail_tv1);//夺宝记录
        setOnClickListener(R.id.detail_tv2);//图文详情
        setOnClickListener(R.id.detail_tv3);//已有幸运
        setOnClickListener(R.id.detail_button1);//立即夺宝
        setOnClickListener(R.id.detail_button2);//加入购物车
        setOnClickListener(R.id.iv_right);
        ImageView ivleft = getViewAndClick(R.id.iv_left);
        ivleft.setVisibility(View.VISIBLE);
    }

    private String productID;

    @Override
    public void initData() {
        showLoading();
        productID = getIntent().getStringExtra("productID");
        apiHttp = new ApiHttp(context);
        apiHttp.postDataCallBack(1, ComantUtils.HttpUrl + "mobile/app_item/" + productID, this, true);
    }


    @Override
    public void requestData() {

    }

    ProuctDetallBean.ItemBean item;

    @Override
    public void updateUI() {
        item = bean.getItem();
        Glide.with(context).load(item.getThumb()).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
        qishu.setText("第" + item.getQishu() + "期");
        content.setText("(第" + item.getQishu() + "期)" + item.getTitle());
        price.setText("价值：" + item.getMoney());
        join.setText(item.getCanyurenshu());
        all.setText(item.getZongrenshu());
        have.setText(item.getShenyurenshu());
        pb.setMax(Integer.valueOf(item.getZongrenshu()));
        pb.setProgress(Integer.valueOf(item.getShenyurenshu()));
    }

    @Override
    public void onViewClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("id", item.getId());
        switch (v.getId()) {
            case R.id.detail_tv1://所有夺宝记录
                intent.putExtra("stats", "buyrecords");
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.detail_tv2://图文详情
                intent.putExtra("stats", "goodsdesc");
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.detail_tv3://已有幸运
                intent.putExtra("stats", "goodspost");
                startAct(intent, Act_MyWeb.class);
                break;
            case R.id.detail_button1://立即夺宝
                CartListBean data = new CartListBean();
                Debug.e(new Gson().toJson(item));
                data.setId(Long.valueOf(bean.getItem().getId()));
                data.setCanyurenshu(bean.getItem().getCanyurenshu());
                data.setNum(1);
                data.setQishu(bean.getItem().getQishu());
                data.setThumb(bean.getItem().getThumb());
                data.setTitle(bean.getItem().getTitle());
                data.setYunjiage(bean.getItem().getYunjiage());
                data.setZongrenshu(bean.getItem().getZongrenshu());
                data.setShenyurenshu(bean.getItem().getShenyurenshu());
                List<CartListBean> dadas = new ArrayList<>();
                dadas.add(data);
                new Act_Pay().setDatas(dadas);
                Intent intent1 = new Intent();
                intent1.putExtra("data", "1");
                startAct(intent1, Act_Pay.class);
                break;
            case R.id.detail_button2://加入购物车
                ShopingTool.getShopinInstance(this).addShoping(item);
                break;
            case R.id.iv_left:
                finish();
                break;
            case R.id.iv_right:
                setResult(5);
                finish();
                break;
        }
    }

    boolean onShoping = false;
    ProuctDetallBean bean;

    @Override
    public void onApiSuccess(int status, Object resultData) {
        Gson gson = new Gson();
        bean = gson.fromJson(resultData.toString(), ProuctDetallBean.class);
        updateUI();
        showCView();
    }

    @Override
    public void onApiError(int status, String errors) {
        MyToast.show(context, errors);
        showCView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (onShoping) {
                setResult(4);
            }
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
