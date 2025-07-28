package com.soarlog.app.ui.charts

import android.graphics.Color
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.soarlog.app.models.Flight
import java.util.Calendar

@Composable
fun FlightsByMonthChart(flights: List<Flight>, year: Int) {
    val flightsByMonth = flights
        .filter { flight ->
            val calendar = Calendar.getInstance()
            calendar.time = flight.date
            calendar.get(Calendar.YEAR) == year
        }
        .groupBy {
            val calendar = Calendar.getInstance()
            calendar.time = it.date
            calendar.get(Calendar.MONTH)
        }

    val entries = (0..11).map { month ->
        val flightCount = flightsByMonth[month]?.size ?: 0
        Entry(month.toFloat(), flightCount.toFloat())
    }

    val chartBackgroundColor = MaterialTheme.colorScheme.background.toArgb()
    val textColor = MaterialTheme.colorScheme.onBackground.toArgb()
    val lineAndPointColor = MaterialTheme.colorScheme.primary.toArgb()

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                setBackgroundColor(chartBackgroundColor)

                xAxis.apply {
                    position = XAxis.XAxisPosition.BOTTOM
                    setDrawGridLines(false)
                    valueFormatter = IndexAxisValueFormatter(arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"))
                    this.textColor = textColor
                    granularity = 1f
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
            val dataSet = LineDataSet(entries, "Flights in $year").apply {
                color = lineAndPointColor
                valueTextColor = textColor
                setCircleColor(lineAndPointColor)
                circleRadius = 4f
                setDrawCircleHole(false)

            }
            chart.data = LineData(dataSet)
            chart.invalidate()
        }
    )
}
