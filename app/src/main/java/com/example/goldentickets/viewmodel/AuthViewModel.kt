package com.example.goldentickets.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goldentickets.data.Repository
import com.example.goldentickets.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState<String>())
    val uiState: StateFlow<UiState<String>> = _uiState

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = Repository.login(email, password)
                if (response.isSuccessful) {
                    _uiState.value = UiState(data = response.body()?.token)
                    onResult(true)
                } else {
                    _uiState.value = UiState(error = "Credenciales inválidas")
                    onResult(false)
                }
            } catch (e: Exception) {
                _uiState.value = UiState(error = e.message)
                onResult(false)
            }
        }
    }

    fun googleLogin(onResult: (Boolean) -> Unit) {
        // Aquí conectarías GoogleSignInClient
        viewModelScope.launch {
            try {
                // Simulación
                val response = Repository.googleLogin("dummy-token")
                if (response.isSuccessful) {
                    _uiState.value = UiState(data = response.body()?.token)
                    onResult(true)
                } else {
                    _uiState.value = UiState(error = "Error con Google OAuth")
                    onResult(false)
                }
            } catch (e: Exception) {
                _uiState.value = UiState(error = e.message)
                onResult(false)
            }
        }
    }
}
