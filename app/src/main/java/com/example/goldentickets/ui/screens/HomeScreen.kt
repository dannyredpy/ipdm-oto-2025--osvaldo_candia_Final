package com.example.goldentickets.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDate

@Composable
fun HomeScreen(navController: NavController) {
    val today = LocalDate.now()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Golden Tickets") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Bienvenido a Golden Tickets", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            Text("Hoy es $today")

            Spacer(Modifier.height(24.dp))
            Button(onClick = { navController.navigate("cartelera") }) { Text("Ver Cartelera") }
            Spacer(Modifier.height(8.dp))
            Button(onClick = { navController.navigate("tickets") }) { Text("Mis Tickets") }
        }
    }
}
