package com.example.mausam.viewModel

import android.content.ClipData
import androidx.lifecycle.*
import com.example.mausam.data.Place
import com.example.mausam.data.PlaceDao
import kotlinx.coroutines.launch

class PlaceViewModel(private val placeDao: PlaceDao) : ViewModel() {

    val allItems : LiveData<List<Place>> = placeDao.getPlaces().asLiveData()

    var placesList :MutableList<String> = mutableListOf()

    fun insertNewPlace(place:Place){
        viewModelScope.launch {
            placeDao.insert(place)
        }
    }

    fun getPlacesList(list: List<Place>){
        for(i in list){
            placesList.add(i.placeName)
        }
    }

    fun presentInDatabase(cityName:String):Boolean{
        if(placesList.contains(cityName)){
            return true
        }
        return false
    }

}

class PlaceViewModelFactory(private val placeDao: PlaceDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlaceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlaceViewModel(placeDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}