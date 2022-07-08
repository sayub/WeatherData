package com.weather.myapplication

import mate.weather.com.weathermate.weather.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("data/2.5/weather?")
    suspend fun getWeatherData(@Query("lat") lat: Float,
                       @Query("lon") lon: Float,
                       @Query("units") units: String,
                       @Query("appid") appid: String) : WeatherData

    @GET("data/2.5/weather?")
    suspend fun getWeatherData(@Query("id") id: Int,
                       @Query("units") units: String,
                       @Query("appid") appid: String) : WeatherData
}