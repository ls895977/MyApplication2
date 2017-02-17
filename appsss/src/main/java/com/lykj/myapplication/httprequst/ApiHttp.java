package com.lykj.myapplication.httprequst;

import android.content.Context;

import com.lykj.myapplication.MyApplication;
import com.lykj.myapplication.R;
import com.yolanda.nohttp.BasicBinary;
import com.yolanda.nohttp.Binary;
import com.yolanda.nohttp.FileBinary;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.OnUploadListener;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.download.DownloadListener;
import com.yolanda.nohttp.download.DownloadRequest;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.NotFoundCacheError;
import com.yolanda.nohttp.error.ParseError;
import com.yolanda.nohttp.error.ServerError;
import com.yolanda.nohttp.error.StorageReadWriteError;
import com.yolanda.nohttp.error.StorageSpaceNotEnoughError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.CacheMode;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.SimpleResponseListener;
import com.yolanda.nohttp.tools.IOUtils;

import java.io.File;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class ApiHttp {
    private Map<String, String> parameters = new HashMap<String, String>();
    private Map<String, String> headers = new HashMap<>();
    private Map<String, File> fileHash = new HashMap<>();
    private ApiCallback apiCallback;
    ApiCallbackFiles apiFile;
    Context context;
    RequestQueue requestQueue = NoHttp.newRequestQueue();

    public ApiHttp(Context context) {
        this.context = context;
        //Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        //Logger.setTag("NoHttpSample");// 设置NoHttp打印Log的tag。
    }

    public void putParameters(String k, String v) {
        parameters.put(k, v);
    }

    public void putHeaders(String k, String v) {
        headers.put(k, v);
    }

    public void putFiles(String k, File v) {
        fileHash.put(k, v);
    }

    /**
     * @param status      请求状态
     * @param url         请求网址
     * @param apiCallback 回调
     * @param cacheMode   是否缓存
     */
    public void postDataCallBack(int status, String url, final ApiCallback apiCallback, boolean cacheMode) {
        this.apiCallback = apiCallback;
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        if (cacheMode) {
            request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);//设置为REQUEST_NETWORK_FAILED_READ_CACHE表示请求服务器失败，就返回上次的缓存，如果缓存为空才会请求失败。
        }
        request.add(parameters);
        for (Iterator iterator = headers.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry element = (Map.Entry) iterator.next();
            String key = (String) element.getKey();
            String value = (String) element.getValue();
            request.addHeader(key, value);
        }
        parameters.clear();
        headers.clear();
        requestQueue.add(status, request, responseStringListener);
    }

    /**
     * @param status      请求状态
     * @param url         请求网址
     * @param apiCallback 回调
     * @param cacheMode   是否缓存
     */
    public void getDataCallBack(int status, String url, final ApiCallback apiCallback, boolean cacheMode) {
        this.apiCallback = apiCallback;
        Request<String> request = NoHttp.createStringRequest(url);
        if (cacheMode) {
            request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);//设置为REQUEST_NETWORK_FAILED_READ_CACHE表示请求服务器失败，就返回上次的缓存，如果缓存为空才会请求失败。
        }
        request.add(parameters);
        for (Iterator iterator = headers.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry element = (Map.Entry) iterator.next();
            String key = (String) element.getKey();
            String value = (String) element.getValue();
            request.addHeader(key, value);
        }
        parameters.clear();
        headers.clear();
        requestQueue.add(status, request, responseStringListener);
    }

    /**
     * 返回String---请求结果
     */
    SimpleResponseListener<String> responseStringListener = new SimpleResponseListener<String>() {
        @Override//请求成功的回调
        public void onSucceed(int what, Response<String> response) {
            boolean fromCache = response.isFromCache();
            if (fromCache) {//结果来自于缓存
                apiCallback.onApiSuccess(what, response.get());
                return;
            }
            int responseCode = response.responseCode();
            if (responseCode != 200) {
                apiCallback.onApiError(what, "请求成功！但网页未成功返回数据！");
                return;
            }
            apiCallback.onApiSuccess(what, response.get());
        }

        @Override
        public void onFailed(int what, Response<String> response) {
            // 请求失败
            Exception exception = response.getException();
            if (exception instanceof NetworkError) {// 网络不好
                apiCallback.onApiError(what, context.getResources().getString(R.string.error_please_check_network));
            } else if (exception instanceof TimeoutError) {// 请求超时
                apiCallback.onApiError(what, context.getResources().getString(R.string.error_timeout));
            } else if (exception instanceof UnKnownHostError) {// 找不到服务器
                apiCallback.onApiError(what, context.getResources().getString(R.string.error_not_found_server));
            } else if (exception instanceof URLError) {// URL是错的
                apiCallback.onApiError(what, context.getResources().getString(R.string.error_url_error));
            } else if (exception instanceof NotFoundCacheError) {
                // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
                apiCallback.onApiError(what, context.getResources().getString(R.string.error_not_found_cache));
            } else if (exception instanceof ProtocolException) {
                apiCallback.onApiError(what, context.getResources().getString(R.string.error_system_unsupport_method));
            } else if (exception instanceof ParseError) {
                apiCallback.onApiError(what, context.getResources().getString(R.string.error_parse_data_error));
            } else {
                apiCallback.onApiError(what, context.getResources().getString(R.string.error_unknow));
            }
        }
    };
    /**
     * 返回上传文件结果Files---请求结果
     */
    SimpleResponseListener<String> responseFilesListener = new SimpleResponseListener<String>() {
        @Override//请求成功的回调
        public void onSucceed(int what, Response<String> response) {
            int responseCode = response.responseCode();
            if (responseCode != 200) {
                apiFile.onApiSuccess(what, "请求成功！但网页未成功返回数据！");
                return;
            }
            apiFile.onApiSuccess(what, response.get());
        }

        @Override
        public void onFailed(int what, Response<String> response) {
            // 请求失败
            Exception exception = response.getException();
            if (exception instanceof NetworkError) {// 网络不好
                apiFile.onApiError(what, context.getResources().getString(R.string.error_please_check_network));
            } else if (exception instanceof TimeoutError) {// 请求超时
                apiFile.onApiError(what, context.getResources().getString(R.string.error_timeout));
            } else if (exception instanceof UnKnownHostError) {// 找不到服务器
                apiFile.onApiError(what, context.getResources().getString(R.string.error_not_found_server));
            } else if (exception instanceof URLError) {// URL是错的
                apiFile.onApiError(what, context.getResources().getString(R.string.error_url_error));
            } else if (exception instanceof NotFoundCacheError) {
                // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
                apiFile.onApiError(what, context.getResources().getString(R.string.error_not_found_cache));
            } else if (exception instanceof ProtocolException) {
                apiFile.onApiError(what, context.getResources().getString(R.string.error_system_unsupport_method));
            } else if (exception instanceof ParseError) {
                apiFile.onApiError(what, context.getResources().getString(R.string.error_parse_data_error));
            } else {
                apiFile.onApiError(what, context.getResources().getString(R.string.error_unknow));
            }
        }
    };

    /**
     * @param status
     * @param url     上传文件地址
     * @param apiFile 回调
     */
    public void postFilesCallBack(int status, String url, ApiCallbackFiles apiFile) {
        this.apiFile = apiFile;
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add(parameters);
        for (Iterator iterator = headers.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry element = (Map.Entry) iterator.next();
            String key = (String) element.getKey();
            String value = (String) element.getValue();
            request.addHeader(key, value);
        }
        int i = 0;
        for (Iterator iterator = fileHash.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry element = (Map.Entry) iterator.next();
            String key = (String) element.getKey();
            File value = (File) element.getValue();
            BasicBinary binary = new FileBinary(value);
            binary.setUploadListener(i, mOnUploadListener);
            request.add(key, binary);
            i++;
        }
        parameters.clear();
        headers.clear();
        fileHash.clear();
        requestQueue.add(status, request, responseFilesListener);
    }

    /**
     * 一个key对应多个文件上传方式
     * @param status
     * @param url
     * @param apiFile
     */
    public void postFileCallBack(int status, String url, ApiCallbackFiles apiFile) {
        this.apiFile = apiFile;
        Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);
        request.add(parameters);
        for (Iterator iterator = headers.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry element = (Map.Entry) iterator.next();
            String key = (String) element.getKey();
            String value = (String) element.getValue();
            request.addHeader(key, value);
        }
        int i = 0;
        String key = "";
        List<Binary> binaries = new ArrayList<>();
        for (Iterator iterator = fileHash.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry element = (Map.Entry) iterator.next();
            key = (String) element.getKey();
            File value = (File) element.getValue();
            BasicBinary binary = new FileBinary(value);
            binary.setUploadListener(i, mOnUploadListener);
            binaries.add(binary);
            i++;
        }
        request.add(key, binaries);
        parameters.clear();
        headers.clear();
        fileHash.clear();
        requestQueue.add(status, request, responseFilesListener);
    }

    /**
     * 文件上传监听。
     */
    private OnUploadListener mOnUploadListener = new OnUploadListener() {

        @Override
        public void onStart(int what) {// 这个文件开始上传。

        }

        @Override
        public void onCancel(int what) {// 这个文件的上传被取消时。

        }

        @Override
        public void onProgress(int what, int progress) {// 这个文件的上传进度发生变化
            apiFile.onApiProgress(what, progress);
        }

        @Override
        public void onFinish(int what) {// 文件上传完成

        }

        @Override
        public void onError(int what, Exception exception) {// 文件上传发生错误。

        }
    };
    /**
     * 下载请求.
     */
    private DownloadRequest mDownloadRequest;

    public void postDownload(int status, String url, ApiCallbackFiles apiFile) {
        this.apiFile = apiFile;
        // 开始下载了，但是任务没有完成，代表正在下载，那么暂停下载。
        if (mDownloadRequest != null && mDownloadRequest.isStarted() && !mDownloadRequest.isFinished()) {
            // 暂停下载。
            mDownloadRequest.cancel();
        } else if (mDownloadRequest == null || mDownloadRequest.isFinished()) {// 没有开始或者下载完成了，就重新下载。

            /**
             * 这里不传文件名称、不断点续传，则会从响应头中读取文件名自动命名，如果响应头中没有则会从url中截取。
             */
            // url 下载地址。
            // fileFolder 文件保存的文件夹。
            // isDeleteOld 发现文件已经存在是否要删除重新下载。
//            mDownloadRequest = NoHttp.createDownloadRequest(Constants.URL_DOWNLOADS[0], AppConfig.getInstance().APP_PATH_ROOT, true);
            /**
             * 如果使用断点续传的话，一定要指定文件名喔。
             */
            // url 下载地址。
            // fileFolder 保存的文件夹。
            // fileName 文件名。
            // isRange 是否断点续传下载。
            // isDeleteOld 如果发现存在同名文件，是否删除后重新下载，如果不删除，则直接下载成功。
            mDownloadRequest = NoHttp.createDownloadRequest(url, MyApplication.APP_PATH_ROOT, "nohttp.apk", true, true);
            // what 区分下载。
            // downloadRequest 下载请求对象。
            // downloadListener 下载监听。
            CallServer.getDownloadInstance().add(status, mDownloadRequest, downloadListener);
        }
    }

    /**
     * 下载监听
     */
    private DownloadListener downloadListener = new DownloadListener() {

        @Override
        public void onStart(int what, boolean isResume, long beforeLength, Headers headers, long allCount) {

        }

        @Override
        public void onDownloadError(int what, Exception exception) {
            Logger.e(exception);
            String message = context.getString(R.string.download_error);
            String messageContent;
            if (exception instanceof ServerError) {
                messageContent = context.getString(R.string.download_error_server);
            } else if (exception instanceof NetworkError) {
                messageContent = context.getString(R.string.download_error_network);
            } else if (exception instanceof StorageReadWriteError) {
                messageContent = context.getString(R.string.download_error_storage);
            } else if (exception instanceof StorageSpaceNotEnoughError) {
                messageContent = context.getString(R.string.download_error_space);
            } else if (exception instanceof TimeoutError) {
                messageContent = context.getString(R.string.download_error_timeout);
            } else if (exception instanceof UnKnownHostError) {
                messageContent = context.getString(R.string.download_error_un_know_host);
            } else if (exception instanceof URLError) {
                messageContent = context.getString(R.string.download_error_url);
            } else {
                messageContent = context.getString(R.string.download_error_un);
            }
            message = String.format(Locale.getDefault(), message, messageContent);
            apiFile.onApiError(what, messageContent);
        }

        @Override
        public void onProgress(int what, int progress, long fileCount) {
            apiFile.onApiProgress(what, progress);
        }

        @Override
        public void onFinish(int what, String filePath) {
            Logger.d("Download finish, file path: " + filePath);
        }

        @Override
        public void onCancel(int what) {

        }

    };

    /**
     * 删除文件
     */
    private void delete() {
        for (int i = 0; i < 4; i++) {
            File file = new File(MyApplication.APP_PATH_ROOT, "nohttp_list" + i + ".apk");
            IOUtils.delFileOrFolder(file);
        }
    }
}
