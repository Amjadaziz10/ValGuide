package com.amjad.valguide.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amjad.valguide.data.remote.api.ApiConfig
import com.amjad.valguide.data.remote.response.AgentList
import com.amjad.valguide.data.remote.response.AgentResponse
import com.amjad.valguide.data.remote.response.AgentResponseById
import com.amjad.valguide.data.remote.response.Role
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    val listAgent = MutableLiveData<ArrayList<AgentList>>()
    val agent = MutableLiveData<AgentList>()

    fun setAgents(){
        val client = ApiConfig.getApiService().getAgents()
        client.enqueue(object : Callback<AgentResponse> {
            override fun onResponse(call: Call<AgentResponse>, response: Response<AgentResponse>) {
                if(response.isSuccessful){
                    listAgent.postValue(response.body()?.data as ArrayList<AgentList>)
                }
            }

            override fun onFailure(call: Call<AgentResponse>, t: Throwable) {
                t.message?.let { Log.d("onFailure", it) }
            }
        })
    }

    fun setAgentById(uuid: String){
        val client = ApiConfig.getApiService().getAgentById(uuid)
        client.enqueue(object : Callback<AgentResponseById> {
            override fun onResponse(call: Call<AgentResponseById>, response: Response<AgentResponseById>) {
                if(response.isSuccessful){
                    agent.postValue(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<AgentResponseById>, t: Throwable) {
                t.message?.let { Log.d("onFailure", it) }
            }
        })
    }

    fun getAgents(): LiveData<ArrayList<AgentList>> {
        return listAgent
    }

    fun getAgentById(): LiveData<AgentList>{
        return agent
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository().apply { instance = this }
            }
    }

}