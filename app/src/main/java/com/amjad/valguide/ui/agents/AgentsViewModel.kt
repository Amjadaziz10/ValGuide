package com.amjad.valguide.ui.agents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amjad.valguide.data.Repository
import com.amjad.valguide.data.remote.response.AgentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AgentsViewModel(private val repository: Repository) : ViewModel() {

    fun setAgents(){
        repository.setAgents()
    }

    fun getAgents(): LiveData<ArrayList<AgentList>> {
        return repository.getAgents()
    }
}