package com.example.skov.item_create.StepFour

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.skov.utils.ImagePickerView

@SuppressLint("UnrememberedMutableState")
@Composable
fun StepFourView(
    selectedImages : MutableState<ArrayList<Uri?>>
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImagePickerView(selectedImages = selectedImages)

        LazyHorizontalStaggeredGrid(
            rows = StaggeredGridCells.Fixed(3),
            content = {
                itemsIndexed(selectedImages.value){ idx, uri ->
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = uri,
                        contentDescription = null
                    )
                }
            }
        )

    }
}