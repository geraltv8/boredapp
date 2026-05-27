package com.utn.boredapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.utn.boredapp.R
import com.utn.boredapp.models.DestinoDetalle
import com.utn.boredapp.ui.components.ActividadRow
import com.utn.boredapp.ui.components.MainColumn
import com.utn.boredapp.viewmodel.BoredUiState
import com.utn.boredapp.viewmodel.BoredViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun PantallaLista(navController: NavController, viewModel: BoredViewModel) {
    MainColumn {
        Text("Bored API", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        when (val estado = viewModel.uiState) {
            is BoredUiState.Loading -> {
                CircularProgressIndicator()
            }
            is BoredUiState.Success -> {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(estado.actividades) { act ->
                        ActividadRow(act,
                            onCardClick = { navController.navigate(DestinoDetalle(Json.encodeToString(act))) },
                            onFavClick = { viewModel.marcarFavorito(act) }
                        )
                    }
                }
            }
            is BoredUiState.Error -> {
                Text(text = estado.mensaje, color = Color.Red, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.cargarNuevas() }) { Text("Reintentar") }
            }
        }

        if (viewModel.uiState !is BoredUiState.Loading) {
            Button(onClick = { viewModel.cargarNuevas() }) { Text("Actualizar Lista") }
        }
    }
}