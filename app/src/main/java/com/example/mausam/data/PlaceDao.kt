package com.example.mausam.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(place: Place)

    @Query("delete from place where id = :id")
    fun delete(id:Int)

    @Query("select * from place order by id asc")
    fun getPlaces() : Flow<List<Place>>

}