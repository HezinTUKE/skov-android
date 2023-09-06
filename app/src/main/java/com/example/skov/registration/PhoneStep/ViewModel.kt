package com.example.skov.registration.PhoneStep

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class ViewModelPhone : ViewModel(){

    val phoneResponse = MutableLiveData<PhoneResponse>()
    val smsResponse = MutableLiveData<SMSResponse>()

    fun sendPhoneNumber(phone : String){
        val part = MultipartBody.Part.createFormData("phone", phone)

        val res = SkovService.getInstance().sendPhone(part)

        res.enqueue(object : Callback<PhoneResponse?> {
            override fun onResponse(
                call: Call<PhoneResponse?>,
                response: Response<PhoneResponse?>
            ) {
                phoneResponse.value = response.body()
                Log.d("ResponsePhone", phoneResponse.value.toString())
            }

            override fun onFailure(call: Call<PhoneResponse?>, t: Throwable) {
                Log.d("ResponsePhone", t.toString())

                call.cancel()
            }

        })
    }

    fun checkSMS(sms : String){
        val part = MultipartBody.Part.createFormData("secret", sms)

        val res = SkovService.getInstance().checkSMS(part)

        res.enqueue(object : Callback<SMSResponse?> {
            override fun onResponse(
                call: Call<SMSResponse?>,
                response: Response<SMSResponse?>
            ) {
                smsResponse.value = response.body()
                Log.d("SMSResponse", smsResponse.value.toString())
            }

            override fun onFailure(call: Call<SMSResponse?>, t: Throwable) {
                Log.d("SMSResponse", t.toString())

                call.cancel()
            }

        })
    }

}