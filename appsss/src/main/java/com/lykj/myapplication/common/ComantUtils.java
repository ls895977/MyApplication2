package com.lykj.myapplication.common;

/**
 * Created by User on 2016/7/26.
 */
public class ComantUtils {
    public static String apiHttpUrl = "http://bosheng.langyadt.com/index.php/api/";//测试服务器地址
    public static String apiImageUrl = "http://bosheng.langyadt.com/";//测试服务器图片地址前半段
    public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;
    public static final int CHATTYPE_CHATROOM = 3;
    public static final int CHAT_PHOTO = 10;//照片
    public static final int CHAT_PHOTOGRAPH = 11;//拍照
    public static final int CHAT_RED = 12;//红包
    public static final int CHAT_BUSINESS_CARD = 13;//名片
    public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
    public static final String GROUP_USERNAME = "item_groups";
    public static final String CHAT_ROOM = "item_chatroom";
    public static final String ACCOUNT_REMOVED = "account_removed";
    public static final String ACCOUNT_CONFLICT = "conflict";
    public static final String CHAT_ROBOT = "item_robots";
    public static final String MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype";
    public static final String ACTION_GROUP_CHANAGED = "action_group_changed";
    public static final String ACTION_CONTACT_CHANAGED = "action_contact_changed";
    public static int ACTION_CONTACT_INDEXT = 3;
    public static boolean ACTION_CONTACT_STATIC = false;
}
