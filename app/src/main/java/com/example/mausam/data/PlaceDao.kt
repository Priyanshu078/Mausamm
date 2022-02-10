package com.example.mausam.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(place: Place)

    @Delete
    suspend fun delete(place:Place)

    @Query("select * from place")
    fun getPlaces() : Flow<List<Place>>

}