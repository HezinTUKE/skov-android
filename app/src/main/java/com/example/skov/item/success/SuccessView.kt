package com.example.skov.item.success

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skov.R
import com.example.skov.item.Item
import com.example.skov.item.ItemViewModel
import com.example.skov.like.LikeView
import com.example.skov.state.Error
import com.example.skov.state.IsNotAuthenticated
import com.example.skov.state.Loading
import com.example.skov.state.Success
import com.example.skov.utils.pagers.ImagePreview
import com.example.skov.widgets.buttons.BottomButton

@Composable
fun SuccessView(
    item : Item,
    id : Int,
    itemViewModel: ItemViewModel,
    onList :  () -> Unit
){
    var linesDescription by remember {
        mutableStateOf(false)
    }

    val removeObserver by itemViewModel.removeObserver.collectAsState()

    LaunchedEffect(removeObserver.state){
        when(removeObserver){
            is Loading -> {

            }
            is Success -> {
                if (removeObserver.state!!.code == 1){
                    onList()
                }else{

                }
            }
            is Error -> {
            }
            is IsNotAuthenticated -> {

            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImagePreview(imageList = item.photos)

        HorizontalDivider(color = Color.Black, thickness = 1.dp)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 5.dp, end = 5.dp, top = 15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
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
                if(!item.is_owner){
                    LikeView(id, item.is_liked)
                }
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

        BottomButton(
            onClickAct = {
                if(item.is_owner){
                    itemViewModel.removeItem(id)

                }else {

                }
            },
            content = {
                if(item.is_owner){
                    Text(text = "Delete")
                    Icon(
                        modifier = Modifier.padding(start = 5.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }else{
                    Text(text = "Add")
                    Icon(
                        modifier = Modifier.padding(start = 5.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                    )
                }
            }
        )
    }
}