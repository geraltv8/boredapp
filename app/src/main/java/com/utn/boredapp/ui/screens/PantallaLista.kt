package com.utn.boredapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.utn.boredapp.R
import com.utn.boredapp.models.DestinoDetalle
import com.utn.boredapp.ui.components.MainColumn
import com.utn.boredapp.viewmodel.BoredViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun PantallaLista(navController: NavController, viewModel: BoredViewModel) {
    Column {
        Text(
            stringResource(id = R.string.mis_actividades),
            style = MaterialTheme.typography.headlineMedium
        )

        if (viewModel.isLoading) CircularProgressIndicator()

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.actividades) { act ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                    navController.navigate(
                        DestinoDetalle(Json.encodeToString(act)
                            )
                        )
                    }
                ) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(act.activity, Modifier.weight(1f))
                        IconButton(onClick = { viewModel.marcarFavoritos(act) }) {
                            Icon(
                                imageVector = if (act.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                tint = if (act.isFavorite) Color.Red else Color.Gray
                            )
                        }
                    }
                }
            }
        }
        Button(onClick = {
            viewModel.cargarNuevas()
        }) { Text(stringResource(id = R.string.actualizar)) }
    }
}