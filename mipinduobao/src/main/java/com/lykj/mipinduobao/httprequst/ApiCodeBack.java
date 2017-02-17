package com.lykj.mipinduobao.httprequst;

/**
 * author David.yan@qq.com
 * time 2016/3/21 19:29
 */
public interface ApiCodeBack {
    void onApiSuccess(Object resultData);

    void onApiCookie(String cookie);

    void onApiError(String errors);
}
