package com.soarlog.app.data;

import java.lang.System;

@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b2\u0006\u0010\r\u001a\u00020\u000eH\'J\u0011\u0010\u000f\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lcom/soarlog/app/data/FlightDao;", "", "delete", "", "flight", "Lcom/soarlog/app/models/Flight;", "(Lcom/soarlog/app/models/Flight;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFlights", "Lkotlinx/coroutines/flow/Flow;", "", "getAllFlightsSync", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFlightById", "id", "", "getFlightCount", "insert", "", "update", "app_debug"})
public abstract interface FlightDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM flight ORDER BY date DESC, takeoffTime DESC")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.soarlog.app.models.Flight>> getAllFlights();
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM flight WHERE id = :id LIMIT 1")
    public abstract kotlinx.coroutines.flow.Flow<com.soarlog.app.models.Flight> getFlightById(int id);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Update
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Delete
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT COUNT(*) FROM flight")
    public abstract java.lang.Object getFlightCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "SELECT * FROM flight")
    public abstract java.lang.Object getAllFlightsSync(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.soarlog.app.models.Flight>> continuation);
}