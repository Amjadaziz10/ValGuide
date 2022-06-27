package com.amjad.valguide.ui.guns

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amjad.valguide.data.Repository
import com.amjad.valguide.data.remote.response.AgentList
import com.amjad.valguide.data.remote.response.GunList

class GunsViewModel(private val repository: Repository) : ViewModel() {

    fun setGuns(){
        repository.setGuns()
    }

    fun getGuns(): LiveData<ArrayList<GunList>> {
        return repository.getGuns()
    }
}