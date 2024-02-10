package com.example.skov

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.skov.login.LoginResponse
import com.example.skov.login.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first

class MainActivityViewModel : ViewModel(){
    private var stateShowBottomBarFlow = MutableStateFlow(false)
    val showBottomBar = stateShowBottomBarFlow.asStateFlow()

    suspend fun checkShowBottomBar(
        context : Context
    ) : Boolean {
        val check = UserSession.getAccessToken(context).first().isNotEmpty()
        stateShowBottomBarFlow.value = check
        Log.d("CheckShowBottomBar", check.toString())
        return check
    }
}