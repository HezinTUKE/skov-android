package com.example.skov.item_create

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.skov.item_create.StepFive.StepFiveView
import com.example.skov.item_create.StepFour.StepFourView
import com.example.skov.item_create.StepOne.StepOneCreateItem
import com.example.skov.item_create.StepThree.StepThreeCreateItem
import com.example.skov.item_create.StepTwo.StepTwoCreateItem

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

    val country = remember {
        mutableStateOf(0)
    }

    val region = remember {
        mutableStateOf(0)
    }

    val district = remember {
        mutableStateOf(0)
    }

    val selectedImages = remember {
        mutableStateListOf<Uri?>()
    }

    val pagerState = rememberPagerState(pageCount = {
        5
    })

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            userScrollEnabled = false,
            state = pagerState,
        ) { page ->
            when(page) {
                0 -> {
                    StepOneCreateItem(
                        name = name,
                        description = description,
                        pager = pagerState
                    )
                }

                1 -> {
                    StepTwoCreateItem(
                        category = category,
                        pager = pagerState
                    )
                }

                2 -> {
                    StepThreeCreateItem(
                        category = category.value,
                        subcategory = subcategory,
                        pager = pagerState
                    )
                }

                3 -> {
                    StepFourView(
                        selectedImages = selectedImages,
                        pager = pagerState
                    )
                }

                4 -> {
                    StepFiveView(
                        country = country,
                        region = region,
                        district = district,
                        pager = pagerState)
                }
            }
        }
    }
}