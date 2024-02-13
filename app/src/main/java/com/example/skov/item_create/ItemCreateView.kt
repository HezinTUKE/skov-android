package com.example.skov.item_create

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.skov.item_create.StepOne.StepOne
import com.example.skov.item_create.StepTwo.StepTwo

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemCreateView(){
    val name = remember{
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }
    val category = remember {
        mutableStateOf(-1)
    }
    val subcategory = remember {
        mutableStateOf(-1)
    }
    val pagerState = rememberPagerState(pageCount = {
        3
    })

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            userScrollEnabled = false,
            state = pagerState,
        ) { page ->
            when(page) {
                0 -> {
                    StepOne(name = name, description = description)
                }

                1 -> {
                    StepTwo(category = category, subcategory = subcategory)
                }

                2 -> {

                }
            }
        }
    }
}