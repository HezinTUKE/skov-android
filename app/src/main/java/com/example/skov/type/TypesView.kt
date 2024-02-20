package com.example.skov.type

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.skov.type.TypeModels.Type
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorysView(
    type : MutableState<Int>,
    types : List<Type>,
    pager : PagerState,
    txtHeader : String
) {
   val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.height(55.dp),
            text = txtHeader
        )
        
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                itemsIndexed(types) { _, categ ->
                    OutlinedButton(
                        onClick = {
                            type.value = categ.id
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (type.value == categ.id) {
                                Color.Black
                            } else {
                                Color.White
                            }
                        )
                    ) {
                        Text(
                            text = categ.name,
                            color = if (type.value == categ.id) {
                                Color.White
                            } else {
                                Color.Black
                            }
                        )
                    }
                }
            }
        )

        OutlinedButton(
            modifier = Modifier.padding(top = 30.dp),
            onClick = {
                coroutine.launch {
                    pager.scrollToPage(pager.currentPage + 1)
                }
            }
        ) {
            Text(text = "Ok")
        }

    }
}