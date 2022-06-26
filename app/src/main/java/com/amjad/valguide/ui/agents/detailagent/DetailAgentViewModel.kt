package com.amjad.valguide.ui.agents.detailagent

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amjad.valguide.data.Repository
import com.amjad.valguide.data.remote.response.AgentList

class DetailAgentViewModel(private val repository: Repository) : ViewModel() {

    fun setAgentById(uuid: String){
        repository.setAgentById(uuid)
    }

    fun getAgentById(): LiveData<AgentList> {
        return repository.getAgentById()
    }
}