package com.example.skov.widgets.datepicker

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

class DateViewModel : ViewModel(){

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTomorrow() : LocalDate{
        return LocalDate.now().plusDays(1)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun convertToTimeStamp(date : LocalDate) : Long{
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond
    }

    @SuppressLint("SimpleDateFormat")
    fun timeStampToDate(timeMillis : Long): String{
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val netDate = Date(timeMillis)
        return sdf.format(netDate)
    }
}