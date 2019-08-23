package com.improve.data.bean;

import com.improve.data.dao.DaoSession;
import com.improve.data.dao.GreenFriendDao;
import com.improve.data.dao.GreenUserDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by javakam on 2018/7/12 0012.
 */
@Entity
public class GreenUser {

    @Id
    private String uid;
    private String username;
    @ToMany(referencedJoinProperty = "fid")
    private List<GreenFriend> friends;

    public void setFriends(List<GreenFriend> friends) {
        this.friends = friends;
    }

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 204181598)

    private transient GreenUserDao myDao;

    @Generated(hash = 1397977567)
    public GreenUser(String uid, String username) {
        this.uid = uid;
        this.username = username;
    }

    @Generated(hash = 1678257977)
    public GreenUser() {
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 814546834)
    public List<GreenFriend> getFriends() {
        if (friends == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GreenFriendDao targetDao = daoSession.getGreenFriendDao();
            List<GreenFriend> friendsNew = targetDao._queryGreenUser_Friends(uid);
            synchronized (this) {
                if (friends == null) {
                    friends = friendsNew;
                }
            }
        }
        return friends;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1638260638)
    public synchronized void resetFriends() {
        friends = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1388707780)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGreenUserDao() : null;
    }

}
