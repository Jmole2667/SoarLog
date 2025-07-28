package com.soarlog.app.ui.charts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.soarlog.app.models.Flight

@Composable
fun FlightsByGliderChart(flights: List<Flight>) {
    val flightsByGlider = flights.groupBy { it.gliderType }
    val entries = flightsByGlider.entries.mapIndexed { index, entry ->
        BarEntry(index.toFloat(), entry.value.size.toFloat())
    }

    val chartBackgroundColor = MaterialTheme.colorScheme.background.toArgb()
    val textColor = MaterialTheme.colorScheme.onBackground.toArgb()
    val barColor = MaterialTheme.colorScheme.primary.toArgb()


    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        factory = { context ->
            BarChart(context).apply {
                description.isEnabled = false
                setFitBars(true)

                setBackgroundColor(chartBackgroundColor)

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    valueFormatter = IndexAxisValueFormatter(flightsByGlider.keys.toList())
                    this.textColor = textColor
                }

                axisLeft.apply {
                    setDrawGridLines(true)
                    this.textColor = textColor
                    axisMinimum = 0f
                }

                axisRight.isEnabled = false
                legend.isEnabled = true
                legend.textColor = textColor

            }
        },
        update = { chart ->
            val dataSet = BarDataSet(entries, "Flights by Glider").apply {
                color = barColor
                valueTextColor = textColor
            }
            chart.data = BarData(dataSet)
            chart.invalidate()
        }
    )
}
