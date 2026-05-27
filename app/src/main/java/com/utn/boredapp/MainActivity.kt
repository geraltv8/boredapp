package com.utn.boredapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.utn.boredapp.ui.navigation.AppNavigation
import com.utn.boredapp.ui.theme.BoredAppTheme
import com.utn.boredapp.viewmodel.BoredViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appContainer = (application as BoredApplication).container
        val boredViewModel = BoredViewModel(appContainer.boredRepository)

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