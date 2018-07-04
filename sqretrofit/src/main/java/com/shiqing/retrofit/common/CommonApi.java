package com.shiqing.retrofit.common;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by javakam on 2018/7/2.
 */
public interface CommonApi {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<CommonEntity> getCall();
}
