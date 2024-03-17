package com.example.skov.item_create

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.Error
import com.example.skov.state.FEState
import com.example.skov.state.IsNotAuthenticated
import com.example.skov.state.Loading
import com.example.skov.state.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class CreateItemViewModel : ViewModel() {
    private val itemResponse =  MutableStateFlow<FEState<PostItemResponse>>(Loading(null))
    val itemResponseObserver = itemResponse.asStateFlow()

    fun postItem(
        category_id: Int,
        subcategory_id: Int,
        country_id: Int,
        region_id: Int,
        is_active: Boolean,
        title: String,
        description: String,
        price: Int = 25,
        photos: List<Uri?>,
        token: String
    ){
        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id.toString())
        val subcategoryPart = MultipartBody.Part.createFormData("subcategory_id", subcategory_id.toString())
        val countryPart = MultipartBody.Part.createFormData("category_id", country_id.toString())
        val regionPart = MultipartBody.Part.createFormData("category_id", region_id.toString())
        val activePart = MultipartBody.Part.createFormData("category_id", is_active.toString())
        val titlePart = MultipartBody.Part.createFormData("category_id", title)
        val descriptionPart = MultipartBody.Part.createFormData("category_id", description)
        val pricePart = MultipartBody.Part.createFormData("category_id", price.toString())
        val photosPart = MultipartBody.Part.createFormData("category_id", photos.toString())

        val res = SkovService.getInstance().postItem(
            "Token $token",
            categoryPart,
            subcategoryPart,
            countryPart,
            regionPart,
            activePart,
            titlePart,
            descriptionPart,
            pricePart,
            photosPart
        )

        res.enqueue(object : Callback<PostItemResponse?>{
            override fun onResponse(
                call: Call<PostItemResponse?>,
                response: Response<PostItemResponse?>
            ) {
                if(response.code() == 401 || response.code() == 405) {
                    itemResponse.value = IsNotAuthenticated(null)
                }else{
                    itemResponse.value = Success(response.body())
                }
            }

            override fun onFailure(call: Call<PostItemResponse?>, t: Throwable) {
                itemResponse.value = Error(null)
            }

        })
    }
}