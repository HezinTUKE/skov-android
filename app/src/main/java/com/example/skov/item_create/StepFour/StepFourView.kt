package com.example.skov.item_create.StepFour

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.skov.utils.ImagePickerView

@Composable
fun StepFourView(
    selectedImages : MutableState<ArrayList<Uri?>>
){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImagePickerView(
            selectedImages = selectedImages,
            list = {
                LazyHorizontalStaggeredGrid(
                    rows = StaggeredGridCells.Fixed(3),
                    content = {
                        Log.d("ListURIView", selectedImages.value.size.toString())
                        itemsIndexed(it){ _, uri ->
                            AsyncImage(
                                modifier = Modifier.fillMaxSize(),
                                model = uri,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        )


    }
}