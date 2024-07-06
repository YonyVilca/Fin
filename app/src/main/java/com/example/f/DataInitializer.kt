package com.example.f

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DataInitializer {

    fun initializeData(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            // Llenar la tabla de usuarios
            val users = listOf(
                User(email = "user1@example.com", password = "password1"),
                User(email = "user2@example.com", password = "password2")
            )


            // Llenar la tabla de productos
            val products = listOf(
                Product(name = "Producto 1", price = 10.0, imageUrl = "https://via.placeholder.com/150", description = "Descripción del producto 1", sales = 50, quantity = 5),
                Product(name = "Producto 2", price = 20.0, imageUrl = "https://via.placeholder.com/150", description = "Descripción del producto 2", sales = 150, quantity = 5),
                Product(name = "Producto 3", price = 30.0, imageUrl = "https://via.placeholder.com/150", description = "Descripción del producto 3", sales = 200, quantity = 5),
                Product(name = "Producto 4", price = 40.0, imageUrl = "https://via.placeholder.com/150", description = "Descripción del producto 4", sales = 300, quantity = 5),
                Product(name = "Producto 5", price = 50.0, imageUrl = "https://via.placeholder.com/150", description = "Descripción del producto 5", sales = 250, quantity = 5)
            )
            products.forEach { ProductRepository.insertProduct(context, it) }
        }
    }
}
