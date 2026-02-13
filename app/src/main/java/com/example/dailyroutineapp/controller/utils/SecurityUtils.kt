package com.example.dailyroutineapp.controller.utils

import java.security.MessageDigest

object SecurityUtils {
    fun encryptPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        // Devuelve la contraseña en formato hexadecimal encriptado
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}