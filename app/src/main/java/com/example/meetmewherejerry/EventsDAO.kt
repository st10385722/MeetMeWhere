package com.example.meetmewherejerry

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EventsDAO {
    @Insert
    fun insertEvent(events: EventsEntity)

    @Query("SELECT * FROM events")
    fun getAllEvents(): LiveData<List<EventsEntity>>
}