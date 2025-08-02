package com.example.goldentickets.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.goldentickets.viewmodel.TicketsViewModel

@Composable
fun ReservaScreen(navController: NavController, eventId: Int, viewModel: TicketsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var quantity by remember { mutableStateOf("1") }
    val uiState = viewModel.uiState

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Reserva de Entradas", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = quantity, onValueChange = { quantity = it }, label = { Text("Cantidad") })

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            viewModel.reserve(eventId, quantity.toInt()) { success ->
                if (success) navController.navigate("tickets")
            }
        }) { Text("Confirmar Reserva") }

        if (uiState.error != null) {
            Text("Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
        }
    }
}
