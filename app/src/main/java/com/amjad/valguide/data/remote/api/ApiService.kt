package com.amjad.valguide.data.remote.api

import com.amjad.valguide.data.remote.response.AgentResponse
import com.amjad.valguide.data.remote.response.AgentResponseById
import com.amjad.valguide.data.remote.response.GunsResponse
import com.amjad.valguide.data.remote.response.MapsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("agents?isPlayableCharacter=true")
    fun getAgents(): Call<AgentResponse>

    @GET("agents/{agentUuid}")
    fun getAgentById(@Path("agentUuid")uuid: String): Call<AgentResponseById>

    @GET("weapons")
    fun getWeapons(): Call<GunsResponse>

    @GET("maps")
    fun getMaps(): Call<MapsResponse>
}