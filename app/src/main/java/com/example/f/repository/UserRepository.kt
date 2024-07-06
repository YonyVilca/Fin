package com.example.f.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.f.database.DatabaseBuilder
import com.example.f.model.User

// Repositorio para gestionar operaciones relacionadas con usuarios
object UserRepository {
    fun registerUser(context: Context, user: User) {
        val db = DatabaseBuilder.getInstance(context)
        db.userDao().insert(user)
    }

    fun loginUser(context: Context, email: String, password: String): LiveData<User?> {
        val db = DatabaseBuilder.getInstance(context)
        return db.userDao().login(email, password)
    }
}
