package com.utn.boredapp.models

import kotlinx.serialization.Serializable

@Serializable
data class Actividad(
    val key: String,
    val activity: String,
    val type: String,
    val participants: Int,
    val price: Double,
    val link: String,
    var isFavorite: Boolean = false
)

@Serializable object DestinoLista
@Serializable data class DestinoDetalle(val activityJson: String)
