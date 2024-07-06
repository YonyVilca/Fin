package com.example.f.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.f.model.Product

// DAO para acceder a los productos en la base de datos
@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Query("DELETE FROM products")
    fun clearTable()

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<Product>>
}
