package com.example.skov.utils

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier

@Composable
fun ImagePickerView(
    selectedImages : MutableState<ArrayList<Uri?>>,
    list : @Composable() (ArrayList<Uri?>) -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        val pickMediaLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(10),
            onResult = { listUri ->
                if (listUri.isNotEmpty()) {

                    listUri.forEach { uri ->
                        if (!selectedImages.value.contains(uri)) {
                            selectedImages.value.add(uri)
                        }
                    }

                    Log.d("ListURI", selectedImages.value.size.toString())

                }
            }
        )

        OutlinedButton(onClick = {
            pickMediaLauncher.launch(
                PickVisualMediaRequest(
                    ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        }) {
            Text(text = "Select Images")
        }

        list(selectedImages.value)

    }
}