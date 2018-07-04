package com.shiqing.retrofit.webservice.response;


import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * 用户角色返回
 * Created by SmileXie on 16/7/15.
 */

@Root(name = "getWeatherbyCityNameResponse")
@Namespace(reference = "http://WebXml.com.cn/")
public class WeatherResponseModel {

    @ElementList(name = "getWeatherbyCityNameResult")
    public List<String> result;

}
