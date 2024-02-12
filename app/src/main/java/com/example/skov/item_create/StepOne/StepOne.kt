package com.example.skov.item_create.StepOne

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StepOne(
    name : MutableState<String>,
    description : MutableState<String>
){
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name.value,
            onValueChange = {
                if(it.length <= 15)
                    name.value = it
            },
            label = {
                Text(text = "Name")
            }
        )

        TextField(
            modifier = Modifier.width(IntrinsicSize.Min),
            value = description.value,
            onValueChange = {
                if(it.length <= 255)
                    description.value = it
            },
            label = {
                Text(text = "Description")
            },
            minLines = 10
        )
    }
}