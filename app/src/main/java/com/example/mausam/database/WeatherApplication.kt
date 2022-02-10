package com.example.mausam.database

import android.app.Application

class WeatherApplication : Application() {
    val database: PlaceRoomDatabase by lazy { PlaceRoomDatabase.getDatabase(this) }
}