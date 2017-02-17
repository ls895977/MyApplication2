package com.lykj.mipinduobao.ui.act;


import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;

import com.lykj.aextreme.afinal.utils.Debug;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.aextreme.afinal.utils.RegularUtils;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.dialog.LoadingDialog;
import com.lykj.mipinduobao.httprequst.ApiCallback;
import com.lykj.mipinduobao.httprequst.ApiHttp;
import com.lykj.mipinduobao.tools.IntenetTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册界面布局加载中心 2016年1月20日下午1:05:40
 */
public class Act_Register extends BaseActivity implements ApiCallback {
    EditText account, pwd, code;
    private ApiHttp apiHttp;
    private LoadingDialog loading;
    private String strBase64;

    @Override
    public int initLayoutId() {
        return R.layout.act_sign;
    }

    @Override
    public void initView() {
        hideHeader();
        setOnClickListener(R.id.register_submit);//注册
        account = getView(R.id.register_account);//帐号
        pwd = getView(R.id.register_passwrod);//密码
    }

    @Override
    public void initData() {
        loading = new LoadingDialog(context);
        apiHttp = new ApiHttp(this);
    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {

    }

    private String accountNumber, pwdNumber;

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.my_baby_sign_fanhui://返回
                finish();
                break;
            case R.id.register_submit://注册
                accountNumber = account.getText().toString();
                pwdNumber = pwd.getText().toString();
                strBase64 = new String(Base64.encode(pwdNumber.getBytes(), Base64.DEFAULT));
                if (accountNumber.length() == 11) {
                    if (!RegularUtils.isMobileSimple(accountNumber)) {
                        MyToast.show(context, "手机号验证错误！");
                        return;
                    }
                    if (TextUtils.isEmpty(pwdNumber)) {
                        MyToast.show(context, "请输入密码！");
                        return;
                    }
                    loading.show("注册中...");
                    apiHttp.postDataCallBack(1, ComantUtils.HttpUrl + "ajax/checkname/" + accountNumber, this, false);//检测手机号
                } else {
                    MyToast.show(context, "手机号码位数不对！");
                }
                break;
        }
    }

    public void subMit() {//用户注册
        new Thread(() -> {
            Map<String, String> params = new HashMap<>();
            String result = IntenetTools.submitPostData(
                    ComantUtils.HttpUrl + "ajax/userMobile/" + accountNumber + "/" + strBase64, params, "Utf-8");
            String str1 = pullJson(result);
            loading.dismiss();
            if (str1.equals("0")) {
//                IntenetTools.getSignCookie(ComantUtils.HttpUrl + "mobile/app_login/" + edAccount + "/" + strBase64);//登录
//                setJson(result);
                Intent intent = new Intent();
                intent.putExtra("phone", accountNumber);
                intent.setClass(this, Act_RegisterWebView.class);
                startActivityForResult(intent, 10);
            } else {
                MyToast.show(context, "该手机号已被注册!");
            }
        }).start();
    }

    private int regA;

    @Override
    public void onApiSuccess(int status, Object resultData) {
        switch (status) {
            case 1:
                Debug.e("检测手机号有没有被注册====" + resultData.toString());
                try {
                    JSONObject jsonObject = new JSONObject(resultData.toString());
                    regA = jsonObject.getInt("state");
                    if (regA == 1) {
                        loading.dismiss();
                        MyToast.show(context, "该号码已被注册!");
                        return;
                    }
                    subMit();//注册
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onApiError(int status, String errors) {
        loading.dismiss();
        MyToast.show(context, errors);
    }

    public String pullJson(String str) {
        String instatic = "";
        try {
            JSONObject json = new JSONObject(str);
            instatic = json.getString("state");
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return instatic;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            Intent intent = new Intent();
            intent.putExtra("pwd", pwdNumber);
            intent.putExtra("phone", accountNumber);
            setResult(5, intent);
            finish();
        }
    }
}
