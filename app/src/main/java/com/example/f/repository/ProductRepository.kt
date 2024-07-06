package com.example.f.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.f.database.DatabaseBuilder
import com.example.f.model.Product
// Repositorio para gestionar operaciones relacionadas con productos

object ProductRepository {
    fun insertProduct(context: Context, product: Product) {
        val db = DatabaseBuilder.getInstance(context)
        db.productDao().insert(product)
    }

    fun updateProduct(context: Context, product: Product) {
        val db = DatabaseBuilder.getInstance(context)
        db.productDao().update(product)
    }

    fun getAllProducts(context: Context): LiveData<List<Product>> {
        val db = DatabaseBuilder.getInstance(context)
        return db.productDao().getAllProducts()
    }
}
