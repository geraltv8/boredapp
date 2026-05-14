package com.utn.boredapp.network

import com.utn.boredapp.models.Actividad
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

interface BoredApiService {
    @GET("random")
    suspend fun getRamdomActivity(): Actividad
}

object RetrofitClient {
    private val json = Json { ignoreUnknownKeys = true}
    val api: BoredApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://bored-api.appbrewery.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(BoredApiService::class.java)
    }
}