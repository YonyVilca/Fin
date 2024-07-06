package com.example.f.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
// Entidad Producto para la base de datos
@Parcelize
@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val description: String,
    val sales: Int,
    val quantity: Int
) : Parcelable
