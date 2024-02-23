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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.skov.CheckImageView
import com.example.skov.utils.ImagePickerView

@SuppressLint("UnrememberedMutableState")
@Composable
fun StepFourView(
    selectedImages : MutableState<ArrayList<Uri?>>
){
    val list_imgs = mutableStateOf<ArrayList<Uri?>>(arrayListOf())

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
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
                itemsIndexed(list_imgs.value) { idx, uri ->
                    CheckImageView(uri!!)
                }

            }
        }

    }
}