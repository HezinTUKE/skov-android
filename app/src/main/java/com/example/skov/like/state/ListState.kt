package com.example.skov.like.state

import com.example.skov.like.LikeModel

sealed class LikeState : StateIF

data class Loading(override val like : LikeModel?) : LikeState()
data class Success(override val like : LikeModel?) : LikeState()
data class Error(override val like : LikeModel?)  : LikeState()
