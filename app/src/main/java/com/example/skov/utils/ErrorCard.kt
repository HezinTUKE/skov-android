package com.example.skov.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorCard(listOfMessages : List<String>){

    if(listOfMessages.isEmpty()) return

    Card(
        modifier = Modifier
            .fillMaxWidth(0.7f),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(226, 130, 130, 255)
        )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            contentPadding = PaddingValues(top = 5.dp, end = 10.dp, start = 10.dp, bottom = 5.dp)
        ){
            items(listOfMessages){
                msg ->
                    Text(text = "$msg")
            }
        }
    }

}
