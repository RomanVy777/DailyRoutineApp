package com.example.dailyroutineapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dailyroutineapp.controller.RetrofitClient
import com.example.dailyroutineapp.controller.DatabaseHelper
import com.example.dailyroutineapp.controller.utils.SecurityUtils
import com.example.dailyroutineapp.databinding.ActivityLoginBinding
import com.example.dailyroutineapp.model.LoginRequest
import com.example.dailyroutineapp.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        comprobarSesionGuardada()

        // Solo un Listener que llame a la función principal
        binding.btnLogin.setOnClickListener {
            ejecutarLogin()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }

    private fun comprobarSesionGuardada() {
        val prefs = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val emailGuardado = prefs.getString("email", null)
        val passGuardada = prefs.getString("password", null)

        if (emailGuardado != null && passGuardada != null) {
            binding.etEmail.setText(emailGuardado)
            binding.etPassword.setText(passGuardada)
        }
    }

    private fun ejecutarLogin() {
        val email = binding.etEmail.text.toString().trim()
        val passwordPlana = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || passwordPlana.isEmpty()) {
            Toast.makeText(this, "Error: Rellene todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Usamos la encriptación
        val passwordEncriptada = SecurityUtils.encryptPassword(passwordPlana)
        val loginRequest = LoginRequest(email, passwordEncriptada)

        RetrofitClient.instance.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val loginResponse = response.body()

                // Ahora usamos .status que es lo que definimos en el Model
                if (response.isSuccessful && loginResponse?.status == "success") {

                    // SQLite: Registro de acceso
                    val db = DatabaseHelper(this@LoginActivity)
                    db.registrarAcceso(email)

                    guardarPreferencias(email, passwordPlana)

                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else {
                    // Si el PHP devuelve "error", mostramos el mensaje que viene del servidor
                    val mensaje = loginResponse?.message ?: "Credenciales incorrectas"
                    Toast.makeText(this@LoginActivity, mensaje, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Error de conexión: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun guardarPreferencias(email: String, pass: String) {
        val prefs = getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("email", email)
        editor.putString("password", pass)
        editor.apply()
    }
}