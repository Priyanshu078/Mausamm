package com.priyanshupaliwal.mausamm

import android.app.Application
import com.priyanshupaliwal.mausamm.database.PlaceRoomDatabase

class WeatherApplication:Application() {
    val database :PlaceRoomDatabase by lazy {
        PlaceRoomDatabase.getDatabase(this)
    }
}