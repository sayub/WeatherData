package mate.weather.com.weathermate.weather

data class WeatherData(val coord: Coord,
                  val weather: ArrayList<Weather>,
                  val base: String?,
                  val main: Main,
                  val visibility : Float,
                  val wind: Wind,
                  val clouds: Clouds,
                  val dt: Float,
                  val sys: Sys,
                  val id: Float,
                  val name: String?,
                  val cod: Float)