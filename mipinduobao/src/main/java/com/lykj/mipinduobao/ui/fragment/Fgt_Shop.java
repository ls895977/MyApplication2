package com.lykj.mipinduobao.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.adapter.ShopCartListViewAdapter;
import com.lykj.mipinduobao.bean.CartListBean;
import com.lykj.mipinduobao.common.BaseFragment;
import com.lykj.mipinduobao.common.ShopingTool;
import com.lykj.mipinduobao.greendao.DBManager;
import com.lykj.mipinduobao.tools.OnShpinNumUi;
import com.lykj.mipinduobao.ui.act.Act_Pay;
import com.lykj.mipinduobao.ui.act.Act_Sign;
import com.lykj.mipinduobao.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 购物车 2016-1-16下午1:50:35
 */
public class Fgt_Shop extends BaseFragment implements ShopCartListViewAdapter.OnButtonClick {
    private NoScrollListView myList;
    private LinearLayout linearLayout;
    private TextView shopNum;
    private TextView price;
    private RelativeLayout noData;

    @Override
    public int initLayoutId() {
        return R.layout.fgt_shop;
    }

    @Override
    public void initView() {
        hideHeader();
        noData = getView(R.id.shop_noShopin);
        myList = getView(R.id.shop_myList);
        linearLayout = getView(R.id.shop_data);
        shopNum = getView(R.id.shop_num);
        price = getView(R.id.shop_price);
        setOnClickListener(R.id.shop_cart_button);
    }

    @Override
    public void initData() {
        updateUI();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Debug.e("-----------------------------"+hidden);
        if (!hidden) {
            updateUI();
        }

    }

    @Override
    public void requestData() {

    }

    ShopCartListViewAdapter adapter = null;
    List<CartListBean> data = new ArrayList<>();
    View hader = null;

    @Override
    public void updateUI() {
        data = DBManager.getInstance(context).queryShopingList();
        if (data.size() == 0) {
            noData.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            return;
        }
        noData.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
        if (hader == null) {
            hader = LayoutInflater.from(context).inflate(R.layout.title, null);
            myList.addHeaderView(hader);
        }
        float priceAll = 0;
        int numAll = 0;
        for (int i = 0; i < data.size(); i++) {
            priceAll += (Float.valueOf(data.get(i).getNum()) * Float.valueOf(data.get(i).getYunjiage()));
            numAll += data.get(i).getNum();
        }
        price.setText("合计金额 ：" + priceAll + "元");
        shopNum.setText("总共夺宝" + numAll + "个商品 ");
        if (adapter == null) {
            adapter = new ShopCartListViewAdapter(context, data);
            myList.setAdapter(adapter);
        } else {
            adapter.refresh(data);
        }
        adapter.setonClic(this);
    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.shop_cart_button://结算
                if (ACache.get(context).getAsString("uid") == null) {
                    startAct(Act_Sign.class);
                    return;
                }
                startActivityForResult(Act_Pay.class, 5);
                break;
        }
    }

    @Override
    public void sendMsg(int flag, Object obj) {

    }

    /**
     * 添加操作
     *
     * @param item
     */
    @Override
    public void setOnAdd(CartListBean item) {
        ShopingTool.getShopinInstance(context).addShoping(item);
        updateUI();
    }

    /**
     * 减少操作
     *
     * @param item
     */
    @Override
    public void setReduce(CartListBean item) {
        ShopingTool.getShopinInstance(context).Reduce(item);
        updateUI();
    }

    /**
     * 删除操作
     *
     * @param item
     */
    @Override
    public void setDelte(CartListBean item) {
        ShopingTool.getShopinInstance(context).deletShopin(item);
        updateUI();
        CartListBean ss = null;
        onshpinNum.onUpNumUI(ss);
    }

    /**
     * 商品点击购物车事件
     *
     * @return
     */
    private OnShpinNumUi onshpinNum;

    public void setShopinUI(OnShpinNumUi onshpin) {
        this.onshpinNum = onshpin;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==5) {
            updateUI();
            onui.numUI();
        }
    }

    /**
     * 刷新num购物车数字
     */
    private OnNumUI onui;

    public void setNumUI(OnNumUI numUi) {
        this.onui = numUi;
    }

    ;

    public interface OnNumUI {
        void numUI();
    }

}
