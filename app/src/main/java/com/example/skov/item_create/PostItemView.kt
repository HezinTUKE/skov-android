package com.example.skov.item_create

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.login.UserSession
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
        )
    }
}