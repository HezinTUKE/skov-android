package com.example.skov.item_create.StepTwo

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.skov.item_create.CategoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.item_create.ListCategory
import com.example.skov.state.Success

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StepTwo(
    category : MutableState<Int>,
    subcategory : MutableState<Int>,
    pager : PagerState,
    viewModelCategory : CategoryViewModel = viewModel()
) {
    val categoryObserver by viewModelCategory.categoryObserver.collectAsState()

    val categoryList = remember {
        mutableListOf("")
    }

    LaunchedEffect(true) {
        viewModelCategory.readCategory()
        Log.d("Categorys", categoryObserver.toString())
    }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        if (categoryObserver is Success) {
            CategorysView(category = category, categorys = categoryObserver.state!!.categorys)
        }
    }

}