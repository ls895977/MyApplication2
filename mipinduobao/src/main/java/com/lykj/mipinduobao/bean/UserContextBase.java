package com.lykj.mipinduobao.bean;

import java.io.Serializable;

/**
 * 用户信息实体类
 * 2016年1月22日上午1:34:24
 */
public class UserContextBase implements Serializable {
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getJingyan() {
		return jingyan;
	}
	public void setJingyan(String jingyan) {
		this.jingyan = jingyan;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public UserContextBase(String uid, String username, String mobile,
			String score, String jingyan, String money, String img) {
		super();
		this.uid = uid;
		this.username = username;
		this.mobile = mobile;
		this.score = score;
		this.jingyan = jingyan;
		this.money = money;
		this.img = img;
	}
	String uid;
	String username;
	String mobile;
	String score;
	String jingyan;
	String money;
	String img;
}
