package com.example.skov

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CheckImageView(uri : Uri){
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = uri,
                contentDescription = null
            )
            Image(
                modifier = Modifier
                    .size(15.dp),
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null
            )
        }
    }
}