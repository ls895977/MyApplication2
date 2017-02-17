package com.lykj.mipinduobao.ui.act;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.aextreme.afinal.view.TimerButton;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.Find1Bean;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.dialog.LoadingDialog;
import com.lykj.mipinduobao.httprequst.ApiCallback;
import com.lykj.mipinduobao.httprequst.ApiHttp;
import com.lykj.mipinduobao.tools.IntenetTools;

/**
 * 找回密码 2016年1月21日下午5:23:17
 */
public class Act_FindPWD2 extends BaseActivity implements ApiCallback {
    Find1Bean find1Bean;
    String phone;
    EditText ed1, ed2;
    IntenetTools tools;
    private TimerButton timerButton;
    private ApiHttp apiHttp;
    private LoadingDialog loading;

    @Override
    public int initLayoutId() {
        return R.layout.act_findpassword2;
    }

    @Override
    public void initView() {
        hideHeader();
        tools = new IntenetTools(this);
        setOnClickListener(R.id.my_baby_sign_fanhui);
        setOnClickListener(R.id.my_baby_imagebuttonQQ);
        setOnClickListener(R.id.my_baby_imagebuttonweixin);
        setOnClickListener(R.id.my_baby_find_password);//注册
        setOnClickListener(R.id.my_baby_register);//用户登录
        setOnClickListener(R.id.mybaby_sign_button);//登录
        timerButton = getViewAndClick(R.id.chonxinfasong);//重新验证
        ed1 = getView(R.id.my_baby_layout_account_nuber);
        ed2 = getView(R.id.my_babay_sign_paseword);
    }

    @Override
    public void initData() {
        apiHttp = new ApiHttp(context);
        loading = new LoadingDialog(context);
        find1Bean = (Find1Bean) getIntent().getSerializableExtra("bean");
        phone = getIntent().getStringExtra("phone");

    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {

    }

    private String code, pwd;

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            // 返回按钮
            case R.id.my_baby_sign_fanhui:

                finish();
                break;
            // qq登录按钮
            case R.id.my_baby_imagebuttonQQ:

                break;
            // 微信登录按钮
            case R.id.my_baby_imagebuttonweixin:

                break;
            // 注册
            case R.id.my_baby_find_password:
                startAct(Act_Register.class);
                break;
            // 登录按钮
            case R.id.mybaby_sign_button:
                code = ed1.getText().toString();
                pwd = ed2.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    MyToast.show(context, "请输入验证码");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    MyToast.show(context, "请输入密码");
                    return;
                }
                if (pwd.length() <= 5) {
                    MyToast.show(context, "请输入六位数密码");
                    return;
                }
                loading.show("提交中...");
                apiHttp.postDataCallBack(2, ComantUtils.HttpUrl + "finduser/app_refreshmobilecode/" + find1Bean.getName(), this, false);
                break;
            case R.id.chonxinfasong://重新发送短息
                loading.show("发送中...");
                apiHttp.putParameters("password", pwd);
                apiHttp.putParameters("checkcode", code);
                apiHttp.postDataCallBack(1, ComantUtils.HttpUrl + "finduser/app_setpassword/" + find1Bean.getName(), this, false);
                break;
        }
    }

    @Override
    public void onApiSuccess(int status, Object resultData) {
        Gson gson = new Gson();
        switch (status) {
            case 1:
                Find1Bean bean = gson.fromJson(resultData.toString(), Find1Bean.class);
                if (bean.getState() == 0) {
                    MyToast.show(context, bean.getMsg());
                    timerButton.start();
                } else {
                    MyToast.show(context, bean.getMsg());
                }
                break;
            case 2:
                Find1Bean bean1 = gson.fromJson(resultData.toString(), Find1Bean.class);
                if (bean1.getState() == 0) {
                    MyToast.show(context, bean1.getMsg());
                    Intent intent = new Intent();
                    intent.putExtra("phone", getIntent().getStringExtra("phone"));
                    intent.putExtra("pwd", pwd);
                    setResult(5, intent);
                    finish();
                } else {
                    MyToast.show(context, bean1.getMsg());
                }
                break;
        }
        loading.dismiss();
    }

    @Override
    public void onApiError(int status, String errors) {
        loading.dismiss();
        MyToast.show(context, errors);
    }
}
