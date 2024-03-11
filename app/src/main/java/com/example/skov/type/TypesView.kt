package com.example.skov.type

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.skov.widgets.buttons.SkovOutlinedButton
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
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.height(55.dp),
            text = txtHeader
        )

        Card(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .padding(3.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(125.dp),
                contentPadding = PaddingValues(16.dp),
                verticalItemSpacing = 16.dp,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    itemsIndexed(types) { _, categ ->
                        SkovOutlinedButton(
                            onClick = {
                                type.value = categ.id
                            },
                            text = categ.name,
                            isClicked = type.value == categ.id
                        )
                    }
                }
            )
        }
        
        Spacer(modifier = Modifier.height(30.dp))

        SkovOutlinedButton(
            onClick = {
                coroutine.launch {
                    pager.scrollToPage(pager.currentPage + 1)
                }
            },
            text = "Ok"
        )

    }
}