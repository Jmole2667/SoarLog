package com.soarlog.app.ui.screens;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a,\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001aJ\u0010\u0007\u001a\u00020\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0006\u0010\n\u001a\u00020\u000b2\u0014\b\u0002\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\rH\u0007\u001a\b\u0010\u0010\u001a\u00020\u0001H\u0007\u001a\u001c\u0010\u0011\u001a\u00020\u00012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\rH\u0007\u001a\u0012\u0010\u0014\u001a\u00020\u000e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002\u00a8\u0006\u0017"}, d2 = {"FlightListItem", "", "flight", "Lcom/soarlog/app/models/Flight;", "onDelete", "Lkotlin/Function0;", "onClick", "FlightListScreen", "flights", "", "viewModel", "Lcom/soarlog/app/viewmodel/FlightLogViewModel;", "onFlightClick", "Lkotlin/Function1;", "", "onEditFlight", "FlightListScreenPreview", "SortMenu", "onSortOrderChange", "Lcom/soarlog/app/ui/screens/SortOrder;", "parseTimeToMinutes", "timeString", "", "app_debug"})
public final class FlightListScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void FlightListScreen(@org.jetbrains.annotations.NotNull
    java.util.List<com.soarlog.app.models.Flight> flights, @org.jetbrains.annotations.NotNull
    com.soarlog.app.viewmodel.FlightLogViewModel viewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onFlightClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onEditFlight) {
    }
    
    private static final int parseTimeToMinutes(java.lang.String timeString) {
        return 0;
    }
    
    @androidx.compose.runtime.Composable
    public static final void SortMenu(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.soarlog.app.ui.screens.SortOrder, kotlin.Unit> onSortOrderChange) {
    }
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void FlightListItem(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void FlightListScreenPreview() {
    }
}