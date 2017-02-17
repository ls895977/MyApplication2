package com.lykj.mipinduobao.wxapi;
import android.app.Activity;
import android.os.Bundle;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.ui.act.Act_Sign;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Act_Sign.wxApi.handleIntent(getIntent(), this);
	}

	@Override
	public void onReq(BaseReq arg0) {
		
	}
	
	@Override
	public void onResp(BaseResp resp) {
		MyToast.show(this,"返回码："+resp.errCode);
		switch (resp.errCode) {
			case BaseResp.ErrCode.ERR_OK://发送成功
				Debug.e("发送成功。。。");
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL://发送取消
				Debug.e("发送取消。。。");
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED://发送被拒绝
				Debug.e("发送被拒绝。。。");
				break;
			default://发送返回
				Debug.e("发送返回。。。");
				break;
		}
		finish();
	}
}
