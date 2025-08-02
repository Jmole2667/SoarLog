package com.soarlog.app.viewmodel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\u0006\u0010\u001a\u001a\u00020\u0019J\u0006\u0010\u001b\u001a\u00020\u0019J\u0010\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0010H\u0016J\u000e\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\rJ\u000e\u0010 \u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u0010J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\rH\u0002J\u0010\u0010$\u001a\u00020\"2\u0006\u0010#\u001a\u00020\rH\u0002J\u0018\u0010%\u001a\u00020\u00192\u0006\u0010&\u001a\u00020\r2\b\b\u0002\u0010\'\u001a\u00020(R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/soarlog/app/viewmodel/FlightLogViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/soarlog/app/repository/FlightRepository;", "(Lcom/soarlog/app/repository/FlightRepository;)V", "_allOgnFlights", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/soarlog/app/viewmodel/OgnFlightDisplay;", "_isSearching", "", "_ognFlights", "_registrationFilter", "", "allFlights", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/soarlog/app/models/Flight;", "getAllFlights", "()Lkotlinx/coroutines/flow/StateFlow;", "isSearching", "ognFlights", "getOgnFlights", "registrationFilter", "getRegistrationFilter", "applyRegistrationFilter", "", "clearRegistrationFilter", "clearSearch", "deleteFlight", "flight", "filterByRegistration", "registration", "insertFlight", "parseOgnTimeToSeconds", "", "timeString", "parseTimeToSeconds", "searchFlightsByAirfield", "airfield", "date", "Ljava/util/Date;", "app_debug"})
public class FlightLogViewModel extends androidx.lifecycle.ViewModel {
    private final com.soarlog.app.repository.FlightRepository repository = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.soarlog.app.viewmodel.OgnFlightDisplay>> _ognFlights = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.soarlog.app.viewmodel.OgnFlightDisplay>> ognFlights = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.soarlog.app.viewmodel.OgnFlightDisplay>> _allOgnFlights = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _registrationFilter = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> registrationFilter = null;
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
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getRegistrationFilter() {
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
    
    public final void searchFlightsByAirfield(@org.jetbrains.annotations.NotNull
    java.lang.String airfield, @org.jetbrains.annotations.NotNull
    java.util.Date date) {
    }
    
    public final void filterByRegistration(@org.jetbrains.annotations.NotNull
    java.lang.String registration) {
    }
    
    private final void applyRegistrationFilter() {
    }
    
    public final void clearRegistrationFilter() {
    }
    
    private final long parseOgnTimeToSeconds(java.lang.String timeString) {
        return 0L;
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