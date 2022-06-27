package com.amjad.valguide.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amjad.valguide.data.Repository
import com.amjad.valguide.di.Injection
import com.amjad.valguide.ui.agents.AgentsViewModel
import com.amjad.valguide.ui.agents.detailagent.DetailAgentViewModel
import com.amjad.valguide.ui.guns.GunsViewModel
import com.amjad.valguide.ui.maps.MapsViewModel

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
        else if(modelClass.isAssignableFrom(DetailAgentViewModel::class.java)){
            return DetailAgentViewModel(repository) as T
        }
        else if(modelClass.isAssignableFrom(GunsViewModel::class.java)){
            return GunsViewModel(repository) as T
        }
        else if(modelClass.isAssignableFrom(MapsViewModel::class.java)){
            return MapsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}