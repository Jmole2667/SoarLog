package com.soarlog.app.ui.charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.soarlog.app.models.Flight

@Composable
fun FlightsByGliderChart(flights: List<Flight>) {
    val flightsByGlider = flights.groupBy { it.gliderType }
    val entries = flightsByGlider.entries.mapIndexed { index, entry ->
        BarEntry(index.toFloat(), entry.value.size.toFloat())
    }

    val dataSet = BarDataSet(entries, "Flights by Glider")

    AndroidView(factory = { context ->
        BarChart(context).apply {
            data = BarData(dataSet)
            description.isEnabled = false
            setFitBars(true)

            // Enable axis lines
            axisLeft.setDrawAxisLine(true)
            xAxis.setDrawAxisLine(true)

            invalidate()
        }
    })
}
