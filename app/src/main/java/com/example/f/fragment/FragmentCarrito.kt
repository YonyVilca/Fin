package com.example.f.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.model.CartItem
import com.example.f.manager.CartManager
import com.example.f.R
import com.example.f.activity.OrderDetailActivity
import com.example.f.adapter.CartAdapter
// Fragmento para mostrar el carrito de compra
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
        // Inicializar el adaptador del carrito
        cartAdapter = CartAdapter(requireContext(), cartItems, { cartItem, newQuantity ->
            CartManager.updateQuantity(requireContext(), cartItem.product, newQuantity) {
                Toast.makeText(
                    requireContext(),
                    "No hay stock suficiente para ${cartItem.product.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
            updateCart()
        }, { cartItem ->
            CartManager.removeProduct(requireContext(), cartItem.product)
            updateCart()
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = cartAdapter

        updateTotalPrice()
        // Configurar listener para el botón de finalizar compra
        checkoutButton.setOnClickListener {
            val intent = Intent(context, OrderDetailActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        updateCart()
    }
    // Actualizar la lista del carrito y el precio total
    private fun updateCart() {
        cartItems.clear()
        cartItems.addAll(CartManager.getCartItems())
        cartAdapter.notifyDataSetChanged()
        updateTotalPrice()
    }
    // Actualizar el precio total
    private fun updateTotalPrice() {
        val totalPrice = CartManager.getTotalPrice()
        totalPriceTextView.text = "Total: S/$totalPrice"
    }
}
