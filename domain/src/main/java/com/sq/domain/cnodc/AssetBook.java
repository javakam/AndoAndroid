package com.sq.domain.cnodc;

import java.util.List;

/**
 * 台账集合
 * <p>
 * Created by javakam on 2018/7/8.
 */
public class AssetBook {
    private List<CocBookBean> data;

    public List<CocBookBean> getData() {
        return data;
    }

    public void setData(List<CocBookBean> data) {
        this.data = data;
    }
}
