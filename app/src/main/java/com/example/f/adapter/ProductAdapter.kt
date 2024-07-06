package com.example.f.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.f.manager.CartManager
import com.example.f.model.Product
import com.example.f.R
import com.example.f.activity.ProductDetailActivity
import com.squareup.picasso.Picasso
// Adaptador para mostrar los productos en un RecyclerView
class ProductAdapter(
    private val context: Context,
    private val productList: List<Product>,
    private val onAddToCartClickListener: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    // ViewHolder para mantener las referencias a las vistas de un elemento del producto
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val addToCartButton: Button = itemView.findViewById(R.id.add_to_cart_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // Inflar la vista de un elemento del producto y crear un ViewHolder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        // Vincular los datos de un producto a las vistas del ViewHolder
        val product = productList[position]
        holder.productName.text = product.name
        holder.productPrice.text = "S/${product.price}"
        Picasso.get().load(product.imageUrl).into(holder.productImage)
        // Configurar el listener para el botón de añadir al carrito
        holder.addToCartButton.setOnClickListener {
            CartManager.addProduct(context, product, {
                Toast.makeText(
                    context,
                    "No hay stock disponible para ${product.name}",
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("product", product)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = productList.size
}
