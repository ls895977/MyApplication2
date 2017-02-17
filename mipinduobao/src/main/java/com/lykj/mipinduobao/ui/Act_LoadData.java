package com.lykj.mipinduobao.ui;

import android.view.View;

import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.common.BaseActivity;

public class Act_LoadData extends BaseActivity {
	@Override
	public int initLayoutId() {
		return R.layout.lodingdatelayout;
	}
	@Override
	public void initView() {
		hideHeader();
//		mLoadingTv = (TextView) findViewById(R.id.loadingTv);
//		mImageView = (ImageView) findViewById(R.id.loadingIv);
	}
	@Override
	public void initData() {
		startAct(MainActivity.class);
		finish();
	}

	@Override
	public void requestData() {

	}

	@Override
	public void updateUI() {

	}

	@Override
	public void onViewClick(View v) {

	}
}
