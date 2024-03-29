package com.example.skov.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelLogin : ViewModel() {
    private val loginResponse = MutableStateFlow<LoginResponse?>(LoginResponse("", 0))
    val response = loginResponse.asStateFlow()

    fun loginUser(
        login : String,
        password : String
    ){

        val loginPart = MultipartBody.Part.createFormData("username", login)
        val passwordPart = MultipartBody.Part.createFormData("password", password)


        val res = SkovService.getInstance().login(
            loginPart,
            passwordPart
        )

        res.enqueue(object : Callback<LoginResponse?>{
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                Log.d("LOGIN_R", "HERE")

                loginResponse.value = response.body()

                Log.d("LoginResponse", loginResponse.value.toString())
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                call.cancel()
            }

        })

    }
}