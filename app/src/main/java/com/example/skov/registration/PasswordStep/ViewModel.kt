package com.example.skov.registration.PasswordStep

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ViewModelPrivate : ViewModel() {

    val privateResponse = MutableLiveData<Models>()

    fun createPrivateUser(
        username : String,
        password : String
    ){
        val usernameModel = MultipartBody.Part.createFormData("username", username)
        val passwordModel = MultipartBody.Part.createFormData("password", password)

        val res = SkovService.getInstance().sendPrivate(
            usernameModel,
            passwordModel
        )

        res.enqueue(object : Callback<Models>{
            override fun onResponse(
                call: Call<Models>,
                response: Response<Models>
            ) {
                privateResponse.value = response.body()
            }

            override fun onFailure(call: Call<Models>, t: Throwable) {
                call.cancel()
            }

        })

    }

}