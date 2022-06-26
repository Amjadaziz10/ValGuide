package com.amjad.valguide.data.remote.response

data class AgentResponse(
    val status: String,
    val data: List<AgentList>
)

data class AgentResponseById(
    val status: String,
    val data: AgentList
)

data class AgentList(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val fullPortraitV2: String,
    val background: String,
    val role: Role,
    val abilities: List<Abilities>
)

data class Role(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String
)

data class Abilities(
    val slot: String,
    val displayName: String,
    val description: String,
    val displayIcon: String
)
