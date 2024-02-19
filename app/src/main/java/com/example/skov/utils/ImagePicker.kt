package com.example.skov.utils

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun ImagePickerView(
    selectedImages : MutableState<ArrayList<Uri?>>
){
    val pickMediaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(10),
        onResult = {listUri ->
            if(listUri.isNotEmpty()) {
                listUri.forEach { uri ->
                    if (!selectedImages.value.contains(uri)) {
                        selectedImages.value.add(uri)
                    }
                }
            }
        }
    )

    OutlinedButton(onClick = { pickMediaLauncher.launch(
        PickVisualMediaRequest(
            ActivityResultContracts.PickVisualMedia.ImageOnly
        )
    ) }) {
        Text(text = "Select Images")
    }

}