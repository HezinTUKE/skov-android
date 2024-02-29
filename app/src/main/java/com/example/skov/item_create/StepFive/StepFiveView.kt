package com.example.skov.item_create.StepFive

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skov.location.LocationView

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StepFiveView(
    country : MutableState<Int>,
    region : MutableState<Int>,
    district : MutableState<Int>,
    pager: PagerState
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LocationView(
            locationType = 0,
            locationState = country,
            idLocation = null
        )
        if (country.value > 0){
            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )
            
            LocationView(
                locationType = 1,
                locationState = region,
                idLocation = country.value
            )
        }

        if (region.value > 0 && country.value > 0){
            Spacer(
                modifier = Modifier
                    .height(25.dp)
            )

            LocationView(
                locationType = 2,
                locationState = district,
                idLocation = region.value
            )
        }

    }
}