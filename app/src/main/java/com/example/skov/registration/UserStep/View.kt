package com.example.skov.registration.UserStep

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.R
import com.example.skov.utils.ErrorCard
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserView(
    phoneState : MutableState<String>,
    nameState : MutableState<String>,
    surnameState : MutableState<String>,
    emailState : MutableState<String>,
    dobState : MutableState<String>,
    usernameState : MutableState<String>,
    passwordState : MutableState<String>,
    viewModel : ViewModel = viewModel()
){
    
    var errorMsgs by remember{
        mutableStateOf(mutableListOf<String>())
    }

    val focusManager = LocalFocusManager.current

    var showDatePickerDialog by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()

    val observer = viewModel.createResponse.observeAsState()

    val scope = rememberCoroutineScope()

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePickerDialog = false
                focusManager.clearFocus(force = true)
            },
            confirmButton = {
                Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let {
                                dobState.value = it.toEUDateFormat()
                            }
                        showDatePickerDialog = false
                        focusManager.clearFocus(force = true)
                    }) {
                    Text(text = "Ok")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.gender_identity),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        ErrorCard(listOfMessages = errorMsgs)

        TextField(
            value = nameState.value,
            onValueChange = {
                nameState.value = it
            },
            label = {
                Text(stringResource(R.string.name))
            }
        )

        TextField(
            value = surnameState.value,
            onValueChange = {
                surnameState.value = it
            },
            label = {
                Text(stringResource(R.string.surname))
            }
        )

        TextField(
            value = emailState.value,
            onValueChange = {
                emailState.value = it
            },
            label = {
                Text("E-mail")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        TextField(
            value = dobState.value,
            onValueChange = { },
            modifier = Modifier
                .onFocusChanged {
                    if (it.isFocused) {
                        showDatePickerDialog = true
                    }
                },
            label = {
                Text(stringResource(R.string.date_of_birth))
            },
            readOnly = true
        )
        
        OutlinedButton(onClick = {
            scope.launch {
                viewModel.createUser(
                    phoneState.value,
                    nameState.value,
                    surnameState.value,
                    emailState.value,
                    dobState.value,
                    usernameState.value,
                    passwordState.value
                )
            }
        }) {
            Text("Ok")
        }
        
    }
}

fun Long.toEUDateFormat(
    pattern: String = "dd.MM.yyyy"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("en_150")
    ).apply {
        timeZone = TimeZone.getTimeZone("CET")
    }
    return formatter.format(date)
}
