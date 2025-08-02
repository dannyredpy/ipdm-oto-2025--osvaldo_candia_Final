package com.example.goldentickets.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.goldentickets.viewmodel.TicketsViewModel
import com.example.goldentickets.data.models.Reservation

@Composable
fun TicketsScreen(navController: NavController, viewModel: TicketsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val reservations by viewModel.reservations.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadReservations()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis Tickets") }) }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(reservations) { res ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Evento: ${res.eventTitle}")
                        Text("Cantidad: ${res.quantity}")
                        Text("Fecha: ${res.date}")
                        Button(onClick = { viewModel.downloadTicket(res.id) }) {
                            Text("Descargar PDF")
                        }
                    }
                }
            }
        }
    }
}
