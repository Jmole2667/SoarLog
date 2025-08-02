package com.soarlog.app.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0014\u001a\n \u0011*\u0004\u0018\u00010\u00150\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\n\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006\u0019"}, d2 = {"Lcom/soarlog/app/network/RetrofitInstance;", "", "()V", "BASE_URL", "", "api", "Lcom/soarlog/app/network/OgnApiService;", "getApi", "()Lcom/soarlog/app/network/OgnApiService;", "api$delegate", "Lkotlin/Lazy;", "headerInterceptor", "Lokhttp3/Interceptor;", "loggingInterceptor", "Lokhttp3/logging/HttpLoggingInterceptor;", "moshi", "Lcom/squareup/moshi/Moshi;", "kotlin.jvm.PlatformType", "okHttpClient", "Lokhttp3/OkHttpClient;", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "app_debug"})
public final class RetrofitInstance {
    @org.jetbrains.annotations.NotNull
    public static final com.soarlog.app.network.RetrofitInstance INSTANCE = null;
    private static final java.lang.String BASE_URL = "https://flightbook.glidernet.org/";
    private static final com.squareup.moshi.Moshi moshi = null;
    private static final okhttp3.logging.HttpLoggingInterceptor loggingInterceptor = null;
    private static final okhttp3.Interceptor headerInterceptor = null;
    private static final okhttp3.OkHttpClient okHttpClient = null;
    private static final kotlin.Lazy retrofit$delegate = null;
    @org.jetbrains.annotations.NotNull
    private static final kotlin.Lazy api$delegate = null;
    
    private RetrofitInstance() {
        super();
    }
    
    private final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.soarlog.app.network.OgnApiService getApi() {
        return null;
    }
}