package com.example.mausam.viewModel

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mausam.fragments.SearchValue
import com.example.mausam.network.WeatherApi
import com.example.mausam.network.WeatherData
import com.example.mausam.network.WeatherForecast
import kotlinx.coroutines.launch

class BasicWeatherInfoViewModel() : ViewModel(), Parcelable{
    private val _status = MutableLiveData<String>()
    private val _data = MutableLiveData<WeatherData>()
    private val _forecastData = MutableLiveData<WeatherForecast>()


    val status:LiveData<String> get() = _status
    val data:LiveData<WeatherData> get() = _data
    val forecastData:LiveData<WeatherForecast> get() = _forecastData

    constructor(parcel: Parcel) : this() {
    }

    init {
        getWeatherData(SearchValue.cityName)
    }

    private fun getWeatherData(cityName:String){
        viewModelScope.launch {
            try {
                val weatherData:WeatherData = WeatherApi.retrofitService.getWeather(
                    cityName,
                    "2a538372ec42bdd273de4e7d52b1b09e",
                    "metric"
                )
                _data.value = weatherData
                _status.value = weatherData.base
                Log.d("Success", _status.value!!.toString())
                _data.value?.coordinates?.longitude?.let {
                    _data.value?.coordinates?.latitude?.let { it1 ->
                        getWeatherForecast(
                            it1,
                            it
                        )
                    }
                }
            }
            catch (e: Exception){
                _status.value = "Error is ${e.message}"
                Log.d("Error", e.message.toString())
            }
        }
    }

    private fun getWeatherForecast(latitude:Double, longitude:Double){
        viewModelScope.launch {
            try{
                val weatherForecast = WeatherApi.retrofitService.getWeatherForecast(
                    latitude,
                    longitude,
                    "minutely",
                    "2a538372ec42bdd273de4e7d52b1b09e",
                    "metric"
                )
                _forecastData.value = weatherForecast
                Log.d("success 2nd call", weatherForecast.latitude.toString())
            }
            catch (e:Exception){
                Log.d("Error",e.message.toString())
            }
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BasicWeatherInfoViewModel> {
        override fun createFromParcel(parcel: Parcel): BasicWeatherInfoViewModel {
            return BasicWeatherInfoViewModel(parcel)
        }

        override fun newArray(size: Int): Array<BasicWeatherInfoViewModel?> {
            return arrayOfNulls(size)
        }
    }

}