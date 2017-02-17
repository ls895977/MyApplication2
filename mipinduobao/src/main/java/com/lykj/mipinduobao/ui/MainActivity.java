package com.lykj.mipinduobao.ui;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.mipinduobao.Fgt_TableHome;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.CartListBean;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.BaseFragment;
import com.lykj.mipinduobao.common.ShopingTool;
import com.lykj.mipinduobao.tools.OnShpinNumUi;
import com.lykj.mipinduobao.ui.act.Act_ProductDetail;
import com.lykj.mipinduobao.ui.act.Act_Sign;
import com.lykj.mipinduobao.ui.fragment.Fgt_AllProduct;
import com.lykj.mipinduobao.ui.fragment.Fgt_Home;
import com.lykj.mipinduobao.ui.fragment.Fgt_Min;
import com.lykj.mipinduobao.ui.fragment.Fgt_News;
import com.lykj.mipinduobao.ui.fragment.Fgt_Shop;
import com.lykj.mipinduobao.view.BadgeView;

import java.util.ArrayList;
import java.util.List;

/**
 * author：LiShan
 * Creation time：2017/1/13 0013
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, Fgt_Home.OnButtonCurrent, Fgt_TableHome.OnBackShoping, OnShpinNumUi, Fgt_Shop.OnNumUI {
    private List<BaseFragment> fgtData = new ArrayList<>();
    private TextView[] tvItem = null;
    private ACache aCache;
    private RelativeLayout myLayout;
    private BadgeView mainNum;

    @Override
    public int initLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void initView() {
        hideHeader();
        myLayout = getView(R.id.main_myFragment);
        tvItem = new TextView[5];
        tvItem[0] = getViewAndClick(R.id.tab_home);
        tvItem[1] = getViewAndClick(R.id.tab_all);
        tvItem[2] = getViewAndClick(R.id.tab_news);
        tvItem[3] = getViewAndClick(R.id.tab_shop_cart);
        tvItem[4] = getViewAndClick(R.id.tab_my_baby);
        tvItem[0].setSelected(true);
        aCache = ACache.get(context);
        mainNum = getView(R.id.shoping_number);
    }

    private Fgt_Min main;

    @Override
    public void initData() {
        Fgt_Home home = new Fgt_Home();
        home.setMyManager(getSupportFragmentManager());//首页
        home.setAct(this);
        home.setShopinUI(this);//实现购物车点击事件回调
        home.setHomeButonCurrent(this);//实现此接口切换页面
        fgtData.add(home);
        Fgt_AllProduct all = new Fgt_AllProduct();
        all.setShopinUI(this);//实现购物车点击事件回调
        all.setShoping(this);
        fgtData.add(all);//所有商品
        fgtData.add(new Fgt_News());//最新揭晓
        Fgt_Shop shop = new Fgt_Shop();//购物车
        shop.setShopinUI(this);//刷新
        shop.setNumUI(this);
        fgtData.add(shop);
        main = new Fgt_Min();//我的
        main.setHomeButonCurrent(this);//实现此接口切换页面
        fgtData.add(main);
        Fgt_TableHome.setOnBack(this);
        getSupportFragmentManager().beginTransaction().add(R.id.main_myFragment, fgtData.get(0)).add(R.id.main_myFragment, fgtData.get(1)).hide(fgtData.get(1)).show(fgtData.get(0)).commit();
        /**
         * 显示购物车商品数量
         */
        updateUI();
    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {
        if (ShopingTool.getShopinInstance(this).shopingAll().size() > 0) {
            mainNum.setVisibility(View.VISIBLE);
            mainNum.setText(String.valueOf(ShopingTool.getShopinInstance(this).shopingAll().size()));
        } else {
            mainNum.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.tab_home:
                setCurrent(0);
                break;
            case R.id.tab_all:
                setCurrent(1);
                break;
            case R.id.tab_news:
                setCurrent(2);
                break;
            case R.id.tab_shop_cart:
                setCurrent(3);
                break;
            case R.id.tab_my_baby:
                setCurrent(4);
                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        setCurrent(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public int currentTabIndex = 0;

    public void setCurrent(int indext) {
        if (indext == 4) {//判断是否是会员
            if (ACache.get(this).getAsString("uid") == null) {
                startActivityForResult(Act_Sign.class, 10);
                return;
            }
        }
        if (currentTabIndex != indext) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fgtData.get(currentTabIndex));
            if (!fgtData.get(indext).isAdded()) {
                trx.add(R.id.main_myFragment, fgtData.get(indext));
            }
            trx.show(fgtData.get(indext)).commit();
        }
        tvItem[currentTabIndex].setSelected(false);
        tvItem[indext].setSelected(true);
        currentTabIndex = indext;
    }

    @Override
    public void setOnClick(int id) {
        setCurrent(id);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//购物车回来执行
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            if (resultCode == 5) {
                setCurrent(4);
            }
        }
        updateUI();
    }

    /**
     * 商品点击详情点击事件
     *
     * @param id
     */
    @Override
    public void onShopingOnclick(String id) {
        Intent intent = new Intent();
        intent.putExtra("productID", id);
        intent.setClass(this, Act_ProductDetail.class);
        startActivityForResult(intent, 10);
    }

    /**
     * 商品购物车点击事件
     *
     * @param bean
     */
    @Override
    public void onUpNumUI(CartListBean bean) {
        if (bean == null) {
            updateUI();
            return;
        }
        ShopingTool.getShopinInstance(context).addShoping(bean);//添加到购物车
        updateUI();
    }

    @Override
    public void numUI() {
        updateUI();
    }
}
