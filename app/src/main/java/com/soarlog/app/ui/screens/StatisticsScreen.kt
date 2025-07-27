package com.soarlog.app.ui.screens

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soarlog.app.models.Flight
import androidx.compose.material3.Card
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatisticsScreen(flights: List<Flight>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Statistics") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Total Flights: ${flights.size}")
                    Text(text = "Total Duration: ${flights.sumOf { it.duration }} minutes")

                    val flightsByGlider = flights.groupBy { it.gliderType }
                    flightsByGlider.forEach { (gliderType, flights) ->
                        Text(text = "Flights with $gliderType: ${flights.size}")
                        Text(text = "Total duration with $gliderType: ${flights.sumOf { it.duration }} minutes")
                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    val flightsByGlider = flights.groupBy { it.gliderType }
                    val entries = flightsByGlider.mapIndexed { index, entry ->
                        BarEntry(index.toFloat(), entry.value.size.toFloat())
                    }
                    val labels = flightsByGlider.keys.map { it ?: "Unknown" }

                    AndroidView(
                        factory = { context ->
                            BarChart(context).apply {
                                description.isEnabled = false
                                xAxis.valueFormatter = IndexAxisValueFormatter(labels)
                                xAxis.position = XAxis.XAxisPosition.BOTTOM
                                xAxis.granularity = 1f
                                axisRight.isEnabled = false
                                setData(BarData(BarDataSet(entries, "Flights per Glider")))
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
