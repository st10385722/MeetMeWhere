package com.example.meetmewherejerry
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Events::class, Users::class],version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val NAME = "meetmewhere_db"
    }
    abstract fun getEventDao() : EventsDAO
    abstract fun getUserDao() : UsersDAO
}

