package com.example.skov.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.network.SkovService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsViewModel : ViewModel(){
    private val listState = MutableStateFlow<ListState>(Loading(null))
    val state = listState.asStateFlow()

    var flag: Boolean by mutableStateOf(false)

    fun getList(){
        val res = SkovService.getInstance().getList()

        res.enqueue(object : Callback<Items?> {
            override fun onResponse(call: Call<Items?>, response: Response<Items?>) {
                listState.value = Success(response.body())
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

