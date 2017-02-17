package com.lykj.mipinduobao.tools;

/**
 * 常量类 2016-1-18下午2:46:39
 */
public class Constants {
	
	public static  String SHOPING="http://m.mingpinduobao.com/?/mobile/ajax/checkname/";
	public static  String REGISTER_URL="http://m.mingpinduobao.com/?/mobile/ajax/userMobile/";
	
	/**
	 * 获取联合登录用户数据（旧接口）
	 */
	public static final String WX_URL="http://m.mingpinduobao.com/?/mobile/mobile/unionLogin/&unionType=4&unionToken=";
	public static final String QQ_URL="http://m.mingpinduobao.com/?/mobile/mobile/unionLogin/&unionType=2&unionToken=";
	/**
	 * 登录
	 */
	public static final String SIGNI_URL="http://m.mingpinduobao.com/?/mobile/mobile/app_login/";
	
	public static final String GLOBAL_URL = "http://m.mingpinduobao.com";
	/** 获取一级目录 */
	public static final String ONEMENU_URL = GLOBAL_URL
			+ "/?/mobile/mobile/app_menu1";
	/** 获取二级目录 */
	public static final String MUCHMENU_URL = GLOBAL_URL
			+ "/?/mobile/mobile/app_menu2/";
	/** 获取商品详情信息，需要拼接ID在末尾 */
	public static final String PRODUCT_DETAIL = "http://m.mingpinduobao.com/?/mobile/mobile/app_item/";
	public static final String TABLE_NAME = "shop_cart";
	public static final String PRODUCT_ID = "pid";
	public static final String PRODUCT_THUMB = "thumb";
	public static final String PRODUCT_QISHU = "qishu";
	public static final String PRODUCT_TITLE = "title";
	public static final String PRODUCT_ZONGRENSHU = "zongrenshu";
	public static final String PRODUCT_CANYURENSHU = "canyurenshu";
	public static final String PRODUCT_SHENYURENSHU = "shenyurenshu";
	public static final String PRODUCT_RENQI = "renqi";
	public static final String PRODUCT_YUNJIAGE = "yunjiage";
	public static final String OMDATA = "one_menu_date";
	public static final String TMDATA = "two_menu_date";
	public static final String GVDATA = "gv_menu_date";
	//图片
	public static final String HOME_IMAGE="http://m.mingpinduobao.com/?/mobile/ajax/slides";
	//最新揭晓几张图
	public static final String HOME_NEW_SAN="http://m.mingpinduobao.com/?/mobile/mobile/app_index_zxjx";
	//
	public static final String HOME_10="http://m.mingpinduobao.com/?/mobile/mobile/app_renqi/10";
	public static final String HOME_20="http://m.mingpinduobao.com/?/mobile/mobile/app_renqi/20";
	public static final String HOME_30="http://m.mingpinduobao.com/?/mobile/mobile/app_renqi/30";
	public static final String HOME_40="http://m.mingpinduobao.com/?/mobile/mobile/app_renqi/40";
	//商城网站 
	public static String shopingurl="http://m.mingpinduobao.com/?/mobile/cart/paysubmit/money/nobank";
	//购物车获取支付参数
	public static final String shopinpay="http://m.mingpinduobao.com/?/mobile/cart/paysubmitWx";
	// 切换数据
	public static final String EXCHANGE_DATA = GLOBAL_URL+ "/?/mobile/mobile/app_menudata/";
	
	public static final String DEFALUT_DATA = "/10/";
	public static final String NEW_DATA = "/20/";
	public static final String HEIGHT_DATA = "/30/";
	public static final String LATER_DATA = "/40/";
	
	// APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static final String APP_ID = "wxbc0f9aeb945c7eff";

    public static class ShowMsgActivity {
		public static final String STitle = "showmsg_title";
		public static final String SMessage = "showmsg_message";
		public static final String BAThumbData = "showmsg_thumb_data";
	}
}
