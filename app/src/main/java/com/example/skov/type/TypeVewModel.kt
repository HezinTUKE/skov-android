package com.example.skov.type

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.skov.type.TypeModels.ListCategory
import com.example.skov.type.TypeModels.ListSubCategory
import com.example.skov.network.SkovService
import com.example.skov.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TypeViewModel : ViewModel() {
    private var category = MutableStateFlow<FEState<ListCategory>>(Loading(null))
    val categoryObserver = category.asStateFlow()

    private var subcategory = MutableStateFlow<FEState<ListSubCategory>>(Loading(null))
    val subcategoryObserver = subcategory.asStateFlow()

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

    fun readSubCategory(id : Int){
        val res = SkovService.getInstance().getSubCategory(id)
        res.enqueue(object : Callback<ListSubCategory?>{
            override fun onResponse(
                call: Call<ListSubCategory?>,
                response: Response<ListSubCategory?>
            ) {
                Log.d("SubCategData", response.body().toString())
                subcategory.value = Success(response.body())
            }

            override fun onFailure(call: Call<ListSubCategory?>, t: Throwable) {
                subcategory.value = Error(null)
            }
        })

    }

}