package com.example.f.database

import android.content.Context
import android.util.Log
import com.example.f.model.Product
import com.example.f.model.User
import com.example.f.repository.ProductRepository
import com.example.f.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Inicializador de datos para poblar la base de datos con datos de ejemplo
object DataInitializer {

    // Método para inicializar los datos en la base de datos
    fun initializeData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("DataInitializer", "Inicializando datos...")

            // Llenar la tabla de usuarios
            val users = listOf(
                User(email = "user1@example.com", password = "password1"),
                User(email = "user2@example.com", password = "password2")
            )
            users.forEach { UserRepository.registerUser(context, it) }

            // Limpiar la tabla de productos antes de insertar nuevos productos
            val db = DatabaseBuilder.getInstance(context)
            db.productDao().clearTable()

            // Llenar la tabla de productos
            val products = listOf(
                Product(name = "Paracetamol 500mg", price = 5.0, imageUrl = "https://dcuk1cxrnzjkh.cloudfront.net/imagesproducto/108218L.jpg", description = "Analgesico y antipiretico", sales = 100, quantity = 20),
                Product(name = "Ibuprofeno 600mg", price = 8.0, imageUrl = "https://via.placeholder.com/150", description = "Antiinflamatorio no esteroideo", sales = 80, quantity = 15),
                Product(name = "Aspirina 100mg", price = 4.0, imageUrl = "https://via.placeholder.com/150", description = "Analgésico y anticoagulante", sales = 120, quantity = 25),
                Product(name = "Omeprazol 20mg", price = 7.0, imageUrl = "https://via.placeholder.com/150", description = "Inhibidor de la bomba de protones", sales = 60, quantity = 30),
                Product(name = "Loratadina 10mg", price = 6.0, imageUrl = "https://via.placeholder.com/150", description = "Antihistamínico para alergias", sales = 70, quantity = 20)
            )
            products.forEach {
                Log.d("DataInitializer", "Insertando producto: ${it.name}")
                ProductRepository.insertProduct(context, it)
            }

            Log.d("DataInitializer", "Datos inicializados correctamente")
        }
    }
}
