package com.weather.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mate.weather.com.weathermate.weather.WeatherData

class WeatherViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    private val _state = MutableStateFlow<WeatherState>(WeatherState.Empty)
    val state: StateFlow<WeatherState> = _state

    fun loadWeather() {
        _state.value = WeatherState.Loading
        try {
            viewModelScope.launch {
                val weather = weatherRepository.loadWeather(4.555f, 8.3738f)
                _state.value = WeatherState.Loaded(weather)
            }
        } catch (e: Exception) {
            _state.value = WeatherState.Error(e.message)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}

sealed class WeatherState {
    object Empty: WeatherState()
    object Loading: WeatherState()
    class Loaded(val data: WeatherData?): WeatherState()
    class Error(val message: String?): WeatherState()
}