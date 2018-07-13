package com.sq.domain.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by javakam on 2018/7/12 0012.
 */
@Entity
public class GreenFriend {
    @Id
    private String fid;
    private String uid;
    private String fname;

    @Generated(hash = 786243639)
    public GreenFriend(String fid, String uid, String fname) {
        this.fid = fid;
        this.uid = uid;
        this.fname = fname;
    }
    @Generated(hash = 78886025)
    public GreenFriend() {
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

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }


}
