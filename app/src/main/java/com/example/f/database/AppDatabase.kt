package com.example.f.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.f.model.Product
import com.example.f.dao.ProductDao
import com.example.f.model.User
import com.example.f.dao.UserDao
// Definición de la base de datos con las entidades y DAOs correspondientes
@Database(entities = [User::class, Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
}
