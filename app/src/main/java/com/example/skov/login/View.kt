package com.example.skov.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skov.R
import com.example.skov.ui.theme.fontFamilyDance
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.utils.ErrorCard
import kotlinx.coroutines.launch

@Composable
fun LoginView(
    onRegistration : () -> Unit,
    onList : () -> Unit,
    viewModel : ViewModelLogin = viewModel()
){

    val loginObserver = viewModel.response.collectAsState()
    val context = LocalContext.current

    var showPassword by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    var errorList by remember {
        mutableStateOf(mutableListOf<String>())
    }

    var username by remember {
        mutableStateOf("vova_hezin")
    }

    var password by remember {
        mutableStateOf("n=MAUcy3")
    }

    if (loginObserver.value?.code == -1) {
        errorList = mutableListOf(stringResource(R.string.wrong_login))
    }

    LaunchedEffect(key1 = loginObserver.value?.code){
        if (loginObserver.value?.code == 1) {
            errorList.clear()
            val token = loginObserver.value?.token

            if(token!!.isNotEmpty()) {
                UserSession.saveToken(context, token)
                Log.d("Logged In", "HERE")

                onList()
            }

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 50.sp,
            fontFamily = fontFamilyDance,
            fontWeight = FontWeight.Bold
        )

        ErrorCard(listOfMessages = errorList)

        TextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text(text = stringResource(R.string.user_name))
            }
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = stringResource(R.string.password))
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
            modifier = Modifier.
                fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(onClick = {
                scope.launch {
                    viewModel.loginUser(
                        username,
                        password
                    )
                }
            }) {
                Text(text = stringResource(R.string.log_in))
            }

            OutlinedButton(
                onClick = onRegistration
            ) {
                Text(text = stringResource(R.string.registration))
            }
        }
    }
}