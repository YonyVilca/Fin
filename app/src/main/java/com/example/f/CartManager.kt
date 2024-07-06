package com.example.f

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun addProduct(context: Context, product: Product, onStockUnavailable: () -> Unit) {
        if (product.quantity <= 0) {
            onStockUnavailable()
            return
        }

        CoroutineScope(Dispatchers.IO).launch {
            val existingItem = cartItems.find { it.product.id == product.id }
            if (existingItem != null) {
                if (existingItem.quantity < product.quantity) {
                    existingItem.quantity++
                    updateProductQuantity(context, product, -1)
                } else {
                    withContext(Dispatchers.Main) {
                        onStockUnavailable()
                    }
                }
            } else {
                cartItems.add(CartItem(product, 1))
                updateProductQuantity(context, product, -1)
            }
        }
    }

    fun getCartItems(): List<CartItem> = cartItems

    fun updateQuantity(context: Context, product: Product, quantity: Int, onStockUnavailable: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val cartItem = cartItems.find { it.product.id == product.id }
            if (cartItem != null) {
                val difference = quantity - cartItem.quantity
                if (product.quantity + difference >= 0) {
                    cartItem.quantity = quantity
                    updateProductQuantity(context, product, -difference)
                } else {
                    withContext(Dispatchers.Main) {
                        onStockUnavailable()
                    }
                }
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

    fun clearCart() {
        cartItems.clear()
    }

    private fun updateProductQuantity(context: Context, product: Product, quantityChange: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val updatedProduct = product.copy(quantity = product.quantity + quantityChange)
            ProductRepository.updateProduct(context, updatedProduct)
        }
    }
}
