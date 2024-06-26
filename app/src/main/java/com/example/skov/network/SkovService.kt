package com.example.skov.network

import com.example.skov.item.ItemResponseModel
import com.example.skov.item.RemoveItemModel
import com.example.skov.item_create.PostItemResponse
import com.example.skov.type.TypeModels.ListCategory
import com.example.skov.type.TypeModels.ListSubCategory
import com.example.skov.like.LikeModel
import com.example.skov.list.Items
import com.example.skov.location.LocationListModel
import com.example.skov.login.LoginResponse
import com.example.skov.registration.PasswordStep.Models
import com.example.skov.registration.PhoneStep.PhoneResponse
import com.example.skov.registration.PhoneStep.SMSResponse
import com.example.skov.registration.UserStep.UserResponse
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming
import java.util.concurrent.TimeUnit

interface SkovService {
    companion object {
        private var retrofitService: SkovService? = null
        fun getInstance() : SkovService {
            if (retrofitService == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                val client = OkHttpClient
                    .Builder()
                    .addNetworkInterceptor(interceptor)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(SkovService::class.java)
            }

            return retrofitService!!
        }
    }

    /**
     registration module
    **/
    @Multipart
    @POST("registration/phone")
    fun sendPhone(
        @Part phone : MultipartBody.Part
    ) : Call<PhoneResponse?>

    @Multipart
    @POST("registration/sms")
    fun checkSMS(
        @Part sms : MultipartBody.Part
    ) : Call<SMSResponse?>

    @Multipart
    @POST("registration/private")
    fun sendPrivate(
        @Part username : MultipartBody.Part,
        @Part password : MultipartBody.Part
    ) : Call<Models>

    @Multipart
    @POST("registration/finish")
    fun createUser(
        @Part phone : MultipartBody.Part,
        @Part first_name : MultipartBody.Part,
        @Part last_name : MultipartBody.Part,
        @Part email : MultipartBody.Part,
        @Part date_of_birth : MultipartBody.Part,
        @Part username : MultipartBody.Part,
        @Part password: MultipartBody.Part
    ) : Call<UserResponse?>



    /**
    login/logout module
     **/

    @Multipart
    @POST("authorize")
    fun login(
        @Part login : MultipartBody.Part,
        @Part password : MultipartBody.Part
    ) : Call<LoginResponse?>

    @POST("logout")
    fun logout()

    /**
     list module
     **/

    @GET("items/list/{type}")
    fun getList(
        @Header("Authorization") token: String,
        @Path("type") type : Int
    ) : Call<Items?>
    /**
     item view
     */

    @GET("items/item")
    fun getItem(
      @Query("id") id : Int,
      @Header("Authorization") token: String,
    ) : Call<ItemResponseModel>

    @Multipart
    @POST("items/like")
    fun postLike(
        @Part id : MultipartBody.Part,
        @Part like : MultipartBody.Part
    ) : Call<LikeModel>

    /**
     Type view
     */

    @GET("items/category")
    fun getCategory() : Call<ListCategory>

    @GET("items/subcategory")
    fun getSubCategory(
        @Query("category") id : Int
    ) : Call<ListSubCategory>

    /**
     Location view
     */

    @GET("location/country")
    fun getCountryList() : Call<LocationListModel>

    @GET("location/region")
    fun getRegionList(
        @Query("country_id") country_id : Int
    ) : Call<LocationListModel>

    @GET("location/district")
    fun getDistrictList(
        @Query("region_id") region_id : Int
    ) : Call<LocationListModel>

    /**
        Create Item
    **/

    @Multipart
    @POST("items/item")
    fun postItem(
        @Header("Authorization") token: String,

        @Part category_id : MultipartBody.Part,
        @Part subcategory_id : MultipartBody.Part,
        @Part country_id : MultipartBody.Part,
        @Part region_id : MultipartBody.Part,
        @Part is_active : MultipartBody.Part,
        @Part title : MultipartBody.Part,
        @Part description : MultipartBody.Part,
        @Part price : MultipartBody.Part,
        @Part photos : ArrayList<MultipartBody.Part>,
    ) : Call<PostItemResponse?>

    /**
        Remove Item
    **/

    @DELETE("items/item/{id}")
    fun removeItem(
        @Header("Authorization") token: String,
        @Path("id") id : String
    ) : Call<RemoveItemModel>

    /**
        Create auction
    **/

    @POST("auction/create")
    fun createAuction(
        @Header("Authorization") token: String,
        @Path("id") id : String
    )
}