package com.sq.domain.cnodc;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 盘点任务单 详情
 */
@Entity
public class CocTaskLines {

    @Id
    private String id;

    private String taskId; //资产盘点头表ID

    private String taskNum; //盘点任务单号

    private String zcfdd; //房间

    private String position; //位置

    private String zcharItem12; //资产类别

    private String txa50; //规格型号

    private String zccbh; //序列号

    private String zbgr; //保管人

    private String departmentKostl;//所属部门编码

    private String departmentName; //所属部门名称

    private String lease; //是否租赁设备

    private String xstil; //资产停用

    private String anln1; //资产编号

    private String txt50; //资产名称

    private String rrf; //备用字段

    @Generated(hash = 2078234142)
    public CocTaskLines(String id, String taskId, String taskNum, String zcfdd,
                        String position, String zcharItem12, String txa50, String zccbh,
                        String zbgr, String departmentKostl, String departmentName,
                        String lease, String xstil, String anln1, String txt50, String rrf) {
        this.id = id;
        this.taskId = taskId;
        this.taskNum = taskNum;
        this.zcfdd = zcfdd;
        this.position = position;
        this.zcharItem12 = zcharItem12;
        this.txa50 = txa50;
        this.zccbh = zccbh;
        this.zbgr = zbgr;
        this.departmentKostl = departmentKostl;
        this.departmentName = departmentName;
        this.lease = lease;
        this.xstil = xstil;
        this.anln1 = anln1;
        this.txt50 = txt50;
        this.rrf = rrf;
    }

    @Generated(hash = 1259069479)
    public CocTaskLines() {
    }

    @Override
    public String toString() {
        return "CocTaskLines{" +
                "id='" + id + '\'' +
                ", taskId='" + taskId + '\'' +
                ", taskNum='" + taskNum + '\'' +
                ", zcfdd='" + zcfdd + '\'' +
                ", position='" + position + '\'' +
                ", zcharItem12='" + zcharItem12 + '\'' +
                ", txa50='" + txa50 + '\'' +
                ", zccbh='" + zccbh + '\'' +
                ", zbgr='" + zbgr + '\'' +
                ", departmentKostl='" + departmentKostl + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", lease='" + lease + '\'' +
                ", xstil='" + xstil + '\'' +
                ", anln1='" + anln1 + '\'' +
                ", txt50='" + txt50 + '\'' +
                ", rrf='" + rrf + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getZcfdd() {
        return zcfdd;
    }

    public void setZcfdd(String zcfdd) {
        this.zcfdd = zcfdd;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getZcharItem12() {
        return zcharItem12;
    }

    public void setZcharItem12(String zcharItem12) {
        this.zcharItem12 = zcharItem12;
    }

    public String getTxa50() {
        return txa50;
    }

    public void setTxa50(String txa50) {
        this.txa50 = txa50;
    }

    public String getZccbh() {
        return zccbh;
    }

    public void setZccbh(String zccbh) {
        this.zccbh = zccbh;
    }

    public String getZbgr() {
        return zbgr;
    }

    public void setZbgr(String zbgr) {
        this.zbgr = zbgr;
    }

    public String getDepartmentKostl() {
        return departmentKostl;
    }

    public void setDepartmentKostl(String departmentKostl) {
        this.departmentKostl = departmentKostl;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLease() {
        return lease;
    }

    public void setLease(String lease) {
        this.lease = lease;
    }

    public String getXstil() {
        return xstil;
    }

    public void setXstil(String xstil) {
        this.xstil = xstil;
    }

    public String getAnln1() {
        return anln1;
    }

    public void setAnln1(String anln1) {
        this.anln1 = anln1;
    }

    public String getTxt50() {
        return txt50;
    }

    public void setTxt50(String txt50) {
        this.txt50 = txt50;
    }

    public String getRrf() {
        return rrf;
    }

    public void setRrf(String rrf) {
        this.rrf = rrf;
    }
}