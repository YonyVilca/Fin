package com.example.f.activity

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.f.R
import com.example.f.manager.CartManager
import com.example.f.model.Product
import com.squareup.picasso.Picasso

// Actividad para mostrar los detalles de un producto
class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product = intent.getParcelableExtra<Product>("product")

        if (product != null) {
            val productImage: ImageView = findViewById(R.id.product_image)
            val productName: TextView = findViewById(R.id.product_name)
            val productPrice: TextView = findViewById(R.id.product_price)
            val productDescription: TextView = findViewById(R.id.product_description)
            val addToCartButton: Button = findViewById(R.id.add_to_cart_button)
            val backButton: Button = findViewById(R.id.back_button)

            productName.text = product.name
            productPrice.text = "S/${product.price}"
            productDescription.text = "Descripción del producto: ${product.description}"
            val temporaliimf : Icon = Icon.createWithResource(this, R.drawable.sinimage)
            productImage.setImageIcon(temporaliimf)

            addToCartButton.setOnClickListener {
                // Añadir producto al carrito
                CartManager.addProduct(this, product) {
                    Toast.makeText(this, "No hay stock disponible para ${product.name}", Toast.LENGTH_SHORT).show()
                }
                // Mostrar un Toast de confirmación
                Toast.makeText(this, "Producto añadido al carrito", Toast.LENGTH_SHORT).show()
                // Regresar a la pantalla anterior
                finish()
            }

            backButton.setOnClickListener {
                finish()
            }
        }
    }
}
