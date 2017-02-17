package com.lykj.mipinduobao.httprequst;

/**
 * author David.yan@qq.com
 * time 2016/3/21 19:29
 */
public interface ApiCallbackFiles {
    void onApiSuccess(int what, Object resultData);

    void onApiProgress(int what, int progress);

    void onApiError(int what, String errors);

}
