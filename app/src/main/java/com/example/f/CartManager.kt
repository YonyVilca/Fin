package com.example.f

import com.example.f.CartItem
import com.example.f.Product

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addProduct(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(product, 1))
        }
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun updateQuantity(product: Product, quantity: Int) {
        val cartItem = cartItems.find { it.product.id == product.id }
        cartItem?.quantity = quantity
    }

    fun removeProduct(product: Product) {
        cartItems.removeAll { it.product.id == product.id }
    }

    fun getTotalPrice(): Double {
        return cartItems.sumByDouble { it.product.price * it.quantity }
    }
}