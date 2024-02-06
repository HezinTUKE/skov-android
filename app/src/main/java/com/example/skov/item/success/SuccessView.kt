package com.example.skov.item.success

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skov.R
import com.example.skov.item.Item
import com.example.skov.like.LikeView
import com.example.skov.utils.pagers.ImagePreview

@Composable
fun SuccessView(
    item : Item,
    id : Int
){
    var linesDescription by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImagePreview(imageList = item.photos)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.price.toString() + "€",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                LikeView(id, item.is_liked)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = item.category,
                        modifier = Modifier.clickable(
                            onClick = {/*TODO: link to category' list */ }
                        ),
                        fontSize = 18.sp,
                        color = Color.Blue
                    )

                    Icon(
                        painter = painterResource(R.drawable.next_category),
                        contentDescription = null
                    )

                    Text(
                        text = item.subcategory,
                        modifier = Modifier.clickable(
                            onClick = {/*TODO: link to subcategory' list*/ }
                        ),
                        fontSize = 18.sp,
                        color = Color.Blue
                    )
                }

                OutlinedCard (
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.Blue)
                ) {
                    Text(
                        modifier = Modifier.padding(2.dp),
                        text = item.country + ", " + item.region
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 5.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Start
            ) {
                Column {
                    Text(
                        modifier = Modifier.clickable(
                            onClick = { linesDescription = !linesDescription }
                        ),
                        text = item.description,
                        maxLines = if (linesDescription) 15 else 3
                    )
                }
            }
        }
    }
}