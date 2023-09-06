package com.example.skov.registration.UserStep

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import retrofit2.Callback
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response

class ViewModel : ViewModel() {

    val createResponse = MutableLiveData<UserResponse>()

    fun createUser(
        phone : String,
        name : String,
        surname : String,
        email : String,
        dob : String,
        username : String,
        password : String,
    ){
        val phonePart = MultipartBody.Part.createFormData("phone", phone)
        val namePart = MultipartBody.Part.createFormData("first_name", name)
        val surnamePart = MultipartBody.Part.createFormData("last_name", surname)
        val emailPart = MultipartBody.Part.createFormData("email", email)
        val dobPart = MultipartBody.Part.createFormData("date_of_birth", dob)
        val usernamePart = MultipartBody.Part.createFormData("username", username)
        val passwordPart = MultipartBody.Part.createFormData("password", password)

        val res = SkovService.getInstance().createUser(
            phonePart,
            namePart,
            surnamePart,
            emailPart,
            dobPart,
            usernamePart,
            passwordPart
        )

        res.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(
                call: Call<UserResponse?>,
                response: Response<UserResponse?>
            ) {
                createResponse.value = response.body()

                Log.d("ResponseFinish", createResponse.value.toString())
            }
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                call.cancel()
            }

        })
    }

}