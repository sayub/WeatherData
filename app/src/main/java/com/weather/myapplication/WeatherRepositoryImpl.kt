package com.weather.myapplication

import mate.weather.com.weathermate.weather.WeatherData

class WeatherRepositoryImpl(val api: Api) : WeatherRepository {
    private val mApiKey = "8dcf20d378aed4d39fed8d2b95092c0d"
    private val mMatric = "metric"

    override suspend fun loadWeather(lat: Float, lon: Float): WeatherData?
        = api.getWeatherData(lat, lon, mMatric, mApiKey)
}