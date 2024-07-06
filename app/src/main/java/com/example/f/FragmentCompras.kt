package com.example.f

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentCompras : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var products: List<Product>
    private lateinit var filteredProducts: MutableList<Product>
    private lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_compras, container, false)
        recyclerView = view.findViewById(R.id.recycler_view_all_products)
        searchView = view.findViewById(R.id.search_view)

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

        filteredProducts = products.toMutableList()

        productAdapter = ProductAdapter(filteredProducts) { product ->
            CartManager.addProduct(product)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        setupSearchView()

        return view
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterProducts(newText)
                return true
            }
        })
    }

    private fun filterProducts(query: String?) {
        filteredProducts.clear()
        if (query.isNullOrEmpty()) {
            filteredProducts.addAll(products)
        } else {
            val lowerCaseQuery = query.lowercase()
            filteredProducts.addAll(products.filter {
                it.name.lowercase().contains(lowerCaseQuery)
            })
        }
        productAdapter.notifyDataSetChanged()
    }
}

