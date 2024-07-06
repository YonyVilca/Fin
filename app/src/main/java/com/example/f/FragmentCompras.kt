package com.example.f

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentCompras : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var products: List<Product>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_compras, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_all_products)

        // Placeholder for products
        products = listOf(
            Product(1, "Producto 1", 10.0, "https://via.placeholder.com/150", "Descripción del producto 1", 0, 10),
            Product(2, "Producto 2", 20.0, "https://via.placeholder.com/150", "Descripción del producto 2", 0, 5),
            // Añadir más productos aquí
        )

        productAdapter = ProductAdapter(requireContext(), products) { product ->
            CartManager.addProduct(requireContext(), product, {
                Toast.makeText(requireContext(), "No hay stock disponible para ${product.name}", Toast.LENGTH_SHORT).show()
            })
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        return view
    }
}
