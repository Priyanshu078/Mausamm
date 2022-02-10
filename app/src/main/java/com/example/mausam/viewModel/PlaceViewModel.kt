package com.example.mausam.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mausam.data.PlaceDao

class PlaceViewModel(private val placeDao: PlaceDao) : ViewModel() {
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