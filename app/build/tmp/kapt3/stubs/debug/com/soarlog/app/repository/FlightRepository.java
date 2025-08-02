package com.soarlog.app.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\b0\u0010H\u0016J!\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\b2\u0006\u0010\u0013\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/soarlog/app/repository/FlightRepository;", "", "apiService", "Lcom/soarlog/app/network/OgnApiService;", "flightDao", "Lcom/soarlog/app/data/FlightDao;", "(Lcom/soarlog/app/network/OgnApiService;Lcom/soarlog/app/data/FlightDao;)V", "airfieldsToSearch", "", "", "delete", "", "flight", "Lcom/soarlog/app/models/Flight;", "(Lcom/soarlog/app/models/Flight;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFlights", "Lkotlinx/coroutines/flow/Flow;", "getFlightResponse", "Lcom/soarlog/app/models/OgnFlightResponse;", "registration", "date", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFlights", "Lcom/soarlog/app/models/OgnFlight;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "app_debug"})
public class FlightRepository {
    private final com.soarlog.app.network.OgnApiService apiService = null;
    private final com.soarlog.app.data.FlightDao flightDao = null;
    private final java.util.List<java.lang.String> airfieldsToSearch = null;
    
    public FlightRepository(@org.jetbrains.annotations.NotNull
    com.soarlog.app.network.OgnApiService apiService, @org.jetbrains.annotations.NotNull
    com.soarlog.app.data.FlightDao flightDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getFlights(@org.jetbrains.annotations.NotNull
    java.lang.String registration, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.soarlog.app.models.OgnFlight>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public java.lang.Object getFlightResponse(@org.jetbrains.annotations.NotNull
    java.lang.String registration, @org.jetbrains.annotations.NotNull
    java.util.Date date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.soarlog.app.models.OgnFlightResponse> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public kotlinx.coroutines.flow.Flow<java.util.List<com.soarlog.app.models.Flight>> getAllFlights() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public java.lang.Object delete(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}