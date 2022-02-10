package com.example.mausam.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "place")
data class Place(
    @PrimaryKey (autoGenerate = true) val id:Int = 0,
    @NotNull @ColumnInfo(name = "name") val placeName:String
)