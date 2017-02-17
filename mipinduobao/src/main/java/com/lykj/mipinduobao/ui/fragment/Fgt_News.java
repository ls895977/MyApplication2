package com.lykj.mipinduobao.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshBase;
import com.lykj.aextreme.afinal.pulltorefresh.PullToRefreshListView;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.adapter.NewsAdapter_Chlidlistview;
import com.lykj.mipinduobao.bean.NewsAdapterListview_base;
import com.lykj.mipinduobao.common.BaseFragment;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.httprequst.ApiCallback;
import com.lykj.mipinduobao.httprequst.ApiHttp;
import com.lykj.mipinduobao.ui.act.Act_MyWeb;

import java.util.ArrayList;
import java.util.List;

/**
 * 最新揭晓
 *         2016-1-16下午1:50:35
 */
public class Fgt_News extends BaseFragment implements ApiCallback,AdapterView.OnItemClickListener{
	private PullToRefreshListView pullToRefreshListView;
	private ApiHttp apiHttp=null;
	private int qian=0,hou=10;
	@Override
	public int initLayoutId() {
		return R.layout.fgt_news;
	}

	@Override
	public void initView() {
			hideHeader();
		pullToRefreshListView=getView(R.id.news_myPullto);
		pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
		pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				qian=0;
				hou=10;
				requestData();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				qian+=10;
				hou+=10;
				requestData();
			}
		});
		View hader= LayoutInflater.from(context).inflate(R.layout.title,null);
		pullToRefreshListView.getRefreshListView().addHeaderView(hader);
		pullToRefreshListView.setOnItemClickListener(this);
	}

	@Override
	public void initData() {
		showLoading();
		apiHttp=new ApiHttp(context);
		requestData();
	}

	@Override
	public void requestData() {
		apiHttp.postDataCallBack(10, ComantUtils.HttpUrl+"ajax/getLotteryList/"+qian+"/"+hou,this,true);
	}

	@Override
	public void updateUI() {

	}

	@Override
	public void onViewClick(View v) {

	}

	@Override
	public void sendMsg(int flag, Object obj) {

	}
	List<NewsAdapterListview_base.ListItemsBean> datas=new ArrayList<>();
	private NewsAdapter_Chlidlistview adapter=null;
	@Override
	public void onApiSuccess(int status, Object resultData) {
		showCView();
		pullToRefreshListView.onRefreshComplete();
		Gson gson=new Gson();
		NewsAdapterListview_base bean=gson.fromJson(resultData.toString(),NewsAdapterListview_base.class);
		if(qian==0&&hou==10){
			datas.clear();
		}
		for (int i=0;i<bean.getListItems().size();i++){
			datas.add(bean.getListItems().get(i));
		}
		if(adapter==null){
			adapter=new NewsAdapter_Chlidlistview(context,datas);
			pullToRefreshListView.setAdapter(adapter);
		}else {
			adapter.refresh(datas);
		}

	}

	@Override
	public void onApiError(int status, String errors) {
		showCView();
		MyToast.show(context,errors);
		pullToRefreshListView.onRefreshComplete();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent=new Intent();
		intent.putExtra("id",String.valueOf(datas.get(position).getId()));
		intent.putExtra("stats","jiexiaojg");
		intent.setClass(context,Act_MyWeb.class);
		startActivityForResult(intent,10);
	}
}
