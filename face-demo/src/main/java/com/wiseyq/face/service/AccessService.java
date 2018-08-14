package com.wiseyq.face.service;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.cloud.sdk.http.HttpMethodName;

public abstract class AccessService {
    protected String serviceName = null;

    protected String region = null;

    protected String ak = null;

    protected String sk = null;

    public AccessService(String serviceName, String region, String ak, String sk) {
        this.region = region;
        this.serviceName = serviceName;
        this.ak = ak;
        this.sk = sk;
    }

    public abstract HttpResponse access(URL url, Map<String, String> header, InputStream content, Long contentLength,
            HttpMethodName httpMethod) throws Exception;

    public HttpResponse access(URL url, Map<String, String> header, HttpMethodName httpMethod) throws Exception {
        return this.access(url, header, null, 0l, httpMethod);
    }

    public HttpResponse access(URL url, InputStream content, Long contentLength, HttpMethodName httpMethod)
            throws Exception {
        return this.access(url, null, content, contentLength, httpMethod);
    }

    public HttpResponse access(URL url, HttpMethodName httpMethod) throws Exception {
        return this.access(url, null, null, 0l, httpMethod);
    }

    public abstract void close();
}
