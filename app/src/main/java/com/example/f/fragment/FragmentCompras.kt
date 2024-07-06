package com.example.f.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.R
import com.example.f.adapter.ProductAdapter
import com.example.f.manager.CartManager
import com.example.f.model.Product
import com.example.f.repository.ProductRepository

// Fragmento para mostrar la lista de compras
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

        // Inicializar el adaptador del producto
        productAdapter = ProductAdapter(requireContext(), emptyList()) { product ->
            CartManager.addProduct(requireContext(), product) {
                Toast.makeText(requireContext(), "No hay stock disponible para ${product.name}", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        // Obtener productos desde el repositorio
        ProductRepository.getAllProducts(requireContext()).observe(viewLifecycleOwner, Observer { productList ->
            products = productList
            productAdapter.updateProducts(products)
        })

        return view
    }
}
