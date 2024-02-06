package com.example.skov.item

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.item.state.Error
import com.example.skov.item.state.Loading
import com.example.skov.item.state.Success
import com.example.skov.item.success.SuccessView
import com.example.skov.list.state.IsNotAuthenticated

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
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp)
                )
            }
        is Success ->
            SuccessView(response.response!!.item, id)
        is Error ->
            Text("error")
    }

}