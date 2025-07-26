package com.soarlog.app.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u000e\u001a\n \r*\u0004\u0018\u00010\u000f0\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\n\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u0013"}, d2 = {"Lcom/soarlog/app/network/RetrofitInstance;", "", "()V", "BASE_URL", "", "api", "Lcom/soarlog/app/network/OgnApiService;", "getApi", "()Lcom/soarlog/app/network/OgnApiService;", "api$delegate", "Lkotlin/Lazy;", "moshi", "Lcom/squareup/moshi/Moshi;", "kotlin.jvm.PlatformType", "retrofit", "Lretrofit2/Retrofit;", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "app_debug"})
public final class RetrofitInstance {
    @org.jetbrains.annotations.NotNull
    public static final com.soarlog.app.network.RetrofitInstance INSTANCE = null;
    private static final java.lang.String BASE_URL = "https://flightbook.glidernet.org/";
    private static final com.squareup.moshi.Moshi moshi = null;
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