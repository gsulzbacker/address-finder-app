package com.gabrielsulzbacker.addressfinder.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabrielsulzbacker.addressfinder.ui.screens.HomeScreen
import com.gabrielsulzbacker.addressfinder.ui.screens.SearchScreen
import com.gabrielsulzbacker.addressfinder.ui.viewmodels.AddressViewModel

@Composable
fun NavGraph(viewModel: AddressViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(navController)
        }
        composable("search") {
            SearchScreen(navController, viewModel)
        }
    }
}