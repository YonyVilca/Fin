package com.example.f

import android.app.Application
import com.example.f.database.DataInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
// Clase Application para inicializar datos al iniciar la aplicación
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inicializar datos en una corrutina
        CoroutineScope(Dispatchers.IO).launch {
            DataInitializer.initializeData(applicationContext)
        }
    }
}
