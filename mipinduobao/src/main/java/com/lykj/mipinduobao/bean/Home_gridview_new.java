package com.lykj.mipinduobao.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 最新揭晓实体类
 * 2016年1月17日下午11:09:06
 */
public class Home_gridview_new implements Serializable{

	/**
	 * state : 0
	 * list : [{"id":"1350","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20151217/64554000321572.jpg","q_uid":"6561","q_username":"138****7489"},{"id":"1348","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20151217/64554000321572.jpg","q_uid":"6020","q_username":"中中中"},{"id":"1344","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20151217/24721600301542.jpg","q_uid":"6020","q_username":"中中中"},{"id":"1343","thumb":"http://m.mingpinduobao.com/statics/uploads/shopimg/20151217/64554000321572.jpg","q_uid":"6020","q_username":"中中中"}]
	 */

	private int state;
	private List<ListBean> list;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<ListBean> getList() {
		return list;
	}

	public void setList(List<ListBean> list) {
		this.list = list;
	}

	public static class ListBean {
		/**
		 * id : 1350
		 * thumb : http://m.mingpinduobao.com/statics/uploads/shopimg/20151217/64554000321572.jpg
		 * q_uid : 6561
		 * q_username : 138****7489
		 */

		private String id;
		private String thumb;
		private String q_uid;
		private String q_username;

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

		public String getQ_uid() {
			return q_uid;
		}

		public void setQ_uid(String q_uid) {
			this.q_uid = q_uid;
		}

		public String getQ_username() {
			return q_username;
		}

		public void setQ_username(String q_username) {
			this.q_username = q_username;
		}
	}
}
