package com.example.f

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.ProductAdapter
import com.example.f.Product

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
            Product(1, "Producto 1", 10.0, "https://via.placeholder.com/150"),
            Product(2, "Producto 2", 20.0, "https://via.placeholder.com/150"),
            // Añadir más productos aquí
        )

        productAdapter = ProductAdapter(products) { product ->
            // Acción al añadir al carrito
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        return view
    }
}