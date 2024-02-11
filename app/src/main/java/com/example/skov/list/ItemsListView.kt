package com.example.skov.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.skov.list.success_part.SuccessWindow
import com.example.skov.login.UserSession
import com.example.skov.state.*
import kotlinx.coroutines.flow.first

@Composable
fun ListView(
    viewModelItems: ItemsViewModel = viewModel(),
    navHost : NavHostController
) {
    val responseResult by viewModelItems.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(responseResult) {
        val token = UserSession.getAccessToken( context ).first()
        viewModelItems.getList(token)
    }

    when(responseResult){
        is Loading ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp)
                )
            }
        is Success -> {
            SuccessWindow(responseResult.state!!,navHost)
        }

        is Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Error")
            }
        }
        is IsNotAuthenticated -> {
            navHost.navigate("login")
        }
    }
}