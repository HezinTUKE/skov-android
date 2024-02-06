package com.example.skov.like

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.skov.R
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LikeView(
    id : Int,
    liked : Boolean = false,
    viewModel: LikeViewModel = viewModel()
){
    var likedItem by remember {
        mutableStateOf(liked)
    }

    val likeObserver = viewModel.state.collectAsState()

    Icon(
        painter =
            if(likedItem) painterResource(id = R.drawable.liked_item)
            else painterResource(id = R.drawable.unliked_item),
        modifier = Modifier.run {
            size(30.dp)
                .clickable(
                    onClick = {
                        likedItem = !likedItem

                        viewModel.postLike(
                            id = id,
                            like = likedItem
                        )

                    },
                )
        },
        tint = Color(223, 36, 36, 255),
        contentDescription = null
    )
}