package com.example.f.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.manager.CartManager
import com.example.f.model.Product
import com.example.f.R
import com.example.f.adapter.ProductAdapter
// Fragmento para mostrar la pantalla de inicio
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

        // Placeholder para el nombre de usuario
        val userName = "Juan Pérez" // Este valor debe ser obtenido del login
        userNameTextView.text = "Hola, $userName"

        // Placeholder para productos
        products = listOf(
            Product(1, "Producto 1", 10.0, "https://via.placeholder.com/150", "Descripción del producto 1", 0, 10),
            Product(2, "Producto 2", 20.0, "https://via.placeholder.com/150", "Descripción del producto 2", 0, 5),
            // Añadir más productos aquí
        )
        // Inicializar el adaptador del producto
        productAdapter = ProductAdapter(requireContext(), products) { product ->
            CartManager.addProduct(requireContext(), product, {
                Toast.makeText(
                    requireContext(),
                    "No hay stock disponible para ${product.name}",
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        return view
    }
}
