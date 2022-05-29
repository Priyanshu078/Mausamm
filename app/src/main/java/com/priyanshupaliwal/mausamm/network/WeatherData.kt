package com.priyanshupaliwal.mausamm.network

import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "coord")val coordinates:Coordinates,
    val weather:List<Weather>,
    val base:String,
    val main:Main,
    val visibility:Long,
    val wind:Wind,
    val clouds:Cloud,
    val dt:Long,
    val sys: Country,
    val timezone:Long,
    val id:Long,
    val name:String,
    val cod:Int
)
data class Coordinates(
    @Json(name = "lon")val longitude:Double,
    @Json(name = "lat")val latitude:Double
)

data class Weather(
    val id:Int,
    val main:String?,
    val description:String?,
    val icon:String?
)

data class Main(
    @Json(name = "temp") val temperature:Double,
    @Json(name = "feels_like") val feelsLike:Double,
    @Json(name = "temp_min") val tempMin:Double,
    @Json(name = "temp_max") val tempMax:Double,
    val pressure:Int,
    val humidity:Int,
)

data class Wind(
    val speed:Double,
    val deg:Int,
)

data class Cloud(
    val all:Int
)

data class Country(
    val country:String,
    val sunrise:Long,
    val sunset:Long
)