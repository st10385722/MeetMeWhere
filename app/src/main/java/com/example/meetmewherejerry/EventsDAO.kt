package com.example.meetmewherejerry

import android.media.metrics.Event
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventsDAO {
    @Insert
    fun insertEvent(events: Events)
    @Query("SELECT * FROM events")
    fun getAllEvents(): LiveData<List<Events>>
    @Query("DELETE FROM events where id = :id")
    fun deleteEvent(id: Int)
}