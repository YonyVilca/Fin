package com.example.f.model

import androidx.room.Entity
import androidx.room.PrimaryKey
// Entidad Usuario para la base de datos
@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val password: String
)
