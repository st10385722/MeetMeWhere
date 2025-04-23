package com.example.meetmewherejerry

import android.content.Intent
import androidx.lifecycle.LiveData

class EventsViewModel {
    val eventDao = MainApplication.appDatabase.getEventDao()
    val eventsList: LiveData<List<Events>> = eventDao.getAllEvents()
    lateinit var event: Events
    fun insertEvent(title: String, description: String, date: java.util.Date, time: String, location: String){
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

    fun updateEvent(event: Events){
        eventDao.updateEvent(event)
    }
}