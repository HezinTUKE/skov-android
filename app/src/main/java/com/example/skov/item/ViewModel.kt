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

class ViewModel : ViewModel() {
    private val responseItem = MutableStateFlow<FEState<ItemResponseModel>>(Loading(null))
    val state = responseItem.asStateFlow()

    private val responseRemoveItem = MutableStateFlow<FEState<RemoveItemModel>>(Loading(null))
    val removeObserver = responseRemoveItem.asStateFlow()

    fun removeItem(
        token : String,
        id : Int
    ){
        val item_id = MultipartBody.Part.createFormData("id", id.toString())

        val req = SkovService.getInstance().removeItem(token, item_id)

        req.enqueue(object : Callback<RemoveItemModel> {
            override fun onResponse(
                call: Call<RemoveItemModel>,
                response: Response<RemoveItemModel>
            ) {
//                removeObserver.value = Success(response.body())
            }

            override fun onFailure(call: Call<RemoveItemModel>, t: Throwable) {
//                removeObserver.value = Error(null)
            }
        })
    }

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
                responseItem.value = Success(response.body())
            }

            override fun onFailure(call: Call<ItemResponseModel>, t: Throwable) {
                responseItem.value = Error(null)
            }

        })
    }

}