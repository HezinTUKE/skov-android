package com.example.skov.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.example.skov.R
import com.example.skov.widgets.textfields.SkovOutlinedTextField

@OptIn(ExperimentalMaterial3Api::class)
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
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        ExposedDropdownMenuBox(
            expanded = listOfCountry.isNotEmpty(),
            onExpandedChange = {
                listOfCountry.clear()
            }
        ){

            OutlinedTextField(
                modifier = Modifier.menuAnchor(),
                value = locationTxt,
                onValueChange = {
                    locationTxt = it
                    listOfCountry.clear()
                    change = true
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
                leadingIcon = {
                    if (location == null || locationType >= 1) {
                        Image(
                            painter = painterResource(R.drawable.location),
                            contentDescription = null
                        )
                    } else {
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
            )
            if(listOfCountry.isNotEmpty()){
                ExposedDropdownMenu(
                    modifier = Modifier.background(Color.Black),
                    expanded = true,
                    onDismissRequest = { listOfCountry.clear() },
                ) {
                    listOfCountry.forEach { country ->
                        DropdownMenuItem(
                            modifier = Modifier.background(Color.White),
                            text = { Text(country!!.name) },
                            onClick = {
                                location = country
                                locationTxt = country!!.name
                                locationState.value = country.id
                                listOfCountry.clear()
                            },
                            leadingIcon ={
                                SubcomposeAsyncImage(
                                    modifier = Modifier.size(25.dp),
                                    model = "http://10.0.2.2:8000/media/${country!!.icon}",
                                    contentDescription = null,
                                )
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            colors = MenuDefaults.itemColors(
                                textColor = Color.Black
                            )
                        )
                        HorizontalDivider(
                            color = Color.Black,
                        )
                    }
                }
            }
        }
    }

}
