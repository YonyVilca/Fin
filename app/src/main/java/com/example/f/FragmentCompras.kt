package com.example.f

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            Product(1, "Producto 1", 10.0, "https://via.placeholder.com/150", "Descripción del producto 1", 50, 100),
            Product(2, "Producto 2", 20.0, "https://via.placeholder.com/150", "Descripción del producto 2", 150, 200),
            Product(3, "Producto 3", 30.0, "https://via.placeholder.com/150", "Descripción del producto 3", 200, 300),
            Product(4, "Producto 4", 40.0, "https://via.placeholder.com/150", "Descripción del producto 4", 300, 400),
            Product(5, "Producto 5", 50.0, "https://via.placeholder.com/150", "Descripción del producto 5", 250, 500)
        )

        productAdapter = ProductAdapter(products) { product ->
            CartManager.addProduct(requireContext(), product)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        return view
    }
}
