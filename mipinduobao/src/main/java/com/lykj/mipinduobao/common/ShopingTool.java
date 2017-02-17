package com.lykj.mipinduobao.common;

import android.content.Context;
import android.icu.util.Calendar;

import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.bean.CartListBean;
import com.lykj.mipinduobao.bean.ProuctDetallBean;
import com.lykj.mipinduobao.greendao.DBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * author：LiShan
 * Creation time：2017/1/22 0022
 */

public class ShopingTool {
    private static ShopingTool mInstance;
    private Context context;

    public ShopingTool(Context context) {
        this.context = context;
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static ShopingTool getShopinInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new ShopingTool(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 得到购物车商品数量
     */
    public List<CartListBean> shopingAll() {
        data = DBManager.getInstance(context).queryShopingList();
        return data;
    }

    /**
     * 添加商品到购物车
     *
     * @param shopin
     */
    List<CartListBean> data = new ArrayList<>();

    public void addShoping(ProuctDetallBean.ItemBean shopin) {
        if (System.currentTimeMillis() - lastClick <= 1000) {
            Debug.e("点。。。"+(System.currentTimeMillis() - lastClick));
            return;
        }
        lastClick = System.currentTimeMillis();
        MyToast.show(context, "添加到购物车！");
        data = DBManager.getInstance(context).queryShopingList();
        if (data.size() == 0) {//购物车没有任何数据时添加
            CartListBean cartListBean = new CartListBean();
            cartListBean.setId(Long.valueOf(shopin.getId()));
            cartListBean.setThumb(shopin.getThumb());
            cartListBean.setQishu(shopin.getQishu());
            cartListBean.setTitle(shopin.getTitle());
            cartListBean.setZongrenshu(shopin.getZongrenshu());
            cartListBean.setCanyurenshu(shopin.getCanyurenshu());
            cartListBean.setShenyurenshu(shopin.getShenyurenshu());
            cartListBean.setYunjiage(shopin.getYunjiage());
            cartListBean.setNum(1);
            DBManager.getInstance(context).insertUser(cartListBean);
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            CartListBean item = data.get(i);
            if (item.getId() == Long.valueOf(shopin.getId())) {
                CartListBean car = data.get(i);
                int num = car.getNum() + 1;
                car.setNum(num);
                DBManager.getInstance(context).updateUser(car);
                return;
            }
        }
        CartListBean cartListBean = new CartListBean();
        cartListBean.setId(Long.valueOf(shopin.getId()));
        cartListBean.setThumb(shopin.getThumb());
        cartListBean.setQishu(shopin.getQishu());
        cartListBean.setTitle(shopin.getTitle());
        cartListBean.setZongrenshu(shopin.getZongrenshu());
        cartListBean.setCanyurenshu(shopin.getCanyurenshu());
        cartListBean.setShenyurenshu(shopin.getShenyurenshu());
        cartListBean.setYunjiage(shopin.getYunjiage());
        cartListBean.setNum(1);
        DBManager.getInstance(context).insertUser(cartListBean);
    }

    /**
     * 添加购物车商品数量
     *
     * @param shopin
     */
    long lastClick = 0;

    public void addShoping(CartListBean shopin) {
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return;
        }
        lastClick = System.currentTimeMillis();
        MyToast.show(context, "添加到购物车！");
        data = DBManager.getInstance(context).queryShopingList();
        if (data.size() == 0) {//购物车没有任何数据时添加
            CartListBean cartListBean = new CartListBean();
            cartListBean.setId(Long.valueOf(shopin.getId()));
            cartListBean.setThumb(shopin.getThumb());
            cartListBean.setQishu(shopin.getQishu());
            cartListBean.setTitle(shopin.getTitle());
            cartListBean.setZongrenshu(shopin.getZongrenshu());
            cartListBean.setCanyurenshu(shopin.getCanyurenshu());
            cartListBean.setShenyurenshu(shopin.getShenyurenshu());
            cartListBean.setYunjiage(shopin.getYunjiage());
            cartListBean.setNum(1);
            DBManager.getInstance(context).insertUser(cartListBean);
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            CartListBean item = data.get(i);
            if (item.getId() == Long.valueOf(shopin.getId())) {
                CartListBean car = data.get(i);
                int num = Integer.valueOf(shopin.getShenyurenshu());
                if (shopin.getNum() < Integer.valueOf(shopin.getShenyurenshu())) {
                    num = car.getNum() + 1;
                }
                car.setNum(num);
                DBManager.getInstance(context).updateUser(car);
                return;
            }
        }
        CartListBean cartListBean = new CartListBean();
        cartListBean.setId(Long.valueOf(shopin.getId()));
        cartListBean.setThumb(shopin.getThumb());
        cartListBean.setQishu(shopin.getQishu());
        cartListBean.setTitle(shopin.getTitle());
        cartListBean.setZongrenshu(shopin.getZongrenshu());
        cartListBean.setCanyurenshu(shopin.getCanyurenshu());
        cartListBean.setShenyurenshu(shopin.getShenyurenshu());
        cartListBean.setYunjiage(shopin.getYunjiage());
        cartListBean.setNum(1);
        DBManager.getInstance(context).insertUser(cartListBean);
    }

    /**
     * 减少购物车商品数量
     *
     * @param shopin
     */
    public void Reduce(CartListBean shopin) {
        data = DBManager.getInstance(context).queryShopingList();
        for (int i = 0; i < data.size(); i++) {
            CartListBean item = data.get(i);
            if (item.getId() == Long.valueOf(shopin.getId())) {
                CartListBean car = data.get(i);
                int num = 1;
                if (shopin.getNum() == 1) {

                } else {
                    num = car.getNum() - 1;
                }
                car.setNum(num);
                DBManager.getInstance(context).updateUser(car);
                return;
            }
        }
    }

    /**
     * 购物车中删除商品
     *
     * @param item
     */
    public void deletShopin(CartListBean item) {
        DBManager.getInstance(context).deleteUser(item);
    }

    /**
     * 删除购物车所有商品
     */
    public void deletAllShopin() {
        data = DBManager.getInstance(context).queryShopingList();
        for (int i = 0; i < data.size(); i++) {
            CartListBean item = data.get(i);
            DBManager.getInstance(context).deleteUser(item);
        }
    }
}
