package com.example.dailyroutineapp.model

data class LoginResponse(
    val status: String,   // Cambiamos 'success' por 'status' para que coincida con el JSON del PHP
    val message: String,
    val id_usuario: Int? = null
)