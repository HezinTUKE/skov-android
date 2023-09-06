package com.example.skov.registration.PasswordStep

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.skov.R
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.utils.ErrorCard

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PasswordView(
    usernameState : MutableState<String>,
    passwordState : MutableState<String>,
    pager : PagerState,
    viewModel : ViewModelPrivate = viewModel()
){
    var showPassword by remember {
        mutableStateOf(false)
    }

    var errorMsgs by remember {
        mutableStateOf(mutableListOf<String>())
    }

    val scope = rememberCoroutineScope()

    val privateObserver = viewModel.privateResponse.observeAsState()

    when (privateObserver.value?.code) {
        1 -> {
            scope.launch {
                pager.scrollToPage(
                    pager.currentPage + 1
                )
            }
        }
        -1 -> {
            errorMsgs.clear()
            errorMsgs = mutableListOf(
                    stringResource(R.string.valid_username),
                    stringResource(R.string.pw_req_title),
                    stringResource(R.string.pw_req1),
                    stringResource(R.string.pw_req2),
                    stringResource(R.string.pw_req3),
                    stringResource(R.string.pw_req4)
                )
        }
        -2 -> {
            errorMsgs.clear()
            errorMsgs = mutableListOf(stringResource(R.string.username_exist))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.protection),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        ErrorCard(listOfMessages = errorMsgs)
        
        TextField(
            value = usernameState.value,
            onValueChange = {
                usernameState.value = it
            },
            label = {
                Text(stringResource(R.string.user_name))
            }
        )

        TextField(
            value = passwordState.value,
            onValueChange = {
                passwordState.value = it
            },
            label = {
                Text(stringResource(R.string.password))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if(!showPassword)
                                        PasswordVisualTransformation()
                                    else VisualTransformation.None,
            trailingIcon = {
                val icon = if(!showPassword){
                    Icon(
                        painter = painterResource(R.drawable.outline_visibility),
                        contentDescription = null
                    )
                }else{
                    Icon(
                        painter = painterResource(R.drawable.outline_invisibility),
                        contentDescription = null
                    )
                }

                IconButton(onClick = { showPassword = !showPassword }) {
                    icon
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(onClick = {
                scope.launch {
                    pager.scrollToPage(
                        pager.currentPage - 1
                    )
                }
            }) {
                Text(stringResource(R.string.back))
            }
            OutlinedButton(onClick = {
                scope.launch {
                    viewModel.createPrivateUser(
                        usernameState.value,
                        passwordState.value
                    )
                }
            }) {
                Text(stringResource(R.string.next_step))
            }
        }

    }
}