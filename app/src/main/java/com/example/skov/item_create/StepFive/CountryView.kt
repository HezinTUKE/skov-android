package com.example.skov.item_create.StepFive

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.skov.location.Location
import com.example.skov.location.LocationViewModel

@Composable
fun CountryView(
    modelViewLocation : LocationViewModel = viewModel()
){
    val listOfCountry = remember { mutableStateListOf<Location?>() }
    var countryTxt by remember { mutableStateOf("") }

    LaunchedEffect( Unit ){
        modelViewLocation.getLocation()
    }

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = countryTxt,
            onValueChange = {
                countryTxt = it
                listOfCountry.clear()

                if (countryTxt.isNotEmpty()) {
                    val list = modelViewLocation.findLocation(countryTxt.lowercase())

                    listOfCountry.addAll(list)
                }
            },
            maxLines = 1,
            supportingText =
                if (listOfCountry.size > 0) {
                    {
                        LazyColumn(


                        ) {
                            itemsIndexed(listOfCountry){ _, country ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .height(30.dp),
                                    border = BorderStroke(width = 1.dp, color = Color.Black)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Text(
                                            modifier = Modifier.height(25.dp),
                                            text = country!!.name
                                        )
                                        SubcomposeAsyncImage(
                                            modifier = Modifier.size(25.dp),
                                            model = "http://10.0.2.2:8000/media/${country.icon}",
                                            contentDescription = null,
                                        )
                                    }

                                }
                            }
                        }
                    }
                }else{ null }

        )
    }

}