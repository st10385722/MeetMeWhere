package com.example.meetmewherejerry

import androidx.lifecycle.LiveData
import java.sql.Date
import java.sql.Time

class EventsViewModel {
    val eventDao = MainApplication.eventDatabase.getEventDao()
    val eventsList: LiveData<List<Events>> = eventDao.getAllEvents()

    fun insertEvent(title: String, description: String, date: Date, time: String, location: String){
        eventDao.insertEvent(Events(
            title = title,
            description = description,
            date = date,
            time = time,
            location = location
        ))
    }

    fun deleteEvent(id: Int){
        eventDao.deleteEvent(id)
    }

}