package com.example.skov.item_create.StepOne

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.skov.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StepOneCreateItem(
    name : MutableState<String>,
    pager : PagerState,
    description : MutableState<String>
){
    Column(
        modifier = Modifier
            .fillMaxHeight(0.8f)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutine = rememberCoroutineScope()

        Text(text = "Nazov")
        TextField(
            modifier = Modifier.width(300.dp),
            value = name.value,
            onValueChange = {
                if (it.length <= 15)
                    name.value = it
            },
            label = {
                Text(text = "Name")
            }
        )

        Text(text = "Description")

        TextField(
            modifier = Modifier.width(300.dp),
            value = description.value,
            onValueChange = {
                if (it.length <= 255)
                    description.value = it
            },
            label = {
                Text(text = "Description")
            },
            minLines = 10
        )

        Row {
           OutlinedButton(onClick = {
               coroutine.launch {
                   pager.scrollToPage(pager.currentPage + 1) }
               }
           ) {
               Text(text = stringResource(R.string.next_step))
           }
        }
    }
}