package com.example.skov.list

data class Items(
    val items : List<ItemModel>
)

data class ItemModel(
    val id : Int,
    val title : String,
    val description : String,
    val price : String,
    val category : String,
    val subcategory : String,
    val country : String,
    val region : String,
    val photos : List<String>
)

