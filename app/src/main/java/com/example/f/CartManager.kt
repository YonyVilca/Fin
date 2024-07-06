package com.example.f

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addProduct(context: Context, product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            val existingItem = cartItems.find { it.product.id == product.id }
            if (existingItem != null) {
                existingItem.quantity++
            } else {
                cartItems.add(CartItem(product, 1))
            }
            updateProductQuantity(context, product, -1)
        }
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun updateQuantity(context: Context, product: Product, quantity: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val cartItem = cartItems.find { it.product.id == product.id }
            if (cartItem != null) {
                val difference = quantity - cartItem.quantity
                cartItem.quantity = quantity
                updateProductQuantity(context, product, -difference)
            }
        }
    }

    fun removeProduct(context: Context, product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            val cartItem = cartItems.find { it.product.id == product.id }
            if (cartItem != null) {
                updateProductQuantity(context, product, cartItem.quantity)
                cartItems.remove(cartItem)
            }
        }
    }

    fun getTotalPrice(): Double {
        return cartItems.sumByDouble { it.product.price * it.quantity }
    }

    private fun updateProductQuantity(context: Context, product: Product, quantityChange: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val updatedProduct = product.copy(quantity = product.quantity + quantityChange)
            ProductRepository.updateProduct(context, updatedProduct)
        }
    }
}
