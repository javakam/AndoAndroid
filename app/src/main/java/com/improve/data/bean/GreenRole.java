package com.improve.data.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by javakam on 2018/7/12 0012.
 */
@Entity
public class GreenRole {
    @Id
    private String rid;

    @Generated(hash = 1033863653)
    public GreenRole(String rid) {
        this.rid = rid;
    }

    @Generated(hash = 1512413082)
    public GreenRole() {
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
