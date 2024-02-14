package com.example.skov.item_create

import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewModel : ViewModel() {
    private var category = MutableStateFlow<FEState<ListCategory>>(Loading(null))
    val categoryObserver = category.asStateFlow()

    fun readCategory(){
        val res = SkovService.getInstance().getCategory()
        res.enqueue(object : Callback<ListCategory?>{
            override fun onResponse(call: Call<ListCategory?>, response: Response<ListCategory?>) {
                category.value = Success(response.body())
            }

            override fun onFailure(call: Call<ListCategory?>, t: Throwable) {
                category.value = Error(null)
            }
        })
    }
}