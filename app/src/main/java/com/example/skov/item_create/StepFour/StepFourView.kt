package com.example.skov.item_create.StepFour

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.example.skov.utils.ImagePickerView

@Composable
fun StepFourView(
    selectedImages : MutableState<ArrayList<Uri?>>
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ImagePickerView(selectedImages = selectedImages)
    }
}