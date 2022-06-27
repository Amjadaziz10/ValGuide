package com.amjad.valguide.data.remote.response

data class MapsResponse (
    val status: String,
    val data: List<MapList>
)

data class MapList(
    val uuid: String,
    val displayName: String,
    val coordinates: String,
    val displayIcon: String,
    val listViewIcon: String,
    val splash: String
)