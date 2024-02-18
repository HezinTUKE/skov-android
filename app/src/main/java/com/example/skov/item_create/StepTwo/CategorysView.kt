package com.example.skov.item_create.StepTwo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.skov.item_create.Type
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategorysView(
    category : MutableState<Int>,
    categorys : List<Type>,
    pager : PagerState
) {
   val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            content = {
                itemsIndexed(categorys) { _, categ ->
                    OutlinedButton(
                        onClick = {
                            category.value = categ.id
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (category.value == categ.id) {
                                Color.Black
                            } else {
                                Color.White
                            }
                        )
                    ) {
                        Text(
                            text = categ.name,
                            color = if (category.value == categ.id) {
                                Color.White
                            } else {
                                Color.Black
                            }
                        )
                    }
                }
            }
        )

        OutlinedButton(onClick = {
            coroutine.launch {
                pager.scrollToPage(pager.currentPage + 1)
            }
        }) {
            Text(text = "Ok")
        }

    }
}