package com.example.skov.item

import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.FEState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.skov.state.*

class ItemViewModel : ViewModel() {
    private val responseItem = MutableStateFlow<FEState<ItemResponseModel>>(Loading(null))
    val state = responseItem.asStateFlow()

    private val responseRemoveItem = MutableStateFlow<FEState<RemoveItemModel>>(Loading(null))
    val removeObserver = responseRemoveItem.asStateFlow()

    private var token : String? = null

    fun setToken(token: String){
        this.token = "Token $token"
    }

    fun removeItem(
        id : Int
    ){
        val req = SkovService.getInstance().removeItem(this.token!!, id.toString())

        req.enqueue(object : Callback<RemoveItemModel> {
            override fun onResponse(
                call: Call<RemoveItemModel>,
                response: Response<RemoveItemModel>
            ) {
                responseRemoveItem.value = Success(response.body())

                if(response.code() == 401 || response.code() == 405) {
                    responseRemoveItem.value = IsNotAuthenticated(null)
                }
            }

            override fun onFailure(call: Call<RemoveItemModel>, t: Throwable) {
                responseRemoveItem.value = Error(null)
            }
        })
    }

    fun getItem(
        id : Int
    ){
        val req = SkovService.getInstance().getItem(id, this.token!!)

        req.enqueue(object : Callback<ItemResponseModel> {
            override fun onResponse(
                call: Call<ItemResponseModel>,
                response: Response<ItemResponseModel>
            ) {
                responseItem.value = Success(response.body())
            }

            override fun onFailure(call: Call<ItemResponseModel>, t: Throwable) {
                responseItem.value = Error(null)
            }

        })
    }

}