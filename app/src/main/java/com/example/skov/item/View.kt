package com.example.skov.item

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.LoadingView
import com.example.skov.item.state.Error
import com.example.skov.item.state.Loading
import com.example.skov.item.state.Success
import com.example.skov.item.success.SuccessView

@Composable
fun ItemView(
    viewModelItem : ViewModel = viewModel(),
    id : Int
){
    val response by viewModelItem.state.collectAsState()

    LaunchedEffect(response){
        viewModelItem.getItem(id)
    }

    when(response){
        is Loading ->
            LoadingView()
        is Success ->
            SuccessView(response.response!!.item, id)
        is Error ->
            Text("error")
    }

}