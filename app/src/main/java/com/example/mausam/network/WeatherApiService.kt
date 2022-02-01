package com.example.mausam.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService{
    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName:String,
        @Query("appid") apiKey:String,
        @Query("units") units:String
    ):WeatherData

    @GET("onecall")
    suspend fun getWeatherForecast(
        @Query("lat") latitude:Double,
        @Query("lon") longitude:Double,
        @Query("exclude") exclude:String,
        @Query("appid") apiKey:String,
        @Query("units") units:String
    ):WeatherForecast
}



object WeatherApi {
    val retrofitService: WeatherApiService by lazy { retrofit.create(WeatherApiService::class.java) }
}