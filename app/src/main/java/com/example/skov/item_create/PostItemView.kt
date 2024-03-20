package com.example.skov.item_create

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.LoadingView
import com.example.skov.login.UserSession
import com.example.skov.state.Error
import com.example.skov.state.IsNotAuthenticated
import com.example.skov.state.Loading
import com.example.skov.state.Success
import kotlinx.coroutines.flow.first

@Composable
fun PostItemView(
    viewModelCreate: CreateItemViewModel = viewModel(),
    category_id: Int,
    subcategory_id: Int,
    country_id: Int,
    region_id: Int,
    is_active: Boolean,
    title: String,
    description: String,
    price: Int ,
    photos: List<Uri?>
){
    val responseCreate by viewModelCreate.itemResponseObserver.collectAsState()
    val context = LocalContext.current

    LaunchedEffect( Unit ) {
        val token = UserSession.getAccessToken(context).first()
        Log.d("HelloPost", "Here1")
        viewModelCreate.postItem(
            category_id,
            subcategory_id,
            country_id,
            region_id,
            is_active,
            title,
            description,
            price,
            photos,
            token,
            context
        )
    }

    when(responseCreate){
        is Success -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Success")
            }
        }
        is IsNotAuthenticated -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "IsNotAuthenticated")
            }
        }
        is Loading -> {
            LoadingView()
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
    }


}