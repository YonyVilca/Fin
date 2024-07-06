package com.example.f

import android.content.Context
import androidx.lifecycle.LiveData

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
