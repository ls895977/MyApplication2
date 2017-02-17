package com.lykj.mipinduobao.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.common.ShopingTool;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    public static int indext;
    private Button btn_sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, ComantUtils.APP_ID_WX);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq resp) {

    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case 0:
                MyToast.show(this, "恭喜您！支付成功");
                switch (indext) {
                    case 0:
                        ShopingTool.getShopinInstance(this).deletAllShopin();
                        finish();
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    default:
                        break;
                }
                finish();
                break;
            case -1:
                MyToast.show(this, "错误");
                finish();
                break;
            case -2:
                MyToast.show(this, "您取消了订单");
                setResult(50);
                finish();
                break;
            default:
                break;
        }
    }
}
