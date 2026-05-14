package com.utn.boredapp.data

import com.utn.boredapp.models.Actividad
import com.utn.boredapp.network.BoredApiService

class BoredRepository(
    private val api: BoredApiService,
    private val store: FavoriteStore
) {
    //OBTENER INFORMACION DE LA API
    suspend fun fetchNewActivity(): Actividad {
        return api.getRamdomActivity()
    }

    //OBTENER FAVORITOS
    fun getFoviretes() = store.favoritesFlow

    //GUARDAR FAVORITOS
    suspend fun updateFavorites(keys: Set<String>) {
        store.saveFavorites(keys)
    }
}