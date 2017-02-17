package com.lykj.mipinduobao.ui.act;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lykj.aextreme.afinal.utils.MyToast;
import com.lykj.aextreme.afinal.utils.RegularUtils;
import com.lykj.mipinduobao.R;
import com.lykj.mipinduobao.bean.Find1Bean;
import com.lykj.mipinduobao.common.BaseActivity;
import com.lykj.mipinduobao.common.ComantUtils;
import com.lykj.mipinduobao.httprequst.ApiCallback;
import com.lykj.mipinduobao.httprequst.ApiHttp;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.List;

/**
 * 找回密码 第一个页面 2016年1月21日下午7:00:14
 */
public class Act_FindPWD1 extends BaseActivity implements ApiCallback {
    private EditText account, pwd;
    private ImageView yzm;
    private String cookie;
    private ApiHttp apiHttp;

    @Override
    public int initLayoutId() {
        return R.layout.act_findpwd1;
    }

    @Override
    public void initView() {
        hideHeader();
        setOnClickListener(R.id.findpwd1_back);//返回
        setOnClickListener(R.id.findpwd1_submit);//下一步
        setOnClickListener(R.id.findpwd1_QQ);//QQ登录
        setOnClickListener(R.id.findpwd1_weixin);//微信登录
        account = getView(R.id.findpwd1_account);//帐号
        pwd = getView(R.id.findpwd1_pwd);//密码
        yzm = getView(R.id.findpwd1_yzm);
    }

    @Override
    public void initData() {
        apiHttp = new ApiHttp(context);
        setimage();
    }

    @Override
    public void requestData() {

    }

    @Override
    public void updateUI() {

    }

    private String zh;

    @Override
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.findpwd1_back:
                finish();
                break;
            case R.id.findpwd1_submit://下一步
                zh = account.getText().toString();
                String yzm = pwd.getText().toString();
                if (!RegularUtils.isMobileExact(zh)) {
                    MyToast.show(this, "请输入正确手机号");
                    return;
                }
                if (yzm.length() == 0) {
                    MyToast.show(this, "请输入验证码");
                    return;
                }
                apiHttp.putParameters("name", zh);
                apiHttp.putParameters("txtRegSN", yzm);
                cookie = cookie.replace("checkcode", "");
                apiHttp.putParameters("checkcode", cookie);
                apiHttp.postDataCallBack(1, ComantUtils.HttpUrl + "finduser/app_findpassword", this, false);
                break;
        }
    }

    /**
     * 获取验证码
     */
    public void setimage() {
        new Thread(() -> {
            deidaocookie("http://m.mingpinduobao.com/?/api/checkcode/image/60_25_0_0_4/");
        }).start();
    }

    /**
     * 获取cookie
     */
    public void deidaocookie(String url) {
        url = url.trim();
        HttpGet request = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获得cookie
                getCookie(httpClient);
                byte[] by = EntityUtils.toByteArray(response.getEntity());
                Bitmap bit = BitmapFactory.decodeByteArray(by, 0, by.length);
                yzm.setImageBitmap(bit);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取cookies
     *
     * @param httpClient
     */
    private void getCookie(HttpClient httpClient) {
        List<Cookie> cookies = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            if (!TextUtils.isEmpty(cookieName) && !TextUtils.isEmpty(cookieValue)) {
                sb.append(cookieName);
                sb.append(cookieValue);
            }
        }
        cookie = sb.toString();
    }

    @Override
    public void onApiSuccess(int status, Object resultData) {
        // 获取验证码
        setimage();
        Gson gson = new Gson();
        Find1Bean bean = gson.fromJson(resultData.toString(), Find1Bean.class);
        if (bean.getState() == 0) {
            MyToast.show(context, bean.getMsg());
            Intent intent = new Intent();
            intent.putExtra("phone", zh);
            intent.putExtra("bean", bean);
            intent.setClass(this, Act_FindPWD2.class);
            startActivityForResult(intent, 10);
        } else {
            MyToast.show(context, bean.getMsg());
        }
    }

    @Override
    public void onApiError(int status, String errors) {
        MyToast.show(context, errors);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 5) {
            Intent intent = new Intent();
            intent.putExtra("phone", data.getStringExtra("phone"));
            intent.putExtra("pwd", data.getStringExtra("pwd"));
            setResult(5, intent);
            finish();
        }

    }
}
