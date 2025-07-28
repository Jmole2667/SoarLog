package com.soarlog.app.ui.charts

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

    AndroidView(
        modifier = Modifier.height(300.dp),
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                setFitBars(true)
                axisLeft.setDrawAxisLine(true)
                xAxis.setDrawAxisLine(true)
                xAxis.valueFormatter = com.github.mikephil.charting.formatter.IndexAxisValueFormatter(flightsByGlider.keys.toList())
            }
        },
        update = { chart ->
            val dataSet = BarDataSet(entries, "Flights by Glider")
            chart.data = BarData(dataSet)
            chart.invalidate()
        }
    )
}
