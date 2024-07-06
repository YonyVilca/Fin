package com.example.f

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CartAdapter(
    private val context: Context,
    private val cartItemList: List<CartItem>,
    private val onQuantityChangedListener: (CartItem, Int) -> Unit,
    private val onRemoveClickListener: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productQuantity: TextView = itemView.findViewById(R.id.product_quantity)
        val increaseButton: Button = itemView.findViewById(R.id.increase_button)
        val decreaseButton: Button = itemView.findViewById(R.id.decrease_button)
        val removeButton: Button = itemView.findViewById(R.id.remove_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItemList[position]
        holder.productName.text = cartItem.product.name
        holder.productPrice.text = "S/${cartItem.product.price}"
        holder.productQuantity.text = cartItem.quantity.toString()
        Picasso.get().load(cartItem.product.imageUrl).into(holder.productImage)

        holder.increaseButton.setOnClickListener {
            onQuantityChangedListener(cartItem, cartItem.quantity + 1)
        }
        holder.decreaseButton.setOnClickListener {
            if (cartItem.quantity > 1) {
                onQuantityChangedListener(cartItem, cartItem.quantity - 1)
            }
        }
        holder.removeButton.setOnClickListener {
            onRemoveClickListener(cartItem)
        }
    }

    override fun getItemCount() = cartItemList.size
}
