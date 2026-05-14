package com.utn.boredapp.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class FavoriteStore(private val context: Context) {
    private val FAV_KEYS = stringSetPreferencesKey("fav_keys")

    suspend fun saveFavorites(keys: Set<String>) {
        context.dataStore.edit { it[FAV_KEYS] = keys }
    }

    val favoritesFlow: Flow<Set<String>> = context.dataStore.data.map {
        it[FAV_KEYS] ?: emptySet()
    }
}
