package com.example.skov.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.skov.list.ListView
import com.example.skov.login.LoginView
import com.example.skov.registration.RegistrationView

@Composable
fun NavigationView(
    controller : NavHostController
){
    NavHost(navController = controller, startDestination = "login"){
        composable("login"){
            LoginView(
                onRegistration = {controller.navigate("registration")},
                onList = {controller.navigate("items")}
            )
        }
        composable("registration"){
            RegistrationView(
                onLogin = {controller.navigate("login")}
            )
        }
        composable("items"){
            ListView()
        }
    }
}