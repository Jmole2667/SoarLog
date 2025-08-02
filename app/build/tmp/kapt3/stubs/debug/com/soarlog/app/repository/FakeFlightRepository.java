package com.soarlog.app.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u000fH\u0016J!\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/soarlog/app/repository/FakeFlightRepository;", "Lcom/soarlog/app/repository/FlightRepository;", "apiService", "Lcom/soarlog/app/network/OgnApiService;", "flightDao", "Lcom/soarlog/app/data/FlightDao;", "(Lcom/soarlog/app/network/OgnApiService;Lcom/soarlog/app/data/FlightDao;)V", "debugDatabaseState", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "flight", "Lcom/soarlog/app/models/Flight;", "(Lcom/soarlog/app/models/Flight;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFlights", "Lkotlinx/coroutines/flow/Flow;", "", "getFlightsByAirfield", "Lcom/soarlog/app/models/OgnFlightResponse;", "airfield", "", "date", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/util/Date;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "app_debug"})
public final class FakeFlightRepository extends com.soarlog.app.repository.FlightRepository {
    
    public FakeFlightRepository(@org.jetbrains.annotations.Nullable
    com.soarlog.app.network.OgnApiService apiService, @org.jetbrains.annotations.Nullable
    com.soarlog.app.data.FlightDao flightDao) {
        super(null, null);
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getFlightsByAirfield(@org.jetbrains.annotations.NotNull
    java.lang.String airfield, @org.jetbrains.annotations.NotNull
    java.util.Date date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.soarlog.app.models.OgnFlightResponse> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.soarlog.app.models.Flight>> getAllFlights() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object delete(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object debugDatabaseState(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}