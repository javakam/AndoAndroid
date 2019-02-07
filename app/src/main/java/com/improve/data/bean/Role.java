package com.improve.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;

/**
 * Created by javakam on 2018/7/6.
 */
@Entity
public class Role {
    @Id
    private String rid;

    @Index
    private String rname;

    @Generated(hash = 1537269817)
    public Role(String rid, String rname) {
        this.rid = rid;
        this.rname = rname;
    }

    @Generated(hash = 844947497)
    public Role() {
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
