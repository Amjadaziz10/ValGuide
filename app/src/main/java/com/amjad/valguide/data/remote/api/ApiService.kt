package com.amjad.valguide.data.remote.api

import com.amjad.valguide.data.remote.response.AgentResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("agents?isPlayableCharacter=true")
    fun getAgents(): Call<AgentResponse>
}