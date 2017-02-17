package com.lykj.mipinduobao.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 网络判断及 其它工具类 2016年1月17日下午1:02:37
 */
public class IntenetTools {
    public static IntenetTools intent;
    private StringBuffer sb;

    public IntenetTools(Context context) {
        this.context = context;
        intent = this;
    }

    /*
     * 判断网络是否可用
     */
    public boolean getIntentStatic() {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null || !info.isAvailable()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 通过网址得到字符串
     */
    public String getDataformNet(String urlpath) {
        //HttpUrlconnction使用
        try {
            urlpath = urlpath.replace(" ", "");
            //1、得到url对象   参数：网址
            URL url = new URL(urlpath);
            //2、得到urlconnection的链接对象
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //3、对连接进行设置
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            //默认情况是get请求   设置请求方法
            conn.setRequestMethod("GET");
            //得到相应码
            int code = conn.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                //通过连接得到一个输入流
                InputStream in = conn.getInputStream();
//				byte[] bfu = new byte[1024];
//				StringBuffer sb = new StringBuffer();
//				int len;
//				while((len = in.read(bfu))!=-1){
//					String str = new String(bfu, 0, len);
//					sb.append(str);
//				}
//				Log.e("aa", sb.toString());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;
                sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }


            return sb.toString();
        } catch (Exception e) {
            return "0";
        }


    }

    /*
    * Function  :   发送Post请求到服务器
    * Param     :   params请求体内容，encode编码格式
    */
    public static String submitPostData(String strUrlPath, Map<String, String> params, String encode) {

        byte[] data = getRequestData(params, encode).toString().getBytes();//获得请求体
        try {
            //String urlPath = "http://192.168.1.9:80/JJKSms/RecSms.php";
            URL url = new URL(strUrlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);     //设置连接超时时间
            httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);
            int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = httpURLConnection.getInputStream();
                return dealResponseResult(inptStream);                     //处理服务器的响应结果
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return "err: " + e.getMessage().toString();
        }
        return "-1";
    }

    /*
    * Function  :   发送Post请求到服务器
    * Param     :   params请求体内容，encode编码格式
    */
    public static String submitPostData(String strUrlPath, Map<String, String> params, Map<String, String> hader, String encode) {

        byte[] data = getRequestData(params, encode).toString().getBytes();//获得请求体
        try {
            //String urlPath = "http://192.168.1.9:80/JJKSms/RecSms.php";
            URL url = new URL(strUrlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);     //设置连接超时时间
            httpURLConnection.setDoInput(true);                  //打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);                 //打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");     //设置以Post方式提交数据
            Iterator iter = hader.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                httpURLConnection.addRequestProperty(key, val);
            }
            httpURLConnection.setUseCaches(false);               //使用Post方式不能使用缓存
            //设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);
            int response = httpURLConnection.getResponseCode();            //获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = httpURLConnection.getInputStream();
                return dealResponseResult(inptStream);                     //处理服务器的响应结果
            }
        } catch (IOException e) {
            //e.printStackTrace();
            return "err: " + e.getMessage().toString();
        }
        return "-1";
    }

    /*
     * Function  :   封装请求体信息
     * Param     :   params请求体内容，encode编码格式
     */
    public static StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }

    /*
     * Function  :   处理服务器的响应结果（将输入流转化成字符串）
     * Param     :   inputStream服务器的响应输入流
     */
    public static String dealResponseResult(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

    /**
     * 获取cookie 并保存
     */
    public static String getSignCookie(Context context1, String url) {
        context = context1;
        BufferedReader in = null;
        url = url.trim();
        HttpGet request = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response;
        try {
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 获得cookie
                getCookie(httpClient);
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }
                in.close();
                return sb.toString();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "网络错误！";
        }
        return "错误！";
    }

    private static void getCookie(HttpClient httpClient) {
        List<Cookie> cookies = ((AbstractHttpClient) httpClient).getCookieStore().getCookies();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            String cookieName = cookie.getName();
            String cookieValue = cookie.getValue();
            if (!TextUtils.isEmpty(cookieName) && !TextUtils.isEmpty(cookieValue)) {
                sb.append(cookieName + "=");
                sb.append(cookieValue + ";");
            }
        }
        saveCookie(sb.toString() + "isapp=1");
    }

    /**
     * 保存获取到的cookie信息
     */
    static Context context;

    public static void saveCookie(String cookie) {
        SharedPreferences shar = context.getSharedPreferences("test", Activity.MODE_PRIVATE);
        SharedPreferences.Editor edit = shar.edit();
        edit.putString("kookie", cookie);
        edit.commit();
    }
}
