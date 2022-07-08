package com.weather.myapplication

import androidx.lifecycle.ViewModelProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyInjector {

    fun getInstance() = this

    fun inject(activity: MainActivity): WeatherViewModel = ViewModelProvider(activity, WeatherViewModelFactory(
        getRepository()))
        .get(WeatherViewModel::class.java)

    fun getRepository(): WeatherRepository = WeatherRepositoryImpl(getApi())

    fun getApi(): Api = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(Api::class.java)

}