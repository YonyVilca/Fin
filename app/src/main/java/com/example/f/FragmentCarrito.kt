package com.example.f

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.CartAdapter
import com.example.f.CartItem
import com.example.f.Product

class FragmentCarrito : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var cartAdapter: CartAdapter
    private lateinit var cartItems: MutableList<CartItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_cart)
        totalPriceTextView = view.findViewById(R.id.total_price_text_view)
        checkoutButton = view.findViewById(R.id.checkout_button)

        // Placeholder for cart items
        cartItems = mutableListOf(
            CartItem(Product(1, "Producto 1", 10.0, "https://via.placeholder.com/150"), 1),
            CartItem(Product(2, "Producto 2", 20.0, "https://via.placeholder.com/150"), 2)
            // Añadir más items aquí
        )

        cartAdapter = CartAdapter(cartItems, { cartItem, newQuantity ->
            // Acción al cambiar la cantidad
            cartItem.quantity = newQuantity
            cartAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }, { cartItem ->
            // Acción al eliminar el item del carrito
            cartItems.remove(cartItem)
            cartAdapter.notifyDataSetChanged()
            updateTotalPrice()
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cartAdapter

        updateTotalPrice()

        checkoutButton.setOnClickListener {
            // Acción al realizar la compra
        }

        return view
    }

    private fun updateTotalPrice() {
        val totalPrice = cartItems.sumByDouble { it.product.price * it.quantity }
        totalPriceTextView.text = "Total: $$totalPrice"
    }
}