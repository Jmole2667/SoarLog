package com.soarlog.app.network;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/soarlog/app/network/OgnApiService;", "", "getFlights", "", "Lcom/soarlog/app/models/OgnFlight;", "registration", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface OgnApiService {
    
    @org.jetbrains.annotations.Nullable
    @retrofit2.http.GET(value = "api/flights/{registration}")
    public abstract java.lang.Object getFlights(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Path(value = "registration")
    java.lang.String registration, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.soarlog.app.models.OgnFlight>> continuation);
}