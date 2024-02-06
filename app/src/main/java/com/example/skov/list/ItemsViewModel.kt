package com.example.skov.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsViewModel : ViewModel(){
    private val listState = MutableStateFlow<FEState<Items>>(Loading(null))
    val state = listState.asStateFlow()

    var flag: Boolean by mutableStateOf(false)

    fun getList(){

        val res = SkovService.getInstance().getList(
            "Token 8abed476985c229762b3c05a12f843f3af06300f",
        )

        res.enqueue(object : Callback<Items?> {
            override fun onResponse(call: Call<Items?>, response: Response<Items?>) {
                if(response.code() == 401 || response.code() == 405){
                    listState.value = IsNotAuthenticated(null)
                }else {
                    listState.value = Success(response.body())
                }

                flag = true;
            }

            override fun onFailure(call: Call<Items?>, t: Throwable) {
                listState.value = Error(null)
                flag = true;

                call.cancel()
            }
        })
    }
}

