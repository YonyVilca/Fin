package com.example.f

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.room.Room
import com.example.f.database.AppDatabase
import com.example.f.database.DatabaseBuilder
import com.example.f.database.DataInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inicializar datos en una corrutina
        CoroutineScope(Dispatchers.IO).launch {
            val sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val isDataInitialized = sharedPreferences.getBoolean("data_initialized", false)

            if (!isDataInitialized) {
                DataInitializer.initializeData(applicationContext)
                with(sharedPreferences.edit()) {
                    putBoolean("data_initialized", true)
                    apply()
                }
            }
        }
    }
}
