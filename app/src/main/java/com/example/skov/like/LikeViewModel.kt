package com.example.skov.like

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeViewModel : ViewModel() {
    private val responseLike = MutableStateFlow<FEState<LikeModel>>(Loading(null))
    val state = responseLike.asStateFlow()

    fun postLike(id : Int, like : Boolean){
        val idForm = MultipartBody.Part.createFormData("item_id", id.toString())
        val likeForm = MultipartBody.Part.createFormData("like", if(like) "1" else "0")

        val req = SkovService.getInstance().postLike(idForm, likeForm)

        req.enqueue(object : Callback<LikeModel>{
            override fun onResponse(call: Call<LikeModel>, response: Response<LikeModel>) {
                responseLike.value = Success(response.body())
                Log.d("LIKE RESPONSE", responseLike.value.toString())
            }
            override fun onFailure(call: Call<LikeModel>, t: Throwable) {
                responseLike.value = Error(null)
                call.cancel()
            }
        })
    }
}