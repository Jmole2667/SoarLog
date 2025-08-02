package com.soarlog.app.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\rH\u0016J\u000e\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\rJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/soarlog/app/viewmodel/FlightLogViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/soarlog/app/repository/FlightRepository;", "(Lcom/soarlog/app/repository/FlightRepository;)V", "_isSearching", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_ognFlights", "", "Lcom/soarlog/app/viewmodel/OgnFlightDisplay;", "allFlights", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/soarlog/app/models/Flight;", "getAllFlights", "()Lkotlinx/coroutines/flow/StateFlow;", "isSearching", "ognFlights", "getOgnFlights", "clearSearch", "", "deleteFlight", "flight", "insertFlight", "parseTimeToSeconds", "", "timeString", "", "searchFlights", "registration", "date", "Ljava/util/Date;", "app_debug"})
public class FlightLogViewModel extends androidx.lifecycle.ViewModel {
    private final com.soarlog.app.repository.FlightRepository repository = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.soarlog.app.viewmodel.OgnFlightDisplay>> _ognFlights = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.viewmodel.OgnFlightDisplay>> ognFlights = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isSearching = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSearching = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.models.Flight>> allFlights = null;
    
    public FlightLogViewModel(@org.jetbrains.annotations.NotNull
    com.soarlog.app.repository.FlightRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.viewmodel.OgnFlightDisplay>> getOgnFlights() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isSearching() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.models.Flight>> getAllFlights() {
        return null;
    }
    
    public final void searchFlights(@org.jetbrains.annotations.NotNull
    java.lang.String registration, @org.jetbrains.annotations.NotNull
    java.util.Date date) {
    }
    
    private final long parseTimeToSeconds(java.lang.String timeString) {
        return 0L;
    }
    
    public final void clearSearch() {
    }
    
    public final void insertFlight(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight) {
    }
    
    public void deleteFlight(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight) {
    }
}