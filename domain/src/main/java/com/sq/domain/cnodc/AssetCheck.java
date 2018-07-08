package com.sq.domain.cnodc;

import java.util.List;

/**
 * 盘点任务单集合
 * <p>
 * Created by javakam on 2018/7/8.
 */
public class AssetCheck {
    private List<CocTaskLines> lineList;
    private List<CocTaskHeaders> headerList;

    public List<CocTaskLines> getLineList() {
        return lineList;
    }

    public void setLineList(List<CocTaskLines> lineList) {
        this.lineList = lineList;
    }

    public List<CocTaskHeaders> getHeaderList() {
        return headerList;
    }

    public void setHeaderList(List<CocTaskHeaders> headerList) {
        this.headerList = headerList;
    }
}
