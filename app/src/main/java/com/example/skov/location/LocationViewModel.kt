package com.example.skov.location

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.skov.network.SkovService
import com.example.skov.state.Error
import com.example.skov.state.FEState
import com.example.skov.state.Loading
import com.example.skov.state.Success
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationViewModel : ViewModel() {

    private var locationCountry = MutableStateFlow<FEState<LocationListModel>>(Loading(null))

    private var locationRegion = MutableStateFlow<FEState<LocationListModel>>(Loading(null))

    private var locationDistrict = MutableStateFlow<FEState<LocationListModel>>(Loading(null))

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
                when (type) {
                    0 -> {
                        locationCountry.value = Success(response.body())
                    }
                    1 -> {
                        locationRegion.value = Success(response.body())
                    }
                    else -> {
                        locationDistrict.value = Success(response.body())
                    }
                }

                Log.d("LocationView", response.body().toString())
            }

            override fun onFailure(call: Call<LocationListModel?>, t: Throwable) {
                when (type) {
                    0 -> {
                        locationCountry.value = Error(null)
                    }
                    1 -> {
                        locationRegion.value = Error(null)
                    }
                    else -> {
                        locationDistrict.value = Error(null)
                    }
                }
            }
        })
    }

    fun getArray(type: Int) : List<Location?>{
        return when (type) {
            0 -> {
                locationCountry.value.state!!.location
            }
            1 -> {
                locationRegion.value.state!!.location
            }
            else -> {
                locationDistrict.value.state!!.location
            }
        }
    }

    fun findLocation(locationInput : String, type : Int) : List<Location?>{
        val listOfHints = ArrayList<Location?>(arrayListOf())
        val locationList = getArray(type)

        locationList.forEach {
            if(it!!.name.lowercase().contains(locationInput.lowercase())) {
                listOfHints.add(it)
            }
        }

        return listOfHints.toList()
    }

    fun autofillLocation(locationInput: String, type : Int) : Location?{
        val locationList = getArray(type)

        var location : Location? = null

        locationList.forEach {
            if(it!!.name.lowercase() == locationInput.lowercase()) {
                location = it
            }
        }

        return location
    }

}