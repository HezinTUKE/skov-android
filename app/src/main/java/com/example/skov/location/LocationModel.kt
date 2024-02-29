package com.example.skov.location

import android.graphics.drawable.Icon

data class LocationListModel(
    val location : List<Location>
)

data class Location(
    val id : Int,
    val name : String,
    val icon : String?
)