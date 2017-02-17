package com.lykj.mipinduobao.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.lykj.mipinduobao.bean.UserContextBase;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class BaseUiListener implements IUiListener {

	Context context;
	public BaseUiListener(Context context) {
		this.context = context;
	}

	@Override
	public void onCancel() {

	}

	String openid;
	String test;

	@Override
	public void onComplete(Object arg0) {
		test = arg0.toString();
		try {
			JSONObject json = new JSONObject(arg0.toString());
			openid = json.getString("openid");
			login();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onError(UiError e) {
		// TODO Auto-generated method stub
		Log.e("aa", "code:" + e.errorCode + ", msg:" + e.errorMessage + ", detail:" + e.errorDetail);
	}

	/**
	 * 登录逻辑处理
	 */
	public void login() {
//		HttpUtils httputils = new HttpUtils();
//		Log.e("aa", Constants.QQ_URL + openid + "---------");
//		httputils.send(HttpMethod.POST, Constants.QQ_URL + openid, new RequestCallBack<String>() {
//
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				Log.e("aa", "微信登录访问服务返回数据HttpException：---" + arg1);
//			}
//			String msg;
//			String codeStatus;
//			String user;
//
//			@Override
//			public void onSuccess(ResponseInfo<String> arg0) {
//				Log.e("aa", "QQ登录访问服务返回数据：---" + arg0.result);
//				Intent intent = new Intent();
//				try {
//					JSONObject josn = new JSONObject(arg0.result);
//					msg = josn.getString("msg");
//                   String codeStatus=josn.getString("codeStatus");
//					switch (codeStatus) {
//					case "0":
////						codeStatus = josn.getString("codeStatus");
////						Toast.makeText(context, "请绑定您的手机号，以后便可以直接登录!", 1000).show();
////						intent.putExtra("openid", openid);
////						intent.putExtra("mode", "2");
////						intent.putExtra("test", test);
////						intent.setClass(context, RegisterActivity.class);
////						context.startActivity(intent);
//						break;
//					case "1":
////						codeStatus = josn.getString("codeStatus");
//							user = josn.getString("user");
//							String ushell = josn.getString("ushell");
//							String uid = josn.getString("uid");
//							saveCookie("ushell=" + ushell + ";" + "uid=" + uid + ";" + "isapp=1");
////							setReout(user);
//						break;
//					default:
//						Toast.makeText(context, "QQ登录失败!请重新登录", 1000).show();
//						break;
//					}
//
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * 保存获取到的cookie信息
	 */
	public void saveCookie(String cookie) {
		SharedPreferences shar = context.getSharedPreferences("test", Activity.MODE_PRIVATE);
		Editor edit = shar.edit();
		edit.putString("kookie", cookie);
		edit.commit();
	}
	/**
	 * 获取会员信息
	 */
	List<UserContextBase> userList;
	public void setReout(String user) {
		userList = new ArrayList<UserContextBase>();
		JSONObject obj;
		try {
			obj = new JSONObject(user);
			String uid = obj.getString("uid");
			String username = obj.getString("username");
			String mobile = obj.getString("score");
			String score = obj.getString("score");
			String jingyan = obj.getString("jingyan");
			String money = obj.getString("money");
			String img = obj.getString("img");
			UserContextBase base = new UserContextBase(uid, username, mobile, score, jingyan, money, img);
			userList.add(base);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Intent intent = new Intent(context, MainActivity.class);
//		Bundle extras = new Bundle();
//		extras.putSerializable("userlist", (Serializable) userList);
//		intent.putExtras(extras);
//		context.startActivity(intent);
	}
}
