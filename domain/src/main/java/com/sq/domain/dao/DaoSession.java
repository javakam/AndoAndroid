package com.sq.domain.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.sq.domain.bean.GreenFriend;
import com.sq.domain.bean.GreenRole;
import com.sq.domain.bean.GreenUser;
import com.sq.domain.bean.Role;
import com.sq.domain.bean.User;
import com.sq.domain.cnodc.CocBookBean;
import com.sq.domain.cnodc.CocTaskHeaders;
import com.sq.domain.cnodc.CocTaskLines;

import com.sq.domain.dao.GreenFriendDao;
import com.sq.domain.dao.GreenRoleDao;
import com.sq.domain.dao.GreenUserDao;
import com.sq.domain.dao.RoleDao;
import com.sq.domain.dao.UserDao;
import com.sq.domain.dao.CocBookBeanDao;
import com.sq.domain.dao.CocTaskHeadersDao;
import com.sq.domain.dao.CocTaskLinesDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig greenFriendDaoConfig;
    private final DaoConfig greenRoleDaoConfig;
    private final DaoConfig greenUserDaoConfig;
    private final DaoConfig roleDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig cocBookBeanDaoConfig;
    private final DaoConfig cocTaskHeadersDaoConfig;
    private final DaoConfig cocTaskLinesDaoConfig;

    private final GreenFriendDao greenFriendDao;
    private final GreenRoleDao greenRoleDao;
    private final GreenUserDao greenUserDao;
    private final RoleDao roleDao;
    private final UserDao userDao;
    private final CocBookBeanDao cocBookBeanDao;
    private final CocTaskHeadersDao cocTaskHeadersDao;
    private final CocTaskLinesDao cocTaskLinesDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        greenFriendDaoConfig = daoConfigMap.get(GreenFriendDao.class).clone();
        greenFriendDaoConfig.initIdentityScope(type);

        greenRoleDaoConfig = daoConfigMap.get(GreenRoleDao.class).clone();
        greenRoleDaoConfig.initIdentityScope(type);

        greenUserDaoConfig = daoConfigMap.get(GreenUserDao.class).clone();
        greenUserDaoConfig.initIdentityScope(type);

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

        greenFriendDao = new GreenFriendDao(greenFriendDaoConfig, this);
        greenRoleDao = new GreenRoleDao(greenRoleDaoConfig, this);
        greenUserDao = new GreenUserDao(greenUserDaoConfig, this);
        roleDao = new RoleDao(roleDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        cocBookBeanDao = new CocBookBeanDao(cocBookBeanDaoConfig, this);
        cocTaskHeadersDao = new CocTaskHeadersDao(cocTaskHeadersDaoConfig, this);
        cocTaskLinesDao = new CocTaskLinesDao(cocTaskLinesDaoConfig, this);

        registerDao(GreenFriend.class, greenFriendDao);
        registerDao(GreenRole.class, greenRoleDao);
        registerDao(GreenUser.class, greenUserDao);
        registerDao(Role.class, roleDao);
        registerDao(User.class, userDao);
        registerDao(CocBookBean.class, cocBookBeanDao);
        registerDao(CocTaskHeaders.class, cocTaskHeadersDao);
        registerDao(CocTaskLines.class, cocTaskLinesDao);
    }
    
    public void clear() {
        greenFriendDaoConfig.clearIdentityScope();
        greenRoleDaoConfig.clearIdentityScope();
        greenUserDaoConfig.clearIdentityScope();
        roleDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        cocBookBeanDaoConfig.clearIdentityScope();
        cocTaskHeadersDaoConfig.clearIdentityScope();
        cocTaskLinesDaoConfig.clearIdentityScope();
    }

    public GreenFriendDao getGreenFriendDao() {
        return greenFriendDao;
    }

    public GreenRoleDao getGreenRoleDao() {
        return greenRoleDao;
    }

    public GreenUserDao getGreenUserDao() {
        return greenUserDao;
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
