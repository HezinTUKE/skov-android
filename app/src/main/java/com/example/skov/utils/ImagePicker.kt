package com.example.skov.utils

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@SuppressLint("UnrememberedMutableState")
@Composable
fun ImagePickerView(
    selectedImages : MutableState<ArrayList<Uri?>>
){
    var list_imgs by mutableStateOf<ArrayList<Uri?>>(arrayListOf())

    Column {

        val pickMediaLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(10),
            onResult = { listUri ->
                if (listUri.isNotEmpty()) {

                    listUri.forEach { uri ->
                        if (!list_imgs.contains(uri)) {
                            list_imgs.add(uri)
                        }
                    }

                    selectedImages.value = list_imgs

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

    }
}