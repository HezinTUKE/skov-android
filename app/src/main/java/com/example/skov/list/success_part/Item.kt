package com.example.skov.list.success_part

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.skov.list.ItemModel
import com.example.skov.utils.ImagePreview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Item(
    item : ItemModel,
    navHost : NavHostController
){
    var url = "http://10.0.2.2:8000/media/";

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    Log.d("IDITEM", item.id.toString())
                    navHost.navigate("item?id=${item.id}")
                }
            ),
        border = BorderStroke(0.dp, Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {

            ImagePreview(imageList = item.photos)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${item.price}€",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                OutlinedCard (
                    modifier = Modifier
                        .padding(top = 3.dp),
                    shape = RectangleShape
                ) {
                    Text(
                        text = item.country
                    )
                }

            }

            Text(
                text = item.title,
                maxLines = 1
            )
        }
    }
}