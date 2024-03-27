package com.example.skov.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.LoadingView
import com.example.skov.item.success.SuccessView
import com.example.skov.login.UserSession
import com.example.skov.state.*
import kotlinx.coroutines.flow.first

@Composable
fun ItemView(
    viewModelItem : ViewModel = viewModel(),
    id : Int
){
    val response by viewModelItem.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(response){
        val token = UserSession.getAccessToken( context ).first()
        viewModelItem.getItem(token, id)
    }

    when(response){
        is Loading ->
            LoadingView()
        is Success ->
            SuccessView(response.state!!.item, id)
        is Error ->
            Text("error")
        is IsNotAuthenticated ->
            Text(text = "")
    }

}