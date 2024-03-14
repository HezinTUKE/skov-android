package com.example.skov.item_create

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.FEState
import com.example.skov.state.Loading
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MultipartBody

class CreateItemViewModel : ViewModel() {
    private val itemResponse =  MutableStateFlow<FEState<PostItemResponse>>(Loading(null))
    val itemResponseObserver = itemResponse.asStateFlow()

    fun postItem(
        category_id : Int,
        subcategory_id : Int,
        country_id : Int,
        region_id : Int,
        is_active : Boolean,
        title: String,
        description : String,
        price : Int = 25,
        photos : List<Uri>,
        token : String
    ){
//        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id.toString())
//        val subcategoryPart = MultipartBody.Part.createFormData("subcategory_id", subcategory_id)
//        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id)
//        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id)
//        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id)
//        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id)
//        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id)
//        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id)
//        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id)

        val res = SkovService.getInstance().postItem(
        )
    }
}