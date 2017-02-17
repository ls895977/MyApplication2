package com.lykj.mipinduobao.bean;

import java.io.Serializable;

/**
 * 最热,最新，高价，低价实体类
 * 2016年1月18日下午7:02:10
 */
public class Home_Thehotest_base implements Serializable {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCanyurenshu() {
		return canyurenshu;
	}
	public void setCanyurenshu(String canyurenshu) {
		this.canyurenshu = canyurenshu;
	}
	public String getZongrenshu() {
		return zongrenshu;
	}
	public void setZongrenshu(String zongrenshu) {
		this.zongrenshu = zongrenshu;
	}
	String id;
	String thumb;
	String title;
	String money;
	String canyurenshu;
	String zongrenshu;
	public Home_Thehotest_base(String id, String thumb, String title,
			String money, String canyurenshu, String zongrenshu) {
		super();
		this.id = id;
		this.thumb = thumb;
		this.title = title;
		this.money = money;
		this.canyurenshu = canyurenshu;
		this.zongrenshu = zongrenshu;
	}
	
}
