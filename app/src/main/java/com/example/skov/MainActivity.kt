package com.example.skov

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.skov.login.LoginView
import com.example.skov.navigation.NavigationView
import com.example.skov.ui.theme.SKOVTheme
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
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    
                    NavigationView(controller = navController)
                }
            }
        }
    }
}