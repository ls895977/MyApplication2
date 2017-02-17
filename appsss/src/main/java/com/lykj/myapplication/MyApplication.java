package com.lykj.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.lykj.aextreme.afinal.common.BaseApplication;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.cache.DBCacheStore;
import com.yolanda.nohttp.cache.DiskCacheStore;
import com.yolanda.nohttp.cookie.DBCookieStore;

import java.io.File;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class MyApplication extends BaseApplication {
    private static MyApplication app;
    public static MyApplication getApp() {
        return app;
    }

    /**
     * App根目录.
     */
    public static String APP_PATH_ROOT;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //对你没看错就是这么一行就这么简单，NOhttp就是这么简单
        APP_PATH_ROOT = FileUtil.getRootPath().getAbsolutePath() + File.separator + "NoHttpSample";
        FileUtil.initDirectory(APP_PATH_ROOT);
        iniNoHttp();
    }

    public void iniNoHttp() {
//        超时配置，默认10s
        NoHttp.initialize(this, new NoHttp.Config()
                .setConnectTimeout(10 * 1000) // 设置连接超时。
                .setReadTimeout(20 * 1000) // 设置读取超时时间，也就是服务器的响应超时。
        );
//        配置缓存，默认保存在数据库
        NoHttp.initialize(this, new NoHttp.Config()
                // 保存到数据库
                .setCacheStore(
                        new DBCacheStore(this).setEnable(true) // 如果不使用缓存，设置false禁用。
                )
                // 或者保存到SD卡
                .setCacheStore(
                        new DiskCacheStore(this)
                )
        );
        NoHttp.initialize(this, new NoHttp.Config()
                // 默认保存数据库DBCookieStore，开发者可以自己实现。
                .setCookieStore(
                        new DBCookieStore(this).setEnable(true) // 如果不维护cookie，设置false禁用。
                )
        );
//        配置网络层
        NoHttp.initialize(this, new NoHttp.Config()
                        // 使用HttpURLConnection
//                .setNetworkExecutor(new URLConnectionNetworkExecutor())
                        // 使用OkHttp
                        .setNetworkExecutor(new OkHttpNetworkExecutor())
        );

    }
}
