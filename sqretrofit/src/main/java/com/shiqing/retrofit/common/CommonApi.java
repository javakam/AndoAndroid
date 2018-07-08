package com.shiqing.retrofit.common;

import com.sq.domain.cnodc.AssetBook;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by javakam on 2018/7/2.
 */
public interface CommonApi {
    @GET("tz")
    Call<AssetBook> getAssetBook();

    @GET("pd")
    Call<AssetBook> getAssetCheck();
}
