package com.example.skov.widgets.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Calendar

@ExperimentalMaterial3Api
@Composable
fun SkovDatePicker(
    minYear : Boolean,
    minTimeMillis : Long?,
    datePickerState : MutableState<DatePickerState?>,
    show : Boolean = true
){

    datePickerState.value = rememberDatePickerState(
        initialDisplayMode = DisplayMode.Input,
        initialSelectedDateMillis = minTimeMillis,
        selectableDates = object : SelectableDates {
            override fun isSelectableYear(year: Int): Boolean {
                return if(!minYear){
                    true
                }else{
                    year >= Calendar.getInstance().get(Calendar.YEAR)
                }
            }
            override fun isSelectableDate(utcTimeMillis: Long) : Boolean {
                return if (minTimeMillis == null){
                    true
                }else {
                    utcTimeMillis >= minTimeMillis
                }

            }
        }
    )

    if(show)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            DatePicker(
                state = datePickerState.value!!,
                colors = DatePickerDefaults.colors(
                    containerColor = Color.Black,
                    todayDateBorderColor = Color.Black,
                    todayContentColor = Color.Black,
                    selectedDayContentColor = Color.White,
                    selectedDayContainerColor = Color.Black
                )
            )
        }
}