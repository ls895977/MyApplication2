package com.lykj.mipinduobao.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lykj.mipinduobao.bean.CartListBean;

import com.lykj.mipinduobao.greendao.CartListBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cartListBeanDaoConfig;

    private final CartListBeanDao cartListBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cartListBeanDaoConfig = daoConfigMap.get(CartListBeanDao.class).clone();
        cartListBeanDaoConfig.initIdentityScope(type);

        cartListBeanDao = new CartListBeanDao(cartListBeanDaoConfig, this);

        registerDao(CartListBean.class, cartListBeanDao);
    }
    
    public void clear() {
        cartListBeanDaoConfig.clearIdentityScope();
    }

    public CartListBeanDao getCartListBeanDao() {
        return cartListBeanDao;
    }

}