package com.soarlog.app.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u000bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/soarlog/app/viewmodel/FlightLogViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/soarlog/app/repository/FlightRepository;", "(Lcom/soarlog/app/repository/FlightRepository;)V", "_ognFlights", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/soarlog/app/models/OgnFlight;", "allFlights", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/soarlog/app/models/Flight;", "getAllFlights", "()Lkotlinx/coroutines/flow/StateFlow;", "ognFlights", "getOgnFlights", "", "registration", "", "insertFlight", "flight", "app_debug"})
public final class FlightLogViewModel extends androidx.lifecycle.ViewModel {
    private final com.soarlog.app.repository.FlightRepository repository = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.soarlog.app.models.OgnFlight>> _ognFlights = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.models.OgnFlight>> ognFlights = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.models.Flight>> allFlights = null;
    
    public FlightLogViewModel(@org.jetbrains.annotations.NotNull
    com.soarlog.app.repository.FlightRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.models.OgnFlight>> getOgnFlights() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.models.Flight>> getAllFlights() {
        return null;
    }
    
    public final void getOgnFlights(@org.jetbrains.annotations.NotNull
    java.lang.String registration) {
    }
    
    public final void insertFlight(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight) {
    }
}