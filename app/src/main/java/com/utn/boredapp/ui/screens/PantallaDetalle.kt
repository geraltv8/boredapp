package com.utn.boredapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.utn.boredapp.R
import com.utn.boredapp.models.Actividad
import com.utn.boredapp.ui.components.MainColumn

@Composable
fun PantallaDetalle(actividad: Actividad) {
    Column {
        Text(actividad.activity, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text(stringResource(id = R.string.tipo) + ": ${actividad.type}")
        Text(stringResource(id = R.string.precio) + ": ${actividad.price}")
        Text(stringResource(id = R.string.participantes) + ": ${actividad.participants}")
        Text(stringResource(id = R.string.link) + ": ${actividad.link}")
    }
}