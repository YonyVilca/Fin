package com.example.f.manager

import android.content.Context
import com.example.f.model.CartItem
import com.example.f.model.Product
import com.example.f.repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
// Objeto para gestionar el carrito de compras
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
    // Obtener todos los elementos del carrito
    fun getCartItems(): List<CartItem> = cartItems
    // Actualizar la cantidad de un producto en el carrito
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
    // Remover un producto del carrito
    fun removeProduct(context: Context, product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            val cartItem = cartItems.find { it.product.id == product.id }
            if (cartItem != null) {
                updateProductQuantity(context, product, cartItem.quantity)
                cartItems.remove(cartItem)
            }
        }
    }

    // Obtener el precio total de los elementos en el carrito
    fun getTotalPrice(): Double {
        return cartItems.sumByDouble { it.product.price * it.quantity }
    }
    // Limpiar el carrito
    fun clearCart() {
        cartItems.clear()
    }
    // Actualizar la cantidad de un producto en el inventario
    private fun updateProductQuantity(context: Context, product: Product, quantityChange: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val updatedProduct = product.copy(quantity = product.quantity + quantityChange)
            ProductRepository.updateProduct(context, updatedProduct)
        }
    }
}
