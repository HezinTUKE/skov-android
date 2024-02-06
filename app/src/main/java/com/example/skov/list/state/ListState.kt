package com.example.skov.list.state

import com.example.skov.list.Items

sealed class ListState : StateIF

data class Loading(override val list : Items?) : ListState()
data class Success(override val list : Items?) : ListState()
data class Error(override val list : Items?)  : ListState()
data class IsNotAuthenticated(override val list : Items?) : ListState()
