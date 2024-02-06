package com.example.skov.item.state

import com.example.skov.item.ItemResponseModel

sealed class ItemState : StateIF

data class Loading(override val response: ItemResponseModel?) : ItemState()
data class Success(override val response: ItemResponseModel?) : ItemState()
data class Error(override val response: ItemResponseModel?) : ItemState()