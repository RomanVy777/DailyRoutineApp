package com.example.dailyroutineapp.controller

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Aquí irá la IP de tu servidor o el dominio de la base de datos externa
    private const val BASE_URL = "http://172.20.10.3/api_rutinas/" // IP especial para el emulador de Android

    val instance: RutinaService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RutinaService::class.java)
    }
}