package com.lykj.mipinduobao.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.NewsAdapterListview_base;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 最新揭晓apdapter 2016年1月17日下午5:57:38
 */
public class NewsAdapter extends BaseAdapter {
	Context context;
	List<NewsAdapterListview_base> newsList;
	private StringBuffer str;
	private StringBuffer sb;
	int start = 10, end = 20;
	// 定义一个值来管理点击事件：
	boolean tv_static = true;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				tv.setText("点击加载更多");
				adapter.notifyDataSetChanged();
				tv_static = true;
				if (end == 20) {
					tv.setVisibility(View.GONE);
				}
				start = end;
				end = end + 10;
			}
		};
	};
	String strActivity;

	public NewsAdapter(String strActivity, Context context, List<NewsAdapterListview_base> newsList) {
		this.context = context;
		this.newsList = newsList;
		this.strActivity = strActivity;
	};

	private NewsAdapter_Chlidlistview adapter;
	private Button tv;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 1;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsadapter_listview_layout, null);
//		ImageView image = (ImageView) convertView.findViewById(R.id.title_letf_image);
//		image.setBackgroundResource(R.drawable.fanhui);
		tv = (Button) convertView.findViewById(R.id.news_layout_text);
		ListView listview = (ListView) convertView.findViewById(R.id.newadapter_newchild);
//		adapter = new NewsAdapter_Chlidlistview(context, newsList);
		listview.setAdapter(adapter);
		// 会员中心
//		convertView.findViewById(R.id.title_sign_in_button).setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				if (BasePager.UserIsNo) {
//					Toast.makeText(context, "您已在会员页！", 1000).show();
//				} else {
//					Intent intent = new Intent(context, SignActivity.class);
//					context.startActivity(intent);
//				}
//			}
//		});
		// 返回

//		convertView.findViewById(R.id.title_letf_image).setOnClickListener(new OnClickListener() {
//			int i;
//
//			@Override
//			public void onClick(View v) {
//				if (BasePager.UserIsNo) {
//					Intent intent = new Intent(context, Non_Member_Activity.class);
//					context.startActivity(intent);
//				} else {
//					if (i == 0) {
//						Toast.makeText(context, "在按一次退出！", Toast.LENGTH_LONG).show();
//					} else if (i == 2) {
//						try {
//							switch (strActivity) {
//							case "Non_Member_Activity":
//								Non_Member_Activity.ga.setFinshe();
//								break;
//							case "All_Non_Member_Activity":
//								All_Non_Member_Activity.ga.setFinsh();
//								break;
//							case "All_Member_Activity":
//								All_Member_Activity.ga.setFinshe();
//
//								break;
//							case "WebView_Mybaby_member_Activity":
//								WebView_Mybaby_member_Activity.ga.setFinshe();
//								break;
//							case "WebView_member_Activity":
//								WebView_member_Activity.ga.setFinshe();
//								break;
//							case "WebView_Nomember_Activity":
//								WebView_Nomember_Activity.ga.setFinshe();
//								break;
//							}
//						} catch (Exception e) {
//							// TODO: handle exception
//						}
//					}
//					i = i + 1;
//				}
//
//			}
//		});
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (getIntentStatic()) {
					if (tv_static) {
						tv.setText("正在加载");
						Log.e("aa", "您正在加载中。。。。");
						intnetDate();
					}
				} else {
					Toast.makeText(context, "请检查您的网络", Toast.LENGTH_LONG).show();
				}
			}
		});
		return convertView;
	}

	/**
	 * 获取数据信息
	 */
	public void intnetDate() {
//		HttpUtils httpResponse = new HttpUtils();
//		httpResponse.send(HttpRequest.HttpMethod.GET,
//				"http://m.mingpinduobao.com/?/mobile/ajax/getLotteryList/" + start + "/" + end,
//				new RequestCallBack<String>() {
//
//					@Override
//					public void onFailure(HttpException arg0, String arg1) {
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> arg0) {
//
//						getzuixinJson(arg0.result);
//					}
//				});
	}

	/*
	 * 判断网络是否可用
	 */
	public boolean getIntentStatic() {

		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		if (info == null || !info.isAvailable()) {
			return false;
		} else {
			// 得到网络类型
			int type = info.getType();
			if (type == ConnectivityManager.TYPE_MOBILE) {

				// info.isRoaming() 判断是否是漫游网络
			} else if (type == ConnectivityManager.TYPE_WIFI) {
			}
			return true;
		}
	}

	/**
	 * 最亲揭晓json解析
	 */
	public void getzuixinJson(String str) {
		try {
			JSONObject object = new JSONObject(str);
			JSONArray array = object.getJSONArray("listItems");
			for (int i = 0; i < array.length(); i++) {
				JSONObject obj = array.getJSONObject(i);
				String thumb = obj.getString("thumb");
				thumb = "http://m.mingpinduobao.com/statics/uploads/" + thumb;
				String userphoto = obj.getString("userphoto");
				userphoto = "http://m.mingpinduobao.com/statics/uploads/" + userphoto;
				String q_user = obj.getString("q_user");
				String gonumber = obj.getString("gonumber");
				String q_user_code = obj.getString("q_user_code");
				String q_end_time = obj.getString("q_end_time");
				String id = obj.getString("id");
				String q_uid = obj.getString("q_uid");
//				NewsAdapterListview_base base = new NewsAdapterListview_base(thumb, userphoto, q_user, gonumber,
//						q_user_code, q_end_time, id, q_uid);
//				newsList.add(base);
			}
			Message msg = handler.obtainMessage();
			msg.what = 1;
			handler.sendMessage(msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过网址得到字符串
	 */
	public String getDataformNet(String urlpath) {
		// HttpUrlconnction使用
		try {
			// 1、得到url对象 参数：网址
			URL url = new URL(urlpath);
			// 2、得到urlconnection的链接对象
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 3、对连接进行设置
			conn.setReadTimeout(100000);
			conn.setConnectTimeout(10000);
			// 默认情况是get请求 设置请求方法
			conn.setRequestMethod("GET");
			// 得到相应码
			int code = conn.getResponseCode();
			if (code == HttpURLConnection.HTTP_OK) {
				// 通过连接得到一个输入流
				InputStream in = conn.getInputStream();
				// byte[] bfu = new byte[1024];
				// StringBuffer sb = new StringBuffer();
				// int len;
				// while((len = in.read(bfu))!=-1){
				// String str = new String(bfu, 0, len);
				// sb.append(str);
				// }
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String line;
				sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}
}
