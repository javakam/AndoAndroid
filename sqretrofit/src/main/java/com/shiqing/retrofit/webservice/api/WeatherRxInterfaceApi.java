package com.shiqing.retrofit.webservice.api;

import com.shiqing.retrofit.webservice.request.RequestEnvelope;
import com.shiqing.retrofit.webservice.response.ResponseEnvelope;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 接口请求
 * Created by SmileXie on 16/7/15.
 */
public interface WeatherRxInterfaceApi {

    /**
     * 请求的Action，类似于方法名
     *
     * @param requestEnvelope
     * @return
     */
    @Headers({"Content-Type: text/xml;charset=UTF-8", "SOAPAction: http://WebXml.com.cn/getWeatherbyCityName"})
    @POST("WeatherWebService.asmx")
    Observable<ResponseEnvelope> getWeatherbyCityNameWithRx(@Body RequestEnvelope requestEnvelope);

}
