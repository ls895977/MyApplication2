package com.lykj.mipinduobao.httprequst;

/**
 * author David.yan@qq.com
 * time 2016/3/21 19:29
 */
public interface ApiCallback {
    void onApiSuccess(int status, Object resultData);

    void onApiError(int status, String errors);
}
