package com.example.meetmewherejerry

import android.app.Application
import androidx.room.Room

class MainApplication : Application() {
    companion object{
        lateinit var eventDatabase : EventDatabase
    }

    override fun onCreate() {
        super.onCreate()
        eventDatabase = Room.databaseBuilder(
            applicationContext,
            EventDatabase::class.java,
            EventDatabase.NAME
        ).build()
    }
}