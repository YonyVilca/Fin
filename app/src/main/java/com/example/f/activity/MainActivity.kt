package com.example.f.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.f.R
import com.google.android.material.bottomnavigation.BottomNavigationView
// Actividad principal que contiene la navegación de fondo
class MainActivity : AppCompatActivity() {

    private var userName: String = "Usuario" // Valor predeterminado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Obtener el nombre de usuario del Intent
        userName = intent.getStringExtra("USER_NAME") ?: "Usuario"

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
    }
    // Método para obtener el nombre de usuario
    fun getUserName(): String {
        return userName
    }
}


