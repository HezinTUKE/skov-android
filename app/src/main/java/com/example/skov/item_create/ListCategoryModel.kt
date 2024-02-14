package com.example.skov.item_create

data class ListCategory(
    val categorys : List<Category>
)

data class Category(
    val id : Int,
    val name : String
)