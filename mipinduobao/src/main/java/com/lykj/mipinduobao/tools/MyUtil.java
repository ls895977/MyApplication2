package com.lykj.mipinduobao.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 工具
 *
 * @author
 * @time 2014年12月24日 下午1:43:34
 */
public class MyUtil {
    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        if (str == null)
            return false;
        return Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$").matcher(str).matches(); // 验证手机号
    }

    /**
     * 是否是数字
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isNumber(String str) {
        if (str == null)
            return false;
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }

    /**
     * 是否是中文
     *
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        if (str == null)
            return false;
        return Pattern.compile("^[\u4e00-\u9fa5]+$").matcher(str).matches();
    }

    /**
     * 是否是IP地址
     *
     * @param str
     * @return
     */
    public static boolean isIpAddress(String str) {
        if (str == null)
            return false;
        return Pattern.compile("(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)){3}").matcher(str).matches();
    }

    /**
     * 是否是身份证
     *
     * @param str
     * @return
     */
    public static boolean isIdentity(String str) {
        if (isEmpty(str))
            return false;
        if (str.length() == 15)
            return Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$").matcher(str).matches();
        if (str.length() == 18)
            return Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$").matcher(str).matches();
        return false;
    }

    /**
     * 电话号码验证
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        if (str == null)
            return false;
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        str = str.replaceAll("-", "");
        // p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        if (str.length() == 11) {
            p1 = Pattern.compile("^[0][1-9]{2,3}[0-9]{5,10}$"); // 验证带区号的
            m = p1.matcher(str);
            b = m.matches();
        } else if (str.length() <= 9) {
            p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
            m = p2.matcher(str);
            b = m.matches();
        }
        if (!b)
            return isMobile(str);
        return b;
    }

    /**
     * 邮箱验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isEmail(String str) {
        if (str == null)
            return false;
        return Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$")
                .matcher(str).matches();
    }

    public static boolean isNoEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(String str) {
        if (null == str)
            return true;
        if (str.length() == 0)
            return true;
        if (str.trim().length() == 0)
            return true;
        if (str.indexOf("null") == 0)
            return true;
        return false;
    }

    public static boolean isNoEmpty(List<?> datas) {
        return !isEmpty(datas);
    }

    public static boolean isEmpty(List<?> datas) {
        if (datas == null)
            return true;
        if (datas.size() == 0)
            return true;
        return false;
    }

    /**
     * 给View 添加按下监听（防止重复点击）
     */
    public static void addViewClick(View view, final View.OnClickListener listener) {
        if (view != null && listener != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    v.setEnabled(false);
                    v.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            v.setEnabled(true);
                        }
                    }, 500);
                    listener.onClick(v);
                }
            });
        }
    }

    /**
     * 去掉多余的0
     *
     * @param str
     * @return
     */
    public static String removeNumberZero(String str) {
        if (isEmpty(str)) {
            return "0";
        }
        if (str.indexOf(".") > 0) {
            str = str.replaceAll("0+?$", "");// 去掉多余的0
            str = str.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return str;
    }

    /**
     * 把字体结果dimen转化成原sp值
     *
     * @return
     */
    public static float floatToSpDimension(float value, Context context) {
        return value / context.getResources().getDisplayMetrics().scaledDensity;
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T getView(View v, int resId) {
        return (T) v.findViewById(resId);
    }

    /**
     * 获取当前时间Date
     *
     * @return 现在时间(Now)
     */
    public static String getNowTime() {
        Date d = new Date(System.currentTimeMillis());
//        String type = "yyyy-MM-dd HH:mm";
        String type = "HH:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(type, Locale.CHINA);
        return formatter.format(d);
    }

    /**
     * 按输入类型，返回当前时间
     * @param type yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getLocationTime(String type) {
        Date d = new Date(System.currentTimeMillis());
//        String type = "yyyy-MM-dd HH:mm";
        if (type == null) type = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(type, Locale.CHINA);
        return formatter.format(d);
    }

    /**
     * 获取当前时间Date
     */
    public static String getDateTime(long ltime) {
        return getDateTime(ltime, null);
    }

    /**
     * 获取当前时间Date
     */
    public static String getDateTime(long ltime, String type) {
        if ((ltime + "").length() == 10) ltime = ltime * 1000L;
        if (type == null) type = "yyyy-MM-dd HH:mm:ss";
        Date d = new Date(ltime);
        SimpleDateFormat formatter = new SimpleDateFormat(type, Locale.CHINA);
        return formatter.format(d);
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取控件的高度，如果获取的高度为0，则重新计算尺寸后再返回高度
     *
     * @param view
     * @return
     */
    public static int getViewMeasuredHeight(View view) {
        // int height = view.getMeasuredHeight();
        // if(0 < height){
        // return height;
        // }
        calcViewMeasure(view);
        return view.getMeasuredHeight();
    }

    /**
     * 获取控件的宽度，如果获取的宽度为0，则重新计算尺寸后再返回宽度
     *
     * @param view
     * @return
     */
    public static int getViewMeasuredWidth(View view) {
        // int width = view.getMeasuredWidth();
        // if(0 < width){
        // return width;
        // }
        calcViewMeasure(view);
        return view.getMeasuredWidth();
    }

    /**
     * 测量控件的尺寸
     *
     * @param view
     */
    public static void calcViewMeasure(View view) {
        // int width = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        // int height = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        // view.measure(width,height);

        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
    }

    /**
     * 返回当前程序版本信息
     */
    public static PackageInfo getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            Log.e("message","VersionInfo|Exception:" + e);
        }
        return null;
    }

    /**
     * 检测该包名所对应的应用是否存在
     *
     * @param context
     * @param packageName
     * @return
     */
//    public static boolean checkPackage(Context context, String packageName) {
//
//        if (TextUtils.isEmpty(packageName))
//            return false;
//        try {
//            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_ACTIVITIES);
//            return true;
//        } catch (NameNotFoundException e) {
//            return false;
//        }
//    }


    private static long lastTime = 0;

    /**
     * 是否是快速点击
     *
     * @return
     */
    public static boolean isFastClick() {
        long curTime = System.currentTimeMillis();
        if (curTime - lastTime < 500)
            return true;
        lastTime = curTime;
        return false;
    }

    /**
     * 是否是车牌号
     *
     * @param str
     * @return
     */
    public static boolean isCarNumber(String str) {
        if (isNoEmpty(str))
            return Pattern.compile("^[\u4e00-\u9fa5|A-Z]{1}[A-Z]{1}[A-Z_0-9]{5}$").matcher(str).matches();
        return false;
    }

    public static final String[] weeks = {};

    /**
     * 获取周几
     *
     * @param week
     * @return
     */
    public static String getWeekName(int week) {
        switch (week) {
            case Calendar.SUNDAY:
                return "周日";
            case Calendar.MONDAY:
                return "周一";
            case Calendar.TUESDAY:
                return "周二";
            case Calendar.WEDNESDAY:
                return "周三";
            case Calendar.THURSDAY:
                return "周四";
            case Calendar.FRIDAY:
                return "周五";
            case Calendar.SATURDAY:
                return "周六";
        }
        return null;
    }

    /**
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 获取当前时间戳
     */
    public static long getTimeStamp() {
        long timeStamp = System.currentTimeMillis()/1000;  //获取当前时间戳
        return timeStamp;
    }

    /**
     * 获取当前是。上午下午
     *
     * @return
     */
    public static String getDayStatic() {
        String str = "";
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        int apm = mCalendar.get(Calendar.AM_PM);
        if (apm == 0) {
            str = "上午 ";
        } else if (apm == 1) {
            str = "下午 ";
        } else {
            str = "晚上 ";
        }
        return str;
    }

    /**
     * @param dateTime 传入的时间戳
     * @param year     格式分格
     * @param month
     * @param day
     * @return
     */
    public static String getDate(long dateTime, String year, String month, String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + year + "MM" + month + "dd" + day);
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(dateTime * 1000))));   // 时间戳转换成时间
        return sd;
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(Context context , String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        try{
            date = sdf.parse(time);
        } catch(ParseException e) {
            Log.e("message","请输入正确的日期");
        }
        return date.getTime();
    }

    /*
    *计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
    * 根据差值返回多长之间前或多长时间后
    * */
    public static String getDistanceTime(long  time1,long time2 ) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff ;
        String flag;
        if(time1<time2) {
            diff = time2 - time1;
            flag="前";
        } else {
            diff = time1 - time2;
            flag="后";
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        if(day!=0)return day+"天"+flag;
        if(hour!=0)return hour+"小时"+flag;
        if(min!=0)return min+"分钟"+flag;

        return "刚刚";
    }


}
