package com.example.skov.location

data class LocationListModel(
    val locations : List<Location>
)

data class Location(
    val id : Int,
    val name : String
)