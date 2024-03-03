package com.example.skov.item_create

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skov.item_create.StepFive.StepFiveView
import com.example.skov.item_create.StepFour.StepFourView
import com.example.skov.item_create.StepOne.StepOneCreateItem
import com.example.skov.item_create.StepThree.StepThreeCreateItem
import com.example.skov.item_create.StepTwo.StepTwoCreateItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemCreatePagerView(
    pagerState : PagerState,
    text : MutableState<String>
){
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        HorizontalPager(
            userScrollEnabled = false,
            state = pagerState,
        ) { page ->
            when(page) {
                0 -> {
                    text.value = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged"
                    StepOneCreateItem(
                        name = name,
                        description = description,
                        pager = pagerState
                    )
                }

                1 -> {
                    text.value = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged"

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