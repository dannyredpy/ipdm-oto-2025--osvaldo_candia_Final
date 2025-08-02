package com.example.goldentickets.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.goldentickets.viewmodel.EventsViewModel
import com.example.goldentickets.data.models.Event

@Composable
fun CarteleraScreen(navController: NavController, viewModel: EventsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val events by viewModel.events.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadEvents()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Cartelera") }) }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(events) { ev ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    onClick = { navController.navigate("reserva/${ev.id}") }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(ev.title, style = MaterialTheme.typography.titleLarge)
                        Text("${ev.category} - ${ev.date} ${ev.time}")
                        Text("Gs. ${ev.price}")
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { navController.navigate("reserva/${ev.id}") }) {
                            Text("Reservar")
                        }
                    }
                }
            }
        }
    }
}
