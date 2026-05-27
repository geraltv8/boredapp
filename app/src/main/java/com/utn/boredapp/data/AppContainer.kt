package com.utn.boredapp.data

import android.content.Context
import com.utn.boredapp.network.RetrofitClient
import com.utn.boredapp.repository.BoredRepository

class AppContainer(context: Context) {
    val favoriteStore = FavoriteStore(context)
    val boredRepository = BoredRepository(api = RetrofitClient.api, store = favoriteStore)
}