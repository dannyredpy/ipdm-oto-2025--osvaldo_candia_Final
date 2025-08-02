package com.example.goldentickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goldentickets.data.Repository
import com.example.goldentickets.data.models.Reservation
import com.example.goldentickets.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState<String>())
    val uiState: StateFlow<UiState<String>> = _uiState

    private val _reservations = MutableStateFlow<List<Reservation>>(emptyList())
    val reservations: StateFlow<List<Reservation>> = _reservations

    fun reserve(eventId: Int, quantity: Int, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = Repository.createReservation(eventId, 1, quantity)
                if (response.isSuccessful) {
                    _uiState.value = UiState(data = "Reserva exitosa")
                    onResult(true)
                } else {
                    _uiState.value = UiState(error = "Error al reservar")
                    onResult(false)
                }
            } catch (e: Exception) {
                _uiState.value = UiState(error = e.message)
                onResult(false)
            }
        }
    }

    fun loadReservations() {
        viewModelScope.launch {
            try {
                _reservations.value = Repository.getReservations(1)
            } catch (e: Exception) {
                _reservations.value = emptyList()
            }
        }
    }

    fun downloadTicket(reservationId: Int) {
        // Aquí podés abrir el navegador con la URL de tu backend que genera el PDF
    }
}
