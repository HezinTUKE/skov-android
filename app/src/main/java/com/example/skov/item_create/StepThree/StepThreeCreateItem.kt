package com.example.skov.item_create.StepThree

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.LoadingView
import com.example.skov.type.CategorysView
import com.example.skov.type.TypeViewModel
import com.example.skov.state.Loading
import com.example.skov.state.Success

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StepThreeCreateItem(
    category : Int,
    subcategory: MutableState<Int>,
    pager : PagerState,
    subCategoryViewModel : TypeViewModel = viewModel()
){
    val subcategoryObserver by subCategoryViewModel.subcategoryObserver.collectAsState()

    LaunchedEffect( true ){
        subCategoryViewModel.readSubCategory(category)
    }

    when(subcategoryObserver){
        is Success -> CategorysView(
            type = subcategory,
            types = subcategoryObserver.state!!.subcategorys,
            pager = pager,
            txtHeader = "SubCategory")
        is Error -> Text(text = "Error")
        is Loading -> LoadingView()
        else -> {}
    }

}