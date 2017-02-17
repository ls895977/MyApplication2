package com.lykj.mipinduobao.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lykj.mipinduobao.bean.CartListBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * author：LiShan
 * Creation time：2017/1/22 0022
 */

public class DBManager {
    private final static String dbName = "test_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param cartList
     */
    public void insertUser(CartListBean cartList) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CartListBeanDao userDao = daoSession.getCartListBeanDao();
        userDao.insert(cartList);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<CartListBean> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CartListBeanDao userDao = daoSession.getCartListBeanDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(CartListBean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CartListBeanDao userDao = daoSession.getCartListBeanDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(CartListBean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CartListBeanDao userDao = daoSession.getCartListBeanDao();
        userDao.update(user);
    }

    /**
     * 查询用户列表
     */
    public List<CartListBean> queryShopingList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CartListBeanDao userDao = daoSession.getCartListBeanDao();
        QueryBuilder<CartListBean> qb = userDao.queryBuilder();
        List<CartListBean> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<CartListBean> queryUserList(int age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        CartListBeanDao userDao = daoSession.getCartListBeanDao();
        QueryBuilder<CartListBean> qb = userDao.queryBuilder();
        qb.where(CartListBeanDao.Properties.Id.gt(age));
        List<CartListBean> list = qb.list();
        return list;
    }

}
