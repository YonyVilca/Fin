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

        // Obtener el nombre de usuario desde los argumentos
        val userName = arguments?.getString("USER_NAME") ?: "Usuario"
        userNameTextView.text = "Hola, $userName"

        // Lista de productos
        products = listOf(
            Product(1, "Producto 1", 10.0, "https://via.placeholder.com/150", "Descripción del producto 1"),
            Product(2, "Producto 2", 20.0, "https://via.placeholder.com/150", "Descripción del producto 2"),
            Product(3, "Producto 3", 30.0, "https://via.placeholder.com/150", "Descripción del producto 3"),
            Product(4, "Producto 4", 40.0, "https://via.placeholder.com/150", "Descripción del producto 4"),
            Product(5, "Producto 5", 50.0, "https://via.placeholder.com/150", "Descripción del producto 5"),
            Product(6, "Producto 6", 60.0, "https://via.placeholder.com/150", "Descripción del producto 6"),
            Product(7, "Producto 7", 70.0, "https://via.placeholder.com/150", "Descripción del producto 7"),
            Product(8, "Producto 8", 80.0, "https://via.placeholder.com/150", "Descripción del producto 8"),
            Product(9, "Producto 9", 90.0, "https://via.placeholder.com/150", "Descripción del producto 9"),
            Product(10, "Producto 10", 100.0, "https://via.placeholder.com/150", "Descripción del producto 10")
        )

        productAdapter = ProductAdapter(products) { product ->
            CartManager.addProduct(product)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        return view
    }

    companion object {
        fun newInstance(userName: String) = FragmentInicio().apply {
            arguments = Bundle().apply {
                putString("USER_NAME", userName)
            }
        }
    }
}



