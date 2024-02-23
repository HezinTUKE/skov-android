package com.example.skov

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CheckImageView(uri : Uri){
    var checked by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    checked = !checked
                }
            ),
        elevation = CardDefaults.cardElevation(6.dp),
        border = BorderStroke(1.dp, Color.Black),
    ) {
        Box(
            contentAlignment = Alignment.TopStart
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = uri,
                contentDescription = null
            )
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .border(
                        width = 3.dp,
                        color = Color.Black,
                        shape = CircleShape
                    ),
            ) {
                if(checked) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(5.dp)
                            .background(Color.Black, CircleShape),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}