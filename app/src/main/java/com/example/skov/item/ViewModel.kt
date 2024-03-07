package com.example.skov.item

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.skov.item.state.Error
import com.example.skov.item.state.ItemState
import com.example.skov.item.state.Loading
import com.example.skov.item.state.Success
import com.example.skov.network.SkovService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModel : ViewModel() {

    private val responseItem = MutableStateFlow<ItemState>(Loading(null))
    val state = responseItem.asStateFlow()

    fun getItem(
        token : String,
        id : Int
    ){


        val req = SkovService.getInstance().getItem(id, "Token $token")

        req.enqueue(object : Callback<ItemResponseModel> {
            override fun onResponse(
                call: Call<ItemResponseModel>,
                response: Response<ItemResponseModel>
            ) {
                Log.d("ItemL", response.body().toString())
                responseItem.value = Success(response.body())
            }

            override fun onFailure(call: Call<ItemResponseModel>, t: Throwable) {
                responseItem.value = Error(null)
            }

        })
    }

}