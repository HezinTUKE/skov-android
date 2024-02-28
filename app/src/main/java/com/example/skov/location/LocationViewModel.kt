package com.example.skov.location

import android.util.Log
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
        val res = SkovService.getInstance().getCountryList()
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

                Log.d("LocationView", response.body().toString())
            }

            override fun onFailure(call: Call<LocationListModel?>, t: Throwable) {
                location.value = Error(null)
            }

        })
    }

    fun findLocation(locationInput : String) : List<Location?>{
        val listOfHints = ArrayList<Location?>(arrayListOf())

        location.value.state!!.countrys.forEach {
            if(it.name.lowercase().contains(locationInput)) {
                listOfHints.add(it)
            }
        }

        return listOfHints.toList()
    }

}