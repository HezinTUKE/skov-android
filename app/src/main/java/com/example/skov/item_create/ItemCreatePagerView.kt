package com.example.skov.item_create

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.skov.item_create.StepSix.StepSixView
import com.example.skov.item_create.StepThree.StepThreeCreateItem
import com.example.skov.item_create.StepTwo.StepTwoCreateItem
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
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

    val postDate = remember {
        mutableStateOf<String?>(null)
    }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        HorizontalPager(
            userScrollEnabled = false,
            state = pagerState,
        ) { page ->
            when(page) {
                0 -> {
                    text.value = "Name your product and describe it"

                    StepOneCreateItem(
                        name = name,
                        description = description,
                        pager = pagerState
                    )
                }

                1 -> {
                    text.value = "Select your product group"

                    StepTwoCreateItem(
                        category = category,
                        pager = pagerState
                    )
                }

                2 -> {
                    text.value = "Select your product brand"

                    StepThreeCreateItem(
                        category = category.value,
                        subcategory = subcategory,
                        pager = pagerState
                    )
                }

                3 -> {
                    text.value = "Attach photos of your product, maxim is 10 photos"

                    StepFourView(
                        selectedImages = selectedImages,
                        pager = pagerState
                    )
                }

                4 -> {
                    text.value = "Select your product location"

                    StepFiveView(
                        country = country,
                        region = region,
                        district = district,
                        pager = pagerState)
                }

                5 -> {
                    text.value = "Select activation date"

                    StepSixView(postDate = postDate)
                }
            }
        }
    }
}