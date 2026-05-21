package com.utn.boredapp.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.utn.boredapp.models.Actividad
import com.utn.boredapp.ui.components.MainColumn

@Composable
fun PantallaDetalle(actividad: Actividad) {
    MainColumn {
        Text(actividad.activity, style = MaterialTheme.typography.headlineSmall)
        Text("Tipo: ${actividad.type}")
        Text("Precio: ${actividad.price}")
        Text("participants: ${actividad.participants}")
        Text("link: ${actividad.link}")
    }
}