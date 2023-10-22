package com.example.skov.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.skov.item.ItemView
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
            ListView(
//                onItem = {controller.navigate("item?id=$id")},
                navHost = controller
            )
        }
        composable(
            route = "item?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            val uId = it.arguments?.getInt("id")
            Log.d("UID_", uId.toString())
            ItemView(id = uId!!)
        }
    }
}