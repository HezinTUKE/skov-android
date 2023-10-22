package com.example.skov.list

import kotlinx.coroutines.flow.MutableStateFlow

sealed class ListState : StateIF

data class Loading(override val list : Items?) : ListState()
data class Success(override val list : Items?) : ListState()
data class Error(override val list : Items?)  : ListState()
