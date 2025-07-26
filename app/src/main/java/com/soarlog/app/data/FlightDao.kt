package com.soarlog.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.soarlog.app.models.Flight
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query("SELECT * FROM flight ORDER BY date DESC")
    fun getAllFlights(): Flow<List<Flight>>

    @Insert
    suspend fun insert(flight: Flight)

    @Delete
    suspend fun delete(flight: Flight)
}
