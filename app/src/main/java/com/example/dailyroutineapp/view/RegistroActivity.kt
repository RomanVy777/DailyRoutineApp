package com.example.dailyroutineapp.view
import com.example.dailyroutineapp.databinding.ActivityRegistroBinding
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dailyroutineapp.R

class RegistroActivity : AppCompatActivity() {

    // 1. Declaramos la variable del binding
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inicializamos el binding
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root) // Cambiamos R.layout por binding.root

        // 3. Usamos binding.root en lugar de findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}