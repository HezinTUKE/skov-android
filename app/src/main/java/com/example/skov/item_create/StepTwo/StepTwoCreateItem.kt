package com.example.skov.item_create.StepTwo

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.skov.item_create.CategoryViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.LoadingView
import com.example.skov.state.Loading
import com.example.skov.state.Success

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StepTwoCreateItem(
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

    when(categoryObserver ) {
        is Success -> CategorysView(category = category, categorys = categoryObserver.state!!.categorys)
        is Error -> Text(text = "Nastala chyba")
        is Loading -> LoadingView()
        else -> {}
    }

}