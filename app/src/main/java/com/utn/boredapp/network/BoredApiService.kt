package com.utn.boredapp.network

import com.utn.boredapp.Utils.Constant
import com.utn.boredapp.models.Actividad
import retrofit2.http.GET

interface BoredApiService {
    @GET(Constant.ENDPOINT_RANDOM)
    suspend fun getRamdomActivity(): Actividad
}