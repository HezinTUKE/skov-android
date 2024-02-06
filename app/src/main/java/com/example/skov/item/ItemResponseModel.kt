package com.example.skov.item

import java.time.format.DateTimeFormatter

data class ItemResponseModel(
    val item: Item
)

data class Item(
    val title : String,
    val price : Float,
    val description : String,
    val category : String,
    val subcategory : String,
    val country : String,
    val region : String,
    val photos : List<String>,
    val is_owner : Boolean,
    val is_active : Boolean,
    val active_time : DateTimeFormatter?,
    val is_liked : Boolean
)