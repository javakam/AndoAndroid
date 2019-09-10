package com.improve.modules.webservice.retrofitsoapsample.webservice.request;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by javakam on 2018/6/4.
 */
@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"),
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/encoding/", prefix = "enc"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv")
})
public class SimpleRequest {
    @Element(name = "getWeatherbyCityName", required = false)
    public RequestModel getWeatherbyCityName;

    @Attribute(name = "xmlns")
    public String cityNameAttribute;

    @Element(name = "theCityName", required = false)
    public String theCityName;
}
