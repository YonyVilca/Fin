package com.example.f

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inicializar datos en una corrutina
        CoroutineScope(Dispatchers.IO).launch {
            DataInitializer.initializeData(applicationContext)
        }
    }
}
