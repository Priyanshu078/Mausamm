package com.example.mausam.network

import com.squareup.moshi.Json

data class WeatherForecast (
    @Json(name = "lon")val longitude:Double,
    @Json(name = "lat")val latitude:Double,
    val current: Current,
    val hourly: List<Hourly>,
    val daily: List<Daily>
    )

data class Current(
    @Json(name = "sunrise") val sunRise:Long,
    @Json(name = "sunset") val sunSet:Long,
    @Json(name = "temp") val temperature:Double,
    @Json(name = "feels_like") val feelsLike:Double,
    val pressure:Int,
    val humidity:Int,
    @Json(name = "dew_point") val dewPoint:Double,
    val uvi:Double,
    val clouds:Int,
    val visibility:Int,
    @Json(name = "wind_speed") val windSpeed:Double,
    @Json(name = "wind_deg") val windDegree:Int,
    val weather:List<Weather>,
)

data class Hourly(
    @Json(name = "dt") val date:Long,
    @Json(name = "temp") val temperature:Double,
    @Json(name = "feels_like") val feelsLike:Double,
    val pressure:Int,
    val humidity:Int,
    @Json(name = "dew_point") val dewPoint:Double,
    val uvi:Double,
    val clouds:Int,
    val visibility:Int,
    @Json(name = "wind_speed") val windSpeed:Double,
    @Json(name = "wind_deg") val windDegree:Int,
    @Json(name = "wind_gust") val windGust:Double,
    val weather:List<Weather>,
)

data class Daily(
    @Json(name = "dt") val date:Long,
    @Json(name = "sunrise") val sunRise:Long,
    @Json(name = "sunset") val sunSet:Long,
    @Json(name = "moonrise") val moonRise:Long,
    @Json(name = "moonset") val moonSet:Long,
    @Json(name = "moon_phase") val moonPhase:Double,
    @Json(name = "temp") val temperature:Temperature,
    @Json(name = "feels_like") val feelsLike:FeelsLike,
    val pressure:Int,
    val humidity:Int,
    @Json(name = "dew_point") val dewPoint:Double,
    val uvi:Double,
    val clouds:Int,
    @Json(name = "wind_speed") val windSpeed:Double,
    @Json(name = "wind_deg") val windDegree:Int,
    @Json(name = "wind_gust") val windGust:Double,
    val weather:List<Weather>,
)

data class Temperature(
    val day:Double,
    val min:Double,
    val max:Double,
    val night:Double,
    @Json(name = "eve") val evening:Double,
    @Json(name = "morn") val morning:Double
)

data class FeelsLike(
    val day:Double,
    val night:Double,
    @Json(name = "eve") val evening:Double,
    @Json(name = "morn") val morning:Double
)