package com.sq.domain.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by javakam on 2018/7/12 0012.
 */
@Entity
public class GreenFriend {
    @Id(autoincrement = true)
    private Long g_fid;
    @Unique
    private String fid;
    private String uid;
    @Generated(hash = 2013492950)
    public GreenFriend(Long g_fid, String fid, String uid) {
        this.g_fid = g_fid;
        this.fid = fid;
        this.uid = uid;
    }
    @Generated(hash = 78886025)
    public GreenFriend() {
    }
    public Long getG_fid() {
        return this.g_fid;
    }
    public void setG_fid(Long g_fid) {
        this.g_fid = g_fid;
    }
    public String getFid() {
        return this.fid;
    }
    public void setFid(String fid) {
        this.fid = fid;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }


}
