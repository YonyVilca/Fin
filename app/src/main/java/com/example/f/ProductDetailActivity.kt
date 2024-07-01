package com.example.f

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

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
            productPrice.text = "$${product.price}"
            productDescription.text = "Descripción del producto: ${product.description}"
            Picasso.get().load(product.imageUrl).into(productImage)

            addToCartButton.setOnClickListener {
                CartManager.addProduct(product)
                finish()
            }

            backButton.setOnClickListener {
                finish()
            }
        }
    }
}
