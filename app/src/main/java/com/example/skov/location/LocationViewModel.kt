package com.example.skov.location

import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.Error
import com.example.skov.state.FEState
import com.example.skov.state.Loading
import com.example.skov.state.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationViewModel : ViewModel() {

    private var location = MutableStateFlow<FEState<LocationListModel>>(Loading(null))
    val locationObserver = location.asStateFlow()

    /**
     type :
        0 - country
        1 - region
        2 - district
     */

    fun getLocation(
        type : Int = 0,
        id : Int? = null
    ){
        val res =
            when (type) {
                0 -> {
                    SkovService.getInstance().getCountryList()
                }
                1 -> {
                    SkovService.getInstance().getRegionList(id!!)
                }
                else -> {
                    SkovService.getInstance().getDistrictList(id!!)
                }
            }

        res.enqueue(object : Callback<LocationListModel?>{
            override fun onResponse(
                call: Call<LocationListModel?>,
                response: Response<LocationListModel?>
            ) {
                location.value = Success(response.body())
            }

            override fun onFailure(call: Call<LocationListModel?>, t: Throwable) {
                location.value = Error(null)
            }

        })
    }

}