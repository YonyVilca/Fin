package com.example.f

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentCarrito : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private lateinit var checkoutButton: Button
    private lateinit var cartAdapter: CartAdapter
    private var cartItems: MutableList<CartItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_cart)
        totalPriceTextView = view.findViewById(R.id.total_price_text_view)
        checkoutButton = view.findViewById(R.id.checkout_button)

        cartItems = CartManager.getCartItems().toMutableList()

        cartAdapter = CartAdapter(cartItems, { cartItem, newQuantity ->
            CartManager.updateQuantity(cartItem.product, newQuantity)
            updateCart()
        }, { cartItem ->
            CartManager.removeProduct(cartItem.product)
            updateCart()
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cartAdapter

        updateTotalPrice()

        checkoutButton.setOnClickListener {
            val intent = Intent(context, OrderDetailActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun updateCart() {
        cartItems.clear()
        cartItems.addAll(CartManager.getCartItems())
        cartAdapter.notifyDataSetChanged()
        updateTotalPrice()
    }

    private fun updateTotalPrice() {
        val totalPrice = CartManager.getTotalPrice()
        totalPriceTextView.text = "Total: $$totalPrice"
    }
}
