package com.amjad.valguide.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amjad.valguide.data.Repository
import com.amjad.valguide.data.remote.response.MapList

class MapsViewModel(private val repository: Repository) : ViewModel() {

    fun setMaps(){
        repository.setMaps()
    }

    fun getMaps(): LiveData<ArrayList<MapList>> {
        return repository.getMaps()
    }


}