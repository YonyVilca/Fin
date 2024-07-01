package com.example.f

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentInicio : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userNameTextView: TextView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var products: List<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_best_sellers)
        userNameTextView = view.findViewById(R.id.user_name_text_view)

        // Placeholder for username
        val userName = "Juan Pérez" // Este valor debe ser obtenido del login
        userNameTextView.text = "Hola, $userName"

        // Placeholder for products
        products = listOf(
            Product(1, "Producto 1", 10.0, "https://via.placeholder.com/150", "Descripción del producto 1"),
            Product(2, "Producto 2", 20.0, "https://via.placeholder.com/150", "Descripción del producto 2"),
            // Añadir más productos aquí
        )

        productAdapter = ProductAdapter(products) { product ->
            CartManager.addProduct(product)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        return view
    }
}
