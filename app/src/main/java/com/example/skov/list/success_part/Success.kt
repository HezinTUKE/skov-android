package com.example.skov.list.success_part

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.skov.list.Items

@Composable
fun SuccessWindow(
    list : Items,
    navHost : NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        if(list.items.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "There are no data yet")
            }
        }else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(150.dp),
                modifier = Modifier.fillMaxSize(),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {

                    itemsIndexed(list.items) { _, item ->
                        Item(item = item, navHost)
                    }
                }
            )
        }
    }
}