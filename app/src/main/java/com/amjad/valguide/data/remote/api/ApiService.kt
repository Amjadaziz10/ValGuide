package com.amjad.valguide.data.remote.api

import com.amjad.valguide.data.remote.response.AgentResponse
import com.amjad.valguide.data.remote.response.AgentResponseById
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("agents?isPlayableCharacter=true")
    fun getAgents(): Call<AgentResponse>

    @GET("agents/{agentUuid}")
    fun getAgentById(@Path("agentUuid")uuid: String): Call<AgentResponseById>
}