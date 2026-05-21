package com.utn.boredapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.utn.boredapp.models.Actividad
import com.utn.boredapp.models.DestinoDetalle
import com.utn.boredapp.models.DestinoLista
import com.utn.boredapp.ui.screens.PantallaDetalle
import com.utn.boredapp.ui.screens.PantallaLista
import com.utn.boredapp.viewmodel.BoredViewModel
import kotlinx.serialization.json.Json

@Composable
fun AppNavigation(viewModel: BoredViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = DestinoLista,
        modifier = modifier)
    {
        composable<DestinoLista> {
            PantallaLista(navController, viewModel)
        }
        composable<DestinoDetalle> { backStackEntry ->
            val route = backStackEntry.toRoute<DestinoDetalle>()
            val actividad = Json.decodeFromString<Actividad>(route.activityJson)
            PantallaDetalle(actividad)
        }
    }
}

