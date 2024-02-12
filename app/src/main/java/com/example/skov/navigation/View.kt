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
    NavHost(navController = controller, startDestination = Routes.LIST.route){
        composable(Routes.LOGIN.route){
            LoginView(
                onRegistration = {controller.navigate(Routes.REGISTRATION.route)},
                onList = {controller.navigate(Routes.LIST.route)}
            )
        }
        composable(Routes.REGISTRATION.route){
            RegistrationView(
                onLogin = {controller.navigate(Routes.LOGIN.route)}
            )
        }
        composable(Routes.LIST.route){
            CommonAppBar(controller) {
                ListView(
                    navHost = controller
                )
            }
        }
        composable(Routes.ITEM.route){
            CommonAppBar(controller) {
                ItemCreateView()
            }
        }
        composable(
            route = "${Routes.ITEM.route}?id={id}",
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