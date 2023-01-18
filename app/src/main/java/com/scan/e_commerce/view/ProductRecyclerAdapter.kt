package com.scan.e_commerce.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scan.e_commerce.databinding.ProductsListItemBinding
import com.scan.e_commerce.presentation.ProductUiState

class ProductRecyclerAdapter(private val products: List<ProductUiState.ProductModel>, val itemClickListener: (product: ProductUiState.ProductModel) -> Unit ): RecyclerView.Adapter<ProductRecyclerAdapter.ProductViewHolder>() {


    class ProductViewHolder(private val binding: ProductsListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductUiState.ProductModel) {
            binding.product = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(ProductsListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
        holder.itemView.setOnClickListener { itemClickListener(products[position]) }
    }

    override fun getItemCount(): Int = products.size
}