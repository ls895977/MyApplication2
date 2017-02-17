package com.lykj.mipinduobao.ui.act;


import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lykj.aextreme.afinal.utils.ACache;
import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.aextreme.afinal.utils.RegularUtils;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.UserDataBean;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.dialog.LoadingDialog;
import com.lykj.mipinduobao.tools.BaseUiListener;
import com.lykj.mipinduobao.tools.IntenetTools;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

/**
 * 登录页面 2016年1月21日下午3:23:58
 */
public class Act_Sign extends BaseActivity {
    private EditText account, pwd;
    private LoadingDialog loading;

    @Override
    public int initLayoutId() {
        return R.layout.act_sign;
    }

    @Override
    public void initView() {
        hideHeader();
        setOnClickListener(R.id.sign_submit);//登录
        setOnClickListener(R.id.my_baby_sign_fanhui);//返回
        setOnClickListener(R.id.sign_FindPassWord);//找回密码
        setOnClickListener(R.id.sign_Register);//注册
        setOnClickListener(R.id.sing_QQ);//QQ
        setOnClickListener(R.id.sign_WX);//WX
        account = getView(R.id.sign_account);//帐号
        pwd = getView(R.id.sign_password);//密码
    }

    @Override
    public void initData() {
        loading = new LoadingDialog(context);
        aCache = ACache.get(this);
    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {

    }

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.my_baby_sign_fanhui:
                finish();
                break;
            case R.id.sign_submit://登录
                submit();
                break;
            case R.id.sign_FindPassWord://找回密码
                startActivityForResult(Act_FindPWD1.class, 10);
                break;
            case R.id.sign_Register://注册
                startActivityForResult(Act_Register.class, 10);
                break;
            case R.id.sing_QQ://QQ登录
                loginQQ();
                break;
            case R.id.sign_WX://微信登录
                getWX();
                break;

        }
    }

    private ACache aCache;
    /**
     * 登录
     */
    String strBase64;
    String msgSt;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (bean.getState() == 0) {
                aCache.put("uid", bean.getUser().getUid());
                aCache.put("username", bean.getUser().getUsername());
                aCache.put("mobile", bean.getUser().getMobile());
                aCache.put("score", bean.getUser().getScore());
                aCache.put("jingyan", bean.getUser().getJingyan());
                aCache.put("money", bean.getUser().getMoney());
                aCache.put("img", bean.getUser().getImg());
                MyToast.show(context, bean.getMsg());
                setResult(5);
                finish();
            } else {
                MyToast.show(context, bean.getMsg());
            }
            loading.dismiss();
        }
    };
    private UserDataBean bean;

    public void submit() {
        String zh = account.getText().toString();
        String passWord = pwd.getText().toString();
        strBase64 = new String(Base64.encode(passWord.getBytes(), Base64.DEFAULT));
        if (zh.length() == 11) {
            if (!RegularUtils.isMobileSimple(zh)) {
                MyToast.show(context, "手机号验证错误！");
                return;
            }
            if (TextUtils.isEmpty(passWord)) {
                MyToast.show(context, "请输入密码！");
                return;
            }
            loading.show("登录中...");
            new Thread(() -> {
                msgSt = IntenetTools.getSignCookie(context, ComantUtils.HttpUrl + "mobile/app_login/" + zh + "/" + strBase64);//登录
                Debug.e("登录信息====" + msgSt);
                Gson gson = new Gson();
                bean = gson.fromJson(msgSt, UserDataBean.class);
                Message message = handler.obtainMessage();
                handler.sendMessage(message);
            }).start();
        } else {
            MyToast.show(context, "手机号码位数不对！");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 5) {
            String phone = data.getStringExtra("phone");
            String pwdNumber = data.getStringExtra("pwd");
            account.setText(phone);
            pwd.setText(pwdNumber);
        }
    }

    /**
     * QQ登录
     */
    private BaseUiListener listener;

    public void loginQQ() {
        listener = new BaseUiListener(this);
        Tencent mTencent = Tencent.createInstance(ComantUtils.APP_ID_QQ, getApplicationContext());
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "Scope", listener);
        }
    }

    /**
     * 微信登录
     */
    public static boolean isWXLogin = false;
    // 调起微信
    public static IWXAPI wxApi;

    public void getWX() {
        wxApi = WXAPIFactory.createWXAPI(this, ComantUtils.APP_ID_WX, true);
        wxApi.registerApp(ComantUtils.APP_ID_WX);
        if (wxApi != null && wxApi.isWXAppInstalled()) {
            isWXLogin = true;
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test_neng";
            wxApi.sendReq(req);
        } else
            MyToast.show(this, "用户未安装微信");
    }
}

