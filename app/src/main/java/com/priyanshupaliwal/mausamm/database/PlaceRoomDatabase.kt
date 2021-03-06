package com.priyanshupaliwal.mausamm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.priyanshupaliwal.mausamm.data.Place
import com.priyanshupaliwal.mausamm.data.PlaceDao

@Database(entities = [Place::class], version = 1, exportSchema = false)
abstract class PlaceRoomDatabase : RoomDatabase() {

    abstract fun placeDao() : PlaceDao

    companion object {

        @Volatile
        private var INSTANCE: PlaceRoomDatabase? = null

        fun getDatabase(context: Context) : PlaceRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaceRoomDatabase::class.java,
                    "place"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}