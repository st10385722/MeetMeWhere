package com.example.meetmewherejerry
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Events::class],version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class EventDatabase : RoomDatabase() {
    companion object {
        const val NAME = "meetmewhere_db"
    }

    abstract fun getEventDao() : EventsDAO
}