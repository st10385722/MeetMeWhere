package com.example.meetmewherejerry

import android.app.Application
import androidx.room.Room

class MainApplication : Application() {
    companion object{
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
            super.onCreate()
            appDatabase = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                AppDatabase.NAME
            ).allowMainThreadQueries().build()
    }
}