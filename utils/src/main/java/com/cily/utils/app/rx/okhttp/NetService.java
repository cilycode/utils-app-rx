package com.cily.utils.app.rx.okhttp;


import com.cily.utils.app.rx.bean.AppVersionBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * user:cily
 * time:2017/2/20
 * desc:需自行实现
 */

public interface NetService {

    @GET
    Observable<BaseResponseBean<AppVersionBean>> getAppUpdate(@Url String url, @HeaderMap Map<String, String> map_header, @QueryMap Map<String, String> map);
//
//    @POST
//    Observable<BaseResponseBean> post(@Url String url, @HeaderMap Map<String, String> map_header, @QueryMap Map<String, String> map);
//
//    @POST
//    Observable<BaseResponseBean> postForm(@Url String url, @HeaderMap Map<String, String> map_header, @QueryMap Map<String, String> map, @Part("image") MultipartBody body);
}
