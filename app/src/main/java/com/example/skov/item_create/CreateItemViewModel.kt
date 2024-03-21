package com.example.skov.item_create

import android.content.Context
import android.net.Uri
import android.os.Build
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.Error
import com.example.skov.state.FEState
import com.example.skov.state.IsNotAuthenticated
import com.example.skov.state.Loading
import com.example.skov.state.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.InputStream


class CreateItemViewModel : ViewModel() {
    private val itemResponse =  MutableStateFlow<FEState<PostItemResponse>>(Loading(null))
    private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    val itemResponseObserver = itemResponse.asStateFlow()

    private fun randomStringByKotlinCollectionRandom() = List(10) {
        charPool.random()
    }.joinToString("")

    @RequiresApi(Build.VERSION_CODES.R)
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
        token: String,
        context : Context
    ){
        val categoryPart = MultipartBody.Part.createFormData("category_id", category_id.toString())
        val subcategoryPart = MultipartBody.Part.createFormData("subcategory_id", subcategory_id.toString())
        val countryPart = MultipartBody.Part.createFormData("country_id", country_id.toString())
        val regionPart = MultipartBody.Part.createFormData("region_id", region_id.toString())
        val activePart = MultipartBody.Part.createFormData("is_active", if (is_active) "True" else "False")
        val titlePart = MultipartBody.Part.createFormData("title", title)
        val descriptionPart = MultipartBody.Part.createFormData("description", description)
        val pricePart = MultipartBody.Part.createFormData("price", price.toString())
        val photosPart = ArrayList<MultipartBody.Part>()

        val resolver = context.contentResolver
        val mime = MimeTypeMap.getSingleton()

        photos.forEach {uri ->
            val inputStream : InputStream? = resolver.openInputStream(uri!!)

            val type = mime.getExtensionFromMimeType(resolver.getType(uri))

            val body = RequestBody.create("image/${type}".toMediaTypeOrNull(), inputStream!!.readBytes())

            photosPart.add(
                MultipartBody.Part.createFormData("photos",
                    randomStringByKotlinCollectionRandom() + ".${type}",
                    body
                )
            )
        }

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
                Log.d("Error", call.toString())
                Log.d("Error", t.toString())

                itemResponse.value = Error(null)
            }

        })
    }
}