package com.example.skov.item_create.StepSix

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.widgets.buttons.SkovOutlinedButton
import com.example.skov.widgets.datepicker.DateViewModel
import com.example.skov.widgets.datepicker.SkovDatePicker
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepSixView(
    postDate : MutableState<String?>,
    dateViewModel : DateViewModel = viewModel()
) {
    val datePickerState = remember {
        mutableStateOf<DatePickerState?>(null)
    }

    val (dateDialogEnable, setEnable ) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .toggleable(
                    value = dateDialogEnable,
                    onValueChange = {
                        setEnable(!dateDialogEnable)
                    },
                    role = Role.Checkbox
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = dateDialogEnable,
                onCheckedChange = null,
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Black,
                    checkedColor = Color.Black,
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = "Option selection",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        SkovDatePicker(
            minYear = true,
            minTimeMillis = (dateViewModel.convertToTimeStamp(dateViewModel.getTomorrow())) * 1000,
            initTimeMillis = (dateViewModel.convertToTimeStamp(dateViewModel.getTomorrow()) + 86400) * 1000,
            datePickerState = datePickerState,
            show = dateDialogEnable
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            SkovOutlinedButton(
                text = "Ok",
                onClick = {
                    if (dateDialogEnable) {
                        postDate.value = dateViewModel
                            .timeStampToDate(datePickerState.value!!.selectedDateMillis!!)
                    }else{
                        postDate.value = null
                    }

                    Log.d("DatePickerLog", postDate.value.toString())
                }
            )
        }

    }
}