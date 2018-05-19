package com.cily.utils.app.rx.okhttp;

import com.cily.utils.base.StrUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * user: cily
 * time: 2016/11/26
 * desc: 添加请求头拦截器
 */

public class HeaderInterceptor implements Interceptor {
    private Map<String, String> headers;

    public HeaderInterceptor() {}

    public HeaderInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder build = chain.request()
                .newBuilder();

        if (headers != null && headers.size() > 0){
            for (String s : headers.keySet()){
                if (StrUtils.isEmpty(s) || StrUtils.isEmpty(headers.get(s))){

                }else {
                    build.addHeader(s, headers.get(s));
                }
            }
        }

        return chain.proceed(build.build());
    }
}
