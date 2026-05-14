package com.utn.boredapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utn.boredapp.data.BoredRepository
import com.utn.boredapp.models.Actividad
import kotlinx.coroutines.launch

class BoredViewModel(private val repository: BoredRepository) : ViewModel() {
    var actividades by mutableStateOf<List<Actividad>>(emptyList())

    fun cargarNuevas() {
        viewModelScope.launch {
            val mantenidas = actividades.filter { it.isFavorite }
            val nuevas = mutableListOf<Actividad>()

            repeat(5 - mantenidas.size) {
                try {
                    nuevas.add(repository.fetchNewActivity())
                } catch (e: Exception) { /* error */}
            }

            actividades = mantenidas + nuevas
        }
    }

    fun marcarFavoritos(actividad: Actividad) {
        actividades = actividades.map {
            if (it.key == actividad.key) it.copy(isFavorite = !it.isFavorite) else it
        }

        viewModelScope.launch {
            val keys = actividades.filter { it.isFavorite }.map { it.key }.toSet()
            repository.updateFavorites((keys))
        }
    }

}