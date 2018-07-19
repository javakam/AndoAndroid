package com.ishiqing.modules.widgets.smarttable;

import android.graphics.Color;

import com.bin.david.form.annotation.SmartColumn;
import com.bin.david.form.annotation.SmartTable;

/**
 * 有无差异就说的是房间是不是一样,数量是不是一样,是不是同一个东西这些。
 * 盘点到的情况下,才区分有无差异
 * "diff": "0",   // 1 有差异  0 无差异
 * "status": "1"   // 1盘点到，-1未盘到，0多盘 , 2未盘点-default
 */
@SmartTable(name = "资产详情")
public class ResultBean {
    private int color = Color.WHITE;

    private String id;
    @SmartColumn(id = 0, name = "任务ID")
    private String taskId;
    private String taskNum;
    @SmartColumn(id = 1, name = "房间")
    private String zcfdd;
    @SmartColumn(id = 2, name = "发卡信息")
    private String anln1;
    @SmartColumn(id = 3, name = "有无差异")
    private String diff;
    @SmartColumn(id = 4, name = "盘点状态")
    private String status = "2";

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
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

    public String getAnln1() {
        return anln1;
    }

    public void setAnln1(String anln1) {
        this.anln1 = anln1;
    }

    public String getDiff() {
        return diff;
    }

    public void setDiff(String diff) {
        this.diff = diff;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}