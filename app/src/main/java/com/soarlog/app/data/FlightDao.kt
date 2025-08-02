package com.soarlog.app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.soarlog.app.models.Flight
import kotlinx.coroutines.flow.Flow

@Dao
interface FlightDao {
    @Query("SELECT * FROM flight ORDER BY date DESC, takeoffTime DESC")
    fun getAllFlights(): Flow<List<Flight>>

    @Query("SELECT * FROM flight WHERE id = :id LIMIT 1")
    fun getFlightById(id: Int): Flow<Flight?>

    @Insert
    suspend fun insert(flight: Flight): Long

    @Update
    suspend fun update(flight: Flight)

    @Delete
    suspend fun delete(flight: Flight)
    
    // Add this for debugging
    @Query("SELECT COUNT(*) FROM flight")
    suspend fun getFlightCount(): Int
    
    @Query("SELECT * FROM flight")
    suspend fun getAllFlightsSync(): List<Flight>
}