package com.example.f

import android.content.Context
import androidx.lifecycle.LiveData

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
