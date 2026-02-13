package com.example.dailyroutineapp.controller

import com.example.dailyroutineapp.model.Rutina
import com.example.dailyroutineapp.model.LoginRequest  // Importante
import com.example.dailyroutineapp.model.LoginResponse // Importante
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RutinaService {

    // --- PARTE 1: AUTENTICACIÓN (Lo que te falta para el error del Login) ---
    @POST("login.php")
    fun login(@Body request: LoginRequest): Call<LoginResponse>


    // --- PARTE 2: REGISTRO DE RUTINAS (Actualizado para compatibilidad) ---
    @POST("crear_rutina.php")
    fun insertarRutina(@Body rutina: Rutina): Call<ResponseBody>

    //Regsitro
    @POST("registro.php")
    fun registro(@Body request: LoginRequest): Call<LoginResponse>

    // --- PARTE 3: LISTADO (Para el Recycler/CardView) ---
    @GET("obtener_rutinas.php")
    fun obtenerRutinas(): Call<List<Rutina>>
}