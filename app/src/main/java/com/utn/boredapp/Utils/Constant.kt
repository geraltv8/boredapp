package com.utn.boredapp.Utils

object Constant {
    // Configuración de Red
    const val BASE_URL = "https://bored-api.appbrewery.com/$ENDPOINT_RANDOM"
    const val ENDPOINT_RANDOM = "random"
    const val CONTENT_TYPE = "application/json"

    // Configuración de Persistencia
    const val DATASTORE_NAME = "settings"
    const val PREF_FAV_KEYS = "fav_keys"

    // Límites y Reglas de Negocio
    const val CANTIDAD_ACTIVIDADES = 5
}