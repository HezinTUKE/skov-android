package com.example.skov.item_create.StepFour

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skov.CheckImageView
import com.example.skov.utils.ImagePickerView

@SuppressLint("MutableCollectionMutableState")
@Composable
fun StepFourView(
    selectedImages : MutableState<ArrayList<Uri?>>
){
    val list_imgs = remember { mutableStateListOf<Uri?>()}

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement =
            if(list_imgs.isNotEmpty()){ Arrangement.Top }
            else{ Arrangement.Center },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ImagePickerView(selectedImages = list_imgs)


        Column(
            modifier = Modifier
                .padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                itemsIndexed(list_imgs) { _, uri ->
                    key(uri) {
                        CheckImageView(uri!!)
                    }
                }

            }
        }

    }
}