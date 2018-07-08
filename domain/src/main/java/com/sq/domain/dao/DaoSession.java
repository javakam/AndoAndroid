package com.sq.domain.dao;

import com.sq.domain.bean.Role;
import com.sq.domain.bean.User;
import com.sq.domain.cnodc.CocBookBean;
import com.sq.domain.cnodc.CocTaskHeaders;
import com.sq.domain.cnodc.CocTaskLines;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig roleDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig cocBookBeanDaoConfig;
    private final DaoConfig cocTaskHeadersDaoConfig;
    private final DaoConfig cocTaskLinesDaoConfig;

    private final RoleDao roleDao;
    private final UserDao userDao;
    private final CocBookBeanDao cocBookBeanDao;
    private final CocTaskHeadersDao cocTaskHeadersDao;
    private final CocTaskLinesDao cocTaskLinesDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        roleDaoConfig = daoConfigMap.get(RoleDao.class).clone();
        roleDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        cocBookBeanDaoConfig = daoConfigMap.get(CocBookBeanDao.class).clone();
        cocBookBeanDaoConfig.initIdentityScope(type);

        cocTaskHeadersDaoConfig = daoConfigMap.get(CocTaskHeadersDao.class).clone();
        cocTaskHeadersDaoConfig.initIdentityScope(type);

        cocTaskLinesDaoConfig = daoConfigMap.get(CocTaskLinesDao.class).clone();
        cocTaskLinesDaoConfig.initIdentityScope(type);

        roleDao = new RoleDao(roleDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        cocBookBeanDao = new CocBookBeanDao(cocBookBeanDaoConfig, this);
        cocTaskHeadersDao = new CocTaskHeadersDao(cocTaskHeadersDaoConfig, this);
        cocTaskLinesDao = new CocTaskLinesDao(cocTaskLinesDaoConfig, this);

        registerDao(Role.class, roleDao);
        registerDao(User.class, userDao);
        registerDao(CocBookBean.class, cocBookBeanDao);
        registerDao(CocTaskHeaders.class, cocTaskHeadersDao);
        registerDao(CocTaskLines.class, cocTaskLinesDao);
    }
    
    public void clear() {
        roleDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        cocBookBeanDaoConfig.clearIdentityScope();
        cocTaskHeadersDaoConfig.clearIdentityScope();
        cocTaskLinesDaoConfig.clearIdentityScope();
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CocBookBeanDao getCocBookBeanDao() {
        return cocBookBeanDao;
    }

    public CocTaskHeadersDao getCocTaskHeadersDao() {
        return cocTaskHeadersDao;
    }

    public CocTaskLinesDao getCocTaskLinesDao() {
        return cocTaskLinesDao;
    }

}
