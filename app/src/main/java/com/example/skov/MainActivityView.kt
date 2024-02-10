package com.example.skov

import android.util.Log
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
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.skov.navigation.NavigationView

@Composable
fun MainActivityView(
    viewModelMain : MainActivityViewModel = viewModel(),
    navController : NavHostController
){
    val showBottomBarObserver = viewModelMain.showBottomBar.collectAsState()
    val context = LocalContext.current

    LaunchedEffect( key1 = false ){
        viewModelMain.checkShowBottomBar(context)
        Log.d("ShowAppBar", viewModelMain.showBottomBar.value.toString())
    }

    Scaffold (
        bottomBar = {
            if(showBottomBarObserver.value) {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
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
                            onClick = { /* do something */ },
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
            }
        }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            NavigationView(controller = navController)
        }
    }
}