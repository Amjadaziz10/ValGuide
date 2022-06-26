package com.amjad.valguide.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amjad.valguide.data.Repository
import com.amjad.valguide.di.Injection
import com.amjad.valguide.ui.agents.AgentsViewModel

class ViewModelFactory private constructor(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(AgentsViewModel::class.java)) {
            return AgentsViewModel(repository) as T
        }
//        else if(modelClass.isAssignableFrom(MapViewModel::class.java)){
//            return MapViewModel(repository) as T
//        }
//        else if(modelClass.isAssignableFrom(TrafficDetailViewModel::class.java)){
//            return TrafficDetailViewModel(repository) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}