package com.example.meetmewherejerry

import android.media.metrics.Event
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EventsDAO {
    @Insert
    fun insertEvent(events: Events)
    @Query("SELECT * FROM events")
    fun getAllEvents(): LiveData<List<Events>>
    @Query("DELETE FROM events where id = :id")
    fun deleteEvent(id: Int)
    @Query("SELECT * FROM events LIMIT 1")
    fun getAnyEvent(): Events
    @Query("SELECT * FROM events WHERE id = :id")
    fun getEventById(id: Int) : Events?
    @Update
    fun updateEvent(event: Events)
}