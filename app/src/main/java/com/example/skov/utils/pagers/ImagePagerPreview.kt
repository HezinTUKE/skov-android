package com.example.skov.utils.pagers

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagePagerPreview(
    imageList : List<String>
){
    val pagerState = rememberPagerState( pageCount = {
        imageList.size
    })

    val urlMedia = "http://10.0.2.2:8000/media/";

    HorizontalPager(
        state = pagerState,
    ) {page ->
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("${urlMedia}${imageList[page]}")
                .crossfade(true)
                .build(),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )
    }
}