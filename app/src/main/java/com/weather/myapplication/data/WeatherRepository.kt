package com.weather.myapplication

import mate.weather.com.weathermate.weather.WeatherData

interface WeatherRepository {

    suspend fun loadWeather(lat: Float, lon: Float): WeatherData?
}