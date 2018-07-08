package com.sq.domain.cnodc;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;

/**
 * 盘点任务单 头信息
 */
@Entity
public class CocTaskHeaders {
    @Id
    private String id;

    private String taskNum;//盘点任务单号

    private String kostl;//所属部门id

    private String txt50;//资产名称

    private String zcharItem12; //资产类别

    private String lease;//是否租赁设备

    private String zsczt;//是否删除

    private String status;//(预留)单据状态

    private Date startDate; //任务有效开始日期

    private Date endDate; //任务有效截止日期

    private String createdBy;

    private Date creationDate;

    private String lastUpdatedBy;

    private Date lastUpdateDate;

    @Generated(hash = 622943674)
    public CocTaskHeaders(String id, String taskNum, String kostl, String txt50,
                          String zcharItem12, String lease, String zsczt, String status,
                          Date startDate, Date endDate, String createdBy, Date creationDate,
                          String lastUpdatedBy, Date lastUpdateDate) {
        this.id = id;
        this.taskNum = taskNum;
        this.kostl = kostl;
        this.txt50 = txt50;
        this.zcharItem12 = zcharItem12;
        this.lease = lease;
        this.zsczt = zsczt;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdateDate = lastUpdateDate;
    }

    @Generated(hash = 1454770968)
    public CocTaskHeaders() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getKostl() {
        return kostl;
    }

    public void setKostl(String kostl) {
        this.kostl = kostl;
    }

    public String getTxt50() {
        return txt50;
    }

    public void setTxt50(String txt50) {
        this.txt50 = txt50;
    }

    public String getZcharItem12() {
        return zcharItem12;
    }

    public void setZcharItem12(String zcharItem12) {
        this.zcharItem12 = zcharItem12;
    }

    public String getLease() {
        return lease;
    }

    public void setLease(String lease) {
        this.lease = lease;
    }

    public String getZsczt() {
        return zsczt;
    }

    public void setZsczt(String zsczt) {
        this.zsczt = zsczt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}