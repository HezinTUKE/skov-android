package com.example.skov.registration

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skov.registration.PasswordStep.PasswordView
import com.example.skov.registration.PhoneStep.PhonePage
import com.example.skov.registration.UserStep.UserView
import com.example.skov.ui.theme.Green
import com.example.skov.ui.theme.fontFamilyQuicksand

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RegistrationView(
    onLogin : () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        val phone = remember{
            mutableStateOf("")
        }

        val username = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }

        val name = remember {
            mutableStateOf("")
        }

        val surname = remember {
            mutableStateOf("")
        }

        val email = remember {
            mutableStateOf("")
        }

        val dob = remember {
            mutableStateOf("")
        }

        val pagerState = rememberPagerState(pageCount = {
            3
        })

        Scaffold (

            topBar = {
                TopAppBar(
                    title = { Text("Registration", fontFamily = fontFamilyQuicksand) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Green),
                    navigationIcon = {
                        IconButton(onClick = onLogin) {
                            Icon(Icons.Filled.ArrowBack, null)
                        }
                    }
                )
            },

            content = {

                Column(
                    modifier = Modifier.padding(
                        top = it.calculateTopPadding() + 30.dp,
                        bottom = it.calculateBottomPadding()
                    )
                ) {
                    HorizontalPager(
                        userScrollEnabled = false,
                        state = pagerState,
                    ) { page ->
                        when (page) {
                            0 -> {
                                PhonePage(phone, pagerState)
                            }

                            1 -> {
                                PasswordView(username, password, pagerState)
                            }

                            2 -> {
                                UserView(phone, name, surname, email, dob, username, password)
                            }
                        }
                    }
                }
            }
        )
    }
}