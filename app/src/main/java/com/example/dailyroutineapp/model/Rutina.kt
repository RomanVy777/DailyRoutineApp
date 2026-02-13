package com.example.dailyroutineapp.model

data class Rutina(
    val nombre: String,
    val descripcion: String,
    val hora: String,
    val estado: String, // "Pendiente" o "Completada"
    val tipoIcono: Int  // Aquí pasaremos el ID del drawable
)