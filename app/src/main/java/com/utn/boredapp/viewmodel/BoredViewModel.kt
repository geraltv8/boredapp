package com.utn.boredapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utn.boredapp.Utils.Constant
import com.utn.boredapp.repository.BoredRepository
import com.utn.boredapp.models.Actividad
import kotlinx.coroutines.launch

sealed interface BoredUiState {
    object Loading : BoredUiState
    data class Success(val actividades: List<Actividad>) : BoredUiState
    data class Error(val mensaje: String) : BoredUiState
}

class BoredViewModel(private val repository: BoredRepository) : ViewModel() {

    var uiState by mutableStateOf<BoredUiState>(BoredUiState.Loading)
        private set

    private var listaInterna = mutableListOf<Actividad>()

    fun cargarNuevas() {
        viewModelScope.launch {
            uiState = BoredUiState.Loading

            val mantenidas = listaInterna.filter { it.isFavorite }
            val nuevas = mutableListOf<Actividad>()

            try {
                repeat(5 - mantenidas.size) {
                    nuevas.add(repository.fetchNewActivity())
                }
                listaInterna = (mantenidas + nuevas).toMutableList()

                uiState = BoredUiState.Success(listaInterna)
            } catch (e: Exception) {
                uiState = BoredUiState.Error("No se pudieron cargar las actividades. Revisa tu conexión.")
            }
        }
    }

    fun marcarFavorito(actividad: Actividad) {
        listaInterna = listaInterna.map {
            if (it.key == actividad.key) it.copy(isFavorite = !it.isFavorite) else it
        }.toMutableList()

        uiState = BoredUiState.Success(listaInterna)

        viewModelScope.launch {
            val keys = listaInterna.filter { it.isFavorite }.map { it.key }.toSet()
            repository.updateFavorites(keys)
        }
    }
}