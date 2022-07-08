package mate.weather.com.weathermate.weather

import com.google.gson.annotations.SerializedName

data class Wind(val speed: Float, @SerializedName("deg") var direction: Float)