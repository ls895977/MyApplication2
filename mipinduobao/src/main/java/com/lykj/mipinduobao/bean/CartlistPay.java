package com.lykj.mipinduobao.bean;

import java.util.List;

/**
 * author：LiShan
 * Creation time：2017/1/22 0022
 */

public class CartlistPay {

    private List<CartlistBean> cartlist;

    public List<CartlistBean> getCartlist() {
        return cartlist;
    }

    public void setCartlist(List<CartlistBean> cartlist) {
        this.cartlist = cartlist;
    }

    public static class CartlistBean {
        /**
         * goods_id : 1349
         * goods_num : 2
         * shenyu : 5683
         * money : 1.00
         */
        private String goods_id;
        private String goods_num;
        private String shenyu;
        private String money;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getShenyu() {
            return shenyu;
        }

        public void setShenyu(String shenyu) {
            this.shenyu = shenyu;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }
    }
}
