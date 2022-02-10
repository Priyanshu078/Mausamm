package com.example.mausam

import android.app.Application
import com.example.mausam.database.PlaceRoomDatabase

class WeatherApplication:Application() {
    val database :PlaceRoomDatabase by lazy {
        PlaceRoomDatabase.getDatabase(this)
    }
}