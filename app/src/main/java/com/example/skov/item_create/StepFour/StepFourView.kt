package com.example.skov.item_create.StepFour

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.skov.CheckImageView
import com.example.skov.utils.ImagePickerView
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StepFourView(
    selectedImages : MutableList<Uri?>,
    pager : PagerState
){
    val list_imgs = remember { mutableStateListOf<Uri?>()}
    val remove_list_imgs = remember { mutableStateListOf<Uri?>()}

    val coroutine = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement =
            if(list_imgs.isNotEmpty()){ Arrangement.spacedBy(6.dp) }
            else{ Arrangement.Center },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            ImagePickerView(selectedImages = list_imgs)
            if (remove_list_imgs.isNotEmpty()){
                IconButton(
                    modifier = Modifier.size(35.dp),
                    onClick = {
                        list_imgs.removeAll(remove_list_imgs)
                        remove_list_imgs.clear()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(35.dp),
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null
                    )
                }
            }
        }

        if(list_imgs.isNotEmpty()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .fillMaxWidth()
                        .padding(3.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    border = BorderStroke(3.dp, Color.Black),
                ) {

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 128.dp),
                        verticalArrangement = Arrangement.spacedBy(3.dp),
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        itemsIndexed(list_imgs) { _, uri ->
                            key(uri) {
                                CheckImageView(
                                    uri = uri!!,
                                    removeList = remove_list_imgs
                                )
                            }
                        }
                    }
                }
                
                OutlinedButton(onClick = {
                    selectedImages.addAll(list_imgs)
                    coroutine.launch {
                        pager.scrollToPage(pager.currentPage + 1)
                    }
                }) {
                    Text(text = "Ok")
                }
                
            }
        }

    }
}