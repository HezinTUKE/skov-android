package com.example.skov.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.skov.AppBar.CommonAppBar
import com.example.skov.item.ItemView
import com.example.skov.item_create.ItemCreateView
import com.example.skov.list.ListView
import com.example.skov.login.LoginView
import com.example.skov.registration.RegistrationView

@Composable
fun NavigationView(
    controller : NavHostController
){
    NavHost(navController = controller, startDestination = "items"){
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
            CommonAppBar(controller) {
                ListView(
                    navHost = controller
                )
            }
        }
        composable("create_item"){
            CommonAppBar(controller) {
                ItemCreateView()
            }
        }
        composable(
            route = "item?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ){
            val uId = it.arguments?.getInt("id")
            Log.d("UID_", uId.toString())
            CommonAppBar(controller) {
                ItemView(id = uId!!)
            }
        }
    }
}