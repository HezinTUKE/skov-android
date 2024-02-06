package com.example.skov.state

sealed class FEState<T> : StateIF<T>

data class Loading<T>(override val state : T?) : FEState<T>()
data class Success<T>(override val state : T?) : FEState<T>()
data class Error<T>(override val state : T?)  : FEState<T>()
data class IsNotAuthenticated<T>(override val state : T?) : FEState<T>()