package com.example.skov.item_create

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemCreateView(
    openCreate : MutableState<Boolean>
){
    var expanded by remember {
        mutableStateOf(false)
    }

    val pagerState = rememberPagerState(pageCount = {
        7
    })

    val text = remember {
        mutableStateOf("")
    }

    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        ),
        shape = RectangleShape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .width(280.dp)
                    .padding(start = 15.dp, top = 15.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .verticalScroll(rememberScrollState())
                    .clickable {
                        expanded = !expanded
                    },
                text = text.value ,
                maxLines = if(!expanded) 1 else 10,
                color = Color.White

            )
            IconButton(onClick = { openCreate.value = false },
                modifier = Modifier
                    .padding(top = 25.dp, end = 25.dp, start = 15.dp)
                    .size(25.dp)
                    .border(3.dp, Color.White, shape = CircleShape)
            ){
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(25.dp),
        ) {
            ItemCreatePagerView(
                pagerState,
                text
            )
        }
    }
}

