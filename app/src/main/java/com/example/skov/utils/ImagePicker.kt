package com.example.skov.utils

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ImagePickerView(
    selectedImages : MutableList<Uri?>
){
    val list_imgs = remember { mutableStateListOf<Uri?>() }

    Column {

        val pickMediaLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.PickMultipleVisualMedia(10),
            onResult = { listUri ->
                if (listUri.isNotEmpty()) {

                    Log.d("Images Length", selectedImages.size.toString())
                    listUri.forEach { uri ->
                        if (!selectedImages.contains(uri)) {
                            Log.d("Images Length", uri.toString())
                            list_imgs.add(uri)
                        }
                    }
                    selectedImages.addAll(list_imgs)
                    list_imgs.clear()
                }
            }
        )

        IconButton(
            modifier = Modifier.size(35.dp),
            onClick = {
                pickMediaLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                )
            }
        ) {
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Outlined.AddCircle,
                contentDescription = null
            )
        }

    }
}