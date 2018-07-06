package com.sq.domain.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

/**
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2017/0703/8144.html
 * <p>
 * 作者：anye0803
 * 链接：http://www.jianshu.com/p/4986100eff90
 * 來源：简书
 */
@Entity
public class User {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tempUsageCount=" + tempUsageCount +
                '}';
    }

    @Id
    private Long id;
    private String name;
    @Transient
    private int tempUsageCount; // not persisted  

    @Generated(hash = 873297011)
    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}