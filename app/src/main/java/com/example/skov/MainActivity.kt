package com.example.skov

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.skov.login.UserSession
import com.example.skov.navigation.NavigationView
import com.example.skov.ui.theme.SKOVTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val config = resources.configuration
        val locale = Locale("sk")
        Locale.setDefault(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            config.setLocale(locale)
        else
            config.locale = locale

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)

        setContent {
            SKOVTheme {
                val navController = rememberNavController()
                val context = LocalContext.current

                var showBottomBar by remember {
                    mutableStateOf(false)
                }

                LaunchedEffect(key1 = showBottomBar ) {
                    showBottomBar = UserSession.getAccessToken(context).first().isNotEmpty()
                }

                Scaffold (
                    bottomBar = {
                        if(showBottomBar) {
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
                // A surface container using the 'background' color from the theme
            }
        }
    }
}