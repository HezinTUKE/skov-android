package com.example.skov.AppBar

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.skov.R
import com.example.skov.navigation.Routes

@Composable
fun CommonAppBar(
    navHost : NavHostController,
    screen : @Composable() () -> Unit
){
    Scaffold (
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { navHost.navigate(Routes.LIST.route) }) {
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
                },

                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { navHost.navigate(Routes.ITEM_CREATE.route) },
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
            Modifier.fillMaxSize().padding(it)

            screen()
        }
    )
}