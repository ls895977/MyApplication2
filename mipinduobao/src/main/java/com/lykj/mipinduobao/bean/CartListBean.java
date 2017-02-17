package com.lykj.mipinduobao.bean;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * author：LiShan
 * Creation time：2017/1/22 0022
 */
@Entity
public class CartListBean {
    @Id
    private long id;
    /**
     * 商品图片
     */
    private String thumb;
    /**
     * 商品期数
     */
    private String qishu;
    /**
     * 商品名称
     */
    private String title;
    /**
     * 商品总人数
     */
    private String zongrenshu;
    /**
     * 商品参与人数
     */
    private String canyurenshu;
    /**
     * 商品剩余人数
     */
    private String shenyurenshu;
    /**
     * 商品价格
     */
    private String yunjiage;
    /**
     * 商品购买数量
     */
    private int num;
    @Generated(hash = 1783435129)
    public CartListBean(long id, String thumb, String qishu, String title,
            String zongrenshu, String canyurenshu, String shenyurenshu,
            String yunjiage, int num) {
        this.id = id;
        this.thumb = thumb;
        this.qishu = qishu;
        this.title = title;
        this.zongrenshu = zongrenshu;
        this.canyurenshu = canyurenshu;
        this.shenyurenshu = shenyurenshu;
        this.yunjiage = yunjiage;
        this.num = num;
    }
    @Generated(hash = 395015845)
    public CartListBean() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getThumb() {
        return this.thumb;
    }
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
    public String getQishu() {
        return this.qishu;
    }
    public void setQishu(String qishu) {
        this.qishu = qishu;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getZongrenshu() {
        return this.zongrenshu;
    }
    public void setZongrenshu(String zongrenshu) {
        this.zongrenshu = zongrenshu;
    }
    public String getCanyurenshu() {
        return this.canyurenshu;
    }
    public void setCanyurenshu(String canyurenshu) {
        this.canyurenshu = canyurenshu;
    }
    public String getShenyurenshu() {
        return this.shenyurenshu;
    }
    public void setShenyurenshu(String shenyurenshu) {
        this.shenyurenshu = shenyurenshu;
    }
    public String getYunjiage() {
        return this.yunjiage;
    }
    public void setYunjiage(String yunjiage) {
        this.yunjiage = yunjiage;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
}
