package com.example.skov.AppBar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.skov.R
import com.example.skov.item_create.ItemCreateView
import com.example.skov.navigation.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CommonAppBar(
    navHost : NavHostController,
    screen : @Composable() () -> Unit
){
    var openCreate = remember {
        mutableStateOf(false)
    }

    val density = LocalDensity.current
    Scaffold (
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = {
                        navHost.navigate(Routes.LIST.route)
                        openCreate.value = false
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.laptop),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Shop List"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.heart),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Liked Items"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.landlord),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Own Items"
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.comments),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Messages"
                        )
                    }
                    IconButton(onClick = {
                        navHost.navigate(Routes.AUCTION_CREATE.route)
                    }) {
                        Image(
                            painter = painterResource(id = R.drawable.auction),
                            modifier = Modifier.size(25.dp),
                            contentDescription = "Auction"
                        )
                    }
                },

                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            openCreate.value = !openCreate.value
                        },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.add),
                            modifier = Modifier.size(35.dp),
                            contentDescription = "Add"
                        )
                    }
                }

            )
        },
        content = {
            Modifier
                .fillMaxSize()
                .padding(it)
            if(openCreate.value){
                    ItemCreateView(openCreate)
            }else {
                screen()

            }
        }
    )
}