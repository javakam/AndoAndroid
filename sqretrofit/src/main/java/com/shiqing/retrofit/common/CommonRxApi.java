package com.shiqing.retrofit.common;

import com.sq.domain.cnodc.AssetBook;
import com.sq.domain.cnodc.AssetCheck;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by javakam on 2018/7/2.
 */
public interface CommonRxApi {
    @GET("tz")
    Observable<AssetBook> getAssetBook();

    @GET("pd")
    Observable<AssetCheck> getAssetCheck();
}
