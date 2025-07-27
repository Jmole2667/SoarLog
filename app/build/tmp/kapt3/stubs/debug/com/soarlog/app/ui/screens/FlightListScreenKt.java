package com.soarlog.app.ui.screens;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u001e\u0010\b\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\b\u0010\r\u001a\u00020\u0001H\u0007\u001a\u001c\u0010\u000e\u001a\u00020\u00012\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010H\u0007\u00a8\u0006\u0012"}, d2 = {"FlightListItem", "", "flight", "Lcom/soarlog/app/models/Flight;", "onDelete", "Lkotlin/Function0;", "flightNumber", "", "FlightListScreen", "flights", "", "viewModel", "Lcom/soarlog/app/viewmodel/FlightLogViewModel;", "FlightListScreenPreview", "SortMenu", "onSortOrderChange", "Lkotlin/Function1;", "Lcom/soarlog/app/ui/screens/SortOrder;", "app_debug"})
public final class FlightListScreenKt {
    
    @androidx.compose.runtime.Composable
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    public static final void FlightListScreen(@org.jetbrains.annotations.NotNull
    java.util.List<com.soarlog.app.models.Flight> flights, @org.jetbrains.annotations.NotNull
    com.soarlog.app.viewmodel.FlightLogViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void SortMenu(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.soarlog.app.ui.screens.SortOrder, kotlin.Unit> onSortOrderChange) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void FlightListItem(@org.jetbrains.annotations.NotNull
    com.soarlog.app.models.Flight flight, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDelete, int flightNumber) {
    }
    
    @androidx.compose.runtime.Composable
    public static final void FlightListScreenPreview() {
    }
}