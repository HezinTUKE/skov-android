package com.example.skov.registration.PhoneStep

import android.annotation.SuppressLint
import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.R
import com.example.skov.utils.ErrorCard
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhonePage(
    phoneState : MutableState<String>,
    pager : PagerState,
    viewModel : ViewModelPhone  = viewModel()
){
    val scope = rememberCoroutineScope()

    val respPhone = viewModel.phoneResponse.observeAsState()
    val respSMS = viewModel.smsResponse.observeAsState()

    val v : String = stringResource(R.string.send_verify_btn)
    val v_a : String = stringResource(R.string.send_verify_again_btn)
    
    var smsState by remember {
        mutableStateOf("")
    }

    var showSMSField by remember {
        mutableStateOf(false)
    }

    var showEmptyError by remember {
        mutableStateOf(false)
    }

    var showInvalidPhoneError by remember {
        mutableStateOf(false)
    }

    var textBtn by remember {
        mutableStateOf(v)
    }

    var errorList by remember {
        mutableStateOf(mutableListOf<String>())
    }

    if(respPhone.value?.code == 1){
        errorList.clear()

        showSMSField = true
        textBtn = v_a
    }

    if(respSMS.value?.code == 1){
        errorList.clear()

        scope.launch {
            pager.scrollToPage(
                pager.currentPage + 1
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(respPhone.value?.code == -1 || showInvalidPhoneError){
            errorList.clear()
            errorList = mutableListOf(stringResource(R.string.valid_phone))
        }else if(showEmptyError){
            errorList.clear()
            errorList = mutableListOf(stringResource(R.string.enter_phone))
        }else if(respSMS.value?.code == -1){
            errorList.clear()
            errorList = mutableListOf(stringResource(R.string.valid_sms))
        }else if(respPhone.value?.code == -2){
            errorList.clear()
            errorList = mutableListOf(stringResource(R.string.phone_exists))
        }

        Image(
            painter = painterResource(id = R.drawable.phones),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        ErrorCard(listOfMessages = errorList)

        TextField(
            value = phoneState.value,
            onValueChange = {
                if(it.length <= 13) {
                    phoneState.value = it
                }
            },
            label = {
                Text(stringResource(R.string.phone_number))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )

        if(showSMSField){
            TextField(
                value = smsState,
                onValueChange = {
                    if(it.length <= 5) {
                        smsState = it
                    }
                },
                label = {
                    Text(stringResource(R.string.sms_verify))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Ascii,
                    capitalization = KeyboardCapitalization.Sentences
                )
            )
        }

        OutlinedButton(onClick = {
            if(phoneState.value.isEmpty()){
                showEmptyError = true
                return@OutlinedButton
            }else{
                showEmptyError = false
            }

            if (Patterns.PHONE.matcher(phoneState.value).matches()) {
                showInvalidPhoneError = false

                scope.launch {
                    viewModel.sendPhoneNumber(phoneState.value)
                }
            }else{
                showInvalidPhoneError = true
                Log.d("PhoneNumber", "ERROR")
            }
        }) {
            Text(text = textBtn)
        }

        if(showSMSField) {
            OutlinedButton(
                onClick = {
                    scope.launch {
                        viewModel.checkSMS(smsState)
                    }
                }
            ) {
                Text(text = stringResource(R.string.next_step))
            }
        }

    }
}