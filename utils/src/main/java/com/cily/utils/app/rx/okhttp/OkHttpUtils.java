package com.cily.utils.app.rx.okhttp;


import com.cily.utils.app.Init;
import com.cily.utils.base.StrUtils;
import com.cily.utils.base.log.LogType;
import com.cily.utils.log.L;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * user: cily
 * time: 2016/11/22
 * desc:
 */

public class OkHttpUtils {
    private final String TAG = this.getClass().getSimpleName();
    private static OkHttpUtils mUtils;
    private static OkHttpClient mOkHttpClient;
    private final static long DEFAULT_TIME_OUT = 45;
    private static long timeOutConn = DEFAULT_TIME_OUT;
    private static long timeOutWrite = DEFAULT_TIME_OUT;
    private static long timeOutRead = DEFAULT_TIME_OUT;
    private static List<Interceptor> interceptors;

    public static void addInterceptor(Interceptor it, boolean resetBefore){
        if (interceptors == null){
            interceptors = new ArrayList<>();
        }
        if (resetBefore){
            interceptors.clear();
        }

        if (it != null){
            interceptors.add(it);
        }
    }

    public static HttpLoggingInterceptor getLogInterceptor(final int logType, HttpLoggingInterceptor.Level level){
        HttpLoggingInterceptor mLogInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (logType > LogType.INFO){
                    L.d(OkHttpUtils.class.getSimpleName(), message == null ? "" : message);
                }else if (logType > LogType.WARN && logType <= LogType.INFO){
                    L.i(OkHttpUtils.class.getSimpleName(), message == null ? "" : message);
                }else {
                    L.w(OkHttpUtils.class.getSimpleName(), message == null ? "" : message);
                }
            }
        });
        mLogInterceptor.setLevel(level);
        return mLogInterceptor;
    }

    private OkHttpUtils() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(timeOutConn, TimeUnit.SECONDS)
                .writeTimeout(timeOutWrite, TimeUnit.SECONDS)
                .readTimeout(timeOutRead, TimeUnit.SECONDS);

        if (interceptors != null && interceptors.size() > 0){
            for (Interceptor it : interceptors){
                builder.addInterceptor(it);
            }
        }
        mOkHttpClient = builder.build();
    }

    public static OkHttpUtils getInstance() {
        if (mUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (mUtils == null) {
                    mUtils = new OkHttpUtils();
                }
            }
        }
        return mUtils;
    }

    public static void setTimeOutConn(long timeOutConn) {
        OkHttpUtils.timeOutConn = timeOutConn;
    }

    public static void setTimeOutWrite(long timeOutWrite) {
        OkHttpUtils.timeOutWrite = timeOutWrite;
    }

    public static void setTimeOutRead(long timeOutRead) {
        OkHttpUtils.timeOutRead = timeOutRead;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}