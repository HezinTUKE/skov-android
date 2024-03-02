package com.example.skov.location

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.skov.R
import com.example.skov.widgets.buttons.SkovOutlinedButton
import com.example.skov.widgets.textfields.SkovOutlinedTextField

@Composable
fun LocationView(
    locationType : Int,
    idLocation: Int?,
    locationState : MutableState<Int>,
    modelViewLocation : LocationViewModel = viewModel()
){
    var location by remember { mutableStateOf<Location?>(null) }
    val listOfCountry = remember { mutableStateListOf<Location?>() }
    var locationTxt by remember { mutableStateOf("") }

    var change  by remember { mutableStateOf(false) }

    LaunchedEffect( Unit ){
        modelViewLocation.getLocation(locationType, idLocation)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment =
            if (!change){
                Alignment.CenterVertically
            }else{
                Alignment.Top
            },
        horizontalArrangement = Arrangement.Center
    ) {
        SkovOutlinedTextField(
            value = locationTxt,
            onValueChange = {
                locationTxt = it
                listOfCountry.clear()

                location = modelViewLocation.autofillLocation(locationTxt, locationType)

                if (locationTxt.isNotEmpty()) {
                    val list = modelViewLocation.findLocation(locationTxt, locationType)
                    listOfCountry.addAll(list)
                } else if (locationTxt.isEmpty()) {
                    locationState.value = 0
                    location = null
                } else if (location != null){
                    locationTxt = location!!.name
                }
            },
            label = "",
            leadingIcon = {
                if (location == null || locationType >= 1) {
                    Image(
                        painter = painterResource(R.drawable.location),
                        contentDescription = null
                    )
                } else{
                    SubcomposeAsyncImage(
                        modifier = Modifier.size(25.dp),
                        model = "http://10.0.2.2:8000/media/${location!!.icon}",
                        contentDescription = null,
                    )
                }
            },
            trailingIcon = {
                if(location != null) {
                    Image(
                        painter = painterResource(R.drawable.check),
                        contentDescription = null
                    )
                }
            },
            supportingText =
            if (listOfCountry.size > 0) {
                {
                    LazyColumn(
                        modifier = Modifier
                            .width(270.dp)
                            .padding(end = 25.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        itemsIndexed(listOfCountry){ _, country ->
                            Card(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(35.dp)
                                    .align(Alignment.CenterVertically)
                                    .clickable {
                                        location = country
                                        locationTxt = country!!.name
                                        locationState.value = country.id
                                        listOfCountry.clear()
                                    },
                                border = BorderStroke(width = 1.dp, color = Color.Black),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White,
                                ),
                                shape = CutCornerShape(0.dp),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        modifier = Modifier
                                            .height(40.dp),
                                        fontSize = 18.sp,
                                        textAlign = TextAlign.Center,
                                        text = country!!.name,
                                    )
                                    if (locationType == 0) {
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
                }
            }else{ null }
        )
    }

}