package com.amjad.valguide.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amjad.valguide.data.remote.api.ApiConfig
import com.amjad.valguide.data.remote.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    val listAgent = MutableLiveData<ArrayList<AgentList>>()
    val agent = MutableLiveData<AgentList>()
    val listGuns = MutableLiveData<ArrayList<GunList>>()
    val listMaps = MutableLiveData<ArrayList<MapList>>()

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

    fun setGuns(){
        val client = ApiConfig.getApiService().getWeapons()
        client.enqueue(object : Callback<GunsResponse> {
            override fun onResponse(call: Call<GunsResponse>, response: Response<GunsResponse>) {
                if(response.isSuccessful){
                    listGuns.postValue(response.body()?.data as ArrayList<GunList>)
                }
            }

            override fun onFailure(call: Call<GunsResponse>, t: Throwable) {
                t.message?.let { Log.d("onFailure", it) }
            }
        })
    }

    fun setMaps(){
        val client = ApiConfig.getApiService().getMaps()
        client.enqueue(object : Callback<MapsResponse> {
            override fun onResponse(call: Call<MapsResponse>, response: Response<MapsResponse>) {
                if(response.isSuccessful){
                    listMaps.postValue(response.body()?.data as ArrayList<MapList>)
                }
            }

            override fun onFailure(call: Call<MapsResponse>, t: Throwable) {
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

    fun getGuns(): LiveData<ArrayList<GunList>> {
        return listGuns
    }

    fun getMaps(): LiveData<ArrayList<MapList>> {
        return listMaps
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