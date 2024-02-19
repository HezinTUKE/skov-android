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
import com.example.skov.type.TypeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.skov.LoadingView
import com.example.skov.state.Loading
import com.example.skov.state.Success
import com.example.skov.type.CategorysView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StepTwoCreateItem(
    category : MutableState<Int>,
    pager : PagerState,
    viewModelCategory : TypeViewModel = viewModel()
) {
    val categoryObserver by viewModelCategory.categoryObserver.collectAsState()

    LaunchedEffect(true) {
        viewModelCategory.readCategory()
        Log.d("Categorys", categoryObserver.toString())
    }

    when(categoryObserver ) {
        is Success -> CategorysView(
            type = category,
            types = categoryObserver.state!!.categorys,
            pager = pager,
            txtHeader = "Category")
        is Error -> Text(text = "Nastala chyba")
        is Loading -> LoadingView()
        else -> {}
    }

}