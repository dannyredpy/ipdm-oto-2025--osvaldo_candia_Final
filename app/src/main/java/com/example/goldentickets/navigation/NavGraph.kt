package com.example.goldentickets.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goldentickets.ui.screens.*

@Composable
fun NavGraph() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("cartelera") { CarteleraScreen(navController) }
        composable("reserva/{eventId}") { backStackEntry ->
            val eventId = backStackEntry.arguments?.getString("eventId") ?: "0"
            ReservaScreen(navController, eventId.toInt())
        }
        composable("tickets") { TicketsScreen(navController) }
    }
}
