package com.utn.boredapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.utn.boredapp.repository.BoredRepository
import com.utn.boredapp.data.FavoriteStore
import com.utn.boredapp.network.RetrofitClient
import com.utn.boredapp.ui.navigation.AppNavigation
import com.utn.boredapp.ui.theme.BoredAppTheme
import com.utn.boredapp.viewmodel.BoredViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val favoriteStore = FavoriteStore(this)

        val repository = BoredRepository(
            api = RetrofitClient.api,
            store = favoriteStore
        )

        val boredViewModel = BoredViewModel(repository)

        boredViewModel.cargarNuevas()

        setContent {
            BoredAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        viewModel = boredViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}