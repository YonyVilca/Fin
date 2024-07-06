package com.example.f.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.f.R
import com.example.f.activity.LoginActivity
import com.example.f.adapter.ProductAdapter
import com.example.f.manager.CartManager
import com.example.f.model.Product
import com.example.f.repository.ProductRepository

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
        val logoutButton: Button = view.findViewById(R.id.logout_button)

        // Placeholder para el nombre de usuario
        val userName = "Juan Pérez" // Este valor debe ser obtenido del login
        userNameTextView.text = "Hola, $userName"

        // Configurar el adaptador del producto
        productAdapter = ProductAdapter(requireContext(), emptyList()) { product ->
            CartManager.addProduct(requireContext(), product) {
                Toast.makeText(requireContext(), "No hay stock disponible para ${product.name}", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter

        // Obtener productos desde el repositorio
        ProductRepository.getAllProducts(requireContext()).observe(viewLifecycleOwner, { productList ->
            products = productList
            productAdapter.updateProducts(products)
        })

        // Configurar el botón de cerrar sesión
        logoutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        return view
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Cerrar sesión")
            .setMessage("¿Estás seguro de que quieres cerrar sesión?")
            .setPositiveButton("Sí") { dialog, _ ->
                dialog.dismiss()
                logout()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun logout() {
        // Aquí puedes agregar la lógica para cerrar sesión, como limpiar las preferencias compartidas o eliminar el token de autenticación
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
    }
}
